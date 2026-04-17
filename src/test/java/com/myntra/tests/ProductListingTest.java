package com.myntra.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.myntra.basetest.KeyWord.*;

import java.util.List;

import com.myntra.basetest.BaseClass;
import com.myntra.pages.ProductListingPage;
import com.myntra.pages.SearchResultPage;

public class ProductListingTest extends BaseClass {
	
	
	public void toVerifyValidProductSearch() {
		SearchResultPage srp = new SearchResultPage();
		srp.clickOnSearchResultsHeader();
		srp.enterTextOnSearchBar("Shampoo");
		srp.enterPressOnSearchBar();
		ProductListingPage plp = new ProductListingPage();
		SoftAssert softly = new SoftAssert();
		String breadCrumbText = plp.getPlpBreadCrumb();
		String title=plp.getPlpTitle();
		softly.assertTrue(breadCrumbText.toLowerCase().contains("shampoo"),
	            "Breadcrumb does not contain Shampoo");
		softly.assertTrue(title.toLowerCase().contains("shampoo"), "Page title does not contain Shampoo");
		System.out.println("Products are Displayed..");
	}
	
	@Test
	public void toVerifyIsBrandFilterWorking() {
		SearchResultPage srp = new SearchResultPage();
		srp.clickOnSearchResultsHeader();
		srp.enterTextOnSearchBar("Shampoo");
		srp.enterPressOnSearchBar();
		ProductListingPage plp = new ProductListingPage();
		plp.filterByBrand("LOreal");
		
		Assert.assertTrue(plp.getAllProductsBrands().contains("Loreal"), "Brand filter not applied correctly");
		System.out.println("Brand filter applied successfully..");
	}
	
	
	
	
	

	@Test
	public void verifyBeautyPLPTest() {
		SearchResultPage srp = new SearchResultPage();
		srp.clickOnSearchResultsHeader();
		srp.enterTextOnSearchBar("Shampoo");
		srp.enterPressOnSearchBar();
		ProductListingPage plp = new ProductListingPage();
		String breadCrumbText = plp.getPlpBreadCrumb();
		int prod_count = plp.getProductCount();
		String url = plp.getPlpUrl();
		String title = plp.getPlpTitle();
		System.out.println("BreadCrumb: " + breadCrumbText);
		System.out.println("Product Count: " + prod_count);
		System.out.println("URL: " + url);
		System.out.println("Title: " + title);
		SoftAssert softly = new SoftAssert();
		softly.assertTrue(breadCrumbText.toLowerCase().contains("shampoo"),
		            "Breadcrumb does not contain Shampoo");

		softly.assertTrue(prod_count > 0,
		            "No products displayed on PLP");

		softly.assertTrue(url.toLowerCase().contains("shampoo"),
		            "URL does not contain Shampoo");

		softly.assertTrue(title.toLowerCase().contains("shampoo"),
		            "Page title does not contain Shampoo");
		
		softly.assertAll();

	}
	
	
	

}
