package com.qa.ngageplatform.pages;

import com.qa.ngageplatform.utils.CommonUtil;
import com.qa.ngageplatform.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.ngageplatform.utils.ElementUtil;

/**
 * This Class is used to provide Object Repo and Actions related to Login Screen
 *
 * @author Nahian Omar Faruqe
 * @version 1.0
 * @since 2022-09-28
 */
public class LoginPage {

    private WebDriver driver;
    private ElementUtil eleUtil;


    // ****************** Locators ****************** //
    private By userNameTextField = By.id("Login1_UserName");
    private By passwordTextField = By.id("Login1_Password");
    private By loginButton = By.id("Login1_LoginImageButton");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }


    /**
     * This method is used to enter UserName
     *
     * @param username Username value for Login
     * @return This will return the Object of LoginPage class
     */
    public LoginPage enterUserName(String username) {
        eleUtil.doSendKeys(this.userNameTextField, username);
        return this;

    }

    /**
     * This method is used to enter Password
     *
     * @param password Password value for Login
     * @return This will return the Object of LoginPage class
     */
    public LoginPage enterPassword(String password) {
        eleUtil.doSendKeys(this.passwordTextField, password);
        return this;
    }

    /**
     * This method is used to click on Login button
     *
     * @return This will return the Object of LoginPage class
     */
    public LoginPage clickLoginButton() {
        new JavaScriptUtil(this.driver).clickElementByJS(eleUtil.getElement(this.loginButton));
        return this;
    }

    /**
     * This method is used to enter the credentials on Login Page
     *
     * @param userName Username value for Login
     * @param password Password value for Login
     * @return This will return the Object of MainPage class
     */
    public MainPage doLogin(String userName, String password) {
        this.enterUserName(userName);
        this.enterPassword(password);
        this.clickLoginButton();
        if (eleUtil.isElementExist(this.loginButton)) {
            this.enterUserName(userName);
            this.enterPassword(password);
            this.clickLoginButton();
        }
        return new MainPage(this.driver);
    }

}
