package com.myntra.pages;

import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myntra.basetest.KeyWord;
import com.myntra.utils.WaitFor;

/**
 * AddToCartPage ─────── This class represents the "Add to Cart" page of the
 * Myntra website. It contains WebElements and methods to interact with the
 * elements on that page.
 *
 * Example usage:
 *
 * public class MyTest {
 *
 * @Test public void testAddToCart() { AddToCartPage cartPage = new AddToCartPage();
 *       cartPage.clickOnCoupon(); // ... other steps ... } }
 *
 * @author Dhanashri-katare
 */
public class AddToCartPage {
	
	@FindBy(xpath="//div[text()='APPLY']")
	WebElement coupons;
	
	@FindBy(xpath="//span[text()='KGLOW']")
	WebElement couponCheckBox;
	
	@FindBy(xpath="//input[@id=\"pincode\"]")
	WebElement Pincode;
	
	@FindBy(xpath="//div[text()='PLACE ORDER']")
	WebElement placeOrderBtn;
	
	
	@FindBy(css=".CheckDeliveryModalV2-base-checkBtn")
	WebElement checkField;
	
	@FindBy(xpath="//p[@class=\"pincode-error pincode-enterPincode\"] ")
	WebElement errorMSG;
	
	@FindBy(css=".addressStripV2-base-changeBtn")
	WebElement pincodeBtn;
	
	@FindBy(xpath="//div[@class=\"itemComponents-base-messageText itemComponents-base-message \"]")
	WebElement deliveryAvailableMsg;	
	
	@FindBy(xpath="//button[text()='REMOVE']")
	WebElement removeBtn;
	
	@FindBy(xpath="//button[@class='inlinebuttonV2-base-actionButton ']")
	WebElement removePopUpBtn ;
	
	@FindBy(xpath="//div[contains(text(),'item removed from bag. ')]")
	WebElement ItemRemove;
	
	{
		PageFactory.initElements(KeyWord.driver, this);
	}

	public String getPageSource() {
		return KeyWord.getPageSource();
	}
	
	public void clickOnCoupon() {
		KeyWord.clickOn(coupons);
		
	}
	
	public void clickOnRemoveBtn() {
		WaitFor.visibilityOfelement(removeBtn);
		WaitFor.elementToBeClickaBle(removeBtn);
		
		KeyWord.clickOn(removeBtn);
		
	}
	

	public void clickOnRemovePopUpBtn() {
		WaitFor.visibilityOfelement(removePopUpBtn);
		WaitFor.elementToBeClickaBle(removePopUpBtn);
		
		KeyWord.clickOn(removePopUpBtn);
		
	}
	
	public boolean isRemovePopUpDisplayed() {
		try {
			return removePopUpBtn.isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
		
	}
	
	public void clickOnPincodeButton() {
		WaitFor.visibilityOfelement(pincodeBtn);
		WaitFor.elementToBeClickaBle(pincodeBtn);
		
		KeyWord.clickOn(pincodeBtn);
		
	}
	

	public void clickOnPincodeCheckField() {

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
		WaitFor.visibilityOfelement(errorMSG);
		return errorMSG.getText();
	}
	
	public void enterPincode(String pincode) {
		WaitFor.visibilityOfelement(Pincode);
		KeyWord.type(Pincode, pincode);
	}
	
	public boolean isDeliveryAvailable() {
		try {
			return deliveryAvailableMsg.isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
		
	}
	
	public String generateRandomPin() {
	    Random rand = new Random();
	    int pin = 100000 + rand.nextInt(900000);
	    return String.valueOf(pin);
	}
	
	public String getItemRemoveMsg() {
		WaitFor.visibilityOfelement(ItemRemove);
		return ItemRemove.getText();
	}

	public void clickOnRemoveButton() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isPlaceOrderBtnDisplayed() {
		try {
			return placeOrderBtn.isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
		
	}
	
	public void clickOnPlaceOrderBtn() {
		WaitFor.visibilityOfelement(placeOrderBtn);
		WaitFor.elementToBeClickaBle(placeOrderBtn);
		
		KeyWord.clickOn(placeOrderBtn);
		
	}
	

}
