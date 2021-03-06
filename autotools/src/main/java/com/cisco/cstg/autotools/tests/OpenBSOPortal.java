package com.cisco.cstg.autotools.tests;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cisco.cstg.autotools.dao.TestStatusDao;
import com.cisco.cstg.autotools.domain.appdb.TestStatus;
import com.cisco.cstg.autotools.semantic.diagnostics.ValidationException;
import com.cisco.cstg.autotools.support.email.PostOffice;
import com.cisco.cstg.autotools.support.email.SystemPostOffice;
import com.cisco.cstg.autotools.tests.pages.BasePage;
import com.cisco.cstg.autotools.tests.pages.LoginPage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=
{
 "/applicationContext.xml",
 "/applicationContext-security.xml",
 "/spring-servlet.xml"
}
)
public class OpenBSOPortal extends BaseTest {
	
	protected static final Logger logger = LoggerFactory.getLogger(OpenBSOPortal.class);
	
	@Autowired
	TestStatusDao testStatusDao; 

	@Test
	public void testOpenBSOPortal() throws Exception {
		try{
			
			Map<String,String> insertions = new TreeMap<String,String>();
			
			TestStatus testStatus = testStatusDao.getByTestId(new Long(100));
			testStatus.setTestStatus("Running");
			testStatusDao.save(testStatus);			
		//start the test
//		driver.get(baseUrl);
//		logger.info("WINDOW HANDLE: "+driver.getWindowHandles().toString());
		WebDriverWait pageLoaded = new WebDriverWait(driver, 60);
		long start = System.nanoTime();	
		long startOfWholeTest = System.nanoTime();
		driver = new LoginPage(driver).loginToPortal(BasePage.baseUrl, BasePage.username, BasePage.pwd);
		driver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		long stop = System.nanoTime();
		String timeTakenToLogin = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
		insertions.put("time_taken_to_login", timeTakenToLogin);

		logger.info("TIME TAKEN FOR LOGIN: "+timeTakenToLogin);
		
		//starting clock for simultaneous load of welcome page and LNP
		start = System.nanoTime();
		/**
		 *IF the welcome window opens up then this code will come into play 
		try{
			logger.info("Waiting for the ok button");
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
												.withTimeout(7, TimeUnit.SECONDS)
												.pollingEvery(1, TimeUnit.SECONDS)
												.ignoring(NoSuchElementException.class);
			WebElement OKButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("xwt_widget_form_TextButton_2")));
			logger.info("Found the Button Field");
			OKButton.click();
			stop = System.nanoTime();
			String timeTakenToDisplayWP = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
			insertions.put("time_taken_for_wcpage", timeTakenToDisplayWP);
			logger.info("TIME TAKEN FOR Accept form to show up: "+timeTakenToDisplayWP);
		}catch(NoSuchElementException exp){
			insertions.put("time_taken_for_wcpage", "0 sec");
			stop = System.nanoTime();
			String timeTakenToDisplayWP = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
			logger.info("NO SUCH BUTTON EXISTS: OK BUTTON: no such element time taken: "+timeTakenToDisplayWP);
		}catch(TimeoutException exp){
			insertions.put("time_taken_for_wcpage", "0 sec");
			String timeTakenToDisplayWP = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
			logger.info("TIME OUT EXCEPTION:  time taken: "+timeTakenToDisplayWP);
		}
		*/
			
			try{
				driver = pageLoaded.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className("workspaceViewFrame")));
				logger.info("FOUND MAIN IFRAME");
				logger.info("WINDOW HANDLE: "+driver.getWindowHandles().toString()+" "+driver.getWindowHandle());
				
				WebElement sntc = pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Smart Net Total Care')]")));
				stop = System.nanoTime();
				
				Coordinates coordinate = ((Locatable)sntc).getCoordinates(); 
				coordinate.onPage(); 
				coordinate.inViewPort();
				logger.info("FOUND SNTC LINK "+coordinate.inViewPort());
				String timeTakenToLoadLNP = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
				insertions.put("time_taken_to_load_lnp", timeTakenToLoadLNP);
				logger.info("TIME TAKEN FOR LEFT NAVIGATION TO LOAD: "+timeTakenToLoadLNP);
				logger.info("FOUND SNTC DASHBOARD LINK");
				sntc.click();
				logger.info("CLICKED SNTC DASHBOARD LINK");
			}catch(NoSuchElementException exp){
				logger.info("NO SUCH ELEMENT EXPCEPTION");
			}catch(TimeoutException exp){
				stop = System.nanoTime();
				String timeTakenToLoadLNP = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
				logger.info("TIME OUT ON FINDING THE SNTC ELEMENT: TimeOutTime: "+timeTakenToLoadLNP);
				insertions.put("time_taken_to_load_lnp", "PAGE NOT LOADING");
			}

			start = System.nanoTime();
			
			boolean inv_loaded = false;
			boolean alerts_loaded = false;
			boolean contracts_loaded = false;
			boolean widgetNotLoaded = false;
			
			while(!(inv_loaded && alerts_loaded && contracts_loaded)){
				try {
					List<WebElement> iframes = pageLoaded.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("iframe")));
					logger.info("Total number of iframes: "+ iframes.size());
					logger.info("WINDOW HANDLE: "+driver.getWindowHandles().toString()+" "+driver.getWindowHandle());
                if(iframes.size()>=3){
					for (WebElement iframeInstance : iframes){
					
						String src = iframeInstance.getAttribute("src");
						logger.info("The tag name: "+ iframeInstance.getTagName()+" "+iframeInstance.getText()+" : "+src);
						logger.info(iframeInstance.getLocation().toString()+" index of the iframe "+iframes.indexOf(iframeInstance));
						driver.switchTo().frame(iframeInstance);				
						if(src.contains("IMS_ALL_DEVICES_DUP")){
							logger.info("FOUND INVENTORY IFRAME");
							try{
								pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Equipment Type')]")));
								logger.info("INVENTORY IFRAME LOADING COMPLETE");
								inv_loaded = true;
								}catch(NoSuchElementException exp){
									logger.info("NO SUCH ELEMENT EXPCEPTION");
								}catch(TimeoutException exp){
									logger.info("TIME OUT ON LOADING INVENTORY SCREEN");
									widgetNotLoaded = true;
									break;
								}
						}else if(src.contains("ALERTSDELTA_BSO")){
							logger.info("FOUND THE ALERTS IFRAME");
							try{
								pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Type')]")));
								logger.info("ALERTS IFRAME LOADING COMPLETE");
								alerts_loaded = true;
								}catch(NoSuchElementException exp){
									logger.info("NO SUCH ELEMENT EXPCEPTION");
								}catch(TimeoutException exp){
									logger.info("TIME OUT ON LOADING ALERTS WIDGET");
									widgetNotLoaded = true;
									break;
								}
						}else if(src.contains("bso_item_list")){
							logger.info("FOUND CONTRACTS IFRAME");
							try{
								WebDriverWait wait = new WebDriverWait(driver, 7);
								wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Coverage')]")));
								logger.info("CONTRACTS IFRAME LOADING COMPLETE");
								contracts_loaded = true;
								}catch(NoSuchElementException exp){
									logger.info("NO SUCH ELEMENT EXPCEPTION");
								}catch(TimeoutException exp){
									logger.info("TIME OUT ON LOADING COVERAGE WIDGET");
									widgetNotLoaded = true;
									break;
								}
						}
						driver.switchTo().parentFrame();
						logger.info("SWITCHED TO PARENT");
					}
					if(widgetNotLoaded){ 
						stop = System.nanoTime();
						String timeTakenToLoadSNTC = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
						insertions.put("time_taken_to_load_sntc", "ATLEAST ONE WIDGET NOT LOADED");
						logger.info("TIME TAKEN FOR SNTC DASHBOARD TO LOAD: TimeOutTime: "+ timeTakenToLoadSNTC);
						break;}
				}
					stop = System.nanoTime();
					String timeTakenToLoadSNTC = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
					insertions.put("time_taken_to_load_sntc", timeTakenToLoadSNTC);
					logger.info("TIME TAKEN FOR SNTC DASHBOARD TO LOAD: "+ timeTakenToLoadSNTC);		
				} catch (Exception e) {
					stop = System.nanoTime();
					String timeTakenToLoadSNTC = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
					insertions.put("time_taken_to_load_sntc", "PAGE NOT LOADED");
					logger.info("TIME TAKEN FOR SNTC DASHBOARD TO LOAD: TimeOutTime: "+ timeTakenToLoadSNTC);
				}	
			}
			
			long stopOfWholeTest = System.nanoTime();
			logger.info("TOTAL TIME TAKEN FOR ALL STEPS SO FAR: "+TimeUnit.SECONDS.convert((stopOfWholeTest - startOfWholeTest), TimeUnit.NANOSECONDS)+ " sec");
			logger.info("WINDOW HANDLE: "+driver.getWindowHandles().toString()+" "+driver.getWindowHandle());
			driver.switchTo().defaultContent();
			//START OF ALL ALERTS
			try{
				driver = pageLoaded.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className("workspaceViewFrame")));
				logger.info("FOUND MAIN IFRAME");
				WebElement allAlerts = pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'All Alerts')]")));
				logger.info("FOUND THE ALL ALERTS");
				Coordinates coordinate = ((Locatable)allAlerts).getCoordinates(); 
//				coordinate.onPage(); 
				Point allAlertsLocation = coordinate.inViewPort();
				logger.debug("Location of All alerts: "+allAlertsLocation.toString()+ " displayed: "+allAlerts.isDisplayed());
//				Actions actions = new Actions(driver);
//				actions.moveToElement(allAlerts).click().perform();
				allAlerts.click();
				start = System.nanoTime();
				logger.info("Clicked the element");
				List<WebElement> iframes = pageLoaded.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("iframe")));
				logger.info("Total number of iframes: "+ iframes.size());
				logger.info("WINDOW HANDLE: "+driver.getWindowHandles().toString());
				for (WebElement iframeInstance : iframes){
				
					String src = iframeInstance.getAttribute("src");
					logger.info("The tag name: "+ iframeInstance.getTagName()+" "+iframeInstance.getText()+" : "+src);
					logger.info(iframeInstance.getLocation().toString()+" index of the iframe "+iframes.indexOf(iframeInstance));
					driver.switchTo().frame(iframeInstance);				
					if(src.contains("SNTC_ALERTSDELTA")){
						logger.info("FOUND THE ALERTS IFRAME");
							pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.ssueNonzerolinkto")));
							logger.info("DATA GRID TABLE LOADED");
					}
					driver.switchTo().parentFrame();
					logger.info("Switched to the iframe Workspace");
				}
				stop = System.nanoTime();
				String timeTakenToLoadAllAlerts = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
				insertions.put("time_taken_to_load_AllAlerts", timeTakenToLoadAllAlerts);
				logger.info("TIME TAKEN FOR ALL ALERTS TO LOAD: "+timeTakenToLoadAllAlerts);
			}catch(NoSuchElementException exp){
				logger.info("NO SUCH ELEMENT EXPCEPTION");
			}catch(TimeoutException exp){
				stop = System.nanoTime();
				String timeTakenToLoadAllAlerts = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
				insertions.put("time_taken_to_load_AllAlerts", "PAGE NOT LOADING");
				logger.info("TIME TAKEN FOR ALL ALERTS TO LOAD: TimeOutTime: "+timeTakenToLoadAllAlerts);
			}
			logger.info("WINDOW HANDLE: "+driver.getWindowHandles().toString()+" "+driver.getWindowHandle());
			driver.switchTo().defaultContent();
			//START OF ALL CONTRACTS
			try{
				driver = pageLoaded.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className("workspaceViewFrame")));
				logger.info("FOUND MAIN IFRAME");
				WebElement allContracts = pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'All Contracts')]")));
				logger.info("FOUND THE ALL CONTRACTS");
				Coordinates coordinate = ((Locatable)allContracts).getCoordinates(); 
				coordinate.onPage(); 
				coordinate.inViewPort();
				allContracts.click();
				start = System.nanoTime();
				logger.info("Clicked the element");
				List<WebElement> iframes = pageLoaded.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("iframe")));
				logger.info("Total number of iframes: "+ iframes.size());
				for (WebElement iframeInstance : iframes){
				
					String src = iframeInstance.getAttribute("src");
					logger.info("The tag name: "+ iframeInstance.getTagName()+" "+iframeInstance.getText()+" : "+src);
					logger.info(iframeInstance.getLocation().toString()+" index of the iframe "+iframes.indexOf(iframeInstance));
					driver.switchTo().frame(iframeInstance);				
					if(src.contains("50002_CONTRACT")){
						logger.info("FOUND THE CONTRACTS IFRAME");
							pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.ssueNonzerolinkto")));
							logger.info("DATA GRID TABLE LOADED");
					}
					driver.switchTo().parentFrame();
					logger.info("Switched to the iframe Workspace");
				}
				stop = System.nanoTime();
				String timeTakenToLoadAllContracts = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
				insertions.put("time_taken_to_load_AllContracts", timeTakenToLoadAllContracts);
				logger.info("TIME TAKEN FOR ALL CONTRACTS TO LOAD: "+timeTakenToLoadAllContracts);
			}catch(NoSuchElementException exp){
				logger.info("NO SUCH ELEMENT EXPCEPTION");
			}catch(TimeoutException exp){
				stop = System.nanoTime();
				String timeTakenToLoadAllContracts = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
				insertions.put("time_taken_to_load_AllContracts", "PAGE NOT LOADING");
				logger.info("TIME TAKEN FOR ALL CONTRACTS TO LOAD: TimeOutTime: "+timeTakenToLoadAllContracts);
			}
			
			logger.info("WINDOW HANDLE: "+driver.getWindowHandles().toString()+" "+driver.getWindowHandle());
			driver.switchTo().defaultContent();
			//START OF ALL EQUIPMENTS
			try{
				driver = pageLoaded.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className("workspaceViewFrame")));
				logger.info("FOUND MAIN IFRAME");
				WebElement allEquip = pageLoaded.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'All Equipment')]")));
				logger.info("FOUND THE ALL EQUIP");
				Coordinates coordinate = ((Locatable)allEquip).getCoordinates(); 
				coordinate.onPage(); 
				coordinate.inViewPort();
				allEquip.click();
				start = System.nanoTime();
				logger.info("Clicked the element");
				List<WebElement> iframes = pageLoaded.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("iframe")));
				logger.info("Total number of iframes: "+ iframes.size());
				for (WebElement iframeInstance : iframes){
				
					String src = iframeInstance.getAttribute("src");
					logger.info("The tag name: "+ iframeInstance.getTagName()+" "+iframeInstance.getText()+" : "+src);
					logger.info(iframeInstance.getLocation().toString()+" index of the iframe "+iframes.indexOf(iframeInstance));
					driver.switchTo().frame(iframeInstance);				
					if(src.contains("IMS_ALL_DEVICES")){
						logger.info("FOUND THE EQUIP IFRAME");
							pageLoaded.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.ssueNonzerolinkto")));
							logger.info("Found one record");
					}
					driver.switchTo().parentFrame();
					logger.info("Switched to the iframe Workspace");
				}
				stop = System.nanoTime();
				String timeTakenToLoadAllEquip = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
				insertions.put("time_taken_to_load_AllEquip", timeTakenToLoadAllEquip);
				logger.info("TIME TAKEN FOR ALL EQUIP TO LOAD: "+timeTakenToLoadAllEquip);
			}catch(NoSuchElementException exp){
				logger.info("NO SUCH ELEMENT EXPCEPTION");
			}catch(TimeoutException exp){
				stop = System.nanoTime();
				String timeTakenToLoadAllEquip = new Long(TimeUnit.SECONDS.convert((stop - start), TimeUnit.NANOSECONDS)).toString() + " sec";
				insertions.put("time_taken_to_load_AllEquip", "PAGE NOT LOADING");
				logger.info("TIME TAKEN FOR ALL EQUIP TO LOAD: TimeOutTime: "+timeTakenToLoadAllEquip);
			}

			logger.info("WINDOW HANDLE: "+driver.getWindowHandles().toString()+" "+driver.getWindowHandle());
			driver.switchTo().defaultContent();
			// test complete
			testStatus.setTestStatus("Passed");

			PostOffice po = new SystemPostOffice();
			
			insertions.put("report_date", (new Date()).toString());
			String [] toAddresses = TestConstants.toEmailAddresses.split(",");
			String [] ccAddresses = null;
			try {
				po.sendEmail(TestConstants.pageloadReportTemplateName, insertions, toAddresses, ccAddresses);
			} catch (ValidationException e) {
				e.printStackTrace();
			}
			//update the database with the test result.
			testStatusDao.save(testStatus);
		}
		catch(Exception exp){
		  logger.debug("EXCEPTION IN GETTING THE DAO");
		  TestStatus testStatus = testStatusDao.getByTestId(new Long(100));
			testStatus.setTestStatus("Passed");
			testStatusDao.save(testStatus);
		    final Writer result = new StringWriter();
		    final PrintWriter printWriter = new PrintWriter(result);
		    exp.printStackTrace(printWriter);
		    logger.debug(result.toString());
		}

	}
}