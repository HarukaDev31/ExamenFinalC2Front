package com.example.examefinalc2frontend.Utils;

import com.example.examefinalc2frontend.Models.EmployeePayment;
import com.example.examefinalc2frontend.Models.Salary;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class EmailButtonCellFactory implements Callback<TableColumn<EmployeePayment, Void>, TableCell<EmployeePayment, Void>> {

    @Override
    public TableCell<EmployeePayment, Void> call(TableColumn<EmployeePayment, Void> param) {
        return new TableCell<>() {
            private final Button sendEmailButton = new Button("Enviar");

            {
                // Configurar acción del botón
                sendEmailButton.setOnAction(event -> {
                    EmployeePayment payment = getTableView().getItems().get(getIndex());
                    if (payment != null) {
                        sendEmailToEmployee(payment.getEmail());
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox hbox = new HBox(sendEmailButton);
                    hbox.setSpacing(10);
                    setGraphic(hbox);
                }
            }
        };
    }

    private void sendEmailToEmployee(String email) {
        // Aquí llamas a tu método para enviar correos
        EmailSender emailSender = new EmailSender("tucorreo@gmail.com", "tucontraseña");
        emailSender.sendEmail(email, "Boleta de pago", "Adjunto se encuentra su boleta de pago.");
        System.out.println("Correo enviado a: " + email);
    }

}
