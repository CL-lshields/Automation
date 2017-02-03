/**
 * 
 */
package qa.upstart;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * @author Bob Eksten <reksten@bizjournals.com>
 *
 */
public class UpstartMainNav {

	@FindBy(how = How.CSS, using="Homepage")
	private WebElement mainNavHomepageLink;
	
	@FindBy(how = How.LINK_TEXT, using="News")
	private WebElement mainNavNewsLink;

	@FindBy(how = How.LINK_TEXT, using="News Wire")
	private WebElement mainNavNewsWireLink;
	
	@FindBy(how = How.LINK_TEXT, using="Technology")
	private WebElement mainNavTechnologyLink;
	
	@FindBy(how = How.LINK_TEXT, using="Lifestyle")
	private WebElement mainNavLifestyleLink;
	
	@FindBy(how = How.LINK_TEXT, using="Polls")
	private WebElement mainNavPollsLink;
	
	@FindBy(how = How.LINK_TEXT, using="Business Travel")
	private WebElement mainNavBusinessTravelLink;
	
	@FindBy(how = How.LINK_TEXT, using="Washington, DC")
	private WebElement mainNavWasingtonDCLink;	

	@FindBy(how = How.LINK_TEXT, using="Entrepreneurs")
	private WebElement mainNavEntrepreneursLink;

	@FindBy(how = How.LINK_TEXT, using="Hot Shots")
	private WebElement mainNavHotShotsLink;
	
	@FindBy(how = How.LINK_TEXT, using="My Great Mistake")
	private WebElement mainNavMyGreatMistakeLink;
	
	@FindBy(how = How.LINK_TEXT, using="Millenial Matters")
	private WebElement mainNavMillenialMattersLink;
	
	@FindBy(how = How.LINK_TEXT, using="First Year")
	private WebElement mainNavFirstYearLink;
	
	@FindBy(how = How.LINK_TEXT, using="Beginners to Big Shots")
	private WebElement mainNavBeginnersToBigShotsLink;
	
	@FindBy(how = How.LINK_TEXT, using="Companies")
	private WebElement mainNavCompaniesLink;

	@FindBy(how = How.LINK_TEXT, using="Startups")
	private WebElement mainNavStartupsLink;
	
	@FindBy(how = How.LINK_TEXT, using="Rebel Brands")
	private WebElement mainNavRebelBrandsLink;
	
	@FindBy(how = How.LINK_TEXT, using="Hatched")
	private WebElement mainNavHatchedLink;
	
	@FindBy(how = How.LINK_TEXT, using="Media")
	private WebElement mainNavMediaLink;
	
	@FindBy(how = How.LINK_TEXT, using="Innovation")
	private WebElement mainNavInnovationLink;
	
	@FindBy(how = How.LINK_TEXT, using="Money")
	private WebElement mainNavMoneyLink;

	@FindBy(how = How.LINK_TEXT, using="Strapped")
	private WebElement mainNavStrappedLink;
	
	@FindBy(how = How.LINK_TEXT, using="Loot")
	private WebElement mainNavLootLink;

	@FindBy(how = How.LINK_TEXT, using="Resources")
	private WebElement mainNavResourcesLink;

	@FindBy(how = How.LINK_TEXT, using="Ask Shama")
	private WebElement mainNavAskShamaLink;
	
	@FindBy(how = How.LINK_TEXT, using="Executive Forum")
	private WebElement mainNavExecutiveForumLink;
	
	@FindBy(how = How.LINK_TEXT, using="Author,Author")
	private WebElement mainNavAuthorAuthorLink;
	
	@FindBy(how = How.LINK_TEXT, using="Social Media")
	private WebElement mainNavSocialMediaLink;
	
	@FindBy(how = How.LINK_TEXT, using="Advice")
	private WebElement mainNavAdviceLink;
	
	@FindBy(how = How.LINK_TEXT, using="How-To")
	private WebElement mainNavHowToLink;
	
	@FindBy(how = How.LINK_TEXT, using="Jobs")
	private WebElement mainNavJobsLink;
	
	@FindBy(how = How.LINK_TEXT, using="Multimedia")
	private WebElement mainNavMultimediaLink;

	@FindBy(how = How.LINK_TEXT, using="Interactives")
	private WebElement mainNavInteractivesLink;
	
	@FindBy(how = How.LINK_TEXT, using="Slideshows")
	private WebElement mainNavSlideshowsLink;
	
	@FindBy(how = How.LINK_TEXT, using="Videos")
	private WebElement mainNavVideosLink;

	/**
	 * @return the mainNavHomepageLink
	 */
	public WebElement getMainNavHomepageLink() {
		return mainNavHomepageLink;
	}

	/**
	 * @return the mainNavNewsLink
	 */
	public WebElement getMainNavNewsLink() {
		return mainNavNewsLink;
	}

	/**
	 * @return the mainNavNewsWireLink
	 */
	public WebElement getMainNavNewsWireLink() {
		return mainNavNewsWireLink;
	}

	/**
	 * @return the mainNavTechnologyLink
	 */
	public WebElement getMainNavTechnologyLink() {
		return mainNavTechnologyLink;
	}

	/**
	 * @return the mainNavLifestyleLink
	 */
	public WebElement getMainNavLifestyleLink() {
		return mainNavLifestyleLink;
	}

	/**
	 * @return the mainNavPollsLink
	 */
	public WebElement getMainNavPollsLink() {
		return mainNavPollsLink;
	}

	/**
	 * @return the mainNavBusinessTravelLink
	 */
	public WebElement getMainNavBusinessTravelLink() {
		return mainNavBusinessTravelLink;
	}

	/**
	 * @return the mainNavWasingtonDCLink
	 */
	public WebElement getMainNavWasingtonDCLink() {
		return mainNavWasingtonDCLink;
	}

	/**
	 * @return the mainNavEntrepreneursLink
	 */
	public WebElement getMainNavEntrepreneursLink() {
		return mainNavEntrepreneursLink;
	}

	/**
	 * @return the mainNavHotShotsLink
	 */
	public WebElement getMainNavHotShotsLink() {
		return mainNavHotShotsLink;
	}

	/**
	 * @return the mainNavMyGreatMistakeLink
	 */
	public WebElement getMainNavMyGreatMistakeLink() {
		return mainNavMyGreatMistakeLink;
	}

	/**
	 * @return the mainNavMillenialMattersLink
	 */
	public WebElement getMainNavMillenialMattersLink() {
		return mainNavMillenialMattersLink;
	}

	/**
	 * @return the mainNavFirstYearLink
	 */
	public WebElement getMainNavFirstYearLink() {
		return mainNavFirstYearLink;
	}

	/**
	 * @return the mainNavBeginnersToBigShotsLink
	 */
	public WebElement getMainNavBeginnersToBigShotsLink() {
		return mainNavBeginnersToBigShotsLink;
	}

	/**
	 * @return the mainNavCompaniesLink
	 */
	public WebElement getMainNavCompaniesLink() {
		return mainNavCompaniesLink;
	}

	/**
	 * @return the mainNavStartupsLink
	 */
	public WebElement getMainNavStartupsLink() {
		return mainNavStartupsLink;
	}

	/**
	 * @return the mainNavRebelBrandsLink
	 */
	public WebElement getMainNavRebelBrandsLink() {
		return mainNavRebelBrandsLink;
	}

	/**
	 * @return the mainNavHatchedLink
	 */
	public WebElement getMainNavHatchedLink() {
		return mainNavHatchedLink;
	}

	/**
	 * @return the mainNavMediaLink
	 */
	public WebElement getMainNavMediaLink() {
		return mainNavMediaLink;
	}

	/**
	 * @return the mainNavInnovationLink
	 */
	public WebElement getMainNavInnovationLink() {
		return mainNavInnovationLink;
	}

	/**
	 * @return the mainNavMoneyLink
	 */
	public WebElement getMainNavMoneyLink() {
		return mainNavMoneyLink;
	}

	/**
	 * @return the mainNavStrappedLink
	 */
	public WebElement getMainNavStrappedLink() {
		return mainNavStrappedLink;
	}

	/**
	 * @return the mainNavLootLink
	 */
	public WebElement getMainNavLootLink() {
		return mainNavLootLink;
	}

	/**
	 * @return the mainNavResourcesLink
	 */
	public WebElement getMainNavResourcesLink() {
		return mainNavResourcesLink;
	}

	/**
	 * @return the mainNavAskShamaLink
	 */
	public WebElement getMainNavAskShamaLink() {
		return mainNavAskShamaLink;
	}

	/**
	 * @return the mainNavExecutiveForumLink
	 */
	public WebElement getMainNavExecutiveForumLink() {
		return mainNavExecutiveForumLink;
	}

	/**
	 * @return the mainNavAuthorAuthorLink
	 */
	public WebElement getMainNavAuthorAuthorLink() {
		return mainNavAuthorAuthorLink;
	}

	/**
	 * @return the mainNavSocialMediaLink
	 */
	public WebElement getMainNavSocialMediaLink() {
		return mainNavSocialMediaLink;
	}

	/**
	 * @return the mainNavAdviceLink
	 */
	public WebElement getMainNavAdviceLink() {
		return mainNavAdviceLink;
	}

	/**
	 * @return the mainNavHowToLink
	 */
	public WebElement getMainNavHowToLink() {
		return mainNavHowToLink;
	}

	/**
	 * @return the mainNavJobsLink
	 */
	public WebElement getMainNavJobsLink() {
		return mainNavJobsLink;
	}

	/**
	 * @return the mainNavMultimediaLink
	 */
	public WebElement getMainNavMultimediaLink() {
		return mainNavMultimediaLink;
	}

	/**
	 * @return the mainNavInteractivesLink
	 */
	public WebElement getMainNavInteractivesLink() {
		return mainNavInteractivesLink;
	}

	/**
	 * @return the mainNavSlideshowsLink
	 */
	public WebElement getMainNavSlideshowsLink() {
		return mainNavSlideshowsLink;
	}

	/**
	 * @return the mainNavVideosLink
	 */
	public WebElement getMainNavVideosLink() {
		return mainNavVideosLink;
	}
	
}