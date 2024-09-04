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

    //Address
    public String addressLine1Faker() { return faker.address().streetName(); }
    public String addressLine2Faker() { return faker.address().streetName(); }
    public String cityFaker() { return faker.address().city(); }
    public String zipCodeFaker() { return "000" + String.valueOf(faker.number().numberBetween(10, 99)); }
    public String homeNumberFaker(){ return faker.number().digits(7); }
    public String mobileNumberFaker(){ return faker.number().digits(7); }
    public String additionalInformationFaker() { return faker.lorem().characters(15); }
    public String addressTitleFaker() { return faker.lorem().characters(10); }

    public String textAreaFaker() { return faker.lorem().characters(10); }
}
