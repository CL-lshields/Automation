package qa.bizintelligence.modules;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import qa.ComponentFactory;
import qa.SeleniumTest;
import qa.utility.InterruptTool;
import qa.utility.WaitTool;

public class BizIntelligenceAdmin {
	public static final String rss = "RSS 2.0";
	public static final String newsMl = "NewsML-G2 Package 2.7 w/ NITF 3.5";
	public static final String newsMlLegacy = "NewsML-G2 2.7 w/ NITF 3.5 [Legacy]";
	public static final String newsMlXhtml = "NewsML 1.3 w/ XHTML";
	public static final String json = "JSON";
	public static final String facebook = "Facebook Instant Article RSS";
	
	private WebDriver driver;
    private ComponentFactory components;
    private WaitTool waitTool;
    @SuppressWarnings("unused")
	private InterruptTool interruptTool;

    public BizIntelligenceAdmin(WebDriver driver) {
        this.driver = driver;
        components = new ComponentFactory(driver);
        waitTool = new WaitTool(driver);
        interruptTool = new InterruptTool(driver);
    }

    public qa.ComponentFactory getComponents() {
        return components;
    }
    
    /**
     * Select third tab in admin stagin header
     */
    public void selectMyPage(String tab){
    	Assert.assertTrue("Could not find specified tab!",waitTool.waitForPresenceOf(By.linkText(tab)));
    	driver.findElement(By.linkText(tab)).click();	
    	SeleniumTest.logger.info("navigated to "+tab+" in admin staging header..."+System.lineSeparator());
    }
}
