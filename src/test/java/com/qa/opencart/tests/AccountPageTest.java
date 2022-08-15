package com.qa.opencart.tests;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountPageTest extends BaseTest{
	
	@BeforeClass
	public void accountSetup() {
		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test
	public void accountPageTitleTest() {
		Assert.assertEquals(accPage.getAccountsPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test
	public void accPageHeaderTest() {
		List<String> accSecHeader=accPage.getAccountSectionsHeaderList();
		System.out.println(accSecHeader);
		Collections.sort(accSecHeader);
		Collections.sort(AppConstants.EXPECTED_ACCOUNTS_HEADERS_LIST);
		Assert.assertEquals(accSecHeader,AppConstants.EXPECTED_ACCOUNTS_HEADERS_LIST);
//		List<String> sortedList=accSecHeader.stream().sorted().collect(Collectors.toList());
//		List<String> expectedsortedList=AppConstants.EXPECTED_ACCOUNTS_HEADERS_LIST.stream().sorted().collect(Collectors.toList());
//		Assert.assertEquals(sortedList,expectedsortedList);

	}
	
	

}
