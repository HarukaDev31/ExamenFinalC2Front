package com.example.examefinalc2frontend.Models;

import java.time.LocalDate;

public class CareerProgress {
    private final LocalDate date;
    private final String position;
    private final double salary;

    public CareerProgress(LocalDate date, String position, double salary) {
        this.date = date;
        this.position = position;
        this.salary = salary;
    }

    // Getters
    public LocalDate getDate() { return date; }
    public String getPosition() { return position; }
    public double getSalary() { return salary; }
}