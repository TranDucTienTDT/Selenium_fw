package com.logigear.testfw.driver;

import java.io.File;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.logigear.testfw.conf.DriverConfig;

/**
 * 
 * @author ngocquang.tran
 *
 */
public class LocalDriver extends BaseDriver {
	
	public LocalDriver(DriverType type, DriverConfig driverConfig)
	{
		try {
			LOG.info(String.format("Create new Driver with type %s", type.getValue()));
			
			String exeDriverPath = driverConfig.getProperty(DriverConfig.KEY_EXEC_PATH);
			switch (type) 
			{
				case CHROME:
					this.createChromeDriver_Windows(exeDriverPath);
					break;
	
				case FIREFOX:
					this.createFirefoxDriver_Windows(exeDriverPath);
					break;
					
				case EDGE:
					this.createEdgeDriver_Windows(exeDriverPath);
					break;
					
				default:
					break;
			}
			
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void createChromeDriver_Windows(String exeDriverPath) throws Exception
	{
		String driverFilePath = System.getProperty("user.dir") + File.separator + exeDriverPath;
		System.setProperty("webdriver.chrome.driver", driverFilePath);
		System.setProperty("webdriver.chrome.args", "--disable-logging");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        
        ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars"); 		// disable start-up information message
		options.addArguments("--no-sandbox");			// Bypass OS security model
		options.addArguments("start-maximized");
		options.addArguments("--disable-extensions");
		options.setCapability("chrome.verbose", false); //disable logging
		
		this._driver = new ChromeDriver(options);
		
	}
	
	private void createFirefoxDriver_Windows(String exeDriverPath) throws Exception
	{
		String driverFilePath = System.getProperty("user.dir") + File.separator + exeDriverPath;
		System.setProperty("webdriver.gecko.driver",driverFilePath);
		//System.setProperty("webdriver.firefox.marionette",driverFilePath);
		
		FirefoxOptions options = new FirefoxOptions();
		options.setAcceptInsecureCerts(true);
		options.setProfile(new FirefoxProfile());
		
		_driver = new FirefoxDriver(options);
		_driver.manage().window().maximize();
		
	}
	
	private void createEdgeDriver_Windows(String exeDriverPath) throws Exception
	{
		// TODO...
	}
}
