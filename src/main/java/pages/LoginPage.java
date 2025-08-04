package pages;

import base.BaseTest;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage extends BaseTest {

    @Step("Email Alanı Doldurulur.")
    public LoginPage sendKeysEmail(String text){
        driver.findElement(By.id("login-email")).sendKeys(text);
        return this;
    }

    @Step("Email Alanı Temizlenir.")
    public LoginPage clearEmail(){
        driver.findElement(By.id("login-email")).clear();
        return this;
    }

    @Step("Şifre Alanı Doldurulur.")
    public LoginPage sendKeysPassword(String text){
        driver.findElement(By.id("login-password-input")).sendKeys(text);
        return this;
    }

    @Step("GirişYap Butonuna Tıklanır.")
    public LoginPage clickLoginButton(){
        driver.findElement(By.cssSelector("[class='q-primary q-fluid q-button-medium q-button submit']")).click();
        return this;
    }

    @Step("Hata Mesajı Alınır.")
    public String getErrorMessage(){
        String hataMesaji= driver.findElement(By.cssSelector("[class='message']")).getText();
        screenshot();
        return hataMesaji;
    }
}

