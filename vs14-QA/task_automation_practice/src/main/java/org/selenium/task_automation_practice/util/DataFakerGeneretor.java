package org.selenium.task_automation_practice.util;

import net.datafaker.Faker;

public class DataFakerGeneretor {

    private static final Faker faker = new Faker();

    public String emailFaker(){
        return faker.internet().emailAddress();
    }
    public String senhaFaker(){
        return faker.internet().password();
    }
    public String firstNameFaker() { return faker.name().firstName(); }
    public String lastNameFaker() { return faker.name().lastName(); }
    public String textAreaFaker() {
        return faker.lorem().characters(10);
    }

}
