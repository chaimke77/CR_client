package com.example.user.cr_client.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.user.cr_client.R;
import com.example.user.cr_client.backend.DBManagerFactory;
import com.example.user.cr_client.controller.MainActivity;
import com.example.user.cr_client.entities.Branch;

import java.util.ArrayList;
import java.util.List;

public class FragmentC extends Fragment {


    public FragmentC() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.c, container, false);

        try {
            new AsyncTask<Void, Void,List<Branch>>() {
                @Override
                protected void onPostExecute(List<Branch> branch) {
                    super.onPostExecute(branch);
                    ArrayList<String> address = new ArrayList<>();
                    for (Branch item:branch)
                        address.add(item.getAdress());
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_list_item_1, android.R.id.text1, address );
                    ListView listCars = (ListView) view.findViewById(R.id.list_item);
                    listCars.setAdapter(adapter);
                    listCars.setOnItemClickListener(new AdapterView.OnItemClickListener()
                    {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            String branch = (String) adapterView.getAdapter().getItem(i);
                            FragmentD fragmentD = new FragmentD();
                            Bundle args = new Bundle();
                            args.putString("branch", branch);
                            fragmentD.setArguments(args);
                            ((MainActivity) getActivity()).changeFragement(fragmentD);


                        }
                    });

                }

                @Override
                protected List<Branch> doInBackground(Void... params) {
                    return DBManagerFactory.getManager().getAllBrunches();
                }
            }.execute();
        } catch (Exception e) {

        }
        return view;
    }

}
