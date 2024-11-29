package com.example.examefinalc2frontend.Controller;

import com.example.examefinalc2frontend.Models.Employee;
import com.example.examefinalc2frontend.Services.EmployeeService;
import com.example.examefinalc2frontend.Utils.Excel.EmployeeExcelProcessor;
import com.example.examefinalc2frontend.Utils.Excel.ExcelProcessor;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController {

    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, Integer> idColumn;
    @FXML
    private TableColumn<Employee, String> firstNameColumn;
    @FXML
    private TableColumn<Employee, String> lastNameColumn;
    @FXML
    private TableColumn<Employee, Boolean> activeColumn;
    @FXML
    private TableColumn<Employee, Boolean> genderColumn;
    @FXML
    private TableColumn<Employee, String> hireDateColumn;
    @FXML
    private TableColumn<Employee, String> terminationDateColumn;
    @FXML
    private TableColumn<Employee, String> phoneNumberColumn;
    @FXML
    private TableColumn<Employee, String> addressColumn;
    @FXML
    private TableColumn<Employee, String> documentNumberColumn;
    @FXML
    private TableColumn<Employee, Integer> documentTypeIdColumn;
    @FXML
    private TableColumn<Employee, String> createdAtColumn;
    @FXML
    private TextField searchField;
    private ExcelProcessor<Employee> excelProcessor;

    List<Employee> employeesList = new ArrayList<>();  // Si no tienes los empleados aún, inicializa como una lista vacía

    @FXML
    public void initialize() {
        this.excelProcessor = new EmployeeExcelProcessor(); // Usamos la implementación específica de Employee

        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        firstNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        lastNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        activeColumn.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isActive()).asObject());
        genderColumn.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isGender()).asObject());
        hireDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHireDate().toString()));
        terminationDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTerminationDate() != null ? cellData.getValue().getTerminationDate().toString() : "N/A"));
        phoneNumberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        addressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        documentNumberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDocumentNumber()));
        documentTypeIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDocumentTypeId()).asObject());
        createdAtColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCreatedAt() != null ? cellData.getValue().getCreatedAt().toString() : "N/A"));
        loadEmployees();

    }


    public List<Employee> searchEmployee() {
        String search = searchField.getText();
        EmployeeService employeeService = new EmployeeService();
        employeesList = employeeService.getEmployees(search).getEmployees();
        if(employeesList == null){
            employeesList = new ArrayList<>();
        }
        employeeTable.getItems().clear();
        employeeTable.getItems().addAll(employeesList);
        return employeesList;
    }
    public  void importEmployees() {

    }
    public void exportEmployees() {
        List<Employee> employeesList = searchEmployee(); // Llama al método de búsqueda de empleados

        if (employeesList.isEmpty()) {
            System.out.println("No employees to export.");
            return;
        }

        try {
            excelProcessor.exportToExcel(employeesList);
            System.out.println("Employees exported successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to export employees.");
        }
    }
    public void  loadEmployees(){
        if (employeesList == null) {
            employeesList = new ArrayList<>();  // O manejar el caso cuando la lista no está disponible
        }

        EmployeeService employeeService = new EmployeeService();
        employeesList = employeeService.getEmployees("").getEmployees();
        if(employeesList == null){
            employeesList = new ArrayList<>();
        }
        for (Employee employee : employeesList) {
            System.out.println(employee);
        }
        employeeTable.getItems().addAll(employeesList);
    }
}
