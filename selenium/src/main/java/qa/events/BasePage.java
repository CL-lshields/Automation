package qa.events;

import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import qa.SeleniumTest;
import qa.utility.WaitTool;

public abstract class BasePage {

	protected WebDriver driver;
	protected WaitTool wait;
	public static Logger logger = Logger.getLogger(SeleniumTest.class.getName());
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WaitTool(driver);
	}
	
	public abstract boolean atPage();
	
	protected void setSelectionState(WebElement selectable, boolean select) {
		if (select != selectable.isSelected()) {
			selectable.click();
		}
	}
	
	/**
	 * creates new working tab
	 */
	public void newTab(){
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		SeleniumTest.logger.info("Created new tab..."+System.lineSeparator());
	}
	
	/**
	 * switches working tab
	 * @param tabs - number of tabs to switch through
	 */
	public void switchTab(int tabs){
		for(int i = tabs;i>0;i--){
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "\t");
		}
		SeleniumTest.logger.info("Switched "+tabs+" tab(s)..."+System.lineSeparator());
		
	}
	/**
	 * set active window
	 * @param window - window to handle
	 */
	public void setWindow(String window){
		Set <String> set1=driver.getWindowHandles();
		Iterator <String> win1= set1.iterator();
		 String parent=win1.next();
		 String child=win1.next();
		if (window.equals("child")){
			 driver.switchTo().window(child);
		} else {
			driver.switchTo().window(parent);
		}	 
	}
	
	/**
	 * Verify download link - WIP - TODO
	 * @throws Exception
	 */
	public void downloadVerification() throws Exception 
    {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         //The below method will save the screen shot in d drive with name "screenshot.png"
            FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")
        			+ "/selenium/src/main/java/qa/data/resources/downloadVerify.png"));
    }
	
	
//	public void switchToFrame(String frame){
//		//Wait until frame is found and then switch to it if able
//		for(int i = 0; i < 10; i++){
//			if(driver.findElement(By.xpath("//iframe[@name='"+ frame+"']")).isDisplayed())
//				try{
//					driver.switchTo().frame(frame);
//				} catch (Exception e) {
//					System.out.println("No Frame with name of " + frame + " was found");
//				}			
//			else
//				try {
//					Thread.sleep(2000);
//				} catch (InterruptedException e) {
//					System.out.println("Sleep for frame "+ frame +" was not successful");
//				}
//		}
//	}
}