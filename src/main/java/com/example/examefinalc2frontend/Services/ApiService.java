package com.example.examefinalc2frontend.Services;

import com.example.examefinalc2frontend.Exception.ApiException;
import com.example.examefinalc2frontend.Exception.NotFoundException;
import com.example.examefinalc2frontend.Exception.UnauthorizedException;
import com.example.examefinalc2frontend.Exception.BadRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ApiService {
    private static final String API_URL = "http://localhost:8080/api/";
    private final ObjectMapper objectMapper;
    public final HttpClient client;
    @Setter
    private  String token;
    public ApiService() {
        this.objectMapper = new ObjectMapper();
        this.client = HttpClient.newHttpClient();
        this.token = "";
    }

    public <T> T get(String url, Class<T> responseType) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + url))
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        return objectMapper.readValue(response.body(), responseType);
    }

    public <T> T post(String url, Object body, Class<T> responseType) throws Exception {
        try {
            String jsonBody = objectMapper.writeValueAsString(body);


            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + url))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );
            if(response.statusCode()==400){
                throw new BadRequestException("Bad Request");
            }else if(response.statusCode()==404){
                throw new NotFoundException("Not Found");
            }else if(response.statusCode()==401){
                throw new UnauthorizedException("Unauthorized");
            }else if(response.statusCode()==403){
                throw new BadRequestException("Forbidden");

            }
            try{
                return objectMapper.readValue(response.body(), responseType);
            }catch (JsonProcessingException e){
                throw new ApiException("Api Error");
            }
        } catch (Exception e) {

            throw e;

        }
    }


}
