package org.selenium.task_automation_practice.test;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.selenium.task_automation_practice.data.CreateAnAccountData;
import org.selenium.task_automation_practice.data.MyAccountData;
import org.selenium.task_automation_practice.dto.CreateAnAccountDto;
import org.selenium.task_automation_practice.dto.MyAccountDto;
import org.selenium.task_automation_practice.page.CreateAnAccountPage;
import org.selenium.task_automation_practice.page.MyAccountPage;
import org.selenium.task_automation_practice.page.PersonalnformationPage;
import org.selenium.task_automation_practice.selenium.Validation;

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
    public void testAtualizarSenhaSucesso() {
        String mensagem = personalnformationPage.trocarSenhaSucesso(cadastro.getPassword());
        validation.validateText("Your personal information has been successfully updated.", mensagem);
    }

    @Test
    public void testNovasSenhasIncompativeis() {
        String mensagem = personalnformationPage.novasSenhasIncompativeis(cadastro.getPassword());
        validation.validateText("The password and confirmation do not match.", mensagem);
    }

    @Test
    public void testSenhaTamanhoInvalido() {
        String mensagem = personalnformationPage.novasSenhasTamanhoInvalido(cadastro.getPassword());
        validation.notNull(mensagem);
    }
}
