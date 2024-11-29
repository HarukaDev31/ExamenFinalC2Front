package com.example.examefinalc2frontend.Utils.Excel;

import java.io.IOException;
import java.util.List;


import java.io.IOException;
import java.util.List;

public interface ExcelProcessor<T> {

    void exportToExcel(List<T> data) throws IOException;

    List<T> importFromExcel(String filePath, Class<T> clazz) throws IOException;
}
