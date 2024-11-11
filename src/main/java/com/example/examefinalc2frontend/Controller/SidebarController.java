package com.example.examefinalc2frontend.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SidebarController {

    private MainController mainController;
    @FXML
    private Button employeeListButton;
    @FXML
    private Button registerEmployeeButton;
    @FXML
    private Button salaryHistoryButton;
    @FXML
    private Button positionHistoryButton;
    @FXML
    private Button workHoursButton;
    @FXML
    private Button monthlyPaymentsButton;
    @FXML
    private Button salaryVariablesButton;
    @FXML
    private Button chartsStatisticsButton;

    @FXML
    private Button personalDataButton;
    @FXML
    private Button attendanceButton;
    @FXML
    private Button employeeHistoryButton;

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


    @FXML
    private void handleRegisterEmployee() {
        mainController.loadView("/views/RegisterEmployeeView.fxml");
        markSelectedOption(registerEmployeeButton);
    }

    @FXML
    public void handlePositionHistory() {
        mainController.loadView("/views/HistoryPositionListView.fxml");
        markSelectedOption(positionHistoryButton);
    }

    public void handleSalaryHistory(ActionEvent actionEvent) {
        mainController.loadView("/views/HistorySalaryListView.fxml");
        markSelectedOption(salaryHistoryButton);
    }

    public void handleWorkHours() {
        mainController.loadView("/views/WorkHoursView.fxml");
        markSelectedOption(workHoursButton);
    }

    public void handleMonthlyPayments() {
        mainController.loadView("/views/MonthlyPaymentsView.fxml");
        markSelectedOption(monthlyPaymentsButton);
    }

    public void handleSalaryVariables() {
        mainController.loadView("/views/SalaryVariablesView.fxml");
        markSelectedOption(salaryVariablesButton);

    }

    public void handleChartsStatistics() {
        mainController.loadView("/views/ChartsStatisticsView.fxml");
        markSelectedOption(chartsStatisticsButton);
    }



    public void handlePersonalData() {
        mainController.loadView("/views/PersonalDataView.fxml");
        markSelectedOption(personalDataButton);
    }

    public void handleAttendance() {
        mainController.loadView("/views/AttendanceView.fxml");
        markSelectedOption(attendanceButton);
    }

    public void handleEmployeeHistory() {
        mainController.loadView("/views/EmployeeHistoryView.fxml");
        markSelectedOption(employeeHistoryButton);
    }
    private void markSelectedOption(Button selectedButton) {
        // Primero, quita la clase 'selected-option' de todos los botones
        employeeListButton.getStyleClass().remove("selected-option");
        registerEmployeeButton.getStyleClass().remove("selected-option");
        salaryHistoryButton.getStyleClass().remove("selected-option");
        positionHistoryButton.getStyleClass().remove("selected-option");
        workHoursButton.getStyleClass().remove("selected-option");
        monthlyPaymentsButton.getStyleClass().remove("selected-option");
        salaryVariablesButton.getStyleClass().remove("selected-option");
        chartsStatisticsButton.getStyleClass().remove("selected-option");
        personalDataButton.getStyleClass().remove("selected-option");
        attendanceButton.getStyleClass().remove("selected-option");
        employeeHistoryButton.getStyleClass().remove("selected-option");
        selectedButton.getStyleClass().add("selected-option");
    }
}
