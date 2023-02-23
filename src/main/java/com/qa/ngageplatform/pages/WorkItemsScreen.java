package com.qa.ngageplatform.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.qa.ngageplatform.listeners.ExtentReportListener;
import com.qa.ngageplatform.utils.CommonUtil;
import com.qa.ngageplatform.utils.ElementUtil;

/**
 * This Class is used to provide Object Repo and Actions related to WorkItems
 * Screen
 *
 * @author Nahian Omar Faruqe
 * @version 1.0
 * @since 2022-09-28
 */
public class WorkItemsScreen {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// ****************** Locators ****************** //

	private By ActionLink = By.xpath("//ul[@id='wfActionsMenu']//a//span[text()='Actions']");
	private By paginationInfoLabel = By
			.xpath("//td[contains(@id,'MyWorkSearchResultsTable_toppager_right')]//*[@class='ui-paging-info']");
	private By searchHeaderBar = By.id("lblSearchHeader");
	private By customerNameSearchTextBox = By.xpath(
			"//*[contains(@class,'searchlabel') and contains(text(),'Customer Name')]//following-sibling::*[last()]//input");
	private By searchButton = By.id("btnSearch");
	private By resetButton = By.id("btnReset");
	private By customerNameColumnFirstRowData = By
			.xpath("(//td[@aria-describedby='MyWorkSearchResultsTable_Customer Name'])[1]");
	private By parentFrame = By.id("iframe_105");
	private By childFrame = By.xpath("(//*[@class='tabpanel-iframe'])[1]");
	private By paginationTextField = By
			.xpath("//*[@id='MyWorkSearchResultsTable_toppager_center']//input[@class='ui-pg-input']");
	// **** Changing xpath as class is different for both search******
//	private By processDueDateStartRangeSearchTextBox = By.xpath(
//			"(//*[contains(@class,'searchlabel') and contains(text(),'Process Due')]//following-sibling::*[last()]//input)[1]");
	private By processDueDateStartRangeSearchTextBox = By
			.xpath("(//*[contains(text(),'Process Due')]//following-sibling::*[last()]//input)[1]");
	// **** Changing xpath as class is different for both search******
	// private By processDueDateEndRangeSearchTextBox = By.xpath(
//			"(//*[contains(@class,'searchlabel') and contains(text(),'Process Due')]//following-sibling::*[last()]//input)[2]");
	private By processDueDateEndRangeSearchTextBox = By
			.xpath("(//*[contains(text(),'Process Due')]//following-sibling::*[last()]//input)[2]");
	private By processDueDateColumnHeader = By.xpath("//th[@id='MyWorkSearchResultsTable_Process Due Date']");
	private By processDueDateColumnFirstRowValue = By
			.xpath("(//td[@aria-describedby='MyWorkSearchResultsTable_Process Due Date'])[1]");
	private By dashboardTab = By.xpath("//a[@data-key='workItemsDashboard']");
	private By showAssignedOnlyCheckbox = By.id("chkAssignedOnly");
	private By firstRowCheckbox = By.xpath("(//table[@id='MyWorkSearchResultsTable']//input)[1]");
	private By firstRowLockIcon = By.xpath("(//td[@aria-describedby='MyWorkSearchResultsTable_InProcess'])[1]//img");
	private By docIdData1st = By.xpath("(//td[@aria-describedby='SimplifiedWorkflowSearchResultsTable_Doc ID'])[1]");
	private By docID = By.xpath("//th[@id='MyWorkSearchResultsTable_Doc ID']");
//private By docID = By.xpath("//td[@aria-describedby='SimplifiedWorkflowSearchResultsTable_Doc ID']");
	private By actionLink = By.xpath("(//ul[@id='wfActionsMenu']//a//span[text()='Actions'])[1]");
	private By assignedWorkItemLink = By.id("ui-id-3");
	private By unlockWorkItemLink = By.id("ui-id-5");
	private By workItemActionsFrame = By.xpath("//iframe[contains(@src,'/actions/')]");
	private By workItemActionsDialogMessage = By.id("lblMsg");
	private By closeButtonOnAssignWorkItemDialog = By
			.xpath("//*[@class='ui-dialog-buttonset']//button[text()='Close']");
	private By assignWorkItemsButtonOnAssignWorkItemDialog = By
			.xpath("//*[@class='ui-dialog-buttonset']//button[contains(text(),'Assign Work Items')]");
	private By selectDropdownOnAssignWorkItemDialog = By.id("lstSelect");
	private By assignToListDropdownOnAssignWorkItemDialog = By.id("lstAssignToList");

//**** Romee's Code ****//
	private By inputDocID = By.xpath("//input[@id='-100']");
	private By activityIframe = By.id("iframe_110");
	private By inputPDueDateStart = By.xpath("//input[@id='100096_1']");
	private By inputPDueDateEnd = By.xpath("//input[@id='100096_2']");
	private By activityFrame = By.xpath("(//*[@class='tabpanel-iframe'])[1]");
	private By activityALink = By.xpath("//a[contains(text(),'Activity A')]");
	private By activityCLink = By.xpath("//a[contains(text(),'Activity C')]");
	private By docCreateDate = By.xpath("//div[@id='jqgh_MyWorkSearchResultsTable_Doc Create Date']");
	private By firstCheckBox = By.xpath("(//input[@role='checkbox' ])[2]");
	private By valueActivity = By.id("ui-id-20");
	private By dialogTitle = By.xpath("//td[@aria-describedby='ProcessStatusGrid_Title']");
	private By dialogMessage = By.xpath("//td[@aria-describedby='ProcessStatusGrid_Message']");
	private By dialogCloseButton = By.xpath("//div[@class='ui-dialog-buttonset']//button[contains(text(),'Close')]");
	private By dialogFrame = By.id("iframe_wfACCWAH_100004_100027");
	private By iframeDialog = By.id("ca48cd10-66fc-47d3-9da6-d66e9a2f1816_iframe");
	private By getNextItem = By.xpath("//input[@name='btnGetNext']");

	public WorkItemsScreen(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	/**
	 * This enum is used to provide Pre-Defined Value for Search Field
	 */
	enum SearchField {
		CUSTOMER_ID, PROCESS_INSTANCE_ID, DOC_CREATE_DATE, DOC_ID, CUSTOMER_NAME, PROCESS_DUE_DATE,
		CUSTOMER_PHONE_NUMBER, PROCESS_GOAL_DATE;
	}

	/**
	 * This enum is used to provide Pre-Defined Value for Search Criteria Field
	 */
	enum SearchCriteria {
		GREATER_THAN(">"), LESS_THAN("<"), EQUAL_TO("="), GREATER_THAN_EQUAL_TO(">="), LESS_THAN_EQUAL_TO("<="),
		NOT_EQUAL_TO("<>"), IN("In"), NOT_IN("Not In"), NULL("Null"), NOT_NULL("Not Null");

		public final String searchCriteria;

		private SearchCriteria(String searchCriteria) {
			this.searchCriteria = searchCriteria;
		}
	}

	/**
	 * This method is used to Switch to WorkItems Screen Parent frame
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen switchToParentFrame() {
		eleUtil.waitForElementPresence(this.parentFrame, 20);
		eleUtil.switchToFrame(this.parentFrame, 20);
		return this;
	}

	/**
	 * This method is used to Switch to WorkItems Screen Child frame
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen switchToChildFrame() {
		eleUtil.waitForElementPresence(this.childFrame, 20);
		eleUtil.switchToFrame(this.childFrame, 20);
		return this;
	}

	/**
	 * This method is used to Switch to WorkItems Screen frame in one go
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen switchToFrame() {
		// this romee's implementation is not working as expected
		if (eleUtil.isElementExist(this.activityIframe))
			eleUtil.switchToFrame(activityIframe);
		if (eleUtil.isElementExist(this.getNextItem)) {
			driver.switchTo().parentFrame();
			eleUtil.switchToFrame(activityIframe);
		} else {
			driver.switchTo().parentFrame();
			this.switchToParentFrame();
			this.switchToChildFrame();
		}
		return this;
	}

	/**
	 * This method is used to Switch to WorkItems Screen WorkItemAction frame
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen switchToWorkItemDialogFrame() {
		eleUtil.waitForElementPresence(this.workItemActionsFrame, 20);
		eleUtil.switchToFrame(this.workItemActionsFrame, 20);
		return this;
	}

	/**
	 * This method is used to get the Pagination Info
	 *
	 * @return This will return the Pagination Info in String Format
	 */
	public String getPaginationInfo() {
		String paginationInfo = null;
		try {
			this.switchToFrame();
			eleUtil.waitForElementPresence(this.paginationInfoLabel, 20);
			paginationInfo = eleUtil.doGetText(this.paginationInfoLabel);
			ExtentReportListener.test.get().log(Status.INFO,
					"Fetching pagination Info \'" + paginationInfo + "\' successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while fetching pagination Info");
			Assert.fail(e.getMessage());
		}
		return paginationInfo;
	}

	/**
	 * This method is used to click on Search Header bar
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen clickOnSearchHeaderBar() {
		try {
			this.switchToFrame();
			eleUtil.doClick(this.searchHeaderBar);
			ExtentReportListener.test.get().log(Status.INFO, "Clicking on \'Search\' header bar successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while clicking on \'Search\' header bar");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to enter the Customer Name in Search Panel
	 *
	 * @param customerName Value to be enter in CustomerName textfield
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen enterCustomerName(String customerName) {
		try {
			this.switchToFrame();
			eleUtil.doSendKeys(this.customerNameSearchTextBox, customerName);
			ExtentReportListener.test.get().log(Status.INFO,
					"Enter " + customerName + " in \'Customer Name\' textfield under Search Panel successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while entering " + customerName + " in \'Customer Name\' textfield under Search Panel");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to enter the Doc ID in Search Panel
	 *
	 * @param customerName Value to be enter in DocID textfield
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public String enterDocID() {
		String docid = null;
		try {
			this.switchToFrame();
			docid = this.getFirstDocID();
			eleUtil.doSendKeys(this.inputDocID, docid);
			ExtentReportListener.test.get().log(Status.INFO,
					"Enter " + docid + " in \'Doc ID\' textfield under Search Panel successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			;
		}
		return docid;
	}

	/**
	 * This method is used to get frist doc id
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public String getFirstDocID() {
		String docID = eleUtil.doGetText(this.docIdData1st);
		return docID;
	}

	/**
	 * This method is used to get frist doc id
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public String getFristDocIDAfterSearch() {
		driver.switchTo().parentFrame();
		eleUtil.switchToFrame(this.activityIframe, 20);
		String actualResultDocId = eleUtil.doGetText(this.docIdData1st);
		driver.switchTo().parentFrame();
		return actualResultDocId;
	}

	/**
	 * This method is used to enter the Process Due Date's first textfield in Search
	 * Panel
	 *
	 * @param processDueDateStartRange Value to be enter in ProcessDueDate's first
	 *                                 textfield
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen enterProcessDueDateStartRange(String processDueDateStartRange) {
		try {
			this.switchToFrame();
			eleUtil.waitForElementPresence(this.processDueDateStartRangeSearchTextBox, 10);
			eleUtil.doSendKeys(this.processDueDateStartRangeSearchTextBox, processDueDateStartRange);
			ExtentReportListener.test.get().log(Status.INFO, "Enter " + processDueDateStartRange
					+ " in \'Process Due Date\' first textfield under Search Panel successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while entering " + processDueDateStartRange
					+ " in \'Process Due Date\' first textfield under Search Panel");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to clear the ProcessDueDate's first textfield in Search
	 * Panel
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen clearProcessDueDateStartRange() {
		try {
			this.switchToFrame();
			eleUtil.doClear(this.processDueDateStartRangeSearchTextBox);
			ExtentReportListener.test.get().log(Status.INFO,
					"Clearing \'Process Due Date\' first textfield under Search Panel successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while clearing \'Process Due Date\' first textfield under Search Panel");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to clear the ProcessDueDate's second textfield in Search
	 * Panel
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen clearProcessDueDateEndRange() {
		try {
			this.switchToFrame();
			eleUtil.doClear(this.processDueDateEndRangeSearchTextBox);
			ExtentReportListener.test.get().log(Status.INFO,
					"Clearing \'Process Due Date\' second textfield under Search Panel successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while clearing \'Process Due Date\' second textfield under Search Panel");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to enter the Process Due Date's second textfield in
	 * Search Panel
	 *
	 * @param processDueDateEndRange Value to be enter in ProcessDueDate's second
	 *                               textfield
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen enterProcessDueDateEndRange(String processDueDateEndRange) {
		try {
			this.switchToFrame();
			eleUtil.waitForElementPresence(this.processDueDateEndRangeSearchTextBox, 10);
			eleUtil.doSendKeys(this.processDueDateEndRangeSearchTextBox, processDueDateEndRange);
			ExtentReportListener.test.get().log(Status.INFO, "Enter " + processDueDateEndRange
					+ " in \'Process Due Date\' second textfield under Search Panel successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while entering " + processDueDateEndRange
					+ " in \'Process Due Date\' second textfield under Search Panel");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to enter the Process Due Date's first textfield in Search
	 * Panel
	 *
	 * @param backDays Number of Days add/sub to have Value to be enter in
	 *                 ProcessDueDate's first textfield
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen enterProcessDueDateStartRange(int backDays) {
		String processDueDateStartRange = new CommonUtil().getCurrentDateTime();
		this.enterProcessDueDateStartRange(processDueDateStartRange);
		return this;
	}

	/**
	 * This method is used to enter the Pagination Number
	 *
	 * @param paginationNumber Value representing Page Number
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen enterPaginationNumber(String paginationNumber) {
		try {
			this.switchToFrame();
			eleUtil.doSendKeys(this.paginationTextField, paginationNumber);
			ExtentReportListener.test.get().log(Status.INFO,
					"Enter " + paginationNumber + " in Pagination textbox successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while entering " + paginationNumber + " in Pagination textbox");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to click on Search button
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen clickOnSearchButton() {
		try {
			this.switchToFrame();
			eleUtil.doClick(this.searchButton);
			ExtentReportListener.test.get().log(Status.INFO, "Clicking on \'Search\' button successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while clicking on \'Search\' button");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to click on Reset button
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen clickOnResetButton() {
		try {
			this.switchToFrame();
			eleUtil.doClick(this.resetButton);
			ExtentReportListener.test.get().log(Status.INFO, "Clicking on \'Reset\' button successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while clicking on \'Reset\' button");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to get First Available Customer Name
	 *
	 * @return This will return the First Available Customer Name in String Format
	 */
	public String getFirstAvailableCustomerNameData() {
		String customerName = null;
		try {
			eleUtil.wait(1);
			this.switchToFrame();
			eleUtil.wait(1);
			eleUtil.waitForElementPresence(this.customerNameColumnFirstRowData, 20);
			customerName = eleUtil.doGetText(this.customerNameColumnFirstRowData);
			ExtentReportListener.test.get().log(Status.INFO,
					"Fetching Customer Name of First Available Row successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while fetching Customer Name");
			Assert.fail(e.getMessage());
		}
		return customerName;
	}

	/**
	 * This method is used to get First Available Process Due Date value
	 *
	 * @return This will return the First Available Process Due Date value in String
	 *         Format
	 */
	public String getFirstAvailableProcessDueDateData() {
		eleUtil.wait(5);
		String processDueData = null;
		try {
			this.switchToFrame();
			//eleUtil.waitForElementPresence(this.processDueDateColumnFirstRowValue, 20);
			processDueData = eleUtil.doGetText(this.processDueDateColumnFirstRowValue);
			ExtentReportListener.test.get().log(Status.INFO,
					"Fetching first available row value for Process Due Date column successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while fetching first available row value for Process Due Date column");
			Assert.fail(e.getMessage());
		}
		return processDueData;
	}

	/**
	 * This method is used to click on Process Due Date Column Header
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen clickOnProcessDueDateColumnHeader() {
		this.switchToFrame();
		eleUtil.waitForElementPresence(this.processDueDateColumnHeader, 20);
		eleUtil.doClick(this.processDueDateColumnHeader);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to Sort the Process Due Date in Descending order
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen sortProcessDueDateColumnInDescOrder() {
		this.clickOnProcessDueDateColumnHeader();
		this.clickOnProcessDueDateColumnHeader();
		return this;
	}

	/**
	 * This method is used to Sort the Process Due Date in Ascending order
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen sortProcessDueDateColumnInAscOrder() {
		this.clickOnProcessDueDateColumnHeader();
		return this;
	}

	/**
	 * This method is used to go to Specific page
	 *
	 * @param paginationNumber Page Number in String format
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen goToPage(String paginationNumber) {
		this.enterPaginationNumber(paginationNumber);
		eleUtil.pressEnterKey();
		return this;
	}

	// ************************************************//
	// ************ Complete Actions/Flows ************//
	// ************************************************//

	/**
	 * This method is used to search and get First Available Customer Name value
	 *
	 * @param customerName value to be enter in CustomerName's textfield
	 * @return This will return the First Available Customer Name value in String
	 *         Format
	 */
	public String searchAndFetchCustomerNameOfFirstAvailableRow(String customerName) {
		this.clickOnSearchHeaderBar();
		this.enterCustomerName(customerName);
		this.clickOnSearchButton();
		return this.getFirstAvailableCustomerNameData();
	}

	/**
	 * This method is used to search and get First Available Doc ID value
	 *
	 * @return This will return the First Available Doc ID value in String Format
	 */
	public String searchandFetchDocIDFirstAvailableRow() {
		this.clickOnSearchHeaderBar();
		String docid = this.enterDocID();
		this.clickOnSearchButton();
		return docid;
	}

	/**
	 * This method is used to Reset the Search Panel
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen resetSearchPanel() {
		this.clickOnSearchHeaderBar();
		this.clickOnResetButton();
		return this;
	}

	/**
	 * This method is used to search by ProcessDueDate's first textfield
	 *
	 * @param processDueDate value to be enter in ProcessDueDate's First textfield
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen searchProcessDueDateByStartRange(String processDueDate) {
		this.clickOnSearchHeaderBar();
		this.clearProcessDueDateEndRange();
		this.enterProcessDueDateStartRange(processDueDate);
		this.clickOnSearchButton();
		return this;
	}

	/**
	 * This method is used to search by ProcessDueDate's Second textfield
	 *
	 * @param processDueDate value to be enter in ProcessDueDate's Second textfield
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen searchProcessDueDateByEndRange(String processDueDate) {
		this.clickOnSearchHeaderBar();
		this.clearProcessDueDateStartRange();
		this.enterProcessDueDateEndRange(processDueDate);
		this.clickOnSearchButton();
		return this;
	}

	/**
	 * This method is used to search by ProcessDueDate's First and Second textfield
	 *
	 * @param processDueDateStartRange value to be enter in ProcessDueDate's First
	 *                                 textfield
	 * @param processDueDateEndRange   value to be enter in ProcessDueDate's Second
	 *                                 textfield
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen searchProcessDueDate(String processDueDateStartRange, String processDueDateEndRange) {
		this.clickOnSearchHeaderBar();
		this.clearProcessDueDateStartRange();
		this.clearProcessDueDateEndRange();
		this.enterProcessDueDateStartRange(processDueDateStartRange);
		this.enterProcessDueDateEndRange(processDueDateEndRange);
		this.clickOnSearchButton();
		return this;
	}

	// ************************************************//
	// ************ Common Component Util *************//
	// ************************************************//

	/**
	 * This method is used to search data by taking searchField , SearchCriteria and
	 * SearchText as a parameter
	 *
	 * @param searchField    SearchField value which we are getting from SearchField
	 *                       enum pre-defined value
	 * @param searchCriteria searchCriteria value which we are getting from
	 *                       searchCriteria enum pre-defined value
	 * @param searchValue    value which user want to Search
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen search(SearchField searchField, SearchCriteria searchCriteria, String searchValue) {
		this.clickOnSearchHeaderBar();

		switch (searchField) {
		case CUSTOMER_ID:
			break;
		case PROCESS_INSTANCE_ID:
			break;
		case DOC_CREATE_DATE:
			break;
		case DOC_ID:
			break;
		case CUSTOMER_NAME:
			break;
		case PROCESS_DUE_DATE:
			break;
		case CUSTOMER_PHONE_NUMBER:
			break;
		case PROCESS_GOAL_DATE:
			break;
		}

		this.clickOnSearchButton();
		return this;
	}

	/**
	 * This method is used to click on Search button
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public DashboardScreen clickOnDashboardTab() {
		try {
			this.switchToParentFrame();
			eleUtil.doClick(this.dashboardTab);
			ExtentReportListener.test.get().log(Status.INFO, "Clicking on \'Dashboard\' tab successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while clicking on \'Dashboard\' tab");
			Assert.fail(e.getMessage());
		}
		return new DashboardScreen(driver);
	}

	/**
	 * This method is used to get the checked/unchecked status of Show Assigned Only
	 * checkbox
	 *
	 * @return This will return True if checkbox is checked otherwise False
	 */
	public boolean getSelectionStatusForShowAssignedOnlyCheckbox() {
		boolean checkboxSelectionStatus = false;
		try {
			this.switchToFrame();
			checkboxSelectionStatus = eleUtil.isCheckboxSelected(this.showAssignedOnlyCheckbox);
			ExtentReportListener.test.get().log(Status.INFO,
					"Getting Selection Status \'Show Assigned Only Checkbox\' successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while getting Selection Status \'Show Assigned Only Checkbox\'");
			Assert.fail(e.getMessage());
		}
		return checkboxSelectionStatus;
	}

	/**
	 * This method is used to get the checked/unchecked status of First Row checkbox
	 *
	 * @return This will return True if checkbox is checked otherwise False
	 */
	public boolean getSelectionStatusForFirstRowCheckbox() {
		boolean checkboxSelectionStatus = false;
		try {
			this.switchToFrame();
			checkboxSelectionStatus = eleUtil.isCheckboxSelected(this.firstRowCheckbox);
			ExtentReportListener.test.get().log(Status.INFO,
					"Getting Selection Status \'First Row Checkbox\' successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while getting Selection Status \'First Row Checkbox\'");
			Assert.fail(e.getMessage());
		}
		return checkboxSelectionStatus;
	}

	/**
	 * This method is used to get the existence status of First Row Lock Icon
	 *
	 * @return This will return True if Lock Icon exists otherwise False
	 */
	public boolean getExistenceStatusForFirstRowLockIcon() {
		boolean isIconExists = false;
		try {
			eleUtil.wait(5);
			this.switchToFrame();
			eleUtil.waitForElementPresence(this.customerNameColumnFirstRowData, 20);
			isIconExists = eleUtil.isElementExist(this.firstRowLockIcon);
			ExtentReportListener.test.get().log(Status.INFO, "Fetching Lock Icon existence status successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while fetching Lock Icon existence status");
			Assert.fail(e.getMessage());
		}
		return isIconExists;
	}

	/**
	 * This method is used to click on First Available Customer Name
	 *
	 * @return This will return the Object of NewDocPage class
	 */
	public NewDocPage clickOnFirstAvailableCustomerName() {
		eleUtil.wait(5);
		this.switchToFrame();
		eleUtil.doClick(this.customerNameColumnFirstRowData);
		eleUtil.switchToDefaultContent();
		return new NewDocPage(this.driver);
	}

	/**
	 * This method is used Sort DocID Column in Descending order for WorkItems List
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen sortDocumentListByDocID() {
		this.sortDocIDInDescOrder();
		return this;
	}

	/**
	 * This method is used Sort DocID Column in Descending order
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen sortDocIDInDescOrder() {
		this.clickOnDocIDColumnHeader();
		this.clickOnDocIDColumnHeader();
		return this;
	}

	/**
	 * This method is used to click on DocID Column Header
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen clickOnDocIDColumnHeader() {
		this.switchToFrame();
		eleUtil.wait(5);
		eleUtil.doClick(this.docID);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to click on Action Link and then Unlock Work Item link on
	 * WorkItems screen
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen navigateAndClickToUnlockWorkItemLink() {
		this.switchToFrame();
		eleUtil.moveToElementAndClick(this.actionLink, this.unlockWorkItemLink);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to click on Action Link and then Assign Work Item link on
	 * WorkItems screen
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen navigateAndClickToAssignWorkItemLink() {
		this.switchToFrame();
		eleUtil.moveToElementAndClick(this.actionLink, this.assignedWorkItemLink);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to click on Close button on WorkItem Action Dialog
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen clickOnCloseButtonOnWorkItemActionDialog() {
		try {
			this.switchToFrame();
			eleUtil.doClick(this.closeButtonOnAssignWorkItemDialog);
			ExtentReportListener.test.get().log(Status.INFO,
					"Clicking on \'Close button\' in WorkItemActions Dialog successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while clicking on \'Close button\' in WorkItemActions Dialog");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to click on Close button on WorkItem Action Dialog
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen clickOnAssignWorkItemsButtonOnAssignWorkItemDialog() {
		try {
			this.switchToFrame();
			eleUtil.doClick(this.assignWorkItemsButtonOnAssignWorkItemDialog);
			ExtentReportListener.test.get().log(Status.INFO,
					"Clicking on \'Close button\' in WorkItemActions Dialog successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while clicking on \'Close button\' in WorkItemActions Dialog");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to get the Work Item Actions Dialog Message
	 *
	 * @return This will return the message on Work Item Actions Dialog
	 */
	public String getWorkItemActionsDialogMessage() {
		String message = null;
		try {
			this.switchToFrame();
			this.switchToWorkItemDialogFrame();
			message = eleUtil.doGetText(this.workItemActionsDialogMessage);
			ExtentReportListener.test.get().log(Status.INFO,
					"Getting \'Message\' on Work Item Actions Dialog successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while getting \'Message\' on Work Item Actions Dialog");
			Assert.fail(e.getMessage());
		}
		return message;
	}

	/**
	 * This method is used to click on First Row checkbox
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen clickOnFirstRowCheckbox() {
		try {
			this.switchToFrame();
			eleUtil.doClick(this.firstRowCheckbox);
			ExtentReportListener.test.get().log(Status.INFO, "Clicking On \'First Row Checkbox\' successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while clicking on \'First Row Checkbox\'");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to select the value from Select dropdown On Assign Work
	 * Item Dialog
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen selectValueFromSelectDropdownOnAssignWorkItemDialog(String value) {
		try {
			this.switchToFrame();
			this.switchToWorkItemDialogFrame();
			eleUtil.doDropDownSelectByVisibleText(this.selectDropdownOnAssignWorkItemDialog, value);
			ExtentReportListener.test.get().log(Status.INFO,
					"select value " + value + " from \'Select\' dropdown successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while selecting value " + value + " from \'Select\' dropdown ");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to select the value from Select dropdown On Assign Work
	 * Item Dialog
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen selectValueFromAssignToListDropdownOnAssignWorkItemDialog(String value) {
		try {
			this.switchToFrame();
			this.switchToWorkItemDialogFrame();
			eleUtil.doDropDownSelectByVisibleText(this.assignToListDropdownOnAssignWorkItemDialog, value);
			ExtentReportListener.test.get().log(Status.INFO,
					"select value " + value + " from \'AssignToList\' dropdown successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while selecting value " + value + " from \'AssignToList\' dropdown ");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to select showAssignedOnly Checkbox
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen clickOnShowAssignedOnlyCheckbox() {

		try {
			this.switchToFrame();
			eleUtil.doClick(this.showAssignedOnlyCheckbox);
			ExtentReportListener.test.get().log(Status.INFO,
					"Clicking on \'Show Assigned Only\' Checkbox successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while clicking on \'Show Assigned Only\' checkbox");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to enter Current Process Due Date and enter Process End
	 * Date
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen enterProcessDueStartAndEndDate() {
		CommonUtil commonUtil = new CommonUtil();
		String dateTimeFormat = "MM-dd-yyyy hh:mm:ss a";
		String expectedProcessDueDateStartRange = commonUtil.getDateTime(0, dateTimeFormat);
		String expectedProcessDueDateEndRange = commonUtil.getDateTime(7, dateTimeFormat);
		this.clickOnSearchHeaderBar();
		eleUtil.switchToFrame(this.activityIframe);
		eleUtil.doSendKeys(this.inputPDueDateStart, expectedProcessDueDateStartRange);
		eleUtil.doSendKeys(this.inputPDueDateEnd, expectedProcessDueDateEndRange);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to validate search filed should be empty agter reset
	 *
	 * @return This will return boolean value of search field Format
	 */
	public boolean verifySearchInputFieldBlank() {
		this.clickOnResetButton();
		eleUtil.switchToFrame(this.activityIframe);
		String docIdField = eleUtil.doGetText(this.inputDocID);
		eleUtil.switchToDefaultContent();
		eleUtil.switchToFrame(this.activityIframe);
		String pDSDateField = eleUtil.doGetText(this.inputPDueDateStart);
		eleUtil.switchToDefaultContent();
		eleUtil.switchToFrame(this.activityIframe);
		String pDEDateField = eleUtil.doGetText(this.inputPDueDateEnd);
		eleUtil.switchToDefaultContent();
		if (docIdField.isEmpty() & pDSDateField.isEmpty() & pDEDateField.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is used to get Activity count
	 * 
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public int getActivityCount() {
		eleUtil.waitForElementPresence(this.parentFrame, 20);
		eleUtil.switchToFrame(this.parentFrame);
		eleUtil.switchToFrame(this.activityFrame);
		eleUtil.waitForElementPresence(this.paginationInfoLabel, 20);
		String pageInfo = eleUtil.doGetText(this.paginationInfoLabel);
		String arPf[] = pageInfo.split("of");
		String ar= arPf[1].trim();
		String resultCount = ar.replace(",","");
		int pageCnt = Integer.parseInt(resultCount);
		eleUtil.switchToDefaultContent();
		return pageCnt;
	}

	/**
	 * This method is used to get the no. of ActivityA
	 * 
	 *
	 * @return This will return integer no of ActivityA count
	 */
	public int getActivityA_Count() {
		eleUtil.moveToElementAndClick(this.activityALink);
		int pageACnt = getActivityCount();
		return pageACnt;
	}

	/**
	 * This method is used to get the no. of ActivityA
	 * 
	 *
	 * @return This will return integer no of ActivityA count
	 */
	public int getActivityC_Count() {
		eleUtil.moveToElementAndClick(this.activityCLink);
		int pageCCnt = getActivityCount();
		return pageCCnt;
	}

	/**
	 * This method is used to close the dialog box
	 * 
	 *
	 * @return This will none
	 */
	public void closeActivityADialogBox() {
		eleUtil.switchToFrameIfExists(this.parentFrame, 20);
		eleUtil.switchToFrameIfExists(this.activityFrame);
		eleUtil.switchToFrameIfExists(this.iframeDialog);
		eleUtil.doClick(this.dialogCloseButton);
		eleUtil.switchToDefaultContent();
		eleUtil.wait(2);
		}

	/**
	 * This method is used to click on action link and click on route to activity C
	 * 
	 *
	 * @return This will return the Object of WorkItemsScreen class
	 */
	public WorkItemsScreen activityAAction() {
		eleUtil.waitForElementPresence(this.parentFrame, 20);
		eleUtil.switchToFrame(this.parentFrame);
		eleUtil.switchToFrame(this.activityFrame);
		eleUtil.doClick(this.docCreateDate);
		eleUtil.doClick(this.docCreateDate);
		eleUtil.switchToDefaultContent();
		eleUtil.switchToFrame(this.parentFrame);
		eleUtil.switchToFrame(this.activityFrame);
		eleUtil.doClick(this.firstCheckBox);
		eleUtil.switchToDefaultContent();
		eleUtil.switchToFrame(this.parentFrame);
		eleUtil.switchToFrame(this.activityFrame);
		eleUtil.moveToElementAndClick(ActionLink, valueActivity);
		eleUtil.waitForElementPresence(this.dialogFrame, 20);
		eleUtil.switchToFrame(this.dialogFrame);
		eleUtil.wait(1);
		try {
			String diaMessage = eleUtil.doGetText(this.dialogMessage);
			
			String diaTitle = eleUtil.doGetText(this.dialogTitle);
			System.out.println(diaTitle);
			if (diaTitle.contains("Closure Action - Closure Action") & diaMessage.contains("Success.")) {
				ExtentReportListener.test.get().log(Status.PASS,
						"Verification of transfer Closure Action of ActivityA to ActivityC is Successful");
				eleUtil.switchToDefaultContent();
				this.closeActivityADialogBox();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

}
