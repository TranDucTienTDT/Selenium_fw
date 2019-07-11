package com.logigear.test.ta_dashboard.pom;

import java.util.Arrays;

import com.logigear.test.ta_dashboard.data_object.ChartPanel;
import com.logigear.test.ta_dashboard.data_object.ChartPanel.Legends;
import com.logigear.test.ta_dashboard.data_object.ChartPanel.Style;
import com.logigear.test.ta_dashboard.data_object.GeneralPanel.PanelType;
import com.logigear.testfw.common.BasePOM;
import com.logigear.testfw.common.Common;
import com.logigear.testfw.element.Element;

public class PanelDialog extends BasePOM{
	//General in Panel dialog
	protected Element tabDisplaySettings;
	protected Element radChart;
	protected Element radIndicator;
	protected Element radReport;
	protected Element radHeatMap;
	protected Element txtDisplayName;
	protected Element cbbDataProfile;
	protected Element btnOK;
	protected Element btnCancel;
	protected Element fdPanelSettings;
	
	//In Chart Panel
	protected Element txtChartTitle;
	protected Element cbbChartType;
	protected Element radChartStyle2D;
	protected Element radChartStyle3D;
	protected Element chkShowTitle;
	protected Element cbbCategoryField;
	protected Element cbbSeriesField;
	protected Element txtCategoryCaption;
	protected Element txtSeriesCaption;
	protected Element radLegendsNone;
	protected Element radLegendsTop;
	protected Element radLegendsRight;
	protected Element radLegendsBottom;
	protected Element radLegendsLeft;
	protected Element chkDataLabelsSeries;
	protected Element chkDataLabelsCategories;
	protected Element chkDataLabelsValue;
	protected Element chkDataLabelsPercentage;
	
	//In Indicator panel
	protected Element txtIndicatorTitle;

	public PanelDialog() {
		super(PanelDialog.class);
	}
	
	@Override
	public void initPageElements() {
		//General in Panel dialog
		this.tabDisplaySettings = new Element(getLocator("tabDisplaySettings").getBy());
		this.radChart = new Element(getLocator("radChart").getBy());
		this.radIndicator = new Element(getLocator("radIndicator").getBy());
		this.radReport = new Element(getLocator("radReport").getBy());
		this.radHeatMap = new Element(getLocator("radHeatMap").getBy());
		this.txtDisplayName = new Element(getLocator("txtDisplayName").getBy());
		this.cbbDataProfile = new Element(getLocator("cbbDataProfile").getBy());
		this.btnOK = new Element(getLocator("btnOK").getBy());
		this.btnCancel = new Element(getLocator("btnCancel").getBy());
		
		//In Chart Panel
		this.txtChartTitle = new Element(getLocator("txtChartTitle").getBy());
		this.cbbChartType = new Element(getLocator("cbbChartType").getBy());
		this.radChartStyle2D = new Element(getLocator("radChartStyle2D").getBy());
		this.radChartStyle3D = new Element(getLocator("radChartStyle3D").getBy());
		this.chkShowTitle = new Element(getLocator("chkShowTitle").getBy());
		this.cbbCategoryField = new Element(getLocator("cbbCategoryField").getBy());
		this.cbbSeriesField = new Element(getLocator("cbbSeriesField").getBy());
		this.txtCategoryCaption = new Element(getLocator("txtCategoryCaption").getBy());
		this.txtSeriesCaption = new Element(getLocator("txtSeriesCaption").getBy());
		this.radLegendsNone = new Element(getLocator("radLegendsNone").getBy());
		this.radLegendsTop = new Element(getLocator("radLegendsTop").getBy());
		this.radLegendsRight = new Element(getLocator("radLegendsRight").getBy());
		this.radLegendsBottom = new Element(getLocator("radLegendsBottom").getBy());
		this.radLegendsLeft = new Element(getLocator("radLegendsLeft").getBy());
		this.chkDataLabelsSeries = new Element(getLocator("chkDataLabelsSeries").getBy());
		this.chkDataLabelsCategories = new Element(getLocator("chkDataLabelsCategories").getBy());
		this.chkDataLabelsValue = new Element(getLocator("chkDataLabelsValue").getBy());
		this.chkDataLabelsPercentage = new Element(getLocator("chkDataLabelsPercentage").getBy());
		
		//In Indicator panel
		this.txtIndicatorTitle = new Element(getLocator("txtIndicatorTitle").getBy());
	}
	
	public void fdPanelSettings(PanelSettingType panelSettingType) {
		this.fdPanelSettings = new Element(getLocator("fdPanelSettings").getBy(panelSettingType.getValue()));
	}

	/*
	 * Author: Tien Tran
	 * Method name: clickRadioButton()
	 * Purpose/Description: click all radio button on the page
	/**
	 * @author tien.duc.tran
	 * 
	 * 
	 * @description 
	 * 
	 * clickIndicatorRadioButton()
	 * clickReportRadioButton()
	 * clickHeatMapRadioButton()
	 */

	public PanelDialog clickIndicatorRadioButton() {
		radIndicator.click();
		return this;
	}	
	
	public PanelDialog clickReportRadioButton() {
		radReport.click();
		return this;
	}

	public PanelDialog clickHeatMapRadioButton() {
		radHeatMap.click();
		return this;
	}
	
	//@author hanh.nguyen
	public void selectPanelType(PanelType panelType) {
		if(panelType == PanelType.CHART) {
			logger.printMessage("Select \"Chart\" radio button");
			radChart.check();
		}
		if(panelType == PanelType.INDICATOR) {
			logger.printMessage("Select \"Indicator\" radio button");
			radIndicator.check();
		}
		if(panelType == PanelType.REPORT) {
			logger.printMessage("Select \"Report\" radio button");
			radReport.check();
		}
		if(panelType == PanelType.HEAT_MAP) {
			logger.printMessage("Select \"Heat Map\" radio button");
			radHeatMap.check();
		}
	}
	
	//@author hanh.nguyen
	public void fillInforInGeneralPanelDialog(String dataProfile, String displayName) {
		if(dataProfile != null && dataProfile != txtDisplayName.getText()) {
			logger.printMessage("In \"Display Name\" combobox, select: " + dataProfile);
			cbbDataProfile.selectByText(dataProfile);
		}
		if(displayName != null && (txtDisplayName.getText() == null || txtDisplayName.getText() != displayName)) {
			logger.printMessage("In \"Display Name\" textbox, enter: " + displayName);
			txtDisplayName.enter(displayName);
		}
	}
	
	//@author hanh.nguyen
	public void fillInforInChartPanelDialog(ChartPanel chartPanel) {
		selectPanelType(PanelType.CHART);
		fillInforInGeneralPanelDialog(chartPanel.getDataProfile(), chartPanel.getDisplayName());
		if(chartPanel.getChartTitle() != null && (txtChartTitle.getText() == null || txtChartTitle.getText() != chartPanel.getChartTitle()))
		{
			logger.printMessage("In \"Chart Title\" textbox, enter: " + chartPanel.getChartTitle());
			txtChartTitle.enter(chartPanel.getChartTitle());
		}
		if(chartPanel.getIsShowTitle() == true) {
			logger.printMessage("Check in checkbox \"Show Title\".");
			chkShowTitle.check();
		}
		else if (chartPanel.getIsShowTitle() == false) {
			logger.printMessage("Uncheck in checkbox \"Show Title\".");
			chkShowTitle.uncheck();
		}
		selectChartType(chartPanel.getChartType());
		if(chartPanel.getStyle() == Style.STYLE2D) {
			logger.printMessage("Check in radio button \"2D\".");
			radChartStyle2D.check();
		}
		else if (chartPanel.getStyle() == Style.STYLE3D) {
			logger.printMessage("Check in radio button \"3D\".");
			radChartStyle3D.check();
		}
		if(chartPanel.getSeries() != null && chartPanel.getSeries() != cbbSeriesField.getText()) {
			logger.printMessage("In \"Series\" combobox, select: " + chartPanel.getSeries());
			cbbSeriesField.selectByValue(chartPanel.getSeries().toLowerCase());
		}
		selectLegendsField(chartPanel.getLegends());
	}
	
	public enum PanelSettingType {
		CHART("Chart Settings"),
		INDICATOR("Indicator Settings"),
		REPORT(""),
		HEAT_MAP("Heat Map Settings");
		
		private String _panelSettingType;
		
		public String getValue() {
			return _panelSettingType;
		}

		public void setValue(String panelSettingType) {
			this._panelSettingType = panelSettingType;
		}

		private PanelSettingType(String panelSettingType) {
			this._panelSettingType = panelSettingType;
		}
	}
	
	public boolean isPanelSettingFormDisplayed(PanelSettingType panelSettingType) {
		fdPanelSettings(panelSettingType);
		boolean isDisplayed = false;
		if(panelSettingType == PanelSettingType.REPORT) {
			if(!fdPanelSettings.isExisted()) {
				isDisplayed = true;
			}
		}
		isDisplayed = fdPanelSettings.isDisplayed();
		logger.printMessage("Is Panel Setting From displayed: " + isDisplayed);
		return isDisplayed;
	}
	
	//@author hanh.nguyen
	public enum ChartType {
		PIE("Pie"),
		SINGLE_BAR("Single Bar"),
		STACKED_BAR("Stacked Bar"),
		GROUP_BAR("Group Bar"),
		LINE("Line");
		
		private String _chartType;
		
		public String getValue() {
			return _chartType;
		}

		public void setValue(String chartType) {
			this._chartType = chartType;
		}

		private ChartType(String chartType) {
			this._chartType = chartType;
		}
	}
	
	//@author hanh.nguyen
	public PanelDialog selectChartType(String chartType) {
		if(chartType != cbbChartType.getText() && chartType != null) {
			logger.printMessage("In \"Chart Type\" combobox, select: " + chartType);
			cbbChartType.selectByText(chartType);
		}
		return this;
	}
	
	//@author hanh.nguyen
	public boolean isChartTypeComboboxDisplayCorrectly(String expectedValue) {
		boolean isCorrect = false;
		String actualValue = cbbChartType.getSelectedOption();
		if(expectedValue.equalsIgnoreCase(actualValue))
			isCorrect = true;
		logger.printMessage("\"Chart Type\" combobox displayed correctly: " + isCorrect);
		return isCorrect;
	}
	
	//@author hanh.nguyen
	public GeneralPage closePanelDialog() {
		logger.printMessage("Close Panel dialog.");
		btnCancel.click();
		btnCancel.waitForDisappear(Common.ELEMENT_TIMEOUT);
		return new GeneralPage(GeneralPage.class);
	}
	
	public enum ChartPanelFields {
		DATA_PROFILE, DISPLAY_NAME, CHART_TITLE, CHART_TYPE, SHOW_TITLE, STYLE, CATEGORY, CATEGORY_CAPTION, SERIES, 
		SERIES_CAPTION, LEGENDS, DATA_lABELS;
	}
	
	public String getAllSettingsInChartPanelDialog(String[] ignoredField) {
		String dataProfile = "", displayName = "", chartTitle = "", chartType = "", category = "", categoryCaption = "", 
				series = "", seriesCaption = "", legends = "", dataLabels = "";
		if(!Arrays.stream(ignoredField).anyMatch(ChartPanelFields.DATA_PROFILE.toString()::equals)) {
			dataProfile = cbbDataProfile.getSelectedOption();
		}
		if(!Arrays.stream(ignoredField).anyMatch(ChartPanelFields.DISPLAY_NAME.toString()::equals)) {
			displayName = txtDisplayName.getText();
		}
		if(!Arrays.stream(ignoredField).anyMatch(ChartPanelFields.CHART_TITLE.toString()::equals)) {
			chartTitle = txtChartTitle.getText();
		}
		if(!Arrays.stream(ignoredField).anyMatch(ChartPanelFields.CHART_TYPE.toString()::equals)) {
			chartType = cbbChartType.getSelectedOption();
		}
		if(!Arrays.stream(ignoredField).anyMatch(ChartPanelFields.CATEGORY.toString()::equals)) {
			category = cbbCategoryField.getSelectedOption();
		}
		if(!Arrays.stream(ignoredField).anyMatch(ChartPanelFields.CATEGORY_CAPTION.toString()::equals)) {
			categoryCaption = txtCategoryCaption.getText();
		}
		if(!Arrays.stream(ignoredField).anyMatch(ChartPanelFields.SERIES.toString()::equals)) {
			series = cbbSeriesField.getSelectedOption();
		}
		if(!Arrays.stream(ignoredField).anyMatch(ChartPanelFields.SERIES_CAPTION.toString()::equals)) {
			seriesCaption = txtSeriesCaption.getText();
		}
		if(!Arrays.stream(ignoredField).anyMatch(ChartPanelFields.LEGENDS.toString()::equals)) {
			legends = getLegendsFieldsValue();
		}
		if(!Arrays.stream(ignoredField).anyMatch(ChartPanelFields.DATA_lABELS.toString()::equals)) {
			dataLabels = getDataLabelsValue();
		}
		String value = dataProfile + displayName + chartTitle + chartType + category + categoryCaption + series + seriesCaption
				       + legends + dataLabels;
		return value;
	}
	
	public String getLegendsFieldsValue() {
		String legendValue = "";
		if(radLegendsNone.isAttributePresent("checked")) {
			legendValue = "None";
		}
		else if (radLegendsTop.isAttributePresent("checked")) {
			legendValue = "Top";
		}
		else if (radLegendsRight.isAttributePresent("checked")) {
			legendValue = "Right";
		}
		else if (radLegendsBottom.isAttributePresent("checked")) {
			legendValue = "Bottom";
		}
		else if (radLegendsLeft.isAttributePresent("checked")) {
			legendValue = "Left";
		}
		return legendValue;
	}
	
	public String getDataLabelsValue() {
		String dataLabelsValue = "";
		if(chkDataLabelsSeries.isSelected())
			dataLabelsValue = dataLabelsValue + "Seiries";
		if(chkDataLabelsCategories.isSelected())
			dataLabelsValue = dataLabelsValue + "Categories";
		if(chkDataLabelsValue.isSelected())
			dataLabelsValue = dataLabelsValue + "Value";	
		if(chkDataLabelsPercentage.isSelected())
			dataLabelsValue = dataLabelsValue + "Percentage";
		return dataLabelsValue;
	}
	
	public PanelDialog selectLegendsField(Legends legend) {
		if(legend == Legends.NONE) {
			logger.printMessage("Select \"None\" radio button");
			radLegendsNone.check();
		}
		if(legend == Legends.TOP) {
			logger.printMessage("Select \"Top\" radio button");
			radLegendsTop.check();
		}
		if(legend == Legends.RIGHT) {
			logger.printMessage("Select \"Right\" radio button");
			radLegendsRight.check();
		}
		if(legend == Legends.BOTTOM) {
			logger.printMessage("Select \"Bottom\" radio button");
			radLegendsBottom.check();
		}
		if(legend == Legends.LEFT) {
			logger.printMessage("Select \"Left\" radio button");
			radLegendsLeft.check();
		}
		return this;
	}
	
	public PanelPage addChartPanel(ChartPanel chartPanel) {
		logger.printMessage("Add a Chart Panel: " + chartPanel.getDisplayName());
		fillInforInChartPanelDialog(chartPanel);
		btnOK.click();
		btnOK.waitForDisappear(Common.ELEMENT_TIMEOUT);
		return new PanelPage();
	}
}