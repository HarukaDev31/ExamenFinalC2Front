package com.example.examefinalc2frontend.Models;

import java.time.LocalDate;

public class PaySlip {
    private final LocalDate date;
    private final double grossSalary;
    private final double netSalary;
    private final String downloadUrl;

    public PaySlip(LocalDate date, double grossSalary, double netSalary, String downloadUrl) {
        this.date = date;
        this.grossSalary = grossSalary;
        this.netSalary = netSalary;
        this.downloadUrl = downloadUrl;
    }

    // Getters
    public LocalDate getDate() { return date; }
    public double getGrossSalary() { return grossSalary; }
    public double getNetSalary() { return netSalary; }
    public String getDownloadUrl() { return downloadUrl; }
}