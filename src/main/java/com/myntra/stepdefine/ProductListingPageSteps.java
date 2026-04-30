package com.myntra.stepdefine;

import com.myntra.basetest.KeyWord;
import com.myntra.hooks.Hooks;
import com.myntra.pages.HomePage;
import com.myntra.pages.ProductDetails;
import com.myntra.pages.ProductListingPage;
import com.myntra.utils.ConfigReader;
import com.myntra.utils.ExcelReader;
import com.myntra.utils.WaitFor;

import java.io.IOException;
import java.util.List;

import static com.myntra.basetest.KeyWord.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductListingPageSteps {
	private static final Logger LOG = LogManager.getLogger(ProductListingPageSteps.class);

	SoftAssert softly = new SoftAssert();
	String selectedColour;
	String selectedBrand;
	String selctedOption;
	String selectedDiscount;
	String selectedGender;
	int beforeCount;
	int afterCount;
	int beforeFilterClearCount;
	int afterClearFilterCount;
	ProductDetails pdp = new ProductDetails();
	HomePage homePage = new HomePage();
	ProductListingPage plp = new ProductListingPage();

	@When("User searches for {string}")
	public void userSearchProduct(String product) {
		HomePage search = new HomePage();
		search.enterTextOnSearchBar(product);
		search.enterPressOnSearchBar();

	}

	@And("User applies colour filter {string}")
	public void userAppliesColourFilter(String colours) {
		selectedColour = colours;
		// Log the received colour value to help debugging substitution from feature
		// Examples
		LOG.info("Received colour in step definition: '" + colours + "'");
		ProductListingPage plp = new ProductListingPage();
		plp.filterByProductColour(colours);
		waitForSeconds(2000);

	}

	@Then("User should see colour filter applied in URL")
	public void userShouldSeeTheAppliedColourFilter() {
		ProductListingPage plp = new ProductListingPage();
		String ActualUrl = plp.getPlpUrl();

		Assert.assertTrue(ActualUrl.toLowerCase().contains(selectedColour.toLowerCase()),
				"URL does not contain applied colour filter");

		LOG.info("colour filter applied successfully....!!!1" + selectedColour);

	}

	@And("hit the enter key")
	public void hitEnterKey() {
		HomePage search = new HomePage();
		search.enterPressOnSearchBar();

	}

	@Then("user should see the plp page with multiple assertion")
	public void userShouldSeePlpPage() {
		ProductListingPage plp = new ProductListingPage();
		String breadCrumbText = plp.getPlpBreadCrumb();
		String title = plp.getPlpTitle();
		softly.assertTrue(breadCrumbText.toLowerCase().contains("lipstick"), "Breadcrumb does not contain Shampoo");
		softly.assertTrue(title.toLowerCase().contains("lipstick"), "Page title does not contain Shampoo");
		softly.assertTrue(plp.getProductCount() > 0, "No products displayed on PLP");
		softly.assertAll();
		LOG.info("Products are Displayed..");
	}

	@When("user searches for products {string}")
	public void user_searches_for(String product) {

		homePage.enterTextOnSearchBar(product);
		homePage.enterPressOnSearchBar();
	}

	@When("user searches for {string}")
	public void users_searches_for(String product) {
		HomePage search = new HomePage();
		search.enterTextOnSearchBar(product);
		search.enterPressOnSearchBar();

	}
	
	
	@And("user applies brand filters {string}")
	public void user_applies_brand_filter(String brand) {
		LOG.info(" product listing page opens!!!");
		plp.filterByBrand(brand);
		WaitFor.pageLoaded();
		LOG.info("Brand is selected sucessfully...!!!" + brand);
	}

	@Then("user should see products related to {string}")
	public void user_should_see_products_related_to(String brand) {
		boolean prod_brand = plp.verifyProductsBelongToBrand(brand);
		Assert.assertTrue(prod_brand);
		LOG.info("Brand is visible sucessfully...!!!" + brand);
	}

	@When("user search for a beauty {string}")
	public void user_searches_for_beauty(String product) {
		homePage.enterTextOnSearchBar(product);
		homePage.enterPressOnSearchBar();

	}

	@And("user selects sort option {string}")
	public void user_selects_sort_option(String sortOption) {
		selctedOption = sortOption;
		plp.sortBy(sortOption);
		WaitFor.pageLoaded();

	}

	@Then("products should be sorted as per sortOption")
	public void product_should_be_sorted() {
		String SortText = plp.getSelectedSortOption();
		Assert.assertTrue(SortText.contains(selctedOption), "Sort By filter not applied correctly");
		LOG.info("Sort By filter applied successfully..");

	}

	@When("user search for the products {string}")
	public void user_search_for_lipsticks(String product) {
		KeyWord.driver.get(ConfigReader.get("base.url"));
		homePage.enterTextOnSearchBar(product);
		homePage.enterPressOnSearchBar();
	}

	@And("user applies discount filter {string}")
	public void applies_discount(String discountOption) {
		selectedDiscount = discountOption;
		plp.filterByDiscountRange(discountOption);
		WaitFor.pageLoaded();

	}

	@Then("user should see all listed products having minimum discount")
	public void user_should_see_discount_product() {
		int minDiscount = Integer.parseInt(selectedDiscount.replace("% and above", "").trim());
		LOG.info("all products discount:" + minDiscount);

		List<Integer> discounts = plp.getAllProductsDiscountPercentages();
		for (Integer discount : discounts) {
			Assert.assertTrue(discount >= minDiscount, "Invalid Discount Found..." + discount);
		}

		LOG.info("Discount filter applied successfully.." + selectedDiscount);

	}

	@When("user search for the product lipsticks")
	public void user_Search_for_the_product() {
		KeyWord.driver.get(ConfigReader.get("base.url"));
		homePage.enterTextOnSearchBar("lipsticks");
		homePage.enterPressOnSearchBar();
	}

	@And("user applies gender filter {string}")
	public void applies_gender_filter(String gender) {
		selectedGender = gender;
		plp.filterByGender(gender);
		WaitFor.pageLoaded();

	}

	@Then("user should be redirected to gender specific page for gender")
	public void user_should_be_redirected_to_gender_specific_page() {
		String ActualUrl = plp.getPlpUrl();
		Assert.assertTrue(ActualUrl.toLowerCase().contains(selectedGender.toLowerCase()), "gender is invalid");

	}

	@When("user gets product count before filter")
	public void user_gets_product_count_before_filter() {
		beforeCount = plp.getProductCounts();
	}

	@When("user applies brands filter {string}")
	public void user_applies_brands_filter(String brand) {
		plp.filterByBrand(brand);
		WaitFor.pageLoaded();
	}

	@When("user gets product count after filter")
	public void user_gets_product_count_after_filter() {
		afterCount = plp.getProductCounts();
	}

	@Then("product count should decrease or remain same after applying filters")
	public void product_count_should_decrease_or_remain_same_after_applying_filters() {
		Assert.assertTrue(beforeCount >= afterCount, "product count not change after the filter");
	}

	@When("user search for thee product Lipsticks")
	public void user_searches_for_beauty_product() {
		homePage.enterTextOnSearchBar("lipsticks");
		homePage.enterPressOnSearchBar();

	}

	@And("user applies colour filter pink")
	public void user_applies_colour_filter() {

		plp.filterByProductColour("pink");
		WaitFor.pageLoaded();
	}

	@When("users search for the product {string}")
	public void users_search_for_the_produ(String product) {
		homePage.enterTextOnSearchBar(product);
		homePage.enterPressOnSearchBar();
	}

	@And("user applies brand filter {string}")
	public void apply_brand_filter(String brand) {
		plp.filterByBrand(brand);
		WaitFor.pageLoaded();

	}

	@And("user applies colour filter {string}")
	public void apply_colour_filter(String colour) {
		plp.filterByProductColour(colour);
		WaitFor.pageLoaded();
	}

	@When("user clicks on product number {int}")
	public void user_clicks_on_product_number(Integer index) {
		plp.clickProduct(index);
	}

	@When("user switches to new tab")
	public void user_switches_to_new_tab() {
		KeyWord.switchToNewTab();
	}

	@Then("user should be redirected to product details page")
	public void user_should_be_redirected_to_product_details_page() {

		String actualUrl = pdp.getPdpUrl();
		Assert.assertTrue(actualUrl.contains("buy"), "product is not correctly selected");
	}

	@When("user search forr the product {string}")
	public void user_search_forr(String product) {
		homePage.enterTextOnSearchBar(product);
		homePage.enterPressOnSearchBar();
	}

	@And("user applie brand filter {string}")
	public void apply_brand_filters(String brand) {
		plp.filterByBrand(brand);
	    LOG.info("brand filter applied sucessfully:" +brand);

	}
	@And("user applie product colour filter {string}")
	public void apply_colour_filters(String colour) {
		plp.filterByProductColour(colour);
	    LOG.info("colour filter applies succesfully: " +colour);

	}
	@When("user gets product count before clearing filters")
	public void user_gets_product_count_before_clearing_filters() {
	    beforeFilterClearCount = plp.getProductCounts();
	    LOG.info("Before filter clear count:" +beforeCount);
	}
	

@When("user clears all filters")
public void user_clears_all_filters() {
    plp.clearAllFilters();
}

@When("user gets product count after clearing filters")
public void user_gets_product_count_after_clearing_filters() {
    afterClearFilterCount = plp.getProductCounts();
    LOG.info("after filter clear count: " +afterClearFilterCount);

}

@Then("filter query should be removed from URL")
public void filter_query_should_be_removed_from_url() {
    String url = plp.getPlpUrl();
    softly.assertFalse(url.contains("f="), "Filter query still present in URL");
}


@Then("product count should increase or remain same after clearing filters")
public void product_count_should_increase_or_remain_same_after_clearing_filters() {
    softly.assertTrue(beforeFilterClearCount <= afterClearFilterCount,
            "after clearing filters no products are displayed which is wrong");
}

@Then("breadcrumb should contain {string}")
public void breadcrumb_should_contain(String expectedText) {
    ProductListingPage plp = new ProductListingPage();
    String breadCrumbText = plp.getPlpBreadCrumb();
    LOG.info("BreadCrumb: " + breadCrumbText);
    softly.assertTrue(breadCrumbText.toLowerCase().contains(expectedText.toLowerCase()),
            "Breadcrumb does not contain " + expectedText);
}

@And("product count should be greater than {int}")
public void product_count_should_be_greater_than(Integer count) {
    ProductListingPage plp = new ProductListingPage();
    int prodCount = plp.getProductCount();
    LOG.info("Product Count: " + prodCount);
    softly.assertTrue(prodCount > count, "No products displayed on PLP");
}

@And("page URL should contain {string}")
public void page_url_should_contain(String expectedText) {
    ProductListingPage plp = new ProductListingPage();
    String url = plp.getPlpUrl();
    LOG.info("URL: " + url);
    softly.assertTrue(url.toLowerCase().contains(expectedText.toLowerCase()),
            "URL does not contain " + expectedText);
}

@And("page title should contain {string}")
public void page_title_should_contain(String expectedText) {
    ProductListingPage plp = new ProductListingPage();
    String title = plp.getPlpTitle();
    LOG.info("Title: " + title);
    softly.assertTrue(title.toLowerCase().contains(expectedText.toLowerCase()),
            "Page title does not contain " + expectedText);
}
@When("user search for the prooduct {string}")
public void user_search(String product) {
	homePage.enterTextOnSearchBar(product);
	homePage.enterPressOnSearchBar();
}



}
