package com.example.examefinalc2frontend;

import com.example.examefinalc2frontend.Controller.MainController;
import com.example.examefinalc2frontend.Controller.SidebarController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PayrollApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        try {
            // Cargar primero el sidebar
            FXMLLoader sidebarLoader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
            Parent sidebarRoot = sidebarLoader.load();
            SidebarController sidebarController = sidebarLoader.getController();

            // Cargar la vista principal
            FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("main.fxml"));
            BorderPane root = mainLoader.load();
            MainController mainController = mainLoader.getController();

            // Establecer el sidebar en el BorderPane
            root.setLeft(sidebarRoot);

            // Conectar los controladores
            sidebarController.setMainController(mainController);

            Scene scene = new Scene(root, 1200, 800, Color.web("#2d2d2d"));
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Gestión de Planilla");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al cargar la aplicación: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}