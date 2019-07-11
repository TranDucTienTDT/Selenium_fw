package com.logigear.test.ta_dashboard.pom;

import com.logigear.testfw.common.Common;
import com.logigear.testfw.element.Element;

public class HomePage extends GeneralPage {

	public HomePage() {
		super(HomePage.class);
	}
	
	protected Element iconEdit;
	protected Element iconMore;
	protected Element iconRemove;

	@Override
	public void initPageElements() {
		super.initPageElements();
	}
	
	public void iconEdit(String lnkText) {
		this.iconEdit = new Element(getLocator("iconEdit").getByWithAltCode(lnkText));
	}
	
	public void iconMore(String lnkText) {
		this.iconMore = new Element(getLocator("iconMore").getByWithAltCode(lnkText));
	}
	
	public void iconRemove(String lnkText) {
		this.iconRemove = new Element(getLocator("iconRemove").getByWithAltCode(lnkText));
	}

	/**
	 * @author 
	 * @description wait page loading.
	 * @param timeOutInSeconds
	 * @return
	 */
	public HomePage waitForLoading(int timeOutInSeconds) {
		tabExecutionDashboard.waitForDisplay(timeOutInSeconds);
		logger.printMessage("Page is loaded successfully");
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public HomePage waitForLoading() {
		waitForLoading(Common.ELEMENT_TIMEOUT);
		return this;
	}
	
	/**
	 * @author nhan.tran
	 * @Description: Check main page apears.
	 * 
	 */
	
	public boolean checkMainPageApears()
	{
		return tabExecutionDashboard.isExisted();
	}
	
	//@author hanh.nguyen
	public PanelDialog clickEditIcon(String panelName) {
		logger.printMessage("Click in \"Edit\" icon of panel: " + panelName);
		iconEdit(panelName);
		iconEdit.click();
		return new PanelDialog();
	}
	
	public HomePage deletePanel(String panelName) {
		logger.printMessage("Delete panel: " + panelName);
		iconMore(panelName);
		iconMore.moveToElement();
		iconRemove(panelName);
		iconRemove.click();
		acceptAlertPopup();
		return this;
	}
	
}
