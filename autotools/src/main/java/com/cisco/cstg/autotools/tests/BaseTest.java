package com.cisco.cstg.autotools.tests;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {

	protected WebDriver driver;
	protected boolean acceptNextAlert = true;
	protected StringBuffer verificationErrors = new StringBuffer();
	private final Logger logger = LoggerFactory.getLogger(BaseTest.class);
	
	/**
	 * Test parameters
	 * BEGIN
	 */
	protected static final String URL="url";
	protected static final String USER_NAMES="usernames";
	protected static final String PASSWORDS="passwords";
	protected static final String CUSTOMER_NAMES="customernames";
	protected static final String INVENTORY_NAMES="inventorynames";
	

	
	/**
	 * TEST PARAMETERS
	 *    END
	 */
	

	public void setUp() throws Exception {
//		try {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(TestConstants.WEBDRIVER_IMPLICIT_TIMEOUT_VALUE, TimeUnit.SECONDS);			
//		} catch (Exception exp) {
//			final Writer result = new StringWriter();
//			final PrintWriter printWriter = new PrintWriter(result);
//			exp.printStackTrace(printWriter);
//			logger.debug(result.toString());
//		}

	}

	public void tearDown() throws Exception {
//	    driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	public WebDriver openFireFoxProfileDriver(String profileName) {
		try {
			FirefoxProfile profile = null;
			profile = new ProfilesIni().getProfile(profileName);
//			File autoauth = new File(new File("").getAbsolutePath()+ "/extensions/autoauth-2.1-fx+fn.xpi");
			
			logger.debug("Path of the file: "+ Thread.currentThread().getContextClassLoader().getResource("autoauth-2.1-fx+fn.xpi"));
			File autoauth = new File(Thread.currentThread().getContextClassLoader().getResource("autoauth-2.1-fx+fn.xpi").getPath());
			profile.addExtension(autoauth);
			driver = new FirefoxDriver(profile);
			driver.manage().timeouts().implicitlyWait(TestConstants.WEBDRIVER_IMPLICIT_TIMEOUT_VALUE, TimeUnit.SECONDS);

		} catch (Exception e) {
			e.printStackTrace();
			final Writer result = new StringWriter();
		    final PrintWriter printWriter = new PrintWriter(result);
		    e.printStackTrace(printWriter);
		    logger.debug(result.toString());
		}
		return driver;
	}
	
	public WebDriver openFireFoxProfileDriverWithoutAuth(String profileName) {
		try {
			FirefoxProfile profile = null;
			profile = new ProfilesIni().getProfile(profileName);
			driver = new FirefoxDriver(profile);
			driver.manage().timeouts().implicitlyWait(TestConstants.WEBDRIVER_IMPLICIT_TIMEOUT_VALUE, TimeUnit.SECONDS);	

		} catch (Exception e) {
			e.printStackTrace();
			final Writer result = new StringWriter();
		    final PrintWriter printWriter = new PrintWriter(result);
		    e.printStackTrace(printWriter);
		    logger.debug(result.toString());
		}
		return driver;
	}
}