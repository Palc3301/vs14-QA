package org.selenium.task_automation_practice.factory.data;

import org.selenium.task_automation_practice.dto.MyAccountDto;
import org.selenium.task_automation_practice.util.DataFakerGeneretor;

public class MyAccountData {

    DataFakerGeneretor dataFakerGeneretor = new DataFakerGeneretor();

    public MyAccountDto loginDadosValidos(){

        MyAccountDto loginDto = new MyAccountDto();
        loginDto.setEmail("Grupo3@gmail.com");
        loginDto.setSenha("12345");

        return loginDto;
    }

    public MyAccountDto loginEmailFormatoInvalido(){

        MyAccountDto loginDto = new MyAccountDto();
        loginDto.setEmail(dataFakerGeneretor.emailFaker().replace("@", ""));
        loginDto.setSenha(dataFakerGeneretor.senhaFaker());

        return loginDto;
    }

    public MyAccountDto loginEmailVazio(){

        MyAccountDto loginDto = new MyAccountDto();
        loginDto.setEmail("");
        loginDto.setSenha(dataFakerGeneretor.senhaFaker());

        return loginDto;
    }

    public MyAccountDto loginPasswordInvalida(){

        MyAccountDto loginDto = new MyAccountDto();
        loginDto.setEmail(dataFakerGeneretor.emailFaker());
        loginDto.setSenha(dataFakerGeneretor.senhaFaker().substring(0, 3));

        return loginDto;
    }

    public MyAccountDto cadastroEmailValido() {

        MyAccountDto email = new MyAccountDto();
        email.setEmail(dataFakerGeneretor.emailFaker());

        return email;
    }

    public MyAccountDto cadastroEmailFormatoInvalido() {

        MyAccountDto email = new MyAccountDto();
        email.setEmail(dataFakerGeneretor.emailFaker().replace("@", ""));

        return email;
    }

    public MyAccountDto cadastroEmailVazio() {

        MyAccountDto email = new MyAccountDto();
        email.setEmail("");

        return email;
    }
}
