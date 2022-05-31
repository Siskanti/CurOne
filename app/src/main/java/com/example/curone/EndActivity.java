package com.example.curone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EndActivity extends AppCompatActivity {
    Button add;
    Adapterr adapterr;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ArrayList<Model> listModel;
    RecyclerView recyclerView;

    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        email = getIntent().getStringExtra(TampilanActivity.EMAIL_INTENT);

        add = findViewById(R.id.btn_add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextActivity = new Intent(getApplicationContext(), TampilanActivity.class);
                nextActivity.putExtra(TampilanActivity.EMAIL_INTENT, email);
                startActivity(nextActivity);
            }
        });
        recyclerView = findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayout);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        tampilData();

    }

    private void tampilData() {
        database.child("Postingan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listModel = new ArrayList<>();
                for (DataSnapshot item : snapshot.getChildren()) {
                    Model model = item.getValue(Model.class);
                    if (model.getUser().equals(email)) {
                        model.setKey(item.getKey());
                        listModel.add(model);
                    }

                }
                adapterr = new Adapterr(listModel, EndActivity.this);
                recyclerView.setAdapter(adapterr);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}