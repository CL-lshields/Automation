package qa.services.modules;

import org.openqa.selenium.WebDriver;

import qa.ComponentFactory;
import qa.utility.WaitTool;

public class ServicesEventsApi {
	
	private WebDriver driver;
	private ComponentFactory components;	

	public ServicesEventsApi(WebDriver driver) {
		this.driver = driver;
		components = new ComponentFactory(driver);		
	}

	public ComponentFactory getComponents() {
		return components;
	}

}
