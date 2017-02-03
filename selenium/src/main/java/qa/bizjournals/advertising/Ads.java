package qa.bizjournals.advertising;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import qa.BasePage;
import qa.SeleniumTest;

public class Ads extends BasePage {
	
	@FindBy(how = How.CSS, using="div[data-position=\"b1\"]")
	private List<WebElement> b1Ads;
	
	@FindBy(how = How.CSS, using="div[data-position=\"c1\"]")
	private List<WebElement> c1Ads;
	
	@FindBy(how = How.ID, using="c1_1")
	private WebElement c1_1;
	
	@FindBy(how = How.CSS, using="div[data-position=\"c2\"]")
	private List<WebElement> c2Ads;
	
	@FindBy(how = How.CSS, using="div[data-position=\"exc\"]")
	private List<WebElement> excAds;
	
	@FindBy(how = How.CSS, using="div[data-position=\"skin\"]")
	private List<WebElement> skinAds;
	
	@FindBy(how = How.CSS, using="div[data-position=\"skybox\"]")
	private List<WebElement> skyboxAds;
	
	@FindBy(how = How.CSS, using="div[data-position=\"t1\"]")
	private List<WebElement> t1Ads;
	
	@FindBy(how = How.CSS, using="div[data-position=\"oop\"]")
	private List<WebElement> oopAds;
	
	/**
	 * Default constructor
	 * 
	 * @param driver
	 */
	public Ads(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Validate the required elements that define the page
	 * 
	 * @return true if the page is valid, false otherwise
	 */
	@Override
	public Boolean pageIsValid() {
		//return adContainers.size() > 0;
		return true;
	}
	
	public int countAdsInPosition(String adPosition) {
		List<WebElement> ads = driver.findElements(By.cssSelector("div[data-position=\"" + adPosition + "\"]"));
		return ads.size();
	}
	
	/**
	 * Fetch the ads with the given ad position and validate each one.
	 * 
	 * @param adPosition	A String that represents the data-position tag for a gpt ad div
	 * @return				A List of the names of the ads that are invalid.  When all ads are
	 * 						valid, the List is empty.
	 */
	public List<String> validateAdsInPosition(String adPosition) {
		
		SeleniumTest.logger.info("Looking for " + adPosition + " ads.");
		
		// set up some helpful containers
		List<WebElement> ads = new ArrayList<WebElement>();
		List<String> invalidAds = new ArrayList<String>();
		
		// fetch web elements in the desired ad position
		switch(adPosition.trim().toLowerCase()) {
			case "b1":	
				ads = b1Ads;
				break;
			case "c1":
				ads = c1Ads;
				break;
			case "c2":
				ads = c2Ads;
				break;
			case "exc":
				ads = excAds;
				break;
			case "skin":
				ads = skinAds;
				break;
			case "skybox":
				ads = skyboxAds;
				break;
			case "t1":
				ads = t1Ads;
				break;
			case "oop":
				ads = oopAds;
				break;
		}
		
		int adCount = ads.size();
		SeleniumTest.logger.info("Found " + adCount + " " + adPosition + " ad(s).");
		
		// iterate through the ads and validate them individually
		if(adCount > 0) {
			for(int i = 0; i < adCount; i++) {
				WebElement ad = ads.get(i);
				interruptTool.scrollIntoView(ad);
				
				// skip ad containers that are not displayed
				if(!ad.isDisplayed()) {
					SeleniumTest.logger.info("Ad is not displayed ... skipping");
					continue;
				}
				
				// validate the ad, if it fails, add to invalidAds return List
				if(!validateAd(ad.getAttribute("id"))) {
					SeleniumTest.logger.severe(ad.getAttribute("id") + " FAILED VALIDATION!");
					invalidAds.add(ad.getAttribute("id"));
				} else {
					SeleniumTest.logger.info(ad.getAttribute("id") + " PASSES VALIDATION!");
				}
						
			}
		} else {
			invalidAds.add("Ad Position '" + adPosition + "' not found.");
		}
		
		// if all went well, our invalidAds List should be empty
		/*if(invalidAds.size() > 0) {
			System.out.println(StringUtils.join(invalidAds, "\n\t"));
		}*/
		return invalidAds;
	}
	
	/**
	 * Validate that an ad contains an iframe supplied by google and has a valid pixel size.
	 * 
	 * @param ad	The WebElement that points to the gpt ad div
	 * @return		TRUE if the ad is valid, FALSE otherwise
	 */
	public Boolean validateAd(String id) {
		
		SeleniumTest.logger.info("Validating " + id);
		
		WebElement ad = driver.findElement(By.id(id));
		
		WebElement iframe = driver.findElement(By.cssSelector("div#" + id + " iframe[id^=\"google_ads_iframe\"]"));
		
		try {
			// fetch the iframe loaded by gpt inside the ad container
			//WebElement gptIframe = ad.findElement(By.cssSelector("iframe[id^=\"google_ads_iframe\"]"));
			
			// check to see if the ad loaded is a valid size
			String validSizes = ad.getAttribute("data-sizes");
			String adSize = "[" + iframe.getAttribute("width") + "," + iframe.getAttribute("height") + "]";
			SeleniumTest.logger.info("Ad size is " + adSize + ".  Checking against valid ad sizes " + validSizes + ".");
					
			if(!validSizes.contains(adSize)) {
				// ad size does not match container specs, ad is invalid
				return false;
			}
		} catch(NoSuchElementException e) {
			// the iframe did not load, ad is invalid
			return false;
		}
		
		return true;
	}
}
