package org.selenium.task_automation_practice.page;

import org.openqa.selenium.By;
import org.selenium.task_automation_practice.selenium.Interactions;

public class LoginPage extends Interactions {

    private static final By campoEmail =
    By.cssSelector("input[data-qa=\"login-email\"]");

    private static final By campoSenha =
    By.cssSelector("input[data-qa=\"login-password\"]");

    public void preencherCampoEmail(String email){
        sendKeys(campoEmail,email);
    }
    public void preencherCampoSenha(String senha){
        sendKeys(campoSenha,senha);
    }

//    public String fazerLogin(String email, String senha){
//        sendKeys(campoEmail,email);
//        sendKeys(campoSenha,email);
//
//        return readText(msgLoginBemSucedido);
//    }
}
