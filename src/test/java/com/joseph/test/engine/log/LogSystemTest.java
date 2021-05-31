package com.joseph.test.engine.log;

import com.joseph.engine.log.LoggingSystem;
import com.joseph.engine.log.SimplifiedLogger;

/**
 * Simple test to ensure that the logging system is working as expected
 * @author Joseph
 *
 */
public class LogSystemTest {
	public static void main(String[] args) {
		LoggingSystem.initialize();
		SimplifiedLogger clazz = LoggingSystem.getLogger(LogSystemTest.class);
		SimplifiedLogger name = LoggingSystem.getLogger("TestLogger");
		
		clazz.trace("Begin");
		
		clazz.debug("why hello there");
		clazz.info("be informed of this info log");
		name.warn("im going to warn you to not do that again");
		name.error("yikes, this should not have happened");
		clazz.getLogger().fatal("The following error caused a fatal problem: ", new Exception("Nothing wrong happened"));
		
		name.trace("end");
		
		
		System.out.println("test if sys out gets logged");
	}
}
