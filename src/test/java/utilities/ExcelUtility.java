
package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    private Workbook workbook;
    private Sheet sheet;

    // Constructor
    public ExcelUtility(String excelPath, String sheetName) {
        try {
            FileInputStream fis = new FileInputStream(excelPath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get Row Count
    public int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    // Get Column Count
    public int getColumnCount() {
        return sheet.getRow(0).getPhysicalNumberOfCells();
    }

    // Get Cell Data
    public String getCellData(int rowNum, int colNum) {
        DataFormatter formatter = new DataFormatter();
        Cell cell = sheet.getRow(rowNum).getCell(colNum);
        return formatter.formatCellValue(cell);
    }

        // Close Workbook
    public void closeWorkbook() throws IOException {
        workbook.close();
    }
}
