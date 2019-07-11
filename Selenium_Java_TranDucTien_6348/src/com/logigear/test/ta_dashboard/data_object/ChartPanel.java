package com.logigear.test.ta_dashboard.data_object;

public class ChartPanel extends GeneralPanel{
	private String _chartTitle, _chartType, _category, _categoryCaption, _series, _seriesCaption;
	private Style _style;
	private Legends _legends;
	private boolean isShowTitle;
	private String[] _dataLabels;
	
	public String getChartTitle() {
		return _chartTitle;
	}
	public void setChartTitle(String chartTitle) {
		this._chartTitle = chartTitle;
	}
	public String getChartType() {
		return _chartType;
	}
	public void setChartType(String _chartType) {
		this._chartType = _chartType;
	}
	public String getCategory() {
		return _category;
	}
	public void setCategory(String _category) {
		this._category = _category;
	}
	public String getCategoryCaption() {
		return _categoryCaption;
	}
	public void setCategoryCaption(String __categoryCaption) {
		this._categoryCaption = __categoryCaption;
	}
	public String getSeries() {
		return _series;
	}
	public void setSeries(String series) {
		this._series = series;
	}
	public String getSeriesCaption() {
		return _seriesCaption;
	}
	public void setSeriesCaption(String _seriesCaption) {
		this._seriesCaption = _seriesCaption;
	}
	public Legends getLegends() {
		return _legends;
	}
	public void setLegends(Legends _legends) {
		this._legends = _legends;
	}
	public Style getStyle() {
		return _style;
	}
	public void setStyle(Style _style) {
		this._style = _style;
	}
	public boolean getIsShowTitle() {
		return isShowTitle;
	}
	public void setShowTitle(boolean isShowTitle) {
		this.isShowTitle = isShowTitle;
	}
	public String[] getDataLabels() {
		return _dataLabels;
	}
	public void setDataLabels(String[] _dataLabels) {
		this._dataLabels = _dataLabels;
	}
	
	public ChartPanel(String displayName, String series) {
		this._panelType = PanelType.CHART;
		this._displayName = displayName;
		this._series = series;
	}
	
	public enum Style {
		STYLE2D("2D"),
		STYLE3D("3D");
		
		public String getValue() {
			return _style;
		}

		public void setValue(String style) {
			this._style = style;
		}

		private String _style;
		
		private Style(String style) {
			this._style = style;
		}
	}
	
	public enum Legends {
		NONE,
		TOP,
		RIGHT,
		BOTTOM,
		LEFT;
		
//		private String _legends;
//		
//		private Legends(String legends) {
//			this._legends = legends;
//		}
//		
//		public String getValue() {
//			return _legends;
//		}
//
//		public void setValue(String legends) {
//			this._legends = legends;
//		}
	}
	
	public enum DataLabels {
		SERIES, CATEGORIES, VALUE, PERCENTAGE; 
	}
}