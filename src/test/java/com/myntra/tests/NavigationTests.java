package com.myntra.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.myntra.basetest.BaseClass;
import com.myntra.listener.MyListener;
import com.myntra.pages.HomePage;
import com.myntra.utils.ConfigReader;

import static com.myntra.basetest.KeyWord.*;

@Listeners(MyListener.class)

/**
 * NavigationTests
 *
 * This test class contains UI navigation and visibility tests for the Myntra
 * homepage and related navigation flows. It extends {@link BaseClass} which
 * provides common test setup/teardown and a shared WebDriver instance.
 *
 * Tests included:
 * - verifyHomepageLoads: Asserts the Myntra logo is visible on load.
 * - verifySearchBarIsVisible: Asserts the search bar is present on the homepage.
 * - verifyWishlistIconIsVisible: Asserts the wishlist icon is visible.
 * - clickBeautyNavOpensBeautyPage: Clicks the 'Beauty' menu and verifies the
 *   resulting page contains 'Beauty' in the title.
 * - directUrlOpensBeautyPage: Navigates directly to the configured beauty URL
 *   and verifies the title contains 'Beauty'.
 *
 * Each test should be independent and rely on the driver provided by
 * {@link BaseClass}. Use {@link com.myntra.pages.HomePage} page object methods
 * for UI interactions to keep tests readable and maintainable.
 */
public class NavigationTests extends BaseClass {

	@Test(description = "Verify that the Myntra homepage loads successfully and the logo is visible")
	public void verifyHomepageLoads() {
		// Arrange: create HomePage page object for interactions
		HomePage homePage = new HomePage();

		// Assert: the Myntra logo is displayed indicating successful homepage load
		Assert.assertTrue(
			homePage.isMyntraLogoDisplayed(),
			"Home page did not load successfully. Myntra logo is not visible."
		);

	}
	
	@Test(description = "Verify that the search bar is visible on the homepage")
	public void verifySearchBarIsVisible() {
		// Use HomePage page object to check presence of search bar
		HomePage homePage = new HomePage();
        
		Assert.assertTrue(
			homePage.isSearchBarDisplayed(),
			"Search bar is not visible on the homepage."
		);
	}
	
	@Test(description = "Verify that the wishlist icon is visible on the homepage")
	public void verifyWishlistIconIsVisible() {
		// Check wishlist icon visibility via page object
		HomePage homePage = new HomePage();
        
		Assert.assertTrue(
			homePage.isWishlistIconDisplayed(),
			"Wishlist icon is not visible on the homepage."
		);
	}
	
	@Test(description = "Verify that clicking the 'Beauty' navigation link opens the Beauty page")
	public void clickBeautyNavOpensBeautyPage() {
		// Act: click the Beauty menu using the HomePage page object
		HomePage homepage=new HomePage();
		homepage.clickBeautyMenu();

		// Assert: page title should reflect navigation to Beauty
		String currentTitle=driver.getTitle();
		Assert.assertTrue(
				currentTitle.contains("Beauty"),
				"Expected to be on the Beauty page but URL does not contain 'beauty'."
			);
        
	}
	@Test(description = "Verify that directly navigating to the Beauty page URL opens the Beauty page")
	public void directUrlOpensBeautyPage() {
		// Arrange/Act: navigate directly to the configured Beauty page URL
		driver.get(ConfigReader.get("beauty.url"));

		// Assert: title should indicate we've reached the Beauty page
		String title=driver.getTitle();
		Assert.assertTrue(
				title.contains("Beauty"),
				"Expected beauty page but URL does not contain 'beauty'."
			);
        
	}

}
