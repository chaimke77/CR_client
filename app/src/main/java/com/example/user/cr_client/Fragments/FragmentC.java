package com.example.user.cr_client.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import com.example.user.cr_client.R;
import com.example.user.cr_client.controller.MainActivity;

import java.util.ArrayList;

public class FragmentC extends Fragment {

    public FragmentC() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.c, container, false);
        final Button button = (Button) view.findViewById(R.id.btnFragmentC);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ArrayList<String> users = new ArrayList<>();
                for (int i=0;i< 10;i++)
                    users.add("user no " + i);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, android.R.id.text1, users);
                ListView listUsers = (ListView) view.findViewById(R.id.list_item);
                listUsers.setAdapter(adapter);
                listUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String user = (String) adapterView.getAdapter().getItem(i);
                        FragmentD fragmentD = new FragmentD();
                        Bundle args = new Bundle();
                        args.putString("userName", user);
                        fragmentD.setArguments(args);
                        ((MainActivity)getActivity()).changeFragement(fragmentD);


                    }
                });
            }
        });
        return view;
    }

}
