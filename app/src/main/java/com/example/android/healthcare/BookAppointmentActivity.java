package com.example.android.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {
EditText ed1,ed2,ed3,ed4;
Button back;
TextView tv;
private DatePickerDialog datePickerDialog;
private TimePickerDialog timePickerDialog;
private Button dateButton,timeButton,btnBook,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        tv=findViewById(R.id.textViewAppTitle);
        ed1=findViewById(R.id.editTextAppFullName);
        ed2=findViewById(R.id.editTextAppAddress);
        ed3=findViewById(R.id.editTextAppContactNumber);
        ed4=findViewById(R.id.editTextAppFees);
        dateButton=findViewById(R.id.buttonAppDate);
        timeButton=findViewById(R.id.buttonAppTime);

        btnBack=findViewById(R.id.buttonAppBack);
        btnBook=findViewById(R.id.buttonBookAppointment);


        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookAppointmentActivity.this,FindDoctorActivity.class));
            }
        });
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Database db=new Database(getApplicationContext(),"heatlhcare",null,1);
                String username=ed1.getText().toString();
                String address=ed2.getText().toString();
                String mobile=ed3.getText().toString();
                Database db1=new Database(getApplicationContext(),"healthcare",null,1);
                db1.addOrder(username,address,mobile);
                Toast.makeText(getApplicationContext(),"your appointment is completed sucessfully",Toast.LENGTH_SHORT).show();
                String appointment="    "+username+"\n    "+address+"\n   "+mobile;
                SharedPreferences shr=getSharedPreferences("demo",MODE_MULTI_PROCESS);
                SharedPreferences.Editor editor= shr.edit();
                editor.putString("str",appointment);
                editor.apply();
                startActivity(new Intent(BookAppointmentActivity.this,OrderDetailActivity.class));

            }
        });

        //datepicker
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });



        Intent it=getIntent();
        String title=it.getStringExtra("text1");
        String fullname=it.getStringExtra("text2");
        String address=it.getStringExtra("text3");
        String contact=it.getStringExtra("text4");
        String fees=it.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullname);
        ed1.setTextSize(14);
        ed2.setText(address);
        ed2.setTextSize(14);
        ed3.setText(contact);
        ed3.setTextSize(14);
        ed4.setText("cons fees:"+fees+"+/-");
        ed4.setTextSize(14);

        back=findViewById(R.id.buttonAppBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookAppointmentActivity.this,DoctorDetailsActivity.class));
            }
        });



    }
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                //dateButton.setText(dayOfMonth+"/"+month+"/"+year);

            }
        };
        Calendar cal=Calendar.getInstance();
        int year= cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);

        int style= AlertDialog.THEME_HOLO_DARK;
        datePickerDialog=new DatePickerDialog(this,style,dateSetListener,year,month,day);

        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    }
    private void initTimePicker()
    {
        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                timeButton.setText(hourOfDay+":"+minute);
            }
        };
        Calendar cal=Calendar.getInstance();
        int hrs=cal.get(Calendar.HOUR);
        int mins=cal.get(Calendar.MINUTE);
       // int style=AlterDialog.THEME_HOLO_DARK;
        timePickerDialog=new TimePickerDialog(this,timeSetListener,hrs,mins,true);

    }
}