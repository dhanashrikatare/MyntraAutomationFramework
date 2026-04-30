package com.myntra.stepdefine;

import org.testng.asserts.SoftAssert;

import com.myntra.pages.AddToCartPage;
import com.myntra.pages.HomePage;
import com.myntra.pages.ProductDetails;
import com.myntra.pages.ProductListingPage;
import com.myntra.utils.WaitFor;

import io.cucumber.java.en.*;

public class PlaceOrderTestSteps {
	
	



	    HomePage home = new HomePage();
	    ProductListingPage plp = new ProductListingPage();
	    ProductDetails pdp = new ProductDetails();
	    AddToCartPage cart = new AddToCartPage();

	    SoftAssert softly = new SoftAssert();

	    String expectedText;
	    String expectedBrand;
	    int expectedPrice;

	   

	    @When("user enters {string} in the search bar")
	    public void user_enters_in_search_bar(String product) {
	        home.enterTextOnSearchBar(product);
	    }

	    @When("user presses Enter on the search bar")
	    public void user_presses_enter_on_search_bar() {
	        home.enterPressOnSearchBar();
	    }

	    @When("user filters products by brand {string}")
	    public void user_filters_products_by_brand(String brand) {
	        plp.filterByBrand(brand);
	        WaitFor.pageLoaded();
	    }

	    @When("user filters products by colour {string}")
	    public void user_filters_products_by_colour(String colour) {
	        plp.filterByProductColour(colour);
	        WaitFor.pageLoaded();
	    }

	    @When("user stores details of product at index {int}")
	    public void user_stores_details_of_product(int index) {
	        expectedText = plp.getProductText(index);
	        expectedBrand = plp.getProductBrand(index);
	        expectedPrice = plp.getProductPrice(index);
	    }

	    @When("user clicks on product at index {int}")
	    public void user_clicks_on_product(int index) {
	        plp.clickProduct(index);
	    }

	    @When("user switches to child window")
	    public void user_switches_to_child_window() {
	        plp.switchToChildWindow();
	    }

	    @Then("product brand on PDP should match selected product brand")
	    public void product_brand_should_match() {
	        String actualBrand = pdp.getBrandName();
	        softly.assertEquals(actualBrand, expectedBrand);
	    }

	    @Then("breadcrumb should contains {string}")
	    public void breadcrumb_should_contain(String text) {
	        softly.assertTrue(pdp.getTextOfBreadcrumb().toLowerCase().contains(text));
	    }

	    @Then("Add to Bag button should be displayed")
	    public void add_to_bag_button_should_be_displayed() {
	        softly.assertTrue(pdp.isAddToBagButtonDisplayed());
	    }

	    @When("user clicks on Add to Bag button")
	    public void user_clicks_on_add_to_bag_button() {
	        pdp.clickaddToBagProduct();
	    }

	    @Then("Go to Bag option should be visible")
	    public void go_to_bag_option_should_be_visible() {
	        softly.assertTrue(pdp.isGotoBagIsVisible());
	    }

	    @When("user clicks on Go to Bag")
	    public void user_clicks_on_go_to_bag() {
	        pdp.clickGoToBag();
	    }

	    @Then("product price in cart should match selected product price")
	    public void product_price_should_match() {
	        softly.assertEquals(cart.getPriceOfProduct(0), expectedPrice);
	    }

	    @Then("product brand in cart should match selected product brand")
	    public void product_brand_in_cart_should_match() {
	        softly.assertEquals(cart.getProductBrand(0), expectedBrand);
	    }

	    @When("user clicks on Place Order button")
	    public void user_clicks_on_place_order_button() {
	        cart.clickOnPlaceOrderBtn();
	    }

	    @Then("user should not see {string} in cart URL")
	    public void user_should_not_see_in_cart_url(String text) {
	        softly.assertFalse(cart.getCartUrl().toLowerCase().contains(text));
	        softly.assertAll();
	    }
	}


