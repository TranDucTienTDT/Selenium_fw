package com.logigear.test.ta_dashboard.data_object;

public class DataProfile {

	protected String _name, _itemType, _relatedData;
	protected String[] _displayField, _sortField, _filterField, _statisticField;

	public String getName() {
		return _name;
	}
	public void setName(String _name) {
		this._name = _name;
	}
	public String getItemType() {
		return _itemType;
	}
	public void setItemType(String _itemType) {
		this._itemType = _itemType;
	}
	public String getRelatedData() {
		return _relatedData;
	}
	public void setRelatedData(String _relatedData) {
		this._relatedData = _relatedData;
	}
	public String[] getDisplayField() {
		return _displayField;
	}
	public void setDisplayField(String[] _displayField) {
		this._displayField = _displayField;
	}
	public String[] getSortField() {
		return _sortField;
	}
	public void setSortField(String[] _sortField) {
		this._sortField = _sortField;
	}
	public String[] getFilterField() {
		return _filterField;
	}
	public void setFilterField(String[] _filterField) {
		this._filterField = _filterField;
	}
	public String[] getStatisticField() {
		return _statisticField;
	}
	public void setStatisticField(String[] _statisticField) {
		this._statisticField = _statisticField;
	}

	public DataProfile(String name, String itemType, String relatedData) {
		this._name = name;
		this._itemType = itemType;
		this._relatedData = relatedData;
	}
	
	public DataProfile(String name, String itemType, String relatedData, String[] displayField, 
			String[] sortField, String[] filterField, String[] statisticField) {
		this._name = name;
		this._itemType = itemType;
		this._relatedData = relatedData;
		this._displayField = displayField;
		this._sortField = sortField;
		this._filterField = filterField;
		this._statisticField = statisticField;
	}

	public DataProfile(String name, String itemType, String[] displayField, String[] sortField,
			String[] filterField, String[] statisticField) {
		this._name = name;
		this._itemType = itemType;
		this._displayField = displayField;
		this._sortField = sortField;
		this._filterField = filterField;
		this._statisticField = statisticField;
	}

}
