package com.myntra.stepdefine;

import static com.myntra.basetest.KeyWord.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.myntra.basetest.KeyWord;
import com.myntra.pages.HomePage;
import com.myntra.utils.ConfigReader;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NavigationTests {

	private static final Logger LOG = LogManager.getLogger(NavigationTests.class);
	SoftAssert softly = new SoftAssert();
	HomePage homePage = new HomePage();
	
	@When("user open the url")
	public void openUrl() {
		KeyWord.driver.get(ConfigReader.get("base.url"));
		LOG.info("Home Page Url Is Opened....");
	}
	
	@Then("search bar should be visible")
	public void searchBarShouldBeVisible() {
		 
		Assert.assertTrue(
			homePage.isSearchBarDisplayed(),
			"Search bar is not visible on the homepage."
		);
		LOG.info("search bar is visible....");
	}
	
	@Then("home page should be display")
	public void homePageShouldBeDisplayed() {
		Assert.assertTrue(
				homePage.isMyntraLogoDisplayed(),
				"Home page did not load successfully. Myntra logo is not visible."
			);
		LOG.info("Home Page is loaded successfully....");
	}
	
	@Then("WishListIcon should be visible")
	public void wishListIconShouldBeVisible() {
		Assert.assertTrue(
				homePage.isWishlistIconDisplayed(),
				"Wishlist icon is not visible on the homepage."
			);
		LOG.info("wishlist icon is visible....");
		
	}
	@And("click on beauty nav tab")
	public void clickOnBeatyNavTab() {
		homePage.clickBeautyMenu();
		LOG.info("successfully clicked beauty nav tab....");
	}
	
	@Then("beauty page should be open")
	public void beautyPageOpens() {
		String currentTitle=driver.getTitle();
		Assert.assertTrue(
				currentTitle.contains("Beauty"),
				"Expected to be on the Beauty page but URL does not contain 'beauty'."
			);
		LOG.info("beauty page is opened successfully....");
	}
	
	@Then("beauty page should be open by direct beaauty url")
	public void openBeatyPageByDirectUrl() {
		driver.get(ConfigReader.get("beauty.url"));

		// Assert: title should indicate we've reached the Beauty page
		String title=driver.getTitle();
		Assert.assertTrue(
				title.contains("Beauty"),
				"Expected beauty page but URL does not contain 'beauty'."
			);
		LOG.info("beauty page displays...");
	}
	
	
	
	

}
