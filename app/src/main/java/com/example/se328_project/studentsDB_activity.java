package com.example.se328_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class studentsDB_activity extends AppCompatActivity {

    String sname_val,surname_val,fname_val;
    DatabaseHelper myData;
    String nid_val;
    String id_val;
    EditText sname,surname,fname,nid,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_db);




        myData = new DatabaseHelper(this);
        sname=(EditText) findViewById(R.id.studentnametext);
        surname=(EditText) findViewById(R.id.surnametext);
        fname=(EditText) findViewById(R.id.fathernametext);
        nid=(EditText) findViewById(R.id.nidtext);
        id=(EditText) findViewById(R.id.idtext);
        Button add = (Button) findViewById(R.id.addbtn);
        Button view = (Button) findViewById(R.id.viewbtn);
        Button delete = (Button) findViewById(R.id.deletebtn);
        Button update = (Button) findViewById(R.id.updatebtn);


        //add button listener
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sname_val=sname.getText().toString();
                surname_val=surname.getText().toString();
                fname_val=fname.getText().toString();
                nid_val= nid.getText().toString();
                id_val= id.getText().toString();
                myData.AddStudents(sname_val,surname_val,fname_val,nid_val,id_val);
                Toast.makeText(studentsDB_activity.this, "Successful Add", Toast.LENGTH_SHORT).show();
            }
        });




        //view button listener



        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cur = myData.ViewStudents();
                StringBuffer buffer = new StringBuffer();
                while (cur.moveToNext()) {
                    buffer.append("student name:" + cur.getString(0) + "\n");
                    buffer.append("surname:" + cur.getString(1) + "\n");
                    buffer.append("father name:" + cur.getString(2) + "\n");
                    buffer.append("natinal ID:" + cur.getString(3) + "\n");
                    buffer.append("ID:" + cur.getString(4) + "\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(studentsDB_activity.this);
                builder.setCancelable(true);
                builder.setTitle("students lists");
                builder.setMessage(buffer.toString());
                builder.show();
                Toast.makeText(studentsDB_activity.this,"Successful View",Toast.LENGTH_LONG).show();

               // startActivity(new Intent(studentsDB_activity.this,studentslist.class));

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdate = myData.updatestudents(
                        sname.getText().toString(),
                        surname.getText().toString(),
                        fname.getText().toString(),
                        id.getText().toString());

                if(isUpdate == true)
                    Toast.makeText(studentsDB_activity.this,"Data Updated",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(studentsDB_activity.this,"Data Not Updated",Toast.LENGTH_LONG).show();
            }
                // startActivity(new Intent(studentsDB_activity.this,studentslist.class));

            });


// just a comment

        //delete button lister
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myData.Deletestudents(id_val);
                Toast.makeText(studentsDB_activity.this,"Successful Delete",Toast.LENGTH_LONG).show();
            }
        });
    }
}
