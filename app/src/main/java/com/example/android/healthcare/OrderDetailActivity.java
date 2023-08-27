package com.example.android.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;


public class OrderDetailActivity extends AppCompatActivity {
    TextView tv;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        tv=findViewById(R.id.textViewDDTitle2);



       SharedPreferences getShared=getSharedPreferences("demo",MODE_MULTI_PROCESS);
       String value=getShared.getString("str","No appointment now");




       tv.setText(value);
    btn=findViewById(R.id.buttonDDBack);
    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(OrderDetailActivity.this,HomeActivity.class));
        }
    });


    }
}