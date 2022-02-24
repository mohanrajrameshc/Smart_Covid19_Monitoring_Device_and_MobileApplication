package com.mohanraj.smartcovid19monitring.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mohanraj.smartcovid19monitring.Bean.MonitoringBean;
import com.mohanraj.smartcovid19monitring.Bean.QuarantinedAdpBean;
import com.mohanraj.smartcovid19monitring.R;
import com.mohanraj.smartcovid19monitring.RecyclerView.QuarantinedAdapter;
import com.mohanraj.smartcovid19monitring.User.UserMonitorring;

import java.text.DecimalFormat;

public class Scan extends AppCompatActivity {
    TextView temperature,lastseend;
    LinearLayout layout;
    DatabaseReference rootRef, demoRef;
    SearchView etBarkod;
    Button btn,search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.activity_scan);
        layout=(LinearLayout)findViewById(R.id.layout);
        temperature=(TextView)findViewById(R.id.temp);
        lastseend=(TextView)findViewById(R.id.lastseen);
        etBarkod=(SearchView) findViewById(R.id.editText);
        btn=(Button)findViewById(R.id.cameraHolder);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),QrCodeScanner.class);
                startActivityForResult(i,1);
            }
        });
        //String uid=intent.getStringExtra("uid");
        etBarkod.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });



    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String result = data.getStringExtra("result");
                etBarkod.setQuery(result,false);
            }
            if (resultCode == RESULT_CANCELED) {

            }
        }
    }

    private void processsearch(String s)
    {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        //Toast.makeText(this, "" + currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();
        DatabaseReference ref = database.getReference("TEMPERATURE/"+s);
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
                Toast.makeText(Scan.this,"The read failed: " + databaseError.getCode(),Toast.LENGTH_LONG).show();
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