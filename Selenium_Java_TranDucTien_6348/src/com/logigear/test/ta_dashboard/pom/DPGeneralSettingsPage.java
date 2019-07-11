package com.logigear.test.ta_dashboard.pom;

import com.logigear.test.ta_dashboard.data_object.DataProfile;
import com.logigear.testfw.common.Common;
import com.logigear.testfw.element.Element;

public class DPGeneralSettingsPage extends GeneralPage{
	
	protected Element lnkMenuOption;
	protected Element txtName;
	protected Element cbbItemType;
	protected Element cbbRelatedData;
	protected Element btnNext;
	protected Element lblHeader;
	
	protected Element btnGeneral;
	
	public DPGeneralSettingsPage() {
		super(DPGeneralSettingsPage.class);
	}

	@Override
	public void initPageElements() {
		super.initPageElements();	
		this.txtName = new Element(getLocator("txtName").getBy());
		this.cbbItemType = new Element(getLocator("cbbItemType").getBy());
		this.cbbRelatedData = new Element(getLocator("cbbRelatedData").getBy());
		this.btnNext = new Element(getLocator("btnNext").getBy());
	}
	
	public void lnkMenuOption(String pageName) {
		this.lnkMenuOption = new Element(getLocator("lnkMenuOption").getBy(pageName));
	}
	
	public void lblHeader(String pageName) {
		this.lblHeader = new Element(getLocator("lblHeader").getBy(pageName));
	}
	
	/**
	 * @author: tien.duc.tran
	 * @Description: isItemTypeExist (Check all fields of selected "Item Type" item are populated correctly)
	 * @param: 
	 */
	
	public boolean isItemTypeExist() {
		//Lam mot ham check rowItem co display trong table hay khong?
		return true;
	}
	
	//@author hanh.nguyen
	public enum TableNavigatedPage{
		GENERAL_SETTINGS("General Settings"),
		DISPLAY_FIELDS("Display Fields"),
		SORT_FIELDS("Sort Fields"),
		FILTER_FIELDS("Filter Fields"),
		STATISTIC_FIELDS("Statistic Fields");
		
		private String tableNavigatedPage;
		
		public String getValue() {
			return tableNavigatedPage;
		}

		public void setValue(String _tableNavigatedPage) {
			this.tableNavigatedPage = _tableNavigatedPage;
		}

		private TableNavigatedPage(String _tableNavigatedPage) {
			this.tableNavigatedPage = _tableNavigatedPage;
		}
	}
	
	/**
	 * @author: tien.duc.tran
	 * @Description: navigateSettingPage (Select left side menu item)
	 * @param: option
	 */

	public Object gotoDataProfileSettingsPage (TableNavigatedPage pageName) {

		lnkMenuOption(pageName.getValue());
		lnkMenuOption.click();
		lblHeader(pageName.getValue());
		lblHeader.waitForDisappear(Common.ELEMENT_TIMEOUT);
		logger.printMessage("Navigate to Data Profile page: " + pageName);

		if(pageName == TableNavigatedPage.GENERAL_SETTINGS) {
			return new DPGeneralSettingsPage();
		}else if(pageName == TableNavigatedPage.DISPLAY_FIELDS) {
			return new DPDisplayFieldsPage();
		}else if(pageName == TableNavigatedPage.SORT_FIELDS) {
			return new DPSortFieldsPage();
		}else if(pageName == TableNavigatedPage.FILTER_FIELDS) {
			return new DPFilterFieldsPage();
		}else if(pageName == TableNavigatedPage.STATISTIC_FIELDS) {
			return new DPStatisticFieldsPage();
		}else
			return this;

	}
	
	//@author hanh.nguyen
	public void fillInDataProfilesGeneralSettingsPage(String name, String itemType, String relatedData) {
		if((name != null) && (txtName.getText() != null || txtName.getText() != name)) {
			logger.printMessage("In \"Name\" textbox, enter: " + name);
			txtName.enter(name);
		}
		if(itemType != null && cbbItemType.getText() != itemType) {
			logger.printMessage("In \"Item Type\" combobox, select: " + itemType);
			cbbItemType.selectByTextIgnoreCase(itemType);
		}
		if(relatedData != null && cbbRelatedData.getText() != relatedData) {
			logger.printMessage("In \"Related Data\" combobox, select: " + relatedData);
			cbbRelatedData.selectByTextIgnoreCase(relatedData);
		}
	}
	
	//@author hanh.nguyen
	public DPDisplayFieldsPage submitDataProfilesGeneralSettingsPage(String name, String itemType, String relatedData) {
		logger.printMessage("Submit \"General Settings\" page.");
		fillInDataProfilesGeneralSettingsPage(name, itemType, relatedData);
		gotoNextPage();
		return new DPDisplayFieldsPage();
	}
	
	//@author hanh.nguyen
	public DPDisplayFieldsPage submitDataProfilesGeneralSettingsPage(DataProfile dataProfile) {
		submitDataProfilesGeneralSettingsPage(dataProfile.getName(), dataProfile.getItemType(), dataProfile.getRelatedData());
		return new DPDisplayFieldsPage();
	}
	
	//@author hanh.nguyen
	public boolean isDataProfilesGeneralSettingsPageDisplayCorrect(DataProfile dataProfile) {
		boolean isCorrect = false;
		String actualValue = txtName.getValue() + cbbItemType.getSelectedOption() + cbbRelatedData.getSelectedOption();
		String relatedData = dataProfile.getRelatedData();
		if(dataProfile.getRelatedData() == null)
			relatedData = "None";
		String expectedValue = dataProfile.getName() + dataProfile.getItemType() + relatedData;
		if(actualValue.equals(expectedValue))
			isCorrect = true;
		logger.printMessage("Is Data Profiles \"General Settings\" page display correct: " + isCorrect);
		return isCorrect;
	}
	
	//@author tien.duc.tran
	public enum GeneralButton{
		BACK("Back"),
		NEXT("Next"),
		FINISH("Finish"),
		CANCEL("Cancel");

		private String generalButton;

		public String getValue() {
			return generalButton;
		}

		public void setValue(String _generalButton) {
			this.generalButton = _generalButton;
		}

		private GeneralButton(String _generalButton) {
			this.generalButton = _generalButton;
		}
	}

	/**
	 * @author: tien.duc.tran
	 * @Description: clickButton (click button Back, next, finish, cancel)
	 * @param: generalButton
	 */
	public void clickButton(GeneralButton generalButton) {
		btnGeneral = new Element(getLocator("btnGeneral").getBy(generalButton.getValue()));
		btnGeneral.click();
	}
		
	//@author hanh.nguyen	
	public DPDisplayFieldsPage gotoNextPage() {
		logger.printMessage("From \"General Settings\" page, click \"Next\" to go to \"Display Fields\" page.");
		btnNext.click();
		txtName.waitForDisappear(Common.ELEMENT_TIMEOUT);
		return new DPDisplayFieldsPage();
	}

}
