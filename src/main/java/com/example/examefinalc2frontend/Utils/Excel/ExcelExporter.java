package com.example.examefinalc2frontend.Utils.Excel;

import com.example.examefinalc2frontend.Utils.FileSelector;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelExporter<T> implements ExcelProcessor<T> {
    private Workbook workbook;
    @Override
    public void loadFile() throws IOException {

    }

    @Override
    public List<T> readData(int sheetIndex) {
        return List.of();
    }

    @Override
    public void fillTemplate(String path, List<T> data) throws IOException {
        try(FileInputStream fis=new FileSelector().selectFile("Select a file",path)) {
            if (fis != null) {
                workbook = new XSSFWorkbook(fis);
                Sheet sheet=workbook.getSheetAt(0);
                for(int i=0;i<data.size();i++){
                    sheet.createRow(i);
                }
            }
        }
    }

    @Override
    public void saveFile(String path) throws IOException {
        try(FileOutputStream fis=new FileSelector().saveFile("Select a file",path)) {
            if (fis != null) {
                workbook.write(fis);
            }
        }
    }

    @Override
    public List<T> fetchData(String storedProcedure) {

        return new ArrayList<>();
    }

    @Override
    public void saveData(List<T> data) throws IOException {

    }
}
