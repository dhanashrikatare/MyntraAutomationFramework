package com.myntra.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myntra.basetest.KeyWord;
import com.myntra.utils.WaitFor;

public class SearchResultPage {
	
	@FindBy(xpath = "//input[@class=\"desktop-searchBar\"]")
	WebElement searchResultsHeader;
	
	{
		
		PageFactory.initElements(KeyWord.driver, this);
	}
	
	public boolean isSearchBarVisible() {
		return true;
	}
	
	
	public void clickOnSearchResultsHeader() {
		WaitFor.elementToBeClickaBle(searchResultsHeader);
		searchResultsHeader.click();
	}
	
	public void enterTextOnSearchBar(String text) {
		WaitFor.visibilityOfelement(searchResultsHeader);
		searchResultsHeader.sendKeys(text);
	}
	
	public void enterPressOnSearchBar() {
		WaitFor.visibilityOfelement(searchResultsHeader);
		KeyWord.typeAndEnter(searchResultsHeader, "");
//		searchResultsHeader.sendKeys(Keys.ENTER);
	}




}
