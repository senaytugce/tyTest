package pages;

import base.BaseTest;
import org.openqa.selenium.By;

public class LoginPage extends BaseTest {

    public LoginPage sendKeysEmail(String text){
        driver.findElement(By.id("login-email")).sendKeys(text);
        return this;
    }

    public LoginPage clearEmail(){
        driver.findElement(By.id("login-email")).clear();
        return this;
    }

    public LoginPage sendKeysPassword(String text){
        driver.findElement(By.id("login-password-input")).sendKeys(text);
        return this;
    }

    public LoginPage clickLoginButton(){
        driver.findElement(By.cssSelector("[class='q-primary q-fluid q-button-medium q-button submit']")).click();
        return this;
    }

    public String getErrorMessage(){
        String hataMesaji= driver.findElement(By.cssSelector("[class='message']")).getText();
        return hataMesaji;
    }

}

