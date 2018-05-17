package com.example.user.cr_client.datasource;

import android.content.ContentValues;


import com.example.user.cr_client.backend.DBManagerFactory;
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
    public Car ReturnCarById(Long values) {
        for (Car item:carList) {
            if(item.getCarNumber()==values )
                return item;
        }
        return null;
    }
    @Override
    public Branch ReturnBranchByName(String values) {
        for (Branch item:branchList) {
            if(item.getAdress().equals(values) )
                return item;
        }
        return null;
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
    public boolean addCustomer(Customer values) {
        return false;
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
    public void updateCarKM(Order order)
    {
        ReturnCarById(order.getNumOfCars()).setKilometers(order.getKilometerFinish());
    }

    @Override
    public List<Car> getAvailableCar() {
        List<Car> temp = carList;
        for(Order item :getAllOpenOrders()) {
            temp.remove(ReturnCarById(item.getNumOfCars()));
        }
        return temp;
    }

    @Override
    public List<Car> getAvailableCarOfBranch(String name) {
        Branch branch =ReturnBranchByName(name);
        List<Car> temp = carList;
        for(Car item : getAvailableCar()){
            if(branch.getBranchNumber()==item.getBranchNumber())
                temp.remove(item);
        }
        return null;
    }

    @Override
    public List<Car> getAvailableCarOnKm() {
        // TODO: 16/05/2018
        return null;
    }

    @Override
    public List<String> getAllModel() {
        // TODO: 16/05/2018
        return null;
    }

    @Override
    public List<Branch> getBranchforModel() {
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
    public Boolean closedAtLastTenSeconds() {
        // TODO: 16/05/2018  

        return null;
    }
}
