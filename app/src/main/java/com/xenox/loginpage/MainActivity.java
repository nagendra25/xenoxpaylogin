package com.xenox.loginpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

       /* Intent intent = new Intent(MainActivity.this,XenoxPayLogin.class);
        startActivity(intent);*/




       LoginClass.userLoginRequest(MainActivity.this,"9029526913","123456","2414414fsffsdfsfsfsfsa2",this);


    }

    @Override
    public void onSuccess(@NonNull String value) {

        Log.d("name25242",value);

    }

    @Override
    public void onError(@NonNull Throwable throwable) {

    }
}
