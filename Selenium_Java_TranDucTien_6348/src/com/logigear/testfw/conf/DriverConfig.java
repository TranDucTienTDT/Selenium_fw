package com.logigear.testfw.conf;

import java.util.Map;


public class DriverConfig {
	
	private String name;
	private Map<String, String> properties=null;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	
	public Map<String, String> getProperties() {
		return properties;
	}
	
	public boolean hasProperty(String propertyName) {
		return properties.containsKey(propertyName);
	}
	
	public String getProperty(String propertyName) {
		if(properties.containsKey(propertyName))
			return properties.get(propertyName);
		return "";
	}
	

	public static final String KEY_EXEC_PATH = "capability.executable_path";
	public static final String KEY_REMOTE_URL = "capability.remoteserver";
	public static final String KEY_STARTUP_URL = "capability.startup_url";
	
}
