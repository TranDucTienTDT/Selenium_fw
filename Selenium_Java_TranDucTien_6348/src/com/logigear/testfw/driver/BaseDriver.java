package com.logigear.testfw.driver;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.logigear.testfw.conf.LogWrapper;

/**
 * 
 * @author ngocquang.tran
 *
 */
public class BaseDriver {
	protected static final Logger LOG = LogWrapper.createLogger(BaseDriver.class.getName());
	
	protected WebDriver _driver;
	
	protected WebDriver getWebDriver() {
		return this._driver;
	}

	public void get(String url) {
		_driver.get(url);
	}

	public String getCurrentUrl() {
		return _driver.getCurrentUrl();
	}

	public String getTitle() {
		return _driver.getTitle();
	}
	
	public void close() {
		if(_driver!=null)
			_driver.close();
	}

	public void quit() {
		if(_driver!=null)
			_driver.quit();
	}
	
	public TargetLocator switchTo() {
		if(_driver!=null)
			return _driver.switchTo();
		return null;
	}
	
	public Options manage() {
		if(_driver!=null)
			return _driver.manage();
		return null;
	}
	
	public Actions action() {
		return new Actions(getWebDriver());
	}
	
	private JavascriptExecutor jsExecutor() {
		return ((JavascriptExecutor) getWebDriver());
	}

	public Object executeScript(String script, Object... args) {
		return jsExecutor().executeScript(script);
	}
	
	public WebElement waitForPresent(By locator, int timeOutInSeconds) {
		WebElement element = null;
		LOG.info(String.format("Wait for control %s to be present in DOM with timeOut %s", locator.toString(),
				timeOutInSeconds));
		try {
			WebDriverWait wait = new WebDriverWait(getWebDriver(), timeOutInSeconds);
			element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Exception error) {
			LOG.severe(String.format("Has error with control '%s': %s", locator.toString(), error.getMessage()));
			throw error;
		}
		return element;
	}
	
	public List<WebElement> waitForAllElementsPresent(By locator, int timeOutInSeconds) {
		List<WebElement> elements = null;
		LOG.info(String.format("Wait for all controls %s to be present in DOM with timeOut %s",locator.toString(),
				timeOutInSeconds));
		try {
			WebDriverWait wait = new WebDriverWait(getWebDriver(), timeOutInSeconds);
			elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		} catch (Exception error) {
			LOG.severe(String.format("Has error with control '%s': %s", locator.toString(), error.getMessage()));
			throw error;
		}
		return elements;
	}
	
	public WebElement waitForDisplay(By locator, int timeOutInSeconds) {
		WebElement element = null;
		try {
			LOG.info(String.format("Wait for control %s to be displayed with timeOut: %s",locator.toString(),
					timeOutInSeconds));
			WebDriverWait wait = new WebDriverWait(getWebDriver(), timeOutInSeconds);
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception error) {
			error.printStackTrace();
			LOG.severe(String.format("Has error with control '%s': %s", locator.toString(), error.getMessage()));
			throw error;
		}
		return element;
	}
	
	public void waitForInvisibility(By locator, int timeOutInSeconds) {
		try {
			LOG.info(String.format("Wait for control %s to be invisibled", locator.toString()));
			WebDriverWait wait = new WebDriverWait(getWebDriver(), timeOutInSeconds);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		} catch (Exception error) {
			LOG.severe(String.format("Has error with control '%s': %s", locator.toString(), error.getMessage()));
			throw error;
		}
	}

	public List<WebElement> waitForAllElementsDisplay(By locator, int timeOutInSeconds) {
		List<WebElement> elements = null;
		try {
			LOG.info(String.format("Wait for all controls %s to be visibled", locator.toString()));
			WebDriverWait wait = new WebDriverWait(getWebDriver(), timeOutInSeconds);
			elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		} catch (Exception error) {
			LOG.severe(String.format("Has error with control '%s': %s", locator.toString(), error.getMessage()));
			throw error;
		}
		return elements;
	}
	
	public WebElement waitForClickable(By locator, int timeOutInSeconds) {
		WebElement element = null;
		try {
			LOG.info(String.format("Wait for control %s to be clickabled", locator.toString()));
			WebDriverWait wait = new WebDriverWait(getWebDriver(), timeOutInSeconds);
			element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (Exception error) {
			LOG.severe(String.format("Has error with control '%s': %s", locator.toString(), error.getMessage()));
			throw error;
		}
		return element;
	}
	
	public void waitForAlertPopupPresent(int timeOutInSeconds) {
		try {
			LOG.info(String.format("Wait for alert popup presented."));
			WebDriverWait wait = new WebDriverWait(getWebDriver(), timeOutInSeconds);
			wait.until(ExpectedConditions.alertIsPresent());
		} catch (Exception error) {
			LOG.severe(String.format("Has error: %s", error.getMessage()));
			throw error;
		}
	}
	
	//@author hanh.nguyen
	public Set <String> getWindowHandles() {
		return _driver.getWindowHandles();
	}
	
	private void wait(int second){	
		try
		{
		    Thread.sleep(second * 1000);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
	}
	
	//@author hanh.nguyen
	public WebElement findElement(By elementDescription, int timeOutInSeconds) {
		WebElement element = getWebDriver().findElement(elementDescription);
		while(timeOutInSeconds > 0){
			if(element != null)
			{
				return element;
			}
			wait(1);
			timeOutInSeconds--;
		}
		return null;
	}
	
}
