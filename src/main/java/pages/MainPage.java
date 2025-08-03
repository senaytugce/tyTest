package pages;

import base.BaseTest;
import org.openqa.selenium.By;

public class MainPage extends BaseTest {

    public String getAccountInfo(){
        String hesabim= driver.findElements(By.cssSelector("[class='link-text']")).get(0).getText();
        return hesabim;
    }

}
