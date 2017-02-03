package qa.utility;

import java.util.HashMap;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.lang.Long;

/**
 * Created by root on 4/5/16.
 */
public class InterruptTool {

	private final String interstitialOne = "iframe/welcome?";
	private final String interstitialTwo = "tpc.googlesyndication.com";
	
    private WebDriver driver;
    private qa.utility.WaitTool wait;
    private JavascriptExecutor js;

    public InterruptTool(final WebDriver driver) {
        this.driver = driver;
        wait = new qa.utility.WaitTool(driver);
        js = (JavascriptExecutor) driver;
    }

    public void handleInterstitial(){
    	if(driver.getCurrentUrl().contains(interstitialOne)){
    		if(!driver.findElements(By.className("tip-enter")).isEmpty()){
    			//click tip-enter
    			wait.waitForElementToBeClickable(driver.findElement(By.className("tip-enter")), 10);
    			driver.findElement(By.className("tip-enter")).click();     			
    		}
    		if(!driver.findElements(By.xpath("/html/body/main/section/div/div[1]/div[2]/div[2]/a")).isEmpty()){
    			//click continue to site
    			wait.waitForElementToBeClickable(driver.findElement(By.xpath("/html/body/main/section/div/div[1]/div[2]/div[2]/a")), 10);
    			driver.findElement(By.xpath("/html/body/main/section/div/div[1]/div[2]/div[2]/a")).click();
    		}
    	}    	
    	else if(driver.getCurrentUrl().contains(interstitialTwo)){
    		if(!driver.findElement(By.className("header-stripes")).findElements(By.linkText("Skip this Ad")).isEmpty()){
    			driver.findElement(By.linkText("Skip this Ad")).click();
    		}
    	}else{    	
    		wait.sleep(20000);
    		qa.SeleniumTest.logger.info("waited for interstitial to pass");
    		if(!driver.getCurrentUrl().contains("http://bizjournals.bizj-dev.com/")){
    			driver.navigate().to("http://bizjournals.bizj-dev.com/");
    		}
    	}
    	
		qa.SeleniumTest.logger.info("Successfully handled interstitial");
    }

    public boolean scrollIntoView(WebElement element){
        try {
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            qa.SeleniumTest.logger.info("sucessfully scrolled " +element.toString()+" into view"+System.lineSeparator());
            return true;
        }catch (Exception ex){
            qa.SeleniumTest.logger.severe(ex.getMessage() + System.lineSeparator());
            return false;
        }
    }
    
    public boolean jsClick(WebElement element){
        try {
            js.executeScript("arguments[0].click();", element);
            qa.SeleniumTest.logger.info("sucessfully clicked " +element.toString()+" into view");
            return true;
        }catch (Exception ex){
            qa.SeleniumTest.logger.severe(ex.getMessage() + System.lineSeparator());
            return false;
        }
    }
    
    public void checkAlert() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception ex) {
        	qa.SeleniumTest.logger.severe(ex.getMessage() + System.lineSeparator());
        }
    }
    
    public void checkPagePerformance() {
    	
    	//perform network diagnostic via javascript
		String map = js.executeScript("return performance.timing || {};").toString();    	
    	map = map.replaceAll("\\{", "");
    	map = map.replaceAll("\\}", "");
    	
    	//place network diagnostic results into a HashMap<String,Long>
    	HashMap<String, Long> netHash = new HashMap<String,Long>();
    	String[] network = map.split(",");
    	for(int i=0;i<network.length;i++){
    		try{
    			netHash.put(network[i].split("=")[0].trim(), Long.valueOf(network[i].split("=")[1]).longValue());
        		//System.out.println(network[i].split("=")[0]+" , " + Long.valueOf(network[i].split("=")[1]).longValue());
    		}catch(NumberFormatException nfe){
    			qa.SeleniumTest.logger.severe("could not add to hashmap: "+nfe.getMessage()+System.lineSeparator());
    		}    	
    		
    		//System.out.println(netHash.toString());
    	}
    	
    	if(netHash.get("domComplete").longValue()==0){
    		checkPagePerformance();
    	}else{
	    	//create HashMap to return
	    	HashMap<String, Long> returnHash = new HashMap<String, Long>();
	    	qa.SeleniumTest.logger.info("connect: "+(netHash.get("connectEnd")-netHash.get("connectStart"))+ "ms"+System.lineSeparator());
	    	returnHash.put("connect", (netHash.get("connectEnd")-netHash.get("connectStart")));
	    	qa.SeleniumTest.logger.info("redirect: "+(netHash.get("redirectEnd")-netHash.get("redirectStart"))+ "ms"+System.lineSeparator());
	    	returnHash.put("redirect", (netHash.get("redirectEnd")-netHash.get("redirectStart")));
	    	qa.SeleniumTest.logger.info("DNS: "+(netHash.get("domainLookupEnd")-netHash.get("domainLookupStart"))+ "ms"+System.lineSeparator());
	    	returnHash.put("DNS", (netHash.get("domainLookupEnd")-netHash.get("domainLookupStart")));
	    	qa.SeleniumTest.logger.info("response: "+(netHash.get("responseEnd")-netHash.get("responseStart"))+ "ms"+System.lineSeparator());
	    	returnHash.put("response", (netHash.get("responseEnd")-netHash.get("responseStart")));
	    	qa.SeleniumTest.logger.info("processing: "+(netHash.get("domContentLoadedEventEnd")-netHash.get("domContentLoadedEventStart"))+ "ms"+System.lineSeparator());
	    	returnHash.put("processing", (netHash.get("domContentLoadedEventEnd")-netHash.get("domContentLoadedEventStart")));
	    	qa.SeleniumTest.logger.info("load: "+(netHash.get("loadEventEnd")-netHash.get("loadEventStart"))+ "ms"+System.lineSeparator());
	    	returnHash.put("load", (netHash.get("loadEventEnd")-netHash.get("loadEventStart")));
	    	qa.SeleniumTest.logger.info("request: "+(netHash.get("responseStart")-netHash.get("requestStart"))+ "ms"+System.lineSeparator());
	    	returnHash.put("request", (netHash.get("responseStart")-netHash.get("requestStart")));
	    	qa.SeleniumTest.logger.info("page load: "+(netHash.get("domComplete")-netHash.get("navigationStart"))+ "ms"+System.lineSeparator());
	    	returnHash.put("page load", (netHash.get("domComplete")-netHash.get("navigationStart")));
    	
	    	NetworkTool.setNetworkMap(returnHash);
    	}
    }    

    public void sendKeysJs(String keys, WebElement element) {
    	js.executeScript("arguments[0].setAttribute('value', '" + keys +"')", element);
    }
}
