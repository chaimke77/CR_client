package com.example.user.cr_client.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
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
import com.example.user.cr_client.controller.MainActivity;
import com.example.user.cr_client.entities.Car;

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
        MainActivity.getSpinner().setVisibility(View.VISIBLE);
        MainActivity.getText().setVisibility(View.VISIBLE);
        final String branch = this.getArguments().getString("branch");
        final View view =  inflater.inflate(R.layout.list_cars, container, false);
        try {
            new AsyncTask<Void, Void,List<Car>>() {
                @Override
                protected void onPostExecute(final List<Car> car) {
                    super.onPostExecute(car);
                    if (car.isEmpty())
                        Toast.makeText(getActivity(), "Sorry but not exists cars", Toast.LENGTH_SHORT).show();
                    else {


                        ArrayAdapter<Car> adapter = new ArrayAdapter<Car>(getActivity(),
                                android.R.layout.simple_list_item_1, android.R.id.text1, car) {
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
                                convertView.setBackgroundColor(position%2==0? Color.GRAY:Color.BLACK);


                                return convertView;
                            }
                        };
                        ListView listCars = (ListView) view.findViewById(R.id.list_item_cars);
                        listCars.setAdapter(adapter);
                        listCars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                final Car car1 = (Car) adapterView.getAdapter().getItem(i);
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
                                AlertDialog.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        switch (which) {
                                            case Dialog.BUTTON_NEGATIVE:
                                                break;
                                            case Dialog.BUTTON_POSITIVE:
                                                //openOrder(car1);
                                                ((MainActivity) getActivity()).openOrder(car1);
                                                break;
                                        }
                                    }
                                };
                                alertDialogBuilder.setMessage("Do you want to order this car?");
                                alertDialogBuilder.setPositiveButton("Ok", onClickListener);
                                alertDialogBuilder.setNegativeButton("Cancel ", onClickListener);
                                AlertDialog alertDialog = alertDialogBuilder.create();
                                alertDialog.show();
                            }
                        });


                    }
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


   /* public void openOrder(final Car car)
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

    }*/

}
