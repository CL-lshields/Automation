package qa.bizintelligence;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import qa.SeleniumTest;
import qa.bizintelligence.modules.BizIntelligenceAdmin;

public class SyndicationApiComponent extends qa.bizintelligence.BasePage {
	qa.utility.WaitTool wait;

	public SyndicationApiComponent(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		wait = new qa.utility.WaitTool(driver);
	}

	@Override
	public boolean atPage() {
		return pageTitleHeader().isDisplayed();
	}

	// --------------------------Elements-------------------------------------//

	private WebElement pageTitleHeader() {
		return driver.findElement(By.tagName("title"));
	}

	private List<WebElement> syndicationTableFeeds() {
		return driver.findElement(By.id("DataTables_Table_0")).findElements(By.tagName("tr"));
	}

	// --------------------------Elements-------------------------------------//

	/**
	 * Retrieves name of feed in the syndication table based on its order in the table.
	 * @param i - number of the feed to retrieve.
	 * @return - returns a string representation of the feeds name.
	 */
	public String getFeedName(int i) {
		syndicationTableFeeds().get(i).findElements(By.tagName("td")).get(5).click();
		SeleniumTest.logger.info(
				"Accessing feed for " + syndicationTableFeeds().get(i).findElements(By.tagName("td")).get(1).getText()
						+ System.lineSeparator());
		return syndicationTableFeeds().get(i).findElements(By.tagName("td")).get(1).getText();
	}
	
	/**
	 * Retrieves name of feed in the syndication table based on its name in the table.
	 * @param feed - name of the feed to retreive.
	 * @return - returns a string representation of the feeds name.
	 */
	public String getFeedName(String feed) {
		for (int i = 0; i < syndicationTableFeeds().size(); i++) {
			if (syndicationTableFeeds().get(i).findElements(By.tagName("td")).size() > 0) {
				if(syndicationTableFeeds().get(i).findElements(By.tagName("td")).get(1).getText().equals(feed))
				return syndicationTableFeeds().get(i).findElements(By.tagName("td")).get(1).getText();				
			}
		}
		return null;
	}
	


	/**
	 * Fetches the feed of the specified market.
	 * @param feed - name of the feed to retrieve.
	 * @param market - name of the market to retrieve feed from.
	 */
	public void fetchMarketFeed(String feed, String market) {
		String feedLink;
		for (int i = 0; i < syndicationTableFeeds().size(); i++) {
			if (syndicationTableFeeds().get(i).findElements(By.tagName("td")).size() > 0) {
				if (syndicationTableFeeds().get(i).findElements(By.tagName("td")).get(1).getText().equals(feed)) {
					feedLink = syndicationTableFeeds().get(i).findElements(By.tagName("td")).get(5).findElement(By.tagName("a")).getAttribute("href");					
					feedLink = feedLink.replace("=all", "="+market.toLowerCase());
					SeleniumTest.logger.info("Accessing feed for "
							+ syndicationTableFeeds().get(i).findElements(By.tagName("td")).get(1).getText()
							+ System.lineSeparator());					
					driver.navigate().to(feedLink);
					wait.waitForPresenceOf(By.tagName("NewsItem"));
					//DEBUG
					//System.out.println(driver.getPageSource());
					break;
				}
			}
		}
	}
	
	/**
	 * Fetches the feed of the specified market.
	 * @param feed - name of the feed to retrieve.
	 */
	public void fetchAllMarketFeed(String feed) {
		String feedLink;
		for (int i = 0; i < syndicationTableFeeds().size(); i++) {
			if (syndicationTableFeeds().get(i).findElements(By.tagName("td")).size() > 0) {
				if (syndicationTableFeeds().get(i).findElements(By.tagName("td")).get(2).getText().equals(feed)) {
					feedLink = syndicationTableFeeds().get(i).findElements(By.tagName("td")).get(5).findElement(By.tagName("a")).getAttribute("href");						
					SeleniumTest.logger.info("Accessing feed for "
							+ syndicationTableFeeds().get(i).findElements(By.tagName("td")).get(2).getText()
							+ System.lineSeparator());					
					driver.navigate().to(feedLink);
					wait.waitForPresenceOf(By.tagName("NewsItem"));							
					break;
				}
			}
		}
	}

	public int getNewsItemCount(String feedType) {	
		switch(feedType){
		case BizIntelligenceAdmin.newsMl:
			SeleniumTest.logger
				.info("NewsItems: " + driver.findElements(By.tagName("NewsItem")).size() + System.lineSeparator());
			return driver.findElements(By.tagName("NewsItem")).size();
		case BizIntelligenceAdmin.newsMlLegacy:
			SeleniumTest.logger
			.info("NewsItems: " + driver.findElements(By.tagName("newsItem")).size() + System.lineSeparator());
			return driver.findElements(By.tagName("newsItem")).size();	
		case BizIntelligenceAdmin.newsMlXhtml:
			SeleniumTest.logger
			.info("NewsItems: " + driver.findElements(By.tagName("NewsItem")).size() + System.lineSeparator());
		return driver.findElements(By.tagName("NewsItem")).size();			
		case BizIntelligenceAdmin.rss:
			
		case BizIntelligenceAdmin.json:
			String input = driver.findElement(By.tagName("pre")).getText();
			int index = input.indexOf("source_id");
			int count = 0;
			while (index != -1) {
			    count++;
			    input = input.substring(index + 1);
			    index = input.indexOf("source_id");
			}			
			SeleniumTest.logger
			.info("NewsItems: " + count + System.lineSeparator());
			return count;
		case BizIntelligenceAdmin.facebook:
			SeleniumTest.logger
			.info("NewsItems: " + driver.findElements(By.className("feedEntryContent")).size() + System.lineSeparator());			
		return driver.findElements(By.className("feedEntryContent")).size();			
		default:
		}
		return 0;
	}
		

	
	public void verifyFeed(String feedType){
		Assert.assertTrue("Feed could not be verified",(110>getNewsItemCount(feedType)&&5<getNewsItemCount(feedType)));
	}
}
