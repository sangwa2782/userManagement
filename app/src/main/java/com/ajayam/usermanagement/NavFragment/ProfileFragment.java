package com.ajayam.usermanagement.NavFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ajayam.usermanagement.ModelResponse.LoginResponse;
import com.ajayam.usermanagement.R;
import com.ajayam.usermanagement.RetrofitClient;
import com.ajayam.usermanagement.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    EditText etuserName, etuserEmail;
    Button updateUserAccountBtn;
    SharedPrefManager sharedPrefManager;
    int userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        etuserName=view.findViewById(R.id.userName);
        etuserEmail=view.findViewById(R.id.userEmail);
        updateUserAccountBtn=view.findViewById(R.id.btnUpdateAccount);

        sharedPrefManager= new SharedPrefManager(getActivity());
        userId=sharedPrefManager.getUser().getId();

        updateUserAccountBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnUpdateAccount) {
            updateUserAccount();
        }

    }

    private void updateUserAccount() {

        String username=etuserName.getText().toString().trim();
        String email = etuserEmail.getText().toString().trim();

        if(username.isEmpty()) {
            etuserName.setError("Please enter user name");
            etuserName.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etuserEmail.requestFocus();
            etuserEmail.setError("Please enter Correct Email");
            return;
        }

        Call<LoginResponse> call= RetrofitClient
                .getInstance()
                .getApi()
                .updateUserAccount(userId,username,email);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse updateResponse=response.body();
                if (response.isSuccessful()) {

                    if (updateResponse.getError().equals("200")) {

                        sharedPrefManager.saveUser(updateResponse.getUser());
                        Toast.makeText(getActivity(), updateResponse.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                    else {
                        Toast.makeText(getActivity(), updateResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                
            }
        });
    }
}