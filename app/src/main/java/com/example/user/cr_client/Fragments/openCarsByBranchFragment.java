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
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.cr_client.R;
import com.example.user.cr_client.backend.DBManagerFactory;
import com.example.user.cr_client.controller.LogIn;
import com.example.user.cr_client.controller.MainActivity;
import com.example.user.cr_client.entities.Car;
import com.example.user.cr_client.entities.Order;

import java.util.List;


public class openCarsByBranchFragment extends Fragment {
    public openCarsByBranchFragment() {

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
        final View view =  inflater.inflate(R.layout.list_cars, container, false);
        try {
            new AsyncTask<Void, Void,List<Car>>() {
                @Override
                protected void onPostExecute(final List<Car> car) {
                    super.onPostExecute(car);


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
                    ListView listCars = (ListView) view.findViewById(R.id.list_item_cars);
                    listCars.setAdapter(adapter);
                    listCars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Car car1 = (Car) adapterView.getAdapter().getItem(i);
                           openOrder(car1);
                        }

                    });
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


    public void openOrder(final Car car)
    {

        try {
            new AsyncTask<Void, Void,Boolean>() {
                @Override
                protected void onPostExecute(Boolean flag) {
                    super.onPostExecute(false);
                    if(flag) {
                        Toast.makeText(getActivity(), "Order Open", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                protected Boolean doInBackground(Void... params) {
                    return DBManagerFactory.getManager().openOrder(new Order(LogIn.getIdCustomer(),null,car.getCarNumber(),null,null,car.getKilometers(),0,false,0,0,0));
                }
            }.execute();
        } catch (Exception e) {

        }
        homeFragment home = new homeFragment();
        ((MainActivity) getActivity()).changeFragement(home);
    }

}
