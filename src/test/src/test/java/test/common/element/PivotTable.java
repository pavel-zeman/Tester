package test.common.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Pavel Zeman
 */
public class PivotTable extends GuiElement {

    public PivotTable(WebElement element) {
        super(element);
    }

    public void waitForData() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // we can safely ignore this one
        }
        // use implicit wait
        getElement().findElement(By.xpath(".//th"));

        // wait for the "loading" layer to disappear
        getElement().findElement(By.xpath(".//div[@class='pq-loading' and @style='display: none;']"));
    }

    public String getValue(int row, int column) {
        return getElement().findElement(By.xpath(String.format(".//tr[@pq-row-indx=%d]/td[@pq-col-indx=%d]", row, column))).getText();
    }
}
