package com.qa.ngageplatform.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.qa.ngageplatform.listeners.ExtentReportListener;
import com.qa.ngageplatform.utils.AssertionUtil;
import com.qa.ngageplatform.utils.CommonUtil;
import com.qa.ngageplatform.utils.ElementUtil;

public class MyWorkSimplifiedPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private MainPage mainPage;

	CommonUtil comUtil = new CommonUtil();
	WorkItemsScreen workItemsScreen;
	private By activityIframe = By.id("iframe_110");
	private By sDropdown = By.xpath("//select[@id='ddActivities']");
	private By cActivityA = By.xpath("//select[@id='ddActivities']/child::option[@value='100027']");
	private By docIdColumnHead = By.xpath("//div/parent::th[@title='Doc ID']");

	private By docIdData1st = By.xpath("(//td[@aria-describedby='SimplifiedWorkflowSearchResultsTable_Doc ID'])[1]");
	private By docIdData2st = By.xpath("(//td[@aria-describedby='SimplifiedWorkflowSearchResultsTable_Doc ID'])[2]");
	private By headSearch = By.xpath("//h3[@id='ui-id-1']");

	private By inputDocID = By.xpath("//input[@id='-100']");
	private By searchDoc = By.xpath("//input[@value='Search']");

	private By pageNoInfo = By.xpath("//div[@class='ui-paging-info']");
	private By docID = By.xpath("//td[@aria-describedby='SimplifiedWorkflowSearchResultsTable_Doc ID']");
	private By docStartDate = By
			.xpath("(//td[@aria-describedby='SimplifiedWorkflowSearchResultsTable_Doc Create Date'])[1]");
	private By processDueDate = By
			.xpath("(//td[@aria-describedby='SimplifiedWorkflowSearchResultsTable_Process Due Date'])[1]");
	private By docIdHead = By.xpath("//div[contains(text(),'Doc ID')]");

	private By selectAllIcon = By.id("cb_SimplifiedWorkflowSearchResultsTable");
	private By openAllSelectedIcon = By.xpath("//span[@class='ui-icon ui-icon-folder-open']");

	private By customerInformation = By.xpath("//span[contains(text(),'Customer Information')]/parent::a");
	private By newWindowIframe = By.xpath("//iframe[@id='ContentPlaceHolder1_iPage']");
	private By customerNameInfo = By.xpath("//*[@name='eform_mcb67676$phBO_3_BO$eidmKey_Customer_Name']");
	private By customerDetailsInfo = By.xpath("//input[@name='eform_mcb67676$phBO_3_BO$eidmKey_Customer_details']");

	private By inputPageNumber = By.xpath("//input[@class='ui-pg-input']");
	private By getNextItem = By.xpath("//input[@name='btnGetNext']");

	private By processDueDateColumnHeader = By
			.xpath("//th[@id='SimplifiedWorkflowSearchResultsTable_Process Due Date']");

	public MyWorkSimplifiedPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		eleUtil.switchToDefaultContent();
		mainPage = new MainPage(driver);
	}

	/**
	 * This method is used to Switch to MyWorkSimplified Screen frame
	 *
	 * @return This will return the Object of MyWorkSimplified class Object
	 */
	public MyWorkSimplifiedPage switchToPageFrame() {
		eleUtil.switchToFrame(this.activityIframe, 20);
		return this;
	}

	/**
	 * This method is used to sendKeys for MyWorkSimplified Screen frame
	 *
	 * @return This will return the Object of MyWorkSimplified class Object
	 */
	public MyWorkSimplifiedPage doSendKeysMWS(By InputLocator, String value) {
		this.switchToPageFrame();
		eleUtil.waitForElementPresence(InputLocator, 10);
		eleUtil.doClear(InputLocator);
		eleUtil.doSendKeys(InputLocator, value);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to verify Search Head Bar is displayed in
	 * MyWorkSimplified Screen frame
	 * 
	 * @param value Value which we need to check
	 * @return This will return true if Search Head Bar is displayed
	 */
	public boolean isSearchHeadDisplay() {
		this.switchToPageFrame();
		eleUtil.waitForElementPresence(this.headSearch, 50);
		boolean searchDisplay = eleUtil.isDisplay(this.headSearch);
		eleUtil.switchToDefaultContent();
		return searchDisplay;
	}

	/**
	 * This method is used to select Activity Closure Action - Activity A from Drop
	 * down
	 *
	 * @return This will return the Object of WorkItemScreen class Object
	 */
	public WorkItemsScreen selectCloserActionActivityA(String value) {
		try {
			this.switchToPageFrame();
			eleUtil.doSelectDropDownValue(this.sDropdown, value);
			eleUtil.switchToDefaultContent();
			eleUtil.wait(5);
			ExtentReportListener.test.get().log(Status.INFO, "Selected Activities \"" + value + "\" successfully");
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while selecting Activities \"" + value + "\"");
			Assert.fail(e.getMessage());
		}
		return new WorkItemsScreen(this.driver);

	}

	/**
	 * This method is used to Get Current selected Activity
	 *
	 * @return This will return the selected activity text
	 */
	public String getActivityText() {
		String activityInfo = null;
		try {

			this.switchToPageFrame();
			eleUtil.waitForElementPresence(this.cActivityA, 50);
			activityInfo = eleUtil.doGetText(this.cActivityA);
			ExtentReportListener.test.get().log(Status.INFO,
					"Fetching activities Info \'" + activityInfo + "\' successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while fetching activities Info");
			Assert.fail(e.getMessage());
		}
		return activityInfo;
	}

	/**
	 * This method is used to click on SearchHeadBar for MyWorkSimplified Screen
	 * frame
	 *
	 * @return This will return the Object of MyWorkSimplified class Object
	 */
	public MyWorkSimplifiedPage clickOnHeadSearch() {
		eleUtil.doClick(this.headSearch);
		return this;
	}

	/**
	 * This method is used to double click on DocID Header for MyWorkSimplified
	 * Screen frame
	 *
	 * @return This will return the Object of MyWorkSimplified class Object
	 */
	public MyWorkSimplifiedPage doubleClickonDocIdHeader() {
		Actions action = new Actions(driver);
		this.switchToPageFrame();
		eleUtil.waitForElementPresence(this.docIdColumnHead, 50);
		WebElement element = driver.findElement(this.docIdColumnHead);
		action.doubleClick(element).perform();
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to get first DocID from search resut
	 *
	 * @return This will return the first DocID
	 */
	public String getFirstDocID() {
		String docID = eleUtil.doGetText(this.docIdData1st);
		return docID;
	}

	/**
	 * This method is used to enter DocID value to be searched & click on search
	 * button
	 *
	 * @return This will return the Object of MyWorkSimplified class Object
	 */
	public MyWorkSimplifiedPage enterDocIDAndSearch() {
		workItemsScreen.clickOnSearchHeaderBar();
		eleUtil.doSendKeys(this.inputDocID, getFirstDocID());
		eleUtil.doClick(this.searchDoc);
		return this;
	}

	/**
	 * This method is used to verify Search Result grid contains 1 record only
	 *
	 * @return This will return the page Information text
	 */
	public String verifyRecordNumber1() {
		String pageInfo = null;
		try {
			this.switchToPageFrame();
			eleUtil.waitForElementPresence(pageNoInfo, 10);
			eleUtil.wait(2);
			pageInfo = eleUtil.doGetText(this.pageNoInfo);
			ExtentReportListener.test.get().log(Status.INFO, "Fetching page Info \'" + pageInfo + "\' successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while fetching page Info");
			Assert.fail(e.getMessage());
		}
		return pageInfo;
	}

	/**
	 * This method is used to get Doc Id from search result page
	 *
	 * @return This will return the Doc Id of search result
	 */
	public String getResultDocId() {
		String actualResultDocId = eleUtil.doGetText(this.docID);
		return actualResultDocId;
	}

	/**
	 * This method is used to click on Process Due Date Column Header
	 *
	 * @return This will return the Object of MyWorkSimplified class
	 */
	public MyWorkSimplifiedPage clickOnProcessDueDateColumnHeader() {
		this.switchToPageFrame();
		eleUtil.waitForElementPresence(this.processDueDateColumnHeader, 20);
		eleUtil.doClick(this.processDueDateColumnHeader);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to sort record by descending order
	 *
	 * @return This will return the Object of MyWorkSimplified class
	 */
	public MyWorkSimplifiedPage sortDocIdDescending() {
		this.switchToPageFrame();
		eleUtil.doClick(docIdHead);
		eleUtil.switchToDefaultContent();
		this.switchToPageFrame();
		eleUtil.doClick(docIdHead);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to verify Records in table are more than start date
	 *
	 * @return This will return true if Process Due Date Start and End both are
	 *         greater than the created date of doc
	 */
	public boolean verifyProcessDueDateGreater() {
		this.switchToPageFrame();
		eleUtil.waitForElementPresence(this.docStartDate, 10);
		String docSDate = eleUtil.doGetText(docStartDate);
		eleUtil.switchToDefaultContent();
		this.switchToPageFrame();
		String pDueDate = eleUtil.doGetText(processDueDate);
		eleUtil.switchToDefaultContent();
		boolean verifydate = comUtil.verifyFirstDateIsGreater(pDueDate, docSDate, "MM-dd-yyyy hh:mm:ss a");
		return verifydate;
	}

	/**
	 * This method is used to enter Recent Doc ID and Search
	 *
	 * @return This will return the Object of MyWorkSimplified class
	 */
	public MyWorkSimplifiedPage enterRecentDocIDAndSearch(String latestdocId) {
		this.switchToPageFrame();
		eleUtil.doSendKeys(this.inputDocID, latestdocId);
		eleUtil.switchToDefaultContent();
		this.switchToPageFrame();
		eleUtil.doClick(this.searchDoc);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to click On Select All Icon
	 *
	 * @return This will return the Object of MyWorkSimplified class
	 */
	public MyWorkSimplifiedPage clickOnAllIconSelectCheckbox() {
		this.switchToPageFrame();
		eleUtil.doClick(selectAllIcon);
		eleUtil.switchToDefaultContent();

		return this;
	}

	/**
	 * This method is used to click on Open All Selected Icon
	 *
	 * @return This will return the Object of MyWorkSimplified class
	 */
	public MyWorkSimplifiedPage clickOnOpenAllSelectedIcon() {
		this.switchToPageFrame();
		eleUtil.doClick(openAllSelectedIcon);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to click on Customer Information tab after land the new
	 * window
	 *
	 * @return This will return the Object of MyWorkSimplified class
	 */
	public MyWorkSimplifiedPage clickOnCustomerInformation() {
		Set<String> allWindows = this.driver.getWindowHandles();
		eleUtil.switchToWindow((allWindows.toArray(new String[allWindows.size()]))[1]);
		eleUtil.waitForElementPresence(this.customerInformation, 80);
		eleUtil.doClick(this.customerInformation);
		eleUtil.wait(5);
		return this;
	}

	/**
	 * This method is used to get customer name info after switch child window
	 *
	 * @return This will return the customer name
	 */
	public String getCustomerNameInfo() {
		eleUtil.switchToFrameIfExists(this.newWindowIframe);
		eleUtil.waitForElementPresence(this.customerNameInfo, 50);
		String customerNInfo = driver.findElement(this.customerNameInfo).getAttribute("value");
		return customerNInfo;

	}

	/**
	 * This method is used to verify search section is hidden
	 *
	 * @return This will return true if search result is hidden
	 */
	public Boolean verifySearchSectionHidden() {
		Boolean result = false;

		try {
			this.switchToPageFrame();
			String ariaExpandCondition = eleUtil.getAttributeValue(this.headSearch, "aria-expanded");
			if (ariaExpandCondition.contains("false")) {
				result = true;
			}
			ExtentReportListener.test.get().log(Status.INFO,
					"Search section is hidden \'" + result + "\' successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Search section is not hidden");
			Assert.fail(e.getMessage());
		}

		return result;

	}

	/**
	 * This method is used to get customer details information
	 *
	 * @return This will return customer details info
	 */
	public String getCustomerDetailInfo() {
		eleUtil.waitForElementPresence(this.customerDetailsInfo, 50);
		String customerDInfo = driver.findElement(this.customerDetailsInfo).getAttribute("value");
		return customerDInfo;
	}

	// MWS008
	/**
	 * This method is used to put value 3 in input feild of page and press enter
	 *
	 * @return This will return the object of 3 page
	 */
	public void enterPageNumberAndPressEnterKey() {

		this.doSendKeysMWS(inputPageNumber, "3");
		this.switchToPageFrame();
		driver.findElement(inputPageNumber).sendKeys(Keys.ENTER);
		eleUtil.switchToDefaultContent();
	}

	/**
	 * This method is used to page information in text
	 *
	 * @return This will return the page info
	 */
	public String getPageInfo() {
		this.switchToPageFrame();
		String pInfo = eleUtil.doGetText(this.pageNoInfo);
		eleUtil.switchToDefaultContent();
		return pInfo;
	}

	// MWS009
	/**
	 * This method is used to verify record sorted by Ascending order
	 *
	 * @return This will return the Object of MyWorkSimplified class
	 */
	public MyWorkSimplifiedPage verifyRecordSortedAscOrder() {
		this.switchToPageFrame();
		String firstDocId = eleUtil.doGetText(this.docIdData1st);
		eleUtil.switchToDefaultContent();
		this.switchToPageFrame();
		String secondDocId = eleUtil.doGetText(this.docIdData2st);
		eleUtil.switchToDefaultContent();
		int firstDocIdNum = comUtil.convertStringToNumber(firstDocId);
		int secondDocIdNum = comUtil.convertStringToNumber(secondDocId);
		AssertionUtil.verifyGreaterThan(secondDocIdNum, firstDocIdNum, "Records are sorted in descending order");

		return this;
	}

	/**
	 * This method is used to verify record sorted by descending order
	 *
	 * @return This will return the Object of MyWorkSimplified class
	 */
	public MyWorkSimplifiedPage verifyRecordSortedDscOrder() {
		this.sortDocIdDescending();
		eleUtil.wait(5);
		this.switchToPageFrame();
		String firstDocId = eleUtil.doGetText(this.docIdData1st);
		eleUtil.switchToDefaultContent();
		this.switchToPageFrame();
		String secondDocId = eleUtil.doGetText(this.docIdData2st);
		eleUtil.switchToDefaultContent();
		int firstDocIdNum = comUtil.convertStringToNumber(firstDocId);
		int secondDocIdNum = comUtil.convertStringToNumber(secondDocId);
		AssertionUtil.verifyGreaterThan(firstDocIdNum, secondDocIdNum, "Records are sorted in descending order");
		return this;
	}

	// MWS010
	/**
	 * This method is used to check 'Get Next Item' is exist or not for closure
	 * Action activity option
	 *
	 * @return This will return the true if 'Get Next Item' is displayed
	 */
	public boolean getNextItemDisplayed() {
		this.switchToPageFrame();
		eleUtil.waitForElementPresence(this.getNextItem, 50);
		boolean nextItemDisplay = eleUtil.isDisplay(getNextItem);
		eleUtil.switchToDefaultContent();
		if (nextItemDisplay = true) {
			System.out.println("Get Next Item is displayed");
		}
		return nextItemDisplay;
	}

	/**
	 * This method is used to check 'Get Next Item' is exist or not for Corresponse
	 * option
	 *
	 * @return This will return the true if 'Get Next Item' is not displayed
	 */
	public boolean selectCorrespondenceGenerationAndVerifyGetNextItemNotDisplayed() {
		String correspond = "Correspondence Generation - Correspondence";
		boolean flag = false;
		this.switchToPageFrame();
		eleUtil.doSelectDropDownValue(this.sDropdown, correspond);
		eleUtil.switchToDefaultContent();
		if (driver.getPageSource().contains("Get Next Item") == false) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}
}
