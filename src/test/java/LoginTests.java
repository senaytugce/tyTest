import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;

public class LoginTests extends BaseTest {

    LoginPage loginPage = new LoginPage();
    MainPage mainPage = new MainPage();

    @Test
    public void BasariliGiris() {
        loginPage.sendKeysEmail(email)
                .sendKeysPassword(password)
                .clickLoginButton();
        sleep(3);

        assertEquals(mainPage.getAccountInfo(),"Hesabım");
    }

    @Test
    public void BasarisizGiris() {
        loginPage.sendKeysEmail(email)
                .sendKeysPassword("hatali")
                .clickLoginButton();
        sleep(3);
        assertEquals(loginPage.getErrorMessage(),"E-posta adresiniz ve/veya şifreniz hatalı." );
    }

    @Test
    public void BosGirisKontrolu() throws InterruptedException {
        driver.findElement(By.cssSelector("[class='q-primary q-fluid q-button-medium q-button submit']")).click();
        Thread.sleep(3000);
        String hataMesaji= driver.findElement(By.cssSelector("[class='message']")).getText();
        Assert.assertEquals(hataMesaji,"Lütfen geçerli bir e-posta adresi giriniz." );
    }

    @Test
    public void BosSifreKontrolu() throws InterruptedException {
        driver.findElement(By.id("login-email")).sendKeys("xxx@gmail.com");
        driver.findElement(By.cssSelector("[class='q-primary q-fluid q-button-medium q-button submit']")).click();
        Thread.sleep(3000);
        String hataMesaji= driver.findElement(By.cssSelector("[class='message']")).getText();
        Assert.assertEquals(hataMesaji,"Lütfen şifrenizi giriniz." );
    }

    @Test
    public void BosMailKontrolu() throws InterruptedException {
        driver.findElement(By.id("login-password-input")).sendKeys("hatalı");
        driver.findElement(By.cssSelector("[class='q-primary q-fluid q-button-medium q-button submit']")).click();
        Thread.sleep(3000);
        String hataMesaji= driver.findElement(By.cssSelector("[class='message']")).getText();
        Assert.assertEquals(hataMesaji,"Lütfen geçerli bir e-posta adresi giriniz." );
    }

    @Test
    public void RequiredControl() throws InterruptedException {
        loginPage.clickLoginButton();
        sleep(3);
        assertEquals(loginPage.getErrorMessage(),"Lütfen geçerli bir e-posta adresi giriniz." );

        loginPage.sendKeysEmail(email)
                        .clickLoginButton();
        sleep(3);
        assertEquals(loginPage.getErrorMessage(),"Lütfen şifrenizi giriniz." );

        loginPage.sendKeysPassword(password)
                        .clickLoginButton();
        sleep(3);
        assertEquals(mainPage.getAccountInfo(),"Hesabım" );
    }

    @Test
    public void MinKarakterKontrolu() throws InterruptedException {
        loginPage.sendKeysEmail("t")
                .clickLoginButton();
        sleep(3);
        assertEquals(loginPage.getErrorMessage(),"Lütfen geçerli bir e-posta adresi giriniz." );        driver.findElement(By.cssSelector("[class='q-primary q-fluid q-button-medium q-button submit']")).click();
        Thread.sleep(3000);
        loginPage.clearEmail()
                        .sendKeysEmail(email)
                        .sendKeysPassword(password)
                        .clickLoginButton();
        sleep(3);
        assertEquals(mainPage.getAccountInfo(),"Hesabım" );
    }
}
