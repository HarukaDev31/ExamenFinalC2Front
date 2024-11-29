package com.example.examefinalc2frontend.Services;

import com.example.examefinalc2frontend.Requests.LoginRequest;
import com.example.examefinalc2frontend.Responses.LoginResponse;
import org.apache.poi.ss.formula.functions.T;

public class AuthService extends ApiService{


    public LoginResponse login(LoginRequest loginRequest) throws Exception {
        try{
            LoginResponse response=post("auth/authenticate", loginRequest, LoginResponse.class);
            System.out.println(response+"response");
            return response;
        }catch (Exception  e){
            return LoginResponse.builder().success(false).error(e.getMessage()).build();
        }
    }

    public T register(String email, String password, int role_id) {
        return null;
    }
}
