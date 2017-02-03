package qa.events;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import qa.SeleniumTest;
import qa.utility.InterruptTool;
import qa.utility.WaitTool;
import qa.utility.bizjdb.DatabaseTool;
import qa.utility.bizjdb.QueryBuilder;

public class AdminStagingEventSearch extends BasePage {
	WaitTool wait;
	InterruptTool interrupt;
	DatabaseTool data;

	public AdminStagingEventSearch(WebDriver driver) throws SQLException {
		super(driver);
		wait = new WaitTool(driver);
		interrupt = new InterruptTool(driver);
		data = new DatabaseTool();
	}

	public void toPage() {
		driver.navigate().to("http://admin.staging.bizjournals.com/event/event/search");
	}

	@Override
	public boolean atPage() {
		return pageTitleHeader().isDisplayed();
	}

	// --------------------------Elements-------------------------------------//

	private WebElement pageTitleHeader() {
		return driver.findElement(By.tagName("title"));
	}

	private WebElement searchButton() {
		return driver.findElement(By.linkText("SEARCH"));
	}
	
	private WebElement eventDeptButton(){
		return driver.findElement(By.id("filters-event_type"));
	}
	// --------------------------Helpers-------------------------------------//

	public void marketSearchSelect(String market) {
		//select market from list of input tags
		for (int i = 0; i < driver.findElements(By.tagName("input")).size(); i++) {
			// data.getResultSet().get(0).get("category_id");
			if (driver.findElements(By.tagName("input")).get(i).getAttribute("name")
					.contains(market)) {
				driver.findElements(By.tagName("input")).get(i).click();
			}
		}
	}

	public void categorySearchSelect() {
		QueryBuilder sql = new QueryBuilder();
		sql.sqlBuildSelect(new String[] { "category_id", "category_name" });
		sql.sqlBuildFrom("bizj.ev_category");
		sql.sqlBuildWhere("is_legacy='0'");
		try {
			data.execute(sql);
			System.out.println(data.getResultSet().toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			SeleniumTest.logger.info(e.getMessage() + System.lineSeparator());
		}
		for (int i = 0; i < driver.findElements(By.tagName("input")).size(); i++) {
			// data.getResultSet().get(0).get("category_id");			
			if (driver.findElements(By.tagName("input")).get(i).getAttribute("id")
					.contains(data.getResultSet().get(0).get("category_id"))) {
				interrupt.scrollIntoView(driver.findElement(By.id("category-check-all")));
				driver.findElements(By.tagName("input")).get(i).sendKeys(Keys.ENTER);
			}
		}
	}

	public void subCategorySearchSelect() {
		QueryBuilder sql = new QueryBuilder();
		sql.sqlBuildSelect(new String[] { "category_id", "category_name" });
		sql.sqlBuildFrom("bizj.ev_category");
		sql.sqlBuildWhere("is_legacy='0' && is_topcategory='0'");
		try {
			data.execute(sql);
			System.out.println(data.getResultSet().toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			SeleniumTest.logger.severe(e.getMessage() + System.lineSeparator());
		}
		for (int i = 0; i < driver.findElements(By.tagName("input")).size(); i++) {
			// data.getResultSet().get(0).get("category_id");
			if (driver.findElements(By.tagName("input")).get(i).getAttribute("id")
					.contains(data.getResultSet().get(0).get("category_id"))) {
				driver.findElements(By.tagName("input")).get(i).click();
			}
		}
	}

	public void topicsSearchSelect() {
		QueryBuilder sql = new QueryBuilder();
		sql.sqlBuildSelect(new String[] { "topic_id", "topic_name" });
		sql.sqlBuildFrom("bizj.ev_topic");
		//sql.sqlBuildWhere("is_legacy='0' && is_topcategory='0'");
		try {
			data.execute(sql);
			System.out.println(data.getResultSet().toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			SeleniumTest.logger.severe(e.getMessage() + System.lineSeparator());
		}
		for (int i = 0; i < driver.findElements(By.tagName("input")).size(); i++) {
			// data.getResultSet().get(0).get("category_id");
			if (driver.findElements(By.tagName("input")).get(i).getAttribute("id")
					.contains("topics-"+data.getResultSet().get(0).get("topic_id"))) {
				driver.findElements(By.tagName("input")).get(i).click();
			}
		}
	}

	public void startDateSearchSelect() {

	}
	
	public void endDateSearchSelect() {

	}

	public void departmentSearchSelect() {

	}

	public void searchEvents() {
		interrupt.scrollIntoView(eventDeptButton());
		searchButton().click();
		SeleniumTest.logger.info("Searching events..." + System.lineSeparator());
	}

}
