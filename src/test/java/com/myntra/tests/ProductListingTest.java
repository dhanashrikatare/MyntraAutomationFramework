package com.myntra.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.myntra.basetest.KeyWord.*;
import static org.testng.Assert.assertTrue;

import java.util.List;

import com.myntra.basetest.BaseClass;
import com.myntra.basetest.KeyWord;
import com.myntra.dataprovider.LipstickDataProvider;
import com.myntra.dataprovider.MyntraSearchTest;

import com.myntra.pages.HomePage;
import com.myntra.pages.LoginPage;
import com.myntra.pages.ProductDetails;
import com.myntra.pages.ProductListingPage;
import com.myntra.utils.ConfigReader;

public class ProductListingTest extends BaseClass {

	SoftAssert softly = new SoftAssert();

	@Test(description = "test case to verify PLP page display after valid product search", priority = 1)
	public void toVerifyPlpPageDisplayAfterValidProductSearch(String product) {
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
		System.out.println("Products are Displayed..");
	}

	@Test(description = "test case to verify brand filter functionality on PLP page", dataProvider = "brandData", dataProviderClass = LipstickDataProvider.class, priority = 2)
	public void toVerifyBrandFilterOnPlpPageForLipsticks(String brand) {
		KeyWord.driver.get(ConfigReader.get("base.url"));
		HomePage srp = new HomePage();
		srp.enterTextOnSearchBar("Lipstick");
		srp.enterPressOnSearchBar();
		ProductListingPage plp = new ProductListingPage();
		plp.filterByBrand(brand);
		String ActualUrl = plp.getPlpUrl();
		// softly.assertTrue(plp.getAllProductsBrands().contains(brand), "Brand filte
		// not applied correctly");
		Assert.assertTrue(ActualUrl.toLowerCase().contains(brand.toLowerCase()),
				"URL does not contain the applied brand filter");

		System.out.println("Brand filter applied successfully..");
	}

	@Test(description = "test case to verify colour filter functionality on PLP page", dataProvider = "colourDataForLipstick", dataProviderClass = MyntraSearchTest.class, priority = 3)
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

		System.out.println("Colour filter applied successfully..");

	}

	@Test(description = "test case to verify sort by filter functionality on PLP page", dataProvider = "sortBy", dataProviderClass = MyntraSearchTest.class, priority = 4)
	public void verifySortByFilterOnPlpPageForLipsticks(String sortByOption) {
		KeyWord.driver.get(ConfigReader.get("base.url"));
		HomePage srp = new HomePage();
		srp.enterTextOnSearchBar("Lipsticks");
		srp.enterPressOnSearchBar();
		ProductListingPage plp = new ProductListingPage();
		plp.sortBy(sortByOption);
		String SortText = plp.getSelectedSortOption();
		Assert.assertTrue(SortText.contains(sortByOption), "Sort By filter not applied correctly");
		System.out.println("Sort By filter applied successfully..");

	}

	@Test(description = "test case to verify discount filter functionality on PLP page", dataProvider = "discountFilterForLipstick", dataProviderClass = MyntraSearchTest.class, priority = 5)
	public void toVerifyDiscountFilterOnPlpPageForLipsticks(String discountRange) {
		KeyWord.driver.get(ConfigReader.get("base.url"));
		HomePage srp = new HomePage();
		srp.enterTextOnSearchBar("Lipsticks");
		srp.enterPressOnSearchBar();
		ProductListingPage plp = new ProductListingPage();
		plp.filterByDiscountRange(discountRange);
		String ActualUrl = plp.getPlpUrl();
		int minDiscount = Integer.parseInt(discountRange.replace("% and above", "").trim());
		List<Integer> discounts = plp.getAllProductsDiscountPercentages();
		for (Integer discount : discounts) {
			Assert.assertTrue(discount >= minDiscount, "Invalid Discount Found..." + discount);
		}
		System.out.println("Discount filter applied successfully..");

	}

	@Test(dataProvider = "genderData", dataProviderClass = MyntraSearchTest.class, priority = 6)
	public void toVerifyFilterByGenderForLipsticks(String gender) {
		HomePage srp = new HomePage();
		srp.enterTextOnSearchBar("Lipsticks");
		srp.enterPressOnSearchBar();
		ProductListingPage plp = new ProductListingPage();
		plp.filterByGender(gender);
		String ActualUrl = plp.getPlpUrl();
		Assert.assertTrue(ActualUrl.toLowerCase().contains(gender.toLowerCase()), "gender is invalid");

	}

	@Test(priority = 7)
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

	@Test(priority = 8)
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

	@Test(priority = 9)
	public void toVerifyClearAllFunctionalityOfFilterss() {
		HomePage search = new HomePage();
		search.enterTextOnSearchBar("lipsticks");
		search.enterPressOnSearchBar();

		ProductListingPage plp = new ProductListingPage();
		plp.filterByBrand("Maybelline");
		plp.filterByProductColour("red");
		int beforeFilterClearCount = plp.getProductCounts();
		plp.clearAllFilters();
		int AfterClearFilterCount = plp.getProductCounts();
		String url = plp.getPlpUrl();

		softly.assertFalse(url.contains("f="), "Filter query still present in URL");
		softly.assertTrue(beforeFilterClearCount <= AfterClearFilterCount,
				"after clearing filters no products are displayed which is wrong");
	}

	@Test(priority = 10)
	public void verifyBeautyPLP() {
		HomePage srp = new HomePage();
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
		softly.assertTrue(breadCrumbText.toLowerCase().contains("shampoo"), "Breadcrumb does not contain Shampoo");

		softly.assertTrue(prod_count > 0, "No products displayed on PLP");

		softly.assertTrue(url.toLowerCase().contains("shampoo"), "URL does not contain Shampoo");

		softly.assertTrue(title.toLowerCase().contains("shampoo"), "Page title does not contain Shampoo");

		softly.assertAll();

	}

	/**
	 * Test case to verify that when user tries to see wishlist without login then
	 * it should redirect to login page
	 */

	@Test(priority = 11, description = "test case to verify that when user tries to see wishlist without login then it should redirect to login page")
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

	@Test(priority = 12, description = "test case to verify that when user tries to see orders list without login then it should redirect to login page")
	public void verifyToSeeOrdersListWithoutLogin() {
		// 3KeyWord.driver.get(ConfigReader.get("base.url"));
		HomePage home = new HomePage();
		home.clickOnProfileIcon();
		home.clickOnMyOrders();

		String Actualurl = KeyWord.driver.getCurrentUrl();

		Assert.assertTrue(Actualurl.contains("login"));

	}

}
