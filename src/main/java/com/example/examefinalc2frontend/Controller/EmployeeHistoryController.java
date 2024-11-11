package com.example.examefinalc2frontend.Controller;

import com.example.examefinalc2frontend.Models.CareerProgress;
import com.example.examefinalc2frontend.Models.PaySlip;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EmployeeHistoryController implements Initializable {
    @FXML
    private ImageView profileImage;
    @FXML private Label employeeName;
    @FXML private Label employeePosition;
    @FXML private ComboBox<String> yearSelector;

    @FXML private TableView<PaySlip> paySlipsTable;
    @FXML private TableColumn<PaySlip, LocalDate> paySlipDateColumn;
    @FXML private TableColumn<PaySlip, Double> grossSalaryColumn;
    @FXML private TableColumn<PaySlip, Double> netSalaryColumn;
    @FXML private TableColumn<PaySlip, String> actionColumn;

    @FXML private LineChart<Number, Number> salaryChart;
    @FXML private TableView<CareerProgress> careerTable;
    @FXML private TableColumn<CareerProgress, LocalDate> careerDateColumn;
    @FXML private TableColumn<CareerProgress, String> positionColumn;
    @FXML private TableColumn<CareerProgress, Double> salaryColumn;

    private final ObservableList<PaySlip> paySlips = FXCollections.observableArrayList();
    private final ObservableList<CareerProgress> careerProgress = FXCollections.observableArrayList();



    private void setupTables() {
        // Configure pay slips table
        paySlipDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        grossSalaryColumn.setCellValueFactory(new PropertyValueFactory<>("grossSalary"));
        netSalaryColumn.setCellValueFactory(new PropertyValueFactory<>("netSalary"));

        // Configure career progress table
        careerDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        setupTableFormatting();
    }

    private void setupTableFormatting() {
        // Date formatting
        paySlipDateColumn.setCellFactory(col -> new TableCell<>() {
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            @Override
            protected void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setText(empty || date == null ? null : formatter.format(date));
            }
        });

        // Currency formatting for pay slips table
        grossSalaryColumn.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Double amount, boolean empty) {
                super.updateItem(amount, empty);
                setText(empty || amount == null ? null : String.format("S/ %.2f", amount));
            }
        });

        netSalaryColumn.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Double amount, boolean empty) {
                super.updateItem(amount, empty);
                setText(empty || amount == null ? null : String.format("S/ %.2f", amount));
            }
        });

        // Download button in action column
        actionColumn.setCellFactory(col -> new TableCell<>() {
            private final Button downloadButton = new Button("Download PDF");
            {
                downloadButton.setOnAction(e -> handleDownload(getTableView().getItems().get(getIndex())));
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : downloadButton);
            }
        });
    }

    private void setupChart() {
        salaryChart.setTitle("Salary Progression");
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Salary");
        salaryChart.getData().add(series);
    }

    private void loadData() {
        // Load sample data
        paySlips.addAll(
                new PaySlip(LocalDate.of(2023, 10, 31), 5000, 4200, "/payslips/october-2023.pdf"),
                new PaySlip(LocalDate.of(2023, 9, 30), 5000, 4200, "/payslips/september-2023.pdf"),
                new PaySlip(LocalDate.of(2023, 8, 31), 4800, 4032, "/payslips/august-2023.pdf"),
                new PaySlip(LocalDate.of(2023, 7, 31), 4800, 4032, "/payslips/july-2023.pdf"),
                new PaySlip(LocalDate.of(2023, 6, 30), 4800, 4032, "/payslips/june-2023.pdf"),
                new PaySlip(LocalDate.of(2023, 5, 31), 4500, 3780, "/payslips/may-2023.pdf")
        );

        careerProgress.addAll(
                new CareerProgress(LocalDate.of(2020, 6, 1), "Marketing Assistant", 3000),
                new CareerProgress(LocalDate.of(2021, 1, 15), "Junior Marketing Analyst", 3500),
                new CareerProgress(LocalDate.of(2022, 3, 1), "Marketing Analyst", 4500),
                new CareerProgress(LocalDate.of(2023, 8, 1), "Senior Marketing Analyst", 5000)
        );

        paySlipsTable.setItems(paySlips);
        careerTable.setItems(careerProgress);
        updateChart();
    }

    private void setupYearSelector() {
        yearSelector.getItems().addAll("2023", "2022", "2021");
        yearSelector.setValue("2023");
        yearSelector.setOnAction(e -> handleYearChange());
    }

    private void updateChart() {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Salary");

        for (int i = 0; i < careerProgress.size(); i++) {
            series.getData().add(new XYChart.Data<>(i, careerProgress.get(i).getSalary()));
        }

        salaryChart.getData().clear();
        salaryChart.getData().add(series);
    }

    @FXML
    private void handleYearChange() {
        String selectedYear = yearSelector.getValue();
        // Implement year filtering logic here
    }

    private void handleDownload(PaySlip paySlip) {
        System.out.println("Downloading: " + paySlip.getDownloadUrl());
        // Implement download logic here
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTables();
        setupChart();
        loadData();
        setupYearSelector();
    }
}
