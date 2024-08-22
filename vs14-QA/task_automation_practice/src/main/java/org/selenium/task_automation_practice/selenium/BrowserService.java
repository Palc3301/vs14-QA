package org.selenium.task_automation_practice.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowserService {

    public static WebDriver driver;
    public static WebDriverWait wait;

    public void chromeDriverInit(String url){

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get(url);
        driver.manage().window().maximize();

    }

    public void quit(){
        driver.quit();
    }
}
