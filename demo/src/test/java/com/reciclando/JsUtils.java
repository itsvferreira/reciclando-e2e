package com.reciclando;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JsUtils {
    private JavascriptExecutor js;

    public JsUtils(WebDriver driver) {
        this.js = (JavascriptExecutor) driver;
    }

    public void jsClick(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
