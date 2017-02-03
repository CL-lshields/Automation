package qa.bizjournals;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import qa.SeleniumTest;
import qa.events.AdminStagingHeader;
import qa.events.BasePage;
import qa.utility.InterruptTool;
import qa.utility.WaitTool;

/**
 * 
 * @author lshields
 *
 */

public class AdminStagingMarketAttributes extends BasePage {
	WaitTool wait;
	InterruptTool interrupt;
	String promo;

	public AdminStagingMarketAttributes(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		wait = new WaitTool(driver);
		interrupt = new InterruptTool(driver);
	}

	@Override
	public boolean atPage() {
		return pageTitleHeader().isDisplayed();
	}

	public String getPromo() {
		return promo = driver.findElement(By.id("promocodes")).findElements(By.tagName("tr")).get(0)
				.findElements(By.tagName("td")).get(0).findElement(By.tagName("p")).getText();
	}

	// --------------------------Elements-------------------------------------//
	
	private WebElement pageTitleHeader() {
		return driver.findElement(By.tagName("title"));
	}
	
	private List<WebElement> siteSectionTable(){
		return driver.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
	}
	
	private List<WebElement> marketAttributeTable(){
		return driver.findElement(By.id("market-attribute-list")).findElements(By.tagName("li"));
	}
	
	private List<WebElement> sectionAttributeTable(){
		return driver.findElement(By.id("section-attribute-list")).findElements(By.tagName("li"));
	}
	
	private WebElement editAttrButton() {
		return driver.findElement(By.id("market-attribute-edit"));
	}	
	
	private WebElement editSectionButton() {
		return driver.findElement(By.id("section-attribute-edit"));
	}	
	
	private WebElement attrValueField() {
		return driver.findElement(By.xpath("(//textarea[@id='attribute_value'])[2]"));
	}
	
	private WebElement attrSaveButton() {
		return driver.findElement(By.xpath("(//button[@type='button'])[3]"));
		
	}
	
	private List<WebElement> sectionList(){
		return driver.findElement(By.id("section-list")).findElements(By.tagName("a"));
	}
	
	
	// --------------------------Helpers------------------------------------//
	
	/**
	 * manage the market attributes for a specific market. These can be found under the Markets/Journals
	 * tab of the configuration menu in the admin staging environment. These are different from managing 
	 * site sections. Site sections should be managed using the manageSections() method
	 * 
	 * @param market - the market whose attributes you wish to manage.
	 */
	public void manageMarket(String market){
		if(market.equals("random")){
			market = AdminStagingHeader.randomMarket;
		}

		for(int i=0;i<siteSectionTable().size();i++){
			if(siteSectionTable().get(i).getText().contains(market)){
				siteSectionTable().get(i).findElement(By.linkText("Manage Market")).click();
				SeleniumTest.logger.info("managing market attributes for "+ market+ System.lineSeparator());
				break;
			}
		}		
	}
	
	/**
	 * Manage the site sections of a particular market. These can be found in the Site/Sections tab of 
	 * the configuration menu in the admin staging environment. These are different from managing market 
	 * attributes. Market attributes should be managed using the manageMarket() method.
	 * @param section
	 */
	
	public void manageSection(String env, String market){
		if(market.equals("random")){
			market = AdminStagingHeader.randomMarket;
		}
		
		//select environment
		for(int i=0;i<driver.findElements(By.tagName("a")).size();i++){
			if (driver.findElements(By.tagName("a")).get(i).getText().equals(env)){
				driver.findElements(By.tagName("a")).get(i).click();
			}
		}
		
		//select market and manage attributes
		for(int i=0;i<sectionList().size();i++){
			if(sectionList().get(i).getText().contains(market.toLowerCase())){
				sectionList().get(i).click();
				SeleniumTest.logger.info("managing sections for " + market+ System.lineSeparator());
				break;
			}
		}		
	}

    public void editMarketAttribute(String attr, String value){
    	boolean set = false;
    	for(int i=0;i<marketAttributeTable().size();i++){
			if(marketAttributeTable().get(i).getText().contains(attr)){
				marketAttributeTable().get(i).click();
				editAttrButton().click();				
				attrValueField().clear();
				attrValueField().sendKeys(value);
				interrupt.jsClick(attrSaveButton());
				SeleniumTest.logger.info(attr+" set to "+value + System.lineSeparator());
				set = true;
				break;
			}
		}	 
    	
    	//fail if attribute wasnt set
    	if(!set){
    	    SeleniumTest.logger.severe("Section Attribute not found!"+ System.lineSeparator());
        	Assert.fail("Section Attribute not found!");
    	}
	}
    
    public void editSectionAttribute(String attr, String value){ 
    	boolean set = false;
    	for(int i=0;i<sectionAttributeTable().size();i++){
			if(sectionAttributeTable().get(i).getText().contains(attr)){
				sectionAttributeTable().get(i).click();
				editSectionButton().click();				
				attrValueField().clear();
				attrValueField().sendKeys(value);
				interrupt.jsClick(attrSaveButton());
				SeleniumTest.logger.info(attr+" set to "+value + System.lineSeparator());
				set = true;
				break;
			}
		}
    	
    	//fail if attribute wasnt set
    	if(!set){
    	    SeleniumTest.logger.severe("Section Attribute not found!"+ System.lineSeparator());
        	Assert.fail("Section Attribute not found!");
    	}
	}
}
