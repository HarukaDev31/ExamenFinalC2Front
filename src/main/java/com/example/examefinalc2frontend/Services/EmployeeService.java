package com.example.examefinalc2frontend.Services;

import com.example.examefinalc2frontend.Models.Employee;
import com.example.examefinalc2frontend.Responses.GetEmployeeResponse;
import com.example.examefinalc2frontend.Utils.SessionManager;

import java.util.List;

//ii
public class EmployeeService extends ApiService{
    public EmployeeService() {
        String token= SessionManager.getInstance().getToken();
        setToken(token);
    }
    public GetEmployeeResponse getEmployees( String search) {
        try {
            System.out.println("call");
            GetEmployeeResponse g = get("employees?search=" + search, GetEmployeeResponse.class);

            List<Employee> employees = g.getEmployees();
            for (Employee e : employees) {
                System.out.println(e);
            }
            return g;
        } catch (Exception e) {
            System.out.printf("Error: %s\n", e.getMessage());
            return GetEmployeeResponse.builder().success(false).error(e.getMessage()).build();
        }
    }

}
