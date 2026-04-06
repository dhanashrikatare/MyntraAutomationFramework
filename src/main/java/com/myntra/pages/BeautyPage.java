	package com.myntra.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.myntra.basetest.KeyWord.*;


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
