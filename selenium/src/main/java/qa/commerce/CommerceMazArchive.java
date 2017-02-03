package qa.commerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import qa.utility.InterruptTool;

/**
 * Created by root on 4/12/16.
 */
public class CommerceMazArchive extends qa.commerce.BasePage {
    qa.utility.WaitTool wait;
    InterruptTool interrupt;

    public CommerceMazArchive(WebDriver driver) {
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

}
