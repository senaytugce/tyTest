import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;

public class LoginTests extends BaseTest {

    LoginPage loginPage = new LoginPage();
    MainPage mainPage = new MainPage();

    @Test(description = "TC001 - Basarili Kullanici Girisi")
    public void BasariliGiris() {
        loginPage.sendKeysEmail(email)
                .sendKeysPassword(password)
                .clickLoginButton();
        sleep(3);

        assertEquals(mainPage.getAccountInfo(),"Hesabım");
    }

    @Test(description = "TC002 - Hatali Kullanici Girisi")
    public void BasarisizGiris() {
        loginPage.sendKeysEmail(email)
                .sendKeysPassword("hatali")
                .clickLoginButton();
        sleep(3);
        assertEquals(loginPage.getErrorMessage(),"E-posta adresiniz ve/veya şifreniz hatalı." );
    }

    @Test(description = "TC003 - Bos Veri Kullanici Girisi")
    public void BosGirisKontrolu() {
        driver.findElement(By.cssSelector("[class='q-primary q-fluid q-button-medium q-button submit']")).click();
        sleep(3);
        String hataMesaji= driver.findElement(By.cssSelector("[class='message']")).getText();
        Assert.assertEquals(hataMesaji,"Lütfen geçerli bir e-posta adresi giriniz." );
    }

    @Test(description = "TC004 - Bos Sifre Kullanici Girisi")
    public void BosSifreKontrolu() {
        driver.findElement(By.id("login-email")).sendKeys("xxx@gmail.com");
        driver.findElement(By.cssSelector("[class='q-primary q-fluid q-button-medium q-button submit']")).click();
        sleep(3);
        String hataMesaji= driver.findElement(By.cssSelector("[class='message']")).getText();
        Assert.assertEquals(hataMesaji,"Lütfen şifrenizi giriniz." );
    }

    @Test(description = "TC005 - Bos Mail Kullanici Girisi")
    public void BosMailKontrolu() {
        driver.findElement(By.id("login-password-input")).sendKeys("hatalı");
        driver.findElement(By.cssSelector("[class='q-primary q-fluid q-button-medium q-button submit']")).click();
        sleep(3);
        String hataMesaji= driver.findElement(By.cssSelector("[class='message']")).getText();
        Assert.assertEquals(hataMesaji,"Lütfen geçerli bir e-posta adresi giriniz." );
    }

    @Test(description = "TC006 - Bos Alan Kontrolu")
    public void RequiredControl() {
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

    @Test(description = "TC007 - Mail Minimum Karakter Kontrolu")
    public void MinKarakterKontrolu() {
        loginPage.sendKeysEmail("t")
                .clickLoginButton();
        sleep(3);
        assertEquals(loginPage.getErrorMessage(),"Lütfen geçerli bir e-posta adresi giriniz." );        driver.findElement(By.cssSelector("[class='q-primary q-fluid q-button-medium q-button submit']")).click();
        sleep(3);
        loginPage.clearEmail()
                        .sendKeysEmail(email)
                        .sendKeysPassword(password)
                        .clickLoginButton();
        sleep(3);
        assertEquals(mainPage.getAccountInfo(),"Hesabım" );
    }
}
