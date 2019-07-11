package com.logigear.testfw.common;

import java.io.File;
import java.util.List;

import org.testng.ITestContext;

import com.logigear.testfw.conf.DriverConfig;
import com.logigear.testfw.conf.DriverConfigLoader;
import com.logigear.testfw.conf.TestEnvironmentConfig;
import com.logigear.testfw.conf.TestPlatform;
import com.logigear.testfw.driver.BaseDriver;
import com.logigear.testfw.driver.DriverManager;

/**
 * 
 * @author ngocquang.tran
 *
 */
public class TestExecutor {
	
	private static TestExecutor _instance = null;
	    	  
    public static TestExecutor getInstance(){ 
        if (_instance == null) 
        	_instance = new TestExecutor(); 
  
        return _instance; 
    } 
	    
	private TestEnvironmentConfig currentEnvironment = null;
	private List<DriverConfig> driverConfigList = null;
	private DriverConfig driverConfig = null;
	
	private TestExecutor(){}
	
	public void initialize(ITestContext context) {
		// set execution environment from setting value in xml file.
		String env = context.getCurrentXmlTest().getParameter("environment");
		setCurrentConfig(env);
		
		// create driver object based on the environment configuration
		initDriver();
	}
	
	/**
	 * create Driver object for current configuration and store it in DriverManager's storage.
	 */
	private void initDriver() 
	{
		driverConfig = getDriverConfig();
		
		if(isMobileTesting()) {
			DriverManager.getInstance().createRemoteDriver(currentEnvironment, driverConfig);
		}
		else {
			String remoteURL = "";
			if(driverConfig != null)
				remoteURL = driverConfig.getProperty(DriverConfig.KEY_REMOTE_URL);
			
			if(remoteURL.isEmpty()) {
				DriverManager.getInstance().createLocalDriver(currentEnvironment, driverConfig);
			}
			else {
				DriverManager.getInstance().createRemoteDriver(currentEnvironment, driverConfig);
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isMobileTesting() {
		if(currentEnvironment != null) {
			if(currentEnvironment.getPlatform() == TestPlatform.ANDROID || 
				currentEnvironment.getPlatform() == TestPlatform.IOS)
				return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public TestEnvironmentConfig getEnvironmentConf() {
		return currentEnvironment;
	}
	

	/**
	 * 
	 * @param environmentConf
	 */
	public void setCurrentConfig(String environmentConf) 
	{
		this.currentEnvironment = new TestEnvironmentConfig(environmentConf);
		
		if(driverConfigList == null) {
			try {
				String driverFilePath = System.getProperty("user.dir") + File.separator + Common.CONFIG_FILE_DRIVER;
				driverConfigList = DriverConfigLoader.getAllDriverConfig(driverFilePath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
	/**
	 * 
	 * @return
	 */
	public DriverConfig getDriverConfig() {
		if(this.currentEnvironment != null)
			return getDriverConfig(this.currentEnvironment.getEnvConfigString());
		return null;
	}
	
	/**
	 * 
	 * @param environmentConf
	 * @return
	 */
	public DriverConfig getDriverConfig(String environmentConf) {
		if(driverConfigList != null) {
			for(DriverConfig config : driverConfigList) {
				if(config.getName().equalsIgnoreCase(environmentConf))
					return config;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public BaseDriver getCurrentDriver() {
		return DriverManager.getInstance().getCurrentDriver(this.currentEnvironment);
	}
}
