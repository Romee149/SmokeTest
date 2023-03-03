package com.qa.ngageplatform.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.qa.ngageplatform.listeners.ExtentReportListener;
import com.qa.ngageplatform.utils.ElementUtil;

/**
 * This Class is used to provide Object Repo and Actions related to MyWork
 * Screen
 *
 * @author Nahian Omar Faruqe
 * @version 1.0
 * @since 2022-09-28
 */
public class MyWorkScreen {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// ****************** Locators ****************** //
	private By pageFrame = By.id("iframe_105");
	private By pieChartSectionHeader = By.xpath("//*[contains(@id,'hart') and @class='top-header chart-header']");
	private By pieChartSlice = By
			.xpath("//*[contains(@id,'hart')]//*[local-name()='svg']//*[local-name()='g' and @class='nv-slice']");
	private By barGraphSectionHeader = By.xpath("//*[contains(@id,'raph') and @class='top-header chart-header']");
	private By barGraphRectangularBar = By.xpath(
			"//*[contains(@id,'raph')]//*[local-name()='svg']//*[local-name()='rect' and @class='nv-bar positive']");
	private By tableSectionRow = By.xpath("//table[contains(@id,'GridProcess')]//tr[@role='row' and @id]");
	private By processSummarySection_ShowOnTimeWorkItemsLink = By.xpath("//button[contains(@id,'btnOn')]");
	private By processSummarySection_ShowDueSoonWorkItemsLink = By.xpath("//button[contains(@id,'btnDue')]");
	private By processSummarySection_ShowOverdueWorkItemsLink = By.xpath("//button[contains(@id,'btnPast')]");
	private By processSummarySection_ShowAllWorkItemsLink = By.xpath("//button[contains(@id,'btnShow')]");
	private By activityALink = By.xpath("//a[contains(text(),'Activity A')]");
	private By reloadOnPostbackLink = By.xpath("//a[contains(text(),'ReloadOnPostback')]");
	private By activity1Link = By.xpath("//a[contains(text(),'Activity1')]");
	private By closureActionLinkExpandButton = By.xpath("//a[contains(text(),'Closure Action')]//ancestor::li[1]/i");
	private By activityAExpandButton = By.xpath("//a[contains(text(),'Activity A')]//ancestor::li[1]/i");
	private By reloadOnPostbackExpandButton = By.xpath("//a[contains(text(),'ReloadOnPostback')]//ancestor::li[1]/i");
	private By rightClickedOptions = By.xpath("//ul[contains(@class,'jstree-default-contextmenu')]//a");
	private By rightClickedRefreshOption = By.xpath("(//ul[contains(@class,'jstree-default-contextmenu')]//a)[1]");
	private By rightClickedFolderingConfigurationOption = By
			.xpath("(//ul[contains(@class,'jstree-default-contextmenu')]//a)[2]");
	private By activityACustomerName = By.xpath("//a[contains(text(),'Activity A')]//following-sibling::ul/li/a");
	private By firstActivityACustomerName = By
			.xpath("(//a[contains(text(),'Activity A')]//following-sibling::ul/li/a)[1]");
	private By processesLink = By.xpath("//a[text()='Processes']");
	private By closureActionLink = By.xpath("//a[text()='Closure Action']");
	private By activityCLink = By.xpath("//a[contains(text(),'Activity C')]");

	public MyWorkScreen(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		eleUtil.switchToDefaultContent();
	}

	/**
	 * This method is used to Switch to MyWork Screen frame
	 *
	 * @return This will return the Object of MyWork class Object
	 */
	public MyWorkScreen switchToPageFrame() {
		eleUtil.waitForElementPresence(this.pageFrame, 20);
		eleUtil.switchToFrame(this.pageFrame, 20);
		return this;
	}

	/**
	 * This method is used to get Pie Chart Section Header
	 *
	 * @return This will return the Header value of Pie Chart Section in String
	 *         Format
	 */
	public String getPieChartSectionHeader() {
		String title = null;
		try {
			this.switchToPageFrame();
			eleUtil.wait(5);
			eleUtil.waitForElementPresence(this.pieChartSectionHeader, 20);
			eleUtil.wait(5);
			title = eleUtil.doGetText(this.pieChartSectionHeader);
			ExtentReportListener.test.get().log(Status.INFO,
					"Fetching Pie Chart Section Header \'" + title + "\' successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while fetching Pie Chart Section Header");
			Assert.fail(e.getMessage());
		}
		return title;
	}

	/**
	 * This method is used to get Pie Chart Slice count
	 *
	 * @return This will return the Pie Chart Slice Count in int Format
	 */
	public int getPieChartSliceCount() {
		int count = 0;
		try {
			this.switchToPageFrame();
			//eleUtil.waitForElementPresence(this.pieChartSlice, 20);
			count = eleUtil.getAllOptionsOfElement(this.pieChartSlice).size();
			ExtentReportListener.test.get().log(Status.INFO, "Fetching Pie Chart Slice Count successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while fetching Pie Chart Slice Count");
			Assert.fail(e.getMessage());
		}
		return count;
	}

	/**
	 * This method is used to get Bar Graph Section Header
	 *
	 * @return This will return the Header value of Bar Graph Section in String
	 *         Format
	 */
	public String getBarGraphSectionHeader() {
		String title = null;
		try {
			this.switchToPageFrame();
			eleUtil.waitForElementPresence(this.barGraphSectionHeader, 20);
			title = eleUtil.doGetText(this.barGraphSectionHeader);
			ExtentReportListener.test.get().log(Status.INFO,
					"Fetching Bar Graph Section Header \'" + title + "\' successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while fetching Bar Graph Section Header");
			Assert.fail(e.getMessage());
		}
		return title;
	}

	/**
	 * This method is used to get Bar Graph's Rectangular Graph count
	 *
	 * @return This will return the Bar Graph's Rectangular Graph Count in int
	 *         Format
	 */
	public int getBarGraphRectangularBarCount() {
		int count = 0;
		try {
			this.switchToPageFrame();
			eleUtil.waitForElementPresence(this.barGraphRectangularBar, 20);
			count = eleUtil.getAllOptionsOfElement(this.barGraphRectangularBar).size();
			ExtentReportListener.test.get().log(Status.INFO, "Fetching Bar Graph Rectangular Bar Count successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while fetching Bar Graph Rectangular Bar Count");
			Assert.fail(e.getMessage());
		}
		return count;
	}

	/**
	 * This method is used to get Dashboard Table Section Row Count
	 *
	 * @return This will return the Dashboard Table Section Row Count in int Format
	 */
	public int getDashboardTableSectionRowCount() {
		int count = 0;
		try {
			this.switchToPageFrame();
			eleUtil.waitForElementPresence(this.tableSectionRow, 20);
			count = eleUtil.getAllOptionsOfElement(this.tableSectionRow).size();
			ExtentReportListener.test.get().log(Status.INFO, "Fetching Table Section Row Count successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while fetching Table Section Row Count");
			Assert.fail(e.getMessage());
		}
		return count;
	}

	/**
	 * This method is used to get the value of Show All Work Item link
	 *
	 * @return This will return the value of Show All Work Item link in String
	 *         Format
	 */
	public String getShowAllWorkItemsLinkText() {
		String title = null;
		try {
			this.switchToPageFrame();
			eleUtil.waitForElementPresence(this.processSummarySection_ShowAllWorkItemsLink, 20);
			title = eleUtil.doGetText(this.processSummarySection_ShowAllWorkItemsLink);
			ExtentReportListener.test.get().log(Status.INFO,
					"Fetching Show All Work Items Link Header \'" + title + "\' successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while fetching Show All Work Items Link Header");
			Assert.fail(e.getMessage());
		}
		return title;
	}

	/**
	 * This method is used to get the value of Show Due Soon Work Items link
	 *
	 * @return This will return the value of Show Due Soon Work Items link in String
	 *         Format
	 */
	public String getShowDueSoonWorkItemsLinkText() {
		String title = null;
		try {
			this.switchToPageFrame();
			eleUtil.waitForElementPresence(this.processSummarySection_ShowDueSoonWorkItemsLink, 20);
			title = eleUtil.doGetText(this.processSummarySection_ShowDueSoonWorkItemsLink);
			ExtentReportListener.test.get().log(Status.INFO,
					"Fetching Show Due Soon Work Items Link Header \'" + title + "\' successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while fetching Show Due Soon Work Items Link Header");
			Assert.fail(e.getMessage());
		}
		return title;
	}

	/**
	 * This method is used to get the value of Show On Time Work Items link
	 *
	 * @return This will return the value of Show On Time Work Items link in String
	 *         Format
	 */
	public String getShowOnTimeWorkItemsLinkText() {
		String title = null;
		try {
			this.switchToPageFrame();
			eleUtil.waitForElementPresence(this.processSummarySection_ShowOnTimeWorkItemsLink, 20);
			title = eleUtil.doGetText(this.processSummarySection_ShowOnTimeWorkItemsLink);
			ExtentReportListener.test.get().log(Status.INFO,
					"Fetching Show On Time Work Items Link Header \'" + title + "\' successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while fetching Show On Time Work Items Link Header");
			Assert.fail(e.getMessage());
		}
		return title;
	}

	/**
	 * This method is used to get the value of Show Overdue Work Items link
	 *
	 * @return This will return the value of Show Overdue Work Items link in String
	 *         Format
	 */
	public String getShowOverdueWorkItemsLinkText() {
		String title = null;
		try {
			this.switchToPageFrame();
			eleUtil.waitForElementPresence(this.processSummarySection_ShowOverdueWorkItemsLink, 20);
			title = eleUtil.doGetText(this.processSummarySection_ShowOverdueWorkItemsLink);
			ExtentReportListener.test.get().log(Status.INFO,
					"Fetching Show Overdue Work Items Link Header \'" + title + "\' successfully");
			eleUtil.switchToDefaultContent();
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while fetching Show Overdue Work Items Link Header");
			Assert.fail(e.getMessage());
		}
		return title;
	}

	/**
	 * This method is used to expand the Closure Action link
	 *
	 * @return This will return the Object of MyWorkScreen class
	 */
	public MyWorkScreen expandClosureAction() {
		try {
			eleUtil.waitForElementPresence(this.closureActionLinkExpandButton, 60);
			eleUtil.doClick(this.closureActionLinkExpandButton);
			ExtentReportListener.test.get().log(Status.INFO,
					"Expand \'Closure Action\' link under \'My Work\' tab successfully");
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while expanding on \'Closure Action\' link under \'My Work\' tab");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to expand the ActivityA link
	 *
	 * @return This will return the Object of MyWorkScreen class
	 */
	public MyWorkScreen expandActivityA() {
		try {
			eleUtil.wait(1);
			eleUtil.waitForElementPresence(this.activityAExpandButton, 40);
			eleUtil.doClick(this.activityAExpandButton);
			ExtentReportListener.test.get().log(Status.INFO,
					"Expand \'Activity A\' link under \'Closure Action\' link successfully");
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while expanding on \'Activity A\' link under \'Closure Action\' link");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to expand the ReloadOnPostback link
	 *
	 * @return This will return the Object of MyWorkScreen class
	 */
	public MyWorkScreen expandReloadOnPostback() {
		try {
			eleUtil.waitForElementPresence(this.reloadOnPostbackExpandButton, 40);
			eleUtil.doClick(this.reloadOnPostbackExpandButton);
			ExtentReportListener.test.get().log(Status.INFO,
					"Expand \'Reload On Postback\' link under \'My Work\' tab successfully");
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while expanding on \'Reload On Postback\' link under \'My Work\' tab");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to click on ActivityA link
	 *
	 * @return This will return the Object of MyWorkScreen class
	 */
	public WorkItemsScreen clickOnActivityALink() {
		try {
			eleUtil.wait(1);
			eleUtil.waitForElementPresence(this.activityALink, 40);
			try {
				eleUtil.doClick(this.activityALink);
			}catch(Exception e) {
				eleUtil.doClick(this.activityALink);
			}
			eleUtil.wait(1);
			ExtentReportListener.test.get().log(Status.INFO,
					"Clicked on \'Activity A\' link under \'Closure Action\' link successfully");			
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while clicking on \'Activity A\' link under \'Closure Action\' link");
			Assert.fail(e.getMessage());
		}
		return new WorkItemsScreen(this.driver);
	}

	/**
	 * This method is used to right click on ActivityA link
	 *
	 * @return This will return the Object of MyWorkScreen class
	 */
	public MyWorkScreen rightClickOnActivityALink() {
		try {
			eleUtil.waitForElementPresence(this.activityALink, 20);
			eleUtil.doRightClickOnElement(this.activityALink);
			ExtentReportListener.test.get().log(Status.INFO,
					"Right clicked on \'Activity A\' link under \'Closure Action\' link successfully");
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while right clicking on \'Activity A\' link under \'Closure Action\' link");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to right click on Activity1 link
	 *
	 * @return This will return the Object of MyWorkScreen class
	 */
	public MyWorkScreen rightClickOnActivity1Link() {
		try {
			eleUtil.waitForElementPresence(this.activity1Link, 20);
			eleUtil.doRightClickOnElement(this.activity1Link);
			ExtentReportListener.test.get().log(Status.INFO,
					"Right clicked on \'Activity1\' link under \'Reload On Postback\' link successfully");
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while right clicking on \'Activity1\' link under \'Reload On Postback\' link");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to get right click Options for already open options
	 *
	 * @return This will return the List of Right Click options
	 */
	public List<String> getRightClickOptions() {
		List<String> options = new ArrayList<String>();
		try {
			eleUtil.waitForElementPresence(this.rightClickedOptions, 20);
			options = eleUtil.getAllOptionsOfElement(this.rightClickedOptions);
			ExtentReportListener.test.get().log(Status.INFO,
					"Fetching all options when right clicked on \'Activity A\' link under \'Closure Action\' link successfully");
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while fetching all options when right clicking on \'Activity A\' link under \'Closure Action\' link");
			Assert.fail(e.getMessage());
		}
		return options;
	}

	/**
	 * This method is used to get ActivityA's Customer Name List
	 *
	 * @return This will return the List of ActivityA's Customer Name List
	 */
	public List<String> getActivityACustomerNameList() {
		List<String> options = new ArrayList<String>();
		try {
			try {
				eleUtil.waitForElementPresence(this.firstActivityACustomerName, 30);
				options = eleUtil.getAllOptionsOfElement(this.activityACustomerName);
			} catch (StaleElementReferenceException e) {
				driver.navigate().refresh();
				new MainPage(this.driver).clickOnMyWorkTab();
				this.expandClosureAction();
				this.expandActivityA();
				eleUtil.waitForElementPresence(this.firstActivityACustomerName, 30);
				options = eleUtil.getAllOptionsOfElement(this.activityACustomerName);
			}

			ExtentReportListener.test.get().log(Status.INFO,
					"Fetching all options when right clicked on \'Activity A\' link under \'Closure Action\' link successfully");
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while fetching all options when right clicking on \'Activity A\' link under \'Closure Action\' link");
			Assert.fail(e.getMessage());
		}
		return options;
	}

	/**
	 * This method is used to verify the Existence of value on Right Click Option
	 *
	 * @param value Value which we need to check in Right Click's available option
	 * @return This will return the true if value is exist on Right Click Option
	 */
	public boolean verifyExistenceOfValueInRightClickOptions(String value) {
		this.getRightClickOptions();
		List<String> options = eleUtil.getAllOptionsOfElement(this.rightClickedOptions);
		boolean isExists = options.contains(value);
		return isExists;
	}

	/**
	 * This method is used to click on Foldering Configuration option which is
	 * available on Right Click event
	 *
	 * @return This will return the Object of FolderingConfigurationScreen class
	 */
	public FolderingConfigurationScreen clickOnFolderingConfiguration() {
		try {
			eleUtil.waitForElementPresence(this.rightClickedFolderingConfigurationOption, 20);
			eleUtil.doClick(this.rightClickedFolderingConfigurationOption);
			ExtentReportListener.test.get().log(Status.INFO,
					"Clicked on \'Foldering Configuration\' link on Right click action successfully");
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while clicking on \'Foldering Configuration\' link on Right click action");
			Assert.fail(e.getMessage());
		}
		return new FolderingConfigurationScreen(this.driver);
	}

	/**
	 * This method is used to click on Processes link
	 *
	 * @return This will return the Object of MyWorkScreen class
	 */
	public MyWorkScreen clickOnProcesses() {
		try {
			eleUtil.waitForElementPresence(this.processesLink, 20);
			eleUtil.doClick(this.processesLink);
			ExtentReportListener.test.get().log(Status.INFO,
					"Clicked on \'Processes\' link under \'My Work\' tab successfully");
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while clicking on \'Processes\' link under \'My Work\' tab");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to click on Closure Action link
	 *
	 * @return This will return the Object of MyWorkScreen class
	 */
	public MyWorkScreen clickOnClosureAction() {
		try {
			eleUtil.waitForElementPresence(this.closureActionLink, 20);
			eleUtil.doClick(this.closureActionLink);
			ExtentReportListener.test.get().log(Status.INFO,
					"Clicked on \'Closure Action\' link under \'My Work\' tab successfully");
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while clicking on \'Closure Action\' link under \'My Work\' tab");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to click on ActivityC link
	 *
	 * @return This will return the Object of MyWorkScreen class
	 */
	public WorkItemsScreen clickOnActivityCLink() {
		try {
			eleUtil.waitForElementPresence(this.activityCLink, 20);
			eleUtil.doClick(this.activityCLink);
			ExtentReportListener.test.get().log(Status.INFO,
					"Clicked on \'Activity C\' link under \'Closure Action\' link successfully");
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL,
					"Failed while clicking on \'Activity C\' link under \'Closure Action\' link");
			Assert.fail(e.getMessage());
		}
		return new WorkItemsScreen(this.driver);
	}

}
