package com.myntra.runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/Features", glue = { "com.myntra.stepdefine",
		"com.myntra.hooks" }, plugin = {
				"pretty",
				"html:target/cucumber-report.html",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
				}, monochrome = true,dryRun = false)
public class Runner extends AbstractTestNGCucumberTests {

//	@Override
//	@DataProvider(parallel = true)
//	public Object[][] scenarios() {
//		return super.scenarios();
//	}
}
