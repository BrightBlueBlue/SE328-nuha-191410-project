package com.example.se328_project;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(Splash.this,welcome_activity.class));
            }

        };
        Timer opening = new Timer();
        opening.schedule(task, 3000);
    }
}

