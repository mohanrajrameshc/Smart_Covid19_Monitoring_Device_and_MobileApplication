package com.mohanraj.smartcovid19monitring.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.mohanraj.smartcovid19monitring.Admin.QrCodeScanner;
import com.mohanraj.smartcovid19monitring.Bean.RealeaedAdpBean;
import com.mohanraj.smartcovid19monitring.R;
import com.mohanraj.smartcovid19monitring.RecyclerView.RealeadedAdapter;
import com.mohanraj.smartcovid19monitring.RecyclerView.UserQurantinedAdap;


public class QuarantinedRecord extends AppCompatActivity {
    private RecyclerView recview;
    private UserQurantinedAdap adapter;
    TextView tv;

    FloatingActionButton report;
    Button btn,search;
    SearchView etBarkod;
    DatabaseReference mbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_quarantined_record);
        recview =(RecyclerView)findViewById(R.id.recview);
        recview=(RecyclerView)findViewById(R.id.recview);
        search=(Button)findViewById(R.id.searchbtn);
        report=(FloatingActionButton)findViewById(R.id.reportfloat);
        etBarkod=(SearchView) findViewById(R.id.editText);
        btn=(Button)findViewById(R.id.cameraHolder);
        tv=(TextView)findViewById(R.id.tvvisible);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), QrCodeScanner.class);
                startActivityForResult(i,1);
            }
        });
        //Firebase root = new Firebase("https://myApp.firebaseio.com/");
        //Firebase ref = root.child("UserWallet");
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();


         DatabaseReference ref = (DatabaseReference) database.getReference().child("RELEASED_LIST");
        Query query=ref.orderByChild("userid").startAt(currentuser);
        recview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        FirebaseRecyclerOptions<RealeaedAdpBean> options =
                new FirebaseRecyclerOptions.Builder<RealeaedAdpBean>()
                        .setQuery(query, RealeaedAdpBean.class)
                        .build();

        adapter=new UserQurantinedAdap(options);
        recview.setAdapter(adapter);

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}