package com.myntra.dataprovider;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.myntra.utils.ExcelReader;

public class LipstickDataProvider {
	
	
	
	@DataProvider(name="brandData")
	public static Object[][] readExcelOfLip_Brands() throws IOException {
		return ExcelReader.readExcel(0);
	
	}
	
	@DataProvider(name="pincodeData")
	public static Object[][] readExcelOfPincodes() throws IOException {
		return ExcelReader.readExcel(1);
	}

}

