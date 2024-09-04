package org.selenium.task_automation_practice.test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.selenium.task_automation_practice.factory.data.MyAccountData;
import org.selenium.task_automation_practice.dto.MyAccountDto;
import org.selenium.task_automation_practice.page.ContactUsPage;
import org.selenium.task_automation_practice.page.MyAccountPage;
import org.selenium.task_automation_practice.factory.selenium.Validation;

import static storys.ContactUsStory.*;

@Epic(EPIC)
@Story(USER_STORY_CONTACT)
public class ContactUsTest extends BaseTest {

    ContactUsPage contactUsPage = new ContactUsPage();
    MyAccountPage myAccountPage = new MyAccountPage();
    MyAccountData myAccountData = new MyAccountData();
    Validation validation = new Validation();

    @BeforeEach
    public void setUp() {
        MyAccountDto usuario = myAccountData.loginDadosValidos();
        myAccountPage.fazerLogin(usuario.getEmail(), usuario.getSenha());
    }

    @Test
    @Description(CE_CONTACT_021)
    public void testEnvioMensagemSucesso() {
        String mensagem = contactUsPage.enviarMensagemSucesso();
        validation.validateText("Your message has been successfully sent to our team.", mensagem);
    }

    @Test
    @Description(CE_CONTACT_022)
    public void testEnvioMensagemTextareaVazio() {
        String mensagem = contactUsPage.enviarMensagemTextareaVazio();
        validation.validateText("The message cannot be blank.", mensagem);
    }

    @Test
    @Description(CE_CONTACT_023)
    public void testEnvioMensagemEmailFormatoInvalido() {
        String mensagem = contactUsPage.enviarMensagemEmailFormatoInvalido();
        validation.validateText("Invalid email address.", mensagem);
    }
}
