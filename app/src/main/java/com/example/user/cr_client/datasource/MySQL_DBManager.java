package com.example.user.cr_client.datasource;

import android.content.ContentValues;


import com.example.user.cr_client.backend.DB_manager;
import com.example.user.cr_client.entities.Branch;
import com.example.user.cr_client.entities.Car;
import com.example.user.cr_client.entities.Customer;
import com.example.user.cr_client.entities.Order;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AAA on 26/04/2018.
 */



public class MySQL_DBManager implements DB_manager {

    private String WEB_URL ="http://crottenb.vlab.jct.ac.il/CR/";
    private List<Customer> customerList;
    private List<Branch> branchList;

    private List<Car> carList;

    public MySQL_DBManager(){
        customerList = getAllCustomers();
        branchList = getAllBrunches();
        carList = getAllCars();
    }


    @Override
    public Customer ReturnCustumerById(String values) {
        for (Customer item:customerList) {
            if(item.getId().equals(values) )
                return item;
        }
        return null;
    }



    @Override
    public boolean carExists(Long values) {

        for (Car item:carList) {
            if(item.getCarNumber()==values )
                return true;
        }
        return false;

    }



    @Override
    public boolean custumerExsits(Customer values) {

        for (Customer item:customerList) {
            if(item.getId().equals(values.getId()) )
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean branchExists(int values) {
        for (Branch item:branchList) {
            if(item.getBranchNumber()==values )
                return true;
        }
        return false;
    }

    @Override
    public Boolean addCustomer(Customer values) {

        try {

            if(custumerExsits(values)==true)
                return false;
            String url = WEB_URL + "addCustomer.php" ;

            final ContentValues v = new ContentValues();
            v.put( "_id", values.getId() );
            v.put( "first_name", values.getFirstName() );
            v.put( "last_name", values.getLastName() );
            v.put( "phoneNumber", values.getPhoneNumber() );
            v.put( "email", values.getEmail() );
            v.put( "creditCard", values.getCreditCard() );

            PHPtools.POST( url, v );

        } catch (Exception e) {
            //Log.w( Constants.Log.APP_LOG, e.getMessage() );
        }
        customerList.add(values);
        return true;
    }

    @Override
    public long addBranch(Branch values) {
        try {

            if(branchExists(values.getBranchNumber())==true)
                return -1;
            String url = WEB_URL + "addBranch.php" ;

            final ContentValues v = new ContentValues();
            v.put( "_id", values.getBranchNumber() );
            v.put( "address", values.getAdress() );
            v.put( "space", values.getNumberOfParkingSpaces() );
            PHPtools.POST( url, v );

        } catch (Exception e) {
            //Log.w( Constants.Log.APP_LOG, e.getMessage() );
        }
        branchList.add(values);
        return values.getBranchNumber();

    }



    @Override
    public long addCar(Car values) {

        try {

            if(carExists(values.getCarNumber())==true)
                return -1;
            String url = WEB_URL + "addCar.php" ;

            final ContentValues v = new ContentValues();
            v.put( "_id", values.getCarNumber() );
            v.put( "branch", values.getBranchNumber() );
            v.put( "model", values.getModel() );
            v.put( "km", values.getKilometers() );
            PHPtools.POST( url, v );

        } catch (Exception e) {
            //Log.w( Constants.Log.APP_LOG, e.getMessage() );
        }
        carList.add(values);
        return values.getCarNumber();

    }



    @Override
    public List<Customer> getAllCustomers() {
        if(customerList != null)
            return customerList;
        List<Customer> result = new ArrayList<Customer>();
        try
        {
            String str = PHPtools.GET(WEB_URL + "/getCustomer.php");
            JSONArray array = new JSONObject(str).getJSONArray("customer");
            for (int i = 0; i < array.length(); i++)
            {
                JSONObject jsonObject = array.getJSONObject(i);
                Customer customer = new Customer();
                customer.setId(jsonObject.getString("_id"));
                customer.setFirstName(jsonObject.getString("first_name"));
                customer.setLastName(jsonObject.getString("last_name"));
                customer.setCreditCard(jsonObject.getInt("creditCard"));
                customer.setEmail(jsonObject.getString("email"));
                customer.setPhoneNumber(jsonObject.getString("phoneNumber"));

                result.add(customer);
            }
            return result;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Branch> getAllBrunches() {
        if(branchList != null)
            return branchList;
            List<Branch> result = new ArrayList<Branch>();
            try
            {
                String str = PHPtools.GET(WEB_URL + "getBranches.php");
                JSONArray array = new JSONObject(str).getJSONArray("branches");
                for (int i = 0; i < array.length(); i++)
                {
                    JSONObject jsonObject = array.getJSONObject(i);
                    Branch branch = new Branch();
                    branch.setBranchNumber(jsonObject.getInt("_id"));
                    branch.setAdress(jsonObject.getString("address"));
                    branch.setNumberOfParkingSpaces(jsonObject.getInt("space"));
                    result.add(branch);
                }
                return result;
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }



    @Override
    public List<Car> getAllCars() {

        if(carList != null)
            return carList;
        List<Car> result = new ArrayList<Car>();
        try
        {
            String str = PHPtools.GET(WEB_URL + "getCar.php");
            JSONArray array = new JSONObject(str).getJSONArray("car");
            for (int i = 0; i < array.length(); i++)
            {
                JSONObject jsonObject = array.getJSONObject(i);
                Car car = new Car();
                car.setCarNumber(jsonObject.getInt("_id"));
                car.setBranchNumber(jsonObject.getInt("branch"));
                car.setModel(jsonObject.getInt("model"));
                car.setKilometers(jsonObject.getInt("km"));
                result.add(car);
            }
            return result;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateCarKl(Order order)
    {
        // TODO: 16/05/2018  
        return false;
    }

    @Override
    public List<Car> getAvailableCar() {
       //// TODO: 16/05/2018  
        return null;
    }

    @Override
    public List<Car> getAvailableCarOfBranch() {
      //// TODO: 16/05/2018  
        return null;
    }

    @Override
    public List<String> getModels() {
        // TODO: 16/05/2018  
        return null;
    }

    @Override
    public List<Order> getAllOpenOrders() {
        // TODO: 16/05/2018  
        return null;
    }

    @Override
    public Boolean openOrder(Order order) {
        // TODO: 16/05/2018  
        return null;
        
    }

    @Override
    public Boolean closeOrder(Order order) {
        // TODO: 16/05/2018  
        return null;
    }

    @Override
    public Boolean closedAtLastSeconds() {
        // TODO: 16/05/2018  
        return null;
    }
}
