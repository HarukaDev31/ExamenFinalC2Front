package com.example.examefinalc2frontend.Controller;

import com.example.examefinalc2frontend.Models.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class EmployeeFormController {

    @FXML
    private TextField idField, nameField, lastNameField, emailField, phoneField, addressField, salaryField, positionField;
    @FXML
    private DatePicker birthDateField, hireDateField;

    // Método para guardar un empleado
    @FXML
    private void saveEmployee() {
        // Obtén los valores de los campos del formulario
        String name = nameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();
        String birthDate = birthDateField.getValue().toString();
        String hireDate = hireDateField.getValue().toString();
        double salary = Double.parseDouble(salaryField.getText());
        String position = positionField.getText();

        // Crea un nuevo objeto Employee (debes adaptar este código según tu implementación de Employee)
        Employee newEmployee = new Employee(0, name, lastName, email, phone, address, birthDate, hireDate, salary, position);

        // Aquí podrías guardar el empleado en una base de datos o hacer alguna otra acción

        // Mostrar mensaje de éxito o limpiar el formulario
        System.out.println("Empleado guardado: " + newEmployee);
    }

    // Método para cancelar el formulario
    @FXML
    private void cancelForm() {
        // Limpia los campos o cierra el formulario
        nameField.clear();
        lastNameField.clear();
        emailField.clear();
        phoneField.clear();
        addressField.clear();
        salaryField.clear();
        positionField.clear();
        birthDateField.setValue(null);
        hireDateField.setValue(null);
    }
}