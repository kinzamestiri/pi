/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.FileOutputStream;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Mon Pc
 */
class TableViewToExcelConverter {
    
    public void convert(TableView table, String fileName) throws IOException  {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("My Sheet");
            
            // Add header row
            Row headerRow = sheet.createRow(0);
            ObservableList<TableColumn> columns = table.getColumns();
            for (int i = 0; i < columns.size(); i++) {
                Cell headerCell = headerRow.createCell(i);
                headerCell.setCellValue(columns.get(i).getText());
            }
            
            // Add data rows
            ObservableList data = table.getItems();
            for (int i = 0; i < data.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                Object item = data.get(i);
                for (int j = 0; j < columns.size(); j++) {
                    TableColumn column = columns.get(j);
                    Cell dataCell = dataRow.createCell(j);
                    dataCell.setCellValue(column.getCellData(item).toString());
                }
            }
            
            FileOutputStream outputStream = new FileOutputStream(fileName);
            workbook.write(outputStream);
        }
    }
    
}
