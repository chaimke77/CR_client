package com.example.user.cr_client.entities;

/**
 * Created by User on 12/04/2018.
 */


public class CarModel {
    private long code;
    private String company;
    private String model;
    private long engineCapacity;
    private Gearbox gear;
    private int seats;

    public CarModel() {
    }

    public CarModel(long code, String company, String model, long engineCapacity, Gearbox gear, int seats) {
        this.code = code;
        this.company = company;
        this.model = model;
        this.engineCapacity = engineCapacity;
        this.gear = gear;
        this.seats = seats;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(long engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public Gearbox getGear() {
        return gear;
    }

    public void setGear(Gearbox gear) {
        this.gear = gear;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}
