package qa.events;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import qa.SeleniumTest;
import qa.utility.WaitTool;

public class AdminStagingLogin extends BasePage {
	WaitTool wait;

	public AdminStagingLogin(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		wait = new WaitTool(driver);
	}

	public void toPage() {
		// TODO Auto-generated method stub
		//driver.navigate().to("http://admin.dev.bizjournals.com/");
		driver.navigate().to("http://admin.staging.bizjournals.com/");
	}
	
	public void toPage(String admin) {
		// TODO Auto-generated method stub
		//driver.navigate().to("http://admin.dev.bizjournals.com/");
		driver.navigate().to(admin);
	}

	@Override
	public boolean atPage() {
		// TODO Auto-generated method stub
		return pageTitleHeader().isDisplayed();
	}

	// --------------------------Elements-------------------------------------//

	private WebElement pageTitleHeader() {
		return driver.findElement(By.tagName("title"));
	}

	public WebElement stagingLoginField() {
		return driver.findElement(By.id("email"));
	}

	public WebElement stagingPasswordField() {
		return driver.findElement(By.id("password"));
	}

	public WebElement adminEventsTechUsername() {
		return driver.findElement(By.id("email"));
	}

	public WebElement adminEventsTechPassword() {
		return driver.findElement(By.id("password"));
	}

	public WebElement adminEventsTechLogin() {
		return driver.findElement(By.id("login"));
	}

	// --------------------------Helpers-------------------------------------//

	/**
	 * supplies the login name neccesary to login into admin staging
	 * @param login - username, usually provided by hasmap found in an 
	 * ExcelDriver instance.
	 */
	public void stagingLogin(String login) {
		stagingLoginField().sendKeys(login);
	}

	/**
	 * supplies the password name neccesary to login into admin staging and 
	 * initiates login using the enter key on the field.
	 * @param password - password, usually provided by hashmap found in an
	 * ExcelDriver instance
	 */
	public void staginPassword(String password) {
		stagingPasswordField().sendKeys(password);
		stagingPasswordField().sendKeys(Keys.RETURN);
		SeleniumTest.logger.info("logged into staging..."+System.lineSeparator());
	}

	/**
	 * supplies the password name neccesary to login into the events tech site.
	 * @param login - username, usually provided by hasmap found in an 
	 * ExcelDriver instance.
	 */
	public void sendAdminEventsTechUser(String login) {
		adminEventsTechUsername().sendKeys(login);		
	}

	/**
	 * supplies the password name neccesary to login into the events tech site,
	 * and initiates login using the enter key on the field.
	 * @param password - password, usually provided by hashmap found in an
	 * ExcelDriver instance
	 */
	public void sendAdminEventsTechPass(String password) {
		adminEventsTechPassword().sendKeys(password);
		adminEventsTechPassword().sendKeys(Keys.RETURN);
		SeleniumTest.logger.info("logged into events.tech..."+System.lineSeparator());
	}

	/**
	 * Deprecated - currently serves no purpose or has any functionality. Retained 
	 * in case of future need.
	 */
	public void submitLogin() {
		// stagingPasswordButton().click();
		// wait.sleep(3);
		// Assert.assertEquals("bizjournals Admin",pageTitleHeader().getText());
	}
}
