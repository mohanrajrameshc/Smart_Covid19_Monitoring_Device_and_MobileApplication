package com.mohanraj.smartcovid19monitring.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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
import com.mohanraj.smartcovid19monitring.Admin.QrCodeScanner;
import com.mohanraj.smartcovid19monitring.Bean.UserHistoryAdapterBean;
import com.mohanraj.smartcovid19monitring.R;
import com.mohanraj.smartcovid19monitring.RecyclerView.AlertAdaptar;
import com.mohanraj.smartcovid19monitring.RecyclerView.UserHistoryAdapter;

public class UserHistory extends AppCompatActivity {
    private RecyclerView recview;
    private UserHistoryAdapter adapter;
    TextView tv;
    String currentuser;

    Button btn,search;
    EditText etBarkod;
    DatabaseReference mbase;
    FirebaseRecyclerOptions<UserHistoryAdapterBean> adapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_user_history);
        recview =(RecyclerView)findViewById(R.id.recview);
        recview=(RecyclerView)findViewById(R.id.recview);
        search=(Button)findViewById(R.id.searchbtn);

        etBarkod=(EditText) findViewById(R.id.editText);
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
        try {
            FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
            //Toast.makeText(this, "" + currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();
            currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();



        }
        catch (IllegalStateException e) {
            //e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Get UId error"+e.getMessage(),Toast.LENGTH_LONG).show();
        }


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = (DatabaseReference) database.getReference().child("HISTORY").child(currentuser);
        Query query=ref.orderByChild("TIME").limitToLast(604800);
        recview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapt =
                new FirebaseRecyclerOptions.Builder<UserHistoryAdapterBean>()
                        .setQuery(query, UserHistoryAdapterBean.class).build();

        adapter = new UserHistoryAdapter(adapt);
        recview.setAdapter(adapter);


    }

    ValueEventListener valueEventListener= new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            recview.clearOnChildAttachStateChangeListeners();
            if(snapshot.exists())
            {
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                   // adapt =
                          //  new FirebaseRecyclerOptions.Builder<UserHistoryAdapterBean>()
                              //      .setQuery(ref, UserHistoryAdapterBean.class).build();

                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
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