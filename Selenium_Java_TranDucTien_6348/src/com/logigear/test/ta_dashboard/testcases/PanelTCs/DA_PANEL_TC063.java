package com.logigear.test.ta_dashboard.testcases.PanelTCs;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.logigear.test.ta_dashboard.pom.HomePage;
import com.logigear.test.ta_dashboard.pom.PanelDialog;
import com.logigear.test.ta_dashboard.pom.PanelDialog.ChartType;
import com.logigear.test.ta_dashboard.testcases.Precondition;

public class DA_PANEL_TC063 extends Precondition{
	
	@Test
	public void TC_DA_PANEL_TC063()
	{
		
		LOG.info("Verify that for \"Action Implementation By Status\" panel instance, when user changes from \"Pie\" chart to any other chart type then change back the \"Edit Panel\" form should be as original");
		
//		Step	Navigate to Dashboard login page
//		Step	Login with valid account
//		Step	Click Choose Panels button
//		Step	Click Action Implementation By Status link
//		Step	Click Ok button on Panel Configuration dialog
//		Step	Click Edit Panel icon
//		Step	Click on Chart Type dropped down menu
//		Step	Select Single Bar
//		Step	Click on Chart Type dropped down menu
//		Step	Select Pie
//		VP	Check original "Pie" - Edit Panel form is displayed:  original "Pie" - Edit Panel form is displayed
//		Step	Close "Edit Panel" form
//		Step	Click Edit Panel icon
//		Step	Click on Chart Type dropped down menu
//		Step	Select Stacked Bar
//		Step	Click on Chart Type dropped down menu
//		Step	Select Pie
//		VP	Check original "Pie" - Edit Panel form is displayed:  original "Pie" - Edit Panel form is displayed
//		Step	Close "Edit Panel" form
//		Step	Click Edit Panel icon
//		Step	Click on Chart Type dropped down menu
//		Step	Select Group Bar
//		Step	Click on Chart Type dropped down menu
//		Step	Select Pie
//		VP	Check original "Pie" - Edit Panel form is displayed:  original "Pie" - Edit Panel form is displayed
//		Step	Close "Edit Panel" form
//		Step	Click Edit Panel icon
//		Step	Click on Chart Type dropped down menu
//		Step	Select Line
//		Step	Click on Chart Type dropped down menu
//		Step	Select Pie
//		VP	Check original "Pie" - Edit Panel form is displayed:  original "Pie" - Edit Panel form is displayed
		
		//init data
		String choosePanelsLnkText = "Action Implementation By Status";
		
		//steps
		HomePage homePage = preconditionLoginValid();
		homePage.openChoosePanelsTab()
				.clickLinkedTextInChoosePanelsTab(choosePanelsLnkText)
				.acceptPanelConfiguration();
		PanelDialog panelDialog = homePage.clickEditIcon(choosePanelsLnkText)             		
											.selectChartType(ChartType.SINGLE_BAR.getValue())
											.selectChartType(ChartType.PIE.getValue());
		boolean isCorrect1 = panelDialog.isChartTypeComboboxDisplayCorrectly(ChartType.PIE.getValue());
		
		Assert.assertTrue(isCorrect1, "The \"Chart Type\' combobox is not display correctly.");
		
		panelDialog.closePanelDialog();
		boolean isCorrect2 = homePage.clickEditIcon(choosePanelsLnkText)
										.selectChartType(ChartType.STACKED_BAR.getValue())
										.selectChartType(ChartType.PIE.getValue())
										.isChartTypeComboboxDisplayCorrectly(ChartType.PIE.getValue());
		
		Assert.assertTrue(isCorrect2, "The \"Chart Type\' combobox is not display correctly.");
		
		panelDialog.closePanelDialog();
		boolean isCorrect3 = homePage.clickEditIcon(choosePanelsLnkText)
										.selectChartType(ChartType.GROUP_BAR.getValue())
										.selectChartType(ChartType.PIE.getValue())
										.isChartTypeComboboxDisplayCorrectly(ChartType.PIE.getValue());

		Assert.assertTrue(isCorrect3, "The \"Chart Type\' combobox is not display correctly.");
		
		panelDialog.closePanelDialog();
		boolean isCorrect4 = homePage.clickEditIcon(choosePanelsLnkText)
										.selectChartType(ChartType.LINE.getValue())
										.selectChartType(ChartType.PIE.getValue())
										.isChartTypeComboboxDisplayCorrectly(ChartType.PIE.getValue());

		Assert.assertTrue(isCorrect4, "The \"Chart Type\' combobox is not display correctly.");
		
		panelDialog.closePanelDialog();
	}
	
	@AfterMethod
	public void postCondition() {
		LOG.info("Post-conditions: delete panels in Home page.");
		HomePage homePage = new HomePage();
		homePage.deletePanel("Action Implementation By Status");
	}
	
}
