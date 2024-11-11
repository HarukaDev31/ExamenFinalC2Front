package com.example.examefinalc2frontend.Controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;

public class ChartsStatisticsController {
    @FXML
    private BarChart<String, Number> expenseBarChart;

    @FXML
    private PieChart genderPieChart;

    @FXML
    private ComboBox<String> areaFilter;

    @FXML
    private ComboBox<String> positionFilter;

    @FXML
    private ComboBox<String> genderFilter;

    // Datos simulados de ejemplo (en un escenario real, estos datos vendrían de la base de datos o una API)
    //private final PayrollDataService dataService = new PayrollDataService();

    @FXML
    public void initialize() {
        loadFilters();
        loadCharts();
    }

    // Método para cargar los filtros en los ComboBox
    private void loadFilters() {
        areaFilter.getItems().addAll("Todas", "Administración", "Ventas", "Producción");
        positionFilter.getItems().addAll("Todos", "Gerente", "Supervisor", "Operador");
        genderFilter.getItems().addAll("Todos", "Masculino", "Femenino");
    }

    // Cargar gráficos con datos iniciales
    private void loadCharts() {
        setupBarChart();
        setupPieChart();
    }

    // Configuración inicial del Gráfico de Barras (Gasto por Área o Cargo)
    private void setupBarChart() {
        expenseBarChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Gasto en USD");

        // Ejemplo de datos para gastos
        series.getData().add(new XYChart.Data<>("Administración", 50000));
        series.getData().add(new XYChart.Data<>("Ventas", 30000));
        series.getData().add(new XYChart.Data<>("Producción", 70000));

        expenseBarChart.getData().add(series);
    }

    // Configuración inicial del Gráfico de Pastel (Distribución por Género)
    private void setupPieChart() {
        genderPieChart.getData().clear();

        PieChart.Data maleData = new PieChart.Data("Masculino", 60);
        PieChart.Data femaleData = new PieChart.Data("Femenino", 40);

        genderPieChart.getData().addAll(maleData, femaleData);
    }

    // Método para aplicar los filtros seleccionados
    @FXML
    private void applyFilters() {
        String selectedArea = areaFilter.getValue();
        String selectedPosition = positionFilter.getValue();
        String selectedGender = genderFilter.getValue();

        // Simulación de filtrado de datos (en un escenario real, el filtrado sería dinámico basado en datos)
        /**double expense = dataService.getFilteredExpense(selectedArea, selectedPosition, selectedGender);
        updateBarChart(expense);
        updatePieChart(selectedGender);**/
    }

    // Actualizar el Gráfico de Barras con el resultado filtrado
    private void updateBarChart(double filteredExpense) {
        expenseBarChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Gasto Filtrado en USD");
        series.getData().add(new XYChart.Data<>("Filtrado", filteredExpense));
        expenseBarChart.getData().add(series);
    }

    // Actualizar el Gráfico de Pastel basado en el género filtrado
    private void updatePieChart(String gender) {
        genderPieChart.getData().clear();

        if ("Masculino".equals(gender)) {
            genderPieChart.getData().add(new PieChart.Data("Masculino", 100));
        } else if ("Femenino".equals(gender)) {
            genderPieChart.getData().add(new PieChart.Data("Femenino", 100));
        } else {
            genderPieChart.getData().add(new PieChart.Data("Masculino", 60));
            genderPieChart.getData().add(new PieChart.Data("Femenino", 40));
        }
    }
}

