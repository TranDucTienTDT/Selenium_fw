package com.logigear.test.ta_dashboard.testcases.PanelTCs;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.logigear.test.ta_dashboard.data_object.ChartPanel;
import com.logigear.test.ta_dashboard.data_object.ChartPanel.Legends;
import com.logigear.test.ta_dashboard.pom.HomePage;
import com.logigear.test.ta_dashboard.pom.PanelDialog;
import com.logigear.test.ta_dashboard.pom.PanelDialog.ChartPanelFields;
import com.logigear.test.ta_dashboard.pom.PanelPage;
import com.logigear.test.ta_dashboard.pom.PanelPage.LinkedText;
import com.logigear.test.ta_dashboard.testcases.Precondition;

public class DA_PANEL_TC060 extends Precondition {

	@Test
	public void TC_DA_PANEL_TC060()
	{

		LOG.info("Verify that all settings within \"Add New Panel\" and \"Edit Panel\" form stay unchanged when user switches between \"Legends\" radio buttons in \"Edit Panel\" form");

		//		Step	Navigate to Dashboard login page
		//		Step	Login with valid account
		//		Step	Click Administer link
		//		Step	Click Panel link
		//		Step	Click Add New link
		//		Step	Click None radio button for Legends
		//		VP		Observe the current page
		//		Step	Click Top radio button for Legends
		//		VP		Observe the current page
		//		Step	Click Right radio button for Legends
		//		VP		Observe the current page
		//		Step	Click Bottom radio button for Legends
		//		VP		Observe the current page
		//		Step	Click Left radio button for Legends
		//		VP		Observe the current page
		//		Step	Create a new panel
		//		Step	Click Edit link
		//		Step	Click None radio button for Legends
		//		VP		Observe the current page
		//		Step	Click Top radio button for Legends
		//		VP		Observe the current page
		//		Step	Click Right radio button for Legends
		//		VP		Observe the current page
		//		Step	Click Bottom radio button for Legends
		//		VP		Observe the current page
		//		Step	Click Left radio button for Legends
		//		VP		Observe the current page

		//init data
		String[] ignoredField = {ChartPanelFields.LEGENDS.toString()};
		ChartPanel chartPanel = new ChartPanel("ChartPanel", "Name");

		//steps
		HomePage homePage = preconditionLoginValid();
		PanelDialog panelDialog = (PanelDialog) homePage.gotoPanelPage()
														.clickLinkedText(LinkedText.ADD_NEW);

		String defaultValueInAddNewDialog = panelDialog.getAllSettingsInChartPanelDialog(ignoredField);
		String valueAfterClickNone = panelDialog.selectLegendsField(Legends.NONE)
												.getAllSettingsInChartPanelDialog(ignoredField);

		Assert.assertEquals(defaultValueInAddNewDialog, valueAfterClickNone, "Settings within \"Add New Panel\" form are not stay unchanged.");

		String valueAfterClickTopInAddNew = panelDialog.selectLegendsField(Legends.TOP)
														.getAllSettingsInChartPanelDialog(ignoredField);

		Assert.assertEquals(defaultValueInAddNewDialog, valueAfterClickTopInAddNew, "Settings within \"Add New Panel\" form are not stay unchanged.");

		String valueAfterClickRightInAddNew = panelDialog.selectLegendsField(Legends.RIGHT)
															.getAllSettingsInChartPanelDialog(ignoredField);

		Assert.assertEquals(defaultValueInAddNewDialog, valueAfterClickRightInAddNew, "Settings within \"Add New Panel\" form are not stay unchanged.");

		String valueAfterClickBottomInAddNew = panelDialog.selectLegendsField(Legends.BOTTOM)
															.getAllSettingsInChartPanelDialog(ignoredField);

		Assert.assertEquals(defaultValueInAddNewDialog, valueAfterClickBottomInAddNew, "Settings within \"Add New Panel\" form are not stay unchanged.");

		String valueAfterClickLeftInAddNew = panelDialog.selectLegendsField(Legends.LEFT)
														.getAllSettingsInChartPanelDialog(ignoredField);

		Assert.assertEquals(defaultValueInAddNewDialog, valueAfterClickLeftInAddNew, "Settings within \"Add New Panel\" form are not stay unchanged.");
		
		String defaultValueInEditDialog = panelDialog.addChartPanel(chartPanel)
														.clickEditLinkedText(chartPanel.getDisplayName())
														.getAllSettingsInChartPanelDialog(ignoredField);
		
		valueAfterClickNone = panelDialog.selectLegendsField(Legends.NONE)
											.getAllSettingsInChartPanelDialog(ignoredField);

		Assert.assertEquals(defaultValueInEditDialog, valueAfterClickNone, "Settings within \"Add New Panel\" form are not stay unchanged.");

		String valueAfterClickTopInEdit = panelDialog.selectLegendsField(Legends.TOP)
														.getAllSettingsInChartPanelDialog(ignoredField);

		Assert.assertEquals(defaultValueInEditDialog, valueAfterClickTopInEdit, "Settings within \"Add New Panel\" form are not stay unchanged.");

		
		String valueAfterClickRightInEdit = panelDialog.selectLegendsField(Legends.RIGHT)
														.getAllSettingsInChartPanelDialog(ignoredField);

		Assert.assertEquals(defaultValueInEditDialog, valueAfterClickRightInEdit, "Settings within \"Edit Panel\" form are not stay unchanged.");

		String valueAfterClickBottomInEdit = panelDialog.selectLegendsField(Legends.BOTTOM)
														.getAllSettingsInChartPanelDialog(ignoredField);

		Assert.assertEquals(defaultValueInEditDialog, valueAfterClickBottomInEdit, "Settings within \"Edit Panel\" form are not stay unchanged.");

		String valueAfterClickLeftInEdit = panelDialog.selectLegendsField(Legends.LEFT)
														.getAllSettingsInChartPanelDialog(ignoredField);

		Assert.assertEquals(defaultValueInEditDialog, valueAfterClickLeftInEdit, "Settings within \"Edit Panel\" form are not stay unchanged.");
		
		panelDialog.closePanelDialog();
	}

	@AfterMethod
	public void postCondition() {
		LOG.info("Post-conditions: delete panels in Panel page.");
		PanelPage panelPage = new PanelPage();
		panelPage.deleteAllPanels();
	}

}
