package qa.cms;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import qa.SeleniumTest;
import qa.events.BasePage;
import qa.utility.WaitTool;

public class BizPulseComponent extends BasePage {
	WaitTool wait;

	public BizPulseComponent(WebDriver driver) {
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

	private WebElement addBizPulseButton() {
		return driver.findElement(By.className("content__header__actions")).findElement(By.tagName("a"));
	}

	private List<WebElement> contentList() {
		return driver.findElement(By.id("content-list")).findElements(By.tagName("tr"));
	}

	// Add biz pulse elements

	private WebElement pulseTypeDropdown() {
		return driver.findElement(By.name("pulse_type"));
	}

	private WebElement pulseTitleField() {
		return driver.findElement(By.name("title"));
	}

	private WebElement pulseDescriptionField() {
		return driver.findElement(By.name("description"));
	}

	private WebElement pulseStartDateField() {
		return driver.findElement(By.name("start_date"));
	}

	private WebElement pulseEndDateField() {
		return driver.findElement(By.name("end_date"));
	}

	private WebElement pulseAnswersField() {
		return driver.findElement(By.id("pulse-questions")).findElements(By.className("js-maxlength--initialized")).get(1);		
	}
	
	private WebElement pulseAnswersAddButton() {
		return driver.findElement(By.className("add-answer"));		
	}	
	
	private WebElement saveButton() {
		return driver.findElement(By.className("btn-save")).findElement(By.tagName("Button"));
	}

	private WebElement deleteButton() {
		return driver.findElement(By.linkText("Delete"));
	}

	private WebElement unpublishButton() {
		return driver.findElement(By.linkText("Unpublish"));
	}
	
	

	// --------------------------Helpers-------------------------------------//

	/**
	 * Add a BizPulse article to the content list using the data from a provided
	 * HashMap.
	 * 
	 * @param data
	 *            - HashMap of test data provided by the test handler.
	 */
	public void addBizPulse(HashMap<String, String> data) {
		addBizPulseButton().click();
		pulseTypeDropdown().findElements(By.tagName("option")).get(1).click();		
		pulseTitleField().sendKeys(data.get("nspass"));
		pulseDescriptionField().sendKeys(data.get("client"));

		// get current date
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		Date date = new Date();
		c.setTime(date);
		pulseStartDateField().clear();
		pulseStartDateField().sendKeys(dateFormat.format(c.getTime()));		
		c.add(Calendar.DATE, 2);
		pulseEndDateField().clear();
		pulseEndDateField().sendKeys(dateFormat.format(c.getTime()));
		
		//poll answeres
		pulseAnswersField().sendKeys(data.get("client"));
		pulseAnswersAddButton().click();
		saveButton().click();
	}

	/**
	 * Remove a BizPulse article to the content list using the data from a
	 * provided hashMap. This method ensures that the BizPulse article created
	 * with the 'addBizPulse' method is removed, provided the same HashMap is
	 * used.
	 * 
	 * @param data - HashMap of test data provided by the test handler
	 */
	public void removeBizPulse(HashMap<String, String> data) {
		for (int i = 0; i < contentList().size(); i++) {
			if (contentList().get(i).getText().contains(data.get("nspass"))) {
				contentList().get(i).findElements(By.tagName("td")).get(2).findElement(By.tagName("h3"))
						.findElement(By.tagName("a")).click();
				try{
					unpublishButton().click();
					Alert alert = driver.switchTo().alert();
					alert.accept();
				}catch(NoSuchElementException ex){
					SeleniumTest.logger.warning("Event was not published..."+System.lineSeparator());
				}
				deleteButton().click();
				Alert alert = driver.switchTo().alert();
				alert.accept();
			}
		}
	}

	/**
	 * Remove a BizPulse article to the content list using the name of the
	 * article.
	 * 
	 * @param bizPulse  - A string representing the name of the article to be removed.
	 */
	public void removeBizPulse(String bizPulse) {
		for (int i = 0; i < contentList().size(); i++) {
			if (contentList().get(i).getText().contains(bizPulse)) {
				contentList().get(i).findElements(By.tagName("td")).get(2).findElement(By.tagName("h3"))
						.findElement(By.tagName("a")).click();
				deleteButton().click();
				Alert alert = driver.switchTo().alert();
				alert.accept();
			}
		}
	}

	/**
	 * Remove a BizPulse article to the content list using its numeric position
	 * in the list. This is a zero based list.
	 * 
	 * @param bizPulse
	 *            - int representing the article to be removed.
	 */
	public void removeBizPulse(int bizPulse) {
		contentList().get(bizPulse).findElements(By.tagName("td")).get(2).findElement(By.tagName("h3"))
				.findElement(By.tagName("a")).click();
		deleteButton().click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	/**
	 * Send a BizPulse article to the publish desk. This method ensures that the BizPulse article sent
	 * is the same article 'addBizPulse' created, provided the same HashMap is
	 * used.
	 * 
	 * @param data - HashMap of test data provided by the test handler.
	 */
	public void sendToPublish(HashMap<String, String> data) {
		for (int i = 0; i < contentList().size(); i++) {
			if (contentList().get(i).getText().contains(data.get("nspass"))) {
				Actions actions = new Actions(driver);
				WebElement sendTo = contentList().get(i).findElements(By.tagName("td")).get(7);
				//sendTo.findElement(By.className("dropdown-toggle")).click();
				sendTo.findElement(By.className("dropdown-toggle")).sendKeys(Keys.ENTER);
				actions.moveToElement(sendTo.findElement(By.className("dropdown-submenu")));
				actions.moveToElement(
						sendTo.findElement(By.className("dropdown-submenu")).findElements(By.tagName("li")).get(1));
				actions.perform();
				sendTo.findElement(By.className("dropdown-submenu")).findElements(By.tagName("li")).get(1).click();
			}
		}
	}
	
	/**
	 * Send a BizPulse article to the publish desk using the name of the article.
	 * 
	 * @param bizPulse -  string representing the name of the article to be sent
	 */
	public void sendToPublish(String bizPulse) {
		for (int i = 0; i < contentList().size(); i++) {
			if (contentList().get(i).getText().contains(bizPulse)) {
				Actions actions = new Actions(driver);
				WebElement sendTo = contentList().get(i).findElements(By.tagName("td")).get(7);
				sendTo.findElement(By.className("dropdown-toggle")).click();
				actions.moveToElement(sendTo.findElement(By.className("dropdown-submenu")));
				actions.moveToElement(
						sendTo.findElement(By.className("dropdown-submenu")).findElements(By.tagName("li")).get(1));
				actions.perform();
				sendTo.findElement(By.className("dropdown-submenu")).findElements(By.tagName("li")).get(1).click();
			}
		}
	}

	/**
	 * Send a BizPulse article to the Publish Desk using its numeric position
	 * in the list. This is a zero based list.
	 * 
	 * @param bizPulse - int representing the article to be sent.
	 */
	public void sendToPublish(int bizPulse) {
		Actions actions = new Actions(driver);
		WebElement sendTo = contentList().get(bizPulse).findElements(By.tagName("td")).get(7);
		sendTo.findElement(By.className("dropdown-toggle")).click();
		actions.moveToElement(sendTo.findElement(By.className("dropdown-submenu")));
		actions.moveToElement(
				sendTo.findElement(By.className("dropdown-submenu")).findElements(By.tagName("li")).get(1));
		actions.perform();
		sendTo.findElement(By.className("dropdown-submenu")).findElements(By.tagName("li")).get(1).click();
	}

}
