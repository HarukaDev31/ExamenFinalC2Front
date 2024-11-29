package com.example.examefinalc2frontend.Responses;

import com.example.examefinalc2frontend.Models.Employee;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;
@Data
@Setter
@Getter
@Builder
public class GetEmployeeResponse {
    // Getters y Setters
    private boolean success;
    private String error;
    private List<Employee> employees=new ArrayList<>();;

    // Constructor sin argumentos
    public GetEmployeeResponse() {}

    // Constructor con argumentos
    public GetEmployeeResponse(boolean success, String error, List<Employee> employees) {
        this.success = success;
        this.error = error;
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "GetEmployeeResponse{" +
                "success=" + success +
                ", error='" + error + '\'' +
                ", employees=" + employees +
                '}';
    }
}


