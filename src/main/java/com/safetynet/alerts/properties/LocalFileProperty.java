package com.safetynet.alerts.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * Configuration file for local file name
 * 
 * @author MikeMatthews
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "com.safetynet.alerts")
public class LocalFileProperty {
	String localFileName;
}