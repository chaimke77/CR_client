package com.example.user.cr_client.entities;

/**
 * Created by User on 12/04/2018.
 */
//import org.codehaus.jackson.annotate.JsonIgnoreProperties;
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Branch {
    private String adress;
    private int NumberOfParkingSpaces;
    private int BranchNumber;

    public Branch() {
    }

    public Branch(String adress, int numberOfParkingSpaces, int branchNumber) {
        this.adress = adress;
        NumberOfParkingSpaces = numberOfParkingSpaces;
        BranchNumber = branchNumber;
    }


    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getNumberOfParkingSpaces() {
        return NumberOfParkingSpaces;
    }

    public void setNumberOfParkingSpaces(int numberOfParkingSpaces) {
        NumberOfParkingSpaces = numberOfParkingSpaces;
    }

    public int getBranchNumber() {
        return BranchNumber;
    }

    public void setBranchNumber(int branchNumber) {
        BranchNumber = branchNumber;
    }
}
