package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.PurchaseProduct;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {
	
	public WebDriver driver;
	public DriverFactory df;
	public LoginPage loginPage;
	public Properties prop;
	public AccountPage accPage;
	public SearchResultsPage searchResPage;
	public ProductInfoPage prodInfoPage;
	public SoftAssert softAssert;
	public RegisterPage regPage;
	public PurchaseProduct purchaseProduct;
	
	@BeforeTest
	public void setup() {
		df= new DriverFactory();
		prop=df.initProp();
		driver=df.initDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert=new SoftAssert();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
