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

	@FindBy(xpath = "//span[text()=\"Wishlist\"]")
	WebElement wishlistIcon;
	
	@FindBy(xpath = "//span[text()='Profile']")
	WebElement profileIcon;
	
	@FindBy(xpath = "(//a[@href=\"/my/orders\"])[2]")
	WebElement myOrdersLink;

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

	public void clickOnProfileIcon() {
		WaitFor.elementToBeClickaBle(profileIcon);
		profileIcon.click();
		
		
	}

	public void clickOnMyOrders() {
		// TODO Auto-generated method stub
		WaitFor.elementToBeClickaBle(myOrdersLink);
		myOrdersLink.click();
		
	}

	public void clickOnWishlistIcon() {
		// TODO Auto-generated method stub
		WaitFor.elementToBeClickaBle(wishlistIcon);
		wishlistIcon.click();
	}
	
	
	
	

}
