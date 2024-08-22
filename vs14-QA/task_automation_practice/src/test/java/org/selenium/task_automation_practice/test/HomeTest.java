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
import org.selenium.task_automation_practice.selenium.Validation;
import org.selenium.task_automation_practice.util.DataFakerGeneretor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static storys.HomeStory.*;

@Epic(EPIC)
@Story(USER_STORY_HOME)
public class HomeTest extends BaseTest {

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

    // Button and Menu Tests
    @Test
    @Description(CE_HOME_025)
    public void testFuncionalidadeWomen() {
        homePage.clickWomenMenu();
    }

    @Test
    @Description(CE_HOME_026)
    public void testFuncionalidadeDresses() {
        homePage.clickDressesMenu();
    }

    @Test
    @Description(CE_HOME_027)
    public void testFuncionalidadeTShirts() {
        homePage.clickTShirtsMenu();
    }

    @Test
    @Description(CE_HOME_028)
    public void testFuncionalidadeBlog() {
        homePage.clickBlogMenu();
    }

    @Test
    @Description(CE_HOME_029)
    public void testFuncionalidadeLogo() {
        homePage.clickTShirtsMenu();
        homePage.clickLogo();
    }

    @Test
    @Description(CE_HOME_030)
    public void testFuncionalidadeGetSavings() {
        homePage.clickTShirtsMenu();
        homePage.clickLogo();
    }

    @Test
    @Description(CE_HOME_031)
    public void testFuncionalidadeShopNow() {
        homePage.clickShopNow();
    }

    @Test
    @Description(CE_HOME_032)
    public void testFuncionalidadePopular() {
        homePage.clickBestSellers();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        homePage.clickPopular();
    }

    @Test
    @Description(CE_HOME_033)
    public void testFuncionalidadeBestSellers() {
        homePage.clickBestSellers();
    }

    @Test
    @Description(CE_HOME_034)
    public void testFuncionalidadeQuickView() {
        homePage.clickBestSellers();
        homePage.clickQuickView();
    }

    @Test
    @Description(CE_HOME_035)
    public void testFuncionalidadeMore() {
        homePage.clickBestSellers();
        homePage.clickMore();
    }

    // SECTION CART TESTS
    @Test
    @Description(CE_HOME_038)
    public void testFuncionalidadeCarrinho() {
        homePage.clickCart();
    }

    @Test
    @Description(CE_HOME_039)
    public void testFuncionalidadeCarrinhoVazio() {
        homePage.clickCart();
        assertTrue(homePage.isCartEmpty());
    }


    // SECTION SEARCH TESTS
    @Test
    @Description(CE_HOME_040)
    public void testFuncionalidadeBuscar() {
        homePage.enterSearchQuery("Dress");
        homePage.clickSearchButton();
        assertTrue(homePage.isSearchResultVisible());
    }

    @Test
    @Description(CE_HOME_041)
    public void testFuncionalidadeBuscarInvalida() {
        homePage.enterSearchQuery("Teste");
        homePage.clickSearchButton();
        assertFalse(homePage.isSearchResultVisibleF());
    }

    @Test
    @Description(CE_HOME_042)
    public void testFuncionalidadeBuscarVazia() {
        homePage.enterSearchQuery(" ");
        homePage.clickSearchButton();
        assertFalse(homePage.isSearchResultVisibleF());
    }

    @Test
    @Description(CE_HOME_043)
    public void testDeveCadastrarEmailNewsletterSucesso() {
        String message = homePage.enterNewsletterEmail();
        validation.validateText("Newsletter : You have successfully subscribed to this newsletter.", message);
    }

    @Test
    @Description(CE_HOME_044)
    public void testTentarCadastrarEmailNewsletterFormatoInvalido() {
        String message = homePage.enterNewsletterInvalidEmail();
        validation.validateText("Newsletter : Invalid email address.", message);
    }

    @Test
    @Description(CE_HOME_044)
    public void testTentarCadastrarEmailNewsletterEmailVazio() {
        String message = homePage.enterNewsletterEmailVazio();
        validation.validateText("Newsletter : Invalid email address.", message);
    }
}