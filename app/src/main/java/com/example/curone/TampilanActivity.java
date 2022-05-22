package com.example.curone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TampilanActivity extends AppCompatActivity {
    private EditText editText;
    private Button btnSave;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilan);
        editText = findViewById(R.id.edit_teks);
        btnSave = findViewById(R.id.btn_save);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getText = editText.getText().toString();

                if(getText.isEmpty()){
                    editText.setError("please fill out this form");
                }else{
                    database.child("Postingan").push().setValue(new Model(getText)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(TampilanActivity.this, "Berhasil disimpan", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(TampilanActivity.this, EndActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(TampilanActivity.this, "Gagal menyimpan", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });



    }
}