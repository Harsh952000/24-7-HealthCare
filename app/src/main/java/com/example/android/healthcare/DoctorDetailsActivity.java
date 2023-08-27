package com.example.android.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1={
            {"Doctor Name : Harsh Sharma", "Hospital Address : Adarsh Nagar Jaipur","Exp : 5 yrs","Mobile NO: 9894939494","600"},
            {"Doctor Name : Tarun Gover", "Hospital Address : Mahesh Nagar Nodia","Exp : 3 yrs","Mobile NO: 9895693945","500"},
            {"Doctor Name : Aarul Mishra", "Hospital Address : Saroj Nagar Faridabad","Exp : 5 yrs","Mobile NO: 9894935494","400"},
            {"Doctor Name : Rashika Gupta", "Hospital Address : Vidhya Nagar Agartala","Exp : 2 yrs","Mobile NO: 9894459494","400"},
            {"Doctor Name : Suresh Gupta", "Hospital Address : Subash Nagar Delhi","Exp : 1 yrs","Mobile NO: 9564939494","300"}
    };
    private String[][] doctor_details2={
            {"Doctor Name : Harish Sharma", "Hospital Address : Arun Nagar Jaipur","Exp : 5 yrs","Mobile NO: 9344939494","600"},
            {"Doctor Name : Taruna Gover", "Hospital Address : Mahesh Nagar Nodia","Exp : 2 yrs","Mobile NO: 9395693945","500"},
            {"Doctor Name : Abhik Mishra", "Hospital Address : Santi Nagar Faridabad","Exp : 6 yrs","Mobile NO: 9894335494","400"},
            {"Doctor Name : Rash Gupta", "Hospital Address : Vida Nagar Agartala","Exp : 2 yrs","Mobile NO: 9694459494","400"},
            {"Doctor Name : kirna Gupta", "Hospital Address : Subash Nagar Delhi","Exp : 1 yrs","Mobile NO: 9564639494","300"}
    };
    private String[][] doctor_details3={
            {"Doctor Name : Brajesh Singh", "Hospital Address : Anand Nagar Pune","Exp : 5 yrs","Mobile NO: 9894919494","600"},
            {"Doctor Name : Kishan Gupta", "Hospital Address :  Ahmad Nagar Nodia","Exp : 3 yrs","Mobile NO: 9295693945","500"},
            {"Doctor Name : Ashok Sharma", "Hospital Address :  Santosh Nagar Faridabad","Exp : 5 yrs","Mobile NO: 9394935494","400"},
            {"Doctor Name : Hitesh Saini", "Hospital Address :  Kishan Pole Agartala","Exp : 2 yrs","Mobile NO: 8894459494","400"},
            {"Doctor Name : Kanchan Verma", "Hospital Address : Sodala Delhi","Exp : 1 yrs","Mobile NO: 98564939494","300"}
    };
    private String[][] doctor_details4={
            {"Doctor Name : Sourav Joshi", "Hospital Address : Akash Nagar Jaipur","Exp : 5 yrs","Mobile NO: 9394939494","600"},
            {"Doctor Name : Tamanna Bhatiya", "Hospital Address : Shubham nagar Nodia","Exp : 3 yrs","Mobile NO: 9595693945","500"},
            {"Doctor Name : Shubham Oberoy", "Hospital Address : Ashok Nagar Faridabad","Exp : 5 yrs","Mobile NO: 9694935494","400"},
            {"Doctor Name : Kiran verma", "Hospital Address : Kirti Nagar Agartala","Exp : 2 yrs","Mobile NO: 9794459494","400"},
            {"Doctor Name : Satish Bhatiya", "Hospital Address : vijay Nagar Delhi","Exp : 1 yrs","Mobile NO: 9864939494","300"}
    };
    private String[][] doctor_details5={
            {"Doctor Name : Varun Negi", "Hospital Address : kranti Nagar Jaipur","Exp : 5 yrs","Mobile NO: 9394939494","600"},
            {"Doctor Name : Shantanu Sharma", "Hospital Address : subash Nagar Nodia","Exp : 3 yrs","Mobile NO:89895693945","500"},
            {"Doctor Name : Shivam sharma", "Hospital Address :  civil Lines Faridabad","Exp : 5 yrs","Mobile NO:89894935494","400"},
            {"Doctor Name : Deepika Mishra", "Hospital Address : Sonipat Agartala","Exp : 2 yrs","Mobile NO: 8894459494","400"},
            {"Doctor Name : kajol Mishra", "Hospital Address : Rajiv chok Delhi","Exp : 1 yrs","Mobile NO: 8564939494","300"}
    };
TextView tv;
Button btn;
String[][] doctor_details={};
ArrayList list;
SimpleAdapter sa;
HashMap<String,String> item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv=findViewById(R.id.textViewDDTitle);
        btn=findViewById(R.id.buttonDDBack);


        Intent it=getIntent();
        String title=it.getStringExtra("title");
        tv.setText(title);
        if(title.compareTo("Family Physicians")==0)
        {
            doctor_details=doctor_details1;
        }
        else if(title.compareTo("Dietician")==0)
        {
            doctor_details=doctor_details2;
        }
        else if(title.compareTo("Dentist")==0)
        {
            doctor_details=doctor_details3;
        }else   if(title.compareTo("Surgeon")==0)
        {
            doctor_details=doctor_details4;
        }
        else
        {
            doctor_details=doctor_details5;
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });
        list=new ArrayList();
        for(int i=0;i<doctor_details.length;i++)
        {
            item=new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","cons Fees"+doctor_details[i][4]+"+/-");
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );
        ListView list=findViewById(R.id.ListViewDD);
        list.setAdapter(sa);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent it=new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);

                startActivity(it);

            }
        });

    }
}