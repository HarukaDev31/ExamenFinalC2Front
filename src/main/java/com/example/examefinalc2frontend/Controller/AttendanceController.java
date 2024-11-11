package com.example.examefinalc2frontend.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AttendanceController {

    @FXML
    private Label currentTimeLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Button entradaButton;

    @FXML
    private Button salidaButton;

    @FXML
    private TableView<AttendanceRecord> attendanceTable;

    @FXML
    private TableColumn<AttendanceRecord, String> dateColumn;

    @FXML
    private TableColumn<AttendanceRecord, String> entryColumn;

    @FXML
    private TableColumn<AttendanceRecord, String> exitColumn;

    @FXML
    private TableColumn<AttendanceRecord, String> totalHoursColumn;

    private Timeline clock;

    @FXML
    public void initialize() {
        // Configurar las columnas de la tabla
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        entryColumn.setCellValueFactory(new PropertyValueFactory<>("entry"));
        exitColumn.setCellValueFactory(new PropertyValueFactory<>("exit"));
        totalHoursColumn.setCellValueFactory(new PropertyValueFactory<>("totalHours"));

        // Iniciar el reloj
        startClock();

        // Cargar datos de ejemplo
        loadSampleData();
    }

    private void startClock() {
        clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            currentTimeLabel.setText(now.format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
    }

    private void loadSampleData() {
        attendanceTable.getItems().addAll(
                new AttendanceRecord("2023-11-15", "08:55", "18:03", "9.13"),
                new AttendanceRecord("2023-11-14", "09:02", "18:00", "8.97"),
                new AttendanceRecord("2023-11-13", "08:50", "17:55", "9.08"),
                new AttendanceRecord("2023-11-10", "08:58", "18:10", "9.20"),
                new AttendanceRecord("2023-11-09", "09:05", "18:02", "8.95")
        );
    }

    @FXML
    private void handleEntrada() {
        // Lógica para marcar entrada
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String currentTime = now.format(timeFormatter);
        // Aquí agregarías la lógica para guardar la entrada

        entradaButton.setDisable(true);
        salidaButton.setDisable(false);
    }

    @FXML
    private void handleSalida() {
        // Lógica para marcar salida
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String currentTime = now.format(timeFormatter);
        // Aquí agregarías la lógica para guardar la salida

        entradaButton.setDisable(false);
        salidaButton.setDisable(true);
    }

    // Clase interna para los registros de asistencia
    public static class AttendanceRecord {
        private final String date;
        private final String entry;
        private final String exit;
        private final String totalHours;

        public AttendanceRecord(String date, String entry, String exit, String totalHours) {
            this.date = date;
            this.entry = entry;
            this.exit = exit;
            this.totalHours = totalHours;
        }

        public String getDate() { return date; }
        public String getEntry() { return entry; }
        public String getExit() { return exit; }
        public String getTotalHours() { return totalHours; }
    }
}