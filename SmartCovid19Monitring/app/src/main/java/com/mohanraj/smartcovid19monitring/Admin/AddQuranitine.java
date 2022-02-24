package com.mohanraj.smartcovid19monitring.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mohanraj.smartcovid19monitring.Bean.AddQurantineBean;
import com.mohanraj.smartcovid19monitring.Bean.AlertBean;
import com.mohanraj.smartcovid19monitring.Bean.AlertUpdate;
import com.mohanraj.smartcovid19monitring.Bean.GetUserbean;
import com.mohanraj.smartcovid19monitring.Bean.MonitoringBean;
import com.mohanraj.smartcovid19monitring.R;
import com.mohanraj.smartcovid19monitring.User.UserMonitorring;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddQuranitine extends AppCompatActivity {
    TextView id,name,ip,add,email,phone,date,fromdate,gender;
    Button btn;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_add_quranitine);
        id=(TextView)findViewById(R.id.tvuid);
        name=(TextView)findViewById(R.id.tvname);
        ip=(TextView)findViewById(R.id.tvip);
        add=(TextView)findViewById(R.id.tvaddress);
        email=(TextView)findViewById(R.id.tvemail);
        phone=(TextView)findViewById(R.id.tvphone);
        btn=(Button)findViewById(R.id.addqurantine);
        gender=(TextView)findViewById(R.id.tvgender);
         date =(TextView) findViewById(R.id.date);
         fromdate=(TextView)findViewById(R.id.alertdate);
         spinner=(Spinner)findViewById(R.id.spinner);
        String[] items = new String[] {"--SELECT--", "AMBUR", "VANIYAMBDI","JOLLARPETI","THIRUPATTUR"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //int uids = getIntent().getIntExtra("id", 0);
        String uid=getIntent().getStringExtra("id");
        final String dates=getIntent().getStringExtra("date");
        //String sam=Integer.toString(uid);
        id.setText(uid);
        fromdate.setText(dates);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        //Toast.makeText(this, "" + currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();
        //String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference ref = database.getReference("USERS/"+uid);

        ref.addValueEventListener(new ValueEventListener() {
                                      @Override
                                      public void onDataChange(@NonNull DataSnapshot snapshot) {

                                          GetUserbean bean= snapshot.getValue(GetUserbean.class);
                                          String mname,mip,madd,memail,mphone,mgender;
                                          mname=bean.getNAME();
                                          mip=bean.getIP_ADDRESS();
                                          madd=bean.getADDRESS();
                                          memail=bean.getEMAIL();
                                          mphone=bean.getPHONE();
                                          mgender=bean.getGENDER();

                                          name.setText(mname);
                                          ip.setText(mip);
                                          add.setText(madd);
                                          email.setText(memail);
                                          phone.setText(mphone);
                                          gender.setText(mgender);

                                      }

                                      @Override
                                      public void onCancelled(@NonNull DatabaseError error) {
                                          Toast.makeText(AddQuranitine.this,"The read failed: " + error.getCode(), Toast.LENGTH_LONG).show();

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

                                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                                        String currentDateandTime = sdf.format(new Date());
                                        date.setText(currentDateandTime);
                                    }
                                });
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                };

        thread.start();

        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                final String center=spinner.getSelectedItem().toString();
                if(center=="--SELECT--")
                {
                    Toast.makeText(getApplicationContext(),"Please select Quarantine center!",Toast.LENGTH_LONG).show();

                }
                else
                {
                    AlertDialog.Builder dialog=new AlertDialog.Builder(AddQuranitine.this);
                    dialog.setMessage("Do you want to add the Quarantine List!");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        private Object AlertUpdate;

                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String UID,ALERTEDDATE,CENTER,QUARANTINEDDATE;
                            QUARANTINEDDATE=date.getText().toString();
                            UID=id.getText().toString();
                            ALERTEDDATE=fromdate.getText().toString();
                            //String text = mySpinner.getSelectedItem().toString
                            CENTER=spinner.getSelectedItem().toString();
                            //String STATUS="ON";
                            AddQurantineBean beans=new AddQurantineBean(UID,ALERTEDDATE,QUARANTINEDDATE,CENTER);


                            //Getting Reference to a User node, (it will be created if not already there)
                            String Key=database.getReference("QUARANTINED_LIST").push().getKey();
                            DatabaseReference userNode = database.getReference("QUARANTINED_LIST/"+Key);
                            userNode.setValue(beans);
                            String Keys=database.getReference("QUARANTINED_LISTS").push().getKey();
                            DatabaseReference userNodes= database.getReference("QUARANTINED_LISTS/"+Key);
                            userNodes.setValue(beans);

                             /*FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference ref = database.getReference("ALERT_LIST");
                            DatabaseReference userRef=ref.child(uid);
                            DatabaseReference HopperRef=userRef.child

                              */
                            DatabaseReference delete=database.getReference("ALERT_LIST/"+UID);
                            delete.removeValue();
                            /*FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference ref = database.getReference("ALERT_LIST");
                            DatabaseReference userRef=ref.child(UID);
                            Map<String, Object> HopperUpdates = new HashMap<>();
                            HopperUpdates.put("STATUS","OFF");
                            userRef.updateChildren(HopperUpdates);*/



                            Intent i=new Intent(getApplicationContext(),AdminActivity.class);
                            startActivity(i);

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
        });


    }


}