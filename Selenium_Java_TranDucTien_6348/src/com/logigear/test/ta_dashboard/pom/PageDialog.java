package com.logigear.test.ta_dashboard.pom;

import com.logigear.test.ta_dashboard.data_object.Page;
import com.logigear.testfw.common.BasePOM;
import com.logigear.testfw.element.Element;

public class PageDialog extends BasePOM {
	// Element
	protected Element txtPageName;
	protected Element cboParentPage;
	protected Element cboNumberOfColumns;
	protected Element cboDisplayAfter;
	protected Element chkIsPublic;
	protected Element btnOK;
	protected Element btnCancel;

	public PageDialog() {
		super(PageDialog.class);
	}

	@Override
	public void initPageElements() {
		this.txtPageName = new Element(getLocator("txtPageName").getBy());
		this.cboParentPage = new Element(getLocator("cboParentPage").getBy());
		this.cboNumberOfColumns = new Element(getLocator("cboNumberOfColumns").getBy());
		this.cboDisplayAfter = new Element(getLocator("cboDisplayAfter").getBy());
		this.chkIsPublic = new Element(getLocator("chkIsPublic").getBy());
		this.btnOK = new Element(getLocator("btnOK").getBy());
		this.btnCancel = new Element(getLocator("btnCancel").getBy());
	}

	/**
	 * Enter page form.
	 *
	 * @author hanh.nguyen
	 * @param pageName   the page name
	 * @param parentPage the parent page
	 * @param colNumber  the col number
	 * @param afterPage  the after page
	 * @param isPublic   the is public
	 */
	public void fillInfoInPageDialog(String pageName, String parentName, String numberOfColumns,
			String displayAfter, boolean isPublic) {
		if ((pageName != null) && (txtPageName.getText() != null || txtPageName.getText() != pageName)) {
			logger.printMessage("In \"Page Name\" textbox, enter: " + pageName);
			txtPageName.enter(pageName);
		}
		if (parentName != null && cboParentPage.getText() != parentName) {
			logger.printMessage("In \"Parent Page\" combobox, select: " + parentName);
			cboParentPage.selectByTextContains(parentName);
		}
		if (numberOfColumns != null && cboParentPage.getText() != numberOfColumns) {
			logger.printMessage("In \"Number of Columns\" combobox, select: " + numberOfColumns);
			cboNumberOfColumns.selectByText(numberOfColumns);
		}
		if (displayAfter != null && cboParentPage.getText() != displayAfter) {
			logger.printMessage("In \"Display After\" combobox, select: " + displayAfter);
			cboDisplayAfter.selectByText(displayAfter);
		}
		if (chkIsPublic.isSelected() != isPublic) {
			logger.printMessage("In \"Public\" checkbox, check it: " + isPublic);
			if(isPublic)
				chkIsPublic.check();
			else if (!isPublic) {
				chkIsPublic.uncheck();
			}
		}
	}
	
	//@author hanh.nguyen
	public void fillInfoInPageDialog(Page page) {
		fillInfoInPageDialog(page.getPageName(), page.getParentName(), page.getNumberOfColumns(),
				page.getDisplayAfter(), page.isIsPublic());
	}

}
