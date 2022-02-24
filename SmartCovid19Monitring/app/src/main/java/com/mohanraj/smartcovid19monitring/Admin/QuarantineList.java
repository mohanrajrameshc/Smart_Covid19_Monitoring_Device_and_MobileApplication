package com.mohanraj.smartcovid19monitring.Admin;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.mohanraj.smartcovid19monitring.Admin.Report.AlertReport;
import com.mohanraj.smartcovid19monitring.Admin.Report.QuarantinedReport;
import com.mohanraj.smartcovid19monitring.Bean.QuarantinedAdpBean;
import com.mohanraj.smartcovid19monitring.R;
import com.mohanraj.smartcovid19monitring.RecyclerView.QuarantinedAdapter;

public class QuarantineList extends AppCompatActivity {
    private RecyclerView recview;
    private QuarantinedAdapter adapter;
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
        setContentView(R.layout.activity_quarantine_list);
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
                Intent i=new Intent(getApplicationContext(),QrCodeScanner.class);
                startActivityForResult(i,1);
            }
        });
        //Firebase root = new Firebase("https://myApp.firebaseio.com/");
        //Firebase ref = root.child("UserWallet");


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        // final DatabaseReference ref = (DatabaseReference) database.getReference().child("ALERT_LIST").orderByChild("STATUS").startAt("O").endAt("N\uf8ff");
        recview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        FirebaseRecyclerOptions<QuarantinedAdpBean> options =
                new FirebaseRecyclerOptions.Builder<QuarantinedAdpBean>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("QUARANTINED_LIST"), QuarantinedAdpBean.class)
                        .build();

        adapter=new QuarantinedAdapter(options);
        recview.setAdapter(adapter);

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

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), QuarantinedReport.class);
                startActivity(i);
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

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu,menu);
        MenuItem item=menu.findItem(R.id.search);
        SearchView searchView=(SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        return super.onCreateOptionsMenu(menu);
    }*/
    private void processsearch(String s)
    {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = (DatabaseReference) database.getReference().child("QUARANTINED_LIST");
        Query query=ref.orderByChild("uid").startAt(s).endAt(s+"\uf8ff");

        FirebaseRecyclerOptions<QuarantinedAdpBean> options =
                new FirebaseRecyclerOptions.Builder<QuarantinedAdpBean>()
                        .setQuery(query, QuarantinedAdpBean.class)
                        .build();

        adapter= new QuarantinedAdapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);

    }
}