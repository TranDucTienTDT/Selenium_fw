package com.logigear.test.foody.pom;

import java.util.ArrayList;

import com.logigear.test.foody.data_object.Store;
import com.logigear.testfw.element.Element;

public class FoodySearchResultPage extends FoodyGeneralPage{
	
	protected Element lnkSearchResult;
	protected Element currentSelectedMenuItem;
	protected Element tabMenuItem;
	protected Element lblStoreName;
	protected Element lblStoreAddress;
	
	public FoodySearchResultPage() {
		super(FoodySearchResultPage.class);
	}
	
	@Override
	public void initPageElements() {
		super.initPageElements();
		this.currentSelectedMenuItem = new Element(getLocator("currentSelectedMenuItem").getBy());
	}
	
	public void lnkSearchResult(String name) {
		this.lnkSearchResult = new Element(getLocator("lnkSearchResult").getBy(name));
	}
	
	public void tabMenuItem(String tabName) {
		this.tabMenuItem = new Element(getLocator("tabMenuItem").getBy(tabName));
	}
	
	public void lblStoreName(String storeName) {
		this.lblStoreName = new Element(getLocator("lblStoreName").getBy(storeName));
	}
	
	public void lblStoreAddress(String storeAddress) {
		this.lblStoreAddress = new Element(getLocator("lblStoreAddress").getBy(lblStoreAddress));
	}
	
	public FoodyStorePage chooseLocation(String location) {
		logger.printMessage("Choose a store: " + location);
		lnkSearchResult(location);
		lnkSearchResult.click();
		switchTab(1);
		return new FoodyStorePage();
	}
	//@author tien.duc.tran
	public boolean isStoreDisplayInResultPage(Store... store) {
		ArrayList<Boolean> areDisplay = new ArrayList<Boolean>();
		for (Store s : store) {
			boolean isDisplay = false;
			lblStoreName(s.getName());
			lblStoreAddress(s.getAddress());
			if(lblStoreName.isDisplayed() && lblStoreAddress.isDisplayed())
				isDisplay = true;
			areDisplay.add(isDisplay);
			logger.printMessage(String.format("Is store %s in search result: %s", s.getName(), isDisplay));
			logger.printMessage(String.format("Is store %s in search result: %s", s.getAddress(), isDisplay));
		}
		if(areDisplay.contains(false)) {
			logger.printMessage("The stores are not all in search result.");
			return false;
		}
		else {
			logger.printMessage("The stores are all in search result.");
			return true;
		}
	}

	//@author tien.duc.tran
	//@description: getCurrentSelectedMenuItem
	public String getCurrentSelectedMenuItem () {
		return this.currentSelectedMenuItem.getText();
	}
	
	//@author tien.duc.tran
	//@description: getCurrentSelectedMenuItem
	public FoodySearchResultPage selectTab(String tabName) {
		tabMenuItem(tabName);
		this.tabMenuItem.click();
		return new FoodySearchResultPage();
	}
	
	//@author tien.duc.tran
	public boolean areStoresDisplayInResultPageAndContainText(String... containText) {
		ArrayList<Boolean> areDisplay = new ArrayList<Boolean>();
//		for (String name : store) {
//			boolean isDisplay = false;
//			lnkSearchResult(name);
//			if(lnkSearchResult.isDisplayed())
//				isDisplay = true;
//			areDisplay.add(isDisplay);
//			logger.printMessage(String.format("Is store %s in search result: %s", name, isDisplay));
//		}
		if(areDisplay.contains(false)) {
			logger.printMessage("The stores are not all in search result.");
			return false;
		}
		else {
			logger.printMessage("The stores are all in search result.");
			return true;
		}
	}
}
