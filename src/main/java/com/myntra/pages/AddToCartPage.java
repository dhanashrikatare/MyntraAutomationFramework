package com.myntra.pages;

import java.util.Random;
import java.util.List;

import org.openqa.selenium.By;
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
 * @Test public void testAddToCart() { AddToCartPage cartPage = new
 *       AddToCartPage(); cartPage.clickOnCoupon(); // ... other steps ... } }
 *
 * @author Dhanashri-katare
 */
public class AddToCartPage {
	@FindBy(xpath = "//div[@class=\"itemContainer-base-details\"]")
	WebElement ProductName;

	@FindBy(xpath = "//div[text()='APPLY']")
	WebElement coupons;

	@FindBy(xpath = "//span[text()='KGLOW']")
	WebElement couponCheckBox;

	@FindBy(xpath = "//input[@id=\"pincode\"]")
	WebElement Pincode;

	@FindBy(xpath = "//div[text()='PLACE ORDER']")
	WebElement placeOrderBtn;

	@FindBy(css = ".CheckDeliveryModalV2-base-checkBtn")
	WebElement checkField;


	@FindBy(xpath = "//div[@class=\"inputV2-base-errorMessage \"] ")

	WebElement errorMSG;

	@FindBy(css = ".addressStripV2-base-changeBtn")
	WebElement pincodeBtn;

	@FindBy(xpath = "//div[@class=\"itemComponents-base-messageText itemComponents-base-message \"]")
	WebElement deliveryAvailableMsg;

	@FindBy(xpath = "//button[text()='REMOVE']")
	WebElement removeBtn;

	@FindBy(xpath = "//button[@class='inlinebuttonV2-base-actionButton ']")
	WebElement removePopUpBtn;

	@FindBy(xpath = "//div[contains(text(),'item removed from bag. ')]")
	WebElement ItemRemove;

	@FindBy(xpath = "//div[@class=\"itemContainer-base-brand\"]")
	WebElement productBrand;

	@FindBy(xpath = "//span[@class=\"itemComponents-base-rupeeBoldIcon\"]/parent::div")
	WebElement productPrice;

	@FindBy(xpath = "//div[@class='itemContainer-base-brand']")
	List<WebElement> productBrands;

//	@FindBy(xpath = "//span[@class=\"itemComponents-base-rupeeBoldIcon\"]/parent::div")
//	List<WebElement> productPrices;


	@FindBy(xpath = "//div[@class=\"cartFillerProduct-base-cartFillerProduct\"]")
	WebElement moreProducts;
	
	@FindBy(xpath="//div[@class=\"bulkActionStrip-selectionIcon\"]")
	WebElement CheckBox;

	@FindBy(xpath="//span[@class=\"bulkActionStrip-itemSelected\"]")
	WebElement getBagItemCount;
	
	@FindBy(xpath="//div[@class=\"emptyCart-base-emptyText\"]")
	WebElement emptyCart;

	@FindBy(xpath="//button[contains(text(),'MOVE TO WISHLIST')]")
	WebElement MoveWishList;
	
	@FindBy(xpath="//div[@class=\"inlinebuttonV2-base-actions \"]/child::div[2]")
	WebElement movePopUpBtn;	
	
	

	{
		PageFactory.initElements(KeyWord.driver, this);
	}

	public String getPageSource() {
		return KeyWord.getPageSource();
	}

	public void clickOnCoupon() {
		KeyWord.clickOn(coupons);

	}

	
	public boolean isEmptyCartDisplayed() {
		WaitFor.visibilityOfelement(emptyCart);
		return emptyCart.isDisplayed();
	}
	
	public String getTexytOfEmptyCart() {
		WaitFor.visibilityOfelement(emptyCart);
		return emptyCart.getText().trim();
	}


	public void clickOnRemoveBtn() {
		WaitFor.visibilityOfelement(removeBtn);
		WaitFor.elementToBeClickaBle(removeBtn);

		KeyWord.clickOn(removeBtn);

	}


	public int getBagItemCount() {
		String count=getBagItemCount.getText().trim();
		 return Integer.parseInt(count.split("/")[0]);
	}
	

	public void clickOnRemovePopUpBtn() {
		WaitFor.visibilityOfelement(removePopUpBtn);
		WaitFor.elementToBeClickaBle(removePopUpBtn);

		KeyWord.clickOn(removePopUpBtn);

	}


	public void selectCheckBox() {
		KeyWord.clickOn(CheckBox);
	}
	

	public boolean isRemovePopUpDisplayed() {
		try {
			return removePopUpBtn.isDisplayed();
		} catch (Exception e) {
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
		} catch (Exception e) {

		}


			
		
		return false;

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



	public boolean isPlaceOrderBtnDisplayed() {
		try {
			return placeOrderBtn.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public void clickOnPlaceOrderBtn() {
		WaitFor.visibilityOfelement(placeOrderBtn);
		WaitFor.elementToBeClickaBle(placeOrderBtn);

		KeyWord.clickOn(placeOrderBtn);

	}

	public boolean isProductDisplayed() {
		// TODO Auto-generated method stub
		WaitFor.visibilityOfelement(ProductName);
		return KeyWord.isDisplayed(ProductName);
	}

	public String getTextOfProduct() {
		WaitFor.visibilityOfelement(ProductName);
		return ProductName.getText();
	}

	public String getProductBrand() {
		WaitFor.visibilityOfelement(productBrand);
		return productBrand.getText();
	}

	public int getPriceOfProduct() {
		String text = productPrice.getText().trim();

		String Pricetext = text.replace("₹", "");


		return Integer.parseInt(Pricetext);
	}

	/**
<<<<<<< Updated upstream
	 * Returns the brand text for the product at the given 0-based index in the cart.
=======
	 * Returns the brand text for the product at the given 0-based index in the
	 * cart.
>>>>>>> Stashed changes
	 */
	public String getProductBrand(int index) {
		By brandsBy = By.xpath("//div[@class='itemContainer-base-brand']");
		List<WebElement> brands = WaitFor.visibilityOfAll(brandsBy);
		return brands.get(index).getText();
	}

	/**
<<<<<<< Updated upstream
	 * Returns the price (integer) for the product at the given 0-based index in the cart.
=======
	 * Returns the price (integer) for the product at the given 0-based index in the
	 * cart.
>>>>>>> Stashed changes
	 */
	public int getPriceOfProduct(int index) {
		By pricesBy = By.xpath("//span[@class=\"itemComponents-base-rupeeBoldIcon\"]/parent::div");
		List<WebElement> prices = WaitFor.visibilityOfAll(pricesBy);
		String text = prices.get(index).getText().trim();
		String pricetext = text.replace("₹", "").replace("Rs.", "").replaceAll("[^0-9]", "");
		return Integer.parseInt(pricetext);
	}


	public void ScrollCartWindow() {
		KeyWord.scrollWindow();
	}

	public boolean isMoreProductsVisible() {
		WaitFor.visibilityOfelement(moreProducts);
		return moreProducts.isDisplayed();
	}

	public String getCartUrl() {
		return KeyWord.getCurrentUrl();
	}

	public void clickOnWishListBtn() {
		// TODO Auto-generated method stub
		WaitFor.visibilityOfelement(MoveWishList);
		KeyWord.clickOn(MoveWishList);
		
	}

	public void clickOnMovePopUpBtn() {
		// TODO Auto-generated method stub
		WaitFor.visibilityOfelement(movePopUpBtn);
		WaitFor.elementToBeClickaBle(movePopUpBtn);

		KeyWord.clickOn(movePopUpBtn);
	}


}
