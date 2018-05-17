package com.example.user.cr_client.backend;


import com.example.user.cr_client.entities.Branch;
import com.example.user.cr_client.entities.Car;
import com.example.user.cr_client.entities.Customer;
import com.example.user.cr_client.entities.Order;
import com.example.user.cr_client.entities.User;

import java.util.List;

/**
 * Created by User on 12/04/2018.
 */

public interface DB_manager {

    Customer ReturnCustumerById(String values);
    boolean custumerExsits(Customer values);
    boolean addCustomer(Customer values);
    List<Customer> getAllCustomers();
    List<Branch> getAllBrunches();
    List<Car> getAllCars();
    boolean updateCarKM(Order order);
    List<Car> getAvailableCar();
    List<Car> getAvailableCarOfBranch();
    List<Car> getAvailableCarOnKm();
    List<String> getAllModel();
    List<Branch> getBranchforModel();
    List<Order> getAllOpenOrders();
    Boolean openOrder(Order order);
    Boolean closeOrder(Order order);
    Boolean closedAtLastTenSeconds();
}
