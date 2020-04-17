package com.xenox.loginpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements RevealCourtPlaceCallbacks{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String naw1 =LoginClass.getDistanceInKm();




        LoginClass.userLoginRequest(MainActivity.this,"9029526913","123456","2414414fsffsdfsfsfsfsa2",this);

        if (LoginClass.Status.equalsIgnoreCase("1")){
            String name =  LoginClass.name;
            String mobile = LoginClass.Mobile;

            Log.d("name25242",name);
        }

        Log.d("name25242",naw1);










    }

    @Override
    public void onSuccess(@NonNull String value) {

        Log.d("name25242",value);

    }

    @Override
    public void onError(@NonNull Throwable throwable) {

    }
}
