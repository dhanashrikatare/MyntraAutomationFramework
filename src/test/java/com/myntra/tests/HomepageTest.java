package com.myntra.tests;

import org.testng.Assert;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.myntra.basetest.BaseClass;
import com.myntra.basetest.KeyWord;
import com.myntra.dataprovider.MyntraSearchTest;
import com.myntra.pages.BeautyPage;
import com.myntra.pages.HomePage;

import com.myntra.utils.ConfigReader;

/**
 * This class contains test cases to verify the search functionality on the
 * Myntra homepage. It includes tests for valid product searches, invalid
 * product searches with numbers, invalid product searches with keywords, and
 * invalid product searches with special characters.
 */

public class HomepageTest extends BaseClass {

	@Test(priority = 1, dataProvider = "searchData", dataProviderClass = MyntraSearchTest.class, description = "test case to verify search functionality with valid products")
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
	@Test(priority = 2, description = "test case to verify search functionality with invalid products")
	public void verifyNoResultsForSearchProductsWithNumbers() {

		HomePage search = new HomePage();
		search.enterTextOnSearchBar("48964596186");
		search.enterPressOnSearchBar();
		String title = KeyWord.driver.getTitle();
		SoftAssert softAssert = new SoftAssert();
		String Matches = search.getCouldNotFindAnyMatchesText();

		Assert.assertTrue(Matches.contains("We couldn't find any matches!"),
				"on invlaid search products are display which is wrong");

	}

	/**
	 * This test case verifies the search functionality with invalid product
	 * keywords. It checks if the appropriate message is displayed when no matches
	 * are found.
	 */

	@Test(priority = 3, description = "test case to verify search functionality with invalid products")
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

	@Test(priority = 4, description = "test case to verify search functionality with invalid products with special characters")
	public void verifySearchInvalidProductsWithSpecialChar() {
		BeautyPage beauty = new BeautyPage();
		HomePage search = new HomePage();
		search.enterTextOnSearchBar("@#$%^&*()");
		search.enterPressOnSearchBar();

		String title = KeyWord.driver.getTitle();
		Assert.assertNotNull(beauty.getPageTitle());

	}

	@Test(priority = 6, description = "test case to verify search suggestions are displayed while typing")
	public void verifySuggestionsDisplayedWhileTyping() {
		HomePage search = new HomePage();
		search.enterTextOnSearchBar("Lip");
		SoftAssert softAssert = new SoftAssert();

		softAssert.assertTrue(search.getCountOfSearchResults() > 0,
				"Search suggestions should be displayed while typing.");
		softAssert.assertTrue(search.areProductSearchingListDisplayed(), "Search bar should be visible while typing.");

	}

}
