package com.example.examefinalc2frontend.Controller;

import com.example.examefinalc2frontend.Models.EmployeePayment;
import com.example.examefinalc2frontend.Models.Payroll;
import com.example.examefinalc2frontend.Models.Salary;
import com.example.examefinalc2frontend.Services.PayrollService;
import com.example.examefinalc2frontend.Utils.EmailButtonCellFactory;
import com.example.examefinalc2frontend.Utils.EmailSender;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import com.itextpdf.layout.Document;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MonthlyPaymentsController {
    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> monthComboBox;

    @FXML
    private ComboBox<Integer> yearComboBox;

    @FXML
    private TableView<EmployeePayment> employeeTable;

    @FXML
    private TableColumn<EmployeePayment, String> employeeColumn;

    @FXML
    private TableColumn<EmployeePayment, String> documentColumn;

    @FXML
    private TableColumn<EmployeePayment, String> emailColumn;

    @FXML
    private TableColumn<EmployeePayment, String> areaColumn;

    @FXML
    private TableColumn<EmployeePayment, String> monthColumn;

    @FXML
    private TableColumn<EmployeePayment, Integer> yearColumn;

    @FXML
    private TableColumn<EmployeePayment, Double> totalColumn;

    @FXML
    private TableColumn<EmployeePayment, Button> downloadColumn;

    @FXML
    private TableColumn<EmployeePayment, Button> sendEmailColumn;

    // ObservableList para manejar los datos de la tabla
    private ObservableList<EmployeePayment> paymentsList;
    @FXML
    public void initialize() {
        // Configurar las columnas de la tabla
        employeeColumn.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        documentColumn.setCellValueFactory(new PropertyValueFactory<>("documentNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));
        monthColumn.setCellValueFactory(new PropertyValueFactory<>("month"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        downloadColumn.setCellValueFactory(new PropertyValueFactory<>("downloadButton"));
        sendEmailColumn.setCellValueFactory(new PropertyValueFactory<>("sendEmailButton"));

        // Inicializar datos de ejemplo (puedes reemplazar con datos reales)
        paymentsList = FXCollections.observableArrayList(getDummyPayments());
        employeeTable.setItems(paymentsList);
        // Configurar los combos de mes y año
        monthComboBox.setItems(FXCollections.observableArrayList("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"));
        yearComboBox.setItems(FXCollections.observableArrayList(2022, 2023, 2024, 2025));
        sendEmailColumn.setCellFactory(column -> new TableCell<>() {
            private final Button sendButton = new Button("Enviar");

            {
                sendButton.setOnAction(event -> {
                    EmployeePayment payment = getTableView().getItems().get(getIndex());
                    if (payment != null) {
                        handleSendEmail(payment);
                    }
                });

            }

            @Override
            protected void updateItem(Button button, boolean b) {
                super.updateItem(button, b);
                if (b) {
                    setGraphic(null);
                } else {
                    setGraphic(sendButton);
                }
            }
        });
        downloadColumn.setCellFactory(column -> new TableCell<>() {
            private final Button downloadButton = new Button("Descargar");

            {
                downloadButton.setOnAction(event -> {
                    EmployeePayment payment = getTableView().getItems().get(getIndex());
                    if (payment != null) {
                        handleDownload(payment);
                    }
                });
            }

            @Override
            protected void updateItem(Button button, boolean b) {
                super.updateItem(button, b);
                if (b) {
                    setGraphic(null);
                } else {
                    setGraphic(downloadButton);
                }
            }
        });
        monthComboBox.setItems(FXCollections.observableArrayList("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"));
        yearComboBox.setItems(FXCollections.observableArrayList(2022, 2023, 2024, 2025));
    }
    @FXML
    private void handleSearch() {
        String searchText = searchField.getText().toLowerCase();
        ObservableList<EmployeePayment> filteredList = FXCollections.observableArrayList();

        for (EmployeePayment payment : paymentsList) {
            if (payment.getEmployeeName().toLowerCase().contains(searchText) ||
                    payment.getDocumentNumber().toLowerCase().contains(searchText)) {
                filteredList.add(payment);
            }
        }

        employeeTable.setItems(filteredList);
    }
    private List<EmployeePayment> getDummyPayments() {
        PayrollService payrollService = new PayrollService();
        List<Payroll> payments= payrollService.getEmployeePayments(11,2024).getPayrolls();
        List<EmployeePayment> employeePayments = convertPayrollToEmployeePayment(payments);
        return employeePayments;

    }
    @FXML
    private void handleSendEmail(EmployeePayment payment) {
        // Datos del correo
        String myEmail = "harukakasugano32@gmail.com";
        String myPassword = "iyyivyvwnnljbuyb"; // O un token generado en tu cuenta de Gmail
        String recipient = payment.getEmail();
        String subject = "Boleta de pago";
        String content = "Adjunto se encuentra su boleta de pago.";
        File pdfFile = handleDownload(payment);
        if (pdfFile != null) {
            EmailSender emailSender = new EmailSender(myEmail, myPassword);
            emailSender.sendEmailWithAttachment(recipient, subject, content, pdfFile); // Método modificado para enviar el adjunto
        }
    }
    public static List<EmployeePayment> convertPayrollToEmployeePayment(List<Payroll> payrollList) {
        return payrollList.stream().map(payroll -> {
            // Crear el botón "Descargar"
            Button downloadButton = new Button("Download");
            // Crear el botón "Enviar Email"
            Button sendEmailButton = new Button("Send Email");

            // Crear el objeto EmployeePayment usando los valores de Payroll
            return new EmployeePayment(
                    payroll.getEmployee().getFirstName() + " " + payroll.getEmployee().getLastName(),
                    payroll.getEmployee().getDocumentNumber(),
                    payroll.getEmployee().getUser().getEmail(),
                    payroll.getEmployee().getArea().getName(),
                    getMonthString(payroll.getMonthCreated()),  // Asumiendo que `monthCreated` es un número
                    payroll.getYearCreated(),
                    payroll.getTotal(),
                    downloadButton,
                    sendEmailButton,
                    payroll.getPayrollDetails()
            );
        }).collect(Collectors.toList());
    }
    public File handleDownload(EmployeePayment payment) {
        // Aquí puedes implementar la lógica para descargar el archivo
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try {
                // Crear el documento PDF
                PdfWriter writer = new PdfWriter(new FileOutputStream(file));
                PdfDocument pdfDoc = new PdfDocument(writer);
                Document document = new Document(pdfDoc, PageSize.A4); // También puedes especificar el tamaño de la página

                PdfFont boldFont = PdfFontFactory.createFont("Helvetica-Bold");
                PdfFont regularFont = PdfFontFactory.createFont("Helvetica");
                // Título de la boleta de pago
                Paragraph title = new Paragraph("Boleta de Pago")
                        .setFont(boldFont)
                        .setFontSize(18);
                document.add(title);

                // Agregar detalles de empleado
                document.add(new Paragraph(payment.getEmployeeName())
                        .setFont(regularFont)
                        .setFontSize(12));
                document.add(new Paragraph(payment.getArea())
                        .setFont(regularFont)
                        .setFontSize(12));




                document.add(new Paragraph("\n"));
                String afp = payment.getPayrollDetails().stream()
                        .filter(payrollDetail -> payrollDetail.getTypeKey().equals("AFP"))
                        .map(payrollDetail -> String.valueOf(payrollDetail.getValue()))  // Convertimos el valor a String
                        .findFirst()  // Obtiene el primer resultado encontrado
                        .orElse("0.00");  // Val
                String salary = payment.getPayrollDetails().stream()
                        .filter(payrollDetail -> payrollDetail.getTypeKey().equals("SALARY"))
                        .map(payrollDetail -> String.valueOf(payrollDetail.getValue()))  // Convertimos el valor a String
                        .findFirst()  // Obtiene el primer resultado encontrado
                        .orElse("0.00");  // Valor por defecto si no se encuentra nada
                String seguro=payment.getPayrollDetails().stream()
                        .filter(payrollDetail -> payrollDetail.getTypeKey().equals("ISSS"))
                        .map(payrollDetail -> String.valueOf(payrollDetail.getValue()))  // Convertimos el valor a String
                        .findFirst()  // Obtiene el primer resultado encontrado
                        .orElse("0.00");  // Valor por defecto si no se encuentra nada
                String cts=payment.getPayrollDetails().stream()
                        .filter(payrollDetail -> payrollDetail.getTypeKey().equals("CTS"))
                        .map(payrollDetail -> String.valueOf(payrollDetail.getValue()))  // Convertimos el valor a String
                        .findFirst()  // Obtiene el primer resultado encontrado
                        .orElse("0.00");  // Valor por defecto si no se encuentra nada
                String gratificacion=payment.getPayrollDetails().stream()
                        .filter(payrollDetail -> payrollDetail.getTypeKey().equals("GRATIFICACION"))
                        .map(payrollDetail -> String.valueOf(payrollDetail.getValue()))  // Convertimos el valor a String
                        .findFirst()  // Obtiene el primer resultado encontrado
                        .orElse("0.00");  // Valor por defecto si no se encuentra nada
                String fiveCategory=payment.getPayrollDetails().stream()
                        .filter(payrollDetail -> payrollDetail.getTypeKey().equals("FIVE_CATEGORY"))
                        .map(payrollDetail -> String.valueOf(payrollDetail.getValue()))  // Convertimos el valor a String
                        .findFirst()  // Obtiene el primer resultado encontrado
                        .orElse("0.00");  // Valor por defecto si no se encuentra nada

                document.add(new Paragraph("Detalles de Pago")
                        .setFont(boldFont)
                        .setFontSize(14));
                document.add(new Paragraph("\nSueldo Base:")
                        .setFont(boldFont)
                        .setFontSize(12));
                document.add(new Paragraph(salary)
                        .setFont(regularFont)
                        .setFontSize(12));



                if (!afp.equals("0.00")) {
                    document.add(new Paragraph("\nAFP:")
                            .setFont(boldFont)
                            .setFontSize(12));
                    document.add(new Paragraph("-" + afp)
                            .setFont(regularFont)
                            .setFontSize(12));
                }
                if (!seguro.equals("0.00")) {
                    document.add(new Paragraph("\nSeguro:")
                            .setFont(boldFont)
                            .setFontSize(12));
                    document.add(new Paragraph("-" + seguro)
                            .setFont(regularFont)
                            .setFontSize(12));
                }
                if (!cts.equals("0.00")) {
                    document.add(new Paragraph("\nCTS:")
                            .setFont(boldFont)
                            .setFontSize(12));
                    document.add(new Paragraph("+ " + cts)
                            .setFont(regularFont)
                            .setFontSize(12));
                }
                if (!gratificacion.equals("0.00")) {
                    document.add(new Paragraph("\nGratificación:")
                            .setFont(boldFont)
                            .setFontSize(12));
                    document.add(new Paragraph("+ " + gratificacion)
                            .setFont(regularFont)
                            .setFontSize(12));
                }
                if (!fiveCategory.equals("0.00")) {
                    document.add(new Paragraph("\nQuinta Categoría:")
                            .setFont(boldFont)
                            .setFontSize(12));
                    document.add(new Paragraph("-" + fiveCategory)
                            .setFont(regularFont)
                            .setFontSize(12));
                }

                document.add(new Paragraph("\nSueldo Total:")
                        .setFont(boldFont)
                        .setFontSize(12));
                document.add(new Paragraph(String.valueOf(payment.getTotal()))
                        .setFont(regularFont)
                        .setFontSize(12));
                // Espaciado adicional
                document.add(new Paragraph("\n"));

                // Agregar fecha y firma
                document.add(new Paragraph("Fecha de emisión: 2024-10-31")
                        .setFont(regularFont)
                        .setFontSize(12));
                document.add(new Paragraph("\nFirma: _______________________")
                        .setFont(regularFont)
                        .setFontSize(12));


                // Cerrar el documento
                document.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }
    private static void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    private static String getMonthString(int month) {
        switch (month) {
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default: return "Unknown";
        }
    }
}
