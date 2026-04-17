package com.myntra.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.myntra.basetest.BaseClass;
import com.myntra.basetest.KeyWord;
import com.myntra.dataprovider.MyntraSearchTest;
import com.myntra.pages.BeautyPage;
import com.myntra.pages.SearchResultPage;

public class SearchResultPageTest extends BaseClass {
	

	@Test(priority = 2, dataProvider = "searchData", dataProviderClass = MyntraSearchTest.class,
			description = "test case to verify search functionality with valid products")
	public void verifySearchValidProducts(String product) {

		SearchResultPage search = new SearchResultPage();
		search.clickOnSearchResultsHeader();
		search.enterTextOnSearchBar(product);
		search.enterPressOnSearchBar();
		String title = KeyWord.driver.getTitle();
		Assert.assertTrue(title.toLowerCase().contains(product));

	}

	@Test(priority = 3,  description = "test case to verify search functionality with invalid products")
	public void verifySearchInvalidProducts() {
		SearchResultPage search = new SearchResultPage();
		BeautyPage beauty = new BeautyPage();
		search.clickOnSearchResultsHeader();
		search.enterTextOnSearchBar("xyzabc99999nonsense");
		search.enterPressOnSearchBar();

		Assert.assertTrue(beauty.areProductsVisible(), "on invlaid search products are display which is wrong");

	}

	@Test(priority = 4,description = "test case to verify search functionality with invalid products with special characters")
	public void verifySearchInvalidProductsWithSpecialChar() {
		BeautyPage beauty = new BeautyPage();
		SearchResultPage search = new SearchResultPage();
		search.clickOnSearchResultsHeader();
		search.enterTextOnSearchBar("@#$%^&*()");
		search.enterPressOnSearchBar();

		String title = KeyWord.driver.getTitle();
		Assert.assertNotNull(beauty.getPageTitle());

	}

	@Test(priority = 5,  description = "test case to verify search functionality with invalid products with blank fields")
	public void verifySearchInvalidProductsWithBlankFields() {
		SearchResultPage search = new SearchResultPage();
		search.clickOnSearchResultsHeader();
		search.enterTextOnSearchBar(" ");
		search.enterPressOnSearchBar();

	}

}
