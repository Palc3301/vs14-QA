package br.com.dbccompany;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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

public class DetailSearchProductTest {

    public static WebDriver driver = new ChromeDriver();
    public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

    @BeforeTest
    public void abrirNavegador() {
        driver.get("https://www.automationexercise.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void testDeveVerificarPaginasProductProductDetail() {
        String btnProducts = "#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(2) > a";
        String btnViewProduct1 = "body > section:nth-child(3) > div.container > div > div.col-sm-9.padding-right > div > div:nth-child(3) > div > div.choose";
        String productName = "body > section > div > div > div.col-sm-9.padding-right > div.product-details > div.col-sm-7 > div > h2";
        String productCategory = "body > section > div > div > div.col-sm-9.padding-right > div.product-details > div.col-sm-7 > div > p:nth-child(3)";
        String productAvaliability = "body > section > div > div > div.col-sm-9.padding-right > div.product-details > div.col-sm-7 > div > p:nth-child(6)";
        String productCondition = "body > section > div > div > div.col-sm-9.padding-right > div.product-details > div.col-sm-7 > div > p:nth-child(7)";
        String productBrand = "body > section > div > div > div.col-sm-9.padding-right > div.product-details > div.col-sm-7 > div > p:nth-child(8)";

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnProducts)));
        driver.findElement(By.cssSelector(btnProducts)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnViewProduct1)));
        driver.findElement(By.cssSelector(btnViewProduct1)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(productName)));
        String productNameText = driver.findElement(By.cssSelector(productName)).getText();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(productCategory)));
        String productCategoryText = driver.findElement(By.cssSelector(productCategory)).getText();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(productAvaliability)));
        String productAvaliabilityText = driver.findElement(By.cssSelector(productAvaliability)).getText();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(productCondition)));
        String productConditionText = driver.findElement(By.cssSelector(productCondition)).getText();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(productBrand)));
        String productBrandText = driver.findElement(By.cssSelector(productBrand)).getText();

        Assert.assertNotNull(productNameText);
        Assert.assertNotNull(productCategoryText);
        Assert.assertNotNull(productAvaliabilityText);
        Assert.assertNotNull(productConditionText);
        Assert.assertNotNull(productBrandText);
    }

    @Test
    public void testDeveBuscarProdutoComSucesso() {
        String btnProducts = "#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(2) > a";
        String allProducts = "body > section:nth-child(3) > div > div > div.col-sm-9.padding-right > div > h2";
        String inputSearch = "#search_product";
        String searchedProducts = "body > section:nth-child(3) > div > div > div.col-sm-9.padding-right > div > h2";
        String firstProductName = "body > section:nth-child(3) > div > div > div.col-sm-9.padding-right > div > div:nth-child(3) > div.product-image-wrapper > div.single-products > div.product-overlay > div > p";

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnProducts)));
        driver.findElement(By.cssSelector(btnProducts)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(allProducts)));
        String allProductsText = driver.findElement(By.cssSelector(allProducts)).getText();

        Assertions.assertEquals("ALL PRODUCTS", allProductsText);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(inputSearch)));
        driver.findElement(By.cssSelector(inputSearch)).sendKeys("shirt");

        String searchedProductsText = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(searchedProducts))).getText();

        Assert.assertEquals("ALL PRODUCTS", searchedProductsText);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(firstProductName)));
        String firstProductNameText = driver.findElement(By.cssSelector(firstProductName)).getText();

        Assert.assertNotNull(firstProductNameText);
    }

    @AfterTest
    public static void finalizarNavegador() {
        driver.quit();
    }

    @AfterEach
    public void voltarHome() {

        String btnHome = "#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(1) > a";

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnHome)));
        driver.findElement(By.cssSelector(btnHome)).click();

    }
}
