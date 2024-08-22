package org.selenium.task_automation_practice.test;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.selenium.task_automation_practice.data.MyAccountData;
import org.selenium.task_automation_practice.dto.MyAccountDto;
import org.selenium.task_automation_practice.page.MyAccountPage;
import org.selenium.task_automation_practice.selenium.Validation;

import static storys.LoginStory.*;

@Epic(EPIC)
@Story(USER_STORY_LOGIN)
public class MyAccountTest extends BaseTest {

    MyAccountPage loginPage = new MyAccountPage();
    MyAccountData loginData = new MyAccountData();
    Validation validation = new Validation();

    @Test
    public void test1validarLoginDadosValidos(){
        MyAccountDto usuario =  loginData.loginDadosValidos();
        String mensagem = loginPage.fazerLogin(usuario.getEmail(), usuario.getSenha());
        validation.validateText("MY ACCOUNT", mensagem);
    }

}
