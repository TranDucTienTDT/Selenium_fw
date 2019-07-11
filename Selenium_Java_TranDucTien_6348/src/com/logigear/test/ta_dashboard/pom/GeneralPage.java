package com.logigear.test.ta_dashboard.pom;

import java.util.ArrayList;
import java.util.Arrays;

import com.logigear.test.ta_dashboard.data_object.ChartPanel;
import com.logigear.test.ta_dashboard.data_object.Page;
import com.logigear.testfw.common.BasePOM;
import com.logigear.testfw.common.Common;
import com.logigear.testfw.common.TestExecutor;
import com.logigear.testfw.element.Element;

public class GeneralPage extends BasePOM {

	// Element
	protected Element lnkMyProfile;
	protected Element tabExecutionDashboard;
	protected Element lnkGlobalSetting;
	protected Element lnkAddPage;
	protected Element lnkChoosePanels;
	protected Element btnCreateNewPanel;
	protected Element lnkCreatePanel;
	protected Element lnkPage;
	protected Element lnkDelete;
	protected Element lnkEdit;
	protected Element lnkAdminister;
	protected Element lnkDataProfile;
	protected Element lnkPanels;
	protected Element lnkCreateDataProfile;
	protected Element lnkInChoosePanels;
	protected Element listPages;
	
	public PageDialog pageDialog = new PageDialog();
	public PanelDialog panelDialog = new PanelDialog();
	public PanelConfigurationDialog panelConfigurationDialog = new PanelConfigurationDialog();
	
	public GeneralPage(Class<?> derivedClass) {
		super(derivedClass);
	}

	@Override
	public void initPageElements() {
		this.lnkMyProfile = new Element(getLocator("lnkMyProfile").getBy());
		this.tabExecutionDashboard = new Element(getLocator("tabExecutionDashboard").getBy());
		this.lnkGlobalSetting = new Element(getLocator("lnkGlobalSetting").getBy());
		this.lnkAddPage = new Element(getLocator("lnkAddPage").getBy());
		this.lnkChoosePanels = new Element(getLocator("lnkChoosePanels").getBy());
		this.btnCreateNewPanel = new Element(getLocator("btnCreateNewPanel").getBy());
		this.lnkCreatePanel = new Element(getLocator("lnkCreatePanel").getBy());
		this.lnkDelete = new Element(getLocator("lnkDelete").getBy());
		this.lnkEdit = new Element(getLocator("lnkEdit").getBy());
		this.lnkAdminister = new Element(getLocator("lnkAdminister").getBy());
		this.lnkDataProfile = new Element(getLocator("lnkDataProfile").getBy());
		this.lnkCreateDataProfile = new Element(getLocator("lnkCreateDataProfile").getBy());
		this.lnkPanels = new Element(getLocator("lnkPanels").getBy());
		this.listPages = new Element(getLocator("listPages").getBy());
	}
	
	public void lnkPage(String pageName)
	{
		this.lnkPage = new Element(getLocator("lnkPage").getByWithAltCode(pageName));
	}
	
	public void lnkInChoosePanels(String lnkText) {
		this.lnkInChoosePanels = new Element(getLocator("lnkInChoosePanels").getByWithAltCode(lnkText));
	}

	/**
	 * Open Add New Page dialog or Edit Page dialog.
	 *
	 * @author hanh.nguyen
	 */
	public PageDialog openPageDialog() {
		logger.printMessage("Open \"New Page\" or \"Edit Page\" dialog.");
		lnkGlobalSetting.click();
		lnkAddPage.click();
		return new PageDialog();
	}

	/**
	 * @author nhan.tran
	 * @Description: Select menu item without <option>tab by hold/click
	 * @param selectedElement Element that should be select.
	 */

	public void selectMenuItem(Element menuElement, Element selectedElement) {
		try {
			menuElement.moveToElement();
			selectedElement.click();
		} catch (Exception error) {
			LOG.severe(String.format("Has error when select item in %d"));
		}
	}
	
	/**
	 * @author hanh.nguyen
	 * @Description: Verify that the page is opened or not
	 * @param pageName	name of the page
	 */
	public boolean isPageOpened(String pageName) {
		String actualTitle = TestExecutor.getInstance().getCurrentDriver().getTitle();
		boolean isOpened = actualTitle.contains(pageName);
		logger.printMessage("Is page \"" + pageName + "\" opened: " + isOpened);
		return isOpened;
	}

	/**
	 * @author: duy.nguyen
	 * @Description: get the currently URL
	 * @param: url (output) URL
	 */
	public String getUrl() {
		String url = TestExecutor.getInstance().getCurrentDriver().getCurrentUrl();
		return url;
	}
	
	/**
	 * @author NhanTran
	 * @description: getText of element
	 * */
	public String getTextOfElementAfter(String elementBefore)
	{
		String xpath = String.format("//ul[./li/a[text()='%s']]/li)[2]", elementBefore);
		Element element = new Element(xpath);
		String result = element.getText();
		return result;
	}
	
	/**
	 * Open Add New Panel dialog.
	 *
	 * @author hanh.nguyen
	 * @param isFromChoosePanels open the dialog from "Choose Panels" linked button
	 *                           or from "Global Setting" linked button
	 */
	
	public PanelDialog openPanelDialog(boolean isFromChoosePanels) {
		logger.printMessage("Open \"Add New Panel\" dialog.");
		if (isFromChoosePanels) {
			openChoosePanelsTab();
			btnCreateNewPanel.click();
		} else if (!isFromChoosePanels) {
			selectMenuItem(lnkGlobalSetting, lnkCreatePanel);
		}
		return new PanelDialog();
	}
	
	//@author hanh.nguyen
	public DPGeneralSettingsPage gotoGeneralSettingsPageByMenuItem() {
		logger.printMessage("Open \"General Settings\" page by selecting menu item.");
		selectMenuItem(lnkGlobalSetting, lnkCreateDataProfile);
		return new DPGeneralSettingsPage();
	}
	
	//@author hanh.nguyen
	public GeneralPage addNewPage(Page page)
	{
		logger.printMessage("Add a Page: " + page.getPageName());
		openPageDialog();
		pageDialog.fillInfoInPageDialog(page);
		pageDialog.btnOK.click();
		pageDialog.btnOK.waitForDisappear(Common.ELEMENT_TIMEOUT);
		return this;
	}
	
	//@author hanh.nguyen
	public GeneralPage addChartPanel(ChartPanel chartPanel, boolean isFromChoosePanels) {
		openPanelDialog(isFromChoosePanels);
		panelDialog.addChartPanel(chartPanel);
		return this;
	}
	
	//@author hanh.nguyen
	public GeneralPage cancelPanelConfiguration() {
		panelConfigurationDialog.cancelPanelConfiguration();
		return this;
	}
	
	//@author hanh.nguyen
	public GeneralPage deletePage(String pageName) {
		logger.printMessage("Delete page: " + pageName);
		clickPage(pageName);
		selectMenuItem(lnkGlobalSetting, lnkDelete);
		acceptAlertPopup();
		return this;
	}
	
	//@author hanh.nguyen
	public GeneralPage deleteSubPages(String... pageNames) {
		clickSubPage(pageNames);
		deletePage(pageNames[pageNames.length - 1]);
		return this;
	}
	
	//@author hanh.nguyen
	public GeneralPage deletePageHasSubPage(String... pageNames) {
		ArrayList<String> page = (ArrayList<String>) Arrays.asList(pageNames);
		for (int i = pageNames.length; i > 0; i--) {
			pageNames = (String[]) page.toArray();
			deleteSubPages(pageNames);
			page.remove(i - 1);
		}
		return this;
	}
	
	//@author hanh.nguyen
	public void acceptAlertPopup() {
		TestExecutor.getInstance().getCurrentDriver().waitForAlertPopupPresent(Common.ELEMENT_TIMEOUT);
		TestExecutor.getInstance().getCurrentDriver().switchTo().alert().accept();
	}
	
//	public GeneralPage deleteAllPage() {
//		
//	}
	
	//@author hanh.nguyen
	public PanelPage gotoPanelPage() {
		logger.printMessage("Go to Panel Page.");
		selectMenuItem(lnkAdminister, lnkPanels);
		return new PanelPage();
	}
	
	//@author hanh.nguyen
	public GeneralPage openChoosePanelsTab() {
		if(lnkChoosePanels.isAttributePresent("class")) {
			logger.printMessage("Click in \"Choose Panels\" button to open \"Choose Panels\" tab.");
			lnkChoosePanels.moveToElement();
			lnkChoosePanels.click();
		}
		return this;
	}
	
	//@author hanh.nguyen
	public PanelConfigurationDialog clickLinkedTextInChoosePanelsTab(String lnkText) {
		logger.printMessage("In \"Choose Panels\" tab, click: " + lnkText);
		lnkInChoosePanels(lnkText);
		lnkInChoosePanels.click();
		return new PanelConfigurationDialog();
	}
	
//	public boolean validatePagePosition(String[] listPageNames) {
//		List<WebElement> actualPageName = listPages;
//		int firstPageIndex = Array.IndexOf(actualPageName, pageName[0]);
//		
//	}
	
	//@author hanh.nguyen
	public GeneralPage clickPage(String pageName) {
		logger.printMessage("Select page: " + pageName);
		lnkPage(pageName);
		lnkPage.click();
		lnkPage.waitForDisplay(Common.ELEMENT_TIMEOUT);
		return this;
	}
	
	//@author hanh.nguyen
	public GeneralPage clickSubPage(String... pageNames) {
		logger.printMessage("Select pages: " + pageNames);
		for (int i = 0; i < (pageNames.length - 2); i++) {
			lnkPage(pageNames[i]);
			lnkPage.moveToElement();
			lnkPage.waitForDisplay(Common.ELEMENT_TIMEOUT);
		}
		clickPage(pageNames[pageNames.length - 1]);
		return this;
	}
	
	//@author hanh.nguyen
	public DataProfilesPage gotoDataProfilesPage() {
		logger.printMessage("Go to Data Profiles Page.");
		selectMenuItem(lnkAdminister, lnkDataProfile);
		return new DataProfilesPage();
	}
	
}

