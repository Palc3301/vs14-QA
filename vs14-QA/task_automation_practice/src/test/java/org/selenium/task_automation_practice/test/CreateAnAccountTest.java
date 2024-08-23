package org.selenium.task_automation_practice.test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.selenium.task_automation_practice.data.CreateAnAccountData;
import org.selenium.task_automation_practice.dto.CreateAnAccountDto;
import org.selenium.task_automation_practice.page.CreateAnAccountPage;
import org.selenium.task_automation_practice.selenium.Validation;

import static storys.CreateAnAccountStory.*;

@Epic(EPIC)
@Story(USER_STORY_CREATE)
public class CreateAnAccountTest extends BaseTest {

    CreateAnAccountPage createAnAccountPage = new CreateAnAccountPage();
    CreateAnAccountData createAnAccountData = new CreateAnAccountData();
    Validation validation = new Validation();

    @Test
    @Description(CE_CREATE_ACCOUNT_001)
    public void testCadastroComSucesso() {
        CreateAnAccountDto cadastro = createAnAccountData.cadastroDadosValidos();
        String mensagem = createAnAccountPage.cadastroValido(cadastro.getEmail(), cadastro.getFirstName(), cadastro.getLastName(), cadastro.getPassword());
        validation.validateText("Your account has been created.", mensagem);
    }

    @Test
    @Description(CE_CREATE_ACCOUNT_003)
    public void testCadastroCamposVazios() {
        CreateAnAccountDto cadastro = createAnAccountData.cadastroDadosValidos();
        String mensagem = createAnAccountPage.cadastroComCamposVazios(cadastro.getEmail());
        validation.notNull(mensagem);
    }

    @Test
    @Description(CE_CREATE_ACCOUNT_004)
    public void testCadastroPasswordInvalida() {
        CreateAnAccountDto cadastro = createAnAccountData.cadastroPasswordInvalida();
        String mensagem = createAnAccountPage.cadastrarComPasswordInvalida(cadastro.getEmail(), cadastro.getFirstName(), cadastro.getLastName(), cadastro.getPassword());
        validation.notNull(mensagem);
    }

    @Test
    @Description(CE_CREATE_ACCOUNT_005)
    public void testCadastroDiaAtual() {
        CreateAnAccountDto cadastro = createAnAccountData.cadastroDadosValidos();
        createAnAccountPage.cadastroDiaAtual(cadastro.getEmail(), cadastro.getFirstName(), cadastro.getLastName(), cadastro.getPassword());
    }
}
