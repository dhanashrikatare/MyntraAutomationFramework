package com.myntra.stepdefine;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.asserts.SoftAssert;

import com.myntra.basetest.KeyWord;
import com.myntra.pages.AddToCartPage;
import com.myntra.pages.HomePage;
import com.myntra.pages.LoginPage;
import com.myntra.pages.ProductDetails;
import com.myntra.pages.ProductListingPage;
import com.myntra.utils.ConfigReader;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CartPageTestSteps {

	private static final Logger LOG = LogManager.getLogger(CartPageTestSteps.class);
	SoftAssert softly = new SoftAssert();
	LoginPage login = new LoginPage();
	HomePage search = new HomePage();
	ProductListingPage plp = new ProductListingPage();
	ProductDetails pdp = new ProductDetails();
	String expectedProductText;
	boolean goToBag;
	boolean DisplayProduct;
	String expectedProductBrand;
	int expectedProductPrice;
	String parentWindow;
	List<String> expectedBrands = new ArrayList<>();
	List<Integer> expectedPrices = new ArrayList<>();
	AddToCartPage cart = new AddToCartPage();

	@Given("User search For The beauty Product")
	public void SearchBeautyProduct() {
		search.enterTextOnSearchBar("lipsticks");
		search.enterPressOnSearchBar();

	}

	@When("plp page opens and user clickOn the product")
	public void productListingPageOpens() {
		expectedProductBrand = plp.getProductBrand(2);
		expectedProductPrice = plp.getProductPrice(2);
		LOG.info(expectedProductPrice);
		LOG.info(expectedProductBrand);
		plp.clickProduct(2);
	}

	@And("productDetails page opens")
	public void productDetailsPageOpens() {
		KeyWord.switchToNewTab();
		String ActualUrl = pdp.getPdpUrl();
		softly.assertTrue(ActualUrl.contains("buy"));
		LOG.info("Product Detail Page Opens....!!!!");
	}

	@And("user add the product to the bag")
	public void addProductToBag() {
		pdp.clickaddToBagProduct();
		expectedProductText = pdp.getProductName();
		goToBag = pdp.isGotoBagIsVisible();
		boolean goToBag = pdp.isGotoBagIsVisible();
		softly.assertTrue(goToBag, "go to bg option is not avaialable");
		LOG.info("Product is added to bag....!!!!");
	}

	@And("user go to bag to see the product in the bag")
	public void goToBagToSeeProductIsAdded() {
		pdp.clickGoToBag();
		DisplayProduct = cart.isProductDisplayed();
		softly.assertTrue(DisplayProduct, "product is not displayed in the cart page");
		LOG.info("Product is on the cart page");
	}

	@Then("user should see the selected product is added to the bag with correct info")
	public void validateTheProductInfoWithSelectedProduct() {
		String CartPageProductBrand = cart.getProductBrand();
		int CartPageProductPrice = cart.getPriceOfProduct();
		LOG.info(CartPageProductBrand);
		LOG.info(CartPageProductPrice);
		softly.assertEquals(expectedProductBrand, CartPageProductBrand, "product is not visible on cart page");
		softly.assertEquals(expectedProductPrice, CartPageProductPrice,
				"product is not on the cart page which is your finding");
		softly.assertAll();

	}

	@And("user should add min five products to the bag")
	public void addMultipleProductsToBag() {
		 parentWindow = KeyWord.driver.getWindowHandle();
		// Add multiple products
		for (int i = 0; i < 3; i++) {

			String brand = plp.getProductBrand(i);
			int price = plp.getProductPrice(i);

			expectedBrands.add(brand);
			expectedPrices.add(price);

			plp.clickProduct(i);
			plp.switchToChildWindow();
			LOG.info("product detail page opens....");
			pdp.clickaddToBagProduct();
			LOG.info("clicked on the add to bag button....");
			softly.assertTrue(pdp.isGotoBagIsVisible(), "Go To Bag button not visible for product " + (i + 1));
			LOG.info("product is added successfully to the cart...");
			KeyWord.driver.close();
			KeyWord.driver.switchTo().window(parentWindow);
			LOG.info("switch back on the parent window....");
		}
	}

	@Then("check for the products in the bag")
	public void checkForTheProductsInTheBag() {
		KeyWord.driver.get(ConfigReader.get("cart.url"));
		// Verify all added products visible in cart
		for (int i = 0; i < 3; i++) {

			String actualBrand = cart.getProductBrand(i);
			int actualPrice = cart.getPriceOfProduct(i);

			softly.assertEquals(actualBrand, expectedBrands.get(i), "Brand mismatch for product " + (i + 1));

			softly.assertEquals(actualPrice, expectedPrices.get(i), "Price mismatch for product " + (i + 1));
		}

		softly.assertAll();
		LOG.info("all products are present on the cart page....");
	}

}
