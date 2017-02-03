package qa.utility;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.google.common.collect.ImmutableMap;


/**
 * Created by root on 4/1/16.
 */
public class WebDriverFactory {

    public static WebDriver createWebDriver(String browser) {
        WebDriver driver = null;
        switch (browser) {
            case "chrome":
            	System.setProperty("webdriver.chrome.driver", 
            			System.getProperty("user.dir")+"/drivers/chrome/chromedriver");
                driver = new ChromeDriver();
                break;
            case "xvfb chrome":
                //driver = new ChromeDriver();
            	System.setProperty("webdriver.chrome.driver", 
            			System.getProperty("user.dir")+"/drivers/chrome/chromedriver");
                ChromeDriverService service = new ChromeDriverService.Builder()
                	    .usingDriverExecutable(new File(System.getProperty("user.dir")+"/drivers/chrome/chromedriver"))
                	    .usingAnyFreePort()
                	    .withEnvironment(ImmutableMap.of("DISPLAY",":20"))
                	    .build();
			try {
				driver = new ChromeDriver();
				service.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "xvfb firefox":
                //FF Binary
                FirefoxBinary firefoxBinary = new FirefoxBinary();
                firefoxBinary.setEnvironmentProperty("DISPLAY", qa.constants.SeleniumConstants.X_DISPLAY_PORT);

                //FF profile
                FirefoxProfile firefoxProfile = new FirefoxProfile();
                firefoxProfile.setPreference("general.useragent.override", "Mozilla/5.0 Selenium WebDriver");

                //driver
                driver = new FirefoxDriver(firefoxBinary, firefoxProfile);
                break;
            case "html unit":
                driver = new HtmlUnitDriver();
                break;
            case "xvfb html unit":
                driver = new HtmlUnitDriver();
                break;
            case "internet explorer 64": 	                
                driver = initiateInternetExplorer("64");
                break;
            case "internet explorer 32":            	
                driver = initiateInternetExplorer("32");
                break;
            case "xvfb internet explorer 64":
                driver = initiateInternetExplorer("64");
                break;
            case "xvfb internet explorer 32":
                driver = initiateInternetExplorer("32");
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            case "xvfb safari":
                driver = new SafariDriver();
                break;
            default:
                driver = new FirefoxDriver();
        }
        return driver;
    }

    //private method to instaniate Internet Explorer Webdriver
    private static WebDriver initiateInternetExplorer(String bit) {
        try {
            //GetPropertyValues properties = new GetPropertyValues();
            File file = new File(System.getProperty("user.dir") + "/drivers/explorer/"+bit+"bit/IEDriverServer.exe");
            System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            qa.SeleniumTest.logger.info("Exception while creating internet explorer driver..."+System.lineSeparator());
        }
        qa.SeleniumTest.logger.info("created internet explorer driver..."+System.lineSeparator());
        return new InternetExplorerDriver();
    }

}

