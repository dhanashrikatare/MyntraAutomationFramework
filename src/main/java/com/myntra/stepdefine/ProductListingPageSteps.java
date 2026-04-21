package com.myntra.stepdefine;

import com.myntra.hooks.Hooks;
import com.myntra.pages.HomePage;
import com.myntra.pages.ProductListingPage;
import com.myntra.utils.ExcelReader;
import java.io.IOException;

import static com.myntra.basetest.KeyWord.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductListingPageSteps {
	private static final Logger LOG = LogManager.getLogger(Hooks.class);
	
	SoftAssert softly = new SoftAssert();
	String selectedColour;
	String selectedBrand;

	@When("User searches for {string}")
	public void userSearchProduct(String product) {
		HomePage search = new HomePage();
		search.enterTextOnSearchBar(product);
		search.enterPressOnSearchBar();

	}

	@And("User applies colour filter {string}")
	public void userAppliesColourFilter(String colours) {
		selectedColour = colours;
		// Log the received colour value to help debugging substitution from feature Examples
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
		HomePage search =new HomePage();
		search.enterPressOnSearchBar();
		
	}
	@Then("user should see the plp page with multiple assertion")
	public void userShouldSeePlpPage() {
		ProductListingPage plp=new ProductListingPage();
		String breadCrumbText = plp.getPlpBreadCrumb();
		String title = plp.getPlpTitle();
		softly.assertTrue(breadCrumbText.toLowerCase().contains("lipstick"), "Breadcrumb does not contain Shampoo");
		softly.assertTrue(title.toLowerCase().contains("lipstick"), "Page title does not contain Shampoo");
		softly.assertTrue(plp.getProductCount() > 0, "No products displayed on PLP");
		softly.assertAll();
		LOG.info("Products are Displayed..");
	}
	
	@And("user applies brand filter {string}")
	public void userAplliesBrandFilter(String brandParam) {
		ProductListingPage plp = new ProductListingPage();
		// Try to read brand from Excel; fall back to the step parameter if anything goes wrong
		try {
			Object[][] data = ExcelReader.readExcel(0);
			if (data != null && data.length > 0 && data[0].length > 0) {
				String brandFromExcel = String.valueOf(data[0][0]);
				selectedBrand = brandFromExcel;
				plp.filterByBrand(brandFromExcel);
			} else {
				// Excel empty, use parameter passed from feature file
				selectedBrand = brandParam;
				plp.filterByBrand(brandParam);
			}
		} catch (IOException e) {
			LOG.error("Failed to read brand from Excel, falling back to step parameter", e);
			selectedBrand = brandParam;
			plp.filterByBrand(brandParam);
		}

		waitForSeconds(2000);
	}
	@Then("user should see brand filter in url")
	public void userShouldSeeTheAppliedBrandFilter() {
		ProductListingPage plp = new ProductListingPage();
		String ActualUrl = plp.getPlpUrl();

		Assert.assertTrue(ActualUrl.toLowerCase().contains(selectedBrand.toLowerCase()),
				"URL does not contain applied brand filter");

		LOG.info("Brand filter applied successfully....!!!1" + selectedBrand);
	}

}
