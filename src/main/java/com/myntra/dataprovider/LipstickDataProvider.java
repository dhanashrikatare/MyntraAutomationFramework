package com.myntra.dataprovider;

import java.io.IOException;

import com.myntra.utils.ExcelReader;

public class LipstickDataProvider {
	
	
	
	
	public static Object[][] getData(){
		
		try {
			ExcelReader.readExcelOfLip_Brands();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
		
	}

}
