package com.example.examefinalc2frontend.Services;

import com.example.examefinalc2frontend.Models.EmployeePayment;
import com.example.examefinalc2frontend.Responses.GetPaymentResponse;
import com.example.examefinalc2frontend.Utils.SessionManager;
import javafx.scene.control.Button;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PayrollService extends  ApiService {
    public PayrollService() {
        String token = SessionManager.getInstance().getToken();
        setToken(token);
    }

    public GetPaymentResponse getEmployeePayments(int month, int year) {
        try {
            String url = "payroll?month=" + month + "&year=" + year;
            GetPaymentResponse g = get(url, GetPaymentResponse.class);
            System.out.printf("Response: %s\n", g.toString());
            System.out.printf("Response: %s\n", g.getPayrolls().toString());

            return g;

        } catch (Exception e) {
            System.out.printf("Error: %s\n", e.getMessage());
            return null;
        }
    }
}
