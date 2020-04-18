package com.xenox.loginpage;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xenox.loginpage.view.MyEditText;
import com.xenox.loginpage.view.MyTextView;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class XenoxPayLogon extends AppCompatActivity {

    public static  String name="";
    public static  String Email="";
    public static  String Password="";
    public static  String Mobile="";
    public static  String UserId="";
    public static String WalletId="";
    public  static String Status = "";
    public static String mainWalletBalance ="";
    MainAPIInterface  mainAPIInterface;
   private MyEditText edtMobile,edtPassword;
   private MyTextView signin1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_xenox_pay_logon);

          mainAPIInterface = ApiUtils.getAPIService();
        edtMobile= (MyEditText)findViewById(R.id.edtMobile);
        edtPassword =(MyEditText)findViewById(R.id.edtPassword);
        signin1 =(MyTextView)findViewById(R.id.signin1);


        signin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (edtMobile.getText().toString().length() < 5) {
                    edtMobile.setFocusable(true);
                    edtMobile.setError("Eneter mobile number");
                } else if (edtPassword.getText().toString().length() < 5) {
                    edtPassword.setFocusable(true);
                    edtPassword.setError("Enter password");
                } else {
                    userLoginRequest(XenoxPayLogon.this,edtMobile.getText().toString(),edtPassword.getText().toString(),"dgagdhafxghasxchgzcxhb");
                }

            }
        });


    }

    private  void userLoginRequest( final AppCompatActivity activity, String strMobile, final String strPassword, String strFcmId) {
        String xAccessToken = "mykey";



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

    private   void getWalletBalance(String wallet_id) {

        MainAPIInterface  mainAPIInterface = ApiUtils.getAPIService();

        String xAccessToken = "mykey";



        MultipartBody.Part seller_id_body = MultipartBody.Part.createFormData("wallet_id", wallet_id);

        mainAPIInterface.getWalletTransactions(xAccessToken, seller_id_body).enqueue(new Callback<GetWalletTransactionsOutput>() {
            @Override
            public void onResponse(Call<GetWalletTransactionsOutput> call, Response<GetWalletTransactionsOutput> response) {

                if (response.isSuccessful()) {

                    if (response.body().getSuccess().equalsIgnoreCase("1")) {

                        mainWalletBalance = response.body().getBalance();

                        Intent intent = new Intent(XenoxPayLogon.this,XenoxPayPaymentActivity.class);

                        startActivity(intent);


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
