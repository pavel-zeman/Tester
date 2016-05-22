package test.common.element;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import test.common.constant.DefaultValue;

/**
 * @author Pavel Zeman
 */
public class ComboSingleSelect extends GuiElement {

    public ComboSingleSelect(WebElement element) {
        super(element);
    }

    private WebElement getValueElement() {
        return getElement().findElement(By.xpath(".//a/span/span"));
    }

    private WebElement getInputElement() {
        return getElement().findElement(By.xpath(".//div/div/div/div/input"));
    }

    public void waitForDefaultValueSet() {
        wait(webDriver -> !DefaultValue.COMBO.equals(getValueElement().getText()));
        wait(webDriver -> !getValueElement().getText().isEmpty());
    }

    public String getValue() {
        return getValueElement().getText();
    }

    public void setValue(String value) {
        getElement().click();
        getInputElement().sendKeys(value + Keys.TAB);
    }
}
