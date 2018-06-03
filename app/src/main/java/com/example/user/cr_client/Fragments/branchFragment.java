package com.example.user.cr_client.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.user.cr_client.R;
import com.example.user.cr_client.backend.DBManagerFactory;
import com.example.user.cr_client.controller.MainActivity;
import com.example.user.cr_client.entities.Branch;

import java.util.ArrayList;
import java.util.List;



public class branchFragment extends Fragment {


    public branchFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.list_branch, container, false);

        try {
            new AsyncTask<Void, Void,List<Branch>>() {
                @Override
                protected void onPostExecute(List<Branch> branch) {
                    super.onPostExecute(branch);
                    ArrayList<String> address = new ArrayList<>();
                    for (Branch item:branch)
                        address.add(item.getAdress());
                   // ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                     //       android.R.layout.simple_list_item_1, android.R.id.text1, address );


                       /* @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            if(position % 2==1)
                            {
                                convertView.setBackgroundResource(Color.alpha(1));
                            }
                            else
                                {
                            convertView.setBackgroundResource(Color.alpha(2));
                            }
                            return view;}
                    };*/

                    Spinner branchSpiner= (Spinner)view.findViewById(R.id.spinnerBrunch);
                    branchSpiner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, address));

                    //ListView listBranch = (ListView) view.findViewById(R.id.list_item);
                    //listBranch.setAdapter(adapter);
                    branchSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                    {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String branch = (String) adapterView.getAdapter().getItem(i);
                            openCarsByBranchFragment fragment = new openCarsByBranchFragment();
                            Bundle args = new Bundle();
                            args.putString("branch", branch);
                            fragment.setArguments(args);
                            ((MainActivity) getActivity()).changeFragement(fragment);
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> arg0){}
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
