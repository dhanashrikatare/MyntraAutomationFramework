package com.myntra.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/Features/ProductDetailsPageTest.feature", glue = { "com.myntra.stepdefine",
		"com.myntra.hooks" }, plugin = "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", monochrome = true)
public class Runner extends AbstractTestNGCucumberTests {

}
