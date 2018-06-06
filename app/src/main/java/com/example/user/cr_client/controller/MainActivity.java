package com.example.user.cr_client.controller;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;


import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.user.cr_client.Fragments.myCarFragment;
import com.example.user.cr_client.Fragments.openCarFragment;
import com.example.user.cr_client.Fragments.openCarsByBranchFragment;
import com.example.user.cr_client.Fragments.homeFragment;
import com.example.user.cr_client.R;
import com.example.user.cr_client.backend.DBManagerFactory;
import com.example.user.cr_client.backend.MyService;
import com.example.user.cr_client.entities.Branch;
import com.example.user.cr_client.entities.Car;
import com.example.user.cr_client.entities.Order;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String mBroadcastAction = "com.example.user.carrentalapplication.A_CUSTOM_INTENT";

    Fragment about, branch, car, my;
    static Spinner branchSpiner;
    static public Spinner getSpinner()
    {
        return branchSpiner;
    }
    static TextView branchFilter;
    static public TextView getText()
    {
        return branchFilter;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(getBaseContext(), MyService.class));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        try {
            new AsyncTask<Void, Void,List<Branch>>() {
                @Override
                protected void onPostExecute(List<Branch> branch) {
                    super.onPostExecute(branch);
                    ArrayList<String> address = new ArrayList<>();
                    address.add("All");
                    for (Branch item:branch)
                        address.add(item.getAdress());

                    branchFilter=(TextView)findViewById(R.id.BranchFilter);
                    branchSpiner= (Spinner)findViewById(R.id.spinnerBrunch);
                    branchSpiner.setPrompt("Select one option");
                    branchSpiner.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, address));
                    branchSpiner.setSelection(0);
                    branchSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                    {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String branch = (String) adapterView.getAdapter().getItem(i);
                            if(branch.equals("All")){
                                changeFragement(new openCarFragment());
                                return;}
                            openCarsByBranchFragment fragment = new openCarsByBranchFragment();
                            Bundle args = new Bundle();
                            args.putString("branch", branch);
                            fragment.setArguments(args);
                            changeFragement(fragment);
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


        about = new homeFragment();
        car = new openCarFragment();
        branch = new openCarsByBranchFragment();
        my = new myCarFragment();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        changeFragement(about);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_about:
                onBackPressed();
                changeFragement(about);
                return true;
            case R.id.nav_branch:
                onBackPressed();
                changeFragement(branch);
                return true;
            case R.id.nav_available_car:
                onBackPressed();
                changeFragement(car);
                return true;
            case R.id.nav_my_car:
                onBackPressed();
                changeFragement(my);
                return true;
            case R.id.nav_send:
                onBackPressed();
                sendEmail();
                return true;
            case R.id.nav_phone:
                onBackPressed();
                phone();
                return true;
            case R.id.nav_exit:
                finish();
                System.exit(0);
                return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void changeFragement(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frgamentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void sendEmail() {


        String[] TO = {"chaimke77@gmail.com"};

        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.setType("message/rfc822");
        try {

            startActivity(Intent.createChooser(emailIntent, "Send mail..."));

        } catch (android.content.ActivityNotFoundException ex) {

        }
    }

    public void phone() {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "0552230150", null)));
    }

     public void openOrder(final Car car)
    {

        try {
            new AsyncTask<Void, Void,Boolean>() {
                @Override
                protected void onPostExecute(Boolean flag) {
                    super.onPostExecute(false);
                    if(flag) {
                      Toast.makeText(getBaseContext(), "Order Open", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                protected Boolean doInBackground(Void... params) {
                    return DBManagerFactory.getManager().openOrder(new Order(LogIn.getIdCustomer(),null,car.getCarNumber(),null,null,car.getKilometers(),0,false,0,0,0));
                }
            }.execute();
        } catch (Exception e) {

        }
        changeFragement(about);

    }

    public void closeOrder(final Order order)
    {

        try {
            new AsyncTask<Void, Void,Boolean>() {
                @Override
                protected void onPostExecute(Boolean flag) {
                    super.onPostExecute(false);
                    if(flag) {
                        Toast.makeText(getBaseContext(), "Order Close", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                protected Boolean doInBackground(Void... params) {
                    return DBManagerFactory.getManager().closeOrder(new Order(LogIn.getIdCustomer(),null,order.getNumOfCars(),null,null,order.getKilometerStart(),order.getKilometerFinish(),false,0,0,order.getOrderNum()));
                }
            }.execute();
        } catch (Exception e) {

        }
        changeFragement(about);

    }


}

