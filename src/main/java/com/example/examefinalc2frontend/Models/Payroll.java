package com.example.examefinalc2frontend.Models;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;
@Getter
@Setter
public class Payroll {
    private Long Id;
    private Employee employee;
    private int monthCreated;
    private int yearCreated;
    private double total;
    private String created_at;
    private String updated_at;
    private List<PayrollDetails> payrollDetails=new ArrayList<>();

    @Override
    public String toString() {
        return "Payroll{" +
                "employee=" + employee +
                ", monthCreated=" + monthCreated +
                ", yearCreated=" + yearCreated +
                ", total=" + total +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", payrollDetails=" + payrollDetails +
                '}';
    }
}
