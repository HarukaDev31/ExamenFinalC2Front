package com.example.examefinalc2frontend.Controller;

import com.example.examefinalc2frontend.Models.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class EmployeeController {

    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, Number> idColumn;
    @FXML
    private TableColumn<Employee, String> nameColumn;
    @FXML
    private TableColumn<Employee, String> lastNameColumn;
    @FXML
    private TableColumn<Employee, String> emailColumn;
    @FXML
    private TableColumn<Employee, String> phoneColumn;
    @FXML
    private TableColumn<Employee, String> addressColumn;
    @FXML
    private TableColumn<Employee, String> birthDateColumn;
    @FXML
    private TableColumn<Employee, String> hireDateColumn;
    @FXML
    private TableColumn<Employee, Number> salaryColumn;
    @FXML
    private TableColumn<Employee, String> positionColumn;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        birthDateColumn.setCellValueFactory(cellData -> cellData.getValue().birthDateProperty());
        hireDateColumn.setCellValueFactory(cellData -> cellData.getValue().hireDateProperty());
        salaryColumn.setCellValueFactory(cellData -> cellData.getValue().salaryProperty());
        positionColumn.setCellValueFactory(cellData -> cellData.getValue().positionProperty());
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        employees.add(new Employee(1, "Juan", "Perez", "juan.perez@mail.com", "555-1234", "Av. Ejemplo 123",
                "01-01-1980", "01-01-2020", 3000.00, "Gerente"));
        employees.add(new Employee(2, "Ana", "Gomez", "ana.gomez@mail.com", "555-5678", "Calle Ejemplo 456",
                "02-02-1990", "02-02-2021", 2500.00, "Supervisor"));
        // Agregar más empleados si es necesario

        // Asignar la lista a la tabla
        employeeTable.setItems(employees);
    }


    public void searchEmployee() {
    }
    public  void importEmployees() {
    }
    public void exportEmployees() {
    }
}
