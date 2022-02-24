package com.mohanraj.smartcovid19monitring.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mohanraj.smartcovid19monitring.Bean.AddQurantineBean;
import com.mohanraj.smartcovid19monitring.Bean.AddRealeasedBean;
import com.mohanraj.smartcovid19monitring.Bean.GetUserbean;
import com.mohanraj.smartcovid19monitring.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddReleasedList extends AppCompatActivity {
    TextView userid,alerteddate,quarantinedate,place,date,name ;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_add_released_list);
        userid=(TextView)findViewById(R.id.tvuid);
        alerteddate=(TextView)findViewById(R.id.tvalertdate);
        quarantinedate=(TextView)findViewById(R.id.tvqd);
        place=(TextView)findViewById(R.id.tvcenter);
        name=(TextView)findViewById(R.id.tvname);
        btn=(Button)findViewById(R.id.addqurantine);
        date=(TextView)findViewById(R.id.tvdate);

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
        String uid=getIntent().getStringExtra("uid");
        String adate=getIntent().getStringExtra("adate");
        String qdate=getIntent().getStringExtra("qdate");
        String center=getIntent().getStringExtra("place");
        //String sam=Integer.toString(uid);
        userid.setText(uid);
        alerteddate.setText(adate);
        quarantinedate.setText(qdate);
        place.setText(center);

        thread.start();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        //Toast.makeText(this, "" + currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();
        //String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();



        DatabaseReference ref = database.getReference("USERS/"+uid);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                GetUserbean bean= snapshot.getValue(GetUserbean.class);
                String mname;
                mname=bean.getNAME();
                name.setText(mname);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AddReleasedList.this,"The read failed: " + error.getCode(), Toast.LENGTH_LONG).show();

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder dialog=new AlertDialog.Builder(AddReleasedList.this);
                dialog.setMessage("Do you want to add the Release list in this person!!");
                dialog.setCancelable(false);
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String uid,qdate,places,alertd,rdate,center;
                        //center=place.getText().toString();
                        alertd=alerteddate.getText().toString();
                        uid=userid.getText().toString();
                        qdate=quarantinedate.getText().toString();
                        rdate=date.getText().toString();
                        places=place.getText().toString();
                        AddRealeasedBean bean=new AddRealeasedBean(uid,alertd,qdate,rdate,places);
                        FirebaseDatabase databaseInstance = FirebaseDatabase.getInstance();

                        //Getting Reference to a User node, (it will be created if not already there)
                        String Key=database.getReference("QUARANTINED_LIST").push().getKey();
                        DatabaseReference userNode = database.getReference("RELEASED_LIST/"+Key);
                        userNode.setValue(bean);
                        DatabaseReference myRef = (DatabaseReference) database.getReference("QUARANTINED_LIST");
                        final Query query=myRef.child("uid").equalTo(uid);
                        //query.getRef().removeValue();

                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot s:snapshot.getChildren())
                                {
                                    query.getRef().removeValue();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        //Removing that user
                        myRef.removeValue();
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
        });



    }
}