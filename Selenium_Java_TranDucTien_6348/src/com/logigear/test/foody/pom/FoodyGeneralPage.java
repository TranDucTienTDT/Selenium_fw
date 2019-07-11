package com.logigear.test.foody.pom;

import java.util.ArrayList;

import com.logigear.test.foody.data_object.SearchValue;
import com.logigear.testfw.common.BasePOM;
import com.logigear.testfw.common.Common;
import com.logigear.testfw.common.TestExecutor;
import com.logigear.testfw.element.Element;

public class FoodyGeneralPage extends BasePOM {
	
	protected Element cbbLocation;
	protected Element mneLocation;
	protected Element cbbCategory;
	protected Element optCategory;
	protected Element mneCategory;
	protected Element optSubCategory;
	protected Element txtSearch;
	protected Element btnSearch;
	protected Element btnFilter;
	protected Element btnSelectType;
	protected Element btnChangeLanguage;
	protected Element imgLanguageValue;
	protected Element optLanguage;
	protected Element tblDelivery;
	protected Element optLocation;
	protected Element txtLocation;
	protected Element filterForm;
	protected Element mneSelectType;
	protected Element optSelectType;
	
	protected FilterForm searchFilterForm = new FilterForm();
	
	public FoodyGeneralPage(Class<?> derivedClass) {
		super(derivedClass);
	}
	
	@Override
	public void initPageElements() {
		this.cbbLocation = new Element(getLocator("cbbLocation").getBy());
		this.mneLocation = new Element(getLocator("mneLocation").getBy());
		this.cbbCategory = new Element(getLocator("cbbCategory").getBy());
		this.mneCategory = new Element(getLocator("mneCategory").getBy());
		this.txtSearch = new Element(getLocator("txtSearch").getBy());
		this.btnSearch = new Element(getLocator("btnSearch").getBy());
		this.btnFilter = new Element(getLocator("btnFilter").getBy());
		this.btnSelectType = new Element(getLocator("btnSelectType").getBy());
		this.btnChangeLanguage = new Element(getLocator("btnChangeLanguage").getBy());
		this.imgLanguageValue = new Element(getLocator("imgLanguageValue").getBy());
		this.tblDelivery = new Element(getLocator("tblDelivery").getBy());
		this.txtLocation = new Element(getLocator("txtLocation").getBy());
		this.filterForm = new Element(getLocator("filterForm").getBy());
		this.mneSelectType = new Element(getLocator("mneSelectType").getBy());
		
	}
	
	public void optCategory(String type) {
		this.optCategory = new Element(getLocator("optCategory").getBy(type));
	}
	
	public void optSubCategory(String type, String subType) {
		this.optSubCategory = new Element(getLocator("optSubCategory").getBy(type, subType));
	}
	
	public void optLanguage(String language) {
		this.optLanguage = new Element(getLocator("optLanguage").getBy(language));
	}
	
	public void optLocation(String location) {
		this.optLocation = new Element(getLocator("optLocation").getBy(location));
	}
	
	public void optSelectType(String type) {
		this.optSelectType = new Element(getLocator("optSelectType").getBy(type));
	}
	
	public FoodySearchResultPage searchWithOnlyLocation(String foodStore) {
		logger.printMessage("Enter value: " + foodStore + " in \"Search\" textbox to search.");
		enterFilterValue(foodStore);
		btnSearch.click();
		//tblDelivery.waitForDisappear(Common.ELEMENT_TIMEOUT);
		return new FoodySearchResultPage();
	}
	
	//@author hanh.nguyen
	//The tabIndex starts with 0
	public void switchTab(int tabIndex) {
		try {
			LOG.info(String.format("Try to switch tab in browser."));
			
			ArrayList<String> tabs = new ArrayList<String> (TestExecutor.getInstance().getCurrentDriver().getWindowHandles());
			if(tabIndex > tabs.size() - 1) {
				LOG.severe("The index is out of bound.");
				return;
			}
			TestExecutor.getInstance().getCurrentDriver().switchTo().window(tabs.get(tabIndex));
		} catch (Exception error) {
			LOG.severe(String.format("Has error when switching tab in browser."));
		}
	}
	
	public void enterFilterValue(String value) {
		if(value != null && (txtSearch.getText() == null || txtSearch.getText() != value)) {
			logger.printMessage("In \"Search\" textbox, enter: " + value);
			txtSearch.enter(value);
		}
	}
	
	public void openMenuAndForm(Element openItem, Element clickItem) {
		if(openItem.isExisted()) {
			if(!openItem.isDisplayed()) {
				clickItem.click();
				openItem.waitForDisplay(Common.ELEMENT_TIMEOUT);
			}
		}
		else if (!openItem.isExisted()) {
			clickItem.click();
			openItem.waitForDisplay(Common.ELEMENT_TIMEOUT);
		}
	}
	
	public void selectLocation(String location) {
		if(location != null && !location.equals(cbbLocation.getText())){
			logger.printMessage("In \"Location\" combobox, select: " + location);
			openMenuAndForm(mneLocation, cbbLocation);
			optLocation(location);
			optLocation.click();
			optLocation.waitForDisappear(Common.ELEMENT_TIMEOUT);
		}
	}
	
	public FoodySearchResultPage selectHeadCategory(String category) {
		if(category != null){
			logger.printMessage("In \"Category\" combobox, select: " + category);
			openMenuAndForm(mneCategory, cbbCategory);
			String[] menuItem = category.split("->", 2);
			optCategory(menuItem[0].trim());
			optCategory.moveToElement();
			optSubCategory(menuItem[0].trim(), menuItem[1].trim());
			optSubCategory.click();
			optSubCategory.waitForDisappear(Common.ELEMENT_TIMEOUT);
		}
		return new FoodySearchResultPage();
	}
	
	public void selectType(String type) {
		openMenuAndForm(mneSelectType, btnSelectType);
		optSelectType(type);
		optSelectType.click();
		optSelectType.waitForDisappear(Common.ELEMENT_TIMEOUT);
	}
	
	public FoodySearchResultPage searchFoodStore(SearchValue searchValue) {
		logger.printMessage("Search food store.");
		System.out.println(searchValue.getHeadCategory());
		selectLocation(searchValue.getLocation());
		selectHeadCategory(searchValue.getHeadCategory());
		enterFilterValue(searchValue.getStore());
		if(searchValue.getDistrict() != null || searchValue.getCuisine() != null || searchValue.getCategory() != null) {
			submitFilterForm(searchValue);
		}
		else
			btnSearch.click();
		return new FoodySearchResultPage();
	}
	
	public FilterForm openFilterForm() {
		logger.printMessage("Open \"Filter Form\" by clicking \"Filert\" button.");
		openMenuAndForm(filterForm, btnFilter);
		return new FilterForm();
	}
	
	public FoodySearchResultPage submitFilterForm(SearchValue searchValue) {
		openFilterForm();
		searchFilterForm.submitFilterForm(searchValue);
		return new FoodySearchResultPage();
	}
	
	public enum Language { 
		ENGLISH("us"),
		VIETNAMESE("vn");
		
		private String _language;
		
		public String getValue() {
			return _language;
		}

		public void setValue(String language) {
			this._language = language;
		}

		private Language(String language) {
			this._language = language;
		}
	}
	
	public FoodyGeneralPage changeLanguage(Language language) {
		if(!imgLanguageValue.getAttribute("src").contains(language.getValue())) {
			logger.printMessage("Change language to: " + language);
			optLanguage(language.getValue());
			openMenuAndForm(optLanguage, btnChangeLanguage);
			optLanguage.click();
			optLanguage.waitForDisappear(Common.ELEMENT_TIMEOUT);
		}
		return this;
	}
	
//	public enum Page{
//		HOME_PAGE("Home Page"),
//		SEARCH_RESULT_PAGE("Search Result Page"),
//		STORE_PAGE("Store Page");
//		
//		private String page;
//		
//		public String getValue() {
//			return page;
//		}
//
//		public void setValue(String _page) {
//			this.page = _page;
//		}
//
//		private Page(String _page) {
//			this.page = _page;
//		}
//	}
	
	//@author tien.duc.tran
	//@description: waitForPageLoading
	//@parameter: timeOut
	public Object waitForPageLoading(String pageName, int timeOut) {
		this.btnChangeLanguage.waitForDisplay(timeOut);
		if(pageName == "Home Page")
			return new FoodyHomePage();
		else if(pageName == "Search Result Page")
			return new FoodySearchResultPage();
		else
			return new FoodyStorePage();
	}
}
