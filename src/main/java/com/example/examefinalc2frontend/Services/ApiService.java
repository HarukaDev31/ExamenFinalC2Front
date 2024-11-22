package com.example.examefinalc2frontend.Services;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService {
    private static final String API_URL = "http://localhost:8080/api/";
    private final ObjectMapper objectMapper;
    public final HttpClient client;

    public ApiService() {
        this.objectMapper = new ObjectMapper();
        this.client = HttpClient.newHttpClient();
    }

    public <T> T get(String url, Class<T> responseType) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + url))
                .header("Accept", "application/json")
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
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );





            return objectMapper.readValue(response.body(), responseType);
        } catch (Exception e) {
            System.err.println("Error en POST request: " + e.getMessage());
            throw e;

        }
    }
}
