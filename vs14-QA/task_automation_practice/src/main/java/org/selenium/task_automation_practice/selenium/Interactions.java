package org.selenium.task_automation_practice.selenium;

import org.openqa.selenium.By;

import static org.selenium.task_automation_practice.selenium.Waits.waitElement;

public class Interactions extends Elements {

    protected static void click(By by) {
        waitElement(by);
        element(by).click();
    }

    protected static void sendKeys(By by, String text) {
        waitElement(by);
        element(by).sendKeys(text);
    }

    protected static String readText(By by) {
        waitElement(by);
        return element(by).getText();
    }

    protected static void sendTab(By by){
        waitElement(by);
        element(by).sendKeys("\t");
    }

    protected static void presenceOfElementLocated(By by) {
        waitElement(by);
    }

    protected static void clearKey(By by) {
        waitElement(by);
        element(by).clear();
    }
}
