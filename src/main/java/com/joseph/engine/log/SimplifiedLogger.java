package com.joseph.engine.log;

import org.apache.logging.log4j.Logger;

public class SimplifiedLogger {
	private final Logger log;
	
	public SimplifiedLogger(Logger log) {
		this.log = log;
	}
	
	public Logger getLogger() {
		return log;
	}
	
	public void debug(Object o) {
		log.debug(o);
	}
	
	public void debug(String s) {
		log.debug(s);
	}
	
	public void info(Object o) {
		log.info(o);
	}
	
	public void info(String s) {
		log.info(s);
	}
	
	public void warn(Object o) {
		log.warn(o);
	}
	
	public void warn(String s) {
		log.warn(s);
	}
	
	public void error(Object o) {
		log.error(o);
	}
	
	public void error(String s) {
		log.error(s);
	}
	
	public void fatal(Object o) {
		log.fatal(o);
	}
	
	public void fatal(String s) {
		log.fatal(s);
	}
}
