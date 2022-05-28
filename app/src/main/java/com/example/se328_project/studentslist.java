package com.example.se328_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;


public class studentslist extends ListActivity {


    String[] StudentsList = {"StudentName", "SurName", "FatherName", "NationalID", "ID"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_studentslist);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_studentslist, R.id.studentslist, StudentsList));
        DatabaseHelper myData = new DatabaseHelper(this);



        Cursor cur = myData.ViewStudents();
        StringBuffer buffer = new StringBuffer();
        while (cur.moveToNext()) {
            buffer.append("student name:" + cur.getString(0) + "\n");
            buffer.append("surname:" + cur.getString(1) + "\n");
            buffer.append("father name:" + cur.getString(2) + "\n");
            buffer.append("natinal ID:" + cur.getString(3) + "\n");
            buffer.append("ID:" + cur.getString(4) + "\n\n");
        }


    }
}