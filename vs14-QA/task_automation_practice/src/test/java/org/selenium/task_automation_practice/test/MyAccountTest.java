package org.selenium.task_automation_practice.test;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.selenium.task_automation_practice.data.CreateAnAccountData;
import org.selenium.task_automation_practice.data.MyAccountData;
import org.selenium.task_automation_practice.dto.CreateAnAccountDto;
import org.selenium.task_automation_practice.dto.MyAccountDto;
import org.selenium.task_automation_practice.page.CreateAnAccountPage;
import org.selenium.task_automation_practice.page.MyAccountPage;
import org.selenium.task_automation_practice.selenium.Validation;

import static storys.LoginStory.*;

@Epic(EPIC)
@Story(USER_STORY_LOGIN)
public class MyAccountTest extends BaseTest {

    MyAccountPage myAccountPage = new MyAccountPage();
    MyAccountData myAccountData = new MyAccountData();

    CreateAnAccountPage createAnAccountPage = new CreateAnAccountPage();
    CreateAnAccountData createAnAccountData = new CreateAnAccountData();

    Validation validation = new Validation();

    @Test
    public void test1validarLoginDadosValidos(){
        MyAccountDto usuario =  myAccountData.loginDadosValidos();
        String mensagem = myAccountPage.fazerLogin(usuario.getEmail(), usuario.getSenha());
        validation.validateText("MY ACCOUNT", mensagem);
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
