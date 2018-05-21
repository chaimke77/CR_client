package com.example.user.cr_client.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.cr_client.R;
import com.example.user.cr_client.backend.MyService;
import com.example.user.cr_client.entities.Order;

public class MainActivity extends AppCompatActivity {

    public static final String mBroadcastAction = "com.example.user.carrentalapplication.A_CUSTOM_INTENT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(getBaseContext(), MyService.class));


    }
}

