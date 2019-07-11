package com.logigear.testfw.driver;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.logigear.testfw.conf.DriverConfig;
import com.logigear.testfw.conf.TestEnvironmentConfig;

/**
 * 
 * @author ngocquang.tran
 *
 */
public class DriverManager {

	//private static final Logger LOG = LogWrapper.createLogger(DriverManager.class.getName());
	private static DriverManager _instance = null;
	  
    public static DriverManager getInstance(){ 
        if (_instance == null) 
        	_instance = new DriverManager(); 
  
        return _instance; 
    } 
    
    private Map<String, BaseDriver> map = new ConcurrentHashMap<String, BaseDriver>();
		
	/**
	 * 
	 * @param currentEnvironment
	 */
	public void createLocalDriver(TestEnvironmentConfig currentEnvironment, DriverConfig driverConfig) 
	{
		String envName = currentEnvironment.getEnvConfigString();
		DriverType type = currentEnvironment.getRunTarget().toDriverType();
		
		LocalDriver driver = new LocalDriver(type, driverConfig);
		map.put(envName, driver);
	}
	
	/**
	 * 
	 * @param currentEnvironment
	 * @param driverConfig
	 */
	public void createRemoteDriver(TestEnvironmentConfig currentEnvironment, DriverConfig driverConfig) {
		String envName = currentEnvironment.getEnvConfigString();
		DriverType type = currentEnvironment.getRunTarget().toDriverType();
		
		RemoteDriver driver = new RemoteDriver(type, driverConfig);
		map.put(envName, driver);
	}
	
	/**
	 * 
	 * @param currentEnvironment
	 * @return
	 */
	public BaseDriver getCurrentDriver(TestEnvironmentConfig currentEnvironment) {
		String envName = currentEnvironment.getEnvConfigString();
		return map.get(envName);
	}
	
	public Collection<BaseDriver> getAllDrivers(){
		return map.values();
	}
}
