package com.example.examefinalc2frontend.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class PayrollDetails {
    private String typeKey;
    private double value;
    private Date createdAt;
    private Long id;
}
