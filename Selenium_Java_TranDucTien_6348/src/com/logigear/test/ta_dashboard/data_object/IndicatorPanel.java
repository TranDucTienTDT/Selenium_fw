package com.logigear.test.ta_dashboard.data_object;

public class IndicatorPanel extends GeneralPanel{
	
	private String _title, _statisticField, _value;
	private boolean isShowTitle, _isPercentage;
	private int[] _from;
	private String[] _color;
	
	public String getTitle() {
		return _title;
	}
	public void setTitle(String _title) {
		this._title = _title;
	}
	public String getStatisticField() {
		return _statisticField;
	}
	public void setStatisticField(String _statisticField) {
		this._statisticField = _statisticField;
	}
	public String getValue() {
		return _value;
	}
	public void setValue(String _value) {
		this._value = _value;
	}
	public boolean getIsShowTitle() {
		return isShowTitle;
	}
	public void setIsShowTitle(boolean isShowTitle) {
		this.isShowTitle = isShowTitle;
	}
	public boolean getIsPercentage() {
		return _isPercentage;
	}
	public void setIsPercentage(boolean _percentage) {
		this._isPercentage = _percentage;
	}
	public int[] getFrom() {
		return _from;
	}
	public void setFrom(int[] _from) {
		this._from = _from;
	}
	public String[] getColor() {
		return _color;
	}
	public void setColor(String[] _color) {
		this._color = _color;
	}
	
	

}
