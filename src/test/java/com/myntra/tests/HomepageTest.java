package com.myntra.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.myntra.basetest.BaseClass;
import com.myntra.basetest.KeyWord;
import com.myntra.dataprovider.MyntraSearchTest;
import com.myntra.hooks.Hooks;
import com.myntra.listener.MyListener;
import com.myntra.pages.BeautyPage;
import com.myntra.pages.HomePage;
import com.myntra.pages.LoginPage;
import com.myntra.utils.ConfigReader;

/**
 * This class contains test cases to verify the search functionality on the
 * Myntra homepage. It includes tests for valid product searches, invalid
 * product searches with numbers, invalid product searches with keywords, and
 * invalid product searches with special characters.
 */
@Listeners(MyListener.class)

public class HomepageTest extends BaseClass {
	private static final Logger LOG = LogManager.getLogger(HomepageTest.class);
	SoftAssert softAssert = new SoftAssert();

	@Test(dataProvider = "searchData", dataProviderClass = MyntraSearchTest.class, description = "test case to verify search functionality with valid products")
	public void verifySearchResultsForValidBeautyProducts(String product) {
		KeyWord.driver.get(ConfigReader.get("base.url"));
		HomePage search = new HomePage();
		search.enterTextOnSearchBar(product);
		search.enterPressOnSearchBar();
		SoftAssert softAssert = new SoftAssert();
		String ActualUrl = KeyWord.driver.getCurrentUrl();
		String title = KeyWord.driver.getTitle();
		softAssert.assertTrue(ActualUrl.toLowerCase().contains(product), "URL does not contain the searched product.");
		softAssert.assertTrue(title.toLowerCase().contains(product));

	}

	/**
	 * This test case verifies the search functionality with invalid product
	 * numbers. It checks if the appropriate message is displayed when no matches
	 * are found.
	 */
	@Test(description = "test case to verify search functionality with invalid products")
	public void verifyNoResultsForSearchProductsWithNumbers() {

		HomePage search = new HomePage();
		search.enterTextOnSearchBar("48964596186");
		search.enterPressOnSearchBar();
		String title = KeyWord.driver.getTitle();
		String Matches = search.getCouldNotFindAnyMatchesText();

		Assert.assertTrue(Matches.contains("We couldn't find any matches!"),
				"on invlaid search products are display which is wrong");

	}

	/**
	 * This test case verifies the search functionality with invalid product
	 * keywords. It checks if the appropriate message is displayed when no matches
	 * are found.
	 */

	@Test( description = "test case to verify search functionality with invalid products")
	public void verifySearchProductsWithInvalidKeyword() {
		HomePage search = new HomePage();
		BeautyPage beauty = new BeautyPage();
		search.enterTextOnSearchBar("xyzabc99999nonsense");
		search.enterPressOnSearchBar();

		Assert.assertTrue(beauty.areProductsVisible(), "on invlaid search products are display which is wrong");

	}

	/**
	 * This test case verifies the search functionality with invalid products
	 * containing special characters. It checks if the appropriate message is
	 * displayed when no matches are found.
	 */

	@Test( description = "test case to verify search functionality with invalid products with special characters")
	public void verifySearchInvalidProductsWithSpecialChar() {
		BeautyPage beauty = new BeautyPage();
		HomePage search = new HomePage();
		search.enterTextOnSearchBar("@#$%^&*()");
		search.enterPressOnSearchBar();

		String title = KeyWord.driver.getTitle();
		Assert.assertNotNull(beauty.getPageTitle());

	}

	@Test( description = "test case to verify search suggestions are displayed while typing")
	public void verifySuggestionsDisplayedWhileTyping() {
		HomePage search = new HomePage();
		search.enterTextOnSearchBar("Lip");
		

		softAssert.assertTrue(search.getCountOfSearchResults() > 0,
				"Search suggestions should be displayed while typing.");
		softAssert.assertTrue(search.areProductSearchingListDisplayed(), "Search bar should be visible while typing.");

	}

	/**
	 * Test case to verify that when user tries to see wishlist without login then
	 * it should redirect to login page
	 */



	@Test( description = "test case to verify that when user tries to see wishlist without login then it should redirect to login page")


	public void verifyToSeeWishListWithoutLogin() {
		HomePage home = new HomePage();
		home.clickOnWishlistIcon();

		LoginPage login = new LoginPage();
		Assert.assertTrue(login.isLoginPopUpDisplayed(),
				"Login pop-up is not displayed when trying to access the wishlist without logging in.");

	}

	/**
	 * Test case to verify that when user tries to see orders list without login
	 * then it should redirect to login page 1. Click on profile icon 2. Click on my
	 * orders 3. Verify that user is redirected to login page
	 */



	@Test( description = "test case to verify that when user tries to see orders list without login then it should redirect to login page")
	public void verifyToSeeOrdersListWithoutLogin() {
		// 3KeyWord.driver.get(ConfigReader.get("base.url"));
		HomePage home = new HomePage();
		home.clickOnProfileIcon();
		LOG.info("Successfully click on ProfileIcon");
		home.clickOnMyOrders();
		LOG.info("Successfully click On ordersIcon");
		String Actualurl = KeyWord.driver.getCurrentUrl();

		Assert.assertTrue(Actualurl.contains("login"));
		LOG.info("user redirected on the login page when Tries to see orders ist without login");


	}

}



