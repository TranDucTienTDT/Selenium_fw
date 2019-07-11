package com.logigear.test.foody.data_object;

public class Store {
	
	protected String _name, _address;
	
	public String getName() {
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}

	public String getAddress() {
		return _address;
	}

	public void setAddress(String address) {
		this._address = address;
	}
	
	public Store(String name, String address) {
		this._name = name;
		this._address = address;
	}
	

}
