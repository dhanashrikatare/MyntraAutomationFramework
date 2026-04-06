package com.myntra.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelReader {
	
	@DataProvider(name="brandData")
	public static Object[][] readExcelOfLip_Brands() throws IOException{
		
		FileInputStream file=new FileInputStream("C:\\Users\\Hp\\Downloads\\Lipsticks_Brands.xlsx");
		XSSFWorkbook book=new XSSFWorkbook(file);
		
		XSSFSheet sheet=book.getSheetAt(0);
		
		int rows=sheet.getPhysicalNumberOfRows();
		
		int cols=sheet.getRow(0).getPhysicalNumberOfCells();
		Object[][] data=new Object[rows][cols];
		
	   for(int i=0;i<rows;i++) {
		   
		   XSSFRow row=sheet.getRow(i);
		   
		   for(int j=0;j<cols;j++) {
			   XSSFCell cell=row.getCell(j);
			   
			   switch (cell.getCellType()) {
			case STRING: 
				String value=cell.getStringCellValue();
				data[i][j] = value;
				System.out.println(value);
				break;
			
			case NUMERIC:
				int value1=(int) cell.getNumericCellValue();
				data[i][j] = value1;
				System.out.println(value1);
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + cell.getCellType());
			}
			   
			   
		   }
		   
		   
		   
		   
	   }
	   return data;
		
		
		
		
	}
	
	
		
	}
	
	
	


