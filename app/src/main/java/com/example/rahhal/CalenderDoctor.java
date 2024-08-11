package com.example.rahhal;

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

import java.text.DateFormat;
import java.util.Calendar;


public class CalenderDoctor extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {



    private TextView txt;
    private static String currentDateString=null;

    private long counter;

    public static String getCurrentDateString() {
        return currentDateString;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        TextView txt = findViewById(R.id.text);


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



        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());







        if( (c.get( Calendar.DAY_OF_WEEK )== Calendar.SATURDAY) || (c.get( Calendar.DAY_OF_WEEK )== Calendar.SUNDAY)){
            Toast.makeText( CalenderDoctor.this,"day not avaible",Toast.LENGTH_LONG ).show();

        } else {
            Toast.makeText( CalenderDoctor.this,"to day is  avaible",Toast.LENGTH_LONG ).show();

            Intent intent = new Intent(CalenderDoctor.this, ListPatient.class);
            startActivity(intent);
        }



    }
}