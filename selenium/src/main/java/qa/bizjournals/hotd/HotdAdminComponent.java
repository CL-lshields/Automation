package qa.bizjournals.hotd;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import qa.events.BasePage;
import qa.utility.WaitTool;

public class HotdAdminComponent extends BasePage{
	WaitTool wait;
	
	public HotdAdminComponent(WebDriver driver) {
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
	
	
	private List<WebElement> areListingsDisplayed(){
		
		return driver.findElements(By.className("row-fluid"));
	}
	
	//--------------------------Helpers-------------------------------------//
	
	public boolean areListings(){
		for(int i=0;i<areListingsDisplayed().size();i++){
			if(areListingsDisplayed().get(i).getText().contains("no listings")){
				return false;
			}
		}
		return true;
	}

}
