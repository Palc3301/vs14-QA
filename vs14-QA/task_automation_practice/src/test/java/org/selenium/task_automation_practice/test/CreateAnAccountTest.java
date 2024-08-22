package org.selenium.task_automation_practice.test;

import org.junit.jupiter.api.Test;
import org.selenium.task_automation_practice.data.CreateAnAccountData;
import org.selenium.task_automation_practice.dto.CreateAnAccountDto;
import org.selenium.task_automation_practice.page.CreateAnAccountPage;
import org.selenium.task_automation_practice.selenium.Validation;

public class CreateAnAccountTest extends BaseTest {

    CreateAnAccountPage createAnAccountPage = new CreateAnAccountPage();
    CreateAnAccountData createAnAccountData = new CreateAnAccountData();
    Validation validation = new Validation();

    @Test
    public void testCadastroComSucesso() {
        CreateAnAccountDto cadastro = createAnAccountData.cadastroDadosValidos();
        String mensagem = createAnAccountPage.cadastroValido(cadastro.getEmail(), cadastro.getFirstName(), cadastro.getLastName(), cadastro.getPassword());
        validation.validateText("Your account has been created.", mensagem);
    }

    @Test
    public void testCadastroCamposVazios() {
        CreateAnAccountDto cadastro = createAnAccountData.cadastroDadosValidos();
        String mensagem = createAnAccountPage.cadastroComCamposVazios(cadastro.getEmail());
        validation.notNull(mensagem);
    }

    @Test
    public void testCadastroPasswordInvalida() {
        CreateAnAccountDto cadastro = createAnAccountData.cadastroPasswordInvalida();
        String mensagem = createAnAccountPage.cadastrarComPasswordInvalida(cadastro.getEmail(), cadastro.getFirstName(), cadastro.getLastName(), cadastro.getPassword());
        validation.notNull(mensagem);
    }

    @Test
    public void testCadastroDiaAtual() {
        CreateAnAccountDto cadastro = createAnAccountData.cadastroDadosValidos();
        createAnAccountPage.cadastroDiaAtual(cadastro.getEmail(), cadastro.getFirstName(), cadastro.getLastName(), cadastro.getPassword());
    }
}
