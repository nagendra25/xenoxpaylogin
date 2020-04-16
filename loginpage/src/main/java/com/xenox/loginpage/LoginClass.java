package com.xenox.loginpage;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.util.Log;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginClass {


    public static String getDistanceInKm(){
        return "done this";
    }
    public static JSONObject userLoginRequest( final AppCompatActivity activity, String strMobile, final String strPassword, String strFcmId) {
        String xAccessToken = "mykey";
        final JSONObject object = new JSONObject();



        MainAPIInterface  mainAPIInterface = ApiUtils.getAPIService();




        MultipartBody.Part phone_body = MultipartBody.Part.createFormData("phone_no", strMobile);


        MultipartBody.Part password_body = MultipartBody.Part.createFormData("password", strPassword);

        MultipartBody.Part fcm_id_body = MultipartBody.Part.createFormData("fcm_id", strFcmId);


        mainAPIInterface.userLogin(xAccessToken, phone_body, password_body, fcm_id_body).enqueue(new Callback<UserProfileOutput>() {
            @Override
            public void onResponse(Call<UserProfileOutput> call, Response<UserProfileOutput> response) {

                if (response.isSuccessful()) {


                    if (response.body().getSuccess().equalsIgnoreCase("1")) {

                        Toast.makeText(activity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        String saveName = response.body().getProfile().getUsername();
                        String saveUserEmail = response.body().getProfile().getEmail();
                        String saveUserPassword = strPassword;
                        String saveUserMobile = response.body().getProfile().getMobile();
                        String saveUserId = response.body().getProfile().getUserId();
                        String saveWalletId = response.body().getProfile().getWallet_id();

                        try {
                            object.put("Username", saveName);
                            object.put("Password", saveUserPassword);
                            object.put("Email", saveUserEmail);
                            object.put("Mobile", saveUserMobile);
                            object.put("UserId", saveUserId);
                            object.put("Wallet_id", saveWalletId);


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        /*Intent i = new Intent(SignInActivity.this, HomeActivity.class);
                        startActivity(i);
                        finish();*/
                    }

                }
            }
            @Override
            public void onFailure(Call<UserProfileOutput> call, Throwable t) {

                Log.i("tag", t.getMessage().toString());
            }
        });

        return object;


    }
}
