package com.myntra.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.myntra.basetest.KeyWord;
/** * ScreenShotUtil ─────────────── This utility class provides a method to take
 * screenshots during test execution. It captures the current state of the browser
 * and saves it as an image file.
 *
 * Why use a utility class for screenshots? - Reusability: the screenshot logic is
 * written once and can be called from any test class. - Consistency: all
 * screenshots will be saved in the same format and location.
 *
 * The getScreenShot method takes a test name as a parameter, captures the
 * screenshot, and saves it with a timestamp in the filename.
 *
 * @author Dhanashri-katare
 */

public class ScreenShotUtil {
	/**
	 * Captures a screenshot and saves it to the ./reports/ directory with a filename
	 * that includes the test name and current date/time.
	 *
	 * @param testName The name of the test for which the screenshot is being taken.
	 */
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
