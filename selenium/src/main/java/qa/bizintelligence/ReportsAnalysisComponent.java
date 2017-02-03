package qa.bizintelligence;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.junit.Assert;
import qa.SeleniumTest;
import qa.utility.InterruptTool;
import qa.utility.WaitTool;
import qa.utility.bizjdb.DatabaseTool;

public class ReportsAnalysisComponent extends qa.events.BasePage {
	WaitTool wait;
	InterruptTool interrupt;
	DatabaseTool databaseTool;
	String tempUrl = "http://qa-reports.bizj-internal.com/Nightly%20Regression/qa/";
	

	public ReportsAnalysisComponent(WebDriver driver) {
		super(driver);

		// TODO Auto-generated constructor stub
		wait = new WaitTool(driver);
		interrupt = new InterruptTool(driver);
	}

	@Override
	public boolean atPage() {
		return pageTitleHeader().isDisplayed();
	}

	// --------------------------Elements-------------------------------------//

	private WebElement pageTitleHeader() {
		return driver.findElement(By.tagName("title"));
	}

	private List<WebElement> linkList() {
		return driver.findElement(By.id("listing")).findElements(By.tagName("a"));
	}

//	private WebElement testsRun() {
//		return driver.findElements(By.className("navbar-text")).get(0);
//	}
//
//	private WebElement testsPassed() {
//		return driver.findElements(By.className("navbar-text")).get(1);
//	}
//
//	private WebElement testsFailed() {
//		return driver.findElements(By.className("navbar-text")).get(2);
//	}
	
	private List<WebElement> testColumn(){
		return driver.findElement(By.className("affix")).findElements(By.className("list-group-item"));
	}

	// --------------------------Elements-------------------------------------//

	private String getDate(boolean month) {
		if (month) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMM");
			Date now = new Date();
			return dateFormat.format(now);
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMMdd-");
			Date now = new Date();
			return dateFormat.format(now);
		}
	}

	private int[] analyzeReport() {
		int tests, passed = 0;
		tests = testColumn().size();
		for(int i=0;i<testColumn().size();i++){
			if(testColumn().get(i).getAttribute("class").contains("success")){
				passed++;
			}
		}
		int[] result = new int[]{tests, passed};
		return result;
	}
	
	private float toReportString(ArrayList<int[]> results) throws ArithmeticException{
		int tests=0, passed=0;
		for(int i=0;i<results.size();i++){
			tests+=results.get(i)[0];
			passed+=results.get(i)[1];
		}
		float percentage = passed/(float)tests;
		SeleniumTest.logger.info(percentage+"% of tests successful in"+ driver.getCurrentUrl()+System.lineSeparator());
		return percentage;
	}
	
	public String getTestLinksUrl(String append){
		return this.tempUrl+append;
	}

	public int getStatsCurrentDay() {
		wait.waitForPresenceOf(By.id("listing"));
		//gather results
		ArrayList<int[]> results = new ArrayList<int[]>();
		for (int i = 0; i < linkList().size(); i++) {
			if (linkList().get(i).getText().contains(getDate(false))) { 
				linkList().get(i).click();
				Assert.assertTrue(wait.waitForPresenceOf(By.className("list-group-item")));
				results.add(analyzeReport());
				driver.navigate().back();
				wait.waitForPresenceOf(By.id("listing"));
			}
		}	
		float temp=0;
		try{
			temp = toReportString(results)*100;			
		}catch(ArithmeticException ex){
			SeleniumTest.logger.severe(ex.getMessage()+System.lineSeparator());	
			Assert.fail(ex.getMessage());
		}
		return (int) temp;
	}

	public int getStatsCurrentMonth() {
		wait.waitForPresenceOf(By.id("listing"));
		ArrayList<int[]> results = new ArrayList<int[]>();
		for (int i = 0; i < linkList().size(); i++) {
			if (linkList().get(i).getText().contains(getDate(true))) {
				linkList().get(i).click();
				Assert.assertTrue(wait.waitForPresenceOf(By.className("list-group-item")));
				results.add(analyzeReport());				
				driver.navigate().back();
				wait.waitForPresenceOf(By.id("listing"));
			}
		}
		float temp=0;
		try{
			temp = toReportString(results)*100;			
		}catch(ArithmeticException ex){
			SeleniumTest.logger.severe(ex.getMessage()+System.lineSeparator());	
			Assert.fail(ex.getMessage());
		}
		return (int) temp;
	}	
}
