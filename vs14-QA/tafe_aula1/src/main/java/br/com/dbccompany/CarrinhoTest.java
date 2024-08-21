package br.com.dbccompany;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import java.util.Locale;

public class CarrinhoTest {

    Faker faker = new Faker(new Locale("PT-BR"));

    private WebDriver driver = new ChromeDriver();
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
    JavascriptExecutor js = (JavascriptExecutor) driver;
    Actions actions = new Actions(driver);
    LoginTest register = new LoginTest();

    @BeforeTest
    public void setUp() {
        driver.get("https://www.automationexercise.com");
        driver.manage().window().maximize();
    }

    @Test
    public void testDeveRealizarAssinatura() {
        String verCarrinho = "#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(3) > a";
        String assinatura = "#footer > div.footer-widget > div > div > div.col-sm-3.col-sm-offset-1 > div > h2";
        String inscritoComSucesso = "#success-subscribe > div";

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body")));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(verCarrinho)));
        driver.findElement(By.cssSelector(verCarrinho)).click();

        driver.findElement(By.cssSelector("body")).sendKeys(Keys.PAGE_DOWN);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(assinatura)));
        Assert.assertEquals(driver.findElement(By.cssSelector(assinatura)).getText(), "SUBSCRIPTION");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#susbscribe_email")));
        driver.findElement(By.cssSelector("#susbscribe_email")).sendKeys(faker.internet().emailAddress());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#subscribe")));
        driver.findElement(By.cssSelector("#subscribe")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(inscritoComSucesso)));
        Assert.assertEquals(driver.findElement(By.cssSelector(inscritoComSucesso)).getText(), "You have been successfully subscribed!");
    }

        @Test
        public void testDeveAdicionarProdutosAoCarrinho() throws InterruptedException{
            String btnProdutos = "#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(2)";
            String secaoProdutos = "body > section:nth-child(3) > div.container > div > div.col-sm-9.padding-right > div > div:nth-child(3) > div > div.single-products > div.product-overlay > div > a";
            String campoHover = "body > section:nth-child(3) > div.container > div > div.col-sm-9.padding-right > div > div:nth-child(3) > div > div.single-products > div.product-overlay";
            String btnHoverAdicionar = "body > section:nth-child(3) > div.container > div > div.col-sm-9.padding-right > div > div:nth-child(3) > div > div.single-products > div.product-overlay > div > a";

            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("body")));

            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(btnProdutos)));
            driver.findElement(By.cssSelector(btnProdutos)).click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(secaoProdutos)));
            js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(secaoProdutos)));
            Thread.sleep(2000);

            WebElement hover = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(campoHover)));
            actions.moveToElement(hover).perform();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnHoverAdicionar)));
            driver.findElement(By.cssSelector(btnHoverAdicionar)).click();
        }

    @Test
    public void testDeveVerificarQuantidadeDeProdutosNoCarrinho() {
        String secaoProdutos = "body > section:nth-child(3) > div > div > div.col-sm-9.padding-right > div.features_items > div:nth-child(3) > div.product-image-wrapper > div.single-products > div.product-overlay";
        String verProduto = "body > section:nth-child(3) > div.container > div > div.col-sm-9.padding-right > div.features_items > div:nth-child(3) > div > div.choose > ul";
        String detalhesProduto = "body > section > div > div > div.col-sm-9.padding-right > div.product-details > div.col-sm-7 > div";
        String campoQuantidade = "#quantity";
        String btnAdicionarCarrinho = "body > section > div > div > div.col-sm-9.padding-right > div.product-details > div.col-sm-7 > div > span > button";
        String verCarrinho = "#cartModal > div > div > div.modal-body > p:nth-child(2) > a";
        String quantidadeProduto = "#product-1 > td.cart_quantity > button";

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("body")));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(secaoProdutos)));
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(secaoProdutos)));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(verProduto)));
        driver.findElement(By.cssSelector(verProduto)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(detalhesProduto)));
        driver.findElement(By.cssSelector(detalhesProduto));

        WebElement campoQuantidadeValue = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(campoQuantidade)));
        campoQuantidadeValue.clear();
        campoQuantidadeValue.sendKeys("4");

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(btnAdicionarCarrinho)));
        driver.findElement(By.cssSelector(btnAdicionarCarrinho)).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(verCarrinho)));
        driver.findElement(By.cssSelector(verCarrinho)).click();

        WebElement quantidadeCarrinho = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(quantidadeProduto)));
        Assert.assertEquals("4", quantidadeCarrinho.getText());
    }

    @Test
    public void deveRegistrarDuranteFinalizaçãoDeCompra() {
        String secaoProdutos = "body > section:nth-child(3) > div > div > div.col-sm-9.padding-right > div.features_items > div:nth-child(3) > div.product-image-wrapper > div.single-products > div.product-overlay";
        String verProduto = "body > section:nth-child(3) > div.container > div > div.col-sm-9.padding-right > div.features_items > div:nth-child(3) > div > div.choose > ul";
        String campoQuantidade = "#quantity";
        String btnAdicionarCarrinho = "body > section > div > div > div.col-sm-9.padding-right > div.product-details > div.col-sm-7 > div > span > button";
        String verCarrinho = "#cartModal > div > div > div.modal-body > p:nth-child(2) > a";
        String continuarParaPagamento = "#do_action > div.container > div > div > a";
        String registrarLogin = "#checkoutModal > div > div > div.modal-body > p:nth-child(2) > a";

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("body")));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(secaoProdutos)));
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(secaoProdutos)));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(verProduto)));
        driver.findElement(By.cssSelector(verProduto)).click();

        WebElement campoQuantidadeValue = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(campoQuantidade)));
        campoQuantidadeValue.clear();
        campoQuantidadeValue.sendKeys("2");

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(btnAdicionarCarrinho)));
        driver.findElement(By.cssSelector(btnAdicionarCarrinho)).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(verCarrinho)));
        driver.findElement(By.cssSelector(verCarrinho)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("body")));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(continuarParaPagamento)));
        driver.findElement(By.cssSelector(continuarParaPagamento)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(registrarLogin)));
        driver.findElement(By.cssSelector(registrarLogin)).click();

        register.deveRegistrarComSucesso();
    }
}
