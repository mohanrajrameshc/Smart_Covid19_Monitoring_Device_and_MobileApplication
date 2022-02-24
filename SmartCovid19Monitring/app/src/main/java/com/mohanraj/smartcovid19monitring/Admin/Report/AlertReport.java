package com.mohanraj.smartcovid19monitring.Admin.Report;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mohanraj.smartcovid19monitring.R;

import java.util.Calendar;

public class AlertReport extends AppCompatActivity {
    TextView fdate,tdate,uid,celsius,adate;
    Button btn,email;
    int mYear;
    int mMonth;
    int mDay;
    int mDateDisplay;
    int count;
    DatePicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_report);
        fdate=(TextView)findViewById(R.id.tvfdate);
        tdate=(TextView)findViewById(R.id.tvtdate);
        uid=(TextView)findViewById(R.id.tvuid);
        btn=(Button)findViewById(R.id.btnget);
        email=(Button)findViewById(R.id.btnsendemail);
        picker=(DatePicker)findViewById(R.id.datePicker);
        fdate.setText(getCurrentDate());
        tdate.setText(getCurrentDate());
        email.setVisibility(View.GONE);
        fdate.setOnClickListener(new View.OnClickListener() {
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
                        fdate.setText(new StringBuilder().append(mDay).append("-").append(mMonth + 1).append("-").append(mYear));
                    }};
                datedialog=new DatePickerDialog(AlertReport.this,mdatesetlistner,mYear,mMonth,mDay);
                datedialog.show();
            }
        });
        tdate.setOnClickListener(new View.OnClickListener() {
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
                        tdate.setText(new StringBuilder().append(mDay).append("-").append(mMonth + 1).append("-").append(mYear));
                    }};
                datedialog=new DatePickerDialog(AlertReport.this,mdatesetlistner,mYear,mMonth,mDay);
                datedialog.show();
            }
        });




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uid.setText("");
                String fromdate,todate;
                fromdate=fdate.getText().toString();
                todate=tdate.getText().toString();
                email.setVisibility(View.VISIBLE);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference table_user = (DatabaseReference) database.getReference("ALERT_LIST");
                Query query=table_user.orderByChild("DATE").startAt(fromdate).endAt(todate);

                // DatabaseReference ord= (DatabaseReference) table_user.orderByChild("DATE").equalTo(fromdate);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists())
                        {
                            count=(int)snapshot.getChildrenCount();
                            uid.setText(Integer.toString(count));
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Data not available!",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(uid==null)
                {
                    Toast.makeText(getApplicationContext(),"No data are found please try again!",Toast.LENGTH_LONG).show();
                }
                else {
                    String fromdate, todate, count;
                    fromdate = fdate.getText().toString();
                    todate = tdate.getText().toString();
                    count = uid.getText().toString();
                    Intent intobj = new Intent(Intent.ACTION_SEND);
                    intobj.setType("message/rfc822");
                    intobj.putExtra(Intent.EXTRA_EMAIL, new String[]{"Admin@gmail.com"});
                    intobj.putExtra(Intent.EXTRA_SUBJECT, "Alerted List");
                    intobj.putExtra(Intent.EXTRA_TEXT, "From date: " + fromdate + "\nTo date: " + todate + "\nTotal count: " + count);
                    startActivity(Intent.createChooser(intobj, "Choose Mail App"));
                }

            }
        });
    }
    public String getCurrentDate(){
        StringBuilder builder=new StringBuilder();;
        builder.append(picker.getDayOfMonth()+"-");
        builder.append((picker.getMonth() + 1)+"-");//month is 0 based
        builder.append(picker.getYear());
        return builder.toString();
    }
}