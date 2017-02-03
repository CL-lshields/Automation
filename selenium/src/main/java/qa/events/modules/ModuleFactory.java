package qa.events.modules;

import org.openqa.selenium.WebDriver;

import qa.bizintelligence.modules.BizIntelligenceAdmin;
import qa.commerce.modules.*;
import qa.magento.modules.MagentoHome;
import qa.services.modules.ServicesEventsApi;

/**
 * module factory is able to create instances of modules for use in test navigation through
 * Driver scripts found in test.java.qa.events
 * 
 * @author lshields
 *
 */

public class ModuleFactory {
	
	private final WebDriver driver;
	
	public ModuleFactory(final WebDriver driver) {
		this.driver = driver;
	}

	public BizJournalsHome bizJournalHome(){
		return new BizJournalsHome(driver);
	}
	
	public BizJournalsEvents bizJournalEvents(){
		return new BizJournalsEvents(driver);
	}
	
	public BizJournalsNominations bizJournalNominations(){
		return new BizJournalsNominations(driver);
	}
	
	public BizJournalsResources bizJournalResources(){
		return new BizJournalsResources(driver);
	}

	public CommerceBizJournalsHome commerceBizjournalsHome(){
		return new CommerceBizJournalsHome(driver);
	}

	public CommerceBizJournalsNews commerceBizJournalsNews(){
		return new CommerceBizJournalsNews(driver);
	}
	
	public BizIntelligenceAdmin bizIntelligenceAdmin(){
		return new BizIntelligenceAdmin(driver);
	}
	
	public MagentoHome magentoHome(){
		return new MagentoHome(driver);
	}
	
	public ServicesEventsApi servicesEventsApi(){
		return new ServicesEventsApi(driver);
	}

}
