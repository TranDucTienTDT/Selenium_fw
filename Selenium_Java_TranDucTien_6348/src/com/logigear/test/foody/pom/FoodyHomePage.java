package com.logigear.test.foody.pom;

import com.logigear.testfw.element.Element;

public class FoodyHomePage extends FoodyGeneralPage{
	
	protected Element lblTitle;
	
	public FoodyHomePage() {
		super(FoodyHomePage.class);
	}
	
	@Override
	public void initPageElements() {
		super.initPageElements();
	}
	
	public void lblTitle(String title) {
		this.lblTitle =  new Element(getLocator("lblTitle").getBy(title));
	}
	
	
	//@author tien.duc.tran
	//@description: check title is displayed
	//@parameter: title
	public boolean isTitleDisplayed(String title) {
		lblTitle(title);
		return this.lblTitle.isDisplayed();
	}
	
	

}
