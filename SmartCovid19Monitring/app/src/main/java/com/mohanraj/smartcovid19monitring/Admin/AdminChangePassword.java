package com.mohanraj.smartcovid19monitring.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mohanraj.smartcovid19monitring.R;

import java.util.HashMap;
import java.util.Map;

public class AdminChangePassword extends AppCompatActivity {
    EditText old,newp,confp;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_admin_change_password);
        old=(EditText)findViewById(R.id.etcurrentpass);
        newp=(EditText)findViewById(R.id.etNewpassword);
        confp=(EditText)findViewById(R.id.etConformpassword);
        btn=(Button)findViewById(R.id.btn);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        final String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String UID=currentuser;

                Drawable warning = (Drawable) getResources().getDrawable(R.drawable.ic_error_black_18dp);
                warning.setBounds(0, 0, warning.getIntrinsicWidth(), warning.getIntrinsicHeight());
                String oldpass = old.getText().toString();
                String cpass = confp.getText().toString();
                final String newPass = newp.getText().toString();
                final FirebaseUser user;
                user = FirebaseAuth.getInstance().getCurrentUser();
                final String email = user.getEmail();
                if (TextUtils.isEmpty(oldpass)) {
                    old.setError("Please enter password!!", warning);

                    return;
                }
                if (oldpass.length() < 6) {
                    old.setError("Please enter password > 6", warning);
                    return;
                }
                if (TextUtils.isEmpty(newPass)) {
                    newp.setError("Please enter password!!", warning);

                    return;
                }
                if (newPass.length() < 6) {
                    newp.setError("Please enter password > 6", warning);
                    return;
                }
                if (TextUtils.isEmpty(cpass)) {
                    confp.setError("Please enter Conform password!!", warning);

                    return;
                }
                if (!newPass.equals(cpass)) {
                    confp.setError("Password does not match!", warning);
                    return;
                } else {
                    AuthCredential credential = EmailAuthProvider.getCredential(email, oldpass);

                    user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                user.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(AdminChangePassword.this, "Something went wrong. Please try again later", Toast.LENGTH_LONG).show();

                                        } else {

                                            DatabaseReference ref = database.getReference("USERS");
                                            DatabaseReference userRef=ref.child(UID);
                                            Map<String, Object> HopperUpdates = new HashMap<>();
                                            HopperUpdates.put("PASSWORD",newPass);
                                            userRef.updateChildren(HopperUpdates);
                                            Toast.makeText(AdminChangePassword.this, "Password Successfully Modified", Toast.LENGTH_LONG).show();

                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(AdminChangePassword.this, "Authentication Failed", Toast.LENGTH_LONG).show();


                            }
                        }
                    });
                }
            }



        });
    }
}