package com.example.curone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private final String TAG = "LoginActivity";
    private EditText editEmail, editPass;
    private Button btn_login;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editEmail = findViewById(R.id.edit2);
        editPass = findViewById(R.id.edit3);
        btn_login = findViewById(R.id.btn);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Waiting");
        progressDialog.setCancelable(false);

        btn_login.setOnClickListener(v -> {
            if (editEmail.getText().length() > 0 && editPass.getText().length() > 0) {
                login(editEmail.getText().toString(), editPass.getText().toString());
            } else {
                Toast.makeText(this, "please fill in your data", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    if (task.getResult().getUser() != null) {
                        reload(task.getResult().getUser().getEmail());
                    } else {
                        Toast.makeText(getApplicationContext(), "Login gagal", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Login gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void reload(String email) {
        Intent nextActivity = new Intent(getApplicationContext(), TampilanActivity.class);
        nextActivity.putExtra(TampilanActivity.EMAIL_INTENT, email);
        startActivity(nextActivity);
    }

}