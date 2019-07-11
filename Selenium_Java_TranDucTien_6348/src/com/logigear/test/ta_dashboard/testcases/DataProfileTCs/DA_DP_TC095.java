package com.logigear.test.ta_dashboard.testcases.DataProfileTCs;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.logigear.test.ta_dashboard.data_object.DataProfile;
import com.logigear.test.ta_dashboard.pom.DPGeneralSettingsPage;
import com.logigear.test.ta_dashboard.pom.DPGeneralSettingsPage.TableNavigatedPage;
import com.logigear.test.ta_dashboard.pom.DPStatisticFieldsPage;
import com.logigear.test.ta_dashboard.pom.HomePage;
import com.logigear.test.ta_dashboard.testcases.Precondition;

public class DA_DP_TC095 extends Precondition{
	
	@Test
	public void TC_DA_DP_TC095() {

		LOG.info("Verify that all fields of selected \"Item Type\" item are populated correctly ");
		//init data
		String[] displayFieldList = {""};
		String[] sortFieldList = {""};
		String[] filterFieldList = {""};
		String[] statisticFieldList = {"Name", "Location", "Description", "Recent result", "Source", "Revision Timestamp", 
				"Assigned user", "Priority", "Status", "Last update date","Last updated by", "Creation date", "Created by", 
				"Notes", "Check out by", "URL"};
		DataProfile dataProfile = new DataProfile("Test Modules", "test modules", "None", displayFieldList, sortFieldList, filterFieldList, statisticFieldList);

		/*
		 * Log in Dashboard
		 * Navigate to Data Profiles page
		 * Input to "Name *" field
		 * Click "Item Type" dropped down menu and choose Test Modules
		 * Navigate to Statistic page
		 * VP: Check all fields of selected "Item Type" item are populated correctly
		 */ 
		HomePage homePage = preconditionLoginValid();
		boolean actualResult = ((DPStatisticFieldsPage) homePage.gotoGeneralSettingsPageByMenuItem()
				.submitDataProfilesGeneralSettingsPage(dataProfile)
				.gotoDataProfileSettingsPage(TableNavigatedPage.STATISTIC_FIELDS))
				.isAllStatisticFieldsDisplayCorrect(dataProfile);

		Assert.assertTrue(actualResult, "The item types don't display as expected");

		/* Navigate to General Settings page
		 * Click "Item Type" dropped down menu and choose Test Cases
		 * Navigate to Statistic page
		 * VP: Check all fields of selected "Item Type" item are populated correctly
		 */
		statisticFieldList = new String[]{"ID", "Location","Title", "Recent result", "Notes","Source", "URL"};
		dataProfile = new DataProfile("Test Cases", "test cases", "None", displayFieldList, sortFieldList, filterFieldList, statisticFieldList);

		actualResult = ((DPStatisticFieldsPage) ((DPGeneralSettingsPage) new DPGeneralSettingsPage().gotoDataProfileSettingsPage(TableNavigatedPage.GENERAL_SETTINGS))
				.submitDataProfilesGeneralSettingsPage(dataProfile)
				.gotoDataProfileSettingsPage(TableNavigatedPage.STATISTIC_FIELDS))
				.isAllStatisticFieldsDisplayCorrect(dataProfile);

		Assert.assertTrue(actualResult, "The item types don't display as expected");

		/* Navigate to General Settings page
		 * Click "Item Type" dropped down menu and choose Test Objectives
		 * Navigate to Statistic page
		 * VP: Check all fields of selected "Item Type" item are populated correctly
		 */ 
		statisticFieldList = new String[]{"ID", "Location","Title", "Recent result", "Notes", "Source", "URL"};
		dataProfile = new DataProfile("Test Objectives", "test objectives", "None", displayFieldList, sortFieldList, filterFieldList, statisticFieldList);

		actualResult = ((DPStatisticFieldsPage) ((DPGeneralSettingsPage) new DPGeneralSettingsPage().gotoDataProfileSettingsPage(TableNavigatedPage.GENERAL_SETTINGS))
				.submitDataProfilesGeneralSettingsPage(dataProfile)
				.gotoDataProfileSettingsPage(TableNavigatedPage.STATISTIC_FIELDS))
				.isAllStatisticFieldsDisplayCorrect(dataProfile);

		Assert.assertTrue(actualResult, "The item types don't display as expected");


		/* Navigate to General Settings page
		 * Click "Item Type" dropped down menu and choose Data Sets
		 * Navigate to Statistic page
		 * VP: Check all fields of selected "Item Type" item are populated correctly
		 */ 
		statisticFieldList = new String[]{"Name", "Location", "Description", "Revision Timestamp", 
				"Assigned user", "Status", "Last update date","Last updated by", "Creation date", "Created by", 
				"Notes", "Check out by", "URL"};
		dataProfile = new DataProfile("Data sets", "data sets", "None", displayFieldList, sortFieldList, filterFieldList, statisticFieldList);

		actualResult = ((DPStatisticFieldsPage) ((DPGeneralSettingsPage) new DPGeneralSettingsPage().gotoDataProfileSettingsPage(TableNavigatedPage.GENERAL_SETTINGS))
				.submitDataProfilesGeneralSettingsPage(dataProfile)
				.gotoDataProfileSettingsPage(TableNavigatedPage.STATISTIC_FIELDS))
				.isAllStatisticFieldsDisplayCorrect(dataProfile);

		Assert.assertTrue(actualResult, "The item types don't display as expected");

		/* Navigate to General Settings page
		 * Click "Item Type" dropped down menu and choose Actions
		 * Navigate to Statistic page
		 * VP: Check all fields of selected "Item Type" item are populated correctly
		 */ 
		statisticFieldList = new String[]{"Name", "Location", "Description", "Revision Timestamp", 
				"Assigned user", "Status", "Last update date","Last updated by", "Creation date", "Created by", 
				"Notes", "Check out by", "URL"};
		dataProfile = new DataProfile("Actions", "actions", "None", displayFieldList, sortFieldList, filterFieldList, statisticFieldList);

		actualResult = ((DPStatisticFieldsPage) ((DPGeneralSettingsPage) new DPGeneralSettingsPage().gotoDataProfileSettingsPage(TableNavigatedPage.GENERAL_SETTINGS))
				.submitDataProfilesGeneralSettingsPage(dataProfile)
				.gotoDataProfileSettingsPage(TableNavigatedPage.STATISTIC_FIELDS))
				.isAllStatisticFieldsDisplayCorrect(dataProfile);

		Assert.assertTrue(actualResult, "The item types don't display as expected");

		/* Navigate to General Settings page
		 * Click "Item Type" dropped down menu and choose Interface Entities
		 * Navigate to Statistic page
		 * VP: Check all fields of selected "Item Type" item are populated correctly
		 */ 
		statisticFieldList = new String[]{"Name", "Location", "Description", "Revision Timestamp", 
				"Assigned user", "Status", "Last update date","Last updated by", "Creation date", "Created by", 
				"Notes", "Check out by", "URL"};
		dataProfile = new DataProfile("Interface Entities", "interface entities", "None", displayFieldList, sortFieldList, filterFieldList, statisticFieldList);

		actualResult = ((DPStatisticFieldsPage) ((DPGeneralSettingsPage) new DPGeneralSettingsPage().gotoDataProfileSettingsPage(TableNavigatedPage.GENERAL_SETTINGS))
				.submitDataProfilesGeneralSettingsPage(dataProfile)
				.gotoDataProfileSettingsPage(TableNavigatedPage.STATISTIC_FIELDS))
				.isAllStatisticFieldsDisplayCorrect(dataProfile);

		Assert.assertTrue(actualResult, "The item types don't display as expected");

		/* Navigate to General Settings page
		 * Click "Item Type" dropped down menu and choose Test Results
		 * Navigate to Statistic page
		 * VP: Check all fields of selected "Item Type" item are populated correctly
		 */
		statisticFieldList = new String[]{"Name", "Location", "Reported by", "Date of run","Start time", "End time", "Duration", "Comment", "Variation"
				,"Result", "Passed", "Failed", "Warnings", "Errors", "Automated/Manual", "Run Machine" ,"Notes", "URL", "Baseline", "OS", "Device OS",
				"Device Name", "Build Number", "AUTVersion", "Unverified picture checks"};
		dataProfile = new DataProfile("Test Results", "test results", "None", displayFieldList, sortFieldList, filterFieldList, statisticFieldList);

		actualResult = ((DPStatisticFieldsPage) ((DPGeneralSettingsPage) new DPGeneralSettingsPage().gotoDataProfileSettingsPage(TableNavigatedPage.GENERAL_SETTINGS))
				.submitDataProfilesGeneralSettingsPage(dataProfile)
				.gotoDataProfileSettingsPage(TableNavigatedPage.STATISTIC_FIELDS))
				.isAllStatisticFieldsDisplayCorrect(dataProfile);

		Assert.assertTrue(actualResult, "The item types don't display as expected");

		/* Navigate to General Settings page
		 * Click "Item Type" dropped down menu and choose Test Case Results
		 * Navigate to Statistic page
		 * VP: Check all fields of selected "Item Type" item are populated correctly
		 */
		statisticFieldList = new String[]{"Name", "Location", "Date of run", "Result", "Passed", "Failed", "Warnings", "Errors","Notes", "URL", "Build Number", "Unverified picture checks"};
		dataProfile = new DataProfile("Test Case Results", "test case results", "None", displayFieldList, sortFieldList, filterFieldList, statisticFieldList);

		actualResult = ((DPStatisticFieldsPage) ((DPGeneralSettingsPage) new DPGeneralSettingsPage().gotoDataProfileSettingsPage(TableNavigatedPage.GENERAL_SETTINGS))
				.submitDataProfilesGeneralSettingsPage(dataProfile)
				.gotoDataProfileSettingsPage(TableNavigatedPage.STATISTIC_FIELDS))
				.isAllStatisticFieldsDisplayCorrect(dataProfile);

		Assert.assertTrue(actualResult, "The item types don't display as expected");
	}
}
