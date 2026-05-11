package com.morningbrew.Classes;

import javafx.beans.property.*;

public class Employee {
    private final IntegerProperty id;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty department;
    private final StringProperty position;
    private final DoubleProperty rate;
    private final IntegerProperty workingDays;

    public Employee(int id, String firstName, String lastName, String department, String position, double rate, int workingDays) {
        this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.department = new SimpleStringProperty(department);
        this.position = new SimpleStringProperty(position);
        this.rate = new SimpleDoubleProperty(rate);
        this.workingDays = new SimpleIntegerProperty(workingDays);
    }

    public int getId() { 
        return id.get(); 
    }

    public void setId(int value) { 
        id.set(value); 
    }
    public IntegerProperty idProperty() { 
        return id; 
    }

    public String getFirstName() { 
        return firstName.get(); 
    }
    
    public void setFirstName(String value) { 
        firstName.set(value); 
    }
    
    public StringProperty firstNameProperty() { 
        return firstName; 
    }

    public String getLastName() { 
        return lastName.get(); 
    }

    public void setLastName(String value) { 
        lastName.set(value); 
    }
    
    public StringProperty lastNameProperty() { return lastName; }

    // --- Department ---
    public String getDepartment() { return department.get(); }
    public void setDepartment(String value) { department.set(value); }
    public StringProperty departmentProperty() { return department; }

    // --- Position ---
    public String getPosition() { return position.get(); }
    public void setPosition(String value) { position.set(value); }
    public StringProperty positionProperty() { return position; }

    // --- Rate ---
    public double getRate() { return rate.get(); }
    public void setRate(double value) { rate.set(value); }
    public DoubleProperty rateProperty() { return rate; }

    // --- Working Days ---
    public int getWorkingDays() { return workingDays.get(); }
    public void setWorkingDays(int value) { workingDays.set(value); }
    public IntegerProperty workingDaysProperty() { return workingDays; }
}