package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductSearchTest extends BaseTest {

	@BeforeClass
	public void productSearchSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] { { "Macbook", "MacBook Pro" }, { "Macbook", "MacBook Air" },
				{ "Samsung", "Samsung SyncMaster 941BW" } };
	}

	@Test(dataProvider = "getProductData")
	public void productSearchTest(String searchKey, String productName) {
		searchResPage = accPage.doSearch(searchKey);
		prodInfoPage = searchResPage.selectProduct(productName);
		String actProductHeader = prodInfoPage.getProductHeaderValue();
		Assert.assertEquals(actProductHeader, productName);
	}

	@DataProvider
	public Object[][] getProductInfoData() {
		return new Object[][] { { "Macbook", "MacBook Pro" ,4}, { "Macbook", "MacBook Air" ,4},
				{ "Samsung", "Samsung SyncMaster 941BW" ,1}, { "iMac", "iMac" ,3}};
	}

	@Test(dataProvider = "getProductInfoData")
	public void productImageCountTest(String searchKey, String productName, int imagesCount) {
		searchResPage = accPage.doSearch(searchKey);
		prodInfoPage = searchResPage.selectProduct(productName);
		int actImagesCount = prodInfoPage.getProductImagesCount();
		Assert.assertEquals(actImagesCount, imagesCount);
	}
	
	@Test
	public void getProductDataTest() {
		searchResPage = accPage.doSearch("Macbook");
		prodInfoPage = searchResPage.selectProduct("MacBook Pro");
		Map<String,String> actProductInfo=prodInfoPage.getProductInfo();
		
		softAssert.assertEquals(actProductInfo.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfo.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductInfo.get("Reward Points"), "800");
		softAssert.assertEquals(actProductInfo.get("Availability"), "In Stock");
		softAssert.assertEquals(actProductInfo.get("productPrice"), "$2,000.00");
		softAssert.assertEquals(actProductInfo.get("exTaxPrice"), "Ex Tax: $2,000.00");
		
		softAssert.assertAll();
	}

}
