package APIs;

import Entities.Cour;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ToXLSCour {

   public void exportToXLS(TableView<Cour> tableView, File file) {
        try {

            // Create a new workbook
            WorkbookSettings ws = new WorkbookSettings();
            ws.setLocale(new Locale("en", "EN"));
            WritableWorkbook workbook = Workbook.createWorkbook(file, ws);

            // Create a new sheet
            WritableSheet sheet = workbook.createSheet("Cours", 0);

            // Get the table columns
            ObservableList<TableColumn<Cour, ?>> columns = tableView.getColumns();

            // Write the column headers to the first row of the sheet
            int columnCount = columns.size() - 1;
            for (int i = 0; i < columnCount; i++) {
                String columnHeader = columns.get(i).getText();
                Label label = new Label(i, 0, columnHeader);
                sheet.addCell(label);
            }

            // Write the data rows to the sheet
            ObservableList<Cour> data = tableView.getItems();
            int rowCount = data.size();
            Cour rowData = null;
            for (int i = 0; i < rowCount; i++) {
                rowData = data.get(i);

                for (int j = 0; j < columnCount; j++) {

                    TableColumn<Cour, ?> column = columns.get(j);
                    Object cellValue = column.getCellData(rowData);
                    Label label = new Label(j, i + 1, cellValue.toString());
                    sheet.addCell(label);

                }
            }

            sheet.removeColumn(1);

            // Write the workbook to disk
            workbook.write();
            workbook.close();
        } catch (IOException | WriteException e) {
            e.printStackTrace();
        }
    }

}
