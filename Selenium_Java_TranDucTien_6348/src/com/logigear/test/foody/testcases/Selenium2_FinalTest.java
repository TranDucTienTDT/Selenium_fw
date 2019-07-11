package com.logigear.test.foody.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.logigear.test.foody.data_object.SearchValue;
import com.logigear.test.foody.data_object.Store;
import com.logigear.test.foody.pom.FoodyHomePage;
import com.logigear.test.foody.pom.FoodySearchResultPage;
import com.logigear.testfw.common.BaseTest;

public class Selenium2_FinalTest extends BaseTest {
  @Test
  public void TestCase001() {
//	  1. Navigate to "Foody" website
//	  VP: 
//		  - "Giao tận nơi" title is displayed
//		  - "Đặt bàn ưu đãi" title is displaye
	  FoodyHomePage foodyHomePage = (FoodyHomePage) new FoodyHomePage().waitForPageLoading("Home Page",60);
	  boolean verifyPoint1 = foodyHomePage.isTitleDisplayed("Giao tận nơi");
	  boolean verifyPoint2 = foodyHomePage.isTitleDisplayed("Đặt bàn ưu đãi");
	  Assert.assertTrue(verifyPoint1, "Title is displayed as expected");
	  Assert.assertTrue(verifyPoint2, "Title is displayed as expected");
	  
//	  2. On home page, enter value "Cơm Tấm" in "Filter" textbox	  
//	  3. Click "Search" button
//	  VP: in new page
//	  	- "Địa điểm" menu item is selected
	  SearchValue searchValue = new SearchValue("TP. HCM", "Ăn uống->Quán ăn", "Cơm Tấm");
	  
	  String verifyPoint3 = foodyHomePage.searchFoodStore(searchValue)
			  								.getCurrentSelectedMenuItem();
	  String currentSelectedMenuItem = "Địa điểm";
	  
	  Assert.assertEquals(verifyPoint3, currentSelectedMenuItem);
	  
//	  4. Click tab "Đánh giá tốt nhất"
	  FoodySearchResultPage foodySearchResultPage = new FoodySearchResultPage();
	  
//	  5. Select any item from result grid
	  Store store = new Store("Cơm Tấm Tài","1 Nguyễn An Ninh, P. 14");
//	  VP:
//		- The selected item is displayed correctly in details in next tab. (Name, address,)
	 boolean verifyPoint4 = ((FoodySearchResultPage) foodySearchResultPage.selectTab("Đánh giá tốt nhất")
			 										.waitForPageLoading("Search Resutl Page",60))
			 										.isStoreDisplayInResultPage(store);
	 
	 Assert.assertTrue(verifyPoint4, "The store is not displayed as expected.");
  }
  
}
