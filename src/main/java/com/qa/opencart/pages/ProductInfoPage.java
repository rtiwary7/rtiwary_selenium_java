package com.qa.opencart.pages;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String, String> productMap;

	// 1. By locator - OR
	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	// constructor

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// page actions
	public String getProductHeaderValue() {
		// By mainProduct = By.xpath("//h1[text()='" + mainProductName + "']");
		String prodHeaderValue = eleUtil.doElementGetText(productHeader);
		System.out.println(prodHeaderValue);
		return prodHeaderValue;
	}

	public int getProductImagesCount() {
		int imgCount = eleUtil.waitForElementsToBeVisible(productImages, AppConstants.MEDIUM_DEFAULT_TIME_OUT).size();
		System.out.println(imgCount);
		return imgCount;
	}

	public Map<String, String> getProductInfo() {

		// add producName in productMap

		//productMap = new HashMap<String, String>();      // no order
		//productMap = new LinkedHashMap<String, String>(); ///maintain order
		productMap = new TreeMap<String, String>(); ///sorted order (alphabetic order based on key)
		productMap.put("productName", getProductHeaderValue());
		getProductMetaData();
		getPriceMetaData();
		
		System.out.println("-----actual Product info------");
		productMap.forEach((k,v) -> System.out.println(k + ":" + v));
		return productMap;
	}

	private void getProductMetaData() {
		
		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
//		Brand: Apple
//		Product Code: Product 18
//		Reward Points: 800
//		Availability: In Stock

		for (WebElement e : metaDataList) {
			String text = e.getText();
			String metaData[] = text.split(":");
			String Key = metaData[0].trim();
			String value = metaData[1].trim();
			productMap.put(Key, value);

		}
	}

	private void getPriceMetaData() {

		// price data
//		$2,000.00
//		Ex Tax: $2,000.00
		List<WebElement> metaPriceList = eleUtil.getElements(productPriceData);
		String productPrice = metaPriceList.get(0).getText();
		String productExTaxPrice = metaPriceList.get(1).getText();

		productMap.put("productPrice", productPrice);
		productMap.put("exTaxPrice", productExTaxPrice);

	}

}
