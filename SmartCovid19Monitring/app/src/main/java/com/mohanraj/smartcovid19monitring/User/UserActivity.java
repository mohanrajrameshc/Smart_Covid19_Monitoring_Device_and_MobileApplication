package com.mohanraj.smartcovid19monitring.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

import com.mohanraj.smartcovid19monitring.R;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTitle("Login!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }
}