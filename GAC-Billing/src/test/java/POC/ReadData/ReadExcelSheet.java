package POC.ReadData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelSheet {

	XSSFWorkbook wb;
	XSSFSheet sheet;

	public ReadExcelSheet(String excelPath) throws InvalidFormatException, IOException {

		File srcFile = new File(excelPath);
		FileInputStream fis = new FileInputStream(srcFile);
		// Read excel file
		wb = new XSSFWorkbook(fis); // xlsx
		// get sheet
		sheet = wb.getSheetAt(0);
		wb.close();
	}

	public String getCellData(int sheetNumber, int row, int column) {
		// sheet =wb.getSheetAt(sheetNumber);
		String data = null;
		try {

			XSSFCell cell = sheet.getRow(row).getCell(column);
			// Read data from excel if data is string
			if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
				data = cell.getStringCellValue();

			// Read data from excel if data is numeric
			else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
				data = Integer.toString((int) cell.getNumericCellValue());

		} catch (Exception e) {
			data = "";
		}
		return data;
	}

	// get row count from excel sheet
	public int getRowCount(int sheetIndex) {
		int rowCNT = wb.getSheetAt(sheetIndex).getLastRowNum();
		return rowCNT;

	}

	// get column count from excel sheet
	public int getColCount(int sheetIndex) {
		int colCNT = wb.getSheetAt(sheetIndex).getRow(0).getLastCellNum();
		return colCNT;

	}

}
