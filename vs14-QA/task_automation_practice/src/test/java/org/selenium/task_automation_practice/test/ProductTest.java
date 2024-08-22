package org.selenium.task_automation_practice.test;

import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.selenium.task_automation_practice.data.MyAccountData;
import org.selenium.task_automation_practice.dto.MyAccountDto;
import org.selenium.task_automation_practice.page.HomePage;
import org.selenium.task_automation_practice.page.MyAccountPage;
import org.selenium.task_automation_practice.page.ProductPage;
import org.selenium.task_automation_practice.selenium.Validation;

import static org.junit.Assert.assertTrue;
import static storys.ProductStory.CE_PRODUCT_036;
import static storys.ProductStory.CE_PRODUCT_037;

public class ProductTest extends BaseTest{

    private ProductPage productPage = new ProductPage();
    private HomePage homePage = new HomePage();
    private MyAccountPage loginPage = new MyAccountPage();
    private MyAccountData loginData = new MyAccountData();
    Validation validation = new Validation();

    @BeforeEach
    public void setup () {
        MyAccountDto loginDto =  loginData.loginDadosValidos();
        String mensagem = loginPage.fazerLogin(loginDto.getEmail(), loginDto.getSenha());
        validation.validateText("MY ACCOUNT", mensagem);
        homePage.clickLogo();
    }

    @Test
    @Description(CE_PRODUCT_036)
    public void testFuncionalidadeAddToCart() throws InterruptedException {
        homePage.clickDressesMenu();
        productPage.clickProduct();
        productPage.clickInProduct();
        Thread.sleep(3000);
        productPage.clickAddBlueDress();
        Thread.sleep(3000);
        productPage.clickAddToCart();
        Thread.sleep(3000);
        productPage.clickProceedToCheckout();
    }

    @Test
    @Description(CE_PRODUCT_037)
    public void testFuncionalidadeAddToCartDuplicado() throws InterruptedException {
        homePage.clickDressesMenu();
        productPage.clickProduct();
        productPage.clickInProduct();
        Thread.sleep(3000);
        productPage.clickAddBlueDress();
        Thread.sleep(3000);
        productPage.clickAddToCart();
        Thread.sleep(3000);
        productPage.clickProceedToCheckout();

        homePage.clickDressesMenu();
        productPage.clickProduct();
        productPage.clickInProduct();
        Thread.sleep(3000);
        productPage.clickAddBlueDress();
        Thread.sleep(3000);
        productPage.clickAddToCart();
        Thread.sleep(3000);
        productPage.clickProceedToCheckout();
    }
}
