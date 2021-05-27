package com.safetynet.alerts.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration file for local file name
 * 
 * @author MikeMatthews
 */
@Configuration
@ConfigurationProperties(prefix = "com.safetynet.alerts")
public class LocalFileProperty {

	String localFileName;

	public String getLocalFileName() {
		return localFileName;
	}

	public void setLocalFileName(String localFileName) {
		this.localFileName = localFileName;
	}
}