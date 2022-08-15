package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductPurchaseTest extends BaseTest{
	
	@BeforeClass
	public void productPurchaseSetup() {
		purchaseProduct = loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test
	public void addToCartTest() {
		boolean flag=purchaseProduct.addToCart("Sony VAIO", "2");
		Assert.assertTrue(flag);
	}

}
