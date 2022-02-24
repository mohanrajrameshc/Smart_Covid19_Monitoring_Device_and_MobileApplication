package com.mohanraj.smartcovid19monitring.User;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.mohanraj.smartcovid19monitring.Admin.AdminActivity;
import com.mohanraj.smartcovid19monitring.Admin.AdminChangePassword;
import com.mohanraj.smartcovid19monitring.MainActivity;
import com.mohanraj.smartcovid19monitring.R;

public class Userdashboard extends AppCompatActivity {
    TextView uid,name;
    ImageView qrimage;
    String qrtext1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_userdashboard);
        uid = (TextView) findViewById(R.id.uid);

        qrimage = (ImageView) findViewById(R.id.qrimage);


        try {
            FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
            //Toast.makeText(this, "" + currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();
            String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
            uid.setText(currentuser);


        }
        catch (IllegalStateException e) {
            //e.printStackTrace();
            Toast.makeText(Userdashboard.this,"Get UId error"+e.getMessage(),Toast.LENGTH_LONG).show();
        }
        qrtext1 =  uid.getText().toString();
        StringBuilder textToSend = new StringBuilder();
        textToSend.append(qrtext1);
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(textToSend.toString(), BarcodeFormat.QR_CODE, 600, 600);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            qrimage.setImageBitmap(bitmap);
            qrimage.setVisibility(View.VISIBLE);
        } catch (WriterException e) {
            //e.printStackTrace();
            Toast.makeText(Userdashboard.this,"Qrcode error"+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public void Thermometer(View view) {
        String userid=uid.getText().toString();
        Intent intent=new Intent(Userdashboard.this,UserMonitorring.class);
        intent.putExtra("uid",userid);
        startActivity(intent);
    }

    public void timeline(View view) {
        Intent intent=new Intent(getApplicationContext(),UserHistory.class);
        startActivity(intent);
    }

    public void quarentinetlist(View view) {
        Intent intent=new Intent(getApplicationContext(),QuarantinedRecord.class);
        startActivity(intent);
    }

    public void help(View view) {
        Intent intent=new Intent(getApplicationContext(), Help.class);
        startActivity(intent);
    }

    public void changepassword(View view) {
        Intent intent=new Intent(getApplicationContext(), AdminChangePassword.class);
        startActivity(intent);
    }

    public void logout(View view) {
        AlertDialog.Builder dialog=new AlertDialog.Builder(Userdashboard.this);
        dialog.setMessage("Do you want to logout!");
        dialog.setCancelable(false);
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(Userdashboard.this, MainActivity.class);
                startActivity(intent);
                Userdashboard.this.finish();

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
        AlertDialog.Builder dialog=new AlertDialog.Builder(Userdashboard.this);
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