package org.selenium.task_automation_practice.page;

import org.openqa.selenium.By;

import org.selenium.task_automation_practice.factory.selenium.Interactions;

public class MyAccountPage extends Interactions {

    private static final By campoEmail = By.cssSelector("#email");
    private static final By campoSenha = By.cssSelector("#passwd");
    private static final By campoEmailCadastro = By.cssSelector("#email_create");
    private static final By btnEntrar = By.cssSelector("#SubmitLogin");
    private static final By btnSignIn = By.cssSelector("#header > div.nav > div > div > nav > div.header_user_info > a");
    private static final By btnForgotPassword = By.cssSelector("#login_form > div > p.lost_password.form-group > a");
    private static final By btnCreateAnAccount = By.cssSelector("#SubmitCreate");
    private static final By msgMyAccount = By.cssSelector("#center_column > h1");
    private static final By msgEmailInvalido = By.cssSelector("#center_column > div.alert.alert-danger > ol > li");
    private static final By msgEmailVazio = By.cssSelector("#center_column > div.alert.alert-danger > ol > li");
    private static final By msgPassowordInvalida = By.cssSelector("#center_column > div.alert.alert-danger > ol > li");
    private static final By msgPaginaForgot = By.cssSelector("#center_column > div > h1");
    private static final By msgPaginaCreate = By.cssSelector("#account-creation_form > div.account_creation > h3");
    private static final By msgEmailCadastroVazio = By.cssSelector("#create_account_error > ol > li");
    private static final By msgEmailCadastroFormatoInvalido = By.cssSelector("#create_account_error > ol > li");
    private static final By btnHistoricoPedidosComPedidos = By.cssSelector("#center_column > div > div > ul > li:nth-child(1) > a > span");
    private static final By btnHistoricoPedidos = By.cssSelector("#center_column > div > div > ul > li:nth-child(2) > a > span");
    private static final By msgHistoricoPedidos = By.cssSelector("#block-history > p");
    private static final By historicoPedidosItem = By.cssSelector("#order-list > tbody > tr.first_item");

    public String fazerLogin(String email, String password){
        click(btnSignIn);
        sendKeys(campoEmail, email);
        sendKeys(campoSenha, password);
        click(btnEntrar);

        return readText(msgMyAccount);
    }

    public String fazerLoginEmailInvalido(String email, String password) {
        click(btnSignIn);
        sendKeys(campoEmail, email);
        sendKeys(campoSenha, password);
        click(btnEntrar);

        return readText(msgEmailInvalido);
    }

    public String fazerLoginEmailVazio(String email, String senha) {
        click(btnSignIn);
        sendKeys(campoEmail, email);
        sendKeys(campoSenha, senha);
        click(btnEntrar);

        return readText(msgEmailVazio);
    }

    public String fazerLoginPasswordInvalida(String email, String password) {
        click(btnSignIn);
        sendKeys(campoEmail, email);
        sendKeys(campoSenha, password);
        click(btnEntrar);

        return readText(msgPassowordInvalida);
    }

    public String botaoForgotPassword() {
        click(btnSignIn);
        click(btnForgotPassword);
        presenceOfElementLocated(msgPaginaForgot);

        return readText(msgPaginaForgot);
    }

    public String criarContaComEmailValido(String email) {
        click(btnSignIn);
        sendKeys(campoEmailCadastro, email);
        click(btnCreateAnAccount);
        presenceOfElementLocated(msgPaginaCreate);

        return readText(msgPaginaCreate);
    }

    public String criarContaEmailVazio(String email) {
        click(btnSignIn);
        sendKeys(campoEmailCadastro, email);
        click(btnCreateAnAccount);

        return readText(msgEmailCadastroVazio);
    }

    public String criarContaEmailFormatoInvalido(String email) {
        click(btnSignIn);
        sendKeys(campoEmailCadastro, email);
        click(btnCreateAnAccount);

        return readText(msgEmailCadastroFormatoInvalido);
    }

    public void verHistoricoPedidos() {
        click(btnHistoricoPedidosComPedidos);
        presenceOfElementLocated(historicoPedidosItem);
    }

    public String verHistoricoPedidosSemPedidos() {
        click(btnHistoricoPedidos);
        return readText(msgHistoricoPedidos);
    }
}
