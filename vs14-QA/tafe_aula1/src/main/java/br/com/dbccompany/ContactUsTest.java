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

public class ContactUsTest {

    Faker faker = new Faker(new Locale("pt-BR"));

    public static WebDriver driver = new ChromeDriver();
    public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

    @BeforeTest
    public void abrirNavegador() {
        driver.get("https://www.automationexercise.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void testDeveEnviarFormularioContatoComSucesso() throws InterruptedException {
        String getInTouch = "#contact-page > div.row > div.col-sm-8 > div > h2";
        String btnContactUs = "#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(8) > a";
        String btnToHome = "#form-section > a > span";
        String btnSubmit = "#contact-us-form > div:nth-child(7) > input";
        String btnSubmitFile = "#contact-us-form > div:nth-child(6) > input";
        String successSubmit = "#contact-page > div.row > div.col-sm-8 > div > div.status.alert.alert-success";

        Thread.sleep(1000);
        // Procura e clica no Contact Us
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnContactUs)));
        driver.findElement(By.cssSelector(btnContactUs)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(getInTouch)));
        String getInTouchText = driver.findElement(By.cssSelector(getInTouch)).getText();

        Assert.assertEquals("GET IN TOUCH", getInTouchText);

        // Preenche campo name
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"name\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"name\"]")).sendKeys(faker.name().firstName());

        // Preenche campo email
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"email\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"email\"]")).sendKeys(faker.internet().emailAddress());

        // Preenche campo subject
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"subject\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"subject\"]")).sendKeys(faker.commerce().department());

        // Preenche campo message
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#message")));
        driver.findElement(By.cssSelector("#message")).sendKeys(faker.lorem().sentence());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnSubmitFile)));
        driver.findElement(By.cssSelector(btnSubmitFile)).sendKeys(System.getProperty("user.dir") + "\\src\\main\\java\\br\\com\\dbccompany\\exemplo.txt");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnSubmit)));
        driver.findElement(By.cssSelector(btnSubmit)).click();

        Thread.sleep(2000);
        driver.switchTo().alert().accept();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(successSubmit)));
        String successSubmitText = driver.findElement(By.cssSelector(successSubmit)).getText();

        Assert.assertEquals("Success! Your details have been submitted successfully.", successSubmitText);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnToHome)));
        driver.findElement(By.cssSelector(btnToHome)).click();
    }

    @AfterTest
    public static void finalizarNavegador() {
        driver.quit();
    }
}
