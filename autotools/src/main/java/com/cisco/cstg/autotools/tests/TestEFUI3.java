package com.cisco.cstg.autotools.tests;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cisco.cstg.autotools.dao.TestStatusDao;
import com.cisco.cstg.autotools.tests.pages.BasePage;
import com.cisco.cstg.autotools.tests.pages.PageConstants;
import com.cisco.cstg.autotools.tests.pages.UI3.EFUI3Page;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=
{
 "/applicationContext.xml",
 "/applicationContext-security.xml",
 "/spring-servlet.xml"
}
)
public class TestEFUI3 extends BaseTest {
	
	protected static final String EFUI3URL = BasePage.pageConfig.getStringValue(PageConstants.EFUI3_URL);
	
	
	protected static final Logger logger = LoggerFactory.getLogger(TestEFUI3.class);
	
	@Autowired
	TestStatusDao testStatusDao; 
	
	@Override
	public void setUp() throws Exception {
		driver = openFireFoxProfileDriver(BasePage.firefoxProfileName); 
	}

	@Test
	public void testEFUI3() throws Exception {
		
		Map<String,String> insertions = new TreeMap<String,String>();
		
//		TestStatus testStatus = testStatusDao.getByTestId(new Long(101));
//		testStatus.setTestStatus("Running");
//		testStatusDao.save(testStatus);		
		try{
		long start = System.nanoTime();	
//		driver = new LoginPage(driver).loginToPortal(baseUrl, username, pwd);
		EFUI3Page uiPage = new EFUI3Page(driver).loginToEFUI3(EFUI3URL);
		uiPage = uiPage.selectServiceProgram();
		Assert.assertTrue("testEFUI3:The EFUI3 Failed", false);
		uiPage = uiPage.selectServiceProgramRole();
		uiPage = uiPage.selectCompanyServiceProgram();
		uiPage = uiPage.selectPartyId();
		uiPage = uiPage.doSearch();
		uiPage = uiPage.findSearch();
		
		driver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		long stop = System.nanoTime();
		String timeTakenToLogin = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
		insertions.put("time_taken_to_login", timeTakenToLogin);

		logger.info("TIME TAKEN FOR LOGIN: "+timeTakenToLogin);

		}catch(Exception e){
			logger.debug("EXCEPTION IN MAIN");
			final Writer result = new StringWriter();
			final PrintWriter printWriter = new PrintWriter(result);
			e.printStackTrace(printWriter);
			logger.debug(result.toString());
		}
//		  testStatus = testStatusDao.getByTestId(new Long(101));
//		  testStatus.setTestStatus("Passed");
//		  testStatusDao.save(testStatus);

	}
}