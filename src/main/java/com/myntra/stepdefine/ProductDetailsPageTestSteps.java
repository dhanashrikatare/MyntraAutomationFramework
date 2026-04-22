package com.myntra.stepdefine;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.myntra.basetest.KeyWord;
import com.myntra.hooks.Hooks;
import com.myntra.pages.AddToCartPage;
import com.myntra.pages.HomePage;
import com.myntra.pages.LoginPage;
import com.myntra.pages.ProductDetails;
import com.myntra.pages.ProductListingPage;
import com.myntra.utils.WaitFor;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductDetailsPageTestSteps {

	private static final Logger LOG = LogManager.getLogger(ProductDetailsPageTestSteps.class);
	SoftAssert softly = new SoftAssert();
	LoginPage login = new LoginPage();
	HomePage search = new HomePage();
	ProductListingPage plp = new ProductListingPage();
	ProductDetails pdp = new ProductDetails();
	String expected;
	AddToCartPage cart = new AddToCartPage();

	@Given("User searches For The beauty Product")
	public void searchBeautyProducts() {
		search.enterTextOnSearchBar("lipsticks");
		search.enterPressOnSearchBar();
	}

	@When("user click on the product on plp page")
	public void productListingPageOpens() {
		expected = plp.getProductText(1);
		LOG.info("expected text of product:" + expected);
		plp.clickProduct(1);
		LOG.info("click second product:" + expected);
	}

	@And("product details page opens")
	public void productDetailsPageOpens() {
		KeyWord.switchToNewTab();
		softly.assertTrue(pdp.getPdpUrl().toLowerCase().contains("buy"));
		LOG.info("Product Detail Page Opens:");

	}

	@Then("that product should be display in the product details page and product details page opened.")
	public void productShouldBeDisplayOnPdp() {
		String actual = pdp.getBrandName();
		LOG.info("Actual brand of product:" + actual);

		String ActualResult = pdp.getTextOfBreadcrumb();
		LOG.info("Actual breadcrumb of product:" + ActualResult);
		softly.assertTrue(
				ActualResult.toLowerCase().contains(expected) || ActualResult.toLowerCase().contains("lipstick"),
				"Breadcrumb does not contain 'beauty' or 'lipstick'. Actual breadcrumb: " + ActualResult);
		softly.assertTrue(expected.contains(actual));
		softly.assertAll();
		LOG.info("passed successfully....!!!!!!");

	}

	@Then("validate the BrandName ,BreadCrumb,Product Price ,WishList Icon For Selected product on pdp page")
	public void checkTheDetailsOfProduct() {
		String actualBrand = pdp.getBrandName();
		String ActualPrice = pdp.getSellingPrice();

		softly.assertTrue(pdp.isAddToBagButtonDisplayed(),
				"Add to Bag button is not displayed on the product details page.");
		softly.assertTrue(pdp.isBreadcrumbVisible());
		softly.assertTrue(pdp.isWishListButtonDisplayed(),
				"Wishlist button is not displayed on the product details page.");
		softly.assertTrue(pdp.isProductPriceIsVisible(), "Product price is not visible on the product details page.");

		softly.assertAll();

	}

	@Then("pdp page opens and validate the GotoBag appears after adding to cart")
	public void checkGoToBagAppears() {
		try {
			pdp.selectShade(0);
			LOG.info("shade selected...!!!!");
		} catch (Exception e) {
			// No shade selection required for this product; proceed to add to bag
		}

		// Click Add to Bag and verify Go To Bag button appears
		pdp.clickaddToBagProduct();
		LOG.info("add to bag button click:");
		Assert.assertTrue(pdp.isGotoBagIsVisible(), "Go To Bag button did not appear after adding product to bag.");
		softly.assertAll();
		LOG.info("product is added to the bag:");
	}

	@And("add selected product to the wishList without login")
	public void addProductToWishListWithoutLogin() {
		// Verify wishlist button exists on PDP
		softly.assertTrue(pdp.isWishListButtonIsPresent(),
				"Wishlist button is not present on the Product Details page.");

		// Attempt to add to wishlist without login
		pdp.clickOnwishListButton();
		LOG.info("user click on wishlist button to add product to wishlist without login:");
		// Verify login prompt appears (user must login to save wishlist)

	}

	@And("user applies some filter to select product")
	public void applyMultipleFilters() {

		plp.filterByGender("Women");
		WaitFor.pageLoaded();
		plp.filterByBrand("Lakme");
		WaitFor.pageLoaded();
		plp.sortBy("popularity");
		WaitFor.pageLoaded();
		plp.filterByProductColour("Pink");
		WaitFor.pageLoaded();
		plp.filterByDiscountRange("30% and above");
		WaitFor.pageLoaded();

	}

	@Then("user on the login page")
	public void userOnTheLoginPage() {
		softly.assertTrue(login.isLoginPageDisplayed(),
				"Login popup was not displayed after clicking wishlist without login.");

		// Additional safety: either popup is shown or URL contains 'login'
		String currentUrl = KeyWord.driver.getCurrentUrl();
		softly.assertTrue(currentUrl.toLowerCase().contains("login") || login.isLoginPopUpDisplayed(),
				"After clicking wishlist without login, no login requirement was detected (no popup and URL doesn't contain 'login'). Current URL: "
						+ currentUrl);

		softly.assertAll();
		LOG.info("user on the login page....!!!!");
	}

	@Then("user enters pincodes check for the message for entered pincode")
	public void userEntersPincode() {
		pdp.clickOnPincodeButton();

		List<String> invalidPinCodes = new ArrayList();
		List<String> validPinCodes = new ArrayList<>();

		int maxAttempts = 10;
		for (int i = 0; i < maxAttempts; i++) {
			String randomPin = pdp.generateRandomPin(); // returns 6-digit string
			pdp.enterPincode(randomPin);
			pdp.clickOnPincodeCheckField();

			// Poll for up to ~5 seconds for either a delivery or invalid message
			boolean gotResponse = false;
			int retries = 0;
			while (retries < 10 && !gotResponse) {
				if (pdp.isDeliveryAvailable()) {
					String deliveryMsg = pdp.getDeliveryAvailableMessage();
					// verify expected delivery message content
					Assert.assertTrue(deliveryMsg != null && deliveryMsg.contains("Get it by"),
							"Expected delivery available message not shown for valid pincode: " + randomPin
									+ ". Actual: " + deliveryMsg);
					validPinCodes.add(randomPin);
					gotResponse = true;
				} else if (pdp.isInvalidPinMessageDisplayed()) {
					invalidPinCodes.add(randomPin);
					// optional: assert invalid message text, or just note it
					gotResponse = true;
				} else {
					KeyWord.waitForSeconds(500);
					retries++;
				}
			}

			// ensure we got some response for this attempt; otherwise log and continue
			// if a response appeared, click change to allow next pincode entry (safe-guard)
			try {
				pdp.clickChangeButton();
			} catch (Exception e) {
				// ignore if not displayed
			}
		}

		Assert.assertTrue(!invalidPinCodes.isEmpty() || !validPinCodes.isEmpty(),
				"No responses observed for any pincodes tried. Tried up to " + maxAttempts + " pins.");

		// Optional: print results
		LOG.info("Invalid pins found: " + invalidPinCodes);
		LOG.info("Valid pins found: " + validPinCodes);

	}

	@And("user leaves pincode field as empty and click on check button")
	public void leavePincodeFieldBlank() {
		pdp.clickaddToBagProduct();
		softly.assertTrue(pdp.isGotoBagIsVisible(), "go to bag is not visible");
		pdp.clickGoToBag();
		LOG.info("click on goto bag to redirect on the cart page");

		cart.clickOnPincodeButton();
		cart.clickOnPincodeCheckField();
	}

	@Then("user should see error message for that")
	public void seeErrorMsg() {
		String ActualMsg = cart.getErrorMsg();

		LOG.info("InvalidPinMessage:" + ActualMsg);
		softly.assertTrue(ActualMsg.contains("Please enter a valid pincode."));
		softly.assertAll();
	}
	@Then("user click on add multiple products to bag and see the behaviour")
	public void addProductToCartMultipleTimesAndCheckTheBagCount() {

		String parentWindow = KeyWord.driver.getWindowHandle();
		for (int i = 0; i < 5; i++) {
			plp.clickProduct(i);
			pdp.switchToChildWindow();
			pdp.clickaddToBagProduct();
			WaitFor.pageLoaded();
			int count = pdp.getBagCount();
			LOG.info("product count in the bag:" + count);
			softly.assertEquals(count, i + 1);
			KeyWord.driver.close();
			KeyWord.driver.switchTo().window(parentWindow);

		}
		softly.assertAll();
		
	}
	
}
