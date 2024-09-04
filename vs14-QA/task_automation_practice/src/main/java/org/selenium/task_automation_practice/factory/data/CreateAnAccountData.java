package org.selenium.task_automation_practice.factory.data;

import org.selenium.task_automation_practice.dto.CreateAnAccountDto;
import org.selenium.task_automation_practice.util.DataFakerGeneretor;

public class CreateAnAccountData {

    DataFakerGeneretor dataFakerGeneretor = new DataFakerGeneretor();

    public CreateAnAccountDto cadastroDadosValidos() {
        CreateAnAccountDto cadastro = new CreateAnAccountDto();
        cadastro.setFirstName(dataFakerGeneretor.firstNameFaker());
        cadastro.setLastName(dataFakerGeneretor.lastNameFaker());
        cadastro.setEmail(dataFakerGeneretor.emailFaker());
        cadastro.setPassword(dataFakerGeneretor.senhaFaker());

        return cadastro;
    }

    public CreateAnAccountDto cadastroPasswordInvalida() {
        CreateAnAccountDto cadastro = new CreateAnAccountDto();
        cadastro.setFirstName(dataFakerGeneretor.firstNameFaker());
        cadastro.setLastName(dataFakerGeneretor.lastNameFaker());
        cadastro.setEmail(dataFakerGeneretor.emailFaker());
        cadastro.setPassword(dataFakerGeneretor.senhaFaker().substring(0, 3));

        return cadastro;
    }
}

