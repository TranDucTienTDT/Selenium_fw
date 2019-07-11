package com.logigear.testfw.driver;

/**
 * 
 * @author ngocquang.tran
 *
 */
public enum DriverType {

	CHROME("chrome"), 
	FIREFOX("firefox"),
	EDGE("edge"),
	NATIVE_MOBILE("native");

	private final String value;

	DriverType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
