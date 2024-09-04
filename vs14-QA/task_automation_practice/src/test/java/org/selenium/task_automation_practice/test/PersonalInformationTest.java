package org.selenium.task_automation_practice.test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.selenium.task_automation_practice.factory.data.CreateAnAccountData;
import org.selenium.task_automation_practice.factory.data.MyAccountData;
import org.selenium.task_automation_practice.dto.CreateAnAccountDto;
import org.selenium.task_automation_practice.page.CreateAnAccountPage;
import org.selenium.task_automation_practice.page.MyAccountPage;
import org.selenium.task_automation_practice.page.PersonalnformationPage;
import org.selenium.task_automation_practice.factory.selenium.Validation;

import static storys.PersonalInformationStory.*;

@Epic(EPIC)
@Story(USER_STORY_PERSONAL_INFORMATION)
public class PersonalInformationTest extends BaseTest{

    PersonalnformationPage personalnformationPage = new PersonalnformationPage();
    CreateAnAccountPage createAnAccountPage = new CreateAnAccountPage();
    CreateAnAccountData createAnAccountData = new CreateAnAccountData();
    MyAccountPage myAccountPage = new MyAccountPage();
    MyAccountData myAccountData = new MyAccountData();
    Validation validation = new Validation();
    CreateAnAccountDto cadastro;

    @BeforeEach
    public void setUp() {
        cadastro = createAnAccountData.cadastroDadosValidos();
        createAnAccountPage.cadastroValido(cadastro.getEmail(), cadastro.getFirstName(), cadastro.getLastName(), cadastro.getPassword());
    }

    @Test
    @Description(CE_INFORMATION_050)
    public void testAtualizarSenhaSucesso() {
        String mensagem = personalnformationPage.trocarSenhaSucesso(cadastro.getPassword());
        validation.validateText("Your personal information has been successfully updated.", mensagem);
    }

    @Test
    @Description(CE_INFORMATION_051)
    public void testNovasSenhasIncompativeis() {
        String mensagem = personalnformationPage.novasSenhasIncompativeis(cadastro.getPassword());
        validation.validateText("The password and confirmation do not match.", mensagem);
    }

    @Test
    @Description(CE_INFORMATION_052)
    public void testSenhaTamanhoInvalido() {
        String mensagem = personalnformationPage.novasSenhasTamanhoInvalido(cadastro.getPassword());
        validation.notNull(mensagem);
    }
}
