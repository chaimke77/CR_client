package com.example.user.cr_client.entities;

/**
 * Created by User on 12/04/2018.
 */

public class Car {
    private int branchNumber;
    private long modelNum;
    private long kilometers;
    private long carNumber;

    public Car() {
    }

    public Car(int branchNumber, long modelNum, long kilometers, long carNumber) {
        this.branchNumber = branchNumber;
        this.modelNum = modelNum;
        this.kilometers = kilometers;
        this.carNumber = carNumber;
    }

    public int getBranchNumber() {return branchNumber;}

    public void setBranchNumber(int branchNumber) {
        this.branchNumber = branchNumber;
    }

    public long getModel() {
        return modelNum;
    }

    public void setModel(long model) {
        this.modelNum = model;
    }

    public long getKilometers() {
        return kilometers;
    }

    public void setKilometers(long kilometers) {
        this.kilometers = kilometers;
    }

    public long getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(long carNumber) {
        this.carNumber = carNumber;
    }
}
