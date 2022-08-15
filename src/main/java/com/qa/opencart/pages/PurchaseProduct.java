package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class PurchaseProduct {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	public Actions act;
	
	private By navigateLink = By.linkText("Laptops & Notebooks");
	private By allLaptopsLink = By.linkText("Show All Laptops & Notebooks");
	private By LaptopList = By.xpath("//div[@class='product-thumb']//h4//a");
	private By quantityOfProduct=By.xpath("//input[@name='quantity']");
	private By addToCartBttn= By.xpath("//button[text()='Add to Cart']");
	private By verifyQuantityOfProducts = By.id("cart-total");
	private By removeBttn=By.xpath("//button[@title='Remove']");
	private By checkoutLink = By.linkText("Checkout");
	private By billingDetailsLink = By.linkText("Step 2: Billing Details ");
	private By addressBttnOld=By.xpath("//input[@type='radio' and @value ='existing']");
	private By addressBttnNew=By.xpath("//input[@type='radio' and @value ='new']");
	private By continuePaymentAddress = By.id("button-payment-address");
	private By continueShippingAddress = By.id("button-shipping-address");
	private By continueShippingMethod = By.id("button-shipping-method");
	private By continuePaymentMethod = By.id("button-payment-method");
	private By agreeCheckbox = By.xpath("//input[@type='checkbox' and @name='agree']");
	private By confirmOrder = By.xpath("//input[@type='button' and @value='Confirm Order']");
	
	public PurchaseProduct(WebDriver driver) {
		eleUtil = new ElementUtil(driver);
		act = new Actions(driver);
	}
	
	public boolean addToCart(String product,String quantity) {
		eleUtil.doClick(verifyQuantityOfProducts);
		if(eleUtil.getElement(removeBttn).isDisplayed()) {
			eleUtil.getElement(removeBttn).click();
		}
		else {
			System.out.println("no products selected");
		}
		act.moveToElement(eleUtil.getElement(navigateLink)).build().perform();
		eleUtil.waitForElementVisible(allLaptopsLink, AppConstants.MEDIUM_DEFAULT_TIME_OUT).click();
		List<String> avaialableProducts =eleUtil.getAllElementsTextList(LaptopList, AppConstants.MEDIUM_DEFAULT_TIME_OUT);
		System.out.println(avaialableProducts);
		if (avaialableProducts.contains(product)) {
			eleUtil.doClick(By.linkText(product));
		}
		else {
			System.out.println("Product is not available");
			return true;
		}
		eleUtil.doSendKeysWithWait(quantityOfProduct, AppConstants.MEDIUM_DEFAULT_TIME_OUT, quantity);
		eleUtil.clickWhenReady(addToCartBttn, AppConstants.MEDIUM_DEFAULT_TIME_OUT);
		
		String text=eleUtil.waitForElementVisible(verifyQuantityOfProducts, AppConstants.MEDIUM_DEFAULT_TIME_OUT).getText();
		//String text= eleUtil.doElementGetText(verifyQuantityOfProducts);
		System.out.println("selected products  " + text);
		//1 item(s) - $1,000.00
		if (text.contains(quantity + " item(s)" )) {
			return true;
		}
		else
			return false;
	}
	
	
	public void checkout(String product,String quantity) {
		if(addToCart(product,quantity)) {
			eleUtil.doClick(checkoutLink);
		}
	}
	
	
	

	////div[@class='product-thumb']//h4//a
}
