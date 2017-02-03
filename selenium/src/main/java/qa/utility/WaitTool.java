package qa.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import qa.SeleniumTest;
import qa.constants.SeleniumConstants;

public class WaitTool {
	
	private static int DEFAULT_WAIT_TIMEOUT = 5; //TODO set this from EnvConfig
	
	private WebDriver driver;
	
	public WaitTool(final WebDriver driver) {
		this.driver = driver;
	}

	public WebElement waitForElementToBeClickable(WebElement element, int timeout) {
		try {
			disableImplicitWait();
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			return wait.until(ExpectedConditions.elementToBeClickable(element));
		} finally {
			enableImplicitWait();
		}
	}
	
	public WebElement waitForVisibilityOf(WebElement element, int timeout) {
		try {
			disableImplicitWait();
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			return wait.until(ExpectedConditions.visibilityOf(element));
		} finally {
			enableImplicitWait();
		}
	}
	
	public boolean waitForPresenceOf(By locator){
		try {
			disableImplicitWait();
			(new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(locator));
			enableImplicitWait();
			return true;			
		} catch(Exception ex) {
			SeleniumTest.logger.severe(ex.getMessage()+System.lineSeparator());
			SeleniumTest.logger.severe("Exception caught waiting for " + locator +System.lineSeparator());
			enableImplicitWait();
			return false;			
		}		
	}
	
	//@Overload - manually set time to wait for element
	public boolean waitForPresenceOf(By locator, int waitTime){
		try {
			disableImplicitWait();
			(new WebDriverWait(driver, waitTime)).until(ExpectedConditions.presenceOfElementLocated(locator));
			enableImplicitWait();
			return true;			
		} catch(Exception ex) {
			SeleniumTest.logger.severe(ex.getMessage()+System.lineSeparator());
			SeleniumTest.logger.severe("Exception caught waiting for " + locator +System.lineSeparator());
			enableImplicitWait();
			return false;			
		}		
	}
	
	public boolean waitForInvisibilityof(By locator, int timeout) {
		try {
			disableImplicitWait();
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		} finally {
			enableImplicitWait();
		}
	}
	
	public void sleep(int duration) {
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			
		}
	}
	
	/**
	 * @author Bob Eksten <reksten@bizjournals.com>
	 * Simple method to wait for JavaScript code to be loaded in a page before
	 * continuing with a test.
	 * 
	 * @param driver
	 * @return boolean
	 */
	public boolean waitForJs(WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, SeleniumConstants.MAX_TIMEOUT);

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		return wait.until(jsLoad);
	}
	
	private void disableImplicitWait() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	
	private void enableImplicitWait() {
		driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_TIMEOUT, TimeUnit.SECONDS);
	}
}