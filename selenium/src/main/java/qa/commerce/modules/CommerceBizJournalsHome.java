package qa.commerce.modules;

import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import qa.ComponentFactory;
import qa.utility.InterruptTool;
import qa.utility.WaitTool;

/**
 * @author Lance Shields <lsheilds@bizjournals.com>
 */
public class CommerceBizJournalsHome {

    private WebDriver driver;
    private ComponentFactory components;
    private WaitTool waitTool;
    private InterruptTool interruptTool;

    public CommerceBizJournalsHome(WebDriver driver) {
        this.driver = driver;
        components = new ComponentFactory(driver);
        waitTool = new WaitTool(driver);
        interruptTool = new InterruptTool(driver);
    }

    public qa.ComponentFactory getComponents() {
        return components;
    }

    /**
     * Navigate to the maz reader from the BizJournals homepage.
     * If not signed in, user should encounter a paywall.
     */
    public boolean weeklyEdition(){
    	waitTool.waitForJs(driver);              
        interruptTool.scrollIntoView(driver.findElement(By.className("item--featured")));
        waitTool.waitForPresenceOf(By.id("digital_edition_link"));   
        driver.get(driver.findElement(By.id("digital_edition_link")).getAttribute("href"));     
        if(driver.findElement(By.id("readerContentPlay")).getCssValue("display").equalsIgnoreCase("none")){
            qa.SeleniumTest.logger.info("Paywall found, sign in required..."+System.lineSeparator());
            return false;
        }else {
            Assert.assertTrue("maz reader may not be displayed.", driver.findElement(By.id("readerPaywall")).getCssValue("display").equalsIgnoreCase("none"));
            qa.SeleniumTest.logger.info("Navigating to weekly edition..." + System.lineSeparator());
            return true;
        }
    }

    public void handleInterstitial(){
//        if(waitTool.waitForPresenceOf(By.className("tip-enter"))){
//            waitTool.waitForElementToBeClickable(driver.findElement(By.className("tip-enter")), 10);
//            driver.findElement(By.className("tip-enter")).click();
//            waitTool.waitForPresenceOf(By.className("header"));
//            qa.SeleniumTest.logger.info("succesfully handled interstitial..." + System.lineSeparator());
//        }else{
//            qa.SeleniumTest.logger.info("no interstitial found..." + System.lineSeparator());
//        }
        interruptTool.handleInterstitial();
    }
    
    public void subscriberContent(){
    
  }
}
