package com.example.user.cr_client.datasource;

import android.content.ContentValues;

import com.example.user.cr_client.backend.DB_manager;
import com.example.user.cr_client.controller.LogIn;
import com.example.user.cr_client.entities.Branch;
import com.example.user.cr_client.entities.Car;
import com.example.user.cr_client.entities.CarModel;
import com.example.user.cr_client.entities.Customer;
import com.example.user.cr_client.entities.Gearbox;
import com.example.user.cr_client.entities.Order;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by AAA on 26/04/2018.
 */



public class MySQL_DBManager implements DB_manager {

    private String WEB_URL ="http://crottenb.vlab.jct.ac.il/CR/";
    private List<Customer> customerList;
    private List<Branch> branchList;
    private List<Order> closeList = new ArrayList<Order>();
    private List<CarModel> modelList;
    private List<Car> carList;
    private List<Order> openOrderList=new ArrayList<Order>();

    public MySQL_DBManager(){
        customerList = getAllCustomers();
        branchList = getAllBrunches();
        carList = getAllCars();
        modelList = getAllModels();


    }

    @Override
    public boolean custumerExsits(String id, String name) {
        boolean retval = true;
        try
        {
            String str = PHPtools.GET(WEB_URL + "/custumerExsits.php?_id='"+id+"'&first_name='"+name+"'");
            JSONArray array = new JSONObject(str).getJSONArray("customer");
            if ( array.length()==0)
                retval = false;


        }catch (Exception e) {
            //Log.w( Constants.Log.APP_LOG, e.getMessage() );
        }
        return retval;
    }


    @Override
    public boolean addCustomer(Customer values) {
        for(Customer item :getAllCustomers())
        {
            if(item.getId()==values.getId())
                return false;
        }
        try {
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
    public List<CarModel> getAllModels() {

        if(modelList != null)
            return modelList;
        List<CarModel> result = new ArrayList<CarModel>();
        try
        {
            String str = PHPtools.GET(WEB_URL + "getModel.php");
            JSONArray array = new JSONObject(str).getJSONArray("model");
            for (int i = 0; i < array.length(); i++)
            {
                JSONObject jsonObject = array.getJSONObject(i);
                CarModel model = new CarModel();
                model.setCode(jsonObject.getInt("_id"));
                model.setCompany(jsonObject.getString("company"));
                model.setModel(jsonObject.getString("model"));
                model.setEngineCapacity(jsonObject.getInt("enginCapacity"));
                model.setGear(Gearbox.valueOf((jsonObject.getString("gear"))));
                model.setSeats(jsonObject.getInt("seats"));
                result.add(model);

            }
            return result;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateCarKM(Long newKM, Long carNum)
    {
        try {
            String url = WEB_URL + "updateCarKM.php" ;
            final ContentValues v = new ContentValues();
            v.put( "km", newKM);
            v.put( "_id", carNum);
            PHPtools.POST( url, v );
        } catch (Exception e) {
            //Log.w( Constants.Log.APP_LOG, e.getMessage() );
        }
    }

    @Override
    public List<Car> getAvailableCar() {
        List<Car> result = new ArrayList<Car>();
        try
        {
            String str = PHPtools.GET(WEB_URL + "getAvailableCar.php");
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
    public List<Car> getAvailableCarOfBranch(String name) {
        List<Car> result = new ArrayList<Car>();
        try
        {
            String str = PHPtools.GET(WEB_URL + "getAvailableCarOfBranch.php?branch='"+name+"'");
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
    public List<Car> getAvailableCarOnKm() {
        return null;
    }


    @Override
    public List<Branch> getBranchforModel() {
        // TODO: 16/05/2018
        return null;
    }


    @Override
    public List<Order> getAllOpenOrders() {

        List<Order> result = new ArrayList<Order>();
        try
        {
            String str = PHPtools.GET(WEB_URL + "getOpenOrder.php?id_customer='"+ LogIn.getIdCustomer()+"'");
            JSONArray array = new JSONObject(str).getJSONArray("orders");
            for (int i = 0; i < array.length(); i++)
            {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                JSONObject jsonObject = array.getJSONObject(i);
                Order order = new Order();
                order.setOrderNum(jsonObject.getInt("_id"));
                order.setCustomerNum(jsonObject.getString("id_customer"));
                order.setNumOfCars(jsonObject.getLong("numCar"));
                order.setKilometerStart(jsonObject.getInt("km_start"));
                order.setRentalStart(formatter.parse(jsonObject.getString("date_start")));
                result.add(order);
            }
            return result;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean openOrder(Order values) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        try {
            String url = WEB_URL + "openOrders.php" ;
            final ContentValues v = new ContentValues();
            v.put( "id_customer", values.getCustomerNum() );
            v.put( "numCar", values.getNumOfCars() );
            v.put( "km_start", values.getKilometerStart() );
            v.put( "date_start", currentDateandTime);
            PHPtools.POST( url, v );
        } catch (Exception e) {
            //Log.w( Constants.Log.APP_LOG, e.getMessage() );
        }

        openOrderList.add(values);
        return true;

    }

    @Override
    public Boolean closeOrder(Order values) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        try {
            String url = WEB_URL + "closeOrders.php" ;
            final ContentValues v = new ContentValues();
            v.put( "_id", values.getOrderNum() );
            v.put( "km_finish", values.getKilometerFinish() );
            v.put( "date_finish", currentDateandTime);
            PHPtools.POST( url, v );
        } catch (Exception e) {
            //Log.w( Constants.Log.APP_LOG, e.getMessage() );
        }
        closeList.add(values);
        return true;
    }


    @Override
    public int closedAtLastTenSeconds() {

        int temp = closeList.size();
        closeList.clear();
        return temp;

    }

}



