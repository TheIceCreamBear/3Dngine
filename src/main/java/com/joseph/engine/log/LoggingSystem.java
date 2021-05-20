package com.joseph.engine.log;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingSystem {
	private static Logger systemLogger;
	private static boolean init = false;
	private static Map<String, SimplifiedLogger> loggerCache;
	
	public static void initialize() {
		// dont init more than once
		if (init) {
			return;
		}
		init = true;
		
		// create the logger cache
		loggerCache = new HashMap<String, SimplifiedLogger>();
		
		// create system logger, which also initializes Log4J
		systemLogger = LogManager.getLogger();
	}
	
	public static SimplifiedLogger getLogger(String name) {
		if (!init) {
			return null; // TODO throw exception? that would seem kinda bonk moment
		}
		
		// get the log4j logger
		Logger log = LogManager.getLogger(name);
		
		// get the logger wrapper
		return getLogger(log);
	}
	
	public static SimplifiedLogger getLogger(Class<?> clazz) {
		if (!init) {
			return null;
		}

		// get the log4j logger
		Logger log = LogManager.getLogger(clazz);
		
		// get the logger wrapper
		return getLogger(log);
	}
	
	private static SimplifiedLogger getLogger(Logger log) {
		// get the name only once
		String name = log.getName();
		// check the cache, if not in cache create a new one and put it in the cache
		SimplifiedLogger logger = loggerCache.get(name);
		if (logger == null) {
			logger = new SimplifiedLogger(log);
			loggerCache.put(name, logger);
		}
		return logger;
	}
	
	/**
	 * Method to determine if the logging system has been initialized yet
	 * @return true if {@link LoggingSystem#initialize()} was called
	 */
	public static boolean initialized() {
		return init;
	}
}
