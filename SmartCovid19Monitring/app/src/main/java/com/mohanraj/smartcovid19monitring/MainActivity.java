package com.mohanraj.smartcovid19monitring;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mohanraj.smartcovid19monitring.Admin.AdminActivity;
import com.mohanraj.smartcovid19monitring.User.Userdashboard;

import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    TextView forg;
    EditText etemail,etpassword;
    private ProgressBar progressbar;
    private Button Btn;
    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9+._%-+]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                    "(" +
                    "." +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                    ")+"
    );

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);
        /*startActivity(new Intent(MainActivity.this,SecondActivity.class));
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // close splash activity
        finish();*/

        mAuth = FirebaseAuth.getInstance();
        etemail=(EditText)findViewById(R.id.etEmail);
        etpassword=(EditText)findViewById(R.id.etPassword);
        progressbar = findViewById(R.id.progressBar);
        forg=(TextView)findViewById(R.id.fogot);
        Btn=(Button)findViewById(R.id.btnlogin);
        forg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,ForgotPassword.class);
                startActivity(i);
            }
        });
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnLogin();
            }
        });

        progressbar.setVisibility(View.GONE);



    }

    public void BtnLogin() {
        Drawable warning = (Drawable)getResources().getDrawable(R.drawable.ic_error_black_18dp);
        warning.setBounds(0, 0, warning.getIntrinsicWidth(), warning.getIntrinsicHeight());

        // show the visibility of progress bar to show loading
        progressbar.setVisibility(View.VISIBLE);

        // Take the value of two edit texts in Strings
        String email, password;
        email = etemail.getText().toString();
        password = etpassword.getText().toString();

        // validations for input email and password
        if (TextUtils.isEmpty(email)) {
            etemail.setError("Please enter Email!!", warning);


            return;
        }
        if(!EMAIL_ADDRESS_PATTERN.matcher(email).matches()){
            etemail.setError("Please enter Valid Email!!", warning);
            return;

        }

        if (TextUtils.isEmpty(password)) {
            etpassword.setError("Please enter Password!!", warning);
            return;

        }
        if (password.length() < 6) {
            etpassword.setError("Password must > 6",warning);
            return;
        }
        else
        {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                String RegisteredUserID = currentUser.getUid();

                                DatabaseReference jLoginDatabase = FirebaseDatabase.getInstance().getReference().child("USERS/"+RegisteredUserID);

                                jLoginDatabase.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        String userType = dataSnapshot.child("LOGINTYPE").getValue().toString();
                                        if(userType.equals("Admin")){
                                            //progressbar.setVisibility(View.VISIBLE);
                                            Toast.makeText(getApplicationContext(), "Admin Login successful!!", Toast.LENGTH_SHORT).show();
                                            Intent intentResident = new Intent(MainActivity.this, AdminActivity.class);

                                            startActivity(intentResident);
                                            finish();
                                        }else if(userType.equals("User")){
                                            progressbar.setVisibility(View.VISIBLE);
                                            Toast.makeText(getApplicationContext(), "User Login successful!!", Toast.LENGTH_SHORT).show();
                                            Intent intentMain = new Intent(MainActivity.this, Userdashboard.class);

                                            startActivity(intentMain);
                                            finish();


                                        }

                                    }



                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            }
                            else{
                                progressbar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Failed Login. Please Try Again", Toast.LENGTH_SHORT).show();
                                progressbar.setVisibility(View.GONE);
                                return;
                            }
                        }
                    });


        }


        // signin existing user

    }

    public void BtnRegister(View view) {

        Intent intent=new Intent(this,RegistrationActivity.class);
        startActivity(intent);
    }

    public void previewDisabledUsersReport(View view) {
    }

    public void forgotpass(View view) {
    }
}