package com.logigear.test.ta_dashboard.testcases.DataProfileTCs;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.logigear.test.ta_dashboard.data_object.DataProfile;
import com.logigear.test.ta_dashboard.pom.DPDisplayFieldsPage;
import com.logigear.test.ta_dashboard.pom.DPDisplayFieldsPage.DataProfileValueField;
import com.logigear.test.ta_dashboard.pom.DPFilterFieldsPage;
import com.logigear.test.ta_dashboard.pom.DPGeneralSettingsPage;
import com.logigear.test.ta_dashboard.pom.DPSortFieldsPage;
import com.logigear.test.ta_dashboard.pom.DPStatisticFieldsPage;
import com.logigear.test.ta_dashboard.pom.HomePage;
import com.logigear.test.ta_dashboard.testcases.Precondition;

public class DA_DP_TC097 extends Precondition{
	
		@Test
		public void TC_DA_DP_TC097()
		{
			LOG.info("Verify that all settings are recorded and updated correctly when user click on \"Finish\" buttons ");
			
			//init data
			DataProfile dataProfile = new DataProfile("Test 1", "Data Sets", 
										new String[] {DataProfileValueField.NAME.getValue()}, 
										new String[] {DataProfileValueField.NAME.getValue()}, 
										new String[] {"Assigned user=\"administrator\""}, 
										new String[] {DataProfileValueField.NAME.getValue()});
			
//			Step	Log in Dashboard		
//			Step	Navigate to Data Profiles page		
//			Step	Input to "Name *" field: Test 1	
//			Step	Click "Item Type" dropped down menu and select any item		
//			Step	Click on Next		
//			Step	Check "Name" checkbox		
//			Step	Click on Next		
//			Step	Select "Name" in Field dropped down menu		
//			Step	Click Next		
//			Step	Add a filter criteria: A	
//			Step	Click Next		
//			Step	Check "Name" checkbox		
//			Step	Click "Finish"		
//			Step	Click on data profile created above: Test 1	
//			Step	Navigate through all pages		
//			VP		Check all settings done above are saved correctly: all settings done above are saved correctly

			HomePage homePage = preconditionLoginValid();
			boolean isGeneralSettingsPageCorrect = homePage.gotoGeneralSettingsPageByMenuItem()
															.submitDataProfilesGeneralSettingsPage(dataProfile)
															.submitDataProfilesDisplayFieldPage(dataProfile)
															.submitDataProfilesSortFieldsPage(dataProfile)
															.submitDataProfilesFilterFieldsPage(dataProfile)
															.submitDataProfilesStatisticFieldPage(dataProfile)
															.clickEditLinkedText(dataProfile.getName())
															.isDataProfilesGeneralSettingsPageDisplayCorrect(dataProfile);
			
			Assert.assertTrue(isGeneralSettingsPageCorrect, "\"General Settings\" page is not displayed correctly.");
			
			DPGeneralSettingsPage dpGeneralSettingsPage = new DPGeneralSettingsPage();
			boolean isDisplayFieldsPageCorrect = dpGeneralSettingsPage.gotoNextPage()
																		.isDataProfilesDisplayFieldPageDisplayCorrect(dataProfile);
			
			Assert.assertTrue(isDisplayFieldsPageCorrect, "\"Display Fields\" page is not displayed correctly.");
			
			DPDisplayFieldsPage dpDisplayFieldsPage = new DPDisplayFieldsPage();
			boolean isSortFieldsPageCorrect = dpDisplayFieldsPage.gotoNextPage()
																	.isDataProfilesSortFieldsPageDisplayCorrect(dataProfile);
			
			Assert.assertTrue(isSortFieldsPageCorrect, "\"Sort Fields\" page is not displayed correctly.");
			
			DPSortFieldsPage dpSortFieldsPage = new DPSortFieldsPage();
			boolean isFilterFieldsPageCorrect = dpSortFieldsPage.gotoNextPage()
																.isFilterFieldsTableDisplayCorrect(dataProfile);

			Assert.assertTrue(isFilterFieldsPageCorrect, "\"Filter Fields\" page is not displayed correctly.");
			
			DPFilterFieldsPage dpFilterFieldsPage = new DPFilterFieldsPage();
			boolean isStatisticFieldsPageCorrect = dpFilterFieldsPage.gotoNextPage()
																		.isDataProfilesStatisticFieldPageDisplayCorrect(dataProfile);

			Assert.assertTrue(isStatisticFieldsPageCorrect, "\"Statistic Fields\" page is not displayed correctly.");
		}
		
		@AfterMethod
		public void postCondition() {
			LOG.info("Post-conditions: delete data profile.");
			DPStatisticFieldsPage dpStatisticFieldsPage = new DPStatisticFieldsPage();
			dpStatisticFieldsPage.gotoDataProfilesPage().deleteDataProfile("Test 1");
		}
}
