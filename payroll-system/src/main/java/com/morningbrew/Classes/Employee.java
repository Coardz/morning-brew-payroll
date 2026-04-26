package com.morningbrew.Classes;

public class Employee extends Person{

    private String department;
    private String position;
    private int rate;

    public Employee(String department, String position, int rate, String id, String firstName, String lastName, String address) {
        super(id, firstName, lastName, address);
        this.department = department;
        this.position = position;
        this.rate = rate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    
}