package qa.cms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import qa.events.BasePage;
import qa.utility.WaitTool;

public class HomepagesComponent extends BasePage {

	WaitTool wait;

	public HomepagesComponent(WebDriver driver) {
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
		return driver.findElement(By.id("bulk-actions")).findElements(By.tagName("tr"));
	}

	// --------------------------Helpers-------------------------------------//

	/**
	 * 
	 * @param i
	 */
	public void getHomepage(int i) {
		contentList().get(i).findElements(By.tagName("td")).get(1).findElement(By.tagName("h3"))
				.findElement(By.tagName("a")).click();
	}

	/**
	 * 
	 * @param i
	 */
	public void getHomepage(String name) {
		for (int i = 0; i < contentList().size(); i++) {
			if (contentList().get(i).findElements(By.tagName("td")).get(1).findElement(By.tagName("h3")).getText()
					.contains(name)) {
				contentList().get(i).findElements(By.tagName("td")).get(1).findElement(By.tagName("h3"))
						.findElement(By.tagName("a")).click();
			}
		}

	}

}
