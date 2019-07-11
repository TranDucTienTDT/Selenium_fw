package com.logigear.test.ta_dashboard.testcases.ModifyPageTCs;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.logigear.test.ta_dashboard.data_object.Page;
import com.logigear.test.ta_dashboard.pom.HomePage;
import com.logigear.test.ta_dashboard.testcases.Precondition;

public class DA_MP_TC024 extends Precondition{
	
	@Test
	public void TC_DA_MP_TC024() {
		
		System.out.println("Verify that \"Bread Crums\" navigation is correct");
		
//		Step	Navigate to Dashboard login page		
//		Step	Login with valid account	test / test	
//		Step	Go to Global Setting -> Add page		
//		Step	Enter info into all required fields on New Page dialog: Page name: Page 1
//																		Parent page: Overview
//		Step	Go to Global Setting -> Add page		
//		Step	Enter info into all required fields on New Page dialog	Page name: Page 2
//																		Parent page: Page 1
//		Step	Click the first breadcrums	Page 1	
//		VP		Observe the current page: The first page is navigated
//		Step	Click the second breadcrums		
//		VP		Observe the current page: The second page is navigated

		//init data
		String overviewPage = "Overview";
		Page page1 = new Page("Page1", overviewPage);
		Page page2 = new Page("Page2", "Page1");
		
		//steps
		HomePage homePage = preconditionLoginValid();
		
		boolean isTheFirstPageOpened = homePage.addNewPage(page1)
												.addNewPage(page2)
												.clickSubPage(overviewPage, page1.getPageName())
												.isPageOpened(page1.getPageName());
		
		Assert.assertTrue(isTheFirstPageOpened, "The first page is not navigated as expected.");
		
		boolean isTheSecondPageOpened = homePage.clickSubPage(overviewPage, page1.getPageName(), page2.getPageName())
												.isPageOpened(page2.getPageName());
		
		Assert.assertTrue(isTheSecondPageOpened, "The second page is not navigated as expected.");
	}
	
	@AfterMethod
	public void postCondition() {
		LOG.info("Post-conditions: delete pages");
		HomePage homePage = new HomePage();
		homePage.deletePageHasSubPage("Overview", "Page1", "Page2");
	}

}
