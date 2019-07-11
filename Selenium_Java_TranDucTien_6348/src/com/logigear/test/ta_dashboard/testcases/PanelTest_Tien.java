package com.logigear.test.ta_dashboard.testcases;

import org.testng.annotations.Test;

import com.logigear.test.ta_dashboard.pom.HomePage;
import com.logigear.test.ta_dashboard.pom.PanelDialog;
import com.logigear.test.ta_dashboard.pom.PanelDialog.PanelSettingType;
import com.logigear.test.ta_dashboard.pom.PanelPage.LinkedText;


import org.testng.Assert;

public class PanelTest_Tien extends Precondition{
	@Test
	public void DA_PANEL_TC031() {
/*
		Navigate to Dashboard login page
		Login with valid account
		Click on Administer/Panels link
		Click on Add new link
		VP: Verify that chart panel setting form is displayed with corresponding panel type selected
		
		Select Indicator type
		VP: Verify that indicator panel setting form is displayed with corresponding panel type selected
		
		Select Report type
		VP: Verify that report panel setting form is displayed with corresponding panel type selected
		
		Select Heat Maps type
		VP: Verify that heatmap panel setting form is displayed with corresponding panel type selected
*/
		HomePage homePage = preconditionLoginValid();

		PanelDialog panelDialog = (PanelDialog) homePage.gotoPanelPage()
														.clickLinkedText(LinkedText.ADD_NEW);
		boolean actualResult = panelDialog.isPanelSettingFormDisplayed(PanelSettingType.CHART);
		Assert.assertTrue(actualResult, "Panel setting From does not display properly.");
				
		actualResult = panelDialog.clickHeatMapRadioButton().isPanelSettingFormDisplayed(PanelSettingType.INDICATOR);
		Assert.assertTrue(actualResult, "Panel setting From does not display properly.");
		
		actualResult = panelDialog.clickHeatMapRadioButton().isPanelSettingFormDisplayed(PanelSettingType.REPORT);
		Assert.assertTrue(actualResult, "Panel setting From does not display properly.");
		
		actualResult = panelDialog.clickHeatMapRadioButton().isPanelSettingFormDisplayed(PanelSettingType.HEAT_MAP);
		Assert.assertTrue(actualResult, "Panel setting From does not display properly.");

		
	}
}
