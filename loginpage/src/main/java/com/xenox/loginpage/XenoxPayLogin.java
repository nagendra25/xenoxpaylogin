package com.xenox.loginpage;


import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public  class XenoxPayLogin extends AppCompatActivity {

    public static  String name="";
    public static  String Email="";
    public static  String Password="";
    public static  String Mobile="";
    public static  String UserId="";
    public static String WalletId="";
    public  static String Status = "";
    public static String mainWalletBalance ="";

    public UserProfileOutput userProfileOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xenox_pay_login);
    }


    public static void userLoginRequest(final AppCompatActivity activity, String strMobile, final String strPassword, String strFcmId,
                                        @Nullable final RevealCourtPlaceCallbacks callbacks) {
        String xAccessToken = "mykey";

        MainAPIInterface  mainAPIInterface = ApiUtils.getAPIService();

        MultipartBody.Part phone_body = MultipartBody.Part.createFormData("phone_no", strMobile);


        MultipartBody.Part password_body = MultipartBody.Part.createFormData("password", strPassword);

        MultipartBody.Part fcm_id_body = MultipartBody.Part.createFormData("fcm_id", strFcmId);


        mainAPIInterface.userLogin(xAccessToken, phone_body, password_body, fcm_id_body).enqueue(new Callback<UserProfileOutput>() {
            @Override
            public void onResponse(Call<UserProfileOutput> call, Response<UserProfileOutput> response) {

                if (response.isSuccessful()) {


                    if (response.body().getSuccess().equalsIgnoreCase("1")) {
                        Status = "1";

                        Toast.makeText(activity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        name = response.body().getProfile().getUsername();
                        Email = response.body().getProfile().getEmail();
                        Password = strPassword;
                        Mobile = response.body().getProfile().getMobile();
                        UserId = response.body().getProfile().getUserId();
                        WalletId = response.body().getProfile().getWallet_id();
                        getWalletBalance(WalletId);


                        JSONObject object = new JSONObject();
                        try {
                            object.put("name",name);
                            object.put("Email",Email);
                            object.put("Password",Password);
                            object.put("Mobile",Mobile);
                            object.put("UserId",UserId);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (callbacks != null)
                            callbacks.onSuccess(object.toString());
                    }
                    Status ="0";
                }
            }
            @Override
            public void onFailure(Call<UserProfileOutput> call, Throwable t) {

                Log.i("tag", t.getMessage().toString());
            }
        });




    }

    public static void getWalletBalance(String wallet_id) {

        MainAPIInterface  mainAPIInterface = ApiUtils.getAPIService();

        String xAccessToken = "mykey";



        MultipartBody.Part seller_id_body = MultipartBody.Part.createFormData("wallet_id", wallet_id);

        mainAPIInterface.getWalletTransactions(xAccessToken, seller_id_body).enqueue(new Callback<GetWalletTransactionsOutput>() {
            @Override
            public void onResponse(Call<GetWalletTransactionsOutput> call, Response<GetWalletTransactionsOutput> response) {

                if (response.isSuccessful()) {

                    if (response.body().getSuccess().equalsIgnoreCase("1")) {

                        mainWalletBalance = response.body().getBalance();

                    }

                }
            }

            @Override
            public void onFailure(Call<GetWalletTransactionsOutput> call, Throwable t) {
                Log.i("tag", t.getMessage().toString());
            }
        });

    }
}
