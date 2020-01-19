package com.example.navigationbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class Class_Routine_Detail_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Routine> routines;
    private RoutineAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_class__routine__detail_ );


        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance();

        Intent intent=getIntent();
        Query query = database.getReference("Routine");


        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                routines=new ArrayList<>();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()) {
                    Routine routine=dataSnapshot1.getValue(Routine.class);
                    routines.add(routine);
                }
                adapter= new RoutineAdapter( Class_Routine_Detail_Activity.this,routines );
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

    }
    }

