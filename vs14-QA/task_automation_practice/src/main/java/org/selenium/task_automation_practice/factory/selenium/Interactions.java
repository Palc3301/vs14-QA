package org.selenium.task_automation_practice.factory.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class Interactions extends Elements {

    protected static void click(By by) {
        Waits.waitElement(by);
        element(by).click();
    }

    protected static void acceptAlert() {
        BrowserService.driver.switchTo().alert().accept();
    }
    protected static void sendKeys(By by, String text) {
        Waits.waitElement(by);
        element(by).sendKeys(text);
    }

    protected static String readText(By by) {
        Waits.waitElement(by);
        return element(by).getText();
    }

    protected static void hoverElement(By by){
        Waits.waitElement(by);
        Actions action = new Actions(driver);
        action.moveToElement(element(by)).perform();
    }

    protected static void presenceOfElementLocated(By by) {
        Waits.waitElement(by);
    }

    protected static void clearKey(By by) {
        Waits.waitElement(by);
        element(by).clear();
    }
}
