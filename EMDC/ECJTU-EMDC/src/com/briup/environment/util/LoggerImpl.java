package com.briup.environment.util;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerImpl implements Log{
	
	private static Logger logger;
	static{
	logger =Logger.getRootLogger();
	PropertyConfigurator.configure("src/log4j.properties");
	
	}
	
	@Override
	public void debug(String msg) {
		logger.debug(msg);
		
	}

	@Override
	public void info(String msg) {
		logger.info(msg);
		
	}

	@Override
	public void warm(String msg) {
		logger.warn(msg);
		
	}

	@Override
	public void error(String msg) {
		logger.error(msg);
		
	}

	@Override
	public void fatal(String msg) {
		logger.fatal(msg);
		
	}

	@Override
	public void init(Properties properties) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
