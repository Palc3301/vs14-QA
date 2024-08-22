package org.selenium.task_automation_practice.test;

import net.datafaker.providers.base.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.selenium.task_automation_practice.data.AddressData;
import org.selenium.task_automation_practice.data.LoginData;
import org.selenium.task_automation_practice.dto.AddressDto;
import org.selenium.task_automation_practice.dto.LoginDto;
import org.selenium.task_automation_practice.page.AddressPage;
import org.selenium.task_automation_practice.page.LoginPage;
import org.selenium.task_automation_practice.selenium.Validation;

public class AddressTest extends BaseTest {

    LoginPage loginPage = new LoginPage();
    AddressPage addressPage = new AddressPage();
    AddressData addressData = new AddressData();
    LoginData loginData = new LoginData();
    Validation validation = new Validation();

    @BeforeEach
    public void setUp() {
        LoginDto usuario =  loginData.loginDadosValidos();
        String mensagem = loginPage.fazerLogin(usuario.getEmail(), usuario.getSenha());
        validation.validateText("MY ACCOUNT", mensagem);
    }

    @Test
    public void testValidarCadastroDeEnderecoComSucesso() {
        AddressDto address = addressData.addressDadosValidos();
        String mensagem = addressPage.cadastrarEndereco(address);
        validation.validateText("MY ADDRESSES", mensagem);
    }

    @Test
    public void testTentarValidarCadastroCamposVazios() {
        String mensagem = addressPage.cadastroEnderecoCamposVazios();
        validation.validateText("There are 5 errors", mensagem);
    }

    @Test
    public void testTentarValidarCadastroZipCodeInvalido() {
        AddressDto address = addressData.addressZipCodeInvalido();
        String mensagem = addressPage.cadastroEnderecoZipCodeInvalido(address);
        validation.validateText("The Zip/Postal code you've entered is invalid. It must follow this format: 00000", mensagem);
    }

    @Test
    public void testTentarValidarCadastroTelefoneInvalido() {
        AddressDto address = addressData.addressHomePhoneInvalido();
        String mensagem = addressPage.cadastroEnderecoHomePhoneInvalido(address);
        validation.validateText("phone is invalid.", mensagem);
    }
}
