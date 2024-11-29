package com.example.examefinalc2frontend.Responses;

import com.example.examefinalc2frontend.Models.Payroll;
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
public class GetPaymentResponse {
    private boolean success;
    private String error;
    private List<Payroll> payrolls=new ArrayList<>();

    public GetPaymentResponse() {

    }

    public GetPaymentResponse(boolean success, String error, List<Payroll> payrolls) {
        this.success = success;
        this.error = error;
        this.payrolls = payrolls;
    }

    @Override
    public String toString() {
        return "GetPaymentResponse{" +
                "success=" + success +
                ", error='" + error + '\'' +
                ", payrolls=" + payrolls +
                '}';
    }
}
