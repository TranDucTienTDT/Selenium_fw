package com.logigear.testfw.conf;

import java.io.File;
import java.util.logging.Logger;

import com.logigear.testfw.common.Common;

/**
 * 
 * @author ngocquang.tran
 *
 */
public class LogWrapper {
	
	static {
		String path = System.getProperty("user.dir") + File.separator + Common.CONFIG_FILE_LOG;
		System.setProperty("java.util.logging.config.file", path);
	}

	/**
	 * 
	 * Creates the logger.
	 *
	 * @param className the class name
	 * @return the logger
	 */
	public static final Logger createLogger(String className) {
		return Logger.getLogger(className);
	}
	
	//@author hanh.nguyen
	public void printMessage(String message) {
		System.out.println(message);
	}
}	
