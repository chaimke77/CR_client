package com.example.user.cr_client.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.cr_client.R;


public class FragmentA extends Fragment {
    public FragmentA() {

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
        View view =  inflater.inflate( R.layout.a, container, false);
        final Button button = (Button) view.findViewById(R.id.btnFragmentA);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Click from fragment A", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
