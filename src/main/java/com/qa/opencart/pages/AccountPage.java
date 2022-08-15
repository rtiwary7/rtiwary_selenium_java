package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. By locator - OR
	private By logoutLink = By.linkText("Logout");
	private By searchField = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	private By accPageHeaders = By.cssSelector("div#content h2");

	// constructor

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3. page actions:
	public String getAccountsPageTitle() {
		String title = eleUtil.waitForTitleToBe(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.SMALL_DEFAULT_TIME_OUT);
		System.out.println("Acc page title : " + title);
		return title;
	}

	public String getAccountsPageUrl() {
		String url = eleUtil.waitForUrl(AppConstants.SMALL_DEFAULT_TIME_OUT, AppConstants.ACCOUNTS_PAGE_URL_FRACTION);
		System.out.println("Acc page url : " + url);
		return url;
	}

	public boolean isLogoutLinkExist() {
		return eleUtil.waitForElementVisible(logoutLink, AppConstants.MEDIUM_DEFAULT_TIME_OUT).isDisplayed();
	}

	public boolean isSearchFieldExist() {
		return eleUtil.waitForElementVisible(searchField, AppConstants.MEDIUM_DEFAULT_TIME_OUT).isDisplayed();
	}

	public List<String> getAccountSectionsHeaderList() {
		return eleUtil.getAllElementsTextList(accPageHeaders, AppConstants.SMALL_DEFAULT_TIME_OUT);
	}

	// common page actions
	public SearchResultsPage doSearch(String productName) {
		System.out.println("Searching for product :" + productName);
		eleUtil.doSendKeysWithWait(searchField, AppConstants.SMALL_DEFAULT_TIME_OUT, productName);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);
	}

}
