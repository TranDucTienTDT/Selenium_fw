package com.logigear.testfw.element;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.google.common.base.Stopwatch;
import com.logigear.testfw.common.Common;
import com.logigear.testfw.common.TestExecutor;
import com.logigear.testfw.conf.LogWrapper;

public class Element extends BaseElement {

	private static final Logger LOG = LogWrapper.createLogger(Element.class.getName());

	public Element(By locator) {
		super(locator);
	}

	public Element(String xpath) {
		super(xpath);
	}
	
	/**
	 * @author: tien.duc.tran
	 * @Description: Constructor for dynamic locator 
	 * @param: dynamicLocator, placeValue
	 */
	public Element(String dynamicLocator, String placeValue) {
		super(dynamicLocator, placeValue);
	}

	public void enter(String text) {
		clear();
		sendKeys(text);
	}

	public void doubleClick() {
		LOG.info(String.format("Click on the control %s twice", getLocator().toString()));
		for (int i = 0; i < 2; i++) {
			click();
		}
	}

	public String getValue() {
		return getAttribute("value");
	}

	public int getWidth() {
		return getSize().getWidth();
	}

	public int getHeight() {
		return getSize().getHeight();
	}

	public int getPointX() {
		return getLocation().getX();
	}

	public int getPointY() {
		return getLocation().getY();
	}

	public void setValue(String value) {
		try {
			LOG.info(String.format("Set value for the control %s", getLocator().toString()));
			String js = String.format("arguments[0].value='" + value + "';");
			TestExecutor.getInstance().getCurrentDriver().executeScript(js, getElement());
		} catch (Exception error) {
			LOG.severe(String.format("Has error with control '%s': %s", getLocator().toString(), error.getMessage()));
			throw error;
		}
	}

	public void setAttribute(String attributeName, String value) {
		try {
			LOG.info(String.format("Set value of a specific attribute for the control %s", getLocator().toString()));
			String js = String.format("arguments[0].setAttribute(arguments[1], arguments[2]);");
			TestExecutor.getInstance().getCurrentDriver().executeScript(js, getElement(), attributeName, value);
		} catch (Exception error) {
			LOG.severe(String.format("Has error with control '%s': %s", getLocator().toString(), error.getMessage()));
			throw error;
		}
	}

	private Actions action() {
		return TestExecutor.getInstance().getCurrentDriver().action();
	}

	public void dragTo(int timeOutInSeconds, Element targetElement) {
		if (timeOutInSeconds <= 0) {
			LOG.severe("The time out is invalid. It must greater than 0");
			return;
		}
		Stopwatch sw = Stopwatch.createStarted();
		try {
			LOG.info(String.format("Drag the control %s to the target", getLocator().toString()));
			action().dragAndDrop(waitForDisplay(timeOutInSeconds), targetElement.getElement());
			action().build().perform();
		} catch (StaleElementReferenceException ex) {
			if (sw.elapsed(TimeUnit.SECONDS) <= (long) timeOutInSeconds) {
				LOG.warning(String.format("Try to drag the control %s to the target again", getLocator().toString()));
				dragTo(timeOutInSeconds - (int) sw.elapsed(TimeUnit.SECONDS), targetElement);
			}
		} catch (Exception error) {
			LOG.severe(String.format("Has error with control '%s': %s", getLocator().toString(), error.getMessage()));
			throw error;
		}
	}

	public void dragTo(Element targetElement) {
		dragTo(Common.ELEMENT_TIMEOUT, targetElement);
	}

	public void moveToElement(int timeOutInSeconds) {
		if (timeOutInSeconds <= 0) {
			LOG.severe("The time out is invalid. It must greater than 0");
			return;
		}
		Stopwatch sw = Stopwatch.createStarted();
		try {
			LOG.info(String.format("Move mouse to the control %s", getLocator().toString()));
			action().moveToElement(waitForDisplay(timeOutInSeconds)).build().perform();
		} catch (StaleElementReferenceException e) {
			if (sw.elapsed(TimeUnit.SECONDS) <= (long) timeOutInSeconds) {
				LOG.warning(String.format("Try to move mouse to the control %s again", getLocator().toString()));
				moveToElement(timeOutInSeconds - (int) sw.elapsed(TimeUnit.SECONDS));
			}
		} catch (Exception error) {
			LOG.severe(String.format("Has error with control '%s': %s", getLocator().toString(), error.getMessage()));
			throw error;
		}
	}

	public void moveToElement() {
		moveToElement(Common.ELEMENT_LONG_TIMEOUT);
	}

	public void focus(int timeOutInSeconds) {
		if (timeOutInSeconds <= 0) {
			LOG.severe("The time out is invalid. It must greater than 0");
			return;
		}
		Stopwatch sw = Stopwatch.createStarted();
		try {
			LOG.info(String.format("Focus on the control %s", getLocator().toString()));
			String js = String.format("arguments[0].focus();");
			TestExecutor.getInstance().getCurrentDriver().executeScript(js, waitForDisplay(timeOutInSeconds));
		} catch (StaleElementReferenceException e) {
			if (sw.elapsed(TimeUnit.SECONDS) <= (long) timeOutInSeconds) {
				LOG.warning(String.format("Try to focus on the control %s again", getLocator().toString()));
				focus(timeOutInSeconds - (int) sw.elapsed(TimeUnit.SECONDS));
			}
		} catch (Exception error) {
			LOG.severe(String.format("Has error with control '%s': %s", getLocator().toString(), error.getMessage()));
			throw error;
		}
	}

	public void focus() {
		focus(Common.ELEMENT_LONG_TIMEOUT);
	}

	public boolean isExisted() {
		boolean isExisted = false;
		try {
			LOG.info(String.format("Check if the control %s is existed in DOM", getLocator().toString()));
			waitForPresent(Common.ELEMENT_TIMEOUT);
			isExisted = true;
		} catch (NoSuchElementException | TimeoutException | NullPointerException ex) {
			LOG.info(String.format("The control %s is not existed in DOM", getLocator().toString()));
			isExisted = false;
		} catch (Exception error) {
			LOG.severe(String.format("Has error with control '%s': %s", getLocator().toString(), error.getMessage()));
			throw error;
		}
		return isExisted;
	}

	public boolean isAttributePresent(String attribute) {
		try {
			LOG.info(String.format("Check if the attribute of the control %s is present", getLocator().toString()));
			if (getElement().getAttribute(attribute) != null)
				return true;
			return false;
		} catch (Exception error) {
			LOG.severe(String.format("Has error with control '%s': %s", getLocator().toString(), error.getMessage()));
			throw error;
		}
	}
	
	//@author hanh.nguyen
	public boolean isClassActivePresent() {
		try {
			LOG.info(String.format("Check if value of attribute \"class\" of the control %s is \"active\" or not", getLocator().toString()));
			if (getElement().getAttribute("class") == "active") 
				return true;
			return false;
		} catch (Exception error) {
			LOG.severe(String.format("Has error with control '%s': %s", getLocator().toString(), error.getMessage()));
			throw error;
		}
	}

	protected Select selection(int timeOutInSeconds) {
		Select selection = new Select(waitForDisplay(timeOutInSeconds));
		return selection;
	}

	public void check() {
		while (!isSelected()) {
			click();
		}
	}

	public void uncheck() {
		while (isSelected()) {
			click();
		}
	}

	public void selectByIndex(int timeOutInSeconds, int index) {
		if (timeOutInSeconds <= 0) {
			LOG.severe("The time out is invalid. It must greater than 0");
			return;
		}
		Stopwatch sw = Stopwatch.createStarted();
		try {
			LOG.info(String.format("Select the option of the control %s by index", getLocator().toString()));
			selection(timeOutInSeconds).selectByIndex(index);
		} catch (StaleElementReferenceException ex) {
			if (sw.elapsed(TimeUnit.SECONDS) <= (long) timeOutInSeconds) {
				LOG.warning(String.format("Try to select the option of the control %s by index again",
						getLocator().toString()));
				selectByIndex(timeOutInSeconds - (int) sw.elapsed(TimeUnit.SECONDS), index);
			}
		} catch (Exception error) {
			LOG.severe(String.format("Has error with control '%s': %s", getLocator().toString(), error.getMessage()));
			throw error;
		}
	}

	public void selectByIndex(int index) {
		selectByIndex(Common.ELEMENT_LONG_TIMEOUT, index);
	}

	public void selectByText(int timeOutInSeconds, String text) {
		if (timeOutInSeconds <= 0) {
			LOG.severe("The time out is invalid. It must greater than 0");
			return;
		}
		Stopwatch sw = Stopwatch.createStarted();
		try {
			LOG.info(String.format("Select the option of the control %s by text", getLocator().toString()));
			selection(timeOutInSeconds).selectByVisibleText(text);
		} catch (StaleElementReferenceException ex) {
			if (sw.elapsed(TimeUnit.SECONDS) <= (long) timeOutInSeconds) {
				LOG.warning(String.format("Try to select the option of the control %s by text again",
						getLocator().toString()));
				selectByText(timeOutInSeconds - (int) sw.elapsed(TimeUnit.SECONDS), text);
			}
		} catch (Exception error) {
			LOG.severe(String.format("Has error with control '%s': %s", getLocator().toString(), error.getMessage()));
			throw error;
		}
	}

	public void selectByText(String text) {
		selectByText(Common.ELEMENT_LONG_TIMEOUT, text);
	}

	public void selectByValue(int timeOutInSeconds, String value) {
		if (timeOutInSeconds <= 0) {
			LOG.severe("The time out is invalid. It must greater than 0");
			return;
		}
		Stopwatch sw = Stopwatch.createStarted();
		try {
			LOG.info(String.format("Select the option of the control %s by value", getLocator().toString()));
			selection(timeOutInSeconds).selectByValue(value);
		} catch (StaleElementReferenceException ex) {
			if (sw.elapsed(TimeUnit.SECONDS) <= (long) timeOutInSeconds) {
				LOG.warning(String.format("Try to select the option of the control %s by value again",
						getLocator().toString()));
				selectByValue(timeOutInSeconds - (int) sw.elapsed(TimeUnit.SECONDS), value);
			}
		} catch (Exception error) {
			LOG.severe(String.format("Has error with control '%s': %s", getLocator().toString(), error.getMessage()));
			throw error;
		}
	}

	public void selectByValue(String value) {
		selectByValue(Common.ELEMENT_LONG_TIMEOUT, value);
	}
	
	//@author hanh.nguyen
	public void selectByTextContains(int timeOutInSeconds, String value) {
		if (timeOutInSeconds <= 0) {
			LOG.severe("The time out is invalid. It must greater than 0");
			return;
		}
		Stopwatch sw = Stopwatch.createStarted();
		try {
			LOG.info(String.format("Select the option of the control %s by text contains", getLocator().toString()));
			List<WebElement> options = selection(timeOutInSeconds).getOptions();
			for (WebElement option : options) {
			    if (option.getText().contains(value))
			        option.click();
			}
		} catch (StaleElementReferenceException ex) {
			if (sw.elapsed(TimeUnit.SECONDS) <= (long) timeOutInSeconds) {
				LOG.warning(String.format("Try to select the option of the control %s by text contains",
						getLocator().toString()));
				selectByValue(timeOutInSeconds - (int) sw.elapsed(TimeUnit.SECONDS), value);
			}
		} catch (Exception error) {
			LOG.severe(String.format("Has error with control '%s': %s", getLocator().toString(), error.getMessage()));
			throw error;
		}
	}
	
	//@author hanh.nguyen
	public void selectByTextContains(String value) {
		selectByTextContains(Common.ELEMENT_LONG_TIMEOUT, value);
	}
	
	//@author hanh.nguyen
	public void selectByTextIgnoreCase(int timeOutInSeconds, String value) {
		if (timeOutInSeconds <= 0) {
			LOG.severe("The time out is invalid. It must greater than 0");
			return;
		}
		Stopwatch sw = Stopwatch.createStarted();
		try {
			LOG.info(String.format("Select the option of the control %s by text ignore case", getLocator().toString()));
			List<WebElement> options = selection(timeOutInSeconds).getOptions();
			for (WebElement option : options) {
				if (option.getText().equalsIgnoreCase(value))
					option.click();
			}
		} catch (StaleElementReferenceException ex) {
			if (sw.elapsed(TimeUnit.SECONDS) <= (long) timeOutInSeconds) {
				LOG.warning(String.format("Try to select the option of the control %s by text ignore case",
						getLocator().toString()));
				selectByValue(timeOutInSeconds - (int) sw.elapsed(TimeUnit.SECONDS), value);
			}
		} catch (Exception error) {
			LOG.severe(String.format("Has error with control '%s': %s", getLocator().toString(), error.getMessage()));
			throw error;
		}
	}

	// @author hanh.nguyen
	public void selectByTextIgnoreCase(String value) {
		selectByTextIgnoreCase(Common.ELEMENT_LONG_TIMEOUT, value);
	}

	public String getSelectedOption(int timeOutInSeconds) {
		String selected = null;
		if (timeOutInSeconds <= 0) {
			LOG.severe("The time out is invalid. It must greater than 0");
			return selected;
		}
		Stopwatch sw = Stopwatch.createStarted();
		try {
			LOG.info(String.format("Get the selected option of the control %s", getLocator().toString()));
			selected = selection(timeOutInSeconds).getFirstSelectedOption().getText();
		} catch (StaleElementReferenceException ex) {
			if (sw.elapsed(TimeUnit.SECONDS) <= (long) timeOutInSeconds) {
				LOG.warning(String.format("Try to get the selected option of the control %s again",
						getLocator().toString()));
				getSelectedOption(timeOutInSeconds - (int) sw.elapsed(TimeUnit.SECONDS));
			}
		} catch (Exception error) {
			LOG.severe(String.format("Has error with control '%s': %s", getLocator().toString(), error.getMessage()));
			throw error;
		}
		return selected;
	}
	
	public String getSelectedOption() {
		return getSelectedOption(Common.ELEMENT_LONG_TIMEOUT);
	}

	public List<String> getOptions(int timeOutInSeconds) {
		List<String> options = new ArrayList<String>();
		if (timeOutInSeconds <= 0) {
			LOG.severe("The time out is invalid. It must greater than 0");
			return options;
		}
		Stopwatch sw = Stopwatch.createStarted();
		try {
			LOG.info(String.format("Get all the options of the control %s", getLocator().toString()));
			List<WebElement> elementsList = selection(timeOutInSeconds).getOptions();
			for (WebElement element : elementsList) {
				options.add(element.getText());
			}
		} catch (StaleElementReferenceException ex) {
			if (sw.elapsed(TimeUnit.SECONDS) <= (long) timeOutInSeconds) {
				LOG.warning(
						String.format("Try to get all the options of the control %s again", getLocator().toString()));
				getOptions(timeOutInSeconds - (int) sw.elapsed(TimeUnit.SECONDS));
			}
		} catch (Exception error) {
			LOG.severe(String.format("Has error with control '%s': %s", getLocator().toString(), error.getMessage()));
			throw error;
		}
		return options;
	}

	public List<String> getOptions() {
		return getOptions(Common.ELEMENT_LONG_TIMEOUT);
	}
	
//	public boolean isElementPresent(int timeOutInSeconds) {
//		try {
//			waitForPresent(timeOutInSeconds);
//			return isElementPresent();
//		} catch (Exception e) {
//			LOG.severe(String.format("IsDisplayed: Has error with control '%s': %s", getLocator().toString(),
//					e.getMessage()));
//			return false;
//		}
//	}
//	
//	public boolean isElementPresent() {
//		return isElementPresent(Common.ELEMENT_LONG_TIMEOUT);
//	}

}
