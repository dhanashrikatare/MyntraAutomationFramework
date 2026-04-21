package com.myntra.basetest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.myntra.hooks.Hooks;
import com.myntra.utils.ConfigReader;

public class BaseClass {
	
	private static final Logger LOG = LogManager.getLogger(Hooks.class);

	/**
	 * BaseClass ─────────────── This is the base class that all test classes will
	 * extend. It contains setup and teardown methods that run before and after each
	 * test.
	 *
	 * Why use a base class? - Avoid code duplication: common setup/teardown code is
	 * written once here. - Ensure consistency: all tests will have the same setup
	 * and teardown process.
	 *
	 * Setup and teardown are handled by TestNG annotations:
	 * 
	 * @BeforeMethod - runs before each @Test method
	 * @AfterMethod - runs after each @Test method
	 */

	@BeforeMethod
	public void setUp() {
		KeyWord.openBrowser();
		LOG.info("Browser is opened..!");
		KeyWord.openUrl();
		LOG.info("Browser is opened..!");
	}

	@AfterMethod
	public void tearDown() {
		KeyWord.closeBrowser();
		LOG.info("Driver Quit successfully......!");
	}

}
