package com.example.rahhal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        //find the view that when we click on Patient we move on to activity of Randez_vous
        Button patient = (Button) findViewById(R.id.patient);
        patient.setBackgroundColor(Color.WHITE);


        // set a click listener on that view
        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create a new intent to open the activity of randez_vous
                Intent patientIntent = new Intent(MainActivity.this,Randez_vous.class);
                Bundle b = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle();
                startActivity(patientIntent);
            }
        });


        //find the view that when we click on Docteur we move on to activity of login
        Button docteur = (Button) findViewById(R.id.docteur);
        docteur.setBackgroundColor(Color.WHITE);
        //set a click listner on that view
        docteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create a new intent to open the activity of login
                Intent docteurIntent = new Intent(MainActivity.this,login.class);
                Bundle b = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle();
                startActivity(docteurIntent);
            }
        });




    }
}