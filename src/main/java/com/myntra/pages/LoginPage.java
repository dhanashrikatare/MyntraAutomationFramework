package com.myntra.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myntra.basetest.KeyWord;
import com.myntra.utils.WaitFor;

public class LoginPage {
	
	@FindBy(css = ".wishlistLogin-button")
	WebElement  wishListLoginPopUp;
	
	
	{
		
		PageFactory.initElements(KeyWord.driver, this);
	}
	
	
	public boolean isLoginPopUpDisplayed() {
		WaitFor.visibilityOfelement(wishListLoginPopUp);
		try {
		return KeyWord.isDisplayed(wishListLoginPopUp);
		}
		catch(Exception e) {
			return false;
		}
		
	}
	
	
	

}
