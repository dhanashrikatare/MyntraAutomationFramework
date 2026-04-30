package com.myntra.hooks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.myntra.utils.ScreenShotUtil;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import static com.myntra.basetest.KeyWord.*;

public class Hooks {

	private static final Logger LOG = LogManager.getLogger(Hooks.class);

	@Before
	public void setUp() {
		openBrowser();
		LOG.info("Browser is opened..!");
		openUrl();
		LOG.info("url is launched..!");
	}

	@After
	public void tearDown(Scenario scenario) {

		if (scenario.isFailed()) {
			ScreenShotUtil.getScreenShot(scenario.getName());
			LOG.info("Screenshot captured for failed scenario");
		}

		closeBrowser();
		LOG.info("Driver is Quit successfully....!");
	}

}
