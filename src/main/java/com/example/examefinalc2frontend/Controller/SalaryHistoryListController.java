package com.example.examefinalc2frontend.Controller;

import com.example.examefinalc2frontend.Models.Salary;
import com.example.examefinalc2frontend.Responses.GetSalariesResponse;
import com.example.examefinalc2frontend.Services.SalariesService;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalaryHistoryListController {
    /*
    Table view variablesTable
    *   <TableColumn text="Empleado"/>
                <TableColumn text="Cargo"/>
                <TableColumn text="Area"/>
                <TableColumn text="Salario"/>
                <TableColumn text="Fecha de Inicio"/>
                <TableColumn text="Fecha de Fin"/>
    * */

    @FXML
    private TableView<Salary> variablesTable;
    @FXML
    private TableColumn<Salary, String> employeeColumn;
    @FXML
    private TableColumn<Salary, String> positionColumn;
    @FXML
    private TableColumn<Salary, String> areaColumn;
    @FXML
    private TableColumn<Salary, Double> amountColumn;
    @FXML
    private TableColumn<Salary, String> startDateColumn;
    @FXML
    private TableColumn<Salary, String> endDateColumn;


    @FXML
    private List<GetSalariesResponse> salaryHistories=new ArrayList<>();
    @FXML
    public void initialize() {
        employeeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmployee().getFirstName() + " " + cellData.getValue().getEmployee().getLastName()));
        positionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPosition().getName()));
        areaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPosition().getArea().getName()));
        amountColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getAmount()).asObject());

        // Usamos un conversor para mostrar las fechas correctamente
        startDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(formatDate(cellData.getValue().getCreatedAt())));
        endDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(formatDate(cellData.getValue().getUpdatedAt())));

        // Llamamos al método que carga los datos
        loadSalaryData();
    }
    public void loadSalaryData() {
        // Crear un servicio de salarios
        SalariesService salariesService = new SalariesService();

        // Obtener la lista de salarios desde el servicio
        List<Salary> salaryList = salariesService.getSalaries().getSalaries();

        // Verificar si la lista de salarios no está vacía
        if (salaryList != null && !salaryList.isEmpty()) {
            // Crear un ObservableList para que JavaFX pueda actualizar la tabla
            ObservableList<Salary> observableSalaries = FXCollections.observableArrayList(salaryList);

            // Establecer los datos a la TableView
            variablesTable.setItems(observableSalaries);
        } else {
            System.out.println("No hay datos de salarios.");
        }
    }
    private String formatDate(Date date) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(date);
        }
        return "N/A";
    }
}
