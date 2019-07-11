package com.logigear.test.ta_dashboard.testcases;


import com.logigear.test.ta_dashboard.pom.HomePage;
import com.logigear.test.ta_dashboard.pom.LoginPage;
import com.logigear.testfw.common.BaseTest;

public class Precondition extends BaseTest{
	/*
	 * @author: tien.duc.tran
	 * Method name: precondition()
	 * Purpose/Description: Precondition Login to Dashboard
	 * 
	 * */
	
	public HomePage preconditionLoginValid() {
		logger.printMessage("Precondition: Login with valid username and password.");
		String SAMPLE_REPO = "SampleRepository";
		String USERNAME = "administrator";
		String PASSWORD = "";
		HomePage homePage = new LoginPage().login(SAMPLE_REPO, USERNAME, PASSWORD);
		return homePage;
	}
}
