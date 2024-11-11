package com.example.examefinalc2frontend.Utils.Excel;

import java.io.IOException;
import java.util.List;

public interface ExcelProcessor<T> {
    void loadFile() throws IOException;
    List<T> readData(int sheetIndex);
    void fillTemplate(String path, List<T> data) throws IOException;
    void saveFile(String path) throws IOException;
    List<T> fetchData(String storedProcedure);
    void saveData(List<T> data) throws IOException;
}
