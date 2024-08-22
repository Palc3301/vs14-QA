package org.selenium.task_automation_practice.data;

import org.selenium.task_automation_practice.dto.LoginDto;
import org.selenium.task_automation_practice.util.DataFakerGeneretor;

public class LoginData {

    DataFakerGeneretor dataFakerGeneretor = new DataFakerGeneretor();

    public LoginDto loginDadosValidos(){

        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("Grupo3DBC@gmail.com");
        loginDto.setSenha("12345");

        return loginDto;
    }

    public LoginDto LoginEmailInvalido(){

        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(dataFakerGeneretor.emailFaker().replace("@", ""));
        loginDto.setSenha(dataFakerGeneretor.senhaFaker());

        return loginDto;
    }

    public LoginDto LoginCamposVazios(){

        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("");
        loginDto.setSenha("");

        return loginDto;
    }

    public LoginDto LoginPasswordInvalida(){

        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(dataFakerGeneretor.emailFaker().replace("@", ""));
        loginDto.setSenha(dataFakerGeneretor.senhaFaker().substring(0, 3));

        return loginDto;
    }
}
