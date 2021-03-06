package com.example.user.cr_client.backend;


import com.example.user.cr_client.entities.Branch;
import com.example.user.cr_client.entities.Car;
import com.example.user.cr_client.entities.CarModel;
import com.example.user.cr_client.entities.Customer;
import com.example.user.cr_client.entities.Order;
import com.example.user.cr_client.entities.User;

import java.util.List;

/**
 * Created by User on 12/04/2018.
 */

public interface DB_manager {


    boolean custumerExsits(String id, String name);
    boolean addCustomer(Customer values);
    List<Customer> getAllCustomers();
    List<Branch> getAllBrunches();
    List<Car> getAllCars();
    List<Car> getAvailableCar();
    List<Car> getAvailableCarOfBranch(String branch);
    List<Car> getAvailableCarOnKm();
   // List<String> getAllModel();
    List<Branch> getBranchforModel();
    List<CarModel> getAllModels();
    List<Order> getAllOpenOrders();
    Boolean openOrder(Order order);
    Boolean closeOrder(Order order);
    int closedAtLastTenSeconds();
    void updateCarKM(Long newKM, Long carNum);
    //List<Order> getCloseList();
}