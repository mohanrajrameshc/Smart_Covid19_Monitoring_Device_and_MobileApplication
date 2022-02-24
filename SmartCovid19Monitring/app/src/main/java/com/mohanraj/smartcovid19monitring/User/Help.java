package com.mohanraj.smartcovid19monitring.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.mohanraj.smartcovid19monitring.R;

public class Help extends AppCompatActivity {
    Button central,state,mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_help);
        central=(Button)findViewById(R.id.central);
        state=(Button)findViewById(R.id.state);
        mail=(Button)findViewById(R.id.email);

        central.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent callIntent = new Intent(Intent.ACTION_NEW_OUTGOING_CALL);
                callIntent.setData(Uri.parse("tel:"+number));
                startActivity(callIntent);*/
                String number="911123978043";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+number));
                startActivity(intent);


            }
        });

        state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number="04429510500";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+number));
                startActivity(intent);

            }
        });

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                Intent intobj = new Intent(Intent.ACTION_SEND);
                intobj.setType("message/rfc822");
                intobj.putExtra(Intent.EXTRA_EMAIL, new String[]{"ncov2019@gmail.com"});
                intobj.putExtra(Intent.EXTRA_SUBJECT, "Help!");
                intobj.putExtra(Intent.EXTRA_TEXT, "UserId:"+currentuser);
                startActivity(Intent.createChooser(intobj, "Choose Mail App"));

            }
        });
    }
}