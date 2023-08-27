package com.example.android.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername,edPassword,edEmail,edConfirmPassword;
    Button register;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername=findViewById(R.id.editTextAppFullName);
        edPassword=findViewById(R.id.editTextAppContactNumber);
        edEmail=findViewById(R.id.editTextAppAddress);
        edConfirmPassword=findViewById(R.id.editTextAppFees);
        register=findViewById(R.id.buttonBookAppointment);
        tv=findViewById(R.id.textViewExistingUser);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=edUsername.getText().toString();
                String email=edEmail.getText().toString();
                String password=edPassword.getText().toString();
                String confirm=edConfirmPassword.getText().toString();
                Database db=new Database(getApplicationContext(),"healthcare",null,1);


                if(username.length()==0 || email.length()==0 || password.length()==0 || confirm.length()==0)
                    Toast.makeText(RegisterActivity.this, "Please Fill The Details", Toast.LENGTH_SHORT).show();
                else
                {
                    if(password.compareTo(confirm)==0)
                    {
                        if(isValid(password))
                        {
                            db.register(username,email,password);

                            Toast.makeText(RegisterActivity.this, "Register SucessFully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this, "Password must contain at least 8 characters ,haing letter,digit and special symbol", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "Password and confirm Password didn't match", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }
    public static boolean isValid(String passwordhere)
    {
        int f1=0,f2=0,f3=0;
        if(passwordhere.length()<8)
                return false;
        else
        {
            for(int p=0;p<passwordhere.length();p++)
            {
                if(Character.isLetter((passwordhere.charAt(p))))
                    f1=1;

            }
            for(int r=0;r<passwordhere.length();r++)
            {
                if(Character.isDigit((passwordhere.charAt(r))))
                    f2=1;

            }
            for(int s=0;s<passwordhere.length();s++)
            {
                char ch=passwordhere.charAt(s);
                if(ch>=33 && ch<=46 || ch==64)
                    f3=1;

            }

        }
        if(f1==1 && f2==1 && f3==1)
            return true;
        else
            return false;

    }
}