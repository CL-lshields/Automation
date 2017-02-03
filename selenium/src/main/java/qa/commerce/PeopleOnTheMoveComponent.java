package qa.commerce;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Lance Shields
 */
public class PeopleOnTheMoveComponent extends qa.commerce.BasePage {
    qa.utility.WaitTool wait;

    public PeopleOnTheMoveComponent(WebDriver driver) {
        super(driver);
        // TODO Auto-generated constructor stub
        wait = new qa.utility.WaitTool(driver);
    }

    @Override
    public boolean atPage() {
        return pageTitleHeader().isDisplayed();
    }

    //--------------------------Elements-------------------------------------//

    private WebElement pageTitleHeader() {
        return driver.findElement(By.tagName("title"));
    }

    private WebElement nonSubscriberOffer(){
        return driver.findElement(By.linkText("Subscribe to Download Contacts"));
    }

    private WebElement peopleAroundCountry(){
        return driver.findElement(By.linkText("PEOPLE AROUND THE COUNTRY"));
    }

    private WebElement SubmitPotmButton(){
        return driver.findElement(By.linkText("Submit People on the Move"));
    }

    @SuppressWarnings("unused")
	private WebElement potmTable(){
        return driver.findElement(By.tagName("title"));
    }

    //--------------------------Helpers-------------------------------------//

    /**
     * Verify Special offer appears for non-subscribers
     */
    public void verifySpecialOffer(){
        Assert.assertTrue("non-subscriber offer not found",nonSubscriberOffer().isDisplayed());
        qa.SeleniumTest.logger.info("found non-subscriber offer..."+System.lineSeparator());
    }

    /**
     * Verify that people around the country module
     * is present in the right rail.
     */
    public void verifyPeopleAroundCountry(){
        Assert.assertTrue("people around the country is missing.",peopleAroundCountry().isDisplayed());
        qa.SeleniumTest.logger.info("found people around the country..."+System.lineSeparator());
    }

    /**
     * Submit a new person on the move. Will direct to submission
     * form when clicked.
     */
    public void submitNewPotm(){
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.linkText("PEOPLE AROUND THE COUNTRY")));
        SubmitPotmButton().click();
        Assert.assertTrue("Form is not dsiplayed.",driver.findElement(By.className("step-number")).isDisplayed());
        qa.SeleniumTest.logger.info("Creating new person on the move..."+System.lineSeparator());
    }

    /**
     * Select first person on the page
     */
    public void selectPerson(){

    }

    /**
     * Select a person by name.
     * @param name - name to search for amongst POTM
     */
    public void selectPerson(String name){

    }

    /**
     * Select a person from the POTM table by using row and column number
     * @param row - number of desired row
     * @param column - number of desired column
     */
    public void selectPerson(int row, int column){

    }
}




