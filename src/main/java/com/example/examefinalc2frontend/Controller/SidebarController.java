package com.example.examefinalc2frontend.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SidebarController {

    private MainController mainController;
    @FXML
    private Button employeeListButton;

    @FXML
    private Button salaryHistoryButton;


    @FXML
    private Button monthlyPaymentsButton;
    @FXML
    private Button salaryVariablesButton;
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        System.out.println("MainController establecido en SidebarController");
    }
    @FXML
    public void initialize() {


    }

    @FXML
    private void handleEmployeeList() {
        if (mainController == null) {
            System.err.println("Error: mainController es null en SidebarController");
            return;
        }
        try {
            mainController.loadView("/views/EmployeeListView.fxml");
            markSelectedOption(employeeListButton);

        } catch (Exception e) {
            System.err.println("Error al cargar la vista de empleados: " + e.getMessage());
            e.printStackTrace();
        }
    }





    public void handleSalaryHistory(ActionEvent actionEvent) {
        mainController.loadView("/views/HistorySalaryListView.fxml");
        markSelectedOption(salaryHistoryButton);
    }



    public void handleMonthlyPayments() {
        mainController.loadView("/views/MonthlyPaymentsView.fxml");
        markSelectedOption(monthlyPaymentsButton);
    }

    public void handleSalaryVariables() {
        mainController.loadView("/views/SalaryVariablesView.fxml");
        markSelectedOption(salaryVariablesButton);

    }




    private void markSelectedOption(Button selectedButton) {
        // Primero, quita la clase 'selected-option' de todos los botones
        employeeListButton.getStyleClass().remove("selected-option");
        salaryHistoryButton.getStyleClass().remove("selected-option");
        monthlyPaymentsButton.getStyleClass().remove("selected-option");
        salaryVariablesButton.getStyleClass().remove("selected-option");
        selectedButton.getStyleClass().add("selected-option");
    }
}
