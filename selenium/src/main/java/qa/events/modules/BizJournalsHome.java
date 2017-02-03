package qa.events.modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import qa.ComponentFactory;
import qa.utility.WaitTool;

public class BizJournalsHome {
	
	private WebDriver driver;
	private ComponentFactory components;
	@SuppressWarnings("unused")
	private WaitTool waitTool;
	
	public BizJournalsHome(WebDriver driver) {
		this.driver = driver;
		components = new ComponentFactory(driver);		
		waitTool = new WaitTool(driver);
	}

	public ComponentFactory getComponents(){
		return components;
	}
	
	//navigate to events from the BizJournals homepage
	public void navigateToEvents() {	
		driver.findElement(By.cssSelector("html.ruby.drawer--layout.viewport--xs.js.flexbox.geolocation.history.csstransforms.csstransforms3d.csstransitions.localstorage.sessionstorage.svg.smil.csspositionsticky.viewport--sm.viewport--md.viewport--lg.viewport--xl.viewport--xl-only body main.container--is-skin-ad.container.container--has-skin-ad section.chunk div.container div.secondary div.module.module--ruled.module--stacked.hidden--xs.hidden--sm div.module__footer a.btn.btn--small.btn--link")).click();
//		for(int i=0;i<driver.findElements(By.className("module__footer")).size();i++){
//			if(driver.findElements(By.className("module__footer")).get(i).findElement(By.tagName("a")).getText().equalsIgnoreCase("see all events")){
//				//(new WebDriverWait(driver, 10))
//				//  .until(ExpectedConditions.elementToBeClickable(driver.findElements(By.tagName("strong")).get(i)));		
//				driver.findElements(By.className("module__footer")).get(i).findElement(By.tagName("a")).click();
//				break;
//			}
		}
	}


