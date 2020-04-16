package com.xenox.loginpage;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface MainAPIInterface {


    @Multipart
    @POST(Constants.USER_LOGIN)
    Call<UserProfileOutput> userLogin(
            @Header("X-API-KEY") String xAccessToken,
            @Part MultipartBody.Part mobile,
            @Part MultipartBody.Part fcm_id,
            @Part MultipartBody.Part password
    );

}
