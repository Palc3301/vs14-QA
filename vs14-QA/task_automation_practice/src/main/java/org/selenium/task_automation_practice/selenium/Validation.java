package org.selenium.task_automation_practice.selenium;

import org.junit.jupiter.api.Assertions;

public class Validation {

    public void validateText(String expected, String Actual){
        Assertions.assertEquals(expected,Actual);
    }
    public void notNull(String elemento) { Assertions.assertNotNull(elemento);}
}
