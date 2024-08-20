package br.com.dbccompany;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;
import java.util.Locale;

public class LoginTest {

    Faker faker = new Faker(new Locale("pt-BR"));

    public static WebDriver driver = new ChromeDriver();
    public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    @BeforeTest
    public void abrirNavegador() {
        driver.get("https://www.automationexercise.com/login");
        driver.manage().window().maximize();
    }

    @Test
    //TestCase -01
    public void deveRegistrarComSucesso() {
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

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnDelete)));
        driver.findElement(By.cssSelector(btnDelete)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(accountDeleted)));
        String textAccountDeleted = driver.findElement(By.cssSelector(accountDeleted)).getText();
        Assert.assertEquals(textAccountDeleted, "ACCOUNT DELETED!");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnContinueDelete)));
        driver.findElement(By.cssSelector(btnContinueDelete)).click();
    }

    @Test
    //TesCase - 02
    public void deveFazerLoginComSucesso() {
        String btnLogin = "#form  div div div.col-sm-4.col-sm-offset-1 div  form > button";
        String btnLogout = "#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(4) > a";

        // 1- Esperar o campo de "login" carregar na tela e depois escrever no campo.
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"login-email\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"login-email\"]")).sendKeys("vs@gmail.com");

        // 2- Esperar o campo de "senha" carregar na tela e depois escrever no campo.
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa=\"login-password\"]")));
        driver.findElement(By.cssSelector("input[data-qa=\"login-password\"]")).sendKeys("123456");

        // 3 - Clicar no botão de login.
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnLogin)));
        driver.findElement(By.cssSelector(btnLogin)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnLogout)));
        String textBtnLogout = driver.findElement(By.cssSelector(btnLogout)).getText();

        Assert.assertEquals(textBtnLogout, "Logout");
    }

    /*
    @AfterTest
    public void finalizarNavegador() {
        driver.quit();
    }
    */
}
