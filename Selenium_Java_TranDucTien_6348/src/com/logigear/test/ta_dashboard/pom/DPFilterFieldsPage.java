package com.logigear.test.ta_dashboard.pom;

import java.util.ArrayList;
import java.util.Arrays;

import com.logigear.test.ta_dashboard.data_object.DataProfile;
import com.logigear.testfw.common.Common;
import com.logigear.testfw.element.Element;

public class DPFilterFieldsPage extends GeneralPage{
	
	protected Element cbbAndOrCondition;
	protected Element cbbField;
	protected Element cbbOperator;
	protected Element txtValue;
	protected Element btnAdd;
	protected Element btnRemove;
	protected Element tableListCondition;
	protected Element btnNext;
	
	public DPFilterFieldsPage() {
		super(DPFilterFieldsPage.class);
	}

	@Override
	public void initPageElements() {
		super.initPageElements();	
		this.cbbAndOrCondition = new Element(getLocator("cbbAndOrCondition").getBy());
		this.cbbField = new Element(getLocator("cbbField").getBy());
		this.cbbOperator = new Element(getLocator("cbbOperator").getBy());
		this.txtValue = new Element(getLocator("txtValue").getBy());
		this.btnAdd = new Element(getLocator("btnAdd").getBy());
		this.btnRemove = new Element(getLocator("btnRemove").getBy());
		this.tableListCondition = new Element(getLocator("tableListCondition").getBy());
		this.btnNext = new Element(getLocator("btnNext").getBy());
	}
	
	//@author hanh.nguyen
	public void addDataProfilesFilterFields(String andOrCondition, String field, String operator, String value) {
		if(andOrCondition != null && cbbAndOrCondition.getText() != andOrCondition) {
			logger.printMessage("In \"And/Or\" combobox, select: " + andOrCondition);
			cbbAndOrCondition.selectByText(andOrCondition);
		}
		if(field != null && cbbField.getText() != field) {
			logger.printMessage("In \"Field\" combobox, select: " + field);
			cbbField.selectByText(field);
		}
		if(operator != null && cbbOperator.getText() != operator) {
			logger.printMessage("In \"Operator\" combobox, select: " + operator);
			cbbOperator.selectByText(operator);
		}
		if((value != null) && (txtValue.getText() != null || txtValue.getText() != value)) {
			logger.printMessage("In \"Value\" textbox, enter: " + value);
			txtValue.enter(value);
		}
		btnAdd.click();
	}
	
	//@author hanh.nguyen
	public int findStartNumberInFilterValue(String filterValue) {
		int start;
		if(filterValue.startsWith("and")) 
			start = 4;
		else if(filterValue.startsWith("or")) 
			start = 3;
		else 
			start = 0;
		return start;
	}
	
	//@author hanh.nguyen
	public ArrayList<String> splitFilterValue(String filterValue){
		ArrayList<String> splitValue;
		int start = findStartNumberInFilterValue(filterValue);	;
		if(filterValue.startsWith("and") || filterValue.startsWith("or")) {
			splitValue = new ArrayList<String>(4);
			if(filterValue.startsWith("and")) 
				splitValue.add("and");
			else if(filterValue.startsWith("or")) 
				splitValue.add("or");
		}
		else 
			splitValue = new ArrayList<String>(3);
		String[] operator = {"=", "Like", ">", ">=", "<", "<=", "<>"};
		for (String x : operator) {
			if(filterValue.contains(x)) {
				int end = filterValue.indexOf(x);
				splitValue.add(filterValue.substring(start, end));
				splitValue.add(x);
				splitValue.add(filterValue.substring(end + x.length() + 1, filterValue.length() - 1));
			}
		}
		return splitValue;
	}
	
	//@author hanh.nguyen
	public void addDataProfilesFilterFields(String... filterValue) {
		logger.printMessage("In Data Profiles \"Filter Fields\" page, add filter value: " + filterValue);
		for (String value : filterValue) {
			ArrayList<String> splitValue = splitFilterValue(value);
			if(splitValue.size() == 3)
				addDataProfilesFilterFields(null, splitValue.get(0), splitValue.get(1), splitValue.get(2));
			else
				addDataProfilesFilterFields(splitValue.get(0), splitValue.get(1), splitValue.get(2), splitValue.get(3));
		}
	}
	
	//@author hanh.nguyen
	public boolean isFilterFieldsTableDisplayCorrect(String... filterField) {
		ArrayList<String> actualValue = (ArrayList<String>) tableListCondition.getOptions();
		boolean isCorrect = false;
		try {
			if(actualValue.size() == 0)
				logger.printMessage("Data Profiles Filter Fields table doesn't has any filter.");
			else {
				ArrayList<String> expectedValue = new ArrayList<String>(Arrays.asList(filterField));
				if(actualValue.equals(expectedValue))
					isCorrect = true;
			}
		} catch (Exception error) {
			LOG.severe("Has error when checking Filter Fields table.");
		}
		logger.printMessage("Is Filter Fields table displayed correctly: " + isCorrect);
		return isCorrect;
	}
	
	//@author hanh.nguyen
	public boolean isFilterFieldsTableDisplayCorrect(DataProfile dataProfile) {
		boolean isCorrect = isFilterFieldsTableDisplayCorrect(dataProfile.getFilterField());
		return isCorrect;
	}
	
	//@author hanh.nguyen
	public DPStatisticFieldsPage submitDataProfilesFilterFieldsPage(String... filterValue) {
		logger.printMessage("Submit \"Filter Fields\" page.");
		addDataProfilesFilterFields(filterValue);
		gotoNextPage();
		return new DPStatisticFieldsPage();
	}
	
	//@author hanh.nguyen
	public DPStatisticFieldsPage submitDataProfilesFilterFieldsPage(DataProfile dataProfile) {
		submitDataProfilesFilterFieldsPage(dataProfile.getFilterField());
		return new DPStatisticFieldsPage();
	}
	
	//@author hanh.nguyen	
	public DPStatisticFieldsPage gotoNextPage() {
		logger.printMessage("From \"Filter Fields\" page, click \"Next\" to go to \"Statistic Fields\" page.");
		btnNext.click();
		btnAdd.waitForDisappear(Common.ELEMENT_TIMEOUT);
		return new DPStatisticFieldsPage();
	}

}
