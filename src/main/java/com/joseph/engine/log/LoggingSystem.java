package com.joseph.engine.log;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Static class representing the logging system, managing the initialization of the logging system, and the 
 * creation of {@link SimplifiedLogger}s. Loggers can be created with a given string as the name, or via
 * an instance of {@link Class}.
 * @author Joseph
 *
 */
public final class LoggingSystem {
	private static Logger systemLogger;
	private static boolean init = false;
	private static Map<String, SimplifiedLogger> loggerCache;
	
	/**
	 * Initializes this logging system, and the log4j logging system. Sets any properties, and initializes the logging system logger.
	 */
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
	
	/**
	 * Returns a {@link SimplifiedLogger} with the given name. If this is the first time this name is being used,
	 * a new {@link Logger} and {@link SimplifiedLogger} are created, and cached into the system cache.
	 * @param name - the name of the logger
	 * @return a new {@link SimplifiedLogger} with the given name
	 */
	public static SimplifiedLogger getLogger(String name) {
		if (!init) {
			return null; // TODO throw exception? that would seem kinda bonk moment
		}
		
		// get the log4j logger
		Logger log = LogManager.getLogger(name);
		
		// get the logger wrapper
		return getLogger(log);
	}
	
	/**
	 * Returns a {@link SimplifiedLogger} with the given class as the name name. If this is the first time this 
	 * name is being used, a new {@link Logger} and {@link SimplifiedLogger} are created, and cached into the system cache.
	 * The name for a {@link Logger} created by this method is its canonical name.
	 * @param clazz - the class to use to name the logger
	 * @return a new {@link SimplifiedLogger} with the given name
	 * @see Class#getCanonicalName()
	 */
	public static SimplifiedLogger getLogger(Class<?> clazz) {
		if (!init) {
			return null;
		}

		// get the log4j logger
		Logger log = LogManager.getLogger(clazz);
		
		// get the logger wrapper
		return getLogger(log);
	}
	
	/**
	 * Takes the given log4j {@link Logger} and checks if a {@link SimplifiedLogger} is cached with the same name as the {@link Logger}.
	 * If one exists in the cache, it is returned. If one does not exist in the cache, a new {@link SimplifiedLogger} is created,
	 * put into the cache, and then returned.
	 * @param log
	 * @return
	 */
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
