package qa.cms;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.junit.Assert;
import qa.events.BasePage;
import qa.utility.WaitTool;

public class DesksComponent extends BasePage {
	WaitTool wait;

	public DesksComponent(WebDriver driver) {
		super(driver);
		wait = new WaitTool(driver);
	}

	@Override
	public boolean atPage() {
		return pageTitleHeader().isDisplayed();
	}

	// --------------------------Elements-------------------------------------//

	private WebElement pageTitleHeader() {
		return driver.findElement(By.tagName("title"));
	}
	
	private List<WebElement> contentList() {
		return driver.findElement(By.id("content-list")).findElements(By.tagName("tr"));
	}
	
	private WebElement actionsDropdown() {
		return driver.findElement(By.name("action"));
	}
	
	private WebElement publishAction(){
		for(int i=0;i<driver.findElement(By.name("action")).findElements(By.tagName("option")).size();i++){
			if(driver.findElement(By.name("action")).findElements(By.tagName("option")).get(i).getText().contains("Publish")){
				return driver.findElement(By.name("action")).findElements(By.tagName("option")).get(i);
			}
		}
		return null;
	}
	
	private WebElement publishButton() {
		return driver.findElement(By.className("control-group")).findElement(By.className("btn"));
	}
	
	private WebElement confirmPublish() {
		return driver.findElement(By.className("primary-actions-footer")).findElement(By.className("primary-actions")).findElement(By.tagName("button"));
	}
	
	// --------------------------Helpers-------------------------------------//

	/**
	 * Publish an article in the content list using the data from a
	 * provided hashMap. This method ensures that the article created
	 * with the 'addBizPulse' method in 'BizPulseComponent' is published, 
	 * provided the same HashMap is	used.
	 * 
	 * @param data - HashMap of test data provided by the test handler
	 */
	public void publishContent(HashMap<String, String> data) {
		for (int i = 0; i < contentList().size(); i++) {
			if (contentList().get(i).getText().contains(data.get("nspass"))) {
				contentList().get(i).findElements(By.tagName("td")).get(0).findElement(By.name("_selected_action")).click();
				//actionsDropdown().click();
				publishAction().click();
				publishButton().click();
				confirmPublish().click();
				if(driver.findElements(By.tagName("h4")).size()>0){
				Assert.assertTrue("Event was not published!",driver.findElement(By.tagName("h4")).getText().contains("has been published"));	
				}else{
					Assert.fail("Content was not published");
				}
				break;				
			}
		}
	}

	/**
	 * Publish an article to the content list using the name of the
	 * article.
	 * 
	 * @param bizPulse  - A string representing the name of the article to be published.
	 */
	public void publishContent(String bizPulse) {
		for (int i = 0; i < contentList().size(); i++) {
			if (contentList().get(i).getText().contains(bizPulse)) {
				contentList().get(i).findElements(By.tagName("td")).get(1).findElement(By.name("_selected_action")).click();
				actionsDropdown().click();
				publishAction().click();
				publishButton().click();
				confirmPublish().click();
			}
		}
	}

	/**
	 * Publish an BizPulse article to the content list using its numeric position
	 * in the list. This is a zero based list.
	 * 
	 * @param bizPulse
	 *            - int representing the article to be published.
	 */
	public void publishContent(int bizPulse) {
		contentList().get(bizPulse).findElements(By.tagName("td")).get(1).findElement(By.name("_selected_action")).click();
		actionsDropdown().click();
		publishAction().click();
		publishButton().click();
		confirmPublish().click();
	}
}
