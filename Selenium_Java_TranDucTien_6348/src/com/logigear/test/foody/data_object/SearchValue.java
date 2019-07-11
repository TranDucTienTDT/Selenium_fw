package com.logigear.test.foody.data_object;

public class SearchValue {
	
	protected String _location, _headCategory, _store, _type, _language;
	protected String[] _district, _cuisine, _category;

	public String getLocation() {
		return _location;
	}

	public void setLocation(String location) {
		this._location = location;
	}

	public String getHeadCategory() {
		return _headCategory;
	}

	public void setHeadCategory(String headCategory) {
		this._headCategory = headCategory;
	}

	public String getStore() {
		return _store;
	}

	public void setStore(String store) {
		this._store = store;
	}

	public String[] getDistrict() {
		return _district;
	}

	public void setDistrict(String[] district) {
		this._district = district;
	}

	public String[] getCuisine() {
		return _cuisine;
	}

	public void setCuisine(String[] cuisine) {
		this._cuisine = cuisine;
	}

	public String[] getCategory() {
		return _category;
	}

	public void setCategory(String[] category) {
		this._category = category;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		this._type = type;
	}

	public String getLanguage() {
		return _language;
	}

	public void setLanguage(String language) {
		this._language = language;
	}
	
	public SearchValue(String location, String headCategory, String store, String[] district, String[] cuisine,
						String[] category) {
		this._location = location;
		this._headCategory = headCategory;
		this._store = store;
		this._district = district;
		this._cuisine = cuisine;
		this._category = category;
	}
	
	public SearchValue(String location, String headCategory, String store) {
		this._location = location;
		this._headCategory = headCategory;
		this._store = store;
	}
}
