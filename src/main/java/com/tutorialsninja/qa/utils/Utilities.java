package com.tutorialsninja.qa.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.aventstack.extentreports.Status;

public class Utilities {
	
	public static final int IMPLICIT_WAIT=10;
	public static final int PAGE_WAIT_TIME=5;
	
	public static String generateTimeStamp() {
		
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String timeStamp = now.format(formatter);
        return  "apekshatest123_" + timeStamp + "@gmail.com";
     
	}
	
	public static Object[][] getTestDataFromExcelSheet(String sheetName) throws IOException {
		
		File excelFile=new File(System.getProperty("user.dir")+"/src/main/java/com/tutorialsninja/qa/testdata/Tutorialsninjadata.xlsx");
		XSSFWorkbook workbook = null;
		Object[][] data=null;
		try {
			FileInputStream fisExcel=new FileInputStream(excelFile);
			workbook = new XSSFWorkbook(fisExcel);
			XSSFSheet sheet=workbook.getSheet(sheetName);
			int rows=sheet.getLastRowNum();
			int colms=sheet.getRow(0).getLastCellNum();
			data= new Object[rows][colms];
			for(int i=0;i<rows;i++) {
				XSSFRow row=sheet.getRow(i+1);
				for(int j=0;j<colms;j++) {
					XSSFCell cell=row.getCell(j);
					CellType cellType=cell.getCellType();
					
					switch(cellType) {
					
					case STRING:
						data[i][j]=cell.getStringCellValue();
						break;
					case NUMERIC:
						data[i][j]=Integer.toString((int)cell.getNumericCellValue());
						break;
					case BOOLEAN:
						data[i][j]=cell.getBooleanCellValue();
						break;
					default:
						break;
					}	
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(data);
		return data;
		
	}
	
	public static String captureScreenshot(WebDriver driver, String testName) {
	
			File srcScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String destinationScreenshotPath = System.getProperty("user.dir")+"\\Screenshot\\"+testName+".png";
			try {
				FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return destinationScreenshotPath;
	}
}
