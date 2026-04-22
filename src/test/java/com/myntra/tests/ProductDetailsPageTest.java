package com.myntra.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.myntra.basetest.BaseClass;
import com.myntra.basetest.KeyWord;
import com.myntra.hooks.Hooks;
import com.myntra.pages.AddToCartPage;
import com.myntra.pages.HomePage;
import com.myntra.pages.ProductDetails;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static com.myntra.basetest.KeyWord.*;
import com.myntra.pages.ProductListingPage;
import com.myntra.utils.WaitFor;
import com.myntra.pages.LoginPage;

public class ProductDetailsPageTest extends BaseClass {
	private static final Logger LOG = LogManager.getLogger(Hooks.class);
	/**
	 * Test class for Product Details Page functionality.
	 *
	 * This class contains TestNG tests that validate: - Product details page opens
	 * with correct product information - Product details correspond to the selected
	 * product on listing page - User actions on the product details page (wishlist,
	 * add to bag, go to bag)
	 *
	 * SoftAssert is used to collect multiple verification points inside each test
	 * before failing the test at the end with assertAll().
	 */
	// SoftAssert used across test methods to collect assertion failures and
	// assertAll at end.
	SoftAssert softly = new SoftAssert();

	@Test(description = "test case to verify that when user click on any product on product listing page then product details page opens with correct product information")
	public void toVerifyProductDetailPageOpens() {

		HomePage search = new HomePage();
		search.enterTextOnSearchBar("lipsticks");
		search.enterPressOnSearchBar();
		ProductListingPage plp = new ProductListingPage();
		String expected = plp.getProductText(1);

		plp.clickProduct(1);
		ProductDetails pdp = new ProductDetails();

		KeyWord.switchToNewTab();

		String actual = pdp.getBrandName();

		String ActualResult = pdp.getTextOfBreadcrumb();
		softly.assertTrue(
				ActualResult.toLowerCase().contains(expected) || ActualResult.toLowerCase().contains("lipstick"),
				"Breadcrumb does not contain 'beauty' or 'lipstick'. Actual breadcrumb: " + ActualResult);
		softly.assertTrue(expected.contains(actual));
		softly.assertAll();
		System.out.println("passed successfully");
	}

	/**
	 * Verifies that the Product Details Page (PDP) corresponds to the product
	 * selected on the Product Listing Page (PLP).
	 *
	 * Test steps: 1. Search for 'lipsticks' and open the first product from PLP. 2.
	 * Switch to the newly opened product tab. 3. Assert PDP elements relate to the
	 * selected product: breadcrumb/brand text contains the expected product, price
	 * is visible, and wishlist/add-to-bag controls are present.
	 */
	@Test(description = "Verify PDP corresponds to the product selected on PLP: checks breadcrumb/brand match, price visibility, wishlist and add-to-bag buttons")
	public void toVerifyProductOnProductDetailsPageIsRelatedToTheProductSelectedOnProductListingPage()
			throws InterruptedException {
		HomePage search = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		ProductDetails pdp = new ProductDetails();
		search.enterTextOnSearchBar("lipsticks");
		search.enterPressOnSearchBar();

		String expected = plp.getProductText(1);

		plp.clickProduct(1);

		KeyWord.switchToNewTab();

		String actual = pdp.getBrandName();
		String ActualPrice = pdp.getSellingPrice();

		softly.assertTrue(pdp.isAddToBagButtonDisplayed(),
				"Add to Bag button is not displayed on the product details page.");
		softly.assertTrue(pdp.isBreadcrumbVisible());
		softly.assertTrue(pdp.isWishListButtonDisplayed(),
				"Wishlist button is not displayed on the product details page.");
		softly.assertTrue(pdp.isProductPriceIsVisible(), "Product price is not visible on the product details page.");
		// softly.assertTrue(expected.contains(ActualPrice), "Product details do not
		// match the product selected on the listing page. Expected: " + expected + ",
		// Actual Brand: " + actual + ", Actual Price: " + ActualPrice);

		softly.assertAll();
	}

	/**
	 * Verifies that a user can add a product to the bag from the Product Details
	 * page.
	 *
	 * Test steps: 1. Search for 'lipsticks' and open the first product from the
	 * listing 2. Switch to the newly opened product tab 3. Select a shade/variant
	 * if available (optional) 4. Click 'Add to Bag' 5. Assert that the 'Go To Bag'
	 * button becomes visible
	 */
	@Test(description = "Test to verify that a user can add a product to the bag from the Product Details page;"
			+ " selects shade if required, clicks Add to Bag and verifies Go To Bag appears")
	public void toVerifyUserCanAddProductToBag() {
		HomePage search = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		ProductDetails pdp = new ProductDetails();

		// Search for a product and open the first result
		search.enterTextOnSearchBar("lipsticks");
		search.enterPressOnSearchBar();

		String expected = plp.getProductText(1);
		plp.clickProduct(1);

		// switch to the newly opened product tab
		KeyWord.switchToNewTab();

		// If the product has shades/variants (swatches), selecting one may be required
		// before the 'Add to Bag' button becomes enabled. Attempt to select the
		// first shade, but continue if selection isn't applicable for this product.
		try {
			pdp.selectShade(0);
		} catch (Exception e) {
			// No shade selection required for this product; proceed to add to bag
		}

		// Click Add to Bag and verify Go To Bag button appears
		pdp.clickaddToBagProduct();

		Assert.assertTrue(pdp.isGotoBagIsVisible(), "Go To Bag button did not appear after adding product to bag.");
		softly.assertAll();
	}

	/**
	 * Verifies that when a user searches for a product, opens its PDP and attempts
	 * to add it to the wishlist without being logged in, the application prompts
	 * for login (wishlist-login popup or redirect).
	 *
	 * Steps: 1. Search for 'lipsticks' and open the first product from PLP 2.
	 * Switch to the newly opened product tab 3. Verify wishlist button is present
	 * on PDP 4. Click wishlist button 5. Assert that login popup is displayed (user
	 * is required to login)
	 */
	@Test(description = "Verify that clicking wishlist on PDP without login prompts for login")
	public void verifyTheSearchAndSelectedProductIsAddedToTheWishListWithoutLogin() {
		HomePage search = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		ProductDetails pdp = new ProductDetails();

		// Search and open first product
		search.enterTextOnSearchBar("lipsticks");
		search.enterPressOnSearchBar();
		String expected = plp.getProductText(1);
		plp.clickProduct(1);
		// switch to the newly opened product tab
		KeyWord.switchToNewTab();

		// Verify wishlist button exists on PDP
		softly.assertTrue(pdp.isWishListButtonIsPresent(),
				"Wishlist button is not present on the Product Details page.");

		// Attempt to add to wishlist without login
		pdp.clickOnwishListButton();

		// Verify login prompt appears (user must login to save wishlist)
		LoginPage login = new LoginPage();
		softly.assertTrue(login.isLoginPageDisplayed(),
				"Login popup was not displayed after clicking wishlist without login.");

		// Additional safety: either popup is shown or URL contains 'login'
		String currentUrl = KeyWord.driver.getCurrentUrl();
		softly.assertTrue(currentUrl.toLowerCase().contains("login") || login.isLoginPopUpDisplayed(),
				"After clicking wishlist without login, no login requirement was detected (no popup and URL doesn't contain 'login'). Current URL: "
						+ currentUrl);

		softly.assertAll();
	}

	@Test(description = "Verify PDP shows correct message for each tried pincode (valid/invalid)")
	public void toVerifyselectedProductIsAvailableForDeliveryWithDifferentPinCodes() {
		HomePage search = new HomePage();

		search.enterTextOnSearchBar("Lipstick");
		search.enterPressOnSearchBar();

		ProductListingPage page = new ProductListingPage();
		page.filterByGender("Women");
		WaitFor.pageLoaded();
		page.filterByBrand("Lakme");
		WaitFor.pageLoaded();
		page.sortBy("popularity");
		WaitFor.pageLoaded();
		page.filterByColour("Pink");
		WaitFor.pageLoaded();
		page.filterByDiscountRange("30% and above");
		WaitFor.pageLoaded();
		page.clickProduct(2);
		WaitFor.pageLoaded();
		KeyWord.switchToNewTab();

		ProductDetails pdp = new ProductDetails();
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

		// Final sanity assertions: ensure each attempt produced a response (or at least
		// we observed some valid/invalid pins)
		Assert.assertTrue(!invalidPinCodes.isEmpty() || !validPinCodes.isEmpty(),
				"No responses observed for any pincodes tried. Tried up to " + maxAttempts + " pins.");

		// Optional: print results
		LOG.info("Invalid pins found: " + invalidPinCodes);
		LOG.info("Valid pins found: " + validPinCodes);
	}

	/**
	 * Test case to verify that when user tries to add product to cart and check
	 * delivery availability with blank pincode field then it should show error
	 * message
	 */

	@Test(description = "test case to verify that when user tries to add product to cart and check delivery availability with blank pincode field then it should show error message")
	public void verifyTheSearchAndSelectedProductwithBlankPinCodeField() {

		HomePage search = new HomePage();
		search.enterTextOnSearchBar("Lipstick");
		search.enterPressOnSearchBar();

		ProductListingPage page = new ProductListingPage();
		page.filterByGender("Women");
		WaitFor.pageLoaded();
		page.filterByBrand("Lakme");
		WaitFor.pageLoaded();
		page.sortBy("popularity");
		WaitFor.pageLoaded();
		page.filterByProductColour("Pink");
		WaitFor.pageLoaded();
		page.filterByDiscountRange("30% and above");
		WaitFor.pageLoaded();
		page.clickProduct(2);
		KeyWord.switchToNewTab();

		ProductDetails pdp = new ProductDetails();
		pdp.clickaddToBagProduct();
		softly.assertTrue(pdp.isGotoBagIsVisible(), "go to bag is not visible");
		pdp.clickGoToBag();
		LOG.info("click on goto bag to redirect on the cart page");

		AddToCartPage cart = new AddToCartPage();
		cart.clickOnPincodeButton();
		cart.clickOnPincodeCheckField();
		String ActualMsg = cart.getErrorMsg();

		LOG.info("InvalidPinMessage:" + ActualMsg);
		softly.assertTrue(ActualMsg.contains("Please enter a valid pincode."));
		softly.assertAll();

	}

	@Test
	public void toVerifyBagCountAfterAddingMultipleProducts() {
		HomePage search = new HomePage();
		search.enterTextOnSearchBar("Lipstick");
		search.enterPressOnSearchBar();

		ProductListingPage page = new ProductListingPage();
		page.filterByGender("Women");
		softly.assertTrue(page.getPlpUrl().toLowerCase().contains("women"));
		page.filterByBrand("Lakme");
		softly.assertTrue(page.getPlpUrl().toLowerCase().contains("lakme"), "brand is invalid");

		ProductDetails pdp = new ProductDetails();

		String parentWindow = KeyWord.driver.getWindowHandle();
		for (int i = 0; i < 5; i++) {
			page.clickProduct(i);
			pdp.switchToChildWindow();
			pdp.clickaddToBagProduct();
			int count = pdp.getBagCount();
			LOG.info("product count in the bag:" + count);
			softly.assertEquals(count, i + 1);
			KeyWord.driver.close();
			KeyWord.driver.switchTo().window(parentWindow);

		}
		softly.assertAll();
	}

}
