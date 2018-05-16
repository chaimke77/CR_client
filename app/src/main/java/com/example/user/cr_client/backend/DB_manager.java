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
    boolean carExists(Long values);
    boolean custumerExsits(Customer values);
    boolean branchExists(int values);
    Boolean addCustomer(Customer values);
    long addBranch(Branch values);
    long addCar(Car values);
    List<Customer> getAllCustomers();
    List<Branch> getAllBrunches();
    List<Car> getAllCars();
    boolean updateCarKl(Order order);
    List<Car> getAvailableCar();
    List<Car> getAvailableCarOfBranch();
    List<String> getModels();
    List<Order> getAllOpenOrders();
    Boolean openOrder(Order order);
    Boolean closeOrder(Order order);
    Boolean closedAtLastSeconds();
}
