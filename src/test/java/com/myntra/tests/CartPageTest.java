package com.myntra.tests;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.myntra.basetest.BaseClass;
import com.myntra.basetest.KeyWord;
import com.myntra.pages.AddToCartPage;
import com.myntra.pages.HomePage;
import com.myntra.pages.ProductDetails;
import com.myntra.pages.ProductListingPage;
import com.myntra.stepdefine.CartPageTestSteps;
import com.myntra.utils.ConfigReader;

public class CartPageTest extends BaseClass {
	private static final Logger LOG = LogManager.getLogger(CartPageTest.class);
	SoftAssert softly = new SoftAssert();

	@Test
	public void toVerifyProductIsAddedToCart() {

		HomePage search = new HomePage();
		search.enterTextOnSearchBar("lipsticks");
		search.enterPressOnSearchBar();

		ProductListingPage plp = new ProductListingPage();
		String expectedProductBrand = plp.getProductBrand(2);
		int expectedProductPrice = plp.getProductPrice(2);
		LOG.info(expectedProductPrice);
		LOG.info(expectedProductBrand);
		plp.clickProduct(2);
		plp.switchToChildWindow();
		ProductDetails pdp = new ProductDetails();
		pdp.clickaddToBagProduct();
		String expectedProductText = pdp.getProductName();
		boolean goToBag = pdp.isGotoBagIsVisible();
		softly.assertTrue(goToBag, "go to bg option is not avaialable");
		// softly.assertTrue(ActualProductBrand.contains(expectedProductText));
		pdp.clickGoToBag();
		AddToCartPage cart = new AddToCartPage();
		boolean DisplayProduct = cart.isProductDisplayed();
		String CartPageProductBrand = cart.getProductBrand();
		int CartPageProductPrice = cart.getPriceOfProduct();
		System.out.println(CartPageProductBrand);
		System.out.println(CartPageProductPrice);
		softly.assertEquals(expectedProductBrand, CartPageProductBrand, "product is not visible on cart page");
		softly.assertEquals(expectedProductPrice, CartPageProductPrice,
				"product is not on the cart page which is your finding");
		softly.assertAll();

	}

	
	
	@Test
	public void toVerifyBehaviourOfcartPageAfterAddingMultipleProducts() {

	    HomePage search = new HomePage();
	    search.enterTextOnSearchBar("lipsticks");
	    search.enterPressOnSearchBar();

	    ProductListingPage plp = new ProductListingPage();
	    ProductDetails pdp = new ProductDetails();

	    String parentWindow = KeyWord.driver.getWindowHandle();

	    List<String> expectedBrands = new ArrayList<>();
	    List<Integer> expectedPrices = new ArrayList<>();

	    // Add multiple products
	    for (int i = 0; i < 3; i++) {

	        String brand = plp.getProductBrand(i);
	        int price = plp.getProductPrice(i);

	        expectedBrands.add(brand);
	        expectedPrices.add(price);

	        plp.clickProduct(i);
	        plp.switchToChildWindow();

	        pdp.clickaddToBagProduct();

	        softly.assertTrue(
	            pdp.isGotoBagIsVisible(),
	            "Go To Bag button not visible for product " + (i + 1)
	        );

	        KeyWord.driver.close();
	        KeyWord.driver.switchTo().window(parentWindow);
	    }

	    // Open bag after adding products
	    KeyWord.driver.get(ConfigReader.get("cart.url"));

	    AddToCartPage cart = new AddToCartPage();

	    // Verify all added products visible in cart
	    for (int i = 0; i < 3; i++) {

	        String actualBrand = cart.getProductBrand(i);
	        int actualPrice = cart.getPriceOfProduct(i);

	        softly.assertEquals(
	            actualBrand,
	            expectedBrands.get(i),
	            "Brand mismatch for product " + (i + 1)
	        );

	        softly.assertEquals(
	            actualPrice,
	            expectedPrices.get(i),
	            "Price mismatch for product " + (i + 1)
	        );
	    }

	    softly.assertAll();
	}

}
