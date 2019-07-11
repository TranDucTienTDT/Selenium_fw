package com.logigear.testfw.conf;

import org.openqa.selenium.By;

public class Locator {
	private String type;
	private String value;
	private By by;
	
	public Locator(String type, String value) {
		this.type = type;
		this.value = value;
	}
	
	public String getType() {
		return type;
	}
	
	public String getValue() {
		return value;
	}
		
	public void setBy(By by) {
		this.by = by;
	}
	
//	public void initBy(Object... args) {
//		String tmp = String.format(value, args);
//		by = LocatorLoader.getByLocator(type, tmp);
//	}
	
	public By getBy() {
		return by;
	}
	
	public By getBy(Object... args) {
		String tmp = String.format(value, args);
		return LocatorLoader.getByLocator(type, tmp);
	}
	
	//@author hanh.nguyen
//	public By getByWithAltCode(String arg) {
//		String locator;
//		if (arg.contains(" "))
//        {
//            String newArg = arg.replace(" ", "\u00a0");
//            locator = String.format(value, newArg);
//        }
//        else
//        {
//            locator = String.format(value, arg);
//        }
//		return LocatorLoader.getByLocator(type, locator);
//	}
	
	//@author hanh.nguyen
	public By getByWithAltCode(Object... arg) {
		String locator = String.format(value, arg);
		for(Object x : arg) {
			if(x.toString().contains(" ")) {
				Object newArg = x.toString().replace(" ", "\u00a0");
				locator = String.format(value, newArg);
			}
		}
		return LocatorLoader.getByLocator(type, locator);	
	}
}
