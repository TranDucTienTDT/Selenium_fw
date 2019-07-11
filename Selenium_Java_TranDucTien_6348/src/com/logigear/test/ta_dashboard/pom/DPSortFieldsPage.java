package com.logigear.test.ta_dashboard.pom;

import java.util.ArrayList;

import com.logigear.test.ta_dashboard.data_object.DataProfile;
import com.logigear.testfw.common.Common;
import com.logigear.testfw.element.Element;

public class DPSortFieldsPage extends GeneralPage{
	
	public DPSortFieldsPage() {
		super(DPSortFieldsPage.class);
	}
	
	protected Element cbbField;
	protected Element btnAddLevel;
	protected Element lblSortFieldValue;
	protected Element btnNext;

	@Override
	public void initPageElements() {
		super.initPageElements();	
		this.cbbField = new Element(getLocator("cbbField").getBy());
		this.btnAddLevel = new Element(getLocator("btnAddLevel").getBy());
		this.btnNext = new Element(getLocator("btnNext").getBy());
		
	}
	
	public void lblSortFieldValue(String sortField) {
		this.lblSortFieldValue = new Element(getLocator("lblSortFieldValue").getBy(sortField));
	}
	
	//@author hanh.nguyen
	public void selectDataProfilesSortFields(String... sortField) {
		logger.printMessage("In \"Sort Fields\" page, select: " + sortField);
		for(String item : sortField) {
			cbbField.selectByText(item);
			btnAddLevel.click();
			lblSortFieldValue(item);
			lblSortFieldValue.waitForDisplay(Common.ELEMENT_TIMEOUT);
		}
	}
	
	//@author hanh.nguyen
	public DPFilterFieldsPage submitDataProfilesSortFieldsPage(String... sortField) {
		logger.printMessage("Submit \"Sort Fields\" page.");
		selectDataProfilesSortFields(sortField);
		gotoNextPage();
		return new DPFilterFieldsPage();
	}
	
	//@author hanh.nguyen
	public DPFilterFieldsPage submitDataProfilesSortFieldsPage(DataProfile dataProfile) {
		submitDataProfilesSortFieldsPage(dataProfile.getSortField());
		return new DPFilterFieldsPage();
	}

	// @author hanh.nguyen
	public boolean isDataProfilesSortFieldsPageDisplayCorrect(DataProfile dataProfile) {
		ArrayList<Boolean> isCorrect = new ArrayList<Boolean>(dataProfile.getSortField().length);
		for (String value : dataProfile.getSortField()) {
			lblSortFieldValue(value);
			isCorrect.add(lblSortFieldValue.isDisplayed());
		}
		if (isCorrect.contains(false)) {
			logger.printMessage("The Sort Field values are not displayed correctly.");
			return false;
		} else {
			logger.printMessage("The Sort Field values are displayed correctly.");
			return true;
		}
	}
	
	//@author hanh.nguyen	
	public DPFilterFieldsPage gotoNextPage() {
		logger.printMessage("From \"Sort Fields\" page, click \"Next\" to go to \"Filter Fields\" page.");
		btnNext.click();
		btnAddLevel.waitForDisappear(Common.ELEMENT_TIMEOUT);
		return new DPFilterFieldsPage();
	}

}
