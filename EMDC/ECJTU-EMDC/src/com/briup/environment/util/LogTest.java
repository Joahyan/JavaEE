package com.briup.environment.util;

import org.apache.log4j.*;

public class LogTest {
	public static void main(String[] args) {
		Logger logger =Logger.getRootLogger();
		PropertyConfigurator.configure("src/log4j.properties");
		logger.debug("this is debug");
		logger.info("this is info");
	}
	
	
}
