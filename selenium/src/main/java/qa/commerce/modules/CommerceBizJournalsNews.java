package qa.commerce.modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import qa.utility.InterruptTool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Assert;

/**
 * Created by root on 4/13/16.
 */
public class CommerceBizJournalsNews {

	private WebDriver driver;
	private qa.ComponentFactory components;
	private qa.utility.WaitTool waitTool;
	private InterruptTool interrupt;

	public CommerceBizJournalsNews(WebDriver driver) {
		this.driver = driver;
		components = new qa.ComponentFactory(driver);
		waitTool = new qa.utility.WaitTool(driver);
		interrupt = new InterruptTool(driver);
	}

	public qa.ComponentFactory getComponents() {
		return components;
	}

	public void VerifyMeteredPaywall() {
		int paywallCount = 0;
		int errorCount = 0;
		boolean verified = false;
		/*
		 * cycle through articles one by one on the page
		 */
		for (int i = 0; i < components.newsListingComponent().getNumberOfArticlesOnPage(); i++) {
			components.newsListingComponent().getArticle(i);
			interrupt.handleInterstitial();
			waitTool.waitForPresenceOf(By.className("menubar"));

			/*
			 * Proceed through list of checks to ensure articles are valid for
			 * triggering the paywall.
			 */

			// Ignore any subscriber content articles
			if (driver.findElements(By.className("icon-key")).size() > 0) {
				qa.SeleniumTest.logger
						.info("Subscriber content. Article not counted in paywall..." + System.lineSeparator());
				driver.navigate().back();
			}

			// Ignore and special category articles
			else if (driver.findElements(By.name("page:special-category:id")).size() > 0) {
				/*
				 * navigate back, because special categories dont count towards
				 * metered paywall total
				 */
				qa.SeleniumTest.logger
						.info("Special category. Article not counted in paywall..." + System.lineSeparator());
				driver.navigate().back();
			}

			// // Ignore articles not under 'news' or 'blogs'. requirement
			// // specified by B.markham
			// else if (!(driver.getCurrentUrl().contains("news") ||
			// driver.getCurrentUrl().contains("blog"))) {
			// qa.SeleniumTest.logger
			// .info("Article wont be counted, is not 'news' or 'blog'..." +
			// System.lineSeparator());
			// driver.navigate().back();
			// }

			// Ignore bizwomen articles. requirement specified by B.markham
			else if (driver.getCurrentUrl().contains("bizwomen")) {
				qa.SeleniumTest.logger.info("Bizwomen articles wont be counted..." + System.lineSeparator());
				driver.navigate().back();
			}

			else {

				/**
				 * Check to see how many paywall prompts have been encountered.
				 * After three, we should the metered paywall instead. If we
				 * encounter the metered paywall, break the loop.
				 */
				if (paywallCount > 2) {
					verified = waitTool.waitForPresenceOf(By.cssSelector(".module.module--padded.striped--champagne"));
					Assert.assertTrue("Paywall not appearing properly", verified);
					qa.SeleniumTest.logger.info("Sucesfully verified paywall meter..." + System.lineSeparator());
					break;
				}

				// Otherwise, the paywall should be present and we increment the
				// counter
				else if (components.newsArticleComponent().isArticleMeterPaywall()) {
					paywallCount++;
					qa.SeleniumTest.logger.info(components.newsArticleComponent().articleCount()
							+ " Articles remaining..." + System.lineSeparator());
					driver.navigate().back();
					interrupt.handleInterstitial();
				} else {
					errorCount++;
					if(errorCount>2){
						Assert.fail("No paywall present where paywall is expected!");
					}					
					qa.SeleniumTest.logger.info("This article could not be counted..." + System.lineSeparator());					
					driver.navigate().back();
					interrupt.handleInterstitial();
				}
			}
		}

		// Something didnt fire correctly
		if (!verified) {
			qa.SeleniumTest.logger.severe("Did not sucesfully verify paywall meter..." + System.lineSeparator());
		}
	}

	public void verifyPostPremiumContent() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
		Calendar calendar = Calendar.getInstance();
		Calendar article = Calendar.getInstance();
		calendar.setTime(new java.util.Date());
		calendar.add(Calendar.DATE, -60);

		// check the first article for paywall
		components.newsListingComponent().getArticle(0);
		interrupt.handleInterstitial();
		Assert.assertTrue("Paywall did not appear as expected",
				components.newsArticleComponent().isArticleFullPaywall());
		driver.navigate().back();
		interrupt.handleInterstitial();

		// cycle through all subscriber articles. When we find one past the post
		// promotion date,
		// assert that the paywall does not exist.
		for (int j = 0; j < 1011; j++) {
			Boolean paywallFound = false;
			// get number of articles on page
			for (int i = 0; i < components.newsListingComponent().getNumberOfArticlesOnPage(); i++) {

				// for each article, get the date
				String articleDate = driver.findElement(By.className("chunk")).findElements(By.tagName("a")).get(i)
						.findElements(By.className("meta-item")).get(1).getText();

				// set the date to a calendar object
				try {
					article.setTime(dateFormat.parse(articleDate));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					qa.SeleniumTest.logger.severe(e.getMessage() + System.lineSeparator());
				}

				// compare the calendar of the article to the promotion date. If
				// the article was written
				// before the promotion date, it is free to view.
				if (calendar.after(article)) {
					components.newsListingComponent().getArticle(i);
					interrupt.handleInterstitial();
					Assert.assertTrue("Paywall appeared unexpectedly!",
							!components.newsArticleComponent().isArticleFullPaywall());
					paywallFound = true;
					break;
				}

				// Debug
				// System.out.println(dateFormat.format(calendar.getTime())+" is
				// not after "+dateFormat.format(article.getTime()));
			}

			if (paywallFound) {
				break;
			}
			components.newsListingComponent().NavigateNextPage();
			qa.SeleniumTest.logger
					.info("Searching page " + j + " for psot promotion articles..." + System.lineSeparator());
		}
	}

	public void VerifyPremiumPaywall() {
		for (int i = 0; i < components.newsListingComponent().getNumberOfArticlesOnPage(); i++) {
			
			
			// Ignore any non-subscriber content articles
			if (components.newsListingComponent().getArticleList().get(i).findElements(By.className("icon-key")).size()==0) {
				qa.SeleniumTest.logger
						.info("non-subscriber content. Article not counted as Premium..." + System.lineSeparator());				
			}else{			
				components.newsListingComponent().getArticle(i);
				Assert.assertTrue("Paywall not present!",components.newsArticleComponent().isArticleFullPaywall());
				qa.SeleniumTest.logger
				.info("Premium paywall present..." + System.lineSeparator());
				driver.navigate().back();
				
			}
		}
	}
}
