package com.example.examefinalc2frontend.Utils.Excel;

import com.example.examefinalc2frontend.Utils.FileSelector;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelImporter<T> implements ExcelProcessor<T> {
    private Workbook workbook;

    @Override
    public void loadFile() throws IOException {
        try(FileInputStream fis=new FileSelector().selectFile("Select a file",null)) {
            if (fis != null) {
                workbook = new XSSFWorkbook(fis);
                System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
            }
        }
    }

    @Override
    public List<T> readData(int sheetIndex) {
        List<T> data = new ArrayList<>();
        Sheet sheet=workbook.getSheetAt(sheetIndex);
        for(Row row:sheet){
            if(row.getRowNum()==0)continue;
        }
        return data;
    }



    @Override
    public void fillTemplate(String path, List<T> data) {
        // TODO Auto-generated method stub
    }

    @Override
    public void saveFile(String path) {
        // TODO Auto-generated method stub
    }

    @Override
    public List<T> fetchData(String storedProcedure) {
        return List.of();
    }



    @Override
    public void saveData(List<T> data) {
        // TODO Auto-generated method stub
    }
}
