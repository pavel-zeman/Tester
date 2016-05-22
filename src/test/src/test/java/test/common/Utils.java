package test.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @author Pavel Zeman
 */
public class Utils {

    private static final String RESULT_DIR = "result";

    private static final String DATE_PATTERN = "d.M.y";

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void setDriver() {
        if (driverThreadLocal.get() == null) {
            WebDriver driver = new FirefoxDriver();
            driverThreadLocal.set(driver);
            setNonZeroTimeout();
        }
    }

    public static void setNonZeroTimeout() {
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public static void setZeroTimeout() {
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    public static void clearDriver() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
        }
        driverThreadLocal.set(null);
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static LocalDate parseDate(String dateString) {
        dateString = dateString.replaceAll("[^0-9.]", "");
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    public static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    private static Path getResultDir() {
        String directory = Utils.class.getResource("/.").getFile().substring(1);
        return Paths.get(directory).getParent().resolve(RESULT_DIR);
    }

    public static void createResultDir() throws IOException {
        Files.createDirectories(getResultDir());
    }

    private static Path getResultDirForClass(String className) {
        return getResultDir().resolve(className.replaceAll("[.]", "/"));
    }

    private static void deleteRecursively(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                for(File child : file.listFiles()) {
                    deleteRecursively(child);
                }
            }
            file.delete();
        }
    }

    public static void clearResultDir(String className) throws IOException {
        createResultDir();
        Path resultDir = getResultDirForClass(className);
        deleteRecursively(resultDir.toFile());
        Files.createDirectories(resultDir);
    }

    public static void storeScreenShot(String className, String methodName, byte [] data) throws IOException {
        Path resultFile = getResultDirForClass(className).resolve(methodName + ".png");
        FileOutputStream fos = new FileOutputStream(resultFile.toString());
        fos.write(data);
        fos.close();
    }

    public static boolean elementExists(WebElement parent, By by) {
        try {
            setZeroTimeout();
            return !parent.findElements(by).isEmpty();
        } finally {
            setNonZeroTimeout();
        }

    }
}
