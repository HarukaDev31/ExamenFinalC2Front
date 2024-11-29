package com.example.examefinalc2frontend.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class Salary {

    private Long id;
    private Employee employee;
    private double amount;
    private Position position;

    private Date createdAt;

    private Date updatedAt;
}
