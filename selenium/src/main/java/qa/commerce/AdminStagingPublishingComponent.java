package qa.commerce;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import qa.SeleniumTest;
import qa.events.BasePage;
import qa.utility.InterruptTool;
import qa.utility.WaitTool;

public class AdminStagingPublishingComponent extends BasePage {
	private final String uploadStatus = "?SHOW_STATUS=1";
	WaitTool wait;
	InterruptTool interrupt;

	public AdminStagingPublishingComponent(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub

		wait = new WaitTool(driver);
		interrupt = new InterruptTool(driver);
	}

	public void toPage() {
		// TODO Auto-generated method stub
		driver.navigate().to("http://admin.staging.bizjournals.com/event/calendar");
	}

	@Override
	public boolean atPage() {
		return pageTitleHeader().isDisplayed();
	}

	// --------------------------Elements-------------------------------------//

	private WebElement pageTitleHeader() {
		return driver.findElement(By.tagName("title"));
	}

	private WebElement digitalEditionLink() {
		return driver.findElement(By.id("globalnav-digital-edition"));
	}

	private List<WebElement> dashboardControls() {
		return driver.findElement(By.id("dashboard")).findElements(By.tagName("a"));
	}

	private List<WebElement> PDFTable() {
		return driver.findElement(By.className("container")).findElement(By.id("uploadStatusTable"))
				.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
	}

	// --------------------------Helpers-------------------------------------//

	public void verifyDigitalUploadStatus() {		
		boolean upload = true;
		digitalEditionLink().click();
		driver.get(driver.getCurrentUrl() + uploadStatus);
		dashboardControls().get(2).click();

		// go through upload status table to find corresponding market
		for (int j = 0; j < PDFTable().size(); j++) {
			if (PDFTable().get(j).findElements(By.tagName("td")).size() < 3) {					
				SeleniumTest.logger.info("did not find upload for " + PDFTable().get(j).findElements(By.tagName("td")).get(0).getText()
						+System.lineSeparator());
				upload = false;
				// break;
			}else{
				SeleniumTest.logger.info("found PDF upload for "+PDFTable().get(j).findElements(By.tagName("td")).get(0).getText()
						+System.lineSeparator());
			}			
		}	
		if(!upload){
			Assert.fail();
		}
	}
}
