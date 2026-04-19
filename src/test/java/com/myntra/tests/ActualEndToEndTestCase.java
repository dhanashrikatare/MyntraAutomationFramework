//package com.myntra.tests;
//
//import org.openqa.selenium.interactions.Actions;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import com.myntra.basetest.BaseClass;
//import com.myntra.basetest.KeyWord;
//import com.myntra.pages.AddToCartPage;
//import com.myntra.pages.HomePage;
//import com.myntra.pages.ProductDetails;
//import com.myntra.pages.ProductListingPage;
//import com.myntra.pages.SearchResultPage;
//
//import static com.myntra.basetest.KeyWord.*;
//
//public class ActualEndToEndTestCase extends BaseClass {
//	
//	
//
//	@Test(description = "Verify that a user can search for a beauty product and add it to the cart")
//	public void verifyBeautyProductIsgettingSearchAndaddedtoCart() throws InterruptedException {
//
//////	
////		HomePage homepage = new HomePage();
////		homepage.clickBeautyMenu();
//
//		SearchResultPage search = new SearchResultPage();
//		search.clickOnSearchResultsHeader();
//		search.enterTextOnSearchBar("Lipstick");
//		search.enterPressOnSearchBar();
//
//		ProductListingPage page = new ProductListingPage();
//		page.filterByGender("Women");
//		page.filterByBrand("Lakme");
//		page.sortBy("popularity");
//
//		page.filterByColour("Pink");
//
//		page.filterByDiscountRange("30% and above");
//
//		page.clickProduct(1);
//		KeyWord.switchToNewTab();
//
//		ProductDetails pdp = new ProductDetails();
////		pdp.getBrandName();
//        String ActualProduct=pdp.getProductName();
//		pdp.clickaddToBagProduct();
//		pdp.clickGoToBag();
////		
//		String src = pdp.getPageSource();
//		String ActualUrl=pdp.getCurrentUrl();
//		
//		
//        Assert.assertTrue(ActualUrl.contains("cart"));
//		Assert.assertTrue(src.contains(ActualProduct), "Product is not added to the cart");
//
//	}
//	
//	
//	
//	@Test
//	public void verifyTheSearchAndSelectedProductIsAddedToTheWishListWithoutLogin(){
//		
//		
//		SearchResultPage search = new SearchResultPage();
//		search.clickOnSearchResultsHeader();
//		search.enterTextOnSearchBar("Lipstick");
//		search.enterPressOnSearchBar();
//
//		ProductListingPage page = new ProductListingPage();
//		page.filterByGender("Women");
//		page.filterByBrand("Lakme");
//		page.sortBy("popularity");
//
//		page.filterByColour("Pink");
//
//		page.filterByDiscountRange("30% and above");
//
//		page.clickProduct(1);
//		KeyWord.switchToNewTab();
//
//		ProductDetails pdp = new ProductDetails();
////		pdp.getBrandName();
//		
//		pdp.clickOnwishListButton();
//		
//		String Actualurl=pdp.getCurrentUrl();
//		
//		
//		Assert.assertTrue(Actualurl.contains("login"));
//		
//	}
//	
//	@Test
//	public void verifyTheSearchAndSelectedProductwithBlankPinCodeField() {
//		
//		SearchResultPage search = new SearchResultPage();
//		search.clickOnSearchResultsHeader();
//		search.enterTextOnSearchBar("Lipstick");
//		search.enterPressOnSearchBar();
//
//		ProductListingPage page = new ProductListingPage();
//		page.filterByGender("Women");
//		page.filterByBrand("Lakme");
//		page.sortBy("popularity");
//
//		page.filterByColour("Pink");
//
//		page.filterByDiscountRange("30% and above");
//
//		page.clickProduct(1);
//		KeyWord.switchToNewTab();
//
//		ProductDetails pdp = new ProductDetails();
//		
//		pdp.clickaddToBagProduct();
//		pdp.clickGoToBag();
//		
//		AddToCartPage cart=new AddToCartPage();
//		cart.clickOnPincodeButton();
//		
//		cart.clickOnPincodeAndCheckField();
//		
//		String ActualMsg=cart.getErrorMsg();
//		System.out.println(ActualMsg);
//		Assert.assertTrue(ActualMsg.contains("Please enter a valid pincode."));
//		
//		
//		
//		
//	}
//	
//	
//	
//	
//
//}
