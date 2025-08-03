package base;

import data.Data;
import org.testng.Assert;

public class BaseLibrary extends Data {

    public void sleep(int millis){
        try {
            Thread.sleep(millis*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void assertEquals(String actual, String expected){
        Assert.assertEquals(actual, expected);

    }



}
