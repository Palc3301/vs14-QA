package org.selenium.task_automation_practice.test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.selenium.task_automation_practice.data.LoginData;
import org.selenium.task_automation_practice.dto.LoginDto;
import org.selenium.task_automation_practice.page.LoginPage;
import org.selenium.task_automation_practice.selenium.Validation;

import static storys.LoginStory.*;

@Epic(EPIC)
@Story(USER_STORY_LOGIN)
public class LoginTest extends BaseTest {

    LoginPage loginPage = new LoginPage();
    LoginData loginData = new LoginData();
    Validation validation = new Validation();

    @Test
    public void test1validarLoginDadosValidos(){
        LoginDto usuario =  loginData.loginDadosValidos();
        String mensagem = loginPage.fazerLogin(usuario.getEmail(), usuario.getSenha());
        validation.validateText("MY ACCOUNT", mensagem);
    }
}
