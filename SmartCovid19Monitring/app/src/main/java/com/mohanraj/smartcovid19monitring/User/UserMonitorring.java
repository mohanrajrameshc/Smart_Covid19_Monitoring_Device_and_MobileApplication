package com.mohanraj.smartcovid19monitring.User;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mohanraj.smartcovid19monitring.Bean.MonitoringBean;
import com.mohanraj.smartcovid19monitring.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserMonitorring extends AppCompatActivity {
    TextView temperature,lastseend;
    LinearLayout layout;
    DatabaseReference rootRef, demoRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_user_monitorring);



        layout=(LinearLayout)findViewById(R.id.layout);
        temperature=(TextView)findViewById(R.id.temp);
        lastseend=(TextView)findViewById(R.id.lastseen);
        Intent intent=getIntent();
        //String uid=intent.getStringExtra("uid");
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        //Toast.makeText(this, "" + currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference ref = database.getReference("TEMPERATURE/"+currentuser);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Float min,max,avg;

                min=36.00f;
                avg=37.50f;
                max=38.50f;
                MonitoringBean post = dataSnapshot.getValue(MonitoringBean.class);
                Float f = post.getCELSIUS();
                String d=post.getDATE();
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                Float celsiu = Float.valueOf(decimalFormat.format(f));
                String celsius=celsiu.toString();
                temperature.setText(celsius.toString());
                lastseend.setText(d);
                if(celsiu<min)
                {
                    layout.setBackgroundColor(Color.BLUE);
                }
                if((celsiu>=min)&&(celsiu<avg))
                {
                    layout.setBackgroundColor(Color.GREEN);
                }

                if((celsiu>=avg)&&(celsiu<max))
                {
                    layout.setBackgroundColor(Color.YELLOW);

                }
                if(celsiu>=max)
                {

                    layout.setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UserMonitorring.this,"The read failed: " + databaseError.getCode(),Toast.LENGTH_LONG).show();
            }
        });

        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        thread.start();


    }
}