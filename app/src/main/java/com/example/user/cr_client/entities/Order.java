package com.example.user.cr_client.entities;

import java.util.Date;

/**
 * Created by User on 12/04/2018.
 */

public class Order {
    private String customerNum;
    private StatusOrder status;
    private int numOfCars;
    private Date rentalStart;
    private Date rentalFinish;
    private long kilometerStart;
    private long kilometerFinish;
    private boolean gaz;
    private int liter;
    private int billing;
    private long orderNum;

    public Order() {
    }

    public Order(String customerNum, StatusOrder status, int numOfCars, Date rentalStart, Date rentalFinish, long kilometerStart, long kilometerFinish, boolean fuel, int liter, int billing, long orderNum) {
        this.customerNum = customerNum;
        this.status = status;
        this.numOfCars = numOfCars;
        this.rentalStart = rentalStart;
        this.rentalFinish = rentalFinish;
        this.kilometerStart = kilometerStart;
        this.kilometerFinish = kilometerFinish;
        this.gaz = fuel;
        this.liter = liter;
        this.billing = billing;
        this.orderNum = orderNum;
    }

    public String getCustomerNum() {return customerNum;}

    public void setCustomerNum(String costumerNum) {
        this.customerNum = costumerNum;
    }

    public StatusOrder getStatus() {
        return status;
    }

    public void setStatus(StatusOrder status) {
        this.status = status;
    }

    public int getNumOfCars() {
        return numOfCars;
    }

    public void setNumOfCars(int numOfCars) {
        this.numOfCars = numOfCars;
    }

    public Date getRentalStart() {
        return rentalStart;
    }

    public void setRentalStart(Date rentalStart) {
        this.rentalStart = rentalStart;
    }

    public Date getRentalFinish() {
        return rentalFinish;
    }

    public void setRentalFinish(Date rentalFinish) {
        this.rentalFinish = rentalFinish;
    }

    public long getKilometerStart() {
        return kilometerStart;
    }

    public void setKilometerStart(long kilometerStart) {
        this.kilometerStart = kilometerStart;
    }

    public long getKilometerFinish() {
        return kilometerFinish;
    }

    public void setKilometerFinish(long kilometerFinish) {
        this.kilometerFinish = kilometerFinish;
    }

    public boolean isGaz() {
        return gaz;
    }

    public void setGaz(boolean gaz) {
        this.gaz = gaz;
    }

    public int getLiter() {
        return liter;
    }

    public void setLiter(int liter) {
        this.liter = liter;
    }

    public int getBilling() {
        return billing;
    }

    public void setBilling(int billing) {
        this.billing = billing;
    }

    public long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(long orderNum) {
        this.orderNum = orderNum;
    }
}
