package com.myntra.listener;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.myntra.utils.ScreenShotUtil;

public class MyListener implements ITestListener {
	
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ScreenShotUtil.getScreenShot(result.getName());
		
	
	}

}
