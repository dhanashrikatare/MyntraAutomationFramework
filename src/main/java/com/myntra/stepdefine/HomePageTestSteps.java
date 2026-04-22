package com.myntra.stepdefine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.myntra.basetest.KeyWord;
import com.myntra.hooks.Hooks;
import com.myntra.pages.BeautyPage;
import com.myntra.pages.HomePage;
import com.myntra.pages.LoginPage;
import com.myntra.pages.ProductListingPage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomePageTestSteps {

	HomePage search = new HomePage();
	SoftAssert softAssert = new SoftAssert();
	BeautyPage beauty = new BeautyPage();
	ProductListingPage plp = new ProductListingPage();
	HomePage home = new HomePage();
	LoginPage login = new LoginPage();
	String visibleProducts;
	private static final Logger LOG = LogManager.getLogger(Hooks.class);

	@When("user search for the Various {string}")
	public void searchValidProduct(String product) {
		visibleProducts = product;
		search.enterTextOnSearchBar(product);
		search.enterPressOnSearchBar(); 
		LOG.info("Search for the various products like:"+product);

	}

	@Then("user should see the result for the valid product")
	public void resultForTheSearchPropductOnplpPage() {
		String AcrtualUrl = plp.getPlpUrl();
		String title = plp.getPlpTitle();
		softAssert.assertTrue(AcrtualUrl.toLowerCase().contains(visibleProducts.toLowerCase()));
		softAssert.assertTrue(title.toLowerCase().contains(visibleProducts.toLowerCase()));
		LOG.info("products are displayed");

	}

	@When("user enters numbers to search products")
	public void searchFunctionalityWithNumbers() {
		search.enterTextOnSearchBar("48964596186");
		search.enterPressOnSearchBar();

	}

	@Then("user cannot find any matches")
	public void inValidResult() {

		String Matches = plp.getCouldNotFindAnyMatchesText();

		Assert.assertTrue(Matches.contains("We couldn't find any matches!"),
				"on invlaid search products are display which is wrong");

	}

	@When("user enters special character to search product")
	public void searchFunctionalityWithSpecialChar() {
		search.enterTextOnSearchBar("@#$%^&*()");
		search.enterPressOnSearchBar();

	}

	@Then("user cannot find the Beauty Products")
	public void specialCharInvalid() {
		String title = plp.getPlpTitle();
		Assert.assertNotNull(beauty.getPageTitle(), "user found the title null");
	}

	@When("user enters giberish text to search product")
	public void searchWithGiberish() {
		search.enterTextOnSearchBar("xyzabc99999nonsense");
		search.enterPressOnSearchBar();
	}

	@Then("user cannot see beauty product")
	public void resultForGiberishSearch() {

		Assert.assertTrue(beauty.areProductsVisible(), "on invalid search products are display which is wrong");

	}

	@When("user enters some keyword to search")
	public void enterPartialTextTosearch() {

		search.enterTextOnSearchBar("Lip");
	}

	@Then("user should see the autosuggestions")
	public void autoSuggestionShouldDisplay() {
		softAssert.assertTrue(search.getCountOfSearchResults() > 0,
				"Search suggestions should be displayed while typing.");
		softAssert.assertTrue(search.areProductSearchingListDisplayed(), "Search bar should be visible while typing.");

	}

	@When("user click on wishlist icon without login")
	public void seeWishListWithoutLogin() {
		home.clickOnWishlistIcon();
	}

	@Then("user should be on the login page")
	public void loginPageOpen() {
		Assert.assertTrue(login.isLoginPopUpDisplayed(),
				"Login pop-up is not displayed when trying to access the wishlist without logging in.");
		LOG.info("user redirected on the login page when Tries to see WishList without Login");

	}
	@When("user click on orders List without login")
	public void seeOrdersListWithoutLogin() {
		home.clickOnProfileIcon();
		LOG.info("Successfully click on ProfileIcon");
		home.clickOnMyOrders();
		LOG.info("Successfully click On ordersIcon");
		
	}
	@Then("user should redirected on the login page")
	public void userShouldBeRedirectedOnLoginPage() {
		String Actualurl = KeyWord.driver.getCurrentUrl();
		Assert.assertTrue(Actualurl.contains("login"));
		
		LOG.info("user redirected on the login page when Tries to see orders ist without login");
		
	}

}
