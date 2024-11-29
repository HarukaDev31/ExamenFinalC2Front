module com.example.examefinalc2frontend {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.poi.ooxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;
    requires static lombok;
    requires java.prefs;
    requires java.mail;
    requires org.json;
    requires layout;
    requires kernel;

    opens com.example.examefinalc2frontend to javafx.fxml;
    opens com.example.examefinalc2frontend.Controller to javafx.fxml;
    opens com.example.examefinalc2frontend.Models to javafx.fxml;
    exports com.example.examefinalc2frontend;
    exports com.example.examefinalc2frontend.Controller;
    exports com.example.examefinalc2frontend.Models;
    exports com.example.examefinalc2frontend.Requests;
    exports com.example.examefinalc2frontend.Responses;}