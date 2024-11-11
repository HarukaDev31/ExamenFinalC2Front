package com.example.examefinalc2frontend.Models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Employee {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty email;
    private final SimpleStringProperty phone;
    private final SimpleStringProperty address;
    private final SimpleStringProperty birthDate;
    private final SimpleStringProperty hireDate;
    private final SimpleDoubleProperty salary;
    private final SimpleStringProperty position;

    public Employee(int id, String name, String lastName, String email, String phone, String address,
                    String birthDate, String hireDate, double salary, String position) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.address = new SimpleStringProperty(address);
        this.birthDate = new SimpleStringProperty(birthDate);
        this.hireDate = new SimpleStringProperty(hireDate);
        this.salary = new SimpleDoubleProperty(salary);
        this.position = new SimpleStringProperty(position);
    }

    // Getters para las propiedades, necesarios para que funcionen las columnas de la tabla
    public SimpleIntegerProperty idProperty() { return id; }
    public SimpleStringProperty nameProperty() { return name; }
    public SimpleStringProperty lastNameProperty() { return lastName; }
    public SimpleStringProperty emailProperty() { return email; }
    public SimpleStringProperty phoneProperty() { return phone; }
    public SimpleStringProperty addressProperty() { return address; }
    public SimpleStringProperty birthDateProperty() { return birthDate; }
    public SimpleStringProperty hireDateProperty() { return hireDate; }
    public SimpleDoubleProperty salaryProperty() { return salary; }
    public SimpleStringProperty positionProperty() { return position; }
}
