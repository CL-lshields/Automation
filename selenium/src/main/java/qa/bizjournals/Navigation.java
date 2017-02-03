package qa.bizjournals;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import qa.BasePage;
import qa.constants.SeleniumConstants;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Navigation extends BasePage {
	
	private Random rand = new Random();
	
	/**
	 * LOGO AND INDUSTRIES
	 */
	@FindBy(how = How.CSS, using="div.select-city button")
	private WebElement marketSelectButton;
	
	@FindBy(how = How.CSS, using="div.select-city ul.select-city__link-list")
	private WebElement marketSelectOptions;
	
	@FindBy(how = How.CSS, using="a.logo-link")
	private WebElement logo;
	
	@FindBy(how = How.CSS, using="nav.industries-nav button")
	private WebElement industriesAndTopicsNav;
	
	@FindBy(how = How.CSS, using="nav.industries-nav div.subnav__industries")
	private WebElement industriesAndTopicsSubmenu;
	
	@FindBy(how = How.CSS, using="nav.industries-nav div.subnav__highlights")
	private WebElement featuredIndustriesSubmenu;	
	
	@FindBy(how = How.CSS, using="nav.industries-nav div.subnav__highlights ul.link-list li")
	private List<WebElement> featuredIndustriesLinks;
	
	
	/**
	 * BANNER AND NAV
	 */
	@FindBy(how = How.CSS, using="div.banner-and-nav ul.nav-list")
	private WebElement navBar;
	
	@FindBy(how = How.CSS, using="a.nav-trigger i.icon-home")
	private WebElement homeLink;
	
	@FindBy(how = How.CSS, using="div.banner-and-nav a[data-ct=\"2016 Nav : Subscribe Now header : Subscription\"]")
	private WebElement subscribeNowLink;
	
	@FindBy(how = How.CSS, using="div.banner-and-nav a[data-ct=\"2016 Nav : Subscribe Now header : Newsletter\"]")
	private WebElement digitalOnlyNewsletterAndAlertsLink;
	
	@FindBy(how = How.CSS, using="div#accountMenu>a")
	private WebElement userAccountMenuLink;
	
	@FindBy(how = How.CSS, using="div#accountMenu div.dropdown__target")
	private WebElement userAccountMenuContainer;
	
	@FindBy(how = How.CSS, using="div#accountMenu div.dropdown__target div#accountLoggedIn")
	private WebElement loggedInUserAccountMenu;
	
	@FindBy(how = How.CSS, using="div#accountMenu div.dropdown__target div#accountLoggedOut")
	private WebElement loggedOutUserAccountMenu;
	
	@FindBy(how = How.CSS, using="div#accountLoggedOut li[data-ct=\"2016 Nav : Account Anonymous User : Sign In\"] a")
	private WebElement signInLink;
	
	@FindBy(how = How.CSS, using="div#accountLoggedOut li[data-ct=\"2016 Nav : Account Anonymous User : Create Your FREE Account\"] a")
	private WebElement createFreeAccountLink;
	
	@FindBy(how = How.CSS, using="div.banner-and-nav button.site-search-trigger")
	private WebElement siteSearchTrigger;
	
	@FindBy(how = How.ID, using="search")
	private WebElement searchForm;
	
	@FindBy(how = How.CSS, using="form#search input.site-search__input")
	private WebElement searchInput;	
	
	/**
	 * Default constructor
	 * 
	 * @param driver
	 */
	public Navigation(WebDriver driver) {
		super(driver);
		waitTool.waitForVisibilityOf(navBar, SeleniumConstants.MAX_TIMEOUT);
	}
	
	/**
	 * Validate the required elements that define the page
	 * 
	 * @return true if the page is valid, false otherwise
	 */
	public Boolean pageIsValid() {
		return true;
	}

	public void selectMarket(String marketName) {
		marketSelectButton.click();
		
		// wait for subnav to load
		waitTool.waitForVisibilityOf(marketSelectOptions, SeleniumConstants.MAX_TIMEOUT);
		
		// click link by name
		WebElement marketLink = marketSelectOptions.findElement(By.linkText(marketName));
		marketLink.click();
		
	}
	
	public void clickLogo() {
		logo.click();
	}
	
	public void clickHome() {
		homeLink.click();
	}
	
	public void clickSubscribeNowLink() {
		subscribeNowLink.click();
	}
	
	public void clickNewslettersAndAlertsLink() {
		digitalOnlyNewsletterAndAlertsLink.click();
	}
	
	public void openUserAccountMenu() {
		userAccountMenuLink.click();
		wait.until(ExpectedConditions.visibilityOf(userAccountMenuContainer));
	}
	
	public SignInPage clickToSignIn() {
		openUserAccountMenu();
		signInLink.click();
		
		return new SignInPage(driver);
	}
	
	public Boolean isUserLoggedIn() {
		// we have to wait a second for javascript to update user account display
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		openUserAccountMenu();
		return loggedInUserAccountMenu.isDisplayed();		
	}
	
	public CreateAccountPage openCreateFreeAccountPage() {
		createFreeAccountLink.click();
		return new CreateAccountPage(driver);
	}

	public void openIndustriesAndTopicsSubmenu() {
		hoverOverElement(industriesAndTopicsNav);
		waitTool.waitForVisibilityOf(industriesAndTopicsSubmenu, SeleniumConstants.MAX_TIMEOUT);		
	}
	
	public IndustriesAndTopicsHome navigateToIndustryNewsPage(String industry) throws NoSuchElementException {
		if(!industriesAndTopicsSubmenu.isDisplayed()) {
			openIndustriesAndTopicsSubmenu();
		}
		
		try {
			WebElement industryLink = industriesAndTopicsSubmenu.findElement(By.linkText(industry.trim()));
			industryLink.click();
			return new IndustriesAndTopicsHome(driver);
		} catch(NoSuchElementException e) {
			throw new NoSuchElementException("Link for industry \"" + industry + "\" does not exist in the \"Industries and Topics\" submenu.");
		}
	}
	
	public IndustriesAndTopicsHome navigateToFeaturedIndustryNewsPage(String industry) throws NoSuchElementException {
		if(!industriesAndTopicsSubmenu.isDisplayed()) {
			openIndustriesAndTopicsSubmenu();
		}
		
		try {
			wait.until(ExpectedConditions.visibilityOf(featuredIndustriesSubmenu));
			WebElement featuredIndustryLink = featuredIndustriesSubmenu.findElement(By.partialLinkText(industry.toUpperCase()));
			featuredIndustryLink.click();
			return new IndustriesAndTopicsHome(driver);
		} catch(NoSuchElementException e) {
			throw new NoSuchElementException("Could not find featured Industries submenu.");
		}
	}
	
	public void openSearch() {
		siteSearchTrigger.click();
		wait.until(ExpectedConditions.visibilityOf(searchForm));
	}
	
	public void closeSearch() {
		String classAttributes = siteSearchTrigger.getAttribute("class");
		if(classAttributes.contains("is-active")) {
			siteSearchTrigger.click();
		}
	}
	
	public Boolean isSearchFormVisible() {
		return searchForm.isDisplayed();
	}
	
	public void enterSearchText(String text) {
		searchInput.sendKeys(text);
	}
	
	public void openSubmenu(String navItemLabel) {
		List<WebElement> navItems = navBar.findElements(By.cssSelector("li.nav-item"));
		Iterator<WebElement> navItemsIterator = navItems.iterator();
		Boolean found = false;
		WebElement navItem;
		
		navItemLabel = navItemLabel.toUpperCase();
		while(!found && navItemsIterator.hasNext()) {
			navItem = navItemsIterator.next();
			
			// get link by text or partial text is not working here because of the span inside the anchor, so we must loop
			// replace ellipses unicode character in "MORE..."
			String navItemText = navItem.getText().replaceAll("\u2026", "");
			if(navItemText.equals(navItemLabel)) {
				found = true;
				hoverOverElement(navItem);
				// TODO - replace this generic wait with the ability to tell if the sibling subnav of the nav-trigger for the nav item is open
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.subnav")));
			}
		}
		
		if(!found) {
			throw new NoSuchElementException("Could not find nav item with label \"" + navItemLabel + "\".");
		}
	}
	
	public void clickSubmenuLink(String submenuTitle, String linkText) {
		openSubmenu(submenuTitle);
		
		// a hack to get nav elements with multi-span link text
		submenuTitle = submenuTitle.replaceAll("\u2026",  "");
		WebElement link = submenuTitle.equals("MORE") ? driver.findElement(By.cssSelector("div.banner-and-nav li[data-ct=\"2016 Nav : more : " + linkText + "\"] a")) : driver.findElement(By.linkText(linkText));
		 
		wait.until(ExpectedConditions.elementToBeClickable(link));
		// NOTE: We are using actions b/c 'click' is failing because Selenium interprets the page overlay as 
		// being on top of the 'MORE' subnav and is receiving the click.
		Actions actions = new Actions(driver);
		actions.moveToElement(link).click().perform();
	}
	
	/**
	 * NEWS SUBMENU
	 */

	public void clickNewsRiverFeatureHeadline() {
		openSubmenu("NEWS");
		WebElement featureHeadline = driver.findElement(By.cssSelector("a[data-ct=\"2016 Nav : News River : Feature Headline\"]"));
		featureHeadline.click();
	}
	
	public void clickNewsRiverSecondaryHeadline() {
		openSubmenu("NEWS");
		
		List<WebElement> secondaryHeadlines = driver.findElements(By.cssSelector("a[data-ct^=\"2016 Nav : News River : Secondary Headline\"]"));
		if(secondaryHeadlines.isEmpty()) {
			throw new NoSuchElementException("Could not find News River secondary headline.");
		}
		
		int index = rand.nextInt(secondaryHeadlines.size());
		WebElement randomSecondaryHeadline = secondaryHeadlines.get(index);
		randomSecondaryHeadline.click();		
	}
	
	/**
	 * LISTS & AWARDS SUBMENU
	 */
	
	public void clickListsRiverHeadline() {
		openSubmenu("LISTS & AWARDS");
		
		List<WebElement> headlines = driver.findElements(By.cssSelector("a[data-ct^=\"2016 Nav : Lists River : Headline\"]"));
		if(headlines.isEmpty()) {
			throw new NoSuchElementException("Could not find Lists River headline.");
		}
		
		int index = rand.nextInt(headlines.size());
		WebElement randomHeadline = headlines.get(index);
		randomHeadline.click();		
	}
	
	/**
	 * PEOPLE & COMPANIES
	 */
	
	public void clickPotmModuleHeadline() {
		openSubmenu("PEOPLE & COMPANIES");
		
		List<WebElement> headlines = driver.findElements(By.cssSelector("a[data-ct^=\"2016 Nav : POTM_I : POTM Module : Headline\"]"));
		if(headlines.isEmpty()) {
			throw new NoSuchElementException("Could not find POTM Module headline.");
		}
		
		int index = rand.nextInt(headlines.size());
		WebElement randomHeadline = headlines.get(index);
		randomHeadline.click();		
	}
	
	/**
	 * EVENTS
	 */
	
	public void clickEventsRiverHeadline() {
		openSubmenu("EVENTS");
		
		List<WebElement> headlines = driver.findElements(By.cssSelector("a[data-ct^=\"2016 Nav : Events River : Featured Events Module : Headline\"]"));
		if(headlines.isEmpty()) {
			throw new NoSuchElementException("Could not find Events River headline.");
		}
		
		int index = rand.nextInt(headlines.size());
		WebElement randomHeadline = headlines.get(index);
		randomHeadline.click();		
	}
}
