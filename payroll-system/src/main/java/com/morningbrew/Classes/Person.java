package com.morningbrew.Classes;

public class Person {
    private String id;
    private String firstname;
    private String lastName;
    private String address;


    public Person(String id, String firstName, String lastName, String address) {
        this.id = id;
        this.firstname = firstName;
        this.lastName = lastName; 
        this.address = address; 
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}