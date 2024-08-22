package org.selenium.task_automation_practice.data;

import org.selenium.task_automation_practice.dto.MyAccountDto;
import org.selenium.task_automation_practice.util.DataFakerGeneretor;

public class MyAccountData {

    DataFakerGeneretor dataFakerGeneretor = new DataFakerGeneretor();

    public MyAccountDto loginDadosValidos(){

        MyAccountDto loginDto = new MyAccountDto();
        loginDto.setEmail("Grupo3DBC@gmail.com");
        loginDto.setSenha("12345");

        return loginDto;
    }

    public MyAccountDto LoginEmailInvalido(){

        MyAccountDto loginDto = new MyAccountDto();
        loginDto.setEmail(dataFakerGeneretor.emailFaker().replace("@", ""));
        loginDto.setSenha(dataFakerGeneretor.senhaFaker());

        return loginDto;
    }

    public MyAccountDto LoginCamposVazios(){

        MyAccountDto loginDto = new MyAccountDto();
        loginDto.setEmail("");
        loginDto.setSenha("");

        return loginDto;
    }

    public MyAccountDto LoginPasswordInvalida(){

        MyAccountDto loginDto = new MyAccountDto();
        loginDto.setEmail(dataFakerGeneretor.emailFaker().replace("@", ""));
        loginDto.setSenha(dataFakerGeneretor.senhaFaker().substring(0, 3));

        return loginDto;
    }
}
