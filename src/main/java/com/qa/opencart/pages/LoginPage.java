package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	// 1. By locator - OR
		private By emailId = By.id("input-email");
		private By password = By.id("input-password");
		private By loginBtn = By.xpath("//input[@value='Login']");
		private By forgotPwdLink = By.linkText("Forgotten Password");
		private By registerLink = By.linkText("Register");
		
	// constructor
		public LoginPage(WebDriver driver) {
			this.driver=driver;
			eleUtil= new ElementUtil(driver);
		}
		
	//3. page actions
		@Step("getting login page title")
		public String getLoginPageTitle() {
			String title = eleUtil.waitForTitleToBe(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SMALL_DEFAULT_TIME_OUT);
			System.out.println(title);
			return title;
		}
		@Step("getting Login Page Url")
		public String getLoginPageUrl() {
			String url = eleUtil.waitForUrl(AppConstants.SMALL_DEFAULT_TIME_OUT,AppConstants.LOGIN_PAGE_URL_FRACTION);
			System.out.println(url);
			return url;
		}
		@Step("check Forgot Pwd link Exist")
		public boolean isForgotPwdLinkExist() {
			return eleUtil.doIsDisplayed(forgotPwdLink);
			//return driver.findElement(forgotPwdLink).isDisplayed();
		}
		@Step("login with username{0} and password{1}")
		public AccountPage doLogin(String username,String pwd) {
			eleUtil.doSendKeysWithWait(emailId, AppConstants.SMALL_DEFAULT_TIME_OUT, username);
			eleUtil.doSendKeys(password, pwd);
			eleUtil.doClick(loginBtn);
			return new AccountPage(driver);
			//return eleUtil.waitForTitleToBe(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.SMALL_DEFAULT_TIME_OUT);
//			driver.findElement(emailId).sendKeys(userename);
//			driver.findElement(password).sendKeys(pwd);
//			driver.findElement(loginBtn).click();
//			return driver.getTitle();
		}
		@Step("Navigating To Register Page")
		public RegisterPage goToRegisterPage() {
			eleUtil.doClick(registerLink);
			return new RegisterPage(driver);
		}
		@Step("login to Purchase Product")
		public PurchaseProduct Login(String userename,String pwd) {
			eleUtil.doSendKeysWithWait(emailId, AppConstants.SMALL_DEFAULT_TIME_OUT, userename);
			eleUtil.doSendKeys(password, pwd);
			eleUtil.doClick(loginBtn);
			return new PurchaseProduct(driver);
		}
}
