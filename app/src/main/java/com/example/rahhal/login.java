package com.example.rahhal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {


   private TextInputEditText email,passeWord;
   private Button next;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        Button login = findViewById(R.id.next);
        login.setBackgroundColor(Color.WHITE);



        email = findViewById(R.id.email);
        passeWord = findViewById(R.id.passeWord);
        next = findViewById(R.id.next);

        mAuth = FirebaseAuth.getInstance();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String loginEmail = email.getText().toString();
                if (TextUtils.isEmpty(loginEmail)){
                    email.setError("entrer ton email ");
                    return;
                }
                String loginPassword = passeWord.getText().toString();
                if (TextUtils.isEmpty(loginPassword)){
                    passeWord.setError("entrer ton most de passe ");
                    return;
                }

                mAuth.signInWithEmailAndPassword(loginEmail,loginPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(login.this,"successful",Toast.LENGTH_SHORT).show();
                            Intent nextIntent = new Intent(login.this, CalenderDoctor.class);
                            startActivity(nextIntent);
                        }
                        else {
                            Toast.makeText(login.this,"echec",Toast.LENGTH_SHORT).show();
                        }

            }
        });
    }

        });
    }
}

