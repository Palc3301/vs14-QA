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
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    JavascriptExecutor js = (JavascriptExecutor) driver;
    Actions actions = new Actions(driver);

    @BeforeTest
    public void setUp() {
        driver.get("https://www.automationexercise.com");
        driver.manage().window().maximize();
    }

    //CT-11
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

    //CT-12
    @Test
    public void testDeveAdicionarProdutosAoCarrinho() {
        String btnProdutos = "#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(2)";
        String secaoProdutos = "body > section:nth-child(3) > div.container > div > div.col-sm-9.padding-right > div > div:nth-child(3) > div > div.single-products > div.product-overlay > div > a";
        String campoHover = "body > section:nth-child(3) > div.container > div > div.col-sm-9.padding-right > div > div:nth-child(3) > div";
        String btnHoverAdicionar = "body > section:nth-child(3) > div.container > div > div.col-sm-9.padding-right > div > div:nth-child(3) > div > div.single-products > div.product-overlay > div > a";
        String btnContinuarComprando = "#cartModal > div > div > div.modal-footer";
        String campoHover2 = "body > section:nth-child(3) > div.container > div > div.col-sm-9.padding-right > div > div:nth-child(4) > div";
        String btnHoverAdicionar2 = "body > section:nth-child(3) > div.container > div > div.col-sm-9.padding-right > div > div:nth-child(4) > div > div.single-products > div.product-overlay > div > a";
        String verCarrinho = "#cartModal > div > div > div.modal-body > p:nth-child(2) > a";
        String produto1 = "#product-1";
        String produto2 = "#product-2";
        String precoUnit = "#product-1 > td.cart_price > p";
        String quantidade = "#product-1 > td.cart_quantity > button";
        String valorTotal = "#product-1 > td.cart_total > p";

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("body")));

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(btnProdutos)));
        driver.findElement(By.cssSelector(btnProdutos)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(secaoProdutos)));
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(secaoProdutos)));

        WebElement hover = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(campoHover)));
        actions.moveToElement(hover).perform();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(btnHoverAdicionar)));
        driver.findElement(By.cssSelector(btnHoverAdicionar)).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(btnContinuarComprando)));
        driver.findElement(By.cssSelector(btnContinuarComprando)).click();

        WebElement hover2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(campoHover2)));
        actions.moveToElement(hover2).perform();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(btnHoverAdicionar2)));
        driver.findElement(By.cssSelector(btnHoverAdicionar2)).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(verCarrinho)));
        driver.findElement(By.cssSelector(verCarrinho)).click();

        WebElement p1 = driver.findElement(By.cssSelector(produto1));
        Assert.assertNotNull(p1);
        WebElement p2 = driver.findElement(By.cssSelector(produto2));
        Assert.assertNotNull(p2);
        WebElement precoUnidade = driver.findElement(By.cssSelector(precoUnit));
        Assert.assertNotNull(precoUnidade);
        WebElement qtd = driver.findElement(By.cssSelector(quantidade));
        Assert.assertNotNull(qtd);
        WebElement valorT = driver.findElement(By.cssSelector(valorTotal));
        Assert.assertNotNull(valorT);

    }

    //CT-13
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

    //CT-14
    @Test
    public void deveRegistrarDuranteFinalizaçãoDeCompra() {
        String secaoProdutos = "body > section:nth-child(3) > div > div > div.col-sm-9.padding-right > div.features_items > div:nth-child(3) > div.product-image-wrapper > div.single-products > div.product-overlay";
        String verProduto = "body > section:nth-child(3) > div.container > div > div.col-sm-9.padding-right > div.features_items > div:nth-child(3) > div > div.choose > ul";
        String campoQuantidade = "#quantity";
        String btnAdicionarCarrinho = "body > section > div > div > div.col-sm-9.padding-right > div.product-details > div.col-sm-7 > div > span > button";
        String verCarrinho = "#cartModal > div > div > div.modal-body > p:nth-child(2) > a";
        String continuarParaPagamento = "#do_action > div.container > div > div > a";
        String registrarLogin = "#checkoutModal > div > div > div.modal-body > p:nth-child(2) > a";
        String iconCarrinho = "#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(3) > a";

        String btnSignUpLogin = "#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(4) > a";
        String btnRegister = "#form > div > div > div:nth-child(3) > div > form > button";
        String btnContinue = "#form > div > div > div > div > a";
        String btnDelete = "#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(5) > a";
        String btnContinueDelete = "#form > div > div > div > div > a";

        String inputTitle = "#id_gender1";
        String inputNewsletter = "#form > div > div > div > div.login-form > form > div:nth-child(7) > label";
        String inputSpecialOffers = "#form > div > div > div > div.login-form > form > div:nth-child(8) > label";

        String selectDayOption = "#days > option:nth-child(6)";
        String selectNameMoth = "#months > option:nth-child(9)";
        String selectYearOption = "#years > option:nth-child(23)";
        String selectCountryOption = "#country > option:nth-child(3)";

        String newUserSignup = "#form > div > div > div:nth-child(3) > div > h2";
        String enterAccount = "#form > div > div > div > div.login-form > h2 > b";
        String accountCreated = "#form > div > div > div > h2 > b";
        String userNameIsVisible = "#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(10) > a > i";
        String accountDeleted = "#form > div > div > div > h2 > b";

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

        // Procura e clica no header botões
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnSignUpLogin)));
        driver.findElement(By.cssSelector(btnSignUpLogin)).click();

        // Verifica se New User Signup! está visivel
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(newUserSignup)));
        String textNewUser = driver.findElement(By.cssSelector(newUserSignup)).getText();
        Assert.assertEquals(textNewUser, "New User Signup!");

        // Preenche campo nome
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"signup-name\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"signup-name\"]")).sendKeys(faker.name().fullName());

        // Preenche campo email
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"signup-email\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"signup-email\"]")).sendKeys(faker.internet().emailAddress());

        // Click em registrar
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnRegister)));
        driver.findElement(By.cssSelector(btnRegister)).click();

        // Verifica se Enter Account Information está visivel
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(enterAccount)));
        String textEnterAccount = driver.findElement(By.cssSelector(enterAccount)).getText();
        Assert.assertEquals(textEnterAccount, "ENTER ACCOUNT INFORMATION");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(inputTitle)));
        driver.findElement(By.cssSelector(inputTitle)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"password\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"password\"]")).sendKeys(faker.internet().password());

        // Date To Year
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selectDayOption)));
        driver.findElement(By.cssSelector(selectDayOption)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selectNameMoth)));
        driver.findElement(By.cssSelector(selectNameMoth)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selectYearOption)));
        driver.findElement(By.cssSelector(selectYearOption)).click();
        //

        // Preenche campo sobrenome
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"first_name\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"first_name\"]")).sendKeys(faker.name().firstName());

        // Preenche campo ultimo sobrenome
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"last_name\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"last_name\"]")).sendKeys(faker.name().lastName());

        // Preenche nome da empresa
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"company\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"company\"]")).sendKeys(faker.company().name());

        // Preenche endereço
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"address\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"address\"]")).sendKeys(faker.address().streetAddress());

        // Preenche endereço2
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"address2\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"address2\"]")).sendKeys(faker.address().secondaryAddress());

        //Selecionar pais
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selectCountryOption)));
        driver.findElement(By.cssSelector(selectCountryOption)).click();

        // Preenche estado
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"state\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"state\"]")).sendKeys(faker.address().state());
        //Inputs
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(inputNewsletter)));
        driver.findElement(By.cssSelector(inputNewsletter)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(inputSpecialOffers)));
        driver.findElement(By.cssSelector(inputSpecialOffers)).click();

        // Preenche cidade
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"city\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"city\"]")).sendKeys(faker.address().city());

        // Preenche CEP
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"zipcode\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"zipcode\"]")).sendKeys(faker.address().zipCode());

        // Preenche telefone
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"mobile_number\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"mobile_number\"]")).sendKeys(faker.phoneNumber().cellPhone());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[data-qa=\"create-account\"]")));
        driver.findElement(By.cssSelector("button[data-qa=\"create-account\"]")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(accountCreated)));
        String textAccountCreated = driver.findElement(By.cssSelector(accountCreated)).getText();
        Assert.assertEquals(textAccountCreated, "ACCOUNT CREATED!");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnContinue)));
        driver.findElement(By.cssSelector(btnContinue)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(userNameIsVisible)));
        String textUserNameIsVisible = driver.findElement(By.cssSelector(userNameIsVisible)).getText();
        Assert.assertNotNull(textUserNameIsVisible);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(iconCarrinho)));
        driver.findElement(By.cssSelector(iconCarrinho)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(continuarParaPagamento)));
        driver.findElement(By.cssSelector(continuarParaPagamento)).click();

        String detalhesEndereço = "#cart_items > div > div:nth-child(2) > h2";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(detalhesEndereço)));
        WebElement detalheE = driver.findElement(By.cssSelector(detalhesEndereço));
        Assert.assertEquals("Address Details", detalheE.getText());

        String detalhesPedido = "#cart_items > div > div:nth-child(4) > h2";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(detalhesPedido)));
        WebElement detalheP = driver.findElement(By.cssSelector(detalhesPedido));
        Assert.assertEquals("Review Your Order", detalheP.getText());

        String textarea = "#ordermsg > textarea";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(textarea)));
        driver.findElement(By.cssSelector(textarea)).sendKeys(faker.lorem().paragraph(10));

        String btnRealizarPedido = "#cart_items > div > div:nth-child(7) > a";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnRealizarPedido)));
        driver.findElement(By.cssSelector(btnRealizarPedido)).click();

        String nomeNoCartao = "#payment-form > div:nth-child(2) > div > input";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(nomeNoCartao)));
        driver.findElement(By.cssSelector(nomeNoCartao)).sendKeys(faker.name().fullName());

        String numeroDoCartao = "#payment-form > div:nth-child(3) > div > input";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(numeroDoCartao)));
        driver.findElement(By.cssSelector(numeroDoCartao)).sendKeys(faker.finance().creditCard());

        String cvc = "#payment-form > div:nth-child(4) > div.col-sm-4.form-group.cvc > input";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cvc)));
        driver.findElement(By.cssSelector(cvc)).sendKeys(faker.number().digits(3));

        String mesExpiracao = "#payment-form > div:nth-child(4) > div:nth-child(2) > input";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mesExpiracao)));
        driver.findElement(By.cssSelector(mesExpiracao)).sendKeys(String.format("%02d", faker.number().numberBetween(1, 12)));

        String anoExpericao = "#payment-form > div:nth-child(4) > div:nth-child(3) > input";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(anoExpericao)));
        driver.findElement(By.cssSelector(anoExpericao)).sendKeys(String.format("%02d", faker.number().numberBetween(24, 30)));

        String confirmarPagamento = "#submit";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(confirmarPagamento)));
        driver.findElement(By.cssSelector(confirmarPagamento)).click();

        String pedidoConfirmado = "#form > div > div > div > p";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(pedidoConfirmado)));
        WebElement order = driver.findElement(By.cssSelector(pedidoConfirmado));
        Assert.assertEquals("Congratulations! Your order has been confirmed!", order.getText());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnDelete)));
        driver.findElement(By.cssSelector(btnDelete)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(accountDeleted)));
        String textAccountDeleted = driver.findElement(By.cssSelector(accountDeleted)).getText();
        Assert.assertEquals(textAccountDeleted, "ACCOUNT DELETED!");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnContinueDelete)));
        driver.findElement(By.cssSelector(btnContinueDelete)).click();
    }

    //CT-15
    @Test
    public void deveRegistrarPedidoAntesDeFinalizarConta() {
        String secaoProdutos = "body > section:nth-child(3) > div > div > div.col-sm-9.padding-right > div.features_items > div:nth-child(3) > div.product-image-wrapper > div.single-products > div.product-overlay";
        String verProduto = "body > section:nth-child(3) > div.container > div > div.col-sm-9.padding-right > div.features_items > div:nth-child(3) > div > div.choose > ul";
        String campoQuantidade = "#quantity";
        String btnAdicionarCarrinho = "body > section > div > div > div.col-sm-9.padding-right > div.product-details > div.col-sm-7 > div > span > button";
        String verCarrinho = "#cartModal > div > div > div.modal-body > p:nth-child(2) > a";
        String continuarParaPagamento = "#do_action > div.container > div > div > a";
        String registrarLogin = "#checkoutModal > div > div > div.modal-body > p:nth-child(2) > a";
        String iconCarrinho = "#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(3) > a";

        String btnSignUpLogin = "#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(4) > a";
        String btnRegister = "#form > div > div > div:nth-child(3) > div > form > button";
        String btnContinue = "#form > div > div > div > div > a";
        String btnDelete = "#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(5) > a";
        String btnContinueDelete = "#form > div > div > div > div > a";

        String inputTitle = "#id_gender1";
        String inputNewsletter = "#form > div > div > div > div.login-form > form > div:nth-child(7) > label";
        String inputSpecialOffers = "#form > div > div > div > div.login-form > form > div:nth-child(8) > label";

        String selectDayOption = "#days > option:nth-child(6)";
        String selectNameMoth = "#months > option:nth-child(9)";
        String selectYearOption = "#years > option:nth-child(23)";
        String selectCountryOption = "#country > option:nth-child(3)";

        String newUserSignup = "#form > div > div > div:nth-child(3) > div > h2";
        String enterAccount = "#form > div > div > div > div.login-form > h2 > b";
        String accountCreated = "#form > div > div > div > h2 > b";
        String userNameIsVisible = "#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(10) > a > i";
        String accountDeleted = "#form > div > div > div > h2 > b";

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("body")));

        // Procura e clica no header botões
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnSignUpLogin)));
        driver.findElement(By.cssSelector(btnSignUpLogin)).click();

        // Verifica se New User Signup! está visivel
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(newUserSignup)));
        String textNewUser = driver.findElement(By.cssSelector(newUserSignup)).getText();
        Assert.assertEquals(textNewUser, "New User Signup!");

        // Preenche campo nome
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"signup-name\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"signup-name\"]")).sendKeys(faker.name().fullName());

        // Preenche campo email
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"signup-email\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"signup-email\"]")).sendKeys(faker.internet().emailAddress());

        // Click em registrar
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnRegister)));
        driver.findElement(By.cssSelector(btnRegister)).click();

        // Verifica se Enter Account Information está visivel
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(enterAccount)));
        String textEnterAccount = driver.findElement(By.cssSelector(enterAccount)).getText();
        Assert.assertEquals(textEnterAccount, "ENTER ACCOUNT INFORMATION");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(inputTitle)));
        driver.findElement(By.cssSelector(inputTitle)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"password\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"password\"]")).sendKeys(faker.internet().password());

        // Date To Year
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selectDayOption)));
        driver.findElement(By.cssSelector(selectDayOption)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selectNameMoth)));
        driver.findElement(By.cssSelector(selectNameMoth)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selectYearOption)));
        driver.findElement(By.cssSelector(selectYearOption)).click();
        //

        // Preenche campo sobrenome
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"first_name\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"first_name\"]")).sendKeys(faker.name().firstName());

        // Preenche campo ultimo sobrenome
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"last_name\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"last_name\"]")).sendKeys(faker.name().lastName());

        // Preenche nome da empresa
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"company\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"company\"]")).sendKeys(faker.company().name());

        // Preenche endereço
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"address\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"address\"]")).sendKeys(faker.address().streetAddress());

        // Preenche endereço2
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"address2\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"address2\"]")).sendKeys(faker.address().secondaryAddress());

        //Selecionar pais
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selectCountryOption)));
        driver.findElement(By.cssSelector(selectCountryOption)).click();

        // Preenche estado
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"state\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"state\"]")).sendKeys(faker.address().state());
        //Inputs
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(inputNewsletter)));
        driver.findElement(By.cssSelector(inputNewsletter)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(inputSpecialOffers)));
        driver.findElement(By.cssSelector(inputSpecialOffers)).click();

        // Preenche cidade
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"city\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"city\"]")).sendKeys(faker.address().city());

        // Preenche CEP
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"zipcode\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"zipcode\"]")).sendKeys(faker.address().zipCode());

        // Preenche telefone
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"mobile_number\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"mobile_number\"]")).sendKeys(faker.phoneNumber().cellPhone());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[data-qa=\"create-account\"]")));
        driver.findElement(By.cssSelector("button[data-qa=\"create-account\"]")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(accountCreated)));
        String textAccountCreated = driver.findElement(By.cssSelector(accountCreated)).getText();
        Assert.assertEquals(textAccountCreated, "ACCOUNT CREATED!");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnContinue)));
        driver.findElement(By.cssSelector(btnContinue)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(userNameIsVisible)));
        String textUserNameIsVisible = driver.findElement(By.cssSelector(userNameIsVisible)).getText();
        Assert.assertNotNull(textUserNameIsVisible);

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

        String detalhesEndereço = "#cart_items > div > div:nth-child(2) > h2";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(detalhesEndereço)));
        WebElement detalheE = driver.findElement(By.cssSelector(detalhesEndereço));
        Assert.assertEquals("Address Details", detalheE.getText());

        String detalhesPedido = "#cart_items > div > div:nth-child(4) > h2";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(detalhesPedido)));
        WebElement detalheP = driver.findElement(By.cssSelector(detalhesPedido));
        Assert.assertEquals("Review Your Order", detalheP.getText());

        String textarea = "#ordermsg > textarea";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(textarea)));
        driver.findElement(By.cssSelector(textarea)).sendKeys(faker.lorem().paragraph(10));

        String btnRealizarPedido = "#cart_items > div > div:nth-child(7) > a";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnRealizarPedido)));
        driver.findElement(By.cssSelector(btnRealizarPedido)).click();

        String nomeNoCartao = "#payment-form > div:nth-child(2) > div > input";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(nomeNoCartao)));
        driver.findElement(By.cssSelector(nomeNoCartao)).sendKeys(faker.name().fullName());

        String numeroDoCartao = "#payment-form > div:nth-child(3) > div > input";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(numeroDoCartao)));
        driver.findElement(By.cssSelector(numeroDoCartao)).sendKeys(faker.finance().creditCard());

        String cvc = "#payment-form > div:nth-child(4) > div.col-sm-4.form-group.cvc > input";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cvc)));
        driver.findElement(By.cssSelector(cvc)).sendKeys(faker.number().digits(3));

        String mesExpiracao = "#payment-form > div:nth-child(4) > div:nth-child(2) > input";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mesExpiracao)));
        driver.findElement(By.cssSelector(mesExpiracao)).sendKeys(String.format("%02d", faker.number().numberBetween(1, 12)));

        String anoExpericao = "#payment-form > div:nth-child(4) > div:nth-child(3) > input";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(anoExpericao)));
        driver.findElement(By.cssSelector(anoExpericao)).sendKeys(String.format("%02d", faker.number().numberBetween(24, 30)));

        String confirmarPagamento = "#submit";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(confirmarPagamento)));
        driver.findElement(By.cssSelector(confirmarPagamento)).click();

        String pedidoConfirmado = "#form > div > div > div > p";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(pedidoConfirmado)));
        WebElement order = driver.findElement(By.cssSelector(pedidoConfirmado));
        Assert.assertEquals("Congratulations! Your order has been confirmed!", order.getText());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnDelete)));
        driver.findElement(By.cssSelector(btnDelete)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(accountDeleted)));
        String textAccountDeleted = driver.findElement(By.cssSelector(accountDeleted)).getText();
        Assert.assertEquals(textAccountDeleted, "ACCOUNT DELETED!");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnContinueDelete)));
        driver.findElement(By.cssSelector(btnContinueDelete)).click();
    }
}
