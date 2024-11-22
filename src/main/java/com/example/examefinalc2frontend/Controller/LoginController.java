package com.example.examefinalc2frontend.Controller;

import com.example.examefinalc2frontend.Requests.LoginRequest;
import com.example.examefinalc2frontend.Responses.LoginResponse;
import com.example.examefinalc2frontend.Services.AuthService;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class LoginController {
    private AuthService authService = new AuthService();

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Label messageLabel;

    public void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty fields");
            alert.setContentText("Please fill in all fields");
            alert.showAndWait();


            return;
        }
        LoginRequest loginRequest = new LoginRequest(username, password);
        try {
            LoginResponse response = authService.login(loginRequest);
            if (response.getToken() != null) {
                messageLabel.setText("Login successful");
            } else {
                messageLabel.setText(response.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void initialize() {
        loginButton.setOnAction(event -> login());
    }
}
