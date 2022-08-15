package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. By locator - OR
	private By productCount = By.cssSelector("div#content div.product-thumb");

	// constructor

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//page actions
	
	public int getProductSearchCount() {
		return eleUtil.waitForElementsToBeVisible(productCount, AppConstants.MEDIUM_DEFAULT_TIME_OUT).size();
	}
	
	public ProductInfoPage selectProduct(String searchProductName) {
		eleUtil.doClick(By.linkText(searchProductName));
		return new ProductInfoPage(driver);
		
	}

}
