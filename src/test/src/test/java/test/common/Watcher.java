package test.common;

import java.io.IOException;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
 * @author Pavel Zeman
 */
public class Watcher extends TestWatcher {

    @Override
    protected void starting(Description description) {
        Utils.setDriver();
    }

    @Override
    protected void failed(Throwable e, Description description) {
        byte [] outputBytes = ((TakesScreenshot) Utils.getDriver()).getScreenshotAs(OutputType.BYTES);
        try {
            Utils.storeScreenShot(description.getTestClass().getName(), description.getMethodName(), outputBytes);
        } catch (IOException ioe) {
            throw new RuntimeException("Error when storing screenshot", ioe);
        }
    }

}
