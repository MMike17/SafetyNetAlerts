package com.safetynet.alerts;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests integration of classes in main application class
 * 
 * @author MikeMatthews
 */
@SpringBootTest
class SafetyNetAlertsApplicationIT {
	static SafetyNetAlertsApplication testedApplication;

	@BeforeAll
	static void setUp() {
		testedApplication = new SafetyNetAlertsApplication();
	}

	/**
	 * Tests if the SpringBoot context is loaded correctly
	 */
	@Test
	void contextLoads() {
	}
}