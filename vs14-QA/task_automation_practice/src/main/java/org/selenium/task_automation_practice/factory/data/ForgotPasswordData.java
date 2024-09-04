package org.selenium.task_automation_practice.factory.data;

import org.selenium.task_automation_practice.dto.ForgotPasswordDto;
import org.selenium.task_automation_practice.util.DataFakerGeneretor;

public class ForgotPasswordData {

    DataFakerGeneretor dataFakerGeneretor = new DataFakerGeneretor();

    public ForgotPasswordDto forgotEmailValido() {

        ForgotPasswordDto email = new ForgotPasswordDto();
        email.setEmail("Grupo3DBC@gmail.com");

        return email;
    }

    public ForgotPasswordDto forgotEmailNaoCadastrado() {

        ForgotPasswordDto email = new ForgotPasswordDto();
        email.setEmail(dataFakerGeneretor.emailFaker());

        return email;
    }

    public ForgotPasswordDto forgotEmailVazio() {

        ForgotPasswordDto email = new ForgotPasswordDto();
        email.setEmail("");

        return email;
    }
}
