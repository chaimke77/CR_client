package com.example.user.cr_client.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.cr_client.R;
import com.example.user.cr_client.backend.DBManagerFactory;
import com.example.user.cr_client.controller.LogIn;
import com.example.user.cr_client.controller.MainActivity;
import com.example.user.cr_client.entities.Branch;
import com.example.user.cr_client.entities.Car;
import com.example.user.cr_client.entities.Order;

import java.util.ArrayList;
import java.util.List;



public class openCarFragment extends Fragment {


    public openCarFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        MainActivity.getSpinner().setVisibility(View.VISIBLE);
        MainActivity.getText().setVisibility(View.VISIBLE);
        final View view = inflater.inflate(R.layout.list_branch, container, false);

        try {
            new AsyncTask<Void, Void,List<Car>>() {
                @Override
                protected void onPostExecute(final List<Car> car) {
                    super.onPostExecute(car);
                    if(car.isEmpty())
                        Toast.makeText(getActivity(), "Sorry but not exists cars", Toast.LENGTH_SHORT).show();
                    else{


                        ArrayAdapter<Car> adapter = new ArrayAdapter<Car>(getActivity(),
                                android.R.layout.simple_list_item_1, android.R.id.text1, car){
                            @Override
                            public View getView(int position, View convertView, ViewGroup parent) {
                                if (convertView == null)
                                    convertView = View.inflate(getContext(), R.layout.item_car_view, null);
                                TextView numModel = (TextView) convertView.findViewById(R.id.modelNumView);
                                TextView km = (TextView) convertView.findViewById(R.id.kilometersView);
                                TextView numCar = (TextView) convertView.findViewById(R.id.carNumberView);

                                numModel.setText("Model Number: " + ((Long) car.get(position).getModel()).toString());
                                km.setText("Km: " + ((Long) car.get(position).getKilometers()).toString());
                                numCar.setText("Car Number: " + ((Long) car.get(position).getCarNumber()).toString());
                                return convertView;
                            }
                        };
                        ListView listCars = (ListView) view.findViewById(R.id.list_item);
                        listCars.setAdapter(adapter);
                        listCars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Car car1 = (Car) adapterView.getAdapter().getItem(i);
                                ((MainActivity)getActivity()).openOrder(car1);
                            }

                        });
                    }
                }

                @Override
                protected List<Car> doInBackground(Void... params) {
                    return DBManagerFactory.getManager().getAvailableCar();
                }
            }.execute();
        } catch (Exception e) {

        }

        return view;
    }



}
