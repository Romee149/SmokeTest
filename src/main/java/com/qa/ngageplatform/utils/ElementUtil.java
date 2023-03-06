package com.qa.ngageplatform.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.ngageplatform.factory.DriverFactory;

/**
 * This Class is used to provide all methods related to selenium actions such as waits, actions, interactions etc
 *
 * @author Nahian Omar Faruqe
 * @version 1.0
 * @since 2022-09-28
 */
public class ElementUtil {

    private WebDriver driver;
    private JavaScriptUtil jsUtil;

    public ElementUtil(WebDriver driver) {
        this.driver = driver;
        jsUtil = new JavaScriptUtil(driver);
    }


    /**
     * This method is used to clear the Textfield
     *
     * @param locator Element Locator in By Format
     */
    public void doClear(By locator) {
        getElement(locator).clear();
    }


    /**
     * This method is used to enter the value in Textfield
     *
     * @param locator Element Locator in By Format
     * @param value   Value in String format
     */
    public void doSendKeys(By locator, String value) {
        doClear(locator);
        getElement(locator).sendKeys(value);
    }
    
    
    /**
     * This method is used to click on element
     *
     * @param locator Element Locator in By Format
     */
    public void doClick(By locator) {
        getElement(locator).click();
    }
    /**
	 * This method is used to perform mouse hover drag an element and drop on another element
	 *
	 * @param source and target in By Format
	 */
	public void dragAndDrop(By source,By target) {
		Actions action = new Actions(driver);
		this.waitForElementPresence(source, 50);
		this.waitForElementPresence(target, 50);
		WebElement sourceElement = this.getElement(source);
		WebElement targetElement = this.getElement(target);
		action.dragAndDrop(sourceElement, targetElement).build().perform();
	}
	

    /**
     * This method is used to click on element when element is ready
     *
     * @param locator         Locator in By Format
     * @param timeOutInSecond Timeout/Wait in Seconds
     */
    public void clickElementWhenReady(By locator, int timeOutInSecond) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSecond));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /**
     * This method is used to get the Text/value of Element
     *
     * @param locator Element Locator in By Format
     * @return Value of Element in String format
     */
    public String doGetText(By locator) {
    	//this.waitForElementPresence(locator, 10);
        return getElement(locator).getText();
    }
    
    
    /**
     * This method is used to get the Text/value of Element
     *
     * @param locator Element Locator in WebElement Format
     * @return Value of Element in String format
     */
    public String doGetText(WebElement locator) {
        return locator.getText();
    }


    /**
     * This method is used check the Existence of Element
     *
     * @param locator Element Locator in By Format
     * @return This will return TRUE is value exist otherwise FALSE
     */
    public boolean isElementExist(By locator) {
        if (getElements(locator).size() > 0)
            return true;
        return false;
    }


    /**
     * This method is used to return locator in By Format by taking Locator Type and Locator Value
     *
     * @param locatorType  Locator Type (such as id, name, xpath, cssSelector etc) in String Format
     * @param locatorValue Locator Value in String Format
     * @return This will return locator in By format
     */
    public By getBy(String locatorType, String locatorValue) {
        By locator = null;

        switch (locatorType.toLowerCase()) {
            case "id":
                locator = By.id(locatorValue);
                break;
            case "name":
                locator = By.name(locatorValue);
                break;
            case "classname":
                locator = By.className(locatorValue);
                break;
            case "xpath":
                locator = By.xpath(locatorValue);
                break;
            case "cssselector":
                locator = By.cssSelector(locatorValue);
                break;
            case "linktext":
                locator = By.linkText(locatorValue);
                break;
            default:
                System.out.println("please pass the right locator type and value.....");
                break;
        }
        return locator;
    }

    /**
     * This method is used to get WebElement by Taking Locator in By format
     *
     * @param locator Locator in By Format
     * @return This will return WebElement
     */
    public WebElement getElement(By locator) {
        WebElement element = driver.findElement(locator);
        if (Boolean.parseBoolean(DriverFactory.highlight)) {
            jsUtil.flash(element);
        }
        return element;
    }

    /**
     * This method is used to get the List of WebElement having common Locator
     *
     * @param locator Locator in By Format
     * @return This will return List of WebElement
     */
    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }


    /**
     * This method is used to get the List of All Elements
     *
     * @param locator Locator in By Format
     * @return This will return the List of String for All Elements
     */
    public List<String> getAllOptionsOfElement(By locator) {
        List<WebElement> list = this.getElements(locator);
        List<String> allOptions = new ArrayList<String>();
        for (WebElement option : list) {
            allOptions.add(option.getText().trim());
        }
        return allOptions;
    }

    /**
     * This method is used to return WebElement by taking Locator Type and Locator Value
     *
     * @param locatorType  Locator Type (such as id, name, xpath, cssSelector etc) in String Format
     * @param locatorValue Locator Value in String Format
     * @return This will return WebElement
     */
    public WebElement getElement(String locatorType, String locatorValue) {
        return driver.findElement(getBy(locatorType, locatorValue));
    }


    /****************************** Drop Down Utils ******************************/

    /**
     * This method is used to select value from dropdown by index
     *
     * @param locator Locator in By Format
     * @param index   index in Int format
     */
    public void doDropDownSelectByIndex(By locator, int index) {
        Select select = new Select(getElement(locator));
        select.selectByIndex(index);
    }

    /**
     * This method is used to select value from dropdown by visible text
     *
     * @param locator Locator in By Format
     * @param text    Visible Text in String format
     */
    public void doDropDownSelectByVisibleText(By locator, String text) {
        Select select = new Select(getElement(locator));
        select.selectByVisibleText(text);
    }

    /**
     * This method is used to select value from dropdown by value attribute text
     *
     * @param locator Locator in By Format
     * @param value   Value Attribute Text in String format
     */
    public void doDropDownSelectByValue(By locator, String value) {
        Select select = new Select(getElement(locator));
        select.selectByValue(value);
    }


    /**
     * This method is used to get the Default Selected value of the dropdown
     *
     * @param locator Locator in By Format
     * @return This will return the default selected value of the dropdown in String format
     */
    public String getDefaultSelectedValueForDropdown(By locator) {
        Select dropdown = new Select(driver.findElement(locator));
        return dropdown.getFirstSelectedOption().getText();
    }
    
    
    /**
     * This method is used to get the all values of the dropdown
     *
     * @param locator Locator in By Format
     * @return This will return the list of value of the dropdown 
     */
    public List<String> getAllOptionsForDropdown(By locator) {
    	List<String> list = new ArrayList<String>();
        Select dropdown = new Select(driver.findElement(locator));
        List<WebElement> elementList = dropdown.getOptions();
        for(WebElement element:elementList) {
        	list.add(element.getText());
        }
        return list;
    }

    /**************** wait util for Non-WebElements *****************/

    /**
     * This method is used to wait for Title and Return the Title if found
     *
     * @param titleFraction Title SubText in String format
     * @param timeOut       Timeout/Wait in Int Format
     * @return This will return the Title in String format
     */
    public String doGetTitleWithFraction(String titleFraction, int timeOut) {
        if (waitForTitleContains(titleFraction, timeOut)) {
            return driver.getTitle();
        }
        return null;
    }

    /**
     * This method is used to wait for the Title
     *
     * @param titleFraction Title SubText in String format
     * @param timeOut       Timeout/Wait in Int Format
     * @return This will return TRUE if title exists otherwise FALSE
     */
    public boolean waitForTitleContains(String titleFraction, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.titleContains(titleFraction));
    }

    /**
     * This method is used to wait for the presence of Element
     *
     * @param locator Locator in By Format
     * @param timeOut Timeout/Wait in Int Format
     */
    public void waitForElementPresence(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * This method is used to provide static wait
     *
     * @param timeOutInSecond Timeout/Wait in Seconds
     */
    public void wait(int timeOutInSecond) {
        try {
            Thread.sleep(timeOutInSecond * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    
    /**
     * This method is used to wait for the presence of Element by refreshing the element
     *
     * @param locator Locator in By Format
     * @param timeOut Timeout/Wait in Int Format
     */
    public void waitForElementPresenceWithRefreshEvent(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(this.getElement(locator))));
    }

    /**
     * This method is used to wait for the Element to be clickable
     *
     * @param locator Locator in By Format
     * @param timeOut Timeout/Wait in Int Format
     */
    public void waitForElementToBeClickable(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.elementToBeClickable(this.getElement(locator)));
    }
    

    /**************** frame util *****************/

    /**
     * This method is used switch to Frame by ID
     *
     * @param frameId FrameId in String format
     */
    public void switchToFrame(String frameId) {
        driver.switchTo().frame(frameId);
    }


    /**
     * This method is used switch to Frame if Frame Exists
     *
     * @param locator Locator in By Format
     */
    public void switchToFrameIfExists(By locator) {
        try {
            driver.switchTo().frame(getElement(locator));
        } catch (Exception e) {
        }
    }


    /**
     * This method is used switch to Frame if Frame Exists with Wait Timeout
     *
     * @param frameId         FrameId in String format
     * @param timeOutInSecond Timeout/Wait in Seconds
     */
    public void switchToFrameIfExists(String frameId, int timeOutInSecond) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));
        } catch (Exception e) {
        }
    }

    
    /**
	 * This method is used switch to Frame if Frame Exists with Wait Timeout
	 *
	 * @param frameLocator 	Frame Locator in By format
	 * @param timeOutInSecond Timeout/Wait in Seconds
	 */
	public void switchToFrameIfExists(By frameLocator, int timeOutInSecond) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
		} catch (Exception e) {
		}
	}
    
    /**
     * This method is used switch to Frame with Wait Timeout
     *
     * @param frameId         FrameId in String format
     * @param timeOutInSecond Timeout/Wait in Seconds
     */
    public void switchToFrame(String frameId, int timeOutInSecond) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));
    }

    /**
     * This method is used switch to Frame by taking locator
     *
     * @param locator Locator in By Format
     */
    public void switchToFrame(By locator) {
        driver.switchTo().frame(getElement(locator));
    }


    /**
     * This method is used switch to Frame by taking locator with Wait Timeout
     *
     * @param locator         Locator in By Format
     * @param timeOutInSecond Timeout/Wait in Seconds
     */
    public void switchToFrame(By locator, int timeOutInSecond) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }


    /**
     * This method is used to switch to Default Content
     */
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }


    /**
     * This method is used to switch to different window using Window Reference
     *
     * @param windowRef Window Reference in String format
     */
    public void switchToWindow(String windowRef) {
        driver.switchTo().window(windowRef);
    }


    /**************** Action Util *****************/

    /**
     * This method is used to perform mouse hover on two elements consecutively
     * Example: mousehover to first element and then mousehover to second element
     *
     * @param firstElement  Mousehover to first element
     * @param secondElement Mousehover to second element
     */
    public void moveToElementAndClick(By firstElement, By secondElement) {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(firstElement))
                .moveToElement(driver.findElement(secondElement))
                .click(driver.findElement(secondElement)).build().perform();
    }

    
    /**
     * This method is used to perform mouse hover on four elements consecutively
     * Example: mousehover to first element and then mousehover to second element
     *
     * @param firstElement  Mousehover to first element
     * @param secondElement Mousehover to second element
     * @param thirdElement Mousehover to third element
     * @param fourthElement Mousehover to fourth element
     */
    public void moveToElementAndClick(By firstElement, By secondElement, By thirdElement , By fourthElement) {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(firstElement)).perform();
        this.wait(5);
        action.moveToElement(driver.findElement(secondElement)).perform();
        this.wait(5);
        action.moveToElement(driver.findElement(thirdElement)).perform();
        this.wait(5);
        action.moveToElement(driver.findElement(fourthElement)).perform();
        this.wait(5);
        action.click(driver.findElement(fourthElement)).build().perform();
    }

    /**
     * This method is used to get the value of provided Attribute
     *
     * @param locator  Locator in By Format
     * @param attrName Attribute Name in String format
     * @return Attribute value in String format
     */
    public String getAttributeValue(By locator, String attrName) {
        String attrValue = getElement(locator).getAttribute(attrName);
        System.out.println(attrValue);
        return attrValue;
    }


    /**
     * This method is used to press Enter Key
     */
    public void pressEnterKey(By element) {       
    	this.getElement(element).sendKeys(Keys.ENTER);   
    }


    /**
     * This method is used to perform Right Click on Element
     *
     * @param locator Locator in By Format
     */
    public void doRightClickOnElement(By locator) {
        try {
            Actions action = new Actions(driver);
            action.contextClick(driver.findElement(locator)).perform();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    
    /**
     * This method is used to return the selection status for the checkbox
     *
     * @param locator Locator in By Format
     * @return This will return True if checkbox selected otherwise False
     */
    public boolean isCheckboxSelected(By locator) {
       return this.getElement(locator).isSelected();
    }
    
    
    /**
     * This method is used to refresh the page
     *
     * 
     */
    public void refreshPage() {
        driver.navigate().refresh();
    }


public void doSelectDropDownValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		for (WebElement e : optionsList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	/**************** Window Util *****************/
	/**
	 * This method is used to see how many windows are opened
	 *
	 */
	public int getWindowTabCount() {
		Set<String> allWindows = driver.getWindowHandles();
		ArrayList<String> tabs = new ArrayList<String>(allWindows);
		System.out.println(tabs.size());
		return tabs.size();

	}

	/**
	 * This method is used to verify element is displayed/present
	 *
	 * @param locator Locator in By Format
	 */
	public boolean isDisplay(By locator) {
		boolean result = getElement(locator).isDisplayed();
		return result;
	}
	
	public boolean isEnabled(By locator) {
		boolean result = getElement(locator).isEnabled();
		return result;
	}
	
	
	
	/**
	 * This method is used to perform Click on Element using Action class when element not clickable easily
	 *
	 * @param locator Locator in By Format
	 */
	public void doClickByActionClass(By locator) {
		try {
			Actions action = new Actions(driver);
			action.click(driver.findElement(locator)).build().perform();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to perform mouse hover on a specific element
	 *
	 * @param webElement  Mousehover to an webelement
	 */
	public void moveToElementAndClick(By webElement) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(webElement))
				.click(driver.findElement(webElement)).build().perform();
	}
	
	
	public void clickByJS(By webElement) {
		jsUtil.clickElementByJS(driver.findElement(webElement));
	}

}
