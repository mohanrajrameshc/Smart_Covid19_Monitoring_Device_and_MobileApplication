package com.mohanraj.smartcovid19monitring.Admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.mohanraj.smartcovid19monitring.MainActivity;
import com.mohanraj.smartcovid19monitring.R;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_admin);
    }

    public void alertlist(View view) {
        Intent intent=new Intent(this, AlertList.class);
        startActivity(intent);
    }
    public void quarentinetlist(View view) {
        Intent intent=new Intent(this, QuarantineList.class);
        startActivity(intent);
    }
    public void releasiedlist(View view) {
        Intent intent=new Intent(this, Realizedlist.class);
        startActivity(intent);
    }
    public void addadmin(View view) {
        Intent intent=new Intent(this, AddAdmin.class);
        startActivity(intent);
    }
    public void changepassword(View view) {
        Intent intent=new Intent(this, AdminChangePassword.class);
        startActivity(intent);
    }

    public void scan(View view) {
        Intent intent=new Intent(this, Scan.class);
        startActivity(intent);
    }

    public void logout(View view) {
        AlertDialog.Builder dialog=new AlertDialog.Builder(AdminActivity.this);
        dialog.setMessage("Do you want to logout!");
        dialog.setCancelable(false);
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(AdminActivity.this, MainActivity.class);
                startActivity(intent);
                AdminActivity.this.finish();

            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();


            }
        });
        AlertDialog alert=dialog.create();
        alert.show();


    }

    public void exit(View view) {
        AlertDialog.Builder dialog=new AlertDialog.Builder(AdminActivity.this);
        dialog.setMessage("Do you want to exit");
        dialog.setCancelable(false);
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
                System.exit(0);

            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();


            }
        });
        AlertDialog alert=dialog.create();
        alert.show();


    }
}