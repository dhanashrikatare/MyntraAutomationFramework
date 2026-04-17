package com.myntra.stepdefine;

import com.myntra.hooks.Hooks;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class BeautyPageStepDefinitions extends Hooks {
	
	@Given("user is on the home page")
	public void homePageOpens() {
		
		
	}

	@When("User is on the Beauty Page")
	public void openBeautyPage() {
		System.out.println("Opening the Beauty Page");
	}
	

}
