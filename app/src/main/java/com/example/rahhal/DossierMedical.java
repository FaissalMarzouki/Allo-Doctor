package com.example.rahhal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DossierMedical extends AppCompatActivity {
private DatabaseReference userRef;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dossier_medical);
        Intent intent = getIntent();
        String cin = intent.getStringExtra("cin");
        String date = intent.getStringExtra("date");
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();


        TextInputEditText nameed = findViewById(R.id.nomed);
        TextInputEditText ageed = findViewById(R.id.ageed);
        TextInputEditText numeroed = findViewById(R.id.numeroed);
        TextInputEditText addressed = findViewById(R.id.adressed);
        TextInputEditText emailed = findViewById(R.id.emailed);
        TextInputEditText civiliteed = findViewById(R.id.civiliteed);
        TextInputEditText cined = findViewById(R.id.cined);

        TextView rdv =findViewById(R.id.date);
        rdv.setText(date);
        Query query = myRef.child("Patient").child(date).orderByChild("cin").equalTo(cin);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                        String name = childSnapshot.child("name").getValue(String.class);
                        String age = childSnapshot.child("age").getValue(String.class);
                        String numero = childSnapshot.child("numero").getValue(String.class);
                        String email = childSnapshot.child("email").getValue(String.class);
                        String civilite = childSnapshot.child("civilite").getValue(String.class);
                        String address = childSnapshot.child("adress").getValue(String.class);

                        nameed.setText(name);
                        ageed.setText(age);
                        addressed.setText(address);
                        numeroed.setText(numero);
                        emailed.setText(email);
                        civiliteed.setText(civilite);
                        cined.setText(cin);
                        break; // Assuming there is only one matching child
                    }
                } else {
                    Toast.makeText(DossierMedical.this, "Échec", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DossierMedical.this, "Erreur lors de la recherche du rendez-vous", Toast.LENGTH_LONG).show();
            }
        });

        Button annuler = findViewById(R.id.annuler);
        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cinToCancel = cined.getText().toString();
                Query query = myRef.child("Patient").child(date).orderByChild("cin").equalTo(cinToCancel);


                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            // Supprimer les données du patient de la base de données
                            snapshot.getRef().removeValue();
                            Toast.makeText(DossierMedical.this, "Les informations ont été annulées.", Toast.LENGTH_SHORT).show();

                            Intent patientIntent = new Intent(DossierMedical.this,MainActivity.class);
                            startActivity(patientIntent);
                            finish();


                        } else {
                            Toast.makeText(DossierMedical.this, "Le cin n'existe pas dans la base de données.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(DossierMedical.this, "Erreur lors de l'annulation des informations.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        Button update = findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer les nouvelles valeurs des vues du formulaire
                String newName = nameed.getText().toString();
                String newAge = ageed.getText().toString();
                String newNumero = numeroed.getText().toString();
                String newAddress = addressed.getText().toString();
                String newEmail = emailed.getText().toString();
                String newCivilite = civiliteed.getText().toString();
                String newCin = cined.getText().toString();

                // Vérifier si le cin existe dans la base de données
                Query query = myRef.child("Patient").child(date).orderByChild("cin").equalTo(newCin);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            // Mettre à jour les informations dans la base de données en temps réel
                            snapshot.getRef().child("name").setValue(newName);
                            snapshot.getRef().child("age").setValue(newAge);
                            snapshot.getRef().child("numero").setValue(newNumero);
                            snapshot.getRef().child("address").setValue(newAddress);
                            snapshot.getRef().child("email").setValue(newEmail);
                            snapshot.getRef().child("civilite").setValue(newCivilite);


                            Toast.makeText(DossierMedical.this, "Les informations ont été mises à jour.", Toast.LENGTH_SHORT).show();
                            Intent patientIntent = new Intent(DossierMedical.this,LoginPatient.class);
                            startActivity(patientIntent);
                            finish();


                        } else {
                            Toast.makeText(DossierMedical.this, "Le cin n'existe pas dans la base de données.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(DossierMedical.this, "Erreur lors de la mise à jour des informations.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });





    }
}