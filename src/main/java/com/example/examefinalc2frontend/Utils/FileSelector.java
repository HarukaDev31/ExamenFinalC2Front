package com.example.examefinalc2frontend.Utils;

import javax.swing.*;
import java.io.*;

public class FileSelector {
    /**
     * Carga un archivo desde una ruta específica o permite al usuario seleccionar un archivo.
     *
     * @param title El título de la ventana de selección de archivos.
     * @param filePath Ruta del archivo a cargar; si es null, se abrirá el selector de archivos.
     * @return FileInputStream del archivo seleccionado o cargado, o null si no se selecciona ningún archivo.
     */
    public FileInputStream selectFile(String title, String filePath) {
        try {
            if (filePath != null && !filePath.isEmpty()) {
                return new FileInputStream(new File(filePath));
            } else {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle(title);
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    return new FileInputStream(fileChooser.getSelectedFile());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public FileOutputStream saveFile(String title, String filePath) {
        try {
            if (filePath != null && !filePath.isEmpty()) {
                return new FileOutputStream(new File(filePath));
            } else {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle(title);
                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    return new FileOutputStream(fileChooser.getSelectedFile());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
