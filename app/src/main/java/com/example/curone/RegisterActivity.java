package com.example.curone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private EditText editName, editEmail, editPassword, editConfirm;
    private Button btn_register;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editName = findViewById(R.id.edit1);
        editEmail = findViewById(R.id.edit2);
        editPassword = findViewById(R.id.edit3);
        editConfirm = findViewById(R.id.edit4);
        btn_register = findViewById(R.id.btnRegis);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Waiting");
        progressDialog.setCancelable(false);

        btn_register.setOnClickListener(v ->{
            if(editName.getText().length()>0 && editEmail.getText().length()>0 && editPassword.getText().length()>0 && editConfirm.getText().length()>0){
                if(editPassword.getText().toString().equals(editConfirm.getText().toString())){
                    register(editName.getText().toString(), editEmail.getText().toString(), editPassword.getText().toString());
                }else{
                    Toast.makeText(this, "please enter the same password", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "please fill in your data", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void register(String name, String email, String password){
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful() && task.getResult()!=null){
                    FirebaseUser firebaseUser = task.getResult().getUser();
                    if (firebaseUser!=null) {
                        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .build();
                        firebaseUser.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                reload();
                            }
                        });
                    }else{
                        Toast.makeText(getApplicationContext(), "Registrasi gagal", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void reload(){
        startActivity(new Intent(getApplicationContext(),MenuActivity.class));
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }

    }
}