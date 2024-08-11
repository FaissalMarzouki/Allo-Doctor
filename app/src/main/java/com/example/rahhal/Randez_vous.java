package com.example.rahhal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

public class Randez_vous extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {




    private static String currentDateString=null;
    private DatabaseReference userDatabaseRef;
    private long counter;

    public static String getCurrentDateString() {
            return currentDateString;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_randez_vous);
            getSupportActionBar().hide();
            Button login = (Button) findViewById(R.id.login);


            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // create a new intent to open the activity of randez_vous
                    Intent patientIntent = new Intent(Randez_vous.this,LoginPatient.class);

                    startActivity(patientIntent);
                }
            });




            Button button = (Button) findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment datePicker = new RDVHelper();

                    datePicker.show(getSupportFragmentManager(), "date picker");


                }

            });

        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


            Calendar dd = Calendar.getInstance();
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());


            userDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Patient").child(currentDateString);

            //use the DataSnapshot Class to get get counter for numbring Patient and used in Randez-vous Class
            userDatabaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        counter= (int) snapshot.getChildrenCount();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



            if( (counter>=10) || (c.getTimeInMillis()< dd.getTimeInMillis()) || (c.get( Calendar.DAY_OF_WEEK )== Calendar.SATURDAY) || (c.get( Calendar.DAY_OF_WEEK )== Calendar.SUNDAY)){
                Toast.makeText( Randez_vous.this,"day not avaible",Toast.LENGTH_LONG ).show();

            } else {
                Toast.makeText( Randez_vous.this,"to day is  avaible",Toast.LENGTH_LONG ).show();

                Intent intent = new Intent(Randez_vous.this,Info_Personal.class);
                startActivity(intent);
            }

        }



    }