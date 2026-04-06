package com.myntra.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myntra.basetest.KeyWord;
import com.myntra.utils.WaitFor;

public class AddToCartPage {
	
	@FindBy(xpath="//div[text()='APPLY']")
	WebElement coupons;
	
	@FindBy(xpath="//span[text()='KGLOW']")
	WebElement couponCheckBox;
	
	@FindBy(xpath="//input[@id=\"pincode\"]")
	WebElement Pincode;
	
	
	@FindBy(css=".CheckDeliveryModalV2-base-checkBtn")
	WebElement checkField;
	
	@FindBy(css=".inputV2-base-errorMessage ")
	WebElement errorMSG;
	
	@FindBy(css=".addressStripV2-base-changeBtn")
	WebElement pincodeBtn;
	
	{
		PageFactory.initElements(KeyWord.driver, this);
	}

	public String getPageSource() {
		return KeyWord.getPageSource();
	}
	
	public void clickOnCoupon() {
		KeyWord.clickOn(coupons);
		
	}
	
	
	public void clickOnPincodeButton() {
		WaitFor.visibilityOfelement(pincodeBtn);
		WaitFor.elementToBeClickaBle(pincodeBtn);
		
		KeyWord.clickOn(pincodeBtn);
		
	}
	

	public void clickOnPincodeAndCheckField() {

//		WaitFor.visibilityOfelement(Pincode);
//		WaitFor.elementToBeClickaBle(Pincode);
//		
//		KeyWord.clickOn(Pincode);
//		
		WaitFor.visibilityOfelement(checkField);
		WaitFor.elementToBeClickaBle(checkField);
		KeyWord.clickOn(checkField);
		
	}
	
	public String getErrorMsg() {
		return errorMSG.getText();
	}
	
	
	

}
