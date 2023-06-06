package com.ajayam.usermanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ajayam.usermanagement.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email, password;
    Button login;
    TextView registerLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //HIDE ACTION BAR
//        getSupportActionBar().hide();

//        //HIDE STATUS BAR
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        email = findViewById(R.id.etemail);
        password = findViewById(R.id.etpassword);
        login = findViewById(R.id.btnlogin);
        registerLink = findViewById(R.id.registerlink);

        registerLink.setOnClickListener(this);
        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

//        switch (view.getId()) {
//            case R.id.btnlogin:
//                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//                break;
//            case R.id.registerlink:
//                switchOnRegister();
//                break;
//        }

        int id = view.getId();
        if (id == R.id.btnlogin){
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        } else if (id == R.id.registerlink) {
            switchOnRegister();
        }


    }

    private void switchOnRegister() {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }
}