package com.myntra.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.myntra.basetest.BaseClass;
import com.myntra.pages.AddToCartPage;
import com.myntra.pages.HomePage;
import com.myntra.pages.ProductDetails;
import com.myntra.pages.ProductListingPage;
import com.myntra.stepdefine.ProductListingPageSteps;
import com.myntra.utils.WaitFor;

public class PlaceOrder extends BaseClass{
	private static final Logger LOG = LogManager.getLogger(PlaceOrder.class);
	SoftAssert softly = new SoftAssert();

	@Test
	public void toVerifyUserCanPlacedOrderSuccessfully() {
		HomePage home = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		ProductDetails pdp = new ProductDetails();
		AddToCartPage cart = new AddToCartPage();
		home.enterTextOnSearchBar("Lipsticks");
		home.enterPressOnSearchBar();
		
		plp.filterByBrand("Lakme");
		WaitFor.pageLoaded();
		LOG.info("brand is selected");
		plp.filterByProductColour("Red");
		WaitFor.pageLoaded();
		LOG.info("colour is selected");
		String expectedtext=plp.getProductText(2);
		String expectedProductBrand=plp.getProductBrand(2);
		int expectedPrice=plp.getProductPrice(2);
		plp.clickProduct(2);
		
		plp.switchToChildWindow();
		
		
		String ActualBrand=pdp.getBrandName();
		softly.assertEquals(expectedProductBrand,ActualBrand,"actual and expected product brand name are not matching");
		
		String breadCrumb=pdp.getTextOfBreadcrumb();
		softly.assertTrue(breadCrumb.toLowerCase().contains("lipstick"));
		softly.assertTrue(pdp.isAddToBagButtonDisplayed(),"add to bag button is not displayed");
		pdp.clickaddToBagProduct();
		softly.assertTrue(pdp.isGotoBagIsVisible(),"product is not added to cart");
		pdp.clickGoToBag();
		LOG.info("product added to bag successfully");
		int actualPrice=cart.getPriceOfProduct(0);
		String cartProductBrand=cart.getProductBrand(0);
		softly.assertEquals(actualPrice, expectedPrice,"not matching");
		softly.assertEquals(expectedProductBrand, cartProductBrand,"not matching");
		cart.clickOnPlaceOrderBtn();
		softly.assertFalse(cart.getCartUrl().toLowerCase().contains("order is placed"));
		
		softly.assertAll();

	}

}
