package com.myntra.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.myntra.basetest.KeyWord;

public class ScreenShotUtil {
	
	public static void getScreenShot(String testName)
	{
		File src=KeyWord.driver.getScreenshotAs(OutputType.FILE);	
		
		String DateTime= new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
		
		File dest=new File("./reports/"+testName+" "+DateTime+".png");
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	

}
