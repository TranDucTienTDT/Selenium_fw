package com.logigear.test.ta_dashboard.pom;

import java.util.ArrayList;
import java.util.Collections;

import com.logigear.test.ta_dashboard.pom.PanelPage.LinkedText;
import com.logigear.testfw.element.Element;

public class DataProfilesPage extends GeneralPage {
	
	protected Element lnkAddNew;
	protected Element lnkDeleteInTopPage;
	protected Element lnkCheckAll;
	protected Element lnkUncheckAll;
	protected Element lnkDataProfilesName;
	protected Element chkDataProfilesName;
	protected Element lnkEdit;
	protected Element lnkDelete;
	
	public DataProfilesPage() {
		super(DataProfilesPage.class);
	}
	
	@Override
	public void initPageElements() {
		super.initPageElements();
		this.lnkAddNew = new Element(getLocator("lnkAddNew").getBy());
		this.lnkDeleteInTopPage = new Element(getLocator("lnkDeleteInTopPage").getBy());
		this.lnkCheckAll = new Element(getLocator("lnkCheckAll").getBy());
		this.lnkUncheckAll = new Element(getLocator("lnkUncheckAll").getBy());
		
	}
	
	public void lnkDataProfilesName(String dataProfileName) {
		this.lnkDataProfilesName =  new Element(getLocator("lnkDataProfilesName").getByWithAltCode(dataProfileName));
	}
	
	public void chkDataProfilesName(String dataProfileName) {
		this.chkDataProfilesName =  new Element(getLocator("chkDataProfilesName").getByWithAltCode(dataProfileName));
	}
	
	public void lnkEdit(String dataProfileName) {
		this.lnkEdit =  new Element(getLocator("lnkEdit").getByWithAltCode(dataProfileName));
	}
	
	public void lnkDelete(String dataProfileName) {
		this.lnkDelete =  new Element(getLocator("lnkDelete").getByWithAltCode(dataProfileName));
	}
	
	//@author hanh.nguyen
	public Object clickLinkedText(LinkedText text) {
		if(text.equals(LinkedText.ADD_NEW)) {
			logger.printMessage("Click \"Add New\" linked text.");
			lnkAddNew.click();
		}
		if(text.equals(LinkedText.DELETE_IN_TOP)) {
			logger.printMessage("Click \"Delete\" linked text on top of page.");
			lnkDeleteInTopPage.click();
		}
		if(text.equals(LinkedText.CHECK_ALL)) {
			logger.printMessage("Click \"Check All\" linked text.");
			lnkCheckAll.click();
		}
		if(text.equals(LinkedText.UNCHECK_ALL)) {
			logger.printMessage("Click \"Uncheck All\" linked text.");
			lnkUncheckAll.click();
		}
		if(text.equals(LinkedText.ADD_NEW))
			return new DPGeneralSettingsPage();
		else
			return new DataProfilesPage();
	}
	
	//@author hanh.nguyen
	public boolean isDataProfileCheckboxChecked(String dataProfileName) {
		lnkDataProfilesName(dataProfileName);
		boolean isChecked = this.lnkDataProfilesName.isSelected();
		logger.printMessage("The checkbox of data profile \"" + dataProfileName + "\" is checked: " + isChecked);
		return isChecked;
	}
	
	//@author hanh.nguyen
	public boolean arePanelCheckboxChecked (String[] dataProfileNames) {
		ArrayList<Boolean> areCheck = new ArrayList<Boolean>(dataProfileNames.length);
		for(int i = 0; i <= dataProfileNames.length; i++) {
			Collections.fill(areCheck, isDataProfileCheckboxChecked(dataProfileNames[i]));
		}
		if(areCheck.contains(false)) {
			logger.printMessage("The data profile checkboxes are not checked all.");
			return false;
		}
		else {
			logger.printMessage("The data profile checkboxes are checked all.");
			return true;
		}
	}
	
	//@author hanh.nguyen
	public DataProfilesPage deleteAllDataProfiles() {
		logger.printMessage("Delete all data profiles in \"Data Profile\" Page.");
		clickLinkedText(LinkedText.CHECK_ALL);
		clickLinkedText(LinkedText.DELETE_IN_TOP);
		acceptAlertPopup();
		return this;
	}
	
	//@author hanh.nguyen
	public DPGeneralSettingsPage clickEditLinkedText(String dataProfileNames) {
		logger.printMessage("Click in \"Edit\" linked text of data profile: " + dataProfileNames);
		lnkEdit(dataProfileNames);
		lnkEdit.click();
		return new DPGeneralSettingsPage();
	}
	
	//@author hanh.nguyen
	public DataProfilesPage deleteDataProfile(String... dataProfileName) {
		logger.printMessage("Delete data profile: " + dataProfileName);
		for (String name : dataProfileName) {
			chkDataProfilesName(name);
			chkDataProfilesName.check();
		}
		clickLinkedText(LinkedText.DELETE_IN_TOP);
		acceptAlertPopup();
		return this;
	}

}
