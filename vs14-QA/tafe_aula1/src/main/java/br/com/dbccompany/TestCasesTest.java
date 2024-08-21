package br.com.dbccompany;

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

public class TestCasesTest {

    public static WebDriver driver = new ChromeDriver();
    public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

    @BeforeTest
    public void abrirNavegador() {
        driver.get("https://www.automationexercise.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void testDeveVerificarPaginaTestCases() {

        String btnTestCase = "#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(5) > a";
        String testCase = "#form > div > div.row > div > h2 > b";

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnTestCase)));
        driver.findElement(By.cssSelector(btnTestCase)).click();

        String testCaseMsg = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(testCase))).getText();
        Assert.assertEquals(testCaseMsg, "TEST CASES");
    }

    @AfterTest
    public static void finalizarNavegador() {
        driver.quit();
    }
}
