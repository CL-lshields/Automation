package qa.bizjournals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import qa.utility.InterruptTool;
import qa.utility.WaitTool;
import qa.utility.bizjdb.DatabaseTool;

public class NewslettersComponent extends qa.events.BasePage {
	WaitTool wait;
	InterruptTool interrupt;
	DatabaseTool databaseTool;

	public NewslettersComponent (WebDriver driver) {
		super(driver);
		
		// TODO Auto-generated constructor stub
		wait = new WaitTool(driver);
		interrupt = new InterruptTool(driver);			
	}

	@Override
	public boolean atPage() {
		return pageTitleHeader().isDisplayed();
	}
	

	// --------------------------Elements-------------------------------------//

	private WebElement pageTitleHeader() {
		return driver.findElement(By.tagName("title"));
	}
	
	private WebElement unsubscribeLink() {
		return driver.findElement(By.className("row--flex")).findElement(By.className("js-newsletters__action"));
	}
	
	private List<WebElement> unsubscribeList() {
		return driver.findElements(By.className("js-newsletter"));
	}
	
	// --------------------------Elements-------------------------------------//
	
	public void unsubscribe(int i){
		unsubscribeList().get(i).click();
	}
	
    public void unsubscribeFromAll(){
    	unsubscribeLink().click();
	}
}
