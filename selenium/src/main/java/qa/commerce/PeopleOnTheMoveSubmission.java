package qa.commerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import qa.utility.InterruptTool;

import java.util.List;

/**
 * @author Lance Shields
 */
public class PeopleOnTheMoveSubmission extends qa.commerce.BasePage {
    qa.utility.WaitTool wait;
    InterruptTool interrupt;

    public PeopleOnTheMoveSubmission(WebDriver driver) {
        super(driver);
        // TODO Auto-generated constructor stub
        wait = new qa.utility.WaitTool(driver);
        interrupt = new InterruptTool(driver);
    }

    @Override
    public boolean atPage() {
        return pageTitleHeader().isDisplayed();
    }

//--------------------------Elements-------------------------------------//

    private WebElement pageTitleHeader() {
        return driver.findElement(By.tagName("title"));
    }

    //Step one and two fields

    private WebElement genderField() {
        return driver.findElement(By.name("gender"));
    }

    private List<WebElement> subTypeRadioButtons() {
        return driver.findElements(By.name("submission_type"));
    }

    private WebElement subTypeSubmit() {
        return driver.findElement(By.linkText("Continue"));
    }

    private WebElement firstNameField() {
        return driver.findElement(By.name("first_name"));
    }

    private WebElement lastNameField() {
        return driver.findElement(By.name("last_name"));
    }

    private List<WebElement> genderDropdown() {
        return driver.findElement(By.name("gender")).findElements(By.tagName("option"));
    }

    private WebElement employerField() {
        return driver.findElement(By.name("current_employer"));
    }

    private WebElement postionField() {
        return driver.findElement(By.name("current_position"));
    }

    private WebElement positionLevel() {
        return driver.findElement(By.name("position_level"));
    }

    private List<WebElement> positionLevelDropdown() {
        return driver.findElement(By.name("position_level")).findElements(By.tagName("option"));
    }

    private WebElement dutiesField() {
        return driver.findElement(By.name("summary"));
    }

    //step three fields

    private WebElement addressField() {
        return driver.findElement(By.name("office_street_1"));
    }

    private WebElement cityField() {
        return driver.findElement(By.name("office_city"));
    }

    private List<WebElement> stateDropdown() {
        return driver.findElement(By.name("office_state")).findElements(By.tagName("option"));
    }

    private WebElement zipField() {
        return driver.findElement(By.name("office_zip"));
    }

    private WebElement telephoneField() {
        return driver.findElement(By.name("office_phone"));
    }

    private List<WebElement> insustryDropdown() {
        return driver.findElement(By.id("industry_id")).findElements(By.tagName("option"));
    }

    @SuppressWarnings("unused")
	private List<WebElement> marketDropdown() {
        return driver.findElement(By.name("market_id")).findElements(By.tagName("option"));
    }

    private WebElement stateField() {
        return driver.findElement(By.name("office_state"));
    }

    private WebElement industryField() {
        return driver.findElement(By.id("industry_id"));
    }

    @SuppressWarnings("unused")
	private WebElement marketField() {
        return driver.findElement(By.name("market_id"));
    }

    //--------------------------Helpers-------------------------------------//

    /**
     * Select submission type based on the name of the corresponding radio button.
     * @param button - value attribute of the radio button to select
     */
    public void clicksubTypeRadioButton(String button){
        //Step 1
        for(int i=0;i<subTypeRadioButtons().size();i++){
            if(subTypeRadioButtons().get(i).getAttribute("value").equalsIgnoreCase(button)){
                subTypeRadioButtons().get(i).click();
                interrupt.scrollIntoView(subTypeRadioButtons().get(i));

            }
        }
        subTypeSubmit().click();
        qa.SeleniumTest.logger.info("Selected "+button+" radio submission type..."+System.lineSeparator());
    }

    /**
     * Submit submission form for new person on the move.
     */
    public void subFormSubmit(){
        //Step 2
        wait.waitForVisibilityOf(firstNameField(),30);
        firstNameField().sendKeys("QA");
        lastNameField().sendKeys("Person");
        employerField().sendKeys("ACBJ");
        genderSelect("Male");
        postionField().sendKeys("QA");
        positionSelect("Other");
        dutiesField().sendKeys("Does QA real good.");
        interrupt.scrollIntoView(subTypeSubmit());
        subTypeSubmit().click();

        //Step 3
        addressField().sendKeys("400 West Morehead");
        cityField().sendKeys("This Market");
        wait.waitForPresenceOf(By.name("office_state"));
        stateField().click();
        stateDropdown().get(1).click();
        zipField().sendKeys("28105");
        telephoneField().sendKeys("555-555-5555");
        interrupt.scrollIntoView(zipField());
        industryField().click();
        qa.SeleniumTest.logger.info(driver.getCurrentUrl()+System.lineSeparator());
        insustryDropdown().get(1).click();
        qa.SeleniumTest.logger.info(driver.getCurrentUrl()+System.lineSeparator());
        subTypeSubmit().click();

    }

    /**
     * 
     * @param gender
     */
    public void genderSelect(String gender){
        genderField().click();
        for(int i=0;i<genderDropdown().size();i++){
            if(genderDropdown().get(i).getText().equalsIgnoreCase(gender)){
                genderDropdown().get(i).click();
            }
        }
    }

    /**
     * 
     * @param position
     */
    public void positionSelect(String position){
        positionLevel().click();
        for(int i=0;i<positionLevelDropdown().size();i++){
            if(positionLevelDropdown().get(i).getText().equalsIgnoreCase(position)){
                positionLevelDropdown().get(i).click();
            }
        }
    }
}
