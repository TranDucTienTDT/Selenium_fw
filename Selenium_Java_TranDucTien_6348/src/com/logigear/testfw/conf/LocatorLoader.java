package com.logigear.testfw.conf;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;

public class LocatorLoader {
	private static final String ALL_TEST_TARGET = "default";
	
	
	
	/**
	 * Find the locator of specified class from files.
	 * @param platform
	 * @param className
	 * @return
	 */
	public static Map<String, Locator> getLocatorsByClassName(Class<?> curClass, String... environments)
	{	
		String platform = "windows";
		String testTarget = "";
		
		if(environments.length>0)
			platform = environments[0];
		
		if(environments.length>1)
			testTarget = environments[1];
		
		Map<String, Locator> locators = new ConcurrentHashMap<String, Locator>();
		
		// read parent class' locators first
		Class<?> parentClass = curClass.getSuperclass();
		if(parentClass != null && !parentClass.getSimpleName().equals("Object"))
			locators.putAll(getLocatorsByClassName(parentClass, environments));
		
		// read default locators
		locators.putAll(getLocatorsByClassName(platform,"", curClass.getSimpleName()) );
		// read specified locators
		if(testTarget != null && !testTarget.isEmpty() && !testTarget.equalsIgnoreCase(ALL_TEST_TARGET))
			locators.putAll(getLocatorsByClassName(platform, testTarget, curClass.getSimpleName()) );
		
		return locators;
	}
	/**
	 * Find the locator of specified class by its classname. <br>
	 * For example, for Login's locators on 'windows' platform, this methods find the <code>Login.json</code> file 
	 * in this location: {project_root}/resources/locators/windows/Login.json
	 * @param platform
	 * @param className
	 * @return
	 */
	public static Map<String, Locator> getLocatorsByClassName(String platform, String testTarget, String className)
	{
		Map<String, Locator> locators = new ConcurrentHashMap<String, Locator>();
		
		if(testTarget == null || testTarget.isEmpty())
			testTarget = ALL_TEST_TARGET;
		
		String filePath = new StringBuilder()
				.append(System.getProperty("user.dir")).append(File.separator)
				.append("resources").append(File.separator)
				.append("locators").append(File.separator)
				.append(platform).append(File.separator)
				.append(className + ".json")
				.toString();
		try {
			if(new File(filePath).exists() == false)
				return locators;
			String text = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
			
			JSONObject jObject = new JSONObject(text);
			JSONObject jsonDefaultTarget = jObject.getJSONObject(testTarget);
			
			if( jsonDefaultTarget != null) {
				JSONArray arrProperties = jsonDefaultTarget.names();
				if(arrProperties == null)
					return locators;
				for(int i = 0; i < arrProperties.length(); i++){
					String propertyName = arrProperties.getString(i);
					JSONObject jsonLocator = jsonDefaultTarget.getJSONObject(propertyName);
					
					Locator locator = new Locator(jsonLocator.getString("type"), jsonLocator.getString("value"));
					locator.setBy(getByLocator(locator.getType(), locator.getValue()));
					
					locators.put(propertyName, locator);
				}
			}
			
		}
		catch(Exception e) {
			// file not exit or invalid format
			LogWrapper.createLogger(LocatorLoader.class.getName()).severe(e.getMessage());
		}		
		
		return locators;
	}

	/**
	 * Generate <code>By</code> object from locator string.
	 * @param type
	 * @param value
	 * @return
	 */
	public static By getByLocator(String type, String value) {
		switch (type) {
			case "css":
				return By.cssSelector(value);
			case "id":
				return By.id(value);
			case "link":
				return By.linkText(value);
			case "tagName":
				return By.tagName(value);
			case "name":
				return By.name(value);
			default:
				return By.xpath(value);
		}
	}
}
