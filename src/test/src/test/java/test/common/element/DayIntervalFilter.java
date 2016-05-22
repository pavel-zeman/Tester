package test.common.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Pavel Zeman
 */
public class DayIntervalFilter extends GuiElement {

    private WebElement element;

    private DayFilter dayFrom;
    private DayFilter dayTo;

    public DayIntervalFilter(WebElement element) {
        super(element);
        this.dayFrom = new DayFilter(element.findElement(By.xpath(".//dfe-date-time-editor[@editor-code='FROM']/div/div/input")));
        this.dayTo = new DayFilter(element.findElement(By.xpath(".//dfe-date-time-editor[@editor-code='TILL']/div/div/input")));
    }

    public DayFilter getDayFrom() {
        return dayFrom;
    }

    public DayFilter getDayTo() {
        return dayTo;
    }
}
