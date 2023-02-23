package com.qa.ngageplatform.pages;

/**
 * This Class is used to provide Object Repo and Actions related to Repository
 * Module
 *
 * @author Romee Afroz
 * @version 1.0
 * @since 2022-11-14
 */
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.aventstack.extentreports.Status;
import com.qa.ngageplatform.listeners.ExtentReportListener;
import com.qa.ngageplatform.utils.AssertionUtil;
import com.qa.ngageplatform.utils.CommonUtil;
import com.qa.ngageplatform.utils.ElementUtil;
import com.qa.ngageplatform.utils.JavaScriptUtil;

public class RepositoryPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private JavaScriptUtil jsUtil;

	CommonUtil comUtil = new CommonUtil();
	WorkItemsScreen workItemsScreen;
	String dateFormat = "MM-dd-yyyy";
	String dateTimeFormat = "MM-dd-yyyy hh:mm:ss a";
	String CurrentDate = comUtil.getCurrentDateTime(dateFormat);
	String dateValue = comUtil.getDateTime(0, dateFormat);
	String dateTimeValue = comUtil.getDateTime(0, dateTimeFormat);

	private By repositoryIframe = By.id("iframe_104");
	private By repoIframe = By.id("ADVMAINTAB_iframe");
	private By selectRepository = By.xpath("//select[@id='WebComboEDMRepository']");
	private By selectSearchFor = By.xpath("//select[@id='webcombosearchclass']");
	private By searchHeadBar = By.xpath("//div[@id='DivAdvWebPanelSearch']/child::h3");
	private By searchBtn = By.id("btnSearch");
	private By searchResultPageInfo = By.xpath("(//div[@class='ui-paging-info'])[1]");
	private By savedSearchResultPageInfo = By.xpath("//div[@class='ui-paging-info']");
	private By inputPageNum = By.xpath("(//input[@class='ui-pg-input'])[1]");
	private By docIdHead = By.xpath("// div[@id='jqgh_divSearchResultsTable_Doc ID']");
	private By docIdColumnValue = By.xpath("(//td[@aria-describedby='divSearchResultsTable_Doc ID'])[1]");
	private By docIdColumnValue2 = By.xpath("(//td[@aria-describedby='divSearchResultsTable_Doc ID'])[2]");
	private By inputdocIdFeild = By.xpath("//input[@name='Doc___ID']");
	private By inputCurrencyS = By.xpath("//input[@name='BM___Currency_1']");
	private By inputCurrencyE = By.xpath("//input[@name='BM___Currency_2']");
	private By clearBtn = By.xpath("//input[@name='btnClear']");
	private By inputDateStart = By.xpath("//input[@name='Date___range_1']");
	private By inputDateEnd = By.xpath("//input[@name='Date___range_2']");
	private By dateRangeHeadBar = By.xpath("//div[contains(text(),'Date range')]");
	private By resultDateRange = By.xpath("(//td[@aria-describedby='divSearchResultsTable_Date range'])[1]");
	private By savedSearchBar = By.xpath("//span[@id='lblSavedSearchHeader']");
	private By saveSearchBtn = By.xpath("//input[@id='btnSaveSearchAdv']");
	private By inputSaveDescriptionField = By.name("txtSaveDesc");
	private By saveDescBtn = By.name("btnSave");
	private By modifiedDateHeadCol = By.xpath("//div[@id='jqgh_tblSavedSearchResults_MODDATE']");
	private By latestDescriptionResult = By.xpath("(//td[@aria-describedby='tblSavedSearchResults_DESC'])[1]");
	private By recordSavedSearch = By.xpath("//*[@id='tblSavedSearchResults_toppager_right']/div");
	private By recallButton = By.xpath("//*[@id='btnRecall']");
	private By deleteButton = By.xpath("//*[@id='btnDelete']");
	private By alertError = By.xpath("//*[@id='dialog']");
	private By alertOkBtn = By.xpath("	//*[contains(text(),'Ok')]");
	private By searchResultBar = By.xpath("//div[@id='DivAdvWebPanelResults']/child::h3");
	private By closureLink = By.xpath("(//a[contains(text(),'Closure')]//ancestor::li[1]/i)[1]");
	private By closureBranchLink = By.xpath("(//a[contains(text(),'Closure')]//ancestor::li[1]/i)[2]");
	private By closureDateFolder = By.xpath("//a[contains(text(),'" + CurrentDate + "')]");

	private By customerNameHead = By.xpath("//*[contains(text(), 'Customer Name')]");
	private By resetLayoutIcon = By.id("divSearchResultsTable_btnResetLayout");
	private By setLayoutIcon = By.id("divSearchResultsTable_btnSetLayout");
	private By closureIframe = By.id("BROWSETAB_iframe");
	private By docIdBeforeSwitch = By.xpath("//*[@id='divSearchResultsTable']/tbody/tr[2]/td[7]");
	private By docIdAfterSwitch = By.xpath("//*[@id='divSearchResultsTable']/tbody/tr[2]/td[8]");
	private By docIdColumnHead = By.xpath("//*[contains(text(), 'Doc ID')]");

	public RepositoryPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		eleUtil.switchToDefaultContent();
		jsUtil = new JavaScriptUtil(driver);

	}

	/**
	 * This method is used to select Search Item for repository from Drop Down down
	 *
	 * @param value1 String value to select repository search doc class
	 * @param value2 String value to select repository search doc type
	 * @return This will return the Object of repositoryPage class Object
	 */
	public RepositoryPage selectRepoSearch(String value1, String value2) {
		eleUtil.switchToFrameIfExists(this.repositoryIframe, 2);
		eleUtil.switchToFrameIfExists(this.repoIframe, 2);
		eleUtil.doSelectDropDownValue(this.selectRepository, value1);
		eleUtil.switchToFrameIfExists(this.repoIframe, 5);
		eleUtil.doDropDownSelectByVisibleText(this.selectSearchFor, value2);
		return this;

	}

	/**
	 * This method is used to click search button for search result
	 *
	 * @return This will return the Object of repositoryPage class Object
	 */
	public RepositoryPage clickRepoSearchBtn() {

		eleUtil.switchToFrameIfExists(this.repoIframe, 5);
		eleUtil.doClick(this.searchBtn);
		eleUtil.wait(2);
		eleUtil.switchToDefaultContent();
		return this;

	}

	/**
	 * This method is used to validate result record
	 *
	 * @return This will return true if search has at least one record
	 */
	public boolean validateSearchResultRecord() {
		eleUtil.switchToFrameIfExists(this.repositoryIframe);
		eleUtil.switchToFrameIfExists(this.repoIframe);
		String pInfo = eleUtil.doGetText(this.savedSearchResultPageInfo);
		String resultCount = pInfo.substring(pInfo.length() - 3);
		int cnt = Integer.parseInt(resultCount.trim());
		eleUtil.switchToDefaultContent();
		if (cnt >= 1) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * This method is used to get latest DocId
	 *
	 * @return This will return String Doc Value of the first Doc column
	 */
	public String getLatestDocId() {
		eleUtil.switchToFrameIfExists(this.repositoryIframe);
		eleUtil.switchToFrameIfExists(this.repoIframe);
		eleUtil.doClick(this.docIdHead);
		eleUtil.doClick(this.docIdHead);
		eleUtil.waitForElementPresence(this.docIdColumnValue, 10);
		eleUtil.wait(2);
		String docValueL = eleUtil.doGetText(this.docIdColumnValue);
		eleUtil.switchToDefaultContent();
		return docValueL;
	}

	/**
	 * This method is used to put DocId, Currency Range and click on clear button
	 *
	 * @return This will return the Object of repositoryPage class Object
	 */
	public RepositoryPage enterDocIdAndCurrency() {
		String docid = this.getLatestDocId();
		eleUtil.switchToFrameIfExists(this.repositoryIframe);
		eleUtil.switchToFrameIfExists(this.repoIframe);
		eleUtil.doClick(this.searchHeadBar);
		eleUtil.doSendKeys(this.inputdocIdFeild, docid);
		eleUtil.doSendKeys(this.inputCurrencyS, "1");
		eleUtil.doSendKeys(this.inputCurrencyE, "50");
		eleUtil.switchToDefaultContent();
		return this;

	}

	/**
	 * This method is used to validate search filed should be empty after clear btn
	 *
	 * @return This will return boolean value of search field Format
	 */
	public boolean verifySearchInputFieldBlank() {
		this.enterDocIdAndCurrency();
		eleUtil.switchToFrameIfExists(this.repositoryIframe);
		eleUtil.switchToFrameIfExists(this.repoIframe);
		eleUtil.doClick(this.clearBtn);
		eleUtil.wait(2);
		String docIdField = eleUtil.doGetText(this.inputdocIdFeild);
		String startCurrency = eleUtil.doGetText(this.inputCurrencyS);
		String endCurrency = eleUtil.doGetText(this.inputCurrencyE);
		if (docIdField.isEmpty() & startCurrency.isEmpty() & endCurrency.isEmpty()) {
			return true;
		} else {
			return false;

		}
	}

	/**
	 * This method is used to put Start and End date and click search to see the
	 * Date Time document result
	 *
	 * @return This will return the Object of repositoryPage class Object
	 */
	public RepositoryPage enterDateStartAndEndAndSearch() {
		eleUtil.doSendKeys(this.inputDateStart, comUtil.getDateTime(0, dateFormat));
		eleUtil.doSendKeys(this.inputDateEnd, comUtil.getDateTime(10, dateFormat));
		eleUtil.doClick(this.searchBtn);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to validate date range of search result should be within
	 * start and end date range
	 *
	 * @return This will return boolean true if records are within specified date
	 *         range
	 */
	public boolean validateDateRangeForStartAndEndDate() {
		eleUtil.waitForElementPresence(this.inputDateStart, 10);
		eleUtil.doSendKeys(this.inputDateStart, comUtil.getDateTime(0, dateFormat));
		String startInputDate = comUtil.getDateTime(0, dateFormat);
		eleUtil.waitForElementPresence(this.inputDateEnd, 10);
		eleUtil.doSendKeys(this.inputDateEnd, comUtil.getDateTime(10, dateFormat));
		String endInputDate = comUtil.getDateTime(10, dateFormat);
		eleUtil.clickElementWhenReady(this.searchBtn, 10);
		eleUtil.wait(1);
		eleUtil.clickElementWhenReady(this.dateRangeHeadBar, 10);
		eleUtil.clickElementWhenReady(this.dateRangeHeadBar, 10);
		eleUtil.wait(2);
		String rangeDate = eleUtil.doGetText(this.resultDateRange);
		eleUtil.switchToDefaultContent();
		boolean condition1 = comUtil.verifyFirstDateIsGreaterOrEqual(rangeDate, startInputDate, "MM-dd-yyyy");
		boolean condition2 = comUtil.verifyFirstDateIsGreaterOrEqual(endInputDate, rangeDate, "MM-dd-yyyy");
		if (condition1 & condition2 == true) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is used to validate date range after search should be equal or
	 * greater than start date range
	 *
	 * @return This will return boolean true if range date of search result is
	 *         greater than or equal to range start date
	 */
	public boolean validateRangeForStartDate() {
		eleUtil.switchToFrameIfExists(this.repositoryIframe);
		eleUtil.switchToFrameIfExists(this.repoIframe);
		eleUtil.clickElementWhenReady(this.searchHeadBar, 2);
		eleUtil.doClick(this.clearBtn);
		eleUtil.wait(2);
		eleUtil.doSendKeys(this.inputDateStart, comUtil.getDateTime(-7, dateFormat));
		String startInputDate = comUtil.getDateTime(-7, dateFormat);
		eleUtil.doClick(this.searchBtn);
		eleUtil.wait(2);
		eleUtil.doClick(this.dateRangeHeadBar);
		eleUtil.doClick(this.dateRangeHeadBar);
		eleUtil.wait(2);
		String dateRange = eleUtil.doGetText(this.resultDateRange);
		eleUtil.switchToDefaultContent();
		return comUtil.verifyFirstDateIsGreaterOrEqual(dateRange, startInputDate, "MM-dd-yyyy");
	}

	/**
	 * This method is used to validate date range after search should be equal or
	 * less than end date range
	 *
	 * @return This will return boolean true if range date of search result is less
	 *         than or equal to range end date
	 */
	public boolean validateRangeEndDate() {
		eleUtil.switchToFrameIfExists(this.repositoryIframe);
		eleUtil.switchToFrameIfExists(this.repoIframe);
		eleUtil.doClickByActionClass(this.searchHeadBar);
		eleUtil.doClick(this.clearBtn);
		eleUtil.wait(2);
		eleUtil.waitForElementPresence(this.inputDateEnd, 20);
		eleUtil.doSendKeys(this.inputDateEnd, comUtil.getDateTime(0, dateFormat));
		String endInputDate = comUtil.getDateTime(0, dateFormat);
		eleUtil.doClick(this.searchBtn);
		eleUtil.wait(2);
		eleUtil.doClick(this.dateRangeHeadBar);
		eleUtil.wait(2);
		String dateRange = eleUtil.doGetText(this.resultDateRange);
		eleUtil.switchToDefaultContent();
		return comUtil.verifyFirstDateIsGreaterOrEqual(endInputDate, dateRange, "MM-dd-yyyy");
	}

	/**
	 * This method is used to Validate Search criteria is displayed in the saved
	 * search grid
	 * 
	 * @param String description value for created variable name of saved search
	 *               description
	 * @return This will return boolean true if Search description is valid
	 */
	public String validateSearchDescription(String description) {
		eleUtil.switchToFrameIfExists(this.repositoryIframe);
		eleUtil.switchToFrameIfExists(this.repoIframe);
		eleUtil.waitForElementPresence(this.searchHeadBar, 5);
		eleUtil.doClick(this.searchHeadBar);
		eleUtil.doSendKeys(this.inputDateStart, comUtil.getDateTime(0, dateFormat));
		eleUtil.wait(1);
		eleUtil.doSendKeys(this.inputDateEnd, comUtil.getDateTime(10, dateFormat));
		eleUtil.wait(1);
		eleUtil.waitForElementPresence(this.searchBtn, 5);
		eleUtil.doClick(this.searchBtn);
		eleUtil.wait(1);
		eleUtil.doClick(this.saveSearchBtn);
		eleUtil.wait(2);
		eleUtil.doSendKeys(this.inputSaveDescriptionField, description);
		eleUtil.doClick(this.saveDescBtn);
		eleUtil.wait(1);
		try {
			String alertError = eleUtil.doGetText(this.alertError);
			if (alertError.contains("Do you want to overwrite existing search")) {
				eleUtil.doClick(this.alertOkBtn);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		eleUtil.wait(1);
		eleUtil.doClick(this.modifiedDateHeadCol);
		eleUtil.wait(1);
		eleUtil.doClick(this.modifiedDateHeadCol);
		eleUtil.wait(1);
		String searchDescription = eleUtil.doGetText(this.latestDescriptionResult);
		eleUtil.switchToDefaultContent();
		return searchDescription;
	}

	/**
	 * This method is used to get Record count in saved search grid
	 *
	 * @return This will return record info in String
	 */
	public String getRecordCountInSavedSearch() {
		eleUtil.switchToFrameIfExists(this.repositoryIframe);
		eleUtil.switchToFrameIfExists(this.repoIframe, 20);
		try {
			String record = eleUtil.doGetText(this.recordSavedSearch);
			if (record == "")
				eleUtil.doClick(this.savedSearchBar);
		} catch (Exception e) {
			e.printStackTrace();
		}
		eleUtil.waitForElementPresence(this.recordSavedSearch, 20);
		String record = eleUtil.doGetText(this.recordSavedSearch);
		eleUtil.switchToDefaultContent();
		return record;
	}

	/**
	 * This method is used to click on Recall button
	 */
	public void clickOnRecallButton() {
		eleUtil.switchToFrameIfExists(this.repositoryIframe);
		eleUtil.switchToFrameIfExists(this.repoIframe);
		try {
			boolean record = eleUtil.isDisplay(this.recallButton);
			if (record == false)
				eleUtil.doClick(this.savedSearchBar);
		} catch (Exception e) {
			e.printStackTrace();
		}
		eleUtil.doClick(this.modifiedDateHeadCol);
		eleUtil.wait(1);
		eleUtil.doClick(this.modifiedDateHeadCol);
		eleUtil.wait(1);
		jsUtil.scrollPageDown();
		eleUtil.wait(1);
		eleUtil.doClick(this.latestDescriptionResult);

		try {
			boolean record = eleUtil.isEnabled(this.recallButton);
			if (record == false)
				eleUtil.doClick(this.latestDescriptionResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		eleUtil.doClick(this.recallButton);
		eleUtil.switchToDefaultContent();
	}

	/**
	 * This method is used to click on delete button
	 */
	public void clickOnDelete() {
		eleUtil.switchToFrameIfExists(this.repositoryIframe);
		eleUtil.switchToFrameIfExists(this.repoIframe);
		try {
			String record = eleUtil.doGetText(this.recordSavedSearch);
			if (record == "")
				eleUtil.doClick(this.savedSearchBar);
		} catch (Exception e) {
			e.printStackTrace();
		}
		eleUtil.doClick(this.searchResultBar);
		eleUtil.doClick(this.modifiedDateHeadCol);
		eleUtil.doClick(this.modifiedDateHeadCol);
		eleUtil.wait(1);
		eleUtil.doClick(this.latestDescriptionResult);
		eleUtil.doClick(this.latestDescriptionResult);
		eleUtil.clickElementWhenReady(this.deleteButton, 20);
		try {
			String alertError = eleUtil.doGetText(this.alertError);
			if (alertError.contains("Are you sure you want to delete this saved search")) {
				eleUtil.doClick(this.alertOkBtn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		eleUtil.switchToDefaultContent();
	}

	/**
	 * This method is used to Validate after click delete btn Description value was
	 * deleted
	 *
	 * @return This will return the next search description value after delete the
	 *         latest search description
	 */
	public String validateDeleteValue() {
		eleUtil.switchToFrameIfExists(this.repositoryIframe);
		eleUtil.switchToFrameIfExists(this.repoIframe);
		eleUtil.doClick(this.modifiedDateHeadCol);
		eleUtil.doClick(this.modifiedDateHeadCol);
		eleUtil.wait(2);
		String searchDescription = eleUtil.doGetText(this.latestDescriptionResult);
		eleUtil.switchToDefaultContent();
		return searchDescription;

	}

	/**
	 * This method is used to verify grid table is displayed
	 *
	 * @return This will return true if search result is not displayed
	 */
	public Boolean verifySearchResultDisplayed() {
		Boolean result = false;
		eleUtil.switchToFrameIfExists(this.repositoryIframe);
		eleUtil.switchToFrameIfExists(this.repoIframe);
		try {
			String ariaExpandCondition = eleUtil.getAttributeValue(this.searchResultBar, "aria-expanded");
			if (ariaExpandCondition.contains("true")) {
				result = true;
			}
			ExtentReportListener.test.get().log(Status.PASS,
					"Search result is displayed \'" + result + "\' successfully");
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Search result is not displayed");
			Assert.fail(e.getMessage());
		}
		eleUtil.switchToDefaultContent();
		return result;

	}

	/**
	 * This method is used to put value 3 in input feild of page and press enter
	 *
	 * @return This will return the object of 3 page
	 */
	public void enterPageNumberAndPressEnterKey() {
		eleUtil.switchToFrameIfExists(this.repositoryIframe);
		eleUtil.switchToFrameIfExists(this.repoIframe);
		eleUtil.doSendKeys(this.inputPageNum, "3");
		driver.findElement(this.inputPageNum).sendKeys(Keys.ENTER);
		eleUtil.switchToDefaultContent();
	}

	/**
	 * This method is used to page information in text
	 *
	 * @return This will return the page info
	 */
	public String getPageInfo() {
		eleUtil.switchToFrameIfExists(this.repositoryIframe);
		eleUtil.switchToFrameIfExists(this.repoIframe);
		String pInfo = eleUtil.doGetText(this.searchResultPageInfo);
		eleUtil.switchToDefaultContent();
		return pInfo;
	}

	/**
	 * This method is used to verify record sorted by Ascending or Descending order
	 * 
	 * @param Order String value to get output in ascending or descending order
	 * @return This will return the Object of RepositoryPage class
	 */
	public RepositoryPage verifyRecordSortedDscOrder(String Order) {
		eleUtil.switchToFrameIfExists(this.repositoryIframe);
		eleUtil.switchToFrameIfExists(this.repoIframe);
		if (Order.contains("Dsc")) {
			eleUtil.doClick(this.docIdHead);
			eleUtil.doClick(this.docIdHead);
		} else if (Order.contains("Asc")) {
			eleUtil.doClick(this.docIdHead);
		}

		eleUtil.wait(2);
		String firstDocId = eleUtil.doGetText(this.docIdColumnValue);
		String secondDocId = eleUtil.doGetText(this.docIdColumnValue2);
		int firstDocIdNum = comUtil.convertStringToNumber(firstDocId);
		int secondDocIdNum = comUtil.convertStringToNumber(secondDocId);
		if (Order.contains("Dsc")) {
			AssertionUtil.verifyGreaterThan(firstDocIdNum, secondDocIdNum, "Records are sorted in descending order");
		} else if (Order.contains("Asc")) {
			AssertionUtil.verifyGreaterThan(secondDocIdNum, firstDocIdNum, "Records are sorted in ascending order");
		}
		eleUtil.switchToDefaultContent();
		return this;
	}

	public void validateLayout() {
		eleUtil.waitForElementPresence(this.closureLink, 20);
		eleUtil.doClick(this.closureLink);
		eleUtil.waitForElementPresence(this.closureBranchLink, 20);
		eleUtil.doClick(this.closureBranchLink);
		eleUtil.waitForElementPresence(this.closureDateFolder, 20);
		eleUtil.doClick(this.closureDateFolder);
	}

	/**
	 * This method is used to verify Set Layout and Reset Layout Icons work properly
	 *
	 * @return This will return none
	 */
	public void verificationOfSetAndResetLayout() {
		eleUtil.switchToFrameIfExists(this.repositoryIframe);
		eleUtil.switchToFrameIfExists(this.closureIframe);
		eleUtil.doClick(this.resetLayoutIcon);
		eleUtil.wait(2);
		eleUtil.waitForElementPresence(this.docIdBeforeSwitch, 50);
		String getDocIdBefore = eleUtil.doGetText(this.docIdBeforeSwitch);
		eleUtil.dragAndDrop(this.docIdColumnHead, this.customerNameHead);
		eleUtil.doClick(this.setLayoutIcon);
		eleUtil.wait(2);
		String getDocIdAfter = eleUtil.doGetText(this.docIdAfterSwitch);
		if (getDocIdBefore.contentEquals(getDocIdAfter)) {
			ExtentReportListener.test.get().log(Status.PASS, "Verification of columns are switched successfully");
		} else {
			ExtentReportListener.test.get().log(Status.FAIL, "Verification of columns are not switched successfully");
		}
		eleUtil.doClick(this.resetLayoutIcon);
		eleUtil.wait(1);
		String getDocIdAfterReset = eleUtil.doGetText(this.docIdBeforeSwitch);
		if (getDocIdBefore.contentEquals(getDocIdAfterReset)) {
			ExtentReportListener.test.get().log(Status.PASS, "Verification of column positon are reset successfully");
		} else {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Verification of column positon are not reset successfully");
		}
		eleUtil.switchToDefaultContent();
	}

}
