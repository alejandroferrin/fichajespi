package com.fichajespi.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerService {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	private final String prefix = "-_- ";

	public void logInfo(String message) {
		LOGGER.info(prefix + message);
	}

	public void logWarn(String message) {
		LOGGER.warn(prefix + message);
	}

	public void logError(String message) {
		LOGGER.error(prefix + message);
	}

}
