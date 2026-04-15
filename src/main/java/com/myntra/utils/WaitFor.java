package com.myntra.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.myntra.basetest.KeyWord;

/**
 * * WaitFor ──────── All waiting methods are kept here in one place.
 *
 * WHY we need waits: Websites take time to load. If we try to click a button
 * before it appears on screen, the test will fail. These methods WAIT until the
 * element is ready, then return it.
 *
 * Usage (called from KeyWord.java): WaitFor.visibilityOfelement(driver,
 * element); WaitFor.elementToBeClickaBle(driver, element);
 */
public class WaitFor {

	public static final WebDriverWait wait;

	static {
		wait = new WebDriverWait(KeyWord.driver, Duration.ofSeconds(60));
		wait.pollingEvery(Duration.ofMillis(500));
		wait.ignoring(NoSuchElementException.class);
	}

	/**
	 * Waits until the element is visible on screen. Returns the element once it
	 * becomes visible.
	 *
	 * Use before: reading text, checking if displayed
	 */
	private WaitFor() {
		// Private constructor to prevent instantiation
	}

	public static void visibilityOfelement(By locator) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}

	public static void elementToBeClickaBle(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));

	}

	public static WebElement visibilityOfelement(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element));

	}

	public static WebElement elementToBeClickaBle(WebElement element) {
		return wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public static List<WebElement> visibilityOfAll(List<WebElement> elements) {

		return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	public static List<WebElement> visibilityOfAll(By elements) {

		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elements));
	}

	public static void untilElementGotStale(WebElement webElement) {

		wait.until(ExpectedConditions.stalenessOf(webElement));

	}

	public static void untilElementGotStale(By locator) {
		wait.until(ExpectedConditions.stalenessOf(KeyWord.driver.findElement(locator)));
	}

}
