package com.xenox.loginpage;

public class ApiUtils {

    private ApiUtils() {
    }


    public static final String BASE_URL = BuildConfig.BASE_URL;

    public static MainAPIInterface getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(MainAPIInterface.class);
    }

}
