package org.selenium.task_automation_practice.test;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.selenium.task_automation_practice.data.MyAccountData;
import org.selenium.task_automation_practice.dto.MyAccountDto;
import org.selenium.task_automation_practice.page.CreateAnAccountPage;
import org.selenium.task_automation_practice.page.ForgotPasswordPage;
import org.selenium.task_automation_practice.page.MyAccountPage;
import org.selenium.task_automation_practice.selenium.Validation;

import static storys.LoginStory.*;

@Epic(EPIC)
@Story(USER_STORY_LOGIN)
public class MyAccountTest extends BaseTest {

    MyAccountPage myAccountPage = new MyAccountPage();
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
    CreateAnAccountPage createAnAccountPage = new CreateAnAccountPage();
    MyAccountData myAccountData = new MyAccountData();
    Validation validation = new Validation();

    @Test
    public void testValidarLoginDadosValidos(){
        MyAccountDto usuario =  myAccountData.loginDadosValidos();
        String mensagem = myAccountPage.fazerLogin(usuario.getEmail(), usuario.getSenha());
        validation.validateText("MY ACCOUNT", mensagem);
    }

    @Test
    public void testTentarValidarLoginEmailFormatoInvalido() {
        MyAccountDto usuario = myAccountData.loginEmailFormatoInvalido();
        String mensagem = myAccountPage.fazerLoginEmailInvalido(usuario.getEmail(), usuario.getSenha());
        validation.validateText("Invalid email address.", mensagem);
    }

    @Test
    public void testTentarValidarLoginEmailVazio() {
        MyAccountDto usuario = myAccountData.loginEmailVazio();
        String mensagem = myAccountPage.fazerLoginEmailVazio(usuario.getEmail(), usuario.getSenha());
        validation.validateText("An email address required.", mensagem);
    }

    @Test
    public void testTentarValidarLoginPasswordInvalida() {
        MyAccountDto usuario = myAccountData.loginPasswordInvalida();
        String mensagem = myAccountPage.fazerLoginPasswordInvalida(usuario.getEmail(), usuario.getSenha());
        validation.validateText("Invalid password.", mensagem);
    }

    @Test
    public void testValidarBotaoForgotPassword() {
        String mensagem = myAccountPage.botaoForgotPassword();
        validation.validateText("FORGOT YOUR PASSWORD?", mensagem);
    }

    @Test
    public void testValidarEmailCadastroComSucesso() {
        MyAccountDto email = myAccountData.cadastroEmailValido();
        String mensagem = myAccountPage.criarContaComEmailValido(email.getEmail());
        validation.validateText("YOUR PERSONAL INFORMATION", mensagem);
    }

    @Test
    public void testValidarEmailCadastroFormatoInvalido() {
        MyAccountDto email = myAccountData.cadastroEmailFormatoInvalido();
        String mensagem = myAccountPage.criarContaEmailFormatoInvalido(email.getEmail());
        validation.validateText("Invalid email address.", mensagem);
    }

    @Test
    public void testValidarEmailCadastroVazio() {
        MyAccountDto email = myAccountData.cadastroEmailFormatoInvalido();
        String mensagem = myAccountPage.criarContaEmailVazio(email.getEmail());
        validation.validateText("Invalid email address.", mensagem);
    }
}
