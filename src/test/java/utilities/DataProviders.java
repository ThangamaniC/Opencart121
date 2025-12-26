package utilities;
import org.testng.annotations.*;

public class DataProviders {
	
	@DataProvider(name="LoginData")
    public static Object[][] getExcelData() {
    	
	
    	String path=".\\testData\\testdata.xlsx";
    	ExcelUtility xcel=new ExcelUtility(path,"Sheet1");
    	
        int rows = xcel.getRowCount();
        int cols = xcel.getColumnCount();

        Object[][] data = new Object[rows - 1][cols];

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i - 1][j] = xcel.getCellData(i, j);
                System.out.println("Data [" + (i-1) + "][" + j + "] = " + data[i-1][j]);
            }
        }
      
        return data;
      
    }


}
