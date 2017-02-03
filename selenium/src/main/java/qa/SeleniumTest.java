/**
 * 
 */
package qa;

import static java.util.concurrent.TimeUnit.SECONDS;
import static qa.constants.SeleniumConstants.WAIT_TIMEOUT;
import static qa.constants.SeleniumConstants.X_DISPLAY_PORT;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.ErrorCollector;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import qa.utility.NetworkTool;
import qa.utility.SingleLineFormatter;
import qa.utility.WebDriverFactory;
import qa.utility.emailutility.EmailTool;
import qa.utility.reportutility.ReportList;

/**
 * @author Bob Eksten <reksten@bizjournals.com>
 *
 */
public abstract class SeleniumTest {

	@Rule
	public ErrorCollector ec = new ErrorCollector();

	public static WebDriver driver;

	public static Logger logger = Logger.getLogger(SeleniumTest.class.getName());

	public static ReportList reportList;

	public static String staticLogFile;
	
	public static boolean emailAlerts = false;
	
	public static String emailAdmin;

	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMMdd-HH:mm:ss");
	static java.util.Date now = new java.util.Date();

	public static String reportName;

	@Rule
	public TestRule watcher = new TestWatcher() {
		@Override
		protected void starting(Description description) {
			ReportList.reportName = File.separator + description.getClassName() + "-" + dateFormat.format(now);
			ReportList.reportResources = description.getMethodName() + "-" + dateFormat.format(now);
			ReportList.reportName = ReportList.reportName.replace(".", File.separator);
			ReportList.reportPath = ReportList.reportName.substring(0,
					ReportList.reportName.lastIndexOf(File.separator) + 1);
			ReportList.testInstance = description.getMethodName();
			logger.info("Starting test: " + description.getMethodName() + System.lineSeparator());
			reportName = description.getMethodName();
		}
	};

	@BeforeClass
	public static void init() {

		try {
			staticLogFile = "logs/" + dateFormat.format(now) + "-" + SeleniumTest.class.getName();			
			FileHandler fileHandler = new FileHandler(staticLogFile, true);
			SingleLineFormatter fmt = new SingleLineFormatter();
			fileHandler.setFormatter(fmt);
			logger.addHandler(fileHandler);
//			
//			SingleLineFormatter fmt = new SingleLineFormatter();
//			fileHandler.setFormatter(fmt);
//			
//						
//			ConsoleHandler consoleHandler = new ConsoleHandler();
//			consoleHandler.setFormatter(fmt);
//					
//			logger.addHandler(consoleHandler);
			reportList = new ReportList();			

		} catch (SecurityException se) {
			se.printStackTrace();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	@Before
	public void setUp() {
		
		FirefoxBinary firefoxBinary = new FirefoxBinary();
		firefoxBinary.setEnvironmentProperty("DISPLAY", X_DISPLAY_PORT);

		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setPreference("javascript.enabled", true);
		firefoxProfile.setPreference("general.useragent.override", "Mozilla/5.0 Selenium WebDriver");
		//firefoxProfile.setPreference("browser.privatebrowsing.autostart", true);
		
		// run under Xvfb
		driver = new FirefoxDriver(firefoxBinary, firefoxProfile);			
		driver.manage().window().maximize();
		
		// configure an implicit wait
		driver.manage().timeouts().pageLoadTimeout(WAIT_TIMEOUT, SECONDS);
	}

	/**
	 * 
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		// attach screenshots to failed tests
		if (!ReportList.getReportList().isEmpty()) {
			if (!ReportList.getReport(ReportList.getReportList().size() - 1).isPass()) {
				try{
				ReportList.getReport(ReportList.getReportList().size() - 1)
						.setScreenshot(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
				}catch(WebDriverException ex){
					logger.severe("Could not attach screenshot for " +  reportName + System.lineSeparator());
				}
			}
		}

		driver.close();
		driver.quit();
	}

	/**
	 * Builds HTML report from the collected reports in the ReportList
	 * 
	 */
	@AfterClass
	public static void tearDownClass() {			
//		for(int i=0;i<ReportList.getReportList().size();i++){			
//			if(ReportList.getReportList().get(i).isPass()){
//				emailAlerts=false;
//			}
//		}
//		if(emailAlerts){
//			EmailTool email = new EmailTool(emailAdmin);
//			email.sendEmail();
//			emailAlerts=false;
//		}
		NetworkTool.netAnalysis();
		ReportList.generateReport(ReportList.reportName);		
	}
	
	/**
	 * Provide an alternative logging style in which log messages are presented on a single line.
	 */
	protected static void enableSingleLineLogging() {
	logger.setUseParentHandlers(false);
		
		// remove handlers from global logger
		Logger globalLogger = Logger.getLogger("global");
		Handler[] globalHandlers = globalLogger.getHandlers();
		for(Handler handler : globalHandlers) {
			globalLogger.removeHandler(handler);
		}				
				
		// remove all handlers from local logger (including default)
		Handler[] handlers = logger.getHandlers();
		for(Handler handler : handlers) {
			logger.removeHandler(handler);
		}
		
		SingleLineFormatter fmt = new SingleLineFormatter();
		
		// console logging
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setFormatter(fmt);
		logger.addHandler(consoleHandler);
			
		// file logging
		try {
			FileHandler fileHandler = new FileHandler(staticLogFile, true);
			fileHandler.setFormatter(fmt);
			logger.addHandler(fileHandler);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}
}
