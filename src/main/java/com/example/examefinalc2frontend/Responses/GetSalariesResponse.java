package com.example.examefinalc2frontend.Responses;

import com.example.examefinalc2frontend.Models.Salary;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Setter
@Getter
@Builder
public class GetSalariesResponse {

    private boolean success;
    private String error;
    private List<Salary> salaries=new ArrayList<>();
    public GetSalariesResponse() {}

    public GetSalariesResponse(boolean success, String error, List<Salary> salaries) {
        this.success = success;
        this.error = error;
        this.salaries = salaries;
    }

    @Override
    public String toString() {
        return "GetSalariesResponse{" +
                "success=" + success +
                ", error='" + error + '\'' +
                ", salaries=" + salaries +
                '}';
    }
}
