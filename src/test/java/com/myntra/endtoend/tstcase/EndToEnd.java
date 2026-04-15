package com.myntra.endtoend.tstcase;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.myntra.basetest.BaseClass;
import com.myntra.basetest.KeyWord;
import com.myntra.pages.AddToCartPage;
import com.myntra.pages.BeautyPage;
import com.myntra.pages.HomePage;
import com.myntra.pages.LoginPage;
import com.myntra.pages.ProductDetails;
import com.myntra.pages.ProductListingPage;
import com.myntra.pages.SearchResultPage;
import com.myntra.utils.ConfigReader;
import com.myntra.utils.ExcelReader;
import com.myntra.utils.WaitFor;
import com.myntra.dataprovider.*;

public class EndToEnd extends BaseClass {
	 BeautyPage beauty = new BeautyPage();
	

	@Test(priority = 1, groups = { "sanity",
			"regression" }, description = "test case to see beauty page is successfully load or not")
	public void verifyBeautyPageLoads() {

		HomePage home = new HomePage();
		home.clickBeautyMenu();
		BeautyPage beauty = new BeautyPage();
		String actualurl = beauty.getcurrentUrl();
		Assert.assertTrue(actualurl.contains("https://www.myntra.com/personal-care"));

	}
@Test(priority = 2, groups = { "sanity",
			"regression" }, description = "test case to see beauty page is successfully load or not by direct navigation")
	 public void testBeautyPageDirectNavigation() {	
		 KeyWord.driver.get(ConfigReader.get("beauty.url"));
		 BeautyPage beauty = new BeautyPage();
		 String actualurl = beauty.getcurrentUrl();
		 Assert.assertTrue(actualurl.contains("https://www.myntra.com/personal-care"));
		 
		 
	 }
	
	
	
	@Test(priority = 2, dataProvider = "searchData", dataProviderClass = MyntraSearchTest.class,
			description = "test case to verify search functionality with valid products")
	public void verifySearchValidProducts(String product) {

		SearchResultPage search = new SearchResultPage();
		search.clickOnSearchResultsHeader();
		search.enterTextOnSearchBar(product);
		search.enterPressOnSearchBar();
		String title = KeyWord.driver.getTitle();
		Assert.assertTrue(title.toLowerCase().contains(product));

	}

	@Test(priority = 3, groups = { "sanity",
			"regression" }, description = "test case to verify search functionality with invalid products")
	public void verifySearchInvalidProducts() {
		SearchResultPage search = new SearchResultPage();
		search.clickOnSearchResultsHeader();
		search.enterTextOnSearchBar("xyzabc99999nonsense");
		search.enterPressOnSearchBar();

		Assert.assertTrue(beauty.areProductsVisible(), "on invlaid search products are display which is wrong");

	}

	@Test(priority = 4, groups = { "sanity",
			"regression" }, description = "test case to verify search functionality with invalid products with special characters")
	public void verifySearchInvalidProductsWithSpecialChar() {

		SearchResultPage search = new SearchResultPage();
		search.clickOnSearchResultsHeader();
		search.enterTextOnSearchBar("@#$%^&*()");
		search.enterPressOnSearchBar();

		String title = KeyWord.driver.getTitle();
		Assert.assertNotNull(beauty.getPageTitle());

	}

	@Test(priority = 5, groups = { "sanity",
			"regression" }, description = "test case to verify search functionality with invalid products with blank fields")
	public void verifySearchInvalidProductsWithBlankFields() {
		SearchResultPage search = new SearchResultPage();
		search.clickOnSearchResultsHeader();
		search.enterTextOnSearchBar(" ");
		search.enterPressOnSearchBar();

	}
	
	


	@Test(dataProvider = "searchData", dataProviderClass = MyntraSearchTest.class, description = "test case to verify that when user search for valid product then product listing page opens and when user click on any product then product details page opens with correct product information")
	public void verifyValidProductIsgettingSearchAndProductDetailPageOpens(String product) {
		SearchResultPage search = new SearchResultPage();
		ProductListingPage plp = new ProductListingPage();
		ProductDetails pdp = new ProductDetails();
		search.clickOnSearchResultsHeader();
		search.enterTextOnSearchBar(product);
		search.enterPressOnSearchBar();
		String expected = plp.getProductText(2);
		// prodcutlisting page opens
		plp.clickProduct(2);

		KeyWord.switchToNewTab();
		// product detail page opens
		String ActualpdpName = pdp.getBrandName();

		Assert.assertTrue(expected.contains(ActualpdpName));

//		String title=KeyWord.driver.getTitle();
//		Assert.assertTrue(title.toLowerCase().contains(product));	

	}

	@Test(priority = 6, groups = { "sanity",
			"regression" }, 
			description = "test case to verify that when user search for lipsticks then product listing page opens with lipstick products only")
	public void verifyLipstickProductsIsdisplayedOnproductListingPage() {
		SearchResultPage search = new SearchResultPage();
		search.clickOnSearchResultsHeader();
		search.enterTextOnSearchBar("lipsticks");
		search.enterPressOnSearchBar();
		// product listing page is displayed after product search

		ProductListingPage plp = new ProductListingPage();
		int product_count = plp.getProductCount();
		System.out.println("lipsticks product count on first page:" + product_count);
		
		Assert.assertTrue(product_count > 0 && product_count <= 50, "Expected product count between 1 and 50, but found: " + product_count);
		

		

	}

	@Test(dataProvider = "brandData", dataProviderClass = LipstickDataProvider.class,
			description = "test case to verify that when user apply brand filter on product listing page then products related to that brand only displayed")
	public void verifyValidBrandFilterIsAppliedAndProductsDisplayedRelatedToThatBrandOnly(String brandName)
			throws InterruptedException {
		KeyWord.driver.get(ConfigReader.get("base.url"));

		SearchResultPage search = new SearchResultPage();
		search.clickOnSearchResultsHeader();
		search.enterTextOnSearchBar("lipsticks");
		search.enterPressOnSearchBar();

		ProductListingPage plp = new ProductListingPage();
		plp.filterByBrand(brandName);
		// Thread.sleep(3000);

		List<String> brandNames = plp.getAllProductsBrands();
		System.out.println(brandNames);

		int count = plp.getProductCount();
		System.out.println(count);

		for (String brand : brandNames) {
			Assert.assertEquals(brand, brandName);
		}

	}

	@Test(dataProvider = "colourData", dataProviderClass = MyntraSearchTest.class)
	public void verifyValidColourFilterIsAppliedAndProductsDisplayedRelatedToThatColourOnly(String colour)
			throws InterruptedException {
		KeyWord.driver.get(ConfigReader.get("base.url"));
		SearchResultPage search = new SearchResultPage();
		search.clickOnSearchResultsHeader();
		search.enterTextOnSearchBar("lipsticks");
		search.enterPressOnSearchBar();

		ProductListingPage plp = new ProductListingPage();
		plp.filterByProductColour(colour);

		System.out.println("colour is selected successfully");

		List<String> colourNames = plp.getAllProductsColours();
		System.out.println(colourNames);

		int count = plp.getProductCount();
		System.out.println(count);

		for (String productcolour : colourNames) {
			Assert.assertTrue(productcolour.toLowerCase().contains(colour.toLowerCase()), "Mismatch: " + productcolour);
		}

	}

	
	@Test
	public void verifyClearAllFiltersFunctionality() throws InterruptedException {
		SearchResultPage search = new SearchResultPage();
		search.clickOnSearchResultsHeader();
		search.enterTextOnSearchBar("lipsticks");
		search.enterPressOnSearchBar();

		ProductListingPage plp = new ProductListingPage();
		plp.filterByBrand("Maybelline");
		plp.filterByProductColour("red");

		plp.clearAllFilters();

		int productCountAfterClearingFilters = plp.getProductCount();

		System.out.println("product count after clearing filters: " + productCountAfterClearingFilters);

		Assert.assertTrue(productCountAfterClearingFilters > 0,
				"after clearing filters no products are displayed which is wrong");
	}

	@Test
	public void verifyProductDetailsPageOpensWithCorrectProductInformation() throws InterruptedException {
		SearchResultPage search = new SearchResultPage();
		ProductListingPage plp = new ProductListingPage();
		ProductDetails pdp = new ProductDetails();
		search.clickOnSearchResultsHeader();
		search.enterTextOnSearchBar("lipsticks");
		search.enterPressOnSearchBar();

		String expected = plp.getProductText(1);

		plp.clickProduct(1);

		KeyWord.switchToNewTab();

		String actual = pdp.getBrandName();
		
		SoftAssert softly = new SoftAssert();
		String ActualResult=pdp.getTextOfBreadcrumb();
		softly.assertTrue(ActualResult.toLowerCase().contains(expected) || ActualResult.toLowerCase().contains("lipstick"), "Breadcrumb does not contain 'beauty' or 'lipstick'. Actual breadcrumb: " + ActualResult);
		softly.assertTrue(expected.contains(actual));
		softly.assertAll();
		System.out.println("passed successfully");
	}
	
	@Test
	public void verifyProductOnProductDetailsPageIsRelatedToTheProductSelectedOnProductListingPage() throws InterruptedException {
		SearchResultPage search = new SearchResultPage();
		ProductListingPage plp = new ProductListingPage();
		ProductDetails pdp = new ProductDetails();
		search.clickOnSearchResultsHeader();
		search.enterTextOnSearchBar("lipsticks");
		search.enterPressOnSearchBar();

		String expected = plp.getProductText(1);

		plp.clickProduct(1);

		KeyWord.switchToNewTab();

		String actual = pdp.getBrandName();
		String ActualPrice=pdp.getSellingPrice();
		
		SoftAssert softly = new SoftAssert();
		softly.assertTrue(pdp.isAddToBagButtonDisplayed(), "Add to Bag button is not displayed on the product details page.");
		softly.assertTrue(pdp.isBreadcrumbVisible());
		softly.assertTrue(pdp.isWishListButtonDisplayed(), "Wishlist button is not displayed on the product details page.");
		softly.assertTrue(pdp.isProductPriceIsVisible(), "Product price is not visible on the product details page.");
		//softly.assertTrue(expected.contains(ActualPrice), "Product details do not match the product selected on the listing page. Expected: " + expected + ", Actual Brand: " + actual + ", Actual Price: " + ActualPrice);

		softly.assertAll();
	}
	
	

	@Test(dataProvider = "sortBy", dataProviderClass = MyntraSearchTest.class)
	public void verifySortingByoptionForTheProducts(String OptionText) throws InterruptedException {
		KeyWord.driver.get(ConfigReader.get("base.url"));
		SearchResultPage search = new SearchResultPage();
		ProductListingPage plp = new ProductListingPage();
		search.clickOnSearchResultsHeader();
		search.enterTextOnSearchBar("lipsticks");
		search.enterPressOnSearchBar();

		plp.sortBy(OptionText);

		List<Double> prices = plp.getAllProductsPrices();

		for (int i = 0; i < prices.size() - 1; i++) {
			Assert.assertTrue(prices.get(i) <= prices.get(i + 1),
					"Sorting error: " + prices.get(i) + " is not less than or equal to " + prices.get(i + 1));
		}
	}

	@Test
	public void verifySortingByPriceHighToLow() throws InterruptedException {
		SearchResultPage search = new SearchResultPage();
		ProductListingPage plp = new ProductListingPage();
		search.clickOnSearchResultsHeader();
		search.enterTextOnSearchBar("lipsticks");
		search.enterPressOnSearchBar();

		plp.sortBy("Price: High to Low");

		List<Double> prices = plp.getAllProductsPrices();

		for (int i = 0; i < prices.size() - 1; i++) {
			Assert.assertTrue(prices.get(i) >= prices.get(i + 1),
					"Sorting error: " + prices.get(i) + " is not greater than or equal to " + prices.get(i + 1));
		}
	}

	@Test
	public void verifyInvalidColourSearchReturnsNoProducts() {
		SearchResultPage search = new SearchResultPage();
		search.clickOnSearchResultsHeader();
		search.enterTextOnSearchBar("lipsticks");
		search.enterPressOnSearchBar();

		ProductListingPage plp = new ProductListingPage();
		plp.filterByProductColour("invalidcolour");

		int productCount = plp.getProductCount();

		Assert.assertEquals(productCount, 0, "Expected no products, but found: " + productCount);
	}
	
	/** Test case to verify that when user tries to see orders list without login then it should redirect to login page
	 * 1. Click on profile icon
	 * 2. Click on my orders
	 * 3. Verify that user is redirected to login page
	 */
	
	@Test
	public void verifyToSeeOrdersListWithoutLogin() {
		//3KeyWord.driver.get(ConfigReader.get("base.url"));
		HomePage home = new HomePage();
		home.clickOnProfileIcon();
		home.clickOnMyOrders();
		
		String Actualurl=KeyWord.driver.getCurrentUrl();
		
		Assert.assertTrue(Actualurl.contains("login"));
		
	}
	/** Test case to verify that when user tries to see wishlist without login then it should redirect to login page*/
	
	@Test
	public void verifyToSeeWishListWithoutLogin() {
		HomePage home = new HomePage();
		home.clickOnWishlistIcon();
		
		
	    LoginPage login = new LoginPage();
		Assert.assertTrue(login.isLoginPopUpDisplayed(), "Login pop-up is not displayed when trying to access the wishlist without logging in.");
		
	}
	/** Test case to verify that when user tries to add product to wishlist without login then it should redirect to login page*/
	
	@Test
	public void verifyTheSearchAndSelectedProductIsAddedToTheWishListWithoutLogin(){
		
		
		SearchResultPage search = new SearchResultPage();
		search.clickOnSearchResultsHeader();
		search.enterTextOnSearchBar("Lipstick");
		search.enterPressOnSearchBar();

		ProductListingPage page = new ProductListingPage();
		page.filterByGender("Women");
		page.filterByBrand("Lakme");
		page.sortBy("popularity");

		page.filterByColour("Pink");

		page.filterByDiscountRange("30% and above");

		page.clickProduct(1);
		KeyWord.switchToNewTab();

		ProductDetails pdp = new ProductDetails();
//		pdp.getBrandName();
		
		pdp.clickOnwishListButton();
		
		String Actualurl=pdp.getCurrentUrl();
		
		
		Assert.assertTrue(Actualurl.contains("login"));
		
	}
	
	/** Test case to verify that when user tries to add product to cart and check delivery availability with blank pincode field 
	 * then it should show error message*/
	
	@Test
	public void verifyTheSearchAndSelectedProductwithBlankPinCodeField() {
		
		SearchResultPage search = new SearchResultPage();
		search.clickOnSearchResultsHeader();
		search.enterTextOnSearchBar("Lipstick");
		search.enterPressOnSearchBar();

		ProductListingPage page = new ProductListingPage();
		page.filterByGender("Women");
		page.filterByBrand("Lakme");
		page.sortBy("popularity");

		page.filterByColour("Pink");

		page.filterByDiscountRange("30% and above");

		page.clickProduct(2);
		KeyWord.switchToNewTab();

		ProductDetails pdp = new ProductDetails();
		
		pdp.clickaddToBagProduct();
		pdp.clickGoToBag();
		
		AddToCartPage cart=new AddToCartPage();
		cart.clickOnPincodeButton();
		
		//cart.clickOnPincodeAndCheckField();
		
		String ActualMsg=cart.getErrorMsg();
		System.out.println(ActualMsg);
		Assert.assertTrue(ActualMsg.contains("Please enter a valid pincode."));
		
		
		
		
	}
	@Test(dataProvider = "pincodeData", dataProviderClass = LipstickDataProvider.class)
	public void verifyTheSearchAndSelectedProductwithvalidPinCode(String pincode) {
		KeyWord.driver.get(ConfigReader.get("base.url"));
		
		SearchResultPage search = new SearchResultPage();
		search.clickOnSearchResultsHeader();
		search.enterTextOnSearchBar("Lipstick");
		search.enterPressOnSearchBar();

		ProductListingPage page = new ProductListingPage();
//		page.filterByGender("Women");
//		page.filterByBrand("Lakme");
//		page.sortBy("popularity");
//		page.filterByColour("Pink");
//
//		page.filterByDiscountRange("30% and above");
		page.clickProduct(2);
		KeyWord.switchToNewTab();
		ProductDetails pdp = new ProductDetails();
		pdp.clickOnPincodeButton();
		pdp.enterPincode(pincode);
		pdp.clickOnPincodeCheckField();
		String deliveryMsg=pdp.getDeliveryAvailableMessage();
		System.out.println(deliveryMsg);
		Assert.assertTrue(deliveryMsg.contains("Get it by"), "Expected delivery available message not shown for valid pincode: " + pincode);

	}
	
	@Test
	public void toVerifyselectedProductIsAvailableForDeliveryWithDifferentPinCodes() {
		
		SearchResultPage search = new SearchResultPage();
		search.clickOnSearchResultsHeader();
		search.enterTextOnSearchBar("Lipstick");
		search.enterPressOnSearchBar();

		ProductListingPage page = new ProductListingPage();
		page.filterByGender("Women");
		page.filterByBrand("Lakme");
		page.sortBy("popularity");
		page.filterByColour("Pink");

		page.filterByDiscountRange("30% and above");

		page.clickProduct(2);
		KeyWord.switchToNewTab();
		
		ProductDetails pdp = new ProductDetails();
		pdp.clickOnPincodeButton();
	
		List<String> InvalidpinCodes = new ArrayList();
		boolean isdeliveryAvailable=false;
		int maxAttempts = 10; // Maximum number of attempts to find an invalid pincode
		for (int i = 0; i < maxAttempts; i++) {
		    String randomPin = pdp.generateRandomPin();
		    pdp.enterPincode(randomPin);
		    pdp.clickOnPincodeCheckField();
		    isdeliveryAvailable = pdp.isDeliveryAvailable();
		    if (pdp.isInvalidPinMessageDisplayed()) {
		        InvalidpinCodes.add(randomPin);
		        System.out.println("Invalid pincode found: " + randomPin);
		        Assert.assertTrue(pdp.isInvalidPinMessageDisplayed(),
		                "Invalid message not shown");

		            // Click Change button
		            pdp.clickChangeButton();

		    } else {
		    	String deliveryMsg=pdp.getDeliveryAvailableMessage();
		        System.out.println("Valid pincode found (skipping): " + randomPin);
		        System.out.println("Delivery available message: " + deliveryMsg);
		        Assert.assertTrue(deliveryMsg.contains("Get it by"), "Expected delivery available message not shown for valid pincode: " + randomPin);
		        isdeliveryAvailable=true;
		        break; // Exit the loop if a valid pincode is found
		    }
		}
		
		Assert.assertTrue(isdeliveryAvailable, "No valid pincode found after " + maxAttempts + " attempts. Invalid pincodes tested: " + InvalidpinCodes);
	
	}	
	
	@Test
	public void verifyThatSelectedProductIsRemoveFromTheCartAfterAddingToTheCart() {

		SearchResultPage search = new SearchResultPage();
		search.clickOnSearchResultsHeader();
		search.enterTextOnSearchBar("Lipstick");
		search.enterPressOnSearchBar();

		ProductListingPage page = new ProductListingPage();
		page.filterByGender("Women");
		page.filterByBrand("Lakme");
		page.sortBy("popularity");
		page.filterByColour("Pink");

		page.filterByDiscountRange("30% and above");

		page.clickProduct(2);
		KeyWord.switchToNewTab();
		ProductDetails pdp = new ProductDetails();
		pdp.clickaddToBagProduct();
		pdp.clickGoToBag();
		AddToCartPage cart=new AddToCartPage();
		cart.clickOnRemoveBtn();
		if(cart.isRemovePopUpDisplayed()) {
			cart.clickOnRemovePopUpBtn();;
		}
		System.out.println("successfully remove from the bag");
		String RemoveItemMsg=cart.getItemRemoveMsg();
		
		Assert.assertTrue(RemoveItemMsg.contains("item removed"), "Expected item removed message not shown after removing product from cart. Actual message: " + RemoveItemMsg);
		
		
		
		
	}
	
	@Test
	public void verifyPlaceOrderWithoutLogin() {
		SearchResultPage search = new SearchResultPage();
		search.clickOnSearchResultsHeader();
		search.enterTextOnSearchBar("Lipstick");
		search.enterPressOnSearchBar();

		ProductListingPage page = new ProductListingPage();
		page.clickProduct(2);
		KeyWord.switchToNewTab();
		ProductDetails pdp = new ProductDetails();
		pdp.clickaddToBagProduct();
		pdp.clickGoToBag();
		AddToCartPage cart=new AddToCartPage();
		if(cart.isPlaceOrderBtnDisplayed()) {
			cart.clickOnPlaceOrderBtn();
			String Actualurl=KeyWord.driver.getCurrentUrl();
			Assert.assertTrue(Actualurl.contains("login"), "Expected to be redirected to login page when trying to place order without logging in. Actual URL: " + Actualurl);
		}
		else {
			Assert.fail("Place Order button is not displayed on the cart page, cannot proceed with the test.");
		}
	}

}
