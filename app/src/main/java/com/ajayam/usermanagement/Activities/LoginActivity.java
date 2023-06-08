package com.ajayam.usermanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ajayam.usermanagement.ModelResponse.LoginResponse;
import com.ajayam.usermanagement.R;
import com.ajayam.usermanagement.RetrofitClient;
import com.ajayam.usermanagement.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email, password;
    Button login;
    TextView registerLink;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        email = findViewById(R.id.etemail);
        password = findViewById(R.id.etpassword);
        login = findViewById(R.id.btnlogin);
        registerLink = findViewById(R.id.registerlink);

        registerLink.setOnClickListener(this);
        login.setOnClickListener(this);

        sharedPrefManager=new SharedPrefManager(getApplicationContext());

    }

    @Override
    public void onClick(View view) {

//        switch (view.getId()) {}


        int id = view.getId();
        if (id == R.id.btnlogin){

            userLogin();

        } else if (id == R.id.registerlink) {
            switchOnRegister();
        }
    }

    private void userLogin() {

        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();


        if (userEmail.isEmpty()){
            email.requestFocus();
            email.setError("Please enter your Email");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            email.requestFocus();
            email.setError("Please enter Correct Email");
            return;
        }
        if (userPassword.isEmpty()){
            password.requestFocus();
            password.setError("Please enter your Password");
            return;
        }
        if (userPassword.length()<8){
            password.requestFocus();
            password.setError("Enter more than OR equal to 8 character in password");
            return;
        }

        Call<LoginResponse> call= RetrofitClient.getInstance().getApi().login(userEmail,userPassword);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                if(response.isSuccessful()){
                   if(loginResponse.getError().equals("200")) {

                       sharedPrefManager.saveUser(loginResponse.getUser());
                       Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                       startActivity(intent);
                       Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();

                   }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void switchOnRegister() {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }

    @Override
    protected void onStart(){
        super.onStart();

        if (sharedPrefManager.isLoggedin()) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}