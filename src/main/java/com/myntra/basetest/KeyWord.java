package com.myntra.basetest;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.myntra.utils.ConfigReader;
import com.myntra.utils.WaitFor;

/**
 * KeyWord ─────── This class contains static methods that can be called from
 * any test class. It is NOT a test class itself, so it does not have @Test
 * methods.
 *
 * Example usage:
 *
 * public class MyTest {
 * 
 * @Test public void testSomething() { KeyWord.openBrowser(); KeyWord.openUrl();
 *       // ... other steps ... } }
 *       @author Dhanashri-katare
 */
public class KeyWord {
	static String browser = ConfigReader.get("browser");
	public static RemoteWebDriver driver;

	public static void openBrowser() {
		if (browser.equalsIgnoreCase("chrome")) {

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			options.addArguments("--start-maximized");
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);

		}

		else if (browser.equalsIgnoreCase("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--private");
			driver = new FirefoxDriver(options);
		}

		else {
			throw new RuntimeException("Browser not supported: " + browser);

		}
		// If an element takes time to load, wait up to 20 seconds before failing
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	public static void openUrl() {
		driver.get(ConfigReader.get("base.url"));
	}

	public static void enterText(String LocatorType, String locator, String text) {
		if (LocatorType.equalsIgnoreCase("id")) {
			driver.findElement(By.id(locator)).sendKeys(text);
		} else if (LocatorType.equalsIgnoreCase("name")) {
			driver.findElement(By.name(locator)).sendKeys(text);
		} else if (LocatorType.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(locator)).sendKeys(text);
		} else if (LocatorType.equalsIgnoreCase("cssSelector")) {
			driver.findElement(By.cssSelector(locator)).sendKeys(text);
		} else if (LocatorType.equalsIgnoreCase("className")) {
			driver.findElement(By.className(locator)).sendKeys(text);
		} else if (LocatorType.equalsIgnoreCase("tagName")) {
			driver.findElement(By.tagName(locator)).sendKeys(text);
		}

	}

	public static void type(WebElement element, String text) {
		WaitFor.visibilityOfelement(element);
		element.clear();
		element.sendKeys(text);

	}

	public static void Enter(WebElement element) {
		WaitFor.visibilityOfelement(element);
		element.clear();
		//type(element, text);
		element.sendKeys(Keys.ENTER);

	}

	public static String getText(WebElement element) {
		return element.getText().trim();

	}

	public static String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public static 	void clickOn(String LocatorType, String locator) {
		if (LocatorType.equalsIgnoreCase("id")) {
			driver.findElement(By.id(locator)).click();
		} else if (LocatorType.equalsIgnoreCase("name")) {
			driver.findElement(By.name(locator)).click();
		} else if (LocatorType.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(locator)).click();
		} else if (LocatorType.equalsIgnoreCase("cssSelector")) {
			driver.findElement(By.cssSelector(locator)).click();
		} else if (LocatorType.equalsIgnoreCase("className")) {
			driver.findElement(By.className(locator)).click();
		} else if (LocatorType.equalsIgnoreCase("tagName")) {
			driver.findElement(By.tagName(locator)).click();
		}

	}

	public static void clickOn(WebElement element) {
		WaitFor.elementToBeClickaBle(element);
		element.click();

	}

	public static void clickOn(By element) {
		driver.findElement(element).click();
		;

	}

	public static void clearFields(String LocatorType, String locator) {
		if (LocatorType.equalsIgnoreCase("id")) {
			driver.findElement(By.id(locator)).clear();
		} else if (LocatorType.equalsIgnoreCase("name")) {
			driver.findElement(By.name(locator)).clear();
		} else if (LocatorType.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(locator)).clear();
		} else if (LocatorType.equalsIgnoreCase("cssSelector")) {
			driver.findElement(By.cssSelector(locator)).clear();
		} else if (LocatorType.equalsIgnoreCase("className")) {
			driver.findElement(By.className(locator)).clear();
		} else if (LocatorType.equalsIgnoreCase("tagName")) {
			driver.findElement(By.tagName(locator)).clear();
		}

	}

	public static void scrollWindow() {
		Actions act = new Actions(driver);
		act.scrollByAmount(0, 1000).perform();
	}

	public static boolean isEnabled(WebElement element) {
		
		return element.isEnabled();

	}

	public static void switchToNewTab() {
		Set<String> tabs = driver.getWindowHandles();
		for (String tab : tabs) {
			driver.switchTo().window(tab);
			System.out.println("switch to new tab: " + driver.getCurrentUrl());

		}

	}
	
	public static void scrollToElement() {
		driver.executeScript("window.scrollBy(0,200)");
	}

	public static void closeBrowser() {
		driver.quit();
	}

	public static boolean isDisplayed(WebElement element) {
		WaitFor.visibilityOfelement(element);
		return element.isDisplayed();
	}

	public static void waitForSeconds(int mili) {
		try {
			Thread.sleep(mili);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

	public static String getPageSource() {
		// TODO Auto-generated method stub
		return driver.getPageSource();
	}

	public static int getImageCount(List<WebElement> element) {
		return element.size();

	}
	
	//For Kids
	public static WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public static void hover(WebElement womenMenu) {
		Actions action = new Actions(driver);
		action.moveToElement(womenMenu).perform();

	}
}
