	package com.myntra.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.myntra.basetest.KeyWord.*;

/**
 * BeautyPage ─────── This class represents the "Beauty" page of the Myntra
 * website. It contains WebElements and methods to interact with the elements on
 * that page.
 *
 * Example usage:
 *
 * public class MyTest {
 *
 * @Test public void testBeautyPage() { BeautyPage beautyPage = new BeautyPage();
 *       String url = beautyPage.getcurrentUrl(); // ... other steps ... } }
 *
 * @author Dhanashri-katare
 */
public class BeautyPage {
	

	By productsCount=By.xpath("//ul[@class=\"results-base\"]/li");
	

	public String getcurrentUrl() {
		// TODO Auto-generated method stub
		return  driver.getCurrentUrl();
		
	}

	public boolean areProductsVisible() {
		
		return driver.findElements(productsCount).size()>0;
	}

	public Object getPageTitle() {
		// TODO Auto-generated method stub
		return driver.getTitle();
	}

}
