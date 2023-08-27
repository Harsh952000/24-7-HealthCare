package com.example.android.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {


    //constructor of our Database class
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version)
    {
        //super class matlab sqliteOpenHelper class ke constructor me context matlab kis application ka db banna h, database name factory version pass kara
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1="create table users(username text,email text, password text)";
        String qry2="create table orders(username text,address text,fees text)";

        sqLiteDatabase.execSQL(qry1);
        sqLiteDatabase.execSQL(qry2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void register(String username,String email,String password)
    {
        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("email",email);
        cv.put("password",password);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("users",null,cv);
        db.close();

    }
    public void addOrder(String username,String address,String fees)
    {
        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("address",address);
        cv.put("fees",fees);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("orders",null,cv);
        db.close();
    }
    public int login(String username,String password)
    {
        int result=0;
        String[] str=new String[2];
        str[0]=username;
        str[1]=password;

        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from users where username=? and password=?",str);
        if(c.moveToFirst())
                result=1;

        return result;
    }
    public ArrayList getOrder(String username)
    {

        ArrayList<String> arr=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        String[] str=new String[1];
        str[0]=username;
        Cursor c=db.rawQuery("select * from orders where username =?",str);
        if(c.moveToFirst())
        {
            do{
                arr.add(c.getString(1)+"$"+c.getString(2)+c.getString(3));
            }
            while(c.moveToNext());
        }
        db.close();
        return arr;





    }
    public int checkAppointmentExists(String username,String fullname,String address,String contact,String date,String time)
    {
        int result=0;
        String str[]=new String[6];
        str[0]=username;
        str[1]=fullname;
        str[2]=address;
        str[3]=contact;
        str[4]=date;
        str[5]=time;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from orderplace where username = ? and fullname = ? and address = ? and contactno = ? and date = ? and time = ?",str);
        if(c.moveToFirst())
        {
            result=1;
        }
        db.close();
        return result;
    }

}
