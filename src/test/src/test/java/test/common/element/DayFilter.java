package test.common.element;

import java.time.LocalDate;

import org.openqa.selenium.WebElement;

import test.common.Utils;

/**
 * @author Pavel Zeman
 */
public class DayFilter extends GuiElement {

    public DayFilter(WebElement element) {
        super(element);
    }

    public LocalDate getValue() {
        wait(webDriver -> !getElement().getAttribute("value").isEmpty());
        return Utils.parseDate(getElement().getAttribute("value"));
    }

    public void setValue(int day, int month, int year) {
        getElement().clear();
        getElement().sendKeys(Utils.formatDate(LocalDate.of(year, month, day)));
    }
}
