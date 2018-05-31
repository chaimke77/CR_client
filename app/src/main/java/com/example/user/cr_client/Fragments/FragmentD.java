package com.example.user.cr_client.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.cr_client.R;


public class FragmentD extends Fragment {
    public FragmentD() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String userName = getArguments().getString("userName");

        View view =  inflater.inflate(R.layout.d, container, false);
        final TextView txtUser = (TextView)view.findViewById(R.id.txtUser);

        txtUser.setText(userName);



        return view;
    }
}
