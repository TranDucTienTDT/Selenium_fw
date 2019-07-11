package com.logigear.test.ta_dashboard.pom;

import java.util.ArrayList;

import com.logigear.test.ta_dashboard.data_object.DataProfile;
import com.logigear.test.ta_dashboard.pom.DPGeneralSettingsPage.TableNavigatedPage;
import com.logigear.testfw.common.Common;
import com.logigear.testfw.element.Element;

public class DPDisplayFieldsPage extends GeneralPage{
	
	protected Element chkDisplayField;
	protected Element lblHeaderDisplayFields;
	protected Element btnNext;
	protected DPGeneralSettingsPage dpGeneralSettingsPage = new DPGeneralSettingsPage();
	
	public DPDisplayFieldsPage() {
		super(DPDisplayFieldsPage.class);
	}

	@Override
	public void initPageElements() {
		super.initPageElements();	
		this.lblHeaderDisplayFields = new Element(getLocator("lblHeaderDisplayFields").getBy());
		this.btnNext = new Element(getLocator("btnNext").getBy());
	}
	
	public void chkDisplayField(String chkValue) {
		this.chkDisplayField = new Element(getLocator("chkDisplayField").getBy(chkValue));
	}
	
	//@author hanh.nguyen
	public enum DataProfileValueField {
		NAME("Name"),
		DESCRIPTION("Description"),
		ASSIGNED_USER("Assigned user"),
		LAST_UPDATE_DATE("Last update date"),
		CREATION_DATE("Creation date"),
		NOTES("Notes"),
		URL("URL"),
		LOCATION("Location"),
		REVISION_TIMESTAMP("Revision Timestamp"),
		STATUS("Status"),
		LAST_UPDATED_BY("Last updated by"),
		CREATED_BY("Created by"),
		CHECK_OUT_BY("Check out by");
		
		private String _dataProfileValueField;
		
		public String getValue() {
			return _dataProfileValueField;
		}

		public void setValue(String dataProfileValueField) {
			this._dataProfileValueField = dataProfileValueField;
		}

		private DataProfileValueField(String dataProfileValueField) {
			this._dataProfileValueField = dataProfileValueField;
		}
	}
	
	//@author hanh.nguyen
	public void selectDataProfilesDisplayField(String... displayField) {
		logger.printMessage("In \"Display Fields\" page, check on: " + displayField);
		for(String item : displayField) {
			chkDisplayField(item);
			chkDisplayField.check();
		}
	}
	
	//@author hanh.nguyen
	public DPSortFieldsPage submitDataProfilesDisplayFieldPage(String... displayField) {
		logger.printMessage("Submit \"Display Fields\" page.");
		selectDataProfilesDisplayField(displayField);
		gotoNextPage();
		return new DPSortFieldsPage();
	}
	
	//@author hanh.nguyen
	public DPSortFieldsPage submitDataProfilesDisplayFieldPage(DataProfile dataProfile) {
		submitDataProfilesDisplayFieldPage(dataProfile.getDisplayField());
		return new DPSortFieldsPage();
	}
	
	//@author hanh.nguyen
	public boolean isDataProfilesDisplayFieldPageDisplayCorrect(DataProfile dataProfile) {
		ArrayList<Boolean> isCorrect = new ArrayList<Boolean>(dataProfile.getDisplayField().length);
		for (String value : dataProfile.getDisplayField()) {
			chkDisplayField(value);
			isCorrect.add(chkDisplayField.isSelected());
		}
		if(isCorrect.contains(false)) {
			logger.printMessage("The Display Field checkboxes are not checked all.");
			return false;
		}
		else {
			logger.printMessage("The Display Field checkboxes are checked all.");
			return true;
		}
	}
	
	public Object gotoDataProfileSettingsPage(TableNavigatedPage pageName) {
		return dpGeneralSettingsPage.gotoDataProfileSettingsPage(pageName);
	}
	
	//@author hanh.nguyen	
	public DPSortFieldsPage gotoNextPage() {
		logger.printMessage("From \"Display Fields\" page, click \"Next\" to go to \"Sort Fields\" page.");
		btnNext.click();
		lblHeaderDisplayFields.waitForDisappear(Common.ELEMENT_TIMEOUT);
		return new DPSortFieldsPage();
	}

}
