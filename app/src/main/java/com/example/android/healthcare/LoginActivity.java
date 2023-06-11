package com.example.android.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edUserName,edPassword;
    Button btn;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUserName=findViewById(R.id.editTextLoginUserName);
        edPassword=findViewById(R.id.editTextLoginPassword);
        btn=findViewById(R.id.buttonLogin);
        tv=findViewById(R.id.textViewNewUser);
        Database db=new Database(getApplicationContext(),"healthcare",null,1);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=edUserName.getText().toString();
                String password=edPassword.getText().toString();
                if(username.length()==0 || password.length()==0)
                    Toast.makeText(getApplicationContext(),"Please Fill All Details",Toast.LENGTH_SHORT).show();
                else {
                    if(db.login(username,password)==1)
                    {
                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedpreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedpreferences.edit();
                        editor.putString("username",username);
                        //to save our data with key and value
                        editor.apply();

                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Invalid UserName or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }
}