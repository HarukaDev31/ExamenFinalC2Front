package com.example.examefinalc2frontend.Services;

import com.example.examefinalc2frontend.Requests.LoginRequest;
import com.example.examefinalc2frontend.Responses.LoginResponse;
import org.apache.poi.ss.formula.functions.T;

public class AuthService extends ApiService{


    public LoginResponse login(LoginRequest loginRequest) throws Exception {
        try{
            LoginResponse response=post("auth/authenticate", loginRequest, LoginResponse.class);
            System.out.println(response);
            return response;
        }catch (HttpClientErrorException  e){
            System.out.printf("Error en login: %s", e.getMessage());
            throw e;
        }
    }

    public T register(String email, String password, int role_id) {
        return null;
    }
}
