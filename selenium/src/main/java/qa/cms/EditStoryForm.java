package qa.cms;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import qa.BasePage;
import qa.constants.SeleniumConstants;

public class EditStoryForm extends BasePage {
	
	/**
	 * FORM MESSAGES AND PLACEHOLDERS
	 */
	public String successfulSaveMessage = "Your changes have been saved";
	public String primaryChannelPlaceholderText = "Used for breadcrumb display along with targeting advertisements and sponsorships.";
	
	@FindBy(how = How.CSS, using="h1.content__header__title")
	private WebElement pageHeader;
	
	/**
	 * FORM INPUTS
	 */
	@FindBy(how = How.NAME, using="headline")
	private WebElement headlineInput;
	
	@FindBy(how = How.NAME, using="short_headline")
	private WebElement shortHeadlineInput;
	
	@FindBy(how = How.NAME, using="slug")
	private WebElement slugInput;
	
	@FindBy(how = How.NAME, using="tease")
	private WebElement teaseTextArea;
	
	@FindBy(how = How.NAME, using="paths[]")
	private WebElement pathsInput;
	
	@FindBy(how = How.NAME, using="redirect_secondary_path")
	private WebElement redirectSecondaryPathCheckbox;
	
	@FindBy(how = How.NAME, using="premium")
	private WebElement premiumCheckbox;
	
	@FindBy(how = How.ID, using="s2id_autogen7")
	private WebElement primaryChannelSelect;
	
	@FindBy(how = How.ID, using="s2id_autogen8_search")
	private WebElement primaryChannelInput;
	
	@FindBy(how = How.CSS, using="div#s2id_autogen7 a.select2-choice abbr.select2-search-choice-close")
	private WebElement primaryChannelDeleteLink;
	
	@FindBy(how = How.ID, using="s2id_autogen4")
	private WebElement additionalChannelInput;
	
	@FindBy(how = How.NAME, using="secondary_channels[]")
	private WebElement additionalChannelSelect;
	
	@FindBy(how = How.NAME, using="meta_headline")
	private WebElement pageTitleInput;
	
	@FindBy(how = How.ID, using="s2id_autogen6")
	private WebElement authorsInput;
	
	@FindBy(how = How.NAME, using="redirect_secondary_path")
	private WebElement notesTextArea;
	
	@FindBy(how = How.CSS, using="a[data-target=\"#story-body\"]")
	private WebElement storyBodyShowHideLink;
	
	@FindBy(how = How.ID, using="story-editor")
	private WebElement storyEditorTextArea;
	
	@FindBy(how = How.CSS, using="a[data-target=\"#optional-fields\"]")
	private WebElement optionalFieldsShowHideLink;
	
	@FindBy(how = How.NAME, using="publish_date")
	private WebElement publishDatePicker;
	
	@FindBy(how = How.NAME, using="publish_time")
	private WebElement publishTimePicker;
	
	@FindBy(how = How.NAME, using="overline")
	private WebElement overlineInput;
	
	@FindBy(how = How.NAME, using="print_headline")
	private WebElement printHeadlineInput;
	
	@FindBy(how = How.NAME, using="page_number")
	private WebElement pageNumberInput;
	
	@FindBy(how = How.NAME, using="subhead")
	private WebElement subheadTextArea;
	
	@FindBy(how = How.NAME, using="one_off_byline")
	private WebElement oneOffTaglineInput;
	
	@FindBy(how = How.NAME, using="correction")
	private WebElement correctionMessageTextArea;
	
	@FindBy(how = How.NAME, using="syndication")
	private WebElement syndicationCheckbox;
	
	@FindBy(how = How.NAME, using="native")
	private WebElement nativeCheckbox;
	
	@FindBy(how = How.NAME, using="special_categories")
	private WebElement specialCategoriesSelect;
	
	@FindBy(how = How.NAME, using="top_embed")
	private WebElement topEmbedTextArea;
	
	@FindBy(how = How.NAME, using="large_embed")
	private WebElement bottomEmbedTextArea;
	
	@FindBy(how = How.NAME, using="small_embed")
	private WebElement smallEmbedTextArea;
	
	@FindBy(how = How.CSS, using="a[data-target=\"#story-assets\"]")
	private WebElement storyAssetsShowHideLink;
	
	@FindBy(how = How.CSS, using="a[rel=\"leadPhoto\"]")
	private WebElement addExistingLeadPhotoButton;
	
	@FindBy(how = How.CSS, using="div[data-callback=\"leadPhoto\"] input")
	private WebElement addNewLeadPhotoButton;
	
	@FindBy(how = How.CSS, using="a[rel=\"thumbnail\"]")
	private WebElement addExistingTeasePhotoButton;
	
	@FindBy(how = How.CSS, using="div[data-callback=\"thumbnail\"] input")
	private WebElement addNewTeasePhotoButton;
	
	@FindBy(how = How.CSS, using="a[rel=\"infographic\"]")
	private WebElement addExistingInfographicButton;
	
	@FindBy(how = How.CSS, using="div[data-callback=\"infographic\"] input")
	private WebElement addNewInfographicButton;
	
	@FindBy(how = How.CSS, using="a[rel=\"video\"]")
	private WebElement addVideoButton;
	
	@FindBy(how = How.CSS, using="a[rel=\"gallery\"]")
	private WebElement addGalleryButton;
	
	@FindBy(how = How.CSS, using="a[rel=\"pulse\"]")
	private WebElement addBusinessPulseButton;
	
	@FindBy(how = How.CSS, using="a[rel=\"list\"]")
	private WebElement addListButton;
	
	@FindBy(how = How.CSS, using="a[rel=\"event\"]")
	private WebElement addEventButton;
	
	@FindBy(how = How.CSS, using="a[rel=\"nomination\"]")
	private WebElement addNominationsButton;
	
	@FindBy(how = How.CSS, using="a[rel=\"csv\"]")
	private WebElement addCsvButton;
	
	@FindBy(how = How.CSS, using="div[data-callback=\"csv\"] input")
	private WebElement addNewCsvButton;
	
	@FindBy(how = How.CSS, using="button[data-fieldset=\"related_content\"")
	private WebElement addRelatedContentButton;
	
	/**
	 * FORM CONTROLS
	 */
	@FindBy(how = How.CSS, using="div.content__header__actions button")
	private WebElement topSaveButton;
	
	@FindBy(how = How.CSS, using="div.content__header__actions a.btn-primary")
	private WebElement topSaveButtonDropdown;
	
	@FindBy(how = How.CSS, using="div.content__header__actions a.btn-cancel")
	private WebElement topCancelButton;
	
	@FindBy(how = How.CSS, using="div.primary-actions-footer button")
	private WebElement bottomSaveButton;
	
	@FindBy(how = How.CSS, using="div.primary-actions-footer a.btn-primary")
	private WebElement bottomSaveButtonDropdown;
	
	@FindBy(how = How.CSS, using="div.primary-actions-footer a.btn-cancel")
	private WebElement bottomCancelButton;
	
	/**
	 * Default constructor
	 * 
	 * @param driver
	 */
	public EditStoryForm(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Validate the required elements that define the page
	 * 
	 * @return true if the page is valid, false otherwise
	 */
	@Override
	public Boolean pageIsValid() {
		return true;
	}
	
	public void setHeadline(String text) {
		headlineInput.clear();
		headlineInput.sendKeys(text);
	}
	
	public String getHeadline() {
		return headlineInput.getAttribute("value");
	}
	
	public String getShortHeadline() {
		return shortHeadlineInput.getAttribute("value");
	}
	
	public Boolean waitForShortHeadlineToUpdate() {
		WebDriverWait wait = new WebDriverWait(driver, SeleniumConstants.MAX_TIMEOUT);
		return wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver d) {
				return d.findElement(By.name("short_headline")).getAttribute("value").length() != 0;
			}
		});
	}
	
	public String getSlug() {
		return slugInput.getAttribute("value");
	}

	public Boolean waitForSlugToUpdate() {
		WebDriverWait wait = new WebDriverWait(driver, SeleniumConstants.MAX_TIMEOUT);
		return wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver d) {
				return d.findElement(By.name("slug")).getAttribute("value").length() != 0;
			}
		});
	}
	
	public void setTease(String text) {
		teaseTextArea.clear();
		teaseTextArea.sendKeys(text);
	}

	public void setPrimaryChannel(String channel) {
		primaryChannelSelect.click();
		WebDriverWait wait = new WebDriverWait(driver, SeleniumConstants.MAX_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOf(primaryChannelInput));
		primaryChannelInput.sendKeys(channel);
		primaryChannelInput.sendKeys(Keys.ENTER);
	}
	
	public String getPrimaryChannel() {
		WebElement primaryChannel = driver.findElement(By.id("select2-chosen-8"));
		return primaryChannel.getText();
	}
	
	public void removePrimaryChannel() {
		primaryChannelDeleteLink.click();		
	}

	public void addAdditionalChannel(String channel) {
		additionalChannelInput.click();
		WebDriverWait wait = new WebDriverWait(driver, SeleniumConstants.MAX_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOf(additionalChannelSelect));
		
		Select select = new Select(additionalChannelSelect);
		select.selectByVisibleText(channel);
	}

	public void removeAdditionalChannel(String secondaryChannel) {
		// TODO Auto-generated method stub
		
	}

	public List<String> getAdditionalChannels() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<String> getAvailableAdditionalChannels() {
		List<String> returnList = new ArrayList<>();
		List<WebElement> availableAdditionalChannels;
		// change!
		//try{
			
			//System.out.println("scrolling to additional channel input");
			//Thread.sleep(5000);
			interruptTool.scrollIntoView(additionalChannelInput);
			
			//System.out.println("attempting to open additional channel select");
			//Thread.sleep(5000);
			additionalChannelInput.sendKeys(Keys.ENTER);
			
			//System.out.println("waiting for dropdown");
			//Thread.sleep(5000);
			WebDriverWait wait = new WebDriverWait(driver, SeleniumConstants.MAX_TIMEOUT);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("select2-drop")));
			
			//System.out.println("getting additional channels");
			//Thread.sleep(5000);
			availableAdditionalChannels = driver.findElements(By.cssSelector("div#select2-drop ul.select2-results li.select2-result-selectable div.select2-result-label"));
			for(WebElement channel : availableAdditionalChannels) {
				returnList.add(channel.getText());
			}
			
		/*} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		return returnList;
	}

	
	

}
