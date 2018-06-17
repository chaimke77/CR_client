package com.example.user.cr_client.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.cr_client.R;
import com.example.user.cr_client.backend.DBManagerFactory;
import com.example.user.cr_client.controller.MainActivity;
import com.example.user.cr_client.entities.Order;

import java.util.Date;
import java.util.List;

/**
 * Created by User on 06/06/2018.
 */

public class myCarFragment extends Fragment {

    public myCarFragment()
    {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().findViewById(R.id.BranchFilter).setVisibility(View.GONE);
        getActivity().findViewById(R.id.spinnerBrunch).setVisibility(View.GONE);

        final View view = inflater.inflate(R.layout.list_cars, container, false);
        try {
            new AsyncTask<Void, Void, List<Order>>() {
                @Override
                protected void onPostExecute(final List<Order> order) {
                    super.onPostExecute(order);
                    if (order.isEmpty())
                        Toast.makeText(getActivity(), "Not exists orders", Toast.LENGTH_SHORT).show();
                    else {
                        ArrayAdapter<Order> adapter = new ArrayAdapter<Order>(getActivity(),
                                android.R.layout.simple_list_item_1, android.R.id.text1, order) {
                            @Override
                            public View getView(int position, View convertView, ViewGroup parent) {
                                if (convertView == null)
                                    convertView = View.inflate(getContext(), R.layout.item_order_view, null);
                                TextView numOrder = (TextView) convertView.findViewById(R.id.orderNumV);
                                TextView numCar = (TextView) convertView.findViewById(R.id.carNumberV);
                                TextView date = (TextView) convertView.findViewById(R.id.dateV);

                                numOrder.setText("Order number: " + ((Long) order.get(position).getOrderNum()).toString());
                                numCar.setText("Car number: " + ((Long) order.get(position).getNumOfCars()).toString());
                                date.setText("Start Date: " + ((Date)order.get(position).getRentalStart()).toString());
                                convertView.setBackgroundColor(position%2==0? Color.GRAY:Color.BLACK);
                                return convertView;
                            }
                        };
                        ListView listOrders = (ListView) view.findViewById(R.id.list_item_cars);
                        listOrders.setAdapter(adapter);
                        listOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, final View view, int i, long l) {
                                final Order order1 = (Order) adapterView.getAdapter().getItem(i);
                                final EditText input = new EditText(getContext());
                                input.setHint("Please enter current km");
                                input.setInputType(InputType.TYPE_CLASS_NUMBER);

                                input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(12)});

                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
                                alertDialogBuilder.setView(input);
                                AlertDialog.OnClickListener onClickListener = new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        switch (which) {
                                            case Dialog.BUTTON_NEGATIVE:
                                                break;
                                            case Dialog.BUTTON_POSITIVE:
                                                if(input.getText().toString().equals(""))
                                                {
                                                    Toast.makeText(getContext(), "Error input", Toast.LENGTH_SHORT).show();
                                                    break;
                                                }
                                                if((order1.getKilometerStart()>Long.parseLong(input.getText().toString()))) {
                                                    Toast.makeText(getContext(), "Error input", Toast.LENGTH_SHORT).show();
                                                    break;
                                                }

                                                order1.setKilometerFinish(Long.parseLong(input.getText().toString()));
                                                ((MainActivity)getActivity()).closeOrder(order1);
                                                break;
                                        }
                                    }
                                };
                                alertDialogBuilder.setMessage("Do you want to close this order?");
                                alertDialogBuilder.setPositiveButton("Ok", onClickListener);
                                alertDialogBuilder.setNegativeButton("Cancel ", onClickListener);
                                AlertDialog alertDialog = alertDialogBuilder.create();
                                alertDialog.show();
                            }
                        });
                    }
                }

                @Override
                protected List<Order> doInBackground(Void... params) {
                    return DBManagerFactory.getManager().getAllOpenOrders();
                }
            }.execute();
        } catch (Exception e) {

        }

        return view;
    }

}
