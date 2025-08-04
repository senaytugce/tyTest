package base;

import data.Data;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static base.BaseTest.driver;

public class BaseLibrary extends Data {

    public static WebDriver driver;

    @Step("{millis} Saniye Beklenir.")
    public void sleep(int millis){
        try {
            Thread.sleep(millis*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Karşılaştırma Sağlanır.")
    public void assertEquals(String actual, String expected){
        Assert.assertEquals(actual, expected);

    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] screenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }



}
