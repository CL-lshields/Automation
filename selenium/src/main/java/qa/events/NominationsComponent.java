package qa.events;

import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.junit.Assert;
import qa.SeleniumTest;
import qa.utility.WaitTool;
import qa.utility.bizjdb.DatabaseTool;
import qa.utility.bizjdb.QueryBuilder;

public class NominationsComponent extends BasePage {
	WaitTool wait;
	DatabaseTool data;
	WebElement activeNomination = null;

	public NominationsComponent(WebDriver driver) {
		super(driver);
		wait = new WaitTool(driver);
		try {
			data = new DatabaseTool();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean atPage() {
		return pageTitleHeader().isDisplayed();
	}

	// --------------------------Elements-------------------------------------//

	private WebElement pageTitleHeader() {
		return driver.findElement(By.tagName("title"));
	}

	private WebElement nominationsTable() {
		return driver.findElement(By.id("tabs-1")).findElement(By.className("stripeMe"));
	}

	public void getNomination() {
		activeNomination = nominationsTable().findElements(By.tagName("tr")).get(1);

	}

	public void getNomination(int row) {
		activeNomination = nominationsTable().findElements(By.tagName("tr")).get(row);
	}

	private List<WebElement> listOfNominations() {
		return driver.findElements(By.className("item__body"));
	}

	// --------------------------Helpers-------------------------------------//

	public void findNomination(int nomination) {
		listOfNominations().get(nomination).findElement(By.className("flag__body")).findElement(By.tagName("button"))
				.click();
		String name = listOfNominations().get(nomination).findElement(By.className("flag__body"))
				.findElement(By.tagName("h3")).getText();
		SeleniumTest.logger.info("Retreived nomination " + name + System.lineSeparator());
	}

	public void findNomination(String nomination) {
		for (int i = 0; i < listOfNominations().size(); i++) {
			if (listOfNominations().get(i).getText().contains(nomination)) {
				String name = listOfNominations().get(i).findElement(By.className("flag__body"))
						.findElement(By.tagName("h3")).getText();
				listOfNominations().get(i).findElement(By.className("flag__body")).findElement(By.tagName("button"))
						.click();
				SeleniumTest.logger.info("Retreived nomination " + name + System.lineSeparator());
				break;
			}
		}
	}

	/**
	 * Downloads csv file from the active nomination in the nomination table
	 */
	/*
	 * public void downloadData() {
	 * activeNomination.findElements(By.tagName("td")).get(7).findElement(By.
	 * linkText("DOWNLOAD DATA")).click(); try { downloadVerification();
	 * UrlStatusChecker check = new UrlStatusChecker(driver); check = new
	 * UrlStatusChecker(driver);
	 * check.setURIToCheck(driver.getCurrentUrl()+activeNomination.findElements(
	 * By.tagName("td")).get(7).findElement(By.linkText("DOWNLOAD DATA"
	 * )).getAttribute("href").toString());
	 * check.setHTTPRequestMethod(RequestMethod.GET); Assert.assertEquals(
	 * "Download is bad!",200, check.getHTTPStatusCode()); } catch (Exception e)
	 * { Assert.fail(e.getMessage()); e.printStackTrace(); } }
	 */

	/**
	 * Iterated through the available nominations until one with attachments is
	 * found. The row is marked as the active nomination and downloadData() is
	 * called before breaking from the loop.
	 */
	/*
	 * public void downloadRowWithAttachment() { for (int i = 1; i <
	 * nominationsTable().findElements(By.tagName("tr")).size(); i++) {
	 * getNomination(i); if (hasAttachment()) { downloadData(); break; } } } /*
	 * 
	 * /** Checks weather active nomination has an attachment.
	 * 
	 * @return - boolean, does nomination have attachment.
	 */
	public boolean hasAttachment() {

		// For debugging purposes
		/*
		 * for (int i = 0; i <
		 * activeNomination.findElements(By.tagName("td")).get(7).findElements(
		 * By.tagName("a")) .size(); i++) {
		 * System.out.println(activeNomination.findElements(By.tagName("td")).
		 * get(7).findElements(By.tagName("a")) .get(i).getText()); }
		 */
		return !activeNomination.findElements(By.tagName("td")).get(7)
				.findElements(By.linkText("DOWNLOAD DATA & ALL ATTACHMENTS")).isEmpty();
	}

	/**
	 * Checks weather there is currently an active nomination.
	 * 
	 * @return - boolean, active nomination
	 */
	public boolean activeNomination() {
		if (activeNomination.equals(null)) {
			return false;
		}
		return true;
	}

	public void verifyNominations(String market) {
		// get market id
		String marketId = "";
		QueryBuilder sqlMarket = new QueryBuilder();
		sqlMarket.sqlBuildSelect(new String[] { "market_id" });
		sqlMarket.sqlBuildFrom("bizj.market");
		sqlMarket.sqlBuildWhere("market_abbrev='" + market + "'");
		try {
			data.execute(sqlMarket);
			marketId = data.getResultSet().get(0).get("market_id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			SeleniumTest.logger.severe("SQL error: " + e.getMessage() + System.lineSeparator());
		}

		// create query
		QueryBuilder sql = new QueryBuilder();
		sql.setColumns(new String[] { "owner", "is_active" });
		// for each nomination, determine if the attached event is active
		for (int i = 0; i < listOfNominations().size(); i++) {

			// try SQL
			try {
				if (listOfNominations().get(i).findElements(By.className("item__title")).size() > 0) {
					String name = listOfNominations().get(i).findElement(By.className("item__title"))
							.getText();

					sql.setQuery(
							"select owner,is_active from bizj.ev_event join bizj.nomination on bizj.ev_event.event_id=bizj.nomination.event_id where bizj.nomination.title='"
									+ name + "' " + "&& bizj.nomination.market_id='" + marketId + "'");
					data.execute(sql);
					
					//if result set != null, verify nominations, else break the loop since there are
					//no more nominations
					if(data.getResultSet()!=null){
						if (data.getResultSet().get(0).get("is_active").equals("1")) {
							Assert.assertTrue("Register button is not present!",
									listOfNominations().get(i).findElement(By.className("item__call-to-action"))
									.findElements(By.tagName("a")).size() > 1);
							SeleniumTest.logger.info("nomination: " + name + " verified..." + System.lineSeparator());
						} else {
							Assert.assertTrue("Register button is present!",
									listOfNominations().get(i).findElement(By.className("item__call-to-action"))
									.findElements(By.tagName("a")).size() < 2);
							SeleniumTest.logger.info("nomination: " + name + " verified..." + System.lineSeparator());
						}
					}
				}
			} catch (SQLException ex) {
				SeleniumTest.logger.severe("SQL error: " + ex.getMessage() + System.lineSeparator());
			}
		}

	}

}
