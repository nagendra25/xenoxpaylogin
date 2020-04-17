package com.xenox.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String naw1 =LoginClass.getDistanceInKm();


       JSONObject api242 =LoginClass.userLoginRequest(MainActivity.this,"9029526913","123456","2414414fsffsdfsfsfsfsa2");






    }
}
