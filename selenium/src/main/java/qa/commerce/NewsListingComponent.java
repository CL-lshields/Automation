package qa.commerce;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.nio.BufferOverflowException;
import java.util.List;

/**
 * Created by lshields on 4/13/16.
 */
public class NewsListingComponent extends qa.commerce.BasePage {
    qa.utility.WaitTool wait;

    public NewsListingComponent(WebDriver driver) {
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

    private List<WebElement> ArticleListings(){
         return driver.findElement(By.className("chunk")).findElement(By.className("container"))
                 .findElement(By.className("primary")).findElement(By.className("items-container"))
                 .findElements(By.tagName("a"));
    }
    
    private WebElement nextPageButton(){
    	return driver.findElement(By.className("pagination-container")).findElement(By.linkText("Next"));
    }

    //--------------------------Helpers-------------------------------------//

    /**
     * grab the first article in the list of news listings
     */
    public void getArticle(){
    	String article = ArticleListings().get(0).getText();
        ArticleListings().get(0).click();
        qa.SeleniumTest.logger.info("navigated to article: "+article+System.lineSeparator());
    }

    /**
     * grab an article by matching name
     * @param name - name of the article to return
     */
    public void getArticle(String name){
        for(int i=0;i<ArticleListings().size();i++){
            if(ArticleListings().get(i).getText().equalsIgnoreCase(name)){
                ArticleListings().get(i).click();
                qa.SeleniumTest.logger.info("navigated to article: "+name+System.lineSeparator());
                break;
            }
        }
    }

    /**
     * grab an article by number
     * @param number - number of the article in the news list to grab
     */
    public void getArticle(int number){
        try {
        	String article = ArticleListings().get(number).getText();
        	wait.waitForElementToBeClickable(ArticleListings().get(number), 15).sendKeys(Keys.ENTER);        	
            qa.SeleniumTest.logger.info("navigated to article: "+article+System.lineSeparator());
        }catch(BufferOverflowException ex){
            qa.SeleniumTest.logger.info("Article "+number+" could not be found..."+System.lineSeparator());
        }
    }
    
    public boolean isSubscriberOnly( WebElement element){
    	return element.findElements(By.className("icon-key")).size()>0;
    }
    
    public int getNumberOfArticlesOnPage(){
    	return ArticleListings().size();
    }
    
    public List<WebElement>getArticleList(){
    	return ArticleListings();
    }
    
    public void NavigateNextPage(){
    	nextPageButton().sendKeys(Keys.ENTER);
    	qa.SeleniumTest.logger.info("Navigated to next subscribers only page..." + System.lineSeparator());
    }
}
