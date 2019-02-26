package datadrivenframework.utils;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {
	
	XSSFWorkbook book;
	XSSFSheet sheet;
	
	public ReadExcelFile(String excelPath) {
		try {
			File file = new File(excelPath);
			FileInputStream input = new FileInputStream(file);
			book = new XSSFWorkbook(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String getData(int sheetNumber, int row, int column) {
		sheet = book.getSheetAt(sheetNumber);
		String data = sheet.getRow(row).getCell(column).getStringCellValue();
		return data;
	}
	
	public int getRowCount(int sheetIndex) {
		int row = book.getSheetAt(sheetIndex).getLastRowNum();
		row = row + 1;
		return row;
	}

}
