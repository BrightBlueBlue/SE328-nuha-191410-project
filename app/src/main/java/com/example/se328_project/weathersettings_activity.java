package com.example.se328_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class weathersettings_activity extends AppCompatActivity {

    // we"ll make HTTP request to this URL to retrieve weather conditions
    String weatherURL = "https://api.openweathermap.org/data/2.5/weather?q=Riyadh&appid=37ab1f1032ed33f2f1ce157df9d65aa8&units=metric";
    ImageView weatherBackground;
    // Textview to show temperature and description
    TextView Temperature, Description, Humidity, Cloudy;
    Spinner spinner;
    Button button, Gobackbtn2;
    JSONObject jsonObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weathersettings);


        Temperature = (TextView) findViewById(R.id.temperature);
        Description = (TextView) findViewById(R.id.description);
        Humidity = (TextView) findViewById(R.id.humidity);
        Cloudy = (TextView) findViewById(R.id.cloudy);
        weatherBackground = (ImageView) findViewById(R.id.weatherbackground);

        button = (Button)findViewById(R.id.button);
        Gobackbtn2 = (Button)findViewById(R.id.gobackbtn2);
        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        weather(weatherURL);

        Gobackbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(weathersettings_activity.this,welcome_activity.class));
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String weatherURL = "https://api.openweathermap.org/data/2.5/weather?q=";
                String place = spinner.getSelectedItem().toString();
                weatherURL = weatherURL + place + "&appid=ec00986f68ad6ce7f6c39e980fffaa46&units=metric";
                weather(weatherURL);

            }
        });
    }


    public void weather(String url) {



        JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Nuha", "Responce success");
                        Log.d("Nuha", response.toString());



                        try {

                            JSONObject jsonMain = response.getJSONObject("main");





                            String town = response.getString("name");
                            Description.setText(town);


                            double temp = jsonMain.getDouble("temp");
                            Log.d("Nuha-temp", String.valueOf(temp));
                            Temperature.setText(String.valueOf(temp));

                            double humi = jsonMain.getDouble("humidity");
                            Log.d("Nuha-humi", String.valueOf(humi));
                            Humidity.setText(String.valueOf(humi));

                            jsonMain = response.getJSONObject("sys");






                            JSONArray jArray = response.getJSONArray("weather");
                            for (int i = 0; i < jArray.length(); i++) {
                                Log.d("Nuha Array", jArray.getString(i));
                                JSONObject oneObject = jArray.getJSONObject(i);
                                String wheater =
                                        oneObject.getString("main");
                                Log.d("nuha weather", wheater);


                                if(wheater.equals("Clouds")){
                                    Glide.with(weathersettings_activity.this)
                                            .load("https://smartwatermagazine.com/sites/default/files/styles/thumbnail-830x455/public/clouds-pixabay.jpg?itok=bAYXTkNK")
                                            .into(weatherBackground);

                                }



                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Reciever Error", e.toString());

                        }


                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Nuha","Responce Error");

            }
        }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObj);

    }




}




