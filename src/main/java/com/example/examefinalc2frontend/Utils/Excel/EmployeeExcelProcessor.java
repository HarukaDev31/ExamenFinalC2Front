package com.example.examefinalc2frontend.Utils.Excel;
import com.example.examefinalc2frontend.Models.Employee;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
public class EmployeeExcelProcessor implements ExcelProcessor<Employee> {

    @Override
    public void exportToExcel(List<Employee> data) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Employees");

        // Crear los encabezados de columna basados en los campos de la clase Employee
        Row headerRow = sheet.createRow(0);
        Employee firstItem = data.get(0);
        Field[] fields = firstItem.getClass().getDeclaredFields();

        // Crear las celdas de los encabezados
        int cellIndex = 0;
        for (Field field : fields) {
            field.setAccessible(true);
            headerRow.createCell(cellIndex++).setCellValue(field.getName());
        }

        // Agregar los datos de las filas
        int rowIndex = 1;
        for (Employee employee : data) {
            Row row = sheet.createRow(rowIndex++);
            cellIndex = 0;
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(employee);
                    // Verificar si el valor es null y asignar un valor por defecto
                    String fieldValue = (value != null) ? value.toString() : "N/A";
                    // Crear la celda con el valor obtenido
                    row.createCell(cellIndex++).setCellValue(fieldValue);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        // Guardar el archivo Excel
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar archivo Excel");
        fileChooser.setSelectedFile(new File("empleados.xlsx"));  // Valor predeterminado

        // Abrir el diálogo de guardado
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            // Si el archivo no tiene extensión, agregar ".xlsx"
            if (!fileToSave.getName().endsWith(".xlsx")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".xlsx");
            }

            // Guardar el archivo Excel
            try (FileOutputStream fileOut = new FileOutputStream(fileToSave)) {
                workbook.write(fileOut);
            }
            workbook.close();
        } else {
            System.out.println("Operación de guardado cancelada.");
        }
    }

    @Override
    public List<Employee> importFromExcel(String filePath, Class<Employee> clazz) throws IOException {
        List<Employee> data = new ArrayList<>();
        FileInputStream fis = new FileInputStream(new File(filePath));
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        // Leer la primera fila (encabezados)
        Row headerRow = sheet.getRow(0);
        Field[] fields = clazz.getDeclaredFields();

        // Leer los datos
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            Employee obj = null;
            try {
                obj = clazz.getDeclaredConstructor().newInstance(); // Crear una nueva instancia de Employee
                for (int j = 0; j < fields.length; j++) {
                    fields[j].setAccessible(true);
                    Cell cell = row.getCell(j);
                    String cellValue = cell != null ? cell.toString() : "";
                    fields[j].set(obj, convertToFieldType(fields[j], cellValue));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (obj != null) {
                data.add(obj);
            }
        }

        workbook.close();
        fis.close();
        return data;
    }

    // Método para convertir el valor de la celda al tipo del campo
    private Object convertToFieldType(Field field, String value) {
        Class<?> fieldType = field.getType();
        if (fieldType == int.class) {
            return Integer.parseInt(value);
        } else if (fieldType == boolean.class) {
            return Boolean.parseBoolean(value);
        } else if (fieldType == String.class) {
            return value;
        }
        return value; // Para otros tipos, puedes agregar más lógica
    }
}