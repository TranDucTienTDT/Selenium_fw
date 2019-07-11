package com.logigear.test.foody.pom;

import com.logigear.testfw.element.Element;

public class FoodyStorePage extends FoodyGeneralPage{
	
	protected Element lblHeaderTitle;
	protected Element lblStreetAddress;
	protected Element lblDistrictAddress;
	protected Element lblCityAddress;
	
	public FoodyStorePage() {
		super(FoodyStorePage.class);
	}
	
	@Override
	public void initPageElements() {
		super.initPageElements();
		this.lblHeaderTitle = new Element(getLocator("lblHeaderTitle").getBy());
		this.lblStreetAddress = new Element(getLocator("lblStreetAddress").getBy());
		this.lblDistrictAddress = new Element(getLocator("lblDistrictAddress").getBy());
		this.lblCityAddress = new Element(getLocator("lblCityAddress").getBy());
	}
	
	public boolean isNameCorrect(String name) {
		boolean isCorrect = false;
		String actualName = lblHeaderTitle.getText();
		if(actualName.equals(name))
			isCorrect = true;
		logger.printMessage("Is store name correct: " + isCorrect);
		return isCorrect;
	}
	
	public String[] splitAddress(String address) {
		String[] add = new String[3];
		int streetEnd = address.indexOf(",", address.indexOf("."));
		add[0] = address.substring(0, streetEnd);
		int districtEnd = address.indexOf(",", streetEnd + 1);
		add[1] = address.substring(streetEnd + 1, districtEnd).trim();
		add[2] = address.substring(districtEnd + 1).trim();
		return add;
	}
	
	public boolean isAddressCorrect(String address) {
		boolean isCorrect = false;
		boolean isStreetCorrect, isDistrictCorrect, isCityCorrect;
		String[] add = splitAddress(address);
		if(lblStreetAddress.getText().equals(add[0]))
			isStreetCorrect = true;
		else {
			isStreetCorrect = false;
			logger.printMessage("The Street address is false.");
		}
		if(lblDistrictAddress.getText().equals(add[1]))
			isDistrictCorrect = true;
		else {
			isDistrictCorrect = false;
			logger.printMessage("The District address is false.");
		}
		if(lblCityAddress.getText().equals(add[2]))
			isCityCorrect = true;
		else {
			isCityCorrect = false;
			logger.printMessage("The City address is false.");
		}
		if(isStreetCorrect && isDistrictCorrect && isCityCorrect)
			isCorrect = true;
		logger.printMessage("Is address correct: " + isCorrect);
		return isCorrect;
	}

}
