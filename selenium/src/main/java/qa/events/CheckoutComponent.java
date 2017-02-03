package qa.events;

import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import qa.SeleniumTest;
import qa.utility.InterruptTool;
import qa.utility.WaitTool;

public class CheckoutComponent extends BasePage {
	WaitTool wait;
	InterruptTool interrupt;
	

	public CheckoutComponent(WebDriver driver) {
		super(driver);
		wait = new WaitTool(driver);
		interrupt = new InterruptTool(driver);
	}

	@Override
	public boolean atPage() {
		return pageTitleHeader().isDisplayed();
	}

	// --------------------------Elements-------------------------------------//

	private WebElement pageTitleHeader() {
		return driver.findElement(By.tagName("title"));
	}
	
    @SuppressWarnings("unused")
	private WebElement squadUpWidget(){
    	return driver.findElement(By.className("squadup-navigation-view-container"));
    }
    
    private WebElement squadUpWidgetLvl2(){
    	return driver.findElement(By.className("squadup-navigation-view-container"))
    			.findElements(By.tagName("div")).get(2);
    }
    
    private WebElement squadUpWidgetLvl3(){
    	return squadUpWidgetLvl2().findElements(By.tagName("div")).get(1);
    }
    
    private WebElement BaseWidget(){
    	return squadUpWidgetLvl3().findElements(By.tagName("div")).get(1);
    }
    
    //ticket select
    
    private WebElement ticketSelect(){
    	return driver.findElements(By.className("Dropdown-control")).get(1);
    }
    
    //Attendee Info
    
    @SuppressWarnings("unused")
	private WebElement getTicketsButton(){
    	return BaseWidget().findElements(By.tagName("form")).get(0).findElement(By.tagName("button"));
    }
    
    private WebElement firstNameField(){
    	return driver.findElement(By.name("first_name"));
    }
    
    private WebElement lastNameField(){
    	return driver.findElement(By.name("last_name"));
    }
    
    private WebElement emailField(){
    	return driver.findElement(By.name("email"));
    }
    
    @SuppressWarnings("unused")
	private WebElement passwordField(){
    	return driver.findElement(By.name("password"));
    }
    
    //credit card fields
    
    private WebElement cardNumberField(){
    	return driver.findElement(By.id("card-number"));
    }
    
    private WebElement expirationDateField(){
    	return driver.findElement(By.id("card-exp"));
    }
    
    private WebElement cvvField(){
    	return driver.findElement(By.id("card-cvv"));
    }
    
    private WebElement cardNameField(){
    	return driver.findElement(By.id("cardholder-name"));
    }
	
    //checkout questions
    
    private WebElement eventReferrer(){
    	//return driver.findElements(By.className("Dropdown-control")).get(1);
    	for(int i=0;i<driver.findElements(By.className("placeholder")).size();i++){
    		if(driver.findElements(By.className("placeholder")).get(i).getText().equals("Select Answer")){
    			return driver.findElements(By.className("placeholder")).get(i);
    		}
    	}
    	return null;
    }
    
    @SuppressWarnings("unused")
	private void checkoutQuestions(){    	
    		List<WebElement> temp = driver.findElements(By.className("ticket-question"));
    		for(int i=0;i<temp.size();i++){
    			if(temp.get(i).findElements(By.tagName("label")).size()>0){
    			System.out.println(	temp.get(i).findElement(By.tagName("label")).getText());
    			}
    		}
    		  	
    }
    
    private WebElement companyQuestion(){    	
		List<WebElement> temp = driver.findElements(By.className("ticket-question"));
		for(int i=0;i<temp.size();i++){
			if(temp.get(i).findElements(By.tagName("label")).size()>0){
				if(temp.get(i).findElement(By.tagName("label")).getText().contains("Company")){
					return temp.get(i).findElement(By.tagName("input"));
				}			
			}
		}
		return null;	  	
    }
    
    private WebElement jobQuestion(){    	
		List<WebElement> temp = driver.findElements(By.className("ticket-question"));
		for(int i=0;i<temp.size();i++){
			if(temp.get(i).findElements(By.tagName("label")).size()>0){
				if(temp.get(i).findElement(By.tagName("label")).getText().contains("Job Title")){
					return temp.get(i).findElement(By.tagName("input"));
				}			
			}
		}
		return null;	  	
    }
    
    private WebElement phoneQuestion(){    	
		List<WebElement> temp = driver.findElements(By.className("ticket-question"));
		for(int i=0;i<temp.size();i++){
			if(temp.get(i).findElements(By.tagName("label")).size()>0){
				if(temp.get(i).findElement(By.tagName("label")).getText().contains("Phone Number")){
					return temp.get(i).findElement(By.tagName("input"));
				}			
			}
		}
		return null;	  	
    }
    
    private WebElement addressQuestion(){    	
		List<WebElement> temp = driver.findElements(By.className("ticket-question"));
		for(int i=0;i<temp.size();i++){
			if(temp.get(i).findElements(By.tagName("label")).size()>0){
				if(temp.get(i).findElement(By.tagName("label")).getText().contains("Address Line 1")){
					return temp.get(i).findElement(By.tagName("input"));
				}			
			}
		}
		return null;	  	
    }    
    
    private WebElement cityQuestion(){    	
		List<WebElement> temp = driver.findElements(By.className("ticket-question"));
		for(int i=0;i<temp.size();i++){			
				if(temp.get(i).findElement(By.tagName("label")).getText().contains("City")){
					return temp.get(i).findElement(By.tagName("input"));
				}					
		}
		return null;	  	
    } 
    
    private WebElement stateQuestion(){    	
		List<WebElement> temp = driver.findElements(By.className("ticket-question"));
		for(int i=0;i<temp.size();i++){			
				if(temp.get(i).findElement(By.tagName("label")).getText().contains("State")){
					return temp.get(i).findElement(By.tagName("input"));
				}						
		}
		return null;	  	
    } 
    
    private WebElement zipQuestion(){    	
		List<WebElement> temp = driver.findElements(By.className("ticket-question"));
		for(int i=0;i<temp.size();i++){			
				if(temp.get(i).findElement(By.tagName("label")).getText().contains("Zip")){
					return temp.get(i).findElement(By.tagName("input"));
				}				
		}
		return null;	  	
    } 
	
    private WebElement registerButton(){
    	return driver.findElement(By.className("squadup-checkout-submit-payment"));
    }
	
	
	// --------------------------Helpers-------------------------------------//

	/**
	 * Check out using the process for a squadup event
	 * 
	 * @param data - the hashmap passed from the test case, usually
	 * an instance of ExcelDriver.
	 */
	public void squadUpcheckOut(HashMap<String,String> data){	
		wait.sleep(15000);
		wait.waitForElementToBeClickable(ticketSelect(), 30);
		ticketSelect().click();		
 		driver.findElements(By.className("Dropdown-option")).get(1).click();
 		Assert.assertTrue("page reset!",driver.findElements(By.className("placeholder")).get(1).getText().contains("1"));
 		wait.waitForElementToBeClickable(firstNameField(), 10);
		firstNameField().sendKeys(data.get("username"));
		wait.waitForElementToBeClickable(lastNameField(), 10);
		lastNameField().sendKeys(data.get("client"));
		wait.waitForElementToBeClickable(emailField(), 10);
		emailField().sendKeys((driver.getCurrentUrl().replace("/", "-")+data.get("username")).replace(":", ""));		
		
		//credit card into
		if(driver.findElements(By.className("placeholder")).get(1).getText().contains("1")){
		Assert.assertTrue("page reset!",driver.findElements(By.className("placeholder")).get(1).getText().contains("1"));
		if(data.get("su-ticket").equalsIgnoreCase("paid")){
			interrupt.scrollIntoView(emailField());				
			//driver.switchTo().frame("braintree-dropin-frame");
			
			wait.waitForPresenceOf(By.id("card-number"));
			cardNumberField().sendKeys(data.get("credit"));
			
			wait.waitForPresenceOf(By.id("card-exp"));
			expirationDateField().sendKeys(data.get("expiration"));
			
			wait.waitForPresenceOf(By.id("card-cvv"));
			cvvField().sendKeys(data.get("cvv").replace("0", ""));
			
			wait.waitForPresenceOf(By.id("cardholder-name"));
			cardNameField().sendKeys(data.get("market")+" " +data.get("su-ticket"));
			
			interrupt.scrollIntoView(cvvField());
			//driver.switchTo().defaultContent();			
		}else{
			interrupt.scrollIntoView(emailField());
		}
		
		//questions	
		Assert.assertTrue("page reset!",driver.findElements(By.className("placeholder")).get(1).getText().contains("1"));
		eventReferrer().click();
		driver.findElements(By.className("Dropdown-option")).get(1).click();

		wait.waitForElementToBeClickable(companyQuestion(), 10);
		companyQuestion().sendKeys("BizJ");
		jobQuestion().sendKeys("IT");
		phoneQuestion().sendKeys(data.get("phone"));
		addressQuestion().sendKeys(data.get("address"));
		cityQuestion().sendKeys(data.get("market"));
		stateQuestion().sendKeys(data.get("code"));
		zipQuestion().sendKeys(data.get("zip"));
		
		interrupt.scrollIntoView(companyQuestion());
		
		registerButton().sendKeys(Keys.ENTER);
		wait.waitForPresenceOf(By.className("flexxy-centered"));		
		wait.sleep(10000);		
		Assert.assertTrue("Confirmation not displayed!",driver.findElement(By.id("squadup-checkout"))
				.findElement(By.className("flexxy-centered"))				
				.getText().contains("Thank you for joining"));
	}else{
		SeleniumTest.logger.info("Page reset..." + System.lineSeparator());
		interrupt.scrollIntoView(driver.findElement(By.id("register")));
		squadUpcheckOut(data);
	}
	
		
	}
}
