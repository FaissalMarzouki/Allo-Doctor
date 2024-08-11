package com.example.rahhal;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
public class LoginPatient extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private TextInputEditText cintext;
    private String cin;
    private Button consulter;
    private String currentDateString;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_patient);
        getSupportActionBar().hide();
        cintext = findViewById(R.id.cin);
        consulter = findViewById(R.id.consulter);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();


        this.context=getApplicationContext();


        
        consulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cin = cintext.getText().toString();
                if (cin.isEmpty()) {
                    Toast.makeText(LoginPatient.this, "Veuillez saisir un numéro de cin", Toast.LENGTH_LONG).show();
                } else {
                    DialogFragment datePicker = new RDVHelper();
                    datePicker.show(getSupportFragmentManager(), "date picker");
                }
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

        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {




            Toast.makeText(LoginPatient.this, "Le rendez-vous n'existe pas ce jour-là", Toast.LENGTH_LONG).show();



        } else {
            String cin = cintext.getText().toString();
            if (!TextUtils.isEmpty(cin)) {
                loginUserByCinAndDate(cin, currentDateString);
            } else {
                Toast.makeText(LoginPatient.this, "Veuillez saisir un numéro de CIN", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void loginUserByCinAndDate(String cin, String currentDateString) {
        // Read from the database
        Query query = myRef.child("Patient").child(currentDateString).orderByChild("cin").equalTo(cin);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Toast.makeText(LoginPatient.this, "Votre rendez-vous existe ce jour-là", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginPatient.this, DossierMedical.class);
                    // Pass the retrieved data to MainActivity2
                    // You can access the data in MainActivity2 using intent.getExtra()

                    intent.putExtra("cin", cin);
                    intent.putExtra("date", currentDateString);

                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginPatient.this, "Aucun rendez-vous trouvé pour ce CIN et cette date", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginPatient.this, "Erreur lors de la recherche du rendez-vous", Toast.LENGTH_LONG).show();
            }
        });
    }

}
