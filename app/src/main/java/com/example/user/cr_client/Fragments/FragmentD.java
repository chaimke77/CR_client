package com.example.user.cr_client.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.user.cr_client.R;
import com.example.user.cr_client.backend.DBManagerFactory;
import com.example.user.cr_client.entities.Car;

import java.util.List;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final String branch = getArguments().getString("branch");
        final View view =  inflater.inflate(R.layout.d, container, false);
        try {
            new AsyncTask<Void, Void,List<Car>>() {
                @Override
                protected void onPostExecute(List<Car> car) {
                    super.onPostExecute(car);
                   /* ArrayList<Car> cars = new ArrayList<Car>();
                    for(Car item:car)
                        cars.add(item);*/

                    ArrayAdapter<Car> adapter = new ArrayAdapter<Car>(getActivity(),
                            android.R.layout.simple_list_item_1, android.R.id.text1, car );
                    ListView listCars = (ListView) view.findViewById(R.id.list_item_cars);
                    listCars.setAdapter(adapter);

                }

                @Override
                protected List<Car> doInBackground(Void... params) {
                    return DBManagerFactory.getManager().getAvailableCarOfBranch(branch);
                }
            }.execute();
        } catch (Exception e) {

        }










        return view;
    }
}
