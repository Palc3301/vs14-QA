package org.selenium.task_automation_practice.page;

import org.openqa.selenium.By;
import org.selenium.task_automation_practice.factory.selenium.Interactions;
import org.selenium.task_automation_practice.util.DataFakerGeneretor;

public class ContactUsPage extends Interactions {

    private static final By btnContactUs = By.cssSelector("#contact-link");
    private static final By selectSubject = By.cssSelector("#id_contact");
    private static final By optionSubject = By.cssSelector("#id_contact > option:nth-child(2)");
    private static final By selectReference = By.cssSelector("#center_column > form > fieldset > div.clearfix > div.col-xs-12.col-md-3 > div:nth-child(6) > div > select");
    private static final By optionReference = By.cssSelector("#center_column > form > fieldset > div.clearfix > div.col-xs-12.col-md-3 > div:nth-child(6) > div > select > option:nth-child(2)");
    private static final By campoEmail = By.cssSelector("#email");
    private static final By file = By.cssSelector("#fileUpload");
    private static final By textArea = By.cssSelector("#message");
    private static final By btnEnviar = By.cssSelector("#submitMessage");
    private static final By msgSucesso = By.cssSelector("#center_column > p");
    private static final By msgTextareaVazio = By.cssSelector("#center_column > div > ol > li");
    private static final By msgEmailFormatoInvalido = By.cssSelector("#center_column > div > ol > li");

    DataFakerGeneretor dataFakerGeneretor = new DataFakerGeneretor();

    public String enviarMensagemSucesso() {
        click(btnContactUs);
        click(selectSubject);
        click(optionSubject);
        click(selectReference);
        click(optionReference);
        sendKeys(textArea, dataFakerGeneretor.textAreaFaker());
        click(btnEnviar);

        return readText(msgSucesso);
    }

    public String enviarMensagemTextareaVazio() {
        click(btnContactUs);
        click(btnEnviar);

        return readText(msgTextareaVazio);
    }

    public String enviarMensagemEmailFormatoInvalido() {
        click(btnContactUs);
        click(selectSubject);
        click(optionSubject);
        clearKey(campoEmail);
        sendKeys(campoEmail, dataFakerGeneretor.emailFaker().replace("@", ""));
        click(selectReference);
        click(optionReference);
        sendKeys(textArea, dataFakerGeneretor.textAreaFaker());
        click(btnEnviar);

        return readText(msgEmailFormatoInvalido);
    }
}
