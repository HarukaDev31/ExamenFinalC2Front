package com.example.examefinalc2frontend.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class MainController {

    @FXML
    private AnchorPane contentArea;

    public void loadView(String fxmlFile) {
        try {
            // Modificar la ruta para que sea absoluta desde la raíz del classpath
            String fullPath = "/com/example/examefinalc2frontend" + fxmlFile;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fullPath));

            if (loader.getLocation() == null) {
                System.err.println("No se pudo encontrar el archivo: " + fullPath);
                return;
            }

            AnchorPane view = loader.load();
            if (contentArea != null) {
                contentArea.getChildren().setAll(view);
            } else {
                System.err.println("contentArea es null en MainController");
            }
        } catch (IOException e) {
            System.err.println("Error al cargar la vista: " + fxmlFile);
            e.printStackTrace();
        }
    }
}
