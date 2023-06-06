package com.ajayam.usermanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ajayam.usermanagement.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView loginLink;
    EditText name, email, password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//HIDE ACTION BAR
//        getSupportActionBar().hide();

//        //HIDE STATUS BAR
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        name = findViewById(R.id.etname);
        email = findViewById(R.id.etemail);
        password = findViewById(R.id.etpassword);
        register = findViewById(R.id.btnregister);
        loginLink = findViewById(R.id.loginLink);

        loginLink.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

//        switch (view.getId()) {

//        }

        int id = view.getId();
        if (id == R.id.btnregister){
            Toast.makeText(this, "Register", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.loginLink) {
            switchOnLogin();
        }
    }

    private void switchOnLogin() {
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
    }
}