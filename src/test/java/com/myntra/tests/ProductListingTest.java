package com.myntra.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.myntra.basetest.KeyWord.*;

import com.myntra.basetest.BaseClass;
import com.myntra.pages.ProductListingPage;



public class ProductListingTest extends BaseClass {
	
	
	  private ProductListingPage listPage;
	  
	  
	 @BeforeMethod
	  public void verifyBeautyProductListingPageIsLoaded() {
		  driver.get("beauty.url");
		  
	  }
	
	
	
	

}
