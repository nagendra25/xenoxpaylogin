package com.xenox.loginpage;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class XenoxPayPaymentActivity extends AppCompatActivity {

    private TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xenox_pay_payment);
        textView1 =(TextView)findViewById(R.id.textView1) ;

        String mainWalletBalance = getIntent().getExtras().getString("mainWalletBalance","0");
        textView1.setText("Your Wallet Balance is "+mainWalletBalance);

    }
}
