package test.common.element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

import test.common.Utils;

/**
 * @author Pavel Zeman
 */
public class GuiElement {

    private WebElement element;

    public WebElement getElement() {
        return element;
    }

    public WebDriver getDriver() {
        return Utils.getDriver();
    }

    public GuiElement() {
        this(null);
    }

    public GuiElement(WebElement element) {
        this.element = element;
    }

    public void wait(Predicate isTrue) {
        WebDriverWait wait = new WebDriverWait(Utils.getDriver(), 5);
        wait.until(isTrue);
    }
}
