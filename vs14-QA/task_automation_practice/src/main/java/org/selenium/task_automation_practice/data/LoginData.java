package org.selenium.task_automation_practice.data;

import org.selenium.task_automation_practice.dto.LoginDto;
import org.selenium.task_automation_practice.util.DataFakerGeneretor;

public class LoginData {

    DataFakerGeneretor dataFakerGeneretor = new DataFakerGeneretor();

    public LoginDto loginDadosValidos(){

        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("test-email@gmail.com");
        loginDto.setSenha("123abc");

        return loginDto;
    }

    public LoginDto LoginDadosDinamicos(){

        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(dataFakerGeneretor.emailFaker());
        loginDto.setSenha(dataFakerGeneretor.senhaFaker());

        return loginDto;
    }
}
