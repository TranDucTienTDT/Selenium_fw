package com.logigear.test.ta_dashboard.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.logigear.test.ta_dashboard.data_object.ChartPanel;
import com.logigear.test.ta_dashboard.data_object.Page;
import com.logigear.test.ta_dashboard.pom.HomePage;
import com.logigear.test.ta_dashboard.pom.PanelPage;
import com.logigear.test.ta_dashboard.pom.PanelPage.LinkedText;

public class PanelTest extends Precondition{
	
		@Test
		public void TC_DA_PANEL_TC064()
		{
			LOG.info("Verify that \"Check All/Uncheck All\" links are working correctly");
			
			//init data
			Page page = new Page("TestPage");
			ChartPanel chartPanel1 = new ChartPanel("ChartPanel1", "Name");
			ChartPanel chartPanel2 = new ChartPanel("ChartPanel2", "Name");
			String[] check = {chartPanel1.getDisplayName(), chartPanel2.getDisplayName()};
			
//			Step	Navigate to Dashboard login page
//			Step	Select a specific repository 
//			Step	Enter valid Username and Password
//			Step	Click 'Login' button
//			Step	Click 'Add Page' button
//			Step	Enter Page Name
//			Step	Click 'OK' button
//			Step	Click 'Choose Panels' button below 'main_hung' button
//			Step	Click 'Create new panel' button
//			Step	Enter a name to Display Name
//			Step	Click OK button
//			Step	Click Cancel button
//			Step	Click 'Create new panel' button
//			Step	Enter a name to Display Name
//			Step	Click OK button
//			Step	Click Cancel button
//			Step	Click 'Administer' link
//			Step	Click 'Panels' link
//			Step	Click 'Check All' link
			
			HomePage homePage = preconditionLoginValid();
			PanelPage panelPage = (PanelPage) homePage.addNewPage(page)
											.addChartPanel(chartPanel1, false)
											.cancelPanelConfiguration()							
											.addChartPanel(chartPanel2, false)	
											.cancelPanelConfiguration()
											.gotoPanelPage()
											.clickLinkedText(LinkedText.CHECK_ALL);
			
//			VP	Check that 'hung_a' checkbox and 'hung_b' checkbox are checked					
			boolean areChecked = panelPage.arePanelCheckboxChecked(check);
			Assert.assertTrue(areChecked, "\"Check All\" links aren't working correctly.");
			
//			Step	Click 'Uncheck All' link
//			VP	Check that 'hung_a' checkbox and 'hung_b' checkbox are unchecked
			
			boolean areUnchecked = ((PanelPage) panelPage.clickLinkedText(LinkedText.UNCHECK_ALL))
											.arePanelCheckboxChecked(check);
			Assert.assertFalse(areUnchecked, "\"Uncheck All\" links aren't working correctly.");
	}
		
		@AfterMethod
		public void postCondition() {
			LOG.info("Post-conditions: delete page and panels");
			PanelPage panelPage = new PanelPage();
			panelPage.deleteAllPanels()
						.deletePage("TestPage");
		}
}
