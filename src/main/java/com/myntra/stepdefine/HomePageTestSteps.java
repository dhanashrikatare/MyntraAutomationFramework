package com.myntra.stepdefine;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.myntra.pages.HomePage;
import com.myntra.pages.ProductListingPage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomePageTestSteps {
	
	
	
	SoftAssert softAssert = new SoftAssert();
	String visibleProducts;
	
	@When("user search for the Various {string}")
	public void searchValidProduct(String product) {
		visibleProducts=product;
		HomePage search =new HomePage();
		search.enterTextOnSearchBar(product);
		search.enterPressOnSearchBar();
		
		
	}
	@Then("user should see the result for the valid product")
	public void resultForTheSearchPropductOnplpPage() {
		ProductListingPage plp=new ProductListingPage();
		String AcrtualUrl=plp.getPlpUrl();
		String title=plp.getPlpTitle();
		softAssert.assertTrue(AcrtualUrl.toLowerCase().contains(visibleProducts.toLowerCase()));
		softAssert.assertTrue(title.toLowerCase().contains(visibleProducts.toLowerCase()));
		
		
	}
	
	@When("user enters numbers to search products {string}")
	public void searchFunctionalityWithNumbers(String product) {
		HomePage search =new HomePage();
		search.enterTextOnSearchBar("48964596186");
		search.enterPressOnSearchBar();
		
	}
	
	@Then("user cannot find any matches")
	public void inValidResult() {
		ProductListingPage plp=new ProductListingPage();
		String Matches = plp.getCouldNotFindAnyMatchesText();

		Assert.assertTrue(Matches.contains("We couldn't find any matches!"),
				"on invlaid search products are display which is wrong");

		
		
	}
	

}
