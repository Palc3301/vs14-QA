package org.selenium.task_automation_practice.page;

import org.openqa.selenium.By;
import org.selenium.task_automation_practice.selenium.Interactions;

public class MyAccountPage extends Interactions {

    private static final By btnSignIn = By.cssSelector("#header > div.nav > div > div > nav > div.header_user_info > a");
    private static final By btnHistoricoPedidosComPedidos = By.cssSelector("#center_column > div > div > ul > li:nth-child(1) > a > span");
    private static final By btnHistoricoPedidos = By.cssSelector("#center_column > div > div > ul > li:nth-child(2) > a > span");
    private static final By campoEmail =
    By.cssSelector("#email");

    private static final By campoSenha =
    By.cssSelector("#passwd");

    private static final By btnEntrar = By.cssSelector("#SubmitLogin");

    private static final By msgMyAccount = By.cssSelector("#center_column > h1");
    private static final By msgHistoricoPedidos = By.cssSelector("#block-history > p");
    private static final By historicoPedidosItem = By.cssSelector("#order-list > tbody > tr.first_item");
    public String fazerLogin(String email, String senha){
        click(btnSignIn);
        sendKeys(campoEmail, email);
        sendKeys(campoSenha, senha);
        click(btnEntrar);

        return readText(msgMyAccount);
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
