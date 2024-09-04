package org.selenium.task_automation_practice.test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.selenium.task_automation_practice.factory.data.ForgotPasswordData;
import org.selenium.task_automation_practice.dto.ForgotPasswordDto;
import org.selenium.task_automation_practice.page.ForgotPasswordPage;
import org.selenium.task_automation_practice.factory.selenium.Validation;

import static storys.ForgotPasswordStory.*;

@Epic(EPIC)
@Story(USER_STORY_FORGOT)
public class ForgotPasswordTest extends  BaseTest {

    ForgotPasswordData forgotPasswordData = new ForgotPasswordData();
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
    Validation validation = new Validation();

    @Test
    @Description(CE_FORGOT_021)
    public void testValidarEnvioEmailSucesso() {
        ForgotPasswordDto email = forgotPasswordData.forgotEmailValido();
        String mensagem = forgotPasswordPage.emailValido(email.getEmail());
        validation.validateText("A confirmation email has been sent to your address: Grupo3DBC@gmail.com", mensagem);
    }

    @Test
    @Description(CE_FORGOT_022)
    public void testValidarEnvioEmailVazio() {
        ForgotPasswordDto email = forgotPasswordData.forgotEmailVazio();
        String mensagem = forgotPasswordPage.emailVazio(email.getEmail());
        validation.validateText("Invalid email address.", mensagem);
    }

    @Test
    @Description(CE_FORGOT_023)
    public void testValidarEnvioEmailNaoRegistrado() {
        ForgotPasswordDto email = forgotPasswordData.forgotEmailNaoCadastrado();
        String mensagem = forgotPasswordPage.emailNaoRegistrado(email.getEmail());
        validation.validateText("There is no account registered for this email address.", mensagem);
    }
}
