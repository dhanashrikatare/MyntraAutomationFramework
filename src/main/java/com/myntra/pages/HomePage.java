package com.myntra.pages;

import java.sql.Driver;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.myntra.basetest.KeyWord.*;

import com.myntra.basetest.KeyWord;
import com.myntra.utils.WaitFor;
/**
 * HomePage ─────── This class represents the "Home" page of the Myntra website.
 * It contains WebElements and methods to interact with the elements on that
 * page.
 *
 * Example usage:
 *
 * public class MyTest {
 *
 * @Test public void testHomePage() { HomePage homePage = new HomePage();
 *       boolean isLogoDisplayed = homePage.isMyntraLogoDisplayed(); // ... other
 *       steps ... } }
 *
 * @author Dhanashri-katare
 */
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
	
	
	@FindBy(xpath = "//input[@class=\"desktop-searchBar\"]")
	WebElement searchResultsHeader;
	
	@FindBy(xpath = "//ul[@class=\"desktop-group\"]/li")
	List<WebElement> searchResultsList;
	
	@FindBy(xpath = "//p[@class=\"index-infoBig\"]")
	WebElement CouldNotFindAnyMatches;
	
	public String getCouldNotFindAnyMatchesText() {
		WaitFor.visibilityOfelement(CouldNotFindAnyMatches);
		return CouldNotFindAnyMatches.getText();
	}
	
	
	public boolean isSearchBarVisible() {
		return true;
	}
	
	public int getCountOfSearchResults() {
		WaitFor.visibilityOfelement(searchResultsHeader);
		return searchResultsList.size();
	}
	
	
	public void clickOnSearchResultsHeader() {
		WaitFor.elementToBeClickaBle(searchResultsHeader);
		WaitFor.untilElementGotStale(searchResultsHeader);
		searchResultsHeader.click();
	}
	
	public void enterTextOnSearchBar(String text) {
		WaitFor.visibilityOfelement(searchResultsHeader);
		WaitFor.elementToBeClickaBle(searchResultsHeader);
		searchResultsHeader.sendKeys(text);
	}
	
	public void enterPressOnSearchBar() {
		WaitFor.visibilityOfelement(searchResultsHeader);
		KeyWord.typeAndEnter(searchResultsHeader, "");
//		searchResultsHeader.sendKeys(Keys.ENTER);
	}

	public boolean areProductSearchingListDisplayed() {
		for(WebElement searchResult : searchResultsList) {
			if(searchResult.isDisplayed()) {
				return true;
			}
		}
		return false;
	}



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
