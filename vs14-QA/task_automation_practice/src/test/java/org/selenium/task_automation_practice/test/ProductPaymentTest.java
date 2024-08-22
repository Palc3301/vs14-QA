package org.selenium.task_automation_practice.test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.selenium.task_automation_practice.data.MyAccountData;
import org.selenium.task_automation_practice.dto.MyAccountDto;
import org.selenium.task_automation_practice.page.HomePage;
import org.selenium.task_automation_practice.page.MyAccountPage;
import org.selenium.task_automation_practice.page.ProductPaymentPage;
import org.selenium.task_automation_practice.selenium.Validation;

import static storys.ProductPaymentStory.*;

@Epic(EPIC)
@Story(USER_STORY_PRODUCT_PAYMENT)
public class ProductPaymentTest extends BaseTest{

    private ProductPaymentPage productPage = new ProductPaymentPage();
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

    @Test
    @Description(CE_PRODUCT_046)
    public void testFuncionalidadeValidarPagamentoComSucesso() throws InterruptedException {
        homePage.clickDressesMenu();
        productPage.clickProduct();
        productPage.clickInProduct();
        Thread.sleep(3000);
        productPage.clickAddBlueDress();
        Thread.sleep(3000);
        productPage.clickAddToCart();
        Thread.sleep(3000);
        productPage.clickProceedToCheckout();
        productPage.clickCheckoutToPayment();
        Thread.sleep(5000);
        productPage.fillTextAreaShipping();
        Thread.sleep(5000);
        productPage.clickCheckoutPaymentLorem();
        Thread.sleep(3000);
        productPage.clickTermsOfService();
        productPage.clickCheckoutAddressToPayment();
        Thread.sleep(3000);
        productPage.clickPaymentByBankWire();
        productPage.clickConfirmOrder();
    }

    @Test
    @Description(CE_PRODUCT_047)
    public void testFuncionalidadeTentarValidarPagamentoSemAceitarTermosDeServico() throws InterruptedException {
        homePage.clickDressesMenu();
        productPage.clickProduct();
        Thread.sleep(3000);
        productPage.clickInProduct();
        Thread.sleep(3000);
        productPage.clickAddBlueDress();
        Thread.sleep(3000);
        productPage.clickAddToCart();
        Thread.sleep(3000);
        productPage.clickProceedToCheckout();
        productPage.clickCheckoutToPayment();
        Thread.sleep(5000);
        productPage.fillTextAreaShipping();
        Thread.sleep(5000);
        productPage.clickCheckoutPaymentLorem();
        Thread.sleep(3000);
        productPage.clickCheckoutAddressToPayment();
        Thread.sleep(3000);
        productPage.closeTermsOfService();
    }

}
