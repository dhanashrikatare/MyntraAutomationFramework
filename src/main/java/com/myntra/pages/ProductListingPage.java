package com.myntra.pages;

import static com.myntra.basetest.KeyWord.*;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myntra.basetest.KeyWord;
import com.myntra.utils.WaitFor;
/**
 * ProductListingPage ─────── This class represents the "Product Listing" page of
 * the Myntra website. It contains WebElements and methods to interact with the
 * elements on that page.
 *
 * Example usage:
 *
 * public class MyTest {
 *
 * @Test public void testProductListingPage() { ProductListingPage plp = new
 *       ProductListingPage(); String title = plp.getPlpTitle(); // ... other
 *       steps ... } }
 *
 * @author Dhanashri-katare
 */
public class ProductListingPage {

	@FindBy(xpath = "//li[@class=\"product-base\"]")
	List<WebElement> productCard;

	@FindBy(xpath = "//ul[@class=\"FilterDirectory-list\"]/li")
	List<WebElement> brandNames;

	@FindBy(css = ".product-brand")
	List<WebElement> productBrands;

	@FindBy(css = ".sort-sortBy, .sort-label")
	WebElement sortButton;

	@FindBy(css = ".sort-list li")
	List<WebElement> sortOptions;

	@FindBy(xpath = "//label[@class=\"vertical-filters-label common-customCheckbox\"]")
	WebElement brandOption;

//	List<WebElement> activeFilterChips;

	@FindBy(xpath = "//span[@class=\"header-clearAllBtn\"]")
	WebElement clearAllButton;

	@FindBy(css = ".title-count, .breadcrumb-count")
	WebElement resultCountText;

	@FindBy(xpath = "//div[@class=\"breadcrumbs-base\"]")
	WebElement breadCrumb;

	@FindBy(xpath = "//div[@class=\"title-container\"]/child::span")
	WebElement productCount;

	@FindBy(xpath = "//ul[@class=\"discount-list\"]/li")
	List<WebElement> DiscountRange;

	@FindBy(xpath = "//ul[@class=\"atsa-values\"]/li/label")
	List<WebElement> colourList;

	@FindBy(xpath = "//ul[@class=\"gender-list\"]/li/label")
	List<WebElement> genders;

	@FindBy(xpath = "//li[@class=\"colour-listItem\"]/label")
	List<WebElement> coloursOption;

	@FindBy(xpath = "//h3[@class=\"product-brand\"]")
	List<WebElement> brands;

	// By brands=By.xpath("//ul[@class=\"results-base\"]/li");

By products=By.cssSelector(".title-count");
	By BrandSearchBar = By.xpath("//span[text()='Brand']/following::span[1]");
	By colourSearchBar = By.xpath("//span[text()='Color']/following::span[1]");
	By colourInputSearchBox = By.xpath("//input[@placeholder=\"Search for Color\"]");
	By BrandInputSearchBox = By.xpath("//input[@placeholder=\"Search for Brand\"]");

	@FindBy(xpath = "//h4[text()='Colour Family']")
	WebElement ColourSelect;
	By filters = By.xpath("//span[text()='FILTERS']");
	By boys_filter = By.xpath("//label[contains(text(),'Boys')]");
	By girls_filter = By.xpath("//label[contains(text(),'Girls')]");
	
	@FindBy(xpath="//span[@class=\"product-discountPercentage\"]")
	List<WebElement> discountLabels;

	{
		PageFactory.initElements(KeyWord.driver, this);
	}
	
	public String getPlpTitle() {
		return KeyWord.driver.getTitle();
	}
	
	public String getPlpBreadCrumb() {
		return breadCrumb.getText();
	}
	
	public String getPlpUrl() {
		return KeyWord.driver.getCurrentUrl();
	}
	

	public boolean verifyMultipleBrands(List<String> brands) {
		for (WebElement brand : brandNames) {
			String actual = brand.getText().toLowerCase();

			if (!brands.contains(actual)) {
				return false;
			}
		}
		return true;
	}

	public void clickProduct(int index) {
		// KeyWord.waitForSeconds(3000);
		// Step 1: Get product name from product list
//
		By productCardBy = By.xpath("//li[@class=\"product-base\"]");
//		// Step 2: Click product
//		List<WebElement> products = KeyWord.driver.findElements(productCardBy);
//		WaitFor.visibilityOfelement(productCardBy);

		List<WebElement> products = WaitFor.visibilityOfAll(productCardBy);
		
		products.get(index).click();
		// KeyWord.waitForSeconds(2000);

	}

//	public String getProductText(String name) {
//		return productCards.get
//	}

	public String getProductText(int index) {

		return productCard.get(index).getText();
	}

	/** opens the dropdown and select by entering text **/
	public void sortBy(String optionText) {
		WaitFor.visibilityOfelement(sortButton);
		WaitFor.elementToBeClickaBle(sortButton);
		KeyWord.clickOn(sortButton);
		for (WebElement option : sortOptions) {
			if (option.getText().trim().equalsIgnoreCase(optionText)) {
				KeyWord.clickOn(option);
				return;
			}

		}
		throw new RuntimeException("Sort option not found: " + optionText);

	}

	// Apply Boys filter
	public void selectBoysFilter() {

		getElement(boys_filter).click();

	}

	// Apply Girls filter
	public void selectGirlsFilter() {
		getElement(girls_filter).click();
	}

	public void filterByProductColour(String colour) {

		KeyWord.scrollToElement();
		WaitFor.visibilityOfelement(colourSearchBar);
		WaitFor.elementToBeClickaBle(colourSearchBar);
		KeyWord.clickOn(colourSearchBar);
		KeyWord.driver.findElement(colourInputSearchBox).clear();
		KeyWord.type(KeyWord.driver.findElement(colourInputSearchBox), colour);
		By colourOption = By.xpath("//label[@class=\"common-customCheckbox\"]");
		PageFactory.initElements(KeyWord.driver, this);
		WaitFor.visibilityOfelement(colourOption);
		WaitFor.elementToBeClickaBle(colourOption);
		
		KeyWord.clickOn(colourOption);

	}

	public void filterByDiscountRange(String range) {

//		KeyWord.scrollWindow();
		for (WebElement ranges : DiscountRange) {
			if (ranges.getText().trim().equalsIgnoreCase(range)) {
				PageFactory.initElements(KeyWord.driver, this);
				KeyWord.clickOn(ranges);
				return;
			}
		}
		throw new RuntimeException("ranges option not found: " + range);

	}

	public void filterByBrand(String BrandName) {
		WaitFor.visibilityOfelement(BrandSearchBar);
		WaitFor.elementToBeClickaBle(BrandSearchBar);
		KeyWord.clickOn(BrandSearchBar);
		KeyWord.driver.findElement(BrandInputSearchBox).clear();
		KeyWord.type(KeyWord.driver.findElement(BrandInputSearchBox), BrandName);
		PageFactory.initElements(KeyWord.driver, this);
		WaitFor.visibilityOfelement(brandOption);
		WaitFor.elementToBeClickaBle(brandOption);
		KeyWord.clickOn(brandOption);
	}

	public void filterByColour(String colour) {
		PageFactory.initElements(KeyWord.driver, this);
		WaitFor.elementToBeClickaBle(ColourSelect);
		KeyWord.clickOn(ColourSelect);
		for (WebElement eachcolour : colourList) {
			if (eachcolour.getText().equalsIgnoreCase(colour)) {
				KeyWord.clickOn(eachcolour);
				System.out.println(colour + " is selected successfully");
				return;
			}

		}
		throw new RuntimeException("colour option not found: " + colour);

//		KeyWord.clickOn(colourSearchBar);
//		KeyWord.type(KeyWord.driver.findElement(colourInputSearchBox), colour);
//		
//		By colourOption=By.xpath("//li[@class=\"colour-listItem\"]");
//		KeyWord.clickOn(colourOption);
	}

	public void filterByGender(String genderName) {

		WaitFor.visibilityOfAll(genders);

		for (WebElement gender : genders) {
			String text = gender.getText().trim();
			System.out.println("Found gender option: '" + text + "'");
			if (text.equalsIgnoreCase(genderName)) {
				KeyWord.clickOn(gender);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(genderName + "selected successfully");
				return;
			}
		}
		throw new RuntimeException("gender option is not avaialable: " + genderName);

	}

	public List<String> getAllProductsBrands() {
		List<String> brandNames = new ArrayList<>();

		for (WebElement brand : brands) {
			brandNames.add(brand.getText());
		}
		return brandNames;

	}

	public int getProductCount() {
		return productCard.size();
	}
	
	public int getProductCounts(){
	String text=driver.findElement(products).getText();
	text=text.replaceAll("[^0-9]","");
	return Integer.parseInt(text);
		}
	
	 
	public void clearAllFilters() {
		KeyWord.clickOn(clearAllButton);
	}

	public List<String> getAllProductsColours() {
		// TODO Auto-generated method stub
		List<String> colourNames = new ArrayList<>();
		for (WebElement colour : coloursOption) {
			colourNames.add(colour.getText());
		}
		return colourNames;
	}

	public List<Double> getAllProductsPrices() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean areAllProductsRelatedToLipsticks() {
		for(WebElement product:productCard) {
			String productText=product.getText().toLowerCase();
			if(!productText.contains("lipstick")) {
				return false;
			}
		}
		return true;
	}

	public boolean isBrandFilterApplied(String string) {
		 for (WebElement brand : productBrands) {
	            String actualBrand = brand.getText().trim();
	            if (!actualBrand.equalsIgnoreCase(string)) {
	                return false; // Found a product that does not match the filter
	            }
	        }
	        return true; // All products match the filter
		// TODO Auto-generated method stub
		
	}
	
	public String getSelectedSortOption() {

	    WaitFor.visibilityOfelement(sortButton);

	    return sortButton.getText()
	            .replace("Sort by :", "")
	            .trim();
	}
	
	public List<Integer> getAllProductsDiscountPercentages() {
		// TODO Auto-generated method stub
		List<Integer>discounts=new ArrayList<Integer>();
		for(WebElement discount: discountLabels) {
			String text=discount.getText();

	        String numbersOnly =
	                text.replaceAll("[^0-9]", "");

	        if (!numbersOnly.isEmpty()) {

	            discounts.add(
	                Integer.parseInt(numbersOnly));
	        }
			
		}
		
		
		
		return discounts;
	}

}
