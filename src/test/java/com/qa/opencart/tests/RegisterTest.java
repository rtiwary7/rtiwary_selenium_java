package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterTest extends BaseTest{
	
	@BeforeClass
	public void regSetup() {
		regPage=loginPage.goToRegisterPage();
	}
	
//	@DataProvider
//	public Object[][] getRegData() {
//		return new Object[][] {
//			{"abc", "manual", "test@1234.com", "123456", "jio@123", "Yes"},
//			{"xyz", "auto", "auto@1234.com", "123456", "air@123", "Yes"},
//			{"rty", "qa", "qa@1234.com", "123456", "bsnl@123", "Yes"},
//			{"note", "load", "hi@1234.com", "123456", "voda@123", "Yes"},
//		};
//	}
	
	@DataProvider
	public Object[][] getExcelRegData() {
		Object regData[][]=ExcelUtil.getTestData(AppConstants.RESISTER_SHEET_NAME);
		return regData;
	}
	
	public String randomEmail() {
		Random random = new Random();
		String email = "rtiwary"+random.nextInt(1000)+"@gmail.com";
		return email;
	}
	
	@Test(dataProvider="getExcelRegData")
	public void userRegistrationTest(String firstName, String lastName,String phone, String password, String subscribe) {
		boolean successFlag=
				regPage.userRegistration(firstName, lastName, randomEmail(), phone, password, subscribe);
		regPage.goToRegisterPage();
		Assert.assertTrue(successFlag);
	}

}
