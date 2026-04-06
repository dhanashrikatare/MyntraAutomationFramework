package com.myntra.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.myntra.basetest.BaseClass;
import com.myntra.pages.HomePage;
import com.myntra.utils.ConfigReader;

import static com.myntra.basetest.KeyWord.*;

public class NavigationTests extends BaseClass {

	@Test(description = "Verify that the Myntra homepage loads successfully and the logo is visible")
	public void verifyHomepageLoads() {
		// Create an instance of the HomePage class
		HomePage homePage = new HomePage();

		// Assert that the homepage is loaded by checking if the logo is visible
		Assert.assertTrue(
			homePage.isMyntraLogoDisplayed(),
			"Home page did not load successfully. Myntra logo is not visible."
		);

	}
	
	@Test(description = "Verify that the search bar is visible on the homepage")
	public void verifySearchBarIsVisible() {
		HomePage homePage = new HomePage();
		
		Assert.assertTrue(
			homePage.isSearchBarDisplayed(),
			"Search bar is not visible on the homepage."
		);
	}
	
	@Test(description = "Verify that the wishlist icon is visible on the homepage")
	public void verifyWishlistIconIsVisible() {
		HomePage homePage = new HomePage();
		
		Assert.assertTrue(
			homePage.isWishlistIconDisplayed(),
			"Wishlist icon is not visible on the homepage."
		);
	}
	
	@Test(description = "Verify that clicking the 'Beauty' navigation link opens the Beauty page")
	public void clickBeautyNavOpensBeautyPage() {
		HomePage homepage=new HomePage();
		homepage.clickBeautyMenu();
		String currentTitle=driver.getTitle();
		Assert.assertTrue(
				currentTitle.contains("Beauty"),
				"Expected to be on the Beauty page but URL does not contain 'beauty'."
			);
		
	}
	@Test(description = "Verify that directly navigating to the Beauty page URL opens the Beauty page")
	public void directUrlOpensBeautyPage() {
	
		driver.get(ConfigReader.get("beauty.url"));
		String title=driver.getTitle();
		Assert.assertTrue(
				title.contains("Beauty"),
				"Expected beauty page but URL does not contain 'beauty'."
			);
		
		
	}

}
