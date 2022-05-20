package com.example.curone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

public class MenuActivity extends AppCompatActivity {
    private Button btn_sign_up, btn_sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btn_sign_up= findViewById(R.id.btn_signup);
        btn_sign_in= findViewById(R.id.btn_signin);

        btn_sign_up.setOnClickListener(v ->{
            startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
        });
        btn_sign_in.setOnClickListener(v ->{
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        });
    }


}