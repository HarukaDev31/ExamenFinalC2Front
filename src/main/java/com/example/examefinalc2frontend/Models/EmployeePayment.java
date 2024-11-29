package com.example.examefinalc2frontend.Models;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class EmployeePayment {
    private String employeeName;

    private String documentNumber;
    private String email;
    private String  area;
    private String month;
    private int year;
    private double total;
    private Button downloadButton;
    private Button sendEmailButton;
    private List<PayrollDetails> payrollDetails=new ArrayList<>();
    public EmployeePayment(String employeeName, String documentNumber, String email, String area, String month, int year, double total, Button downloadButton, Button sendEmailButton,
                           List<PayrollDetails> payrollDetails) {
        this.employeeName = employeeName;
        this.documentNumber = documentNumber;
        this.email = email;
        this.area = area;
        this.month = month;
        this.year = year;
        this.total = total;
        this.downloadButton = downloadButton;
        this.sendEmailButton = sendEmailButton;
        this.payrollDetails = payrollDetails;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getArea() {
        return area;
    }

    public String getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public double getTotal() {
        return total;
    }

    public Button getDownloadButton() {
        return downloadButton;
    }

    public Button getSendEmailButton() {
        return sendEmailButton;
    }

    public List<PayrollDetails> getPayrollDetails() {
        return payrollDetails;
    }

    public void setPayrollDetails(List<PayrollDetails> payrollDetails) {
        this.payrollDetails = payrollDetails;
    }
}