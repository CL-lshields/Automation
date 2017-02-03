package qa.events.modules;

import org.openqa.selenium.WebDriver;

import qa.ComponentFactory;
import qa.utility.WaitTool;

public class BizJournalsResources {

	@SuppressWarnings("unused")
	private WebDriver driver;
	private ComponentFactory components;
	
	@SuppressWarnings("unused")
	private WaitTool waitTool;

	public BizJournalsResources(WebDriver driver) {
		this.driver = driver;
		components = new ComponentFactory(driver);
		waitTool = new WaitTool(driver);
	}

	public ComponentFactory getComponents() {
		return components;
	}	
}
