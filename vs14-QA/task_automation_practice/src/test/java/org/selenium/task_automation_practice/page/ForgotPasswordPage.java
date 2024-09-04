package org.selenium.task_automation_practice.page;

import org.openqa.selenium.By;
import org.selenium.task_automation_practice.factory.selenium.Interactions;

public class ForgotPasswordPage extends Interactions {

    private static final By btnSignIn = By.cssSelector("#header > div.nav > div > div > nav > div.header_user_info > a");

    private static final By btnForgotPassword = By.cssSelector("#login_form > div > p.lost_password.form-group > a");

    private static final By btnRetrievePassword = By.cssSelector("#form_forgotpassword > fieldset > p > button");

    private static final By campoEmail = By.cssSelector("#email");

    //There is no account registered for this email address.
    private static final By msgEmailNaoRegistrado = By.cssSelector("#center_column > div > div > ol > li");

    //A confirmation email has been sent to your address: Grupo3DBC@gmail.com
    private static final By msgEnviadoSucesso = By.cssSelector("#center_column > div > p");

    //Invalid email address.
    private static final By msgEmailVazio = By.cssSelector("#center_column > div > div > ol > li");


    public String emailValido(String email) {
        click(btnSignIn);
        click(btnForgotPassword);
        sendKeys(campoEmail, email);
        click(btnRetrievePassword);

        return readText(msgEnviadoSucesso);
    }

    public String emailNaoRegistrado(String email) {
        click(btnSignIn);
        click(btnForgotPassword);
        sendKeys(campoEmail, email);
        click(btnRetrievePassword);

        return readText(msgEmailNaoRegistrado);
    }

    public String emailVazio(String email) {
        click(btnSignIn);
        click(btnForgotPassword);
        sendKeys(campoEmail, email);
        click(btnRetrievePassword);

        return readText(msgEmailVazio);
    }
}
