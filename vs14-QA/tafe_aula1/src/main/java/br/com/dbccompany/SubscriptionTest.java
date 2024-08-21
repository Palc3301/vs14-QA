package br.com.dbccompany;

import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import java.time.Duration;
import java.util.Locale;

public class SubscriptionTest {

    Faker faker = new Faker(new Locale("pt-BR"));

    public static WebDriver driver = new ChromeDriver();
    public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

    @BeforeTest
    public void abrirNavegador() {
        driver.get("https://www.automationexercise.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void testDeveValidarSubscricaoComSucesso() {

        String subscriptionField = "#footer > div.footer-widget > div > div > div.col-sm-3.col-sm-offset-1 > div > h2";
        String inputEmailSubscribe = "#susbscribe_email";
        String successSubscribe = "#success-subscribe > div";
        String btnArrowSubscribe = "#subscribe > i";

        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        driver.findElement(By.tagName("body")).sendKeys(Keys.END);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(subscriptionField)));
        String subscriptionFieldText = driver.findElement(By.cssSelector(subscriptionField)).getText();

        Assert.assertEquals("SUBSCRIPTION", subscriptionFieldText);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(inputEmailSubscribe)));
        driver.findElement(By.cssSelector(inputEmailSubscribe)).sendKeys(faker.internet().emailAddress());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnArrowSubscribe)));
        driver.findElement(By.cssSelector(btnArrowSubscribe)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(successSubscribe)));
        String successSubscribeText = driver.findElement(By.cssSelector(successSubscribe)).getText();
        Assert.assertEquals("You have been successfully subscribed!", successSubscribeText);
    }

    @AfterTest
    public static void finalizarNavegador() {
        driver.quit();
    }

}

