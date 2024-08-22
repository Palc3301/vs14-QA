package org.selenium.task_automation_practice.data;

import org.selenium.task_automation_practice.dto.MyAccountDto;
import org.selenium.task_automation_practice.util.DataFakerGeneretor;

public class MyAccountData {

    DataFakerGeneretor dataFakerGeneretor = new DataFakerGeneretor();

    public MyAccountDto loginDadosValidos(){

        MyAccountDto myAccountDto = new MyAccountDto();
        myAccountDto.setEmail("Grupo3DBC@gmail.com");
        myAccountDto.setSenha("12345");

        return myAccountDto;
    }

    public MyAccountDto LoginEmailInvalido(){

        MyAccountDto myAccountDto = new MyAccountDto();
        myAccountDto.setEmail(dataFakerGeneretor.emailFaker().replace("@", ""));
        myAccountDto.setSenha(dataFakerGeneretor.senhaFaker());

        return myAccountDto;
    }

    public MyAccountDto LoginCamposVazios(){

        MyAccountDto myAccountDto = new MyAccountDto();
        myAccountDto.setEmail("");
        myAccountDto.setSenha("");

        return myAccountDto;
    }

    public MyAccountDto LoginPasswordInvalida(){

        MyAccountDto myAccountDto = new MyAccountDto();
        myAccountDto.setEmail(dataFakerGeneretor.emailFaker().replace("@", ""));
        myAccountDto.setSenha(dataFakerGeneretor.senhaFaker().substring(0, 3));

        return myAccountDto;
    }
}
