package com.example.rahhal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ListPatient extends AppCompatActivity {

    RecyclerView recview;
    Myadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);

        recview= findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<DoctorHelperPatient> options =
                new FirebaseRecyclerOptions.Builder<DoctorHelperPatient>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Patient").child(CalenderDoctor.getCurrentDateString()), DoctorHelperPatient.class)
                        .build();

        adapter=new Myadapter(options);
        recview.setAdapter(adapter);

    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                txtSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                txtSearch(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    private void txtSearch(String s){
        FirebaseRecyclerOptions<DoctorHelperPatient> options =
                new FirebaseRecyclerOptions.Builder<DoctorHelperPatient>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Patient").child(CalenderDoctor.getCurrentDateString()).orderByChild("name").startAt(s).endAt(s+"~"), DoctorHelperPatient.class)
                        .build();
        adapter = new Myadapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);
    }
}