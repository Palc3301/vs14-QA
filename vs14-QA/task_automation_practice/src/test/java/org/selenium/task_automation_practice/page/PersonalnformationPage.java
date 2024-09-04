package org.selenium.task_automation_practice.page;

import org.openqa.selenium.By;
import org.selenium.task_automation_practice.factory.selenium.Interactions;
import org.selenium.task_automation_practice.util.DataFakerGeneretor;

public class PersonalnformationPage extends Interactions {

    public static final By btnMyPersonalInformation = By.cssSelector("#center_column > div > div > ul > li:nth-child(5)");
    public static final By campoPassword = By.cssSelector("#old_passwd");
    public static final By campoNewPassword = By.cssSelector("#passwd");
    public static final By campoConfirmation = By.cssSelector("#confirmation");
    public static final By btnSalvar = By.cssSelector("#center_column > div > form > fieldset > div:nth-child(10) > button");
    public static final By msgSucesso = By.cssSelector("#center_column > div > p");
    public static final By msgIncompativel = By.cssSelector("#center_column > div > div > ol > li");
    public static final By msgTamanhoInvalido = By.cssSelector("#center_column > div > div > ol > li");

    DataFakerGeneretor dataFakerGeneretor = new DataFakerGeneretor();

    public String trocarSenhaSucesso(String password) {

        String newPassword = dataFakerGeneretor.senhaFaker();

        click(btnMyPersonalInformation);
        sendKeys(campoPassword, password);
        sendKeys(campoNewPassword, newPassword);
        sendKeys(campoConfirmation, newPassword);
        click(btnSalvar);

        return readText(msgSucesso);
    }

    public String novasSenhasIncompativeis(String password) {

        click(btnMyPersonalInformation);
        sendKeys(campoPassword, password);
        sendKeys(campoNewPassword, dataFakerGeneretor.senhaFaker());
        sendKeys(campoConfirmation, dataFakerGeneretor.senhaFaker());
        click(btnSalvar);

        return readText(msgIncompativel);
    }

    public String novasSenhasTamanhoInvalido(String password) {

        click(btnMyPersonalInformation);
        sendKeys(campoPassword, password);
        sendKeys(campoNewPassword, dataFakerGeneretor.senhaFaker().substring(0, 3));
        sendKeys(campoConfirmation, dataFakerGeneretor.senhaFaker().substring(0, 3));
        click(btnSalvar);

        return readText(msgTamanhoInvalido);
    }
}
