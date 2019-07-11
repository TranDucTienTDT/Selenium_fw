package com.logigear.test.ta_dashboard.pom;

import java.util.ArrayList;

import com.logigear.test.ta_dashboard.data_object.DataProfile;
import com.logigear.testfw.common.Common;
import com.logigear.testfw.element.Element;

public class DPStatisticFieldsPage extends GeneralPage{
	
	protected Element chkStatisticField;
	protected Element lblStatisticField;
	protected Element btnFinish;
	protected Element lnkCheckAll;
	protected Element lnkUncheckAll;
	
	public DPStatisticFieldsPage() {
		super(DPStatisticFieldsPage.class);
	}

	@Override
	public void initPageElements() {
		super.initPageElements();	
		this.btnFinish = new Element(getLocator("btnFinish").getBy());
		this.lnkCheckAll = new Element(getLocator("lnkCheckAll").getBy());
		this.lnkUncheckAll = new Element(getLocator("lnkUncheckAll").getBy());
	}
	
	public void chkStatisticField(String statisticField) {
		this.chkStatisticField = new Element(getLocator("chkStatisticField").getBy(statisticField));
	}
	
	public void lblStatisticField(String statisticField) {
		this.lblStatisticField = new Element(getLocator("lblStatisticField").getBy(statisticField));
	}
	
	//@author hanh.nguyen
	public void selectDataProfilesStatisticField(String... statisticField) {
		logger.printMessage("In \"Statistic Fields\" page, check on: " + statisticField);
		for(String item : statisticField) {
			chkStatisticField(item);
			chkStatisticField.check();
		}
	}
		
	//@author hanh.nguyen
	public DataProfilesPage submitDataProfilesStatisticFieldPage(String... statisticField) {
		logger.printMessage("Submit \"Statistic Fields\" page.");
		selectDataProfilesStatisticField(statisticField);
		submitNewDataProfile();
		return new DataProfilesPage();
	}
	
	//@author hanh.nguyen
	public DataProfilesPage submitDataProfilesStatisticFieldPage(DataProfile dataProfile) {
		submitDataProfilesStatisticFieldPage(dataProfile.getStatisticField());
		return new DataProfilesPage();
	}
	
	//@author hanh.nguyen
	public boolean isDataProfilesStatisticFieldPageDisplayCorrect(DataProfile dataProfile) {
		ArrayList<Boolean> isCorrect = new ArrayList<Boolean>(dataProfile.getStatisticField().length);
		for (String value : dataProfile.getStatisticField()) {
			chkStatisticField(value);
			isCorrect.add(chkStatisticField.isSelected());
		}
		if (isCorrect.contains(false)) {
			logger.printMessage("The Statistic Field checkboxes are not checked all.");
			return false;
		} else {
			logger.printMessage("The Statistic Field checkboxes are checked all.");
			return true;
		}
	}
	
	/**
	 * @author: tien.duc.tran
	 * @Description: isStatisticItemTypeDisplayCorrect (check checkboxes display properly follow Item Type)
	 * @param: DataProfile dataProfile
	 */
	
	public boolean isAllStatisticFieldsDisplayCorrect(DataProfile dataProfile) {
		ArrayList<Boolean> isCorrect = new ArrayList<Boolean>(dataProfile.getStatisticField().length);
		for (String value : dataProfile.getStatisticField()) {
			lblStatisticField(value);
			isCorrect.add(lblStatisticField.isDisplayed());
		}
		if (isCorrect.contains(false)) {
			logger.printMessage("The Statistic Field checkboxes do not display properly.");
			return false;
		} else {
			logger.printMessage("The Statistic Field checkboxes display properly.");
			return true;
		}
	}
	
	
	//@author hanh.nguyen	
	public DataProfilesPage submitNewDataProfile() {
		logger.printMessage("From \"Statistic Fields\" page, click \"Finish\" to sumit a new data profile and go to \"Data Profiles\" page.");
		btnFinish.click();
		btnFinish.waitForDisappear(Common.ELEMENT_TIMEOUT);
		return new DataProfilesPage();
	}

}
