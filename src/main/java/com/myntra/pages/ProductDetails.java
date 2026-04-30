package com.myntra.pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myntra.basetest.KeyWord;
import com.myntra.utils.WaitFor;
/**
 * ProductDetails ─────── This class represents the "Product Details" page of the
 * Myntra website. It contains WebElements and methods to interact with the
 * elements on that page.
 *
 * Example usage:
 *
 * public class MyTest {
 *
 * @Test public void testProductDetails() { ProductDetails productDetails = new
 *       ProductDetails(); String brandName = productDetails.getBrandName(); // ...
 *       other steps ... } }
 *
 * @author Dhanashri-katare
 */

public class ProductDetails {

	@FindBy(css = ".pdp-title")
	WebElement brandName;

	@FindBy(css = ".pdp-name")
	WebElement productName;

	@FindBy(xpath = "//span[@class='pdp-price']")
	WebElement sellingPrice;

	@FindBy(css = ".pdp-mrp s")
	WebElement originalPrice;

	@FindBy(css = ".pdp-discount")
	WebElement discountPercent;

	@FindBy(css = ".index-overallRating")
	WebElement overallRating;

	@FindBy(xpath = "//ul[@class=\"colors-shadeList colors-shadeListDesktop\"]/li")
	List<WebElement> shadeSwatch;

	@FindBy(xpath = "//div[contains(@class,'pdp-add-to-bag')]")
	WebElement addToBagButton;

	@FindBy(xpath = "//a[@class=\"pdp-goToCart pdp-add-to-bag pdp-button pdp-flex pdp-center \"]")
	WebElement goToBagButton;

	@FindBy(css=".pdp-add-to-wishlist")
	WebElement WishListButton;

	@FindBy(xpath = "//div[@class=\"pdp-productDescriptors\"]")
	WebElement productDescription;
	
	
	@FindBy(xpath="//input[@class=\"pincode-code\"]")
	WebElement Pincode;
	
	@FindBy(xpath="//input[@value=\"Check\"]")
	WebElement checkField;
	
	@FindBy(xpath="//div[@class=\"breadcrumbs-container\"]")
	WebElement breadcrumb;
	
	@FindBy(xpath="//button[@class=\"pincode-check-another-pincode pincode-button\"]")
	WebElement ChangePincodeButton;
	
	@FindBy(xpath="(//h4[@class=\"pincode-serviceabilityTitle\"])[1]")
	WebElement deliveryAvailableMsg;
	
	@FindBy(xpath="//p[@class=\"pincode-error pincode-enterPincode\"]")
	WebElement invalidPinMessage ;

	{
		PageFactory.initElements(KeyWord.driver, this);
	}

	public void clickaddToBagProduct() {
		//WaitFor.elementToBeClickaBle(addToBagButton);

		WaitFor.visibilityOfelement(addToBagButton);
		WaitFor.elementToBeClickaBle(addToBagButton);
		
		KeyWord.clickOn(addToBagButton);
		
	}
	
	public void enterPincode(String pincode) {
		WaitFor.visibilityOfelement(Pincode);
		Pincode.clear();
		KeyWord.type(Pincode, pincode);
	}
	
	public boolean isBreadcrumbVisible() {
		return KeyWord.isDisplayed(breadcrumb);
	}
	
	
	
	
	public String getTextOfBreadcrumb() {
		return KeyWord.getText(breadcrumb);
	}
	
	public String getPdpUrl() {
		return KeyWord.driver.getCurrentUrl();
	}
	
	
	public void clickOnPincodeButton() {
		WaitFor.visibilityOfelement(Pincode);
		WaitFor.elementToBeClickaBle(Pincode);
		
		KeyWord.clickOn(Pincode);
		
	}
	
	public void clickOnPincodeCheckField() {
	WaitFor.visibilityOfelement(checkField);
		WaitFor.elementToBeClickaBle(checkField);
		KeyWord.clickOn(checkField);
		
	}
	
	public void clickOnChangePincodeButton() {
		WaitFor.visibilityOfelement(ChangePincodeButton);
		WaitFor.elementToBeClickaBle(ChangePincodeButton);
		KeyWord.clickOn(ChangePincodeButton);
		
	}
	
	public String getDeliveryAvailableMessage() {
		WaitFor.visibilityOfelement(deliveryAvailableMsg);
		return KeyWord.getText(deliveryAvailableMsg);
	}
	
	public boolean isDeliveryAvailable() {
		try {
			return deliveryAvailableMsg.isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
		
	}
	
	public String getInvalidPinMessage() {
		WaitFor.visibilityOfelement(invalidPinMessage);
		return invalidPinMessage.getText();
		
	}
	
	
	public boolean isWishListButtonIsPresent() {
		return KeyWord.isDisplayed(WishListButton);
	}
	
	public void clickOnwishListButton() {
		WaitFor.visibilityOfelement(WishListButton);
		WaitFor.elementToBeClickaBle(WishListButton);
		KeyWord.clickOn(WishListButton);
		
		
	}
	
	
	
	
	public boolean isaddtobagbuttonvisible() {
		return KeyWord.isDisplayed(addToBagButton)
;	}

	public void clickGoToBag() {
		KeyWord.clickOn(goToBagButton);

	}

	public void clickWishList() {
		KeyWord.clickOn(WishListButton);
	}

	public void selectShade(int index) {
		KeyWord.clickOn(shadeSwatch.get(index));

	}

	public String getBrandName() {
		System.out.println(brandName);
		return KeyWord.getText(brandName);
		
	}

	public String getProductName() {
		return KeyWord.getText(productName);
	}

	public String getSellingPrice() {
		return KeyWord.getText(sellingPrice);

	}
	
	public String generateRandomPin() {
	    Random rand = new Random();
	    int pin = 100000 + rand.nextInt(900000);
	    return String.valueOf(pin);
	}
	
	public boolean isInvalidPinMessageDisplayed() {
		try {
			return invalidPinMessage.isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
	}

	public String getOriginalPrice() {
		return KeyWord.getText(originalPrice);
	}

	public boolean isAddTobagButtonEnabled() {
		return KeyWord.isEnabled(addToBagButton);

	}

	public boolean isGotoBagIsVisible() {
		return KeyWord.isDisplayed(goToBagButton);
	}
	
	public boolean isProductPriceIsVisible() {
		KeyWord.isDisplayed(sellingPrice);
		return KeyWord.isDisplayed(originalPrice);
	}

	public boolean isDescriptionVisible() {
		return KeyWord.isDisplayed(productDescription);
	}

	public boolean isRatingVisible() {
		return KeyWord.isDisplayed(overallRating);
	}

	public boolean isBreadcrumbCorrect() {
		String pageSource = KeyWord.getPageSource().toLowerCase();
		return pageSource.contains("beauty") || pageSource.contains("Lipstick");
	}
	
	public boolean isProductDetailsPageLoaded() {
		return KeyWord.isDisplayed(brandName) && KeyWord.isDisplayed(productName) && KeyWord.isDisplayed(sellingPrice);
	}
	
	public boolean isAddToBagButtonDisplayed() {
		return KeyWord.isDisplayed(addToBagButton);
	}
	
	public boolean isWishListButtonDisplayed() {
		return KeyWord.isDisplayed(WishListButton);
	}

	public String getCurrentUrl() {
		return  KeyWord.getCurrentUrl();
	 }
	
	public String getPageSource() {
		return KeyWord.getPageSource();
	}

	public void clickChangeButton() {
		WaitFor.visibilityOfelement(ChangePincodeButton);
		KeyWord.clickOn(ChangePincodeButton);
		
	}
	
	public void switchToChildWindow() {
		String parentWindow=KeyWord.driver.getWindowHandle();
		for(String window:KeyWord.driver.getWindowHandles()) {
			if(!window.equals(parentWindow)) {
				KeyWord.driver.switchTo().window(window);
				break;
			}
		}
	}
	
	public void switchToParentWindow() {
		String parentWindow=KeyWord.driver.getWindowHandle();
		for(String window:KeyWord.driver.getWindowHandles()) {
			KeyWord.driver.switchTo().window(window);
			break;
		}
	}

	public void closePdpPage() {
		KeyWord.driver.close();
	}
	
	public int getBagCount() {
		
		By bagCount=By.xpath("//span[contains(@class,'desktop-badge')]");
		String count=(KeyWord.driver.findElement(bagCount).getText().trim());
		// TODO Auto-generated method stub

	    if (count.isEmpty()) {
	        return 0;
	    }

		return Integer.parseInt(count);
	}


}
