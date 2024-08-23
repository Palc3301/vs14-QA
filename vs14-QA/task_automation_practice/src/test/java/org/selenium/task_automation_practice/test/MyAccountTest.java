package org.selenium.task_automation_practice.test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.selenium.task_automation_practice.data.MyAccountData;
import org.selenium.task_automation_practice.dto.MyAccountDto;
import org.selenium.task_automation_practice.page.CreateAnAccountPage;
import org.selenium.task_automation_practice.data.CreateAnAccountData;
import org.selenium.task_automation_practice.dto.CreateAnAccountDto;
import org.selenium.task_automation_practice.page.MyAccountPage;
import org.selenium.task_automation_practice.selenium.Validation;

import static storys.CreateAnAccountStory.CE_CREATE_ACCOUNT_002;
import static storys.MyAccountStory.*;

@Epic(EPIC)
@Story(USER_STORY_LOGIN)
public class MyAccountTest extends BaseTest {

    MyAccountPage myAccountPage = new MyAccountPage();
    MyAccountData myAccountData = new MyAccountData();
    Validation validation = new Validation();
    CreateAnAccountPage createAnAccountPage = new CreateAnAccountPage();
    CreateAnAccountData createAnAccountData = new CreateAnAccountData();

    @Test
    @Description(CE_LOGIN_006)
    public void testValidarLoginDadosValidos(){

      MyAccountDto usuario =  myAccountData.loginDadosValidos();
      String mensagem = myAccountPage.fazerLogin(usuario.getEmail(), usuario.getSenha());
      validation.validateText("MY ACCOUNT", mensagem);
    }

    @Test
    @Description(CE_LOGIN_007)
    public void testTentarValidarLoginEmailFormatoInvalido() {
        MyAccountDto usuario = myAccountData.loginEmailFormatoInvalido();
        String mensagem = myAccountPage.fazerLoginEmailInvalido(usuario.getEmail(), usuario.getSenha());
        validation.validateText("Invalid email address.", mensagem);
    }

    @Test
    @Description(CE_LOGIN_008)
    public void testTentarValidarLoginEmailVazio() {
        MyAccountDto usuario = myAccountData.loginEmailVazio();
        String mensagem = myAccountPage.fazerLoginEmailVazio(usuario.getEmail(), usuario.getSenha());
        validation.validateText("An email address required.", mensagem);
    }

    @Test
    @Description(CE_LOGIN_009)
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
    @Description(CE_CREATE_ACCOUNT_002)
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
    
    @Test
    public void testValidarHistoricoPedidosComSucesso() {

        MyAccountDto usuario =  myAccountData.loginDadosValidos();
        String mensagem = myAccountPage.fazerLogin(usuario.getEmail(), usuario.getSenha());
        validation.validateText("MY ACCOUNT", mensagem);

        myAccountPage.verHistoricoPedidos();
    }

    @Test
    public void testTentarValidarHistoricoPedidosSemPedidos() {
        CreateAnAccountDto cadastro = createAnAccountData.cadastroDadosValidos();
        String mensagemCadastro = createAnAccountPage.cadastroValido(cadastro.getEmail(), cadastro.getFirstName(), cadastro.getLastName(), cadastro.getPassword());
        validation.validateText("Your account has been created.", mensagemCadastro);

        String mensagem = myAccountPage.verHistoricoPedidosSemPedidos();
        validation.validateText("You have not placed any orders.", mensagem);
    }
}
