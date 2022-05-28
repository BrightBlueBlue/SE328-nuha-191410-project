package com.example.se328_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcome_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button studentsdb = (Button) findViewById(R.id.studentsdbbtn);
        Button weathersettings = (Button) findViewById(R.id.weathersettingsbtn);

        studentsdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(welcome_activity.this,studentsDB_activity.class));
            }
        });

        weathersettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(welcome_activity.this,weathersettings_activity.class));

            }
        });
    }
}