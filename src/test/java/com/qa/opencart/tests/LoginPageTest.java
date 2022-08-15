package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic - 1 : Login Functionality")
@Story("US - 1: Verify credentials")
public class LoginPageTest extends BaseTest {

	@Test
	@Description("Test 001: Verify Login Page Title")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test
	@Description("Test 002: Verify Login Page Url")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginPageUrl();
		Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
	}

	@Test
	@Description("Test 003: Verify Forgot Pwd Link Exist")
	@Severity(SeverityLevel.CRITICAL)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Test
	@Description("Test 004: Verify User is able to login or not")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accPage.getAccountsPageTitle().equals(AppConstants.ACCOUNTS_PAGE_TITLE));
	}
	
	@Test(enabled = false)
	public void loginPageFooterTest() {
		System.out.println("loginPageFooterTest");
	}

}
