package com.example.examefinalc2frontend.Controller;

import com.example.examefinalc2frontend.Requests.LoginRequest;
import com.example.examefinalc2frontend.Responses.LoginResponse;
import com.example.examefinalc2frontend.Services.AuthService;
import com.example.examefinalc2frontend.Utils.SessionManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;


public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private Button loginButton;

    private AuthService authService;

    public LoginController() {
    }

    @FXML
    public void login() {
        clearMessage();
        if (!validateFields()) {
            return;
        }

        try {
            setLoadingState(true);

            LoginRequest loginRequest = new LoginRequest(
                    usernameField.getText().trim(),
                    passwordField.getText()
            );

            LoginResponse response = authService.login(loginRequest);
            handleLoginResponse(response);

        } catch (Exception e) {
            handleError("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            setLoadingState(false);
        }
    }

    private boolean validateFields() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showError("Validation Error", "Empty fields",
                    "Please fill in all fields");
            return false;
        }

        if (username.length() < 3) {
            showError("Validation Error", "Invalid username",
                    "Username must be at least 3 characters long");
            return false;
        }

        if (password.length() < 6) {
            showError("Validation Error", "Invalid password",
                    "Password must be at least 6 characters long");
            return false;
        }

        return true;
    }

    private void handleLoginResponse(LoginResponse response) {
        if (response.getError() != null) {
            showMessage(response.getError(), "error");
            return;
        }

        if (response.getToken() != null) {
            handleSuccessfulLogin(response);
        } else {
            showMessage(response.getMessage() != null ?
                    response.getMessage() : "Unknown error occurred", "error");
        }
    }

    private void handleError(String errorMessage) {
        Platform.runLater(() -> {
            showMessage(errorMessage, "error");
            // También podrías mostrar una alerta más detallada si lo prefieres
            showError("Error", "Login Error", errorMessage);

            // Limpiar el campo de contraseña por seguridad
            passwordField.clear();

            // Devolver el foco al campo de usuario si está vacío, si no al de contraseña
            if (usernameField.getText().trim().isEmpty()) {
                usernameField.requestFocus();
            } else {
                passwordField.requestFocus();
            }
        });
    }

    private void handleSuccessfulLogin(LoginResponse response) {
        showMessage("Login successful!", "success");

        saveUserSession(response);

        Platform.runLater(() -> {
            try {
                navigateToMainScreen();
            } catch (Exception e) {
                showError("Navigation Error", "Could not load main screen",
                        e.getMessage());
            }
        });
    }

    private void saveUserSession(LoginResponse response) {
        SessionManager.getInstance().setToken(response.getToken());
        SessionManager.getInstance().setUsername(response.getUsername());
    }

    private void navigateToMainScreen() throws IOException {

            String fullPath = "/com/example/examefinalc2frontend";

            FXMLLoader sidebarLoader = new FXMLLoader(getClass().getResource(fullPath + "/sidebar.fxml"));
            Parent sidebarRoot = sidebarLoader.load();
            SidebarController sidebarController = sidebarLoader.getController();

            // Cargar la vista principal
            FXMLLoader mainLoader = new FXMLLoader(getClass().getResource(fullPath + "/main.fxml"));
            BorderPane root = mainLoader.load();
            MainController mainController = mainLoader.getController();

            // Establecer el sidebar en el BorderPane
            root.setLeft(sidebarRoot);

            // Conectar los controladores
            sidebarController.setMainController(mainController);

            Scene scene = new Scene(root, 1200, 800, Color.web("#2d2d2d"));
            scene.getStylesheets().add(getClass().getResource(fullPath + "/styles.css").toExternalForm());
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(scene);
    }

    private void showError(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showMessage(String message, String type) {
        messageLabel.setText(message);
        messageLabel.getStyleClass().clear();
        messageLabel.getStyleClass().add("message-" + type);
    }

    private void clearMessage() {
        messageLabel.setText("");
        messageLabel.getStyleClass().clear();
    }

    private void setLoadingState(boolean loading) {
        loginButton.setDisable(loading);
        usernameField.setDisable(loading);
        passwordField.setDisable(loading);

        if (loading) {
            loginButton.setText("Logging in...");
        } else {
            loginButton.setText("Login");
        }
    }

    public void initialize() {
        this.authService = new AuthService(); // O obtenerlo de donde corresponda

        SessionManager.getInstance().loadSession();
        if (SessionManager.getInstance().hasActiveSession()) {
            try {
                navigateToMainScreen();
            } catch (IOException e) {
                showError("Navigation Error", "Could not load main screen",
                        e.getMessage());
            }
        }else{
            //add event listener to login button
            loginButton.setOnAction(event -> login());

        }
    }
}