package qa;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;

import qa.bizintelligence.ReportsAnalysisComponent;
import qa.bizintelligence.SyndicationApiComponent;
import qa.bizjournals.AdminStagingMarketAttributes;
import qa.bizjournals.BizJournalsHeader;
import qa.bizjournals.NewslettersComponent;
import qa.bizjournals.SignInPage;
import qa.bizjournals.hotd.HodComponent;
import qa.bizjournals.hotd.HotdAdminComponent;
import qa.cms.BizPulseComponent;
import qa.cms.DesksComponent;
import qa.cms.LoginPage;
import qa.cms.Navigation;
import qa.commerce.AdminStagingPublishingComponent;
import qa.commerce.NewsArticleComponent;
import qa.commerce.NewsListingComponent;
import qa.commerce.PeopleOnTheMoveComponent;
import qa.commerce.PeopleOnTheMoveSubmission;
import qa.events.AdminCommerceComponent;
import qa.events.AdminStagingCalendar;
import qa.events.AdminStagingEventSearch;
import qa.events.AdminStagingEvents;
import qa.events.AdminStagingHeader;
import qa.events.AdminStagingLogin;
import qa.events.AdminStagingNominations;
import qa.events.CalendarListingComponent;
import qa.events.CheckoutComponent;
import qa.events.EventComponent;
import qa.events.EventRegistration;
import qa.events.NominationsComponent;
import qa.events.SquadUpComponent;
import qa.magento.MagentoCheckoutComponent;
import qa.services.EventsApiComponent;

/**
 * component factory is able to create instances of components for use in the modules
 * that drive the driver scripts found in test.java.qa.events
 * 
 * @author lshields
 *
 */

public class ComponentFactory {

private final WebDriver driver;
	
	public ComponentFactory(final WebDriver driver) {
		this.driver = driver;
	}
	
	public EventRegistration eventsRegistration(){
		return new EventRegistration(driver);
	}
	
	public BizJournalsHeader bizJournalsHeader(){
		return new BizJournalsHeader(driver);
	}
	
	public EventComponent eventComponent(){
		return new EventComponent(driver);
	}	
	
	public AdminCommerceComponent adminCommerceComponent(){
		return new AdminCommerceComponent(driver);
	}
	
	public AdminStagingLogin adminStagingLogin(){
		return new AdminStagingLogin(driver);
	}
	
	public AdminStagingNominations adminStagingNominations(){
		return new AdminStagingNominations(driver);
	}
	
	public AdminStagingHeader adminStagingHeader(){
		return new AdminStagingHeader(driver);
	}
	
	public AdminStagingEvents adminStagingEvents(){
		return new AdminStagingEvents(driver);
	}
	
	public AdminStagingCalendar adminStagingCalendar(){
		return new AdminStagingCalendar(driver);
	}
	
	public AdminStagingEventSearch adminStagingEventSearch() throws SQLException{
		return new AdminStagingEventSearch(driver);
	}
	
	public AdminStagingMarketAttributes adminStagingMarketAttributes(){
		return new AdminStagingMarketAttributes(driver);
	}
	
	public HodComponent hodComponent(){
		return new HodComponent(driver);
	}
	
	public HotdAdminComponent hotdAdminComponent(){
		return new HotdAdminComponent(driver);
	}

	public PeopleOnTheMoveComponent peopleOnTheMoveComponent(){
		return new PeopleOnTheMoveComponent (driver);
	}

	public PeopleOnTheMoveSubmission peopleOnTheMoveSubmission(){
		return new PeopleOnTheMoveSubmission(driver);
	}	

	public NewsArticleComponent newsArticleComponent(){
		return new NewsArticleComponent(driver);
	}

	public NewsListingComponent newsListingComponent (){
		return new NewsListingComponent(driver);
	}
	
	public CheckoutComponent checkoutComponent (){
		return new CheckoutComponent(driver);
	}
	
	public NominationsComponent nominationsComponent (){
		return new NominationsComponent(driver);
	}
	
	public SquadUpComponent squadUpComponent  (){
		return new SquadUpComponent (driver);
	}
	
	public SyndicationApiComponent syndicationApiComponent(){
		return new SyndicationApiComponent (driver);
	}
	
	//TODO - the navigation component in cms should be changed to fit convention
	// with the rest of the framework.	
	public Navigation cmsNavigationComponent(){
		return new Navigation(driver);
	}
	
	//TODO - the cmd Login component in cms should be changed to fit convention
	// with the rest of the framework.	
	public LoginPage cmsLoginComponent(){
		return new LoginPage(driver);
	}
	
	public BizPulseComponent bizPulseComonent(){
		return new BizPulseComponent(driver);
	}
	
	public DesksComponent desksComponent(){
		return new DesksComponent(driver);
	}
	
	public MagentoCheckoutComponent magentoCheckoutComponent(){
		return new MagentoCheckoutComponent(driver);
	}
	
	public AdminStagingPublishingComponent adminStagingPublishingComponent(){
		return new AdminStagingPublishingComponent(driver);
	}
	
	public CalendarListingComponent calendarListingComponent (){
		return new CalendarListingComponent(driver);
	}
	
	public SignInPage signInPage(){
		return new SignInPage(driver);
	}
	
	public NewslettersComponent newsletterscomponent(){
		return new NewslettersComponent(driver);
	}
	
	public ReportsAnalysisComponent reportAnalysisComponent(){
		return new ReportsAnalysisComponent(driver);
	}
	
//	public EventsApiComponent eventsApiComponent (){
//		return new EventsApiComponent (HashMap<String,String> data);
//	}
	
}

