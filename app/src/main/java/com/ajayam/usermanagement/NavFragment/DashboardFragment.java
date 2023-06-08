package com.ajayam.usermanagement.NavFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ajayam.usermanagement.R;
import com.ajayam.usermanagement.SharedPrefManager;

public class DashboardFragment extends Fragment {
    TextView etname, etemail;
    SharedPrefManager sharedPrefManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        etname=view.findViewById(R.id.etname);
        etemail=view.findViewById(R.id.etemail);

        sharedPrefManager=new SharedPrefManager(getActivity());

        String userName="Hey' "+sharedPrefManager.getUser().getUsername();
        etname.setText(userName);
        etemail.setText(sharedPrefManager.getUser().getEmail());

        return view;
    }
}