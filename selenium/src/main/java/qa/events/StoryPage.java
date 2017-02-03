package qa.events;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import qa.SeleniumTest;
import qa.utility.WaitTool;


public class StoryPage extends BasePage {
	WaitTool wait;

	public StoryPage(WebDriver driver) {
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

	private WebElement findListButton() {
		return driver.findElement(By.linkText("Find List"));
	}

	private WebElement verifyList() {
		return driver.findElement(By.className("media"))				
				.findElement(By.tagName("h5"));
			
	}

	@SuppressWarnings("unused")
	private WebElement selectList() {
		return driver.findElement(By.tagName("title"));
	}

	private WebElement savePublishStory() {
		return driver.findElement(By.className("primary-actions")).findElement(By.className("caret"));
	}
	
	private WebElement savePublishStoryConfirmation() {
		return driver.findElement(By.className("primary-actions")).findElement(By.tagName("button"));
	}
	
	private WebElement listModel(){
		return driver.findElement(By.id("list-list-modal"));
	}
	
	@SuppressWarnings("unused")
	private WebElement modelTable(){
		return driver.findElement(By.tagName("table"));
	}
	
	private WebElement UrlTracking(){
		return driver.findElement(By.className("input-prepend row-fluid")).findElement(By.className("btn-group"));
	}
	// --------------------------Helpers-------------------------------------//

	/**
	 * Clicks the Find List button on the page of an individual story if present,
	 * using the element specified in findListButton().
	 */
	public void clickFindList() {
		try {
			findListButton().click();
		} catch (UnhandledAlertException f) {
			SeleniumTest.logger.severe("alert may be interfering..."+f.getMessage()+System.lineSeparator());
			try {
				Alert alert = driver.switchTo().alert();
				String alertText = alert.getText();
				System.out.println("Alert data: " + alertText);
				alert.dismiss();
			} catch (NoAlertPresentException e) {
				e.printStackTrace();
				SeleniumTest.logger.severe(e.getMessage()+System.lineSeparator());
			}
		}		
	}
	
	/**
	 * Selects a list from the JS modal element on the page. The first element in the list 
	 * is always the one selected, using the select button.
	 */
	public void selectListFromModel(){
		wait.waitForElementToBeClickable(listModel(), 20);
		driver.findElement(By.cssSelector("html body.logged-in.nav__global.modal-on div#list-list-modal.modal.hide.span12.in div.modal-body div.row-fluid div.span9 div.modal-body__primary table.table.table-striped.table--select tbody tr td button.btn.btn-inverse.modal-finish")).click();
		//driver.findElement(By.xpath("/html/body/div[6]/div[2]/div/div[2]/div/table/tbody/tr[1]/td[4]/button")).click();
		//JavascriptLibrary jsLib = new JavascriptLibrary(); 
		//jsLib.callEmbeddedSelenium(driver,"triggerMouseEventAt", modelTable().findElements(By.tagName("tr")).get(0).findElements(By.tagName("td")).get(3),"click", "0,0");
		//modelTable().findElements(By.tagName("tr")).get(0).findElements(By.tagName("td")).get(3).click();
		wait.waitForVisibilityOf(verifyList(), 10);
		Assert.assertTrue("No Item added to List!", !verifyList().getText().isEmpty());
		SeleniumTest.logger.info("Modal selected from list..."+System.lineSeparator());
	}
	
	/**
	 * If the story is already published, this function will attempt to return its URL.	 * 
	 */
	public String returnArticleUrl(){
		UrlTracking().click();
		return driver.findElement(By.linkText("URL")).getAttribute("data-url");		
	}
	
	/**
	 * Publishes the story to save changes, or re-publishes if necessary.
	 */
	public void publish(){
		savePublishStory().click();
		driver.findElement(By.linkText("Publish")).click();
		wait.waitForElementToBeClickable(savePublishStoryConfirmation(), 10);
		SeleniumTest.logger.info("Publishing story..."+System.lineSeparator());
	}
}
