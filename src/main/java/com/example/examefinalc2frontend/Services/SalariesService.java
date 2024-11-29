package com.example.examefinalc2frontend.Services;

import com.example.examefinalc2frontend.Models.Salary;
import com.example.examefinalc2frontend.Responses.GetSalariesResponse;
import com.example.examefinalc2frontend.Utils.SessionManager;

import java.util.List;

public class SalariesService extends ApiService {
    public SalariesService(){
        String token = SessionManager.getInstance().getToken();
        setToken(token);
    }

    public GetSalariesResponse getSalaries() {
        try {
            System.out.println("call");
            GetSalariesResponse g = get("salaries",GetSalariesResponse.class);
            System.out.println(g.toString());

            List<Salary> salaries = g.getSalaries();
            for (Salary s : salaries) {
                System.out.println(s.toString());
            }
            return g;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

