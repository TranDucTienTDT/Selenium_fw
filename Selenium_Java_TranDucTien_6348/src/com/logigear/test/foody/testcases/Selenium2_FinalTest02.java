package com.logigear.test.foody.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.logigear.test.foody.data_object.SearchValue;
import com.logigear.test.foody.pom.FoodyHomePage;
import com.logigear.test.foody.pom.FoodySearchResultPage;
import com.logigear.testfw.common.BaseTest;

public class Selenium2_FinalTest02 extends BaseTest {
	@Test
	public void TestCase002() {
		//		  1. Navigate to "Foody" website
		//		  2. Select "Tỉnh thành" combobox value "TP.HCM"
		//		  3. Select "Category" combobox value "Ăn Uống -> Quán Ăn"
		//		  4. Enter value "Phở Bò" in "Filter" textbox 
		//		  5. Click "Search" button
		//		  VP:
		//			- The name of all displayed items in the result grid contains "Phở/Bò"

		SearchValue searchValue = new SearchValue("TP. HCM", "Ăn uống->Quán ăn", "Phở Bò");
		FoodySearchResultPage foodySearchResultPage = new FoodyHomePage().searchFoodStore(searchValue);
		
		boolean verifyPoint = foodySearchResultPage.areStoresDisplayInResultPageAndContainText("Phở","Bò");
		Assert.assertTrue(verifyPoint, "The stores are not display as expected.");
	}
}
