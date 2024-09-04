package org.selenium.task_automation_practice.page;

import org.openqa.selenium.By;
import org.selenium.task_automation_practice.factory.selenium.Interactions;

public class CreateAnAccountPage extends Interactions {

    private static final By btnSignIn = By.cssSelector("#header > div.nav > div > div > nav > div.header_user_info > a");
    private static final By campoEmail = By.cssSelector("#email_create");
    private static final By btnCreateAnAccount = By.cssSelector("#SubmitCreate");
    private static final By inputTitle = By.cssSelector("#id_gender1");
    private static final By campoFirstName = By.cssSelector("#customer_firstname");
    private static final By campoLastName = By.cssSelector("#customer_lastname");
    private static final By campoPassword = By.cssSelector("#passwd");
    private static final By selectData = By.cssSelector("#days");
    private static final By optionData = By.cssSelector("#days > option:nth-child(23)");
    private static final By selectMes = By.cssSelector("#months");
    private static final By optionMes = By.cssSelector("#months > option:nth-child(9)");
    private static final By selectAno = By.cssSelector("#years");
    private static final By optionAno = By.cssSelector("#years > option:nth-child(2)");
    private static final By checkboxNewsletter = By.cssSelector("#newsletter");
    private static final By btnRegister = By.cssSelector("#submitAccount");
    private static final By msgCadastradoSucesso = By.cssSelector("#center_column > p.alert.alert-success");
    private static final By msgCamposVazios = By.cssSelector("#center_column > div");
    private static final By msgSenhaInvalida = By.cssSelector("#center_column > div > ol > li");

    public String cadastroValido(String email, String firstName, String lastName, String password) {
        click(btnSignIn);
        sendKeys(campoEmail, email);
        click(btnCreateAnAccount);
        click(inputTitle);
        sendKeys(campoFirstName, firstName);
        sendKeys(campoLastName, lastName);
        sendKeys(campoPassword, password);
        click(selectData);
        click(optionData);
        click(selectMes);
        click(optionMes);
        click(selectAno);
        click(optionAno);
        click(checkboxNewsletter);
        click(btnRegister);

        return readText(msgCadastradoSucesso);
    }

    public String cadastroComCamposVazios(String email) {
        click(btnSignIn);
        sendKeys(campoEmail, email);
        click(btnCreateAnAccount);
        click(btnRegister);

        return readText(msgCamposVazios);
    }

    public String cadastrarComPasswordInvalida(String email, String firstName, String lastName, String password) {
        click(btnSignIn);
        sendKeys(campoEmail, email);
        click(btnCreateAnAccount);
        click(inputTitle);
        sendKeys(campoFirstName, firstName);
        sendKeys(campoLastName, lastName);
        sendKeys(campoPassword, password);
        click(selectData);
        click(optionData);
        click(selectMes);
        click(optionMes);
        click(selectAno);
        click(optionAno);
        click(checkboxNewsletter);
        click(btnRegister);

        return readText(msgSenhaInvalida);
    }

    public void cadastroDiaAtual(String email, String firstName, String lastName, String password) {
        click(btnSignIn);
        sendKeys(campoEmail, email);
        click(btnCreateAnAccount);
        click(inputTitle);
        sendKeys(campoFirstName, firstName);
        sendKeys(campoLastName, lastName);
        sendKeys(campoPassword, password);
        click(selectData);
        click(optionData);
        click(selectMes);
        click(optionMes);
        click(selectAno);
        click(optionAno);
        click(checkboxNewsletter);
        click(btnRegister);
    }
}
