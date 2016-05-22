package test.common.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import test.common.Configuration;

/**
 * @author Pavel Zeman
 */
public class Page extends GuiElement {

    public Page(String ucCode) {
        getDriver().get(Configuration.TARGET_URL + ucCode);
    }

    public static Page get(String ucCode) {
        return new Page(ucCode);
    }

    public DayIntervalFilter getDayIntervalFilter() {
        return new DayIntervalFilter(getDriver().findElement(By.tagName("dfe-time-interval-editor")));
    }

    public ComboSingleSelect getComboSingleSelectFilter(String code) {
        return new ComboSingleSelect(getDriver().findElement(By.xpath("//dfe-combobox-editor-databound[@editor-code='" + code + "']")));
    }

    private WebElement getSubmitButton() {
        return getDriver().findElement(By.xpath("//button[@class='form-component__button--submit']"));
    }

    public void submit() {
        getSubmitButton().click();
    }

    public PivotTable getPivotTable() {
        return new PivotTable(getDriver().findElement(By.id("TS_PIVOT")));
    }

}
