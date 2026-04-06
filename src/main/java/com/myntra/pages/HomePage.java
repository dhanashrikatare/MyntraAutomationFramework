package com.myntra.pages;

import java.sql.Driver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.myntra.basetest.KeyWord.*;
import com.myntra.utils.WaitFor;

public class HomePage {
	@FindBy(xpath = "//a[@class=\"myntraweb-sprite desktop-logo sprites-headerLogo \"]")
	WebElement myntraLogo;

	@FindBy(xpath = "//input[@class=\"desktop-searchBar\"]")
	WebElement searchBar;

	@FindBy(xpath = "//a[@data-group=\"beauty\"]")
	WebElement beautyMenuLink;

	@FindBy(xpath = "//a[@class=\"desktop-wishlist\"]")
	WebElement wishlistIcon;

	{
		PageFactory.initElements(driver, this);
	}

	public boolean isMyntraLogoDisplayed() {
		WaitFor.visibilityOfelement(myntraLogo);
		return myntraLogo.isDisplayed();
	}

	public boolean isSearchBarDisplayed() {
		WaitFor.visibilityOfelement(searchBar);
		return searchBar.isDisplayed();
	}

	public boolean isWishlistIconDisplayed() {
		WaitFor.visibilityOfelement(wishlistIcon);
		return wishlistIcon.isDisplayed();
	}
	public void clickBeautyMenu() {
		WaitFor.elementToBeClickaBle(beautyMenuLink);
		beautyMenuLink.click();
	}

	public String getCurrentUrl() {
		// TODO Auto-generated method stub
		return driver.getCurrentUrl();
				
	}
	
	
	

}
