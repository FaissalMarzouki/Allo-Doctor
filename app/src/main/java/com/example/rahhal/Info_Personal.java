package com.example.rahhal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class Info_Personal extends AppCompatActivity {

    private TextInputEditText nom ,age,numero,email,cin,adress,civilite;
    private static String name,ci;

    public TextInputEditText getNom() {
        return nom;
    }

    public void setNom(TextInputEditText nom) {
        this.nom = nom;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Info_Personal.name = name;
    }

    public static String getCi() {
        return ci;
    }

    public static void setCi(String ci) {
        Info_Personal.ci = ci;
    }

    private DatabaseReference userDatabaseRef;
    private static int counter = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_personal);
        getSupportActionBar().hide();

        Button login = findViewById(R.id.next);
        login.setBackgroundColor(Color.WHITE);
       // linking TextInputEditText and button with xml code
        Button next = findViewById(R.id.next);
        nom = findViewById(R.id.nom);
        age = findViewById(R.id.age);
        numero = findViewById(R.id.numero);
        email = findViewById(R.id.email);
        cin = findViewById(R.id.cin);
        adress = findViewById(R.id.adress);
        civilite = findViewById(R.id.civilite);
        userDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Patient").child(Randez_vous.getCurrentDateString());

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

        //make a button for uploading data and move on to next page
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get values from users and change them to string
                 name = nom.getText().toString();
                 if (TextUtils.isEmpty(name)){
                     nom.setError("entrer ton nom ");
                     return;
                 }


                String ag = age.getText().toString();
                if (TextUtils.isEmpty(ag)){
                    age.setError("entrer ton age ");
                    return;
                }
                String num = numero.getText().toString();
                if (TextUtils.isEmpty(num)){
                    numero.setError("entrer ton nom ");
                    return;
                }
                String mail = email.getText().toString();
                if (TextUtils.isEmpty(mail)){
                    email.setError("entrer ton nom ");
                    return;
                }
                 ci = cin.getText().toString();
                if (TextUtils.isEmpty(ci)){
                    cin.setError("entrer ton nom ");
                    return;
                }
                String add = adress.getText().toString();
                if (TextUtils.isEmpty(add)){
                    adress.setError("entrer ton nom ");
                    return;
                }
                String civi = civilite.getText().toString();
                if (TextUtils.isEmpty(civi)){
                    civilite.setError("entrer ton nom ");
                    return;
                }

                //a class help in manage variables
              PatientHelper helper = new PatientHelper(name,mail,ag,num,ci,add,civi,"null");


                //upload data to database
                userDatabaseRef.child(String.valueOf(counter+1)).setValue(helper);


                //move on to next page
                Intent intent = new Intent(Info_Personal.this, LoginPatient.class);
                startActivity(intent);



            }
        });

    }
}