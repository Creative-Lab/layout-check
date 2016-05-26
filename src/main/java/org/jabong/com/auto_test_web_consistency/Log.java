package org.jabong.com.auto_test_web_consistency;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log {

	final static Logger logger = Logger.getLogger(Log.class);
	
	public static void setLogLevel(String level){
		if(level == null || level.equalsIgnoreCase("info"))
			LogManager.getRootLogger().setLevel(Level.INFO);
		else if(level.equalsIgnoreCase("warn"))
			LogManager.getRootLogger().setLevel(Level.WARN);
		else if(level.equalsIgnoreCase("error"))
			LogManager.getRootLogger().setLevel(Level.ERROR);
		else if(level.equalsIgnoreCase("off"))
			LogManager.getRootLogger().setLevel(Level.OFF);
		else if(level.equalsIgnoreCase("all"))
			LogManager.getRootLogger().setLevel(Level.ALL);
		else if(level.equalsIgnoreCase("debug"))
			LogManager.getRootLogger().setLevel(Level.DEBUG);
	}
	
	public static void INFO(String infoMessage){
		logger.info(infoMessage);
	}
	
	public static void DEBUG(String debugMessage){
		logger.debug(debugMessage);
	}
	
	public static void ERROR(String errMessage){
		logger.error(errMessage);
	}
	
	public static void WARN(String warnMessage){
		logger.warn(warnMessage);
	}
	
	public static void FATAL(String fatalMessage){
		logger.fatal(fatalMessage);
	}
}
