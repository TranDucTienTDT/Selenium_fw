package com.logigear.test.foody.pom;

import com.logigear.test.foody.data_object.SearchValue;
import com.logigear.testfw.common.BasePOM;
import com.logigear.testfw.element.Element;

public class FilterForm extends BasePOM{
	
	protected Element tabDistrict;
	protected Element tabCuisine;
	protected Element tabCategory;
	protected Element chkSelectValue;
	protected Element btnSearch;
	protected Element btnClearFilter;
	
	public FilterForm() {
		super(FilterForm.class);
	}
	
	@Override
	public void initPageElements() {
		this.tabDistrict = new Element(getLocator("tabDistrict").getBy());
		this.tabCuisine = new Element(getLocator("tabCuisine").getBy());
		this.tabCategory = new Element(getLocator("tabCategory").getBy());
		this.btnSearch = new Element(getLocator("btnSearch").getBy());
		this.btnClearFilter = new Element(getLocator("btnClearFilter").getBy());
	}
	
	public void chkSelectValue(String value) {
		this.chkSelectValue = new Element(getLocator("chkSelectValue").getBy(value));
	}
	
	public void selectDistrict(String... district) {
		if(!tabDistrict.isClassActivePresent()) {
			logger.printMessage("Click in tab \"District\".");
			tabDistrict.click();
		}
		selectCheckboxOfFilterForm(district);
	}
	
	public void selectCheckboxOfFilterForm(String... chkValue) {
		logger.printMessage("in \"Filter Form\', select checkbox: " + chkValue);
		for (String item : chkValue) {
			chkSelectValue(item);
			chkSelectValue.check();
		}
	}
	
	public void selectCategory(String... category) {
		if(!tabCategory.isClassActivePresent()) {
			logger.printMessage("Click in tab \"Category\".");
			tabCategory.click();
		}
		selectCheckboxOfFilterForm(category);
	}
	
	public void selectCuisine(String... cuisine) {
		if(!tabCuisine.isClassActivePresent()) {
			logger.printMessage("Click in tab \"Cuisine\".");
			tabCuisine.click();
		}
		selectCheckboxOfFilterForm(cuisine);
	}
	
	public FoodySearchResultPage submitFilterForm(SearchValue searchValue) {
		logger.printMessage("Select some filter values in \"Filter Form\' and submit it.");
		selectDistrict(searchValue.getDistrict());
		selectCuisine(searchValue.getCuisine());
		selectCategory(searchValue.getCategory());
		btnSearch.click();
		return new FoodySearchResultPage();
	}

}
