package org.selenium.task_automation_practice.test;

import org.junit.jupiter.api.Test;
import org.selenium.task_automation_practice.data.ForgotPasswordData;
import org.selenium.task_automation_practice.dto.ForgotPasswordDto;
import org.selenium.task_automation_practice.page.ForgotPasswordPage;
import org.selenium.task_automation_practice.selenium.Validation;

public class ForgotPasswordTest extends  BaseTest {

    ForgotPasswordData forgotPasswordData = new ForgotPasswordData();
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
    Validation validation = new Validation();

    @Test
    public void testValidarEnvioEmailSucesso() {
        ForgotPasswordDto email = forgotPasswordData.forgotEmailValido();
        String mensagem = forgotPasswordPage.emailValido(email.getEmail());
        validation.validateText("A confirmation email has been sent to your address: Grupo3DBC@gmail.com", mensagem);
    }

    @Test
    public void testValidarEnvioEmailNaoRegistrado() {
        ForgotPasswordDto email = forgotPasswordData.forgotEmailNaoCadastrado();
        String mensagem = forgotPasswordPage.emailNaoRegistrado(email.getEmail());
        validation.validateText("There is no account registered for this email address.", mensagem);
    }

    @Test
    public void testValidarEnvioEmailVazio() {
        ForgotPasswordDto email = forgotPasswordData.forgotEmailVazio();
        String mensagem = forgotPasswordPage.emailVazio(email.getEmail());
        validation.validateText("Invalid email address.", mensagem);
    }
}
