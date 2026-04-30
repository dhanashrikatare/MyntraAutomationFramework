package com.myntra.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.myntra.basetest.KeyWord.*;
import static org.testng.Assert.assertTrue;

import java.util.List;

import com.myntra.basetest.BaseClass;
import com.myntra.basetest.KeyWord;
import com.myntra.dataprovider.LipstickDataProvider;
import com.myntra.dataprovider.MyntraSearchTest;
import com.myntra.hooks.Hooks;
import com.myntra.listener.MyListener;

import com.myntra.pages.HomePage;
import com.myntra.pages.LoginPage;
import com.myntra.pages.ProductDetails;
import com.myntra.pages.ProductListingPage;
import com.myntra.utils.ConfigReader;

import com.myntra.utils.WaitFor;

@Listeners(MyListener.class)

public class ProductListingTest extends BaseClass {
	private static final Logger LOG = LogManager.getLogger(ProductListingTest.class);
	SoftAssert softly = new SoftAssert();

	@Test(description = "test case to verify PLP page display after valid product search")
	public void toVerifyPlpPageDisplayAfterValidProductSearch() {

		HomePage srp = new HomePage();
		srp.enterTextOnSearchBar("Shampoo");
		srp.enterPressOnSearchBar();
		ProductListingPage plp = new ProductListingPage();

		String breadCrumbText = plp.getPlpBreadCrumb();
		String title = plp.getPlpTitle();
		softly.assertTrue(breadCrumbText.toLowerCase().contains("shampoo"), "Breadcrumb does not contain Shampoo");
		softly.assertTrue(title.toLowerCase().contains("shampoo"), "Page title does not contain Shampoo");
		softly.assertTrue(plp.getProductCount() > 0, "No products displayed on PLP");
		softly.assertAll();
		LOG.info("Products are Displayed..");
	}

	@Test(description = "test case to verify brand filter functionality on PLP page", dataProvider = "brandData", dataProviderClass = LipstickDataProvider.class)

	public void toVerifyBrandFilterOnPlpPageForLipsticks(String brand) {
		KeyWord.driver.get(ConfigReader.get("base.url"));
		HomePage srp = new HomePage();
		srp.enterTextOnSearchBar("Lipstick");
		srp.enterPressOnSearchBar();
		ProductListingPage plp = new ProductListingPage();
		plp.filterByBrand(brand);
		String ActualUrl = plp.getPlpUrl();

		Assert.assertTrue(ActualUrl.toLowerCase().contains(brand.toLowerCase()),
				"URL does not contain the applied brand filter");

		LOG.info("Brand filter applied successfully..");
	}

	@Test(description = "test case to verify colour filter functionality on PLP page", dataProvider = "colourDataForLipstick", dataProviderClass = MyntraSearchTest.class)

	public void toVerifyColourFilterOnPlpPageForLipsticks(String colour) {
		KeyWord.driver.get(ConfigReader.get("base.url"));
		HomePage srp = new HomePage();
		srp.enterTextOnSearchBar("Lipsticks");
		srp.enterPressOnSearchBar();
		ProductListingPage plp = new ProductListingPage();
		plp.filterByProductColour(colour);
		String ActualUrl = plp.getPlpUrl();
		// softly.assertTrue(plp.getAllProductsColours().contains(colour), "Colour
		// filter not applied correctly");
		Assert.assertTrue(ActualUrl.toLowerCase().contains(colour.toLowerCase()),
				"URL does not contain the applied colour filter");

		LOG.info("Colour filter applied successfully..");

	}

	@Test(description = "test case to verify sort by filter functionality on PLP page", dataProvider = "sortBy", dataProviderClass = MyntraSearchTest.class)

	public void verifySortByFilterOnPlpPageForLipsticks(String sortByOption) {
		KeyWord.driver.get(ConfigReader.get("base.url"));
		HomePage srp = new HomePage();
		srp.enterTextOnSearchBar("Lipsticks");
		srp.enterPressOnSearchBar();
		ProductListingPage plp = new ProductListingPage();
		plp.sortBy(sortByOption);

		String SortText = plp.getSelectedSortOption();
		Assert.assertTrue(SortText.contains(sortByOption), "Sort By filter not applied correctly");
		LOG.info("Sort By filter applied successfully..");

	}

	@Test(description = "test case to verify discount filter functionality on PLP page", dataProvider = "discountFilterForLipstick", dataProviderClass = MyntraSearchTest.class)

	public void toVerifyDiscountFilterOnPlpPageForLipsticks(String discountRange) {
		KeyWord.driver.get(ConfigReader.get("base.url"));
		HomePage srp = new HomePage();
		srp.enterTextOnSearchBar("Lipsticks");
		srp.enterPressOnSearchBar();
		ProductListingPage plp = new ProductListingPage();
		plp.filterByDiscountRange(discountRange);

		waitForSeconds(2000);
		int minDiscount = Integer.parseInt(discountRange.replace("% and above", "").trim());
		LOG.info(minDiscount);

		List<Integer> discounts = plp.getAllProductsDiscountPercentages();
		for (Integer discount : discounts) {
			Assert.assertTrue(discount >= minDiscount, "Invalid Discount Found..." + discount);
		}

		LOG.info("Discount filter applied successfully..");

	}

	@Test(dataProvider = "genderData", dataProviderClass = MyntraSearchTest.class)

	public void toVerifyFilterByGenderForLipsticks(String gender) {
		HomePage srp = new HomePage();
		srp.enterTextOnSearchBar("Lipsticks");
		srp.enterPressOnSearchBar();
		ProductListingPage plp = new ProductListingPage();
		plp.filterByGender(gender);
		String ActualUrl = plp.getPlpUrl();
		Assert.assertTrue(ActualUrl.toLowerCase().contains(gender.toLowerCase()), "gender is invalid");

	}

	@Test
	public void toVerifyProductCountChangesAfterFilter() {
		HomePage srp = new HomePage();
		srp.enterTextOnSearchBar("Lipsticks");
		srp.enterPressOnSearchBar();
		ProductListingPage plp = new ProductListingPage();
		int beforeCount = plp.getProductCounts();
		plp.filterByBrand("Lakme");
		plp.filterByColour("pink");
		int afterCount = plp.getProductCounts();

		Assert.assertTrue(beforeCount >= afterCount, "product count not change after the filter");

	}

	@Test
	public void toVerifyUserCanClickOnAnyProduct() {
		HomePage srp = new HomePage();

		srp.enterTextOnSearchBar("Lipsticks");
		srp.enterPressOnSearchBar();
		ProductListingPage plp = new ProductListingPage();
		int beforeCount = plp.getProductCounts();
		plp.filterByBrand("Lakme");
		plp.filterByColour("pink");
		plp.clickProduct(2);
		KeyWord.switchToNewTab();
		ProductDetails pdp = new ProductDetails();
		String ActualUrl = pdp.getPdpUrl();
		Assert.assertTrue(ActualUrl.contains("buy"), "product is not correctly selected");

	}

	@Test
	public void toVerifyClearAllFunctionalityOfFilterss() {
		HomePage search = new HomePage();
		search.enterTextOnSearchBar("lipsticks");
		search.enterPressOnSearchBar();

		ProductListingPage plp = new ProductListingPage();
		plp.filterByBrand("Maybelline");
		WaitFor.pageLoaded();
		plp.filterByProductColour("red");
		WaitFor.pageLoaded();
		int beforeFilterClearCount = plp.getProductCounts();
		LOG.info("before filter clear count:" + beforeFilterClearCount);

		plp.clearAllFilters();
		int AfterClearFilterCount = plp.getProductCounts();
		LOG.info("after filter clear count:" + AfterClearFilterCount);

		String url = plp.getPlpUrl();

		softly.assertFalse(url.contains("f="), "Filter query still present in URL");
		softly.assertTrue(beforeFilterClearCount <= AfterClearFilterCount,
				"after clearing filters no products are displayed which is wrong");
	}

	@Test
	public void toverifyBeautyPLP() {

		HomePage srp = new HomePage();
		srp.enterTextOnSearchBar("Lipsticks");
		srp.enterPressOnSearchBar();
		ProductListingPage plp = new ProductListingPage();
		String breadCrumbText = plp.getPlpBreadCrumb();
		int prod_count = plp.getProductCount();
		String url = plp.getPlpUrl();
		String title = plp.getPlpTitle();
		LOG.info("BreadCrumb: " + breadCrumbText);
		LOG.info("Product Count: " + prod_count);
		LOG.info("URL: " + url);
		LOG.info("Title: " + title);
		SoftAssert softly = new SoftAssert();
		softly.assertTrue(breadCrumbText.toLowerCase().contains("shampoo"), "Breadcrumb does not contain Shampoo");

		softly.assertTrue(prod_count > 0, "No products displayed on PLP");

		softly.assertTrue(url.toLowerCase().contains("shampoo"), "URL does not contain Shampoo");

		softly.assertTrue(title.toLowerCase().contains("shampoo"), "Page title does not contain Shampoo");

		softly.assertAll();

	}
}