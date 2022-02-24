package com.mohanraj.smartcovid19monitring.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.mohanraj.smartcovid19monitring.Bean.Admin;
import com.mohanraj.smartcovid19monitring.Bean.User;
import com.mohanraj.smartcovid19monitring.R;
import com.mohanraj.smartcovid19monitring.RegistrationActivity;

import java.util.Calendar;
import java.util.regex.Pattern;

public class AddAdmin extends AppCompatActivity {
    private EditText Name,Email,Password,CPassword,Address,Phone;
    TextView DateofBirth;
    ProgressBar progressBar;
    Button btn;
    Spinner spinner;
    int mYear;
    int mMonth;
    int mDay;
    int mDateDisplay;
    int count;
    DatePicker picker;
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
        setContentView(R.layout.activity_add_admin);
        Name=(EditText)findViewById(R.id.etName);
        Email=(EditText)findViewById(R.id.etEmail);
        Address=(EditText)findViewById(R.id.etAdress);
        Phone=(EditText)findViewById(R.id.etMobileNumber);
        Password=(EditText)findViewById(R.id.etpassword);
        CPassword=(EditText)findViewById(R.id.etcpassword);
        progressBar = findViewById(R.id.progressBar);
        btn=(Button)findViewById(R.id.btnsignup);
        progressBar.setVisibility(View.GONE);
        DateofBirth=(TextView) findViewById(R.id.etdateofbirth);
        spinner=(Spinner)findViewById(R.id.spinner);
        String[] items = new String[] {"--SELECT--", "Male", "Female","Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        DateofBirth.setText("dd-MM-yyyy");


        DateofBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datedialog;
                Calendar c=Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog.OnDateSetListener mdatesetlistner=new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int i, int i1, int i2) {
                        mYear = i;
                        mMonth = i1;
                        mDay = i2;
                        DateofBirth.setText(new StringBuilder().append(mDay).append("-").append(mMonth + 1).append("-").append(mYear));
                    }};
                datedialog=new DatePickerDialog(AddAdmin.this,mdatesetlistner,mYear,mMonth,mDay);
                datedialog.show();
            }
        });
        mAuth = FirebaseAuth.getInstance();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable warning = (Drawable)getResources().getDrawable(R.drawable.ic_error_black_18dp);
                warning.setBounds(0, 0, warning.getIntrinsicWidth(), warning.getIntrinsicHeight());

                final String name,email,password,cpassword,aaddress,phone,dob;
                final String gender=spinner.getSelectedItem().toString();
                name = Name.getText().toString();
                email = Email.getText().toString();
                password = Password.getText().toString();
                cpassword = CPassword.getText().toString();
                aaddress = Address.getText().toString();
                phone = Phone.getText().toString();
                dob=DateofBirth.getText().toString();




                // Validations for input email and password
                if (TextUtils.isEmpty(name)) {
                    Name.setError("Please enter Name!!", warning);
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    Email.setError("Please enter Email!!", warning);


                    return;
                }
                if(!EMAIL_ADDRESS_PATTERN.matcher(email).matches()){
                    Email.setError("Please enter Valid Email!!", warning);
                    return;

                }
                if (TextUtils.isEmpty(phone)) {
                    Phone.setError("Please enter Phone number!!", warning);


                    return;
                }
                if (phone.length() != 10) {
                    Phone.setError("Enter correct Number!!",warning);

                    return;
                }

                if(gender.equals("--SELECT--"))
                {

                    Toast.makeText(getApplicationContext(),"Please select gender!",Toast.LENGTH_LONG).show();

                }
                if(dob.equals("dd-MM-yyyy"))
                {
                    DateofBirth.setError("Enter Date of birth!!",warning);
                }
                if (TextUtils.isEmpty(aaddress)) {
                    Address.setError("Please enter Address!!", warning);


                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Password.setError("Please enter password!!", warning);

                    return;
                }
                if (password.length() < 6) {
                    Password.setError("Please enter password > 6",warning);
                    CPassword.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(cpassword)) {
                    CPassword.setError("Please enter Conform password!!", warning);

                    return;
                }
                if(!password.equals(cpassword))
                {
                    CPassword.setError("Password does not match!",warning);
                    return;
                }
                else
                {
                    final String logintype="Admin";
                    progressBar.setVisibility(View.VISIBLE);

                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {

                                        Admin user = new Admin(
                                                name,email,phone,aaddress,password,logintype,dob,gender
                                        );

                                        FirebaseDatabase.getInstance().getReference("USERS")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if (task.isSuccessful()) {
                                                    progressBar.setVisibility(View.VISIBLE);
                                                    Toast.makeText(AddAdmin.this,"Registred Successfull", Toast.LENGTH_LONG).show();
                                                } else {
                                                    progressBar.setVisibility(View.GONE);
                                                    Toast.makeText(AddAdmin.this,"Registration faild",Toast.LENGTH_LONG).show();
                                                    //display a failure message
                                                }
                                            }
                                        });

                                    } else {
                                        //progressBar.setVisibility(View.GONE);
                                        Toast.makeText(AddAdmin.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                }

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            //handle the already login user
        }
    }
}