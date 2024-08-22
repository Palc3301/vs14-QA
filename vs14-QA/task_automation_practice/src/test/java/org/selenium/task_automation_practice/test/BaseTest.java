package org.selenium.task_automation_practice.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.selenium.task_automation_practice.selenium.BrowserService;

public class BaseTest extends BrowserService {

    @BeforeEach
    public void abrirNavegador(){
        chromeDriverInit("http://www.automationpractice.pl/index.php");
    }

    /*@AfterEach
    public void fecharNavegador(){
        quit();
    }*/
}
