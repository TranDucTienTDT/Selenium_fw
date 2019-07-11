package com.logigear.testfw.driver;

import java.net.URL;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.logigear.testfw.conf.DriverConfig;

/**
 * 
 * @author ngocquang.tran
 *
 */
public class RemoteDriver extends BaseDriver {
	
	public RemoteDriver(DriverType type, DriverConfig driverConfig)
	{
		try {
			LOG.info(String.format("Create new Driver with type %s", type.getValue()));
			
			String remoteURL = driverConfig.getProperty(DriverConfig.KEY_REMOTE_URL);
			switch (type)
			{
				case NATIVE_MOBILE:
					this.createMobileDriver(remoteURL);
					break;
					
				case CHROME: // Chrome on mobile or remote desktop
					this.createRemoteChromeDriver_Windows(remoteURL);
					break;
					
				case FIREFOX: // Firefox on remote desktop
					this.createRemoteFirefoxDriver_Windows(remoteURL);
					break;
					
				default:
					break;
			}
		} catch (Exception e) {
			LOG.severe(e.getMessage());
		}
	}
	
	private void createRemoteChromeDriver_Windows(String remoteURL) throws Exception
	{
		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
		
		options.addArguments("disable-infobars"); 		// disable start-up information message
		options.addArguments("--no-sandbox");			// Bypass OS security model
		options.addArguments("start-maximized");
		options.addArguments("--disable-extensions");
		options.setCapability("chrome.verbose", false); //disable logging
		
		_driver = new RemoteWebDriver(new URL(remoteURL), options);
		
	}
	
	private void createRemoteFirefoxDriver_Windows(String remoteURL) throws Exception
	{
		FirefoxOptions options = new FirefoxOptions();
		options.setAcceptInsecureCerts(true);
		options.setProfile(new FirefoxProfile());
		
		_driver = new RemoteWebDriver(new URL(remoteURL), options);
		_driver.manage().window().maximize();
	}
	
	private void createMobileDriver(String remoteURL) throws Exception
	{
		// TODO
	}
}
