package org.selenium.task_automation_practice.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.selenium.task_automation_practice.data.AddressData;
import org.selenium.task_automation_practice.data.MyAccountData;
import org.selenium.task_automation_practice.dto.AddressDto;
import org.selenium.task_automation_practice.dto.MyAccountDto;
import org.selenium.task_automation_practice.page.AddressPage;
import org.selenium.task_automation_practice.page.MyAccountPage;
import org.selenium.task_automation_practice.selenium.Validation;

public class AddressTest extends BaseTest {

    MyAccountPage myAccountPage = new MyAccountPage();
    AddressPage addressPage = new AddressPage();
    AddressData addressData = new AddressData();
    MyAccountData myAccountData = new MyAccountData();
    Validation validation = new Validation();

    @BeforeEach
    public void setUp() {
        MyAccountDto usuario =  myAccountData.loginDadosValidos();
        String mensagem = myAccountPage.fazerLogin(usuario.getEmail(), usuario.getSenha());
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

    // Atualizar Cadastro
    @Test
    public void testValidarAtualizaçãoDeEnderecoComSucesso() {
        AddressDto newAddress = addressData.addressDadosValidos();
        String mensagem = addressPage.atualizarEndereco(newAddress);
        validation.validateText("MY ADDRESSES", mensagem);
    }

    @Test
    public void testTentarValidarAtualizaçãoDeEnderecoCamposVazios() {
        String mensagem = addressPage.atualizarEnderecoCamposVazios();
        validation.validateText("There are 5 errors", mensagem);
    }

    @Test
    public void testTentarValidarAtualizacaoDeEnderecoZipCodeInvalido() {
        AddressDto address = addressData.addressZipCodeInvalido();
        String mensagem = addressPage.atualizarEnderecoZipCodeInvalido(address);
        validation.validateText("The Zip/Postal code you've entered is invalid. It must follow this format: 00000", mensagem);
    }

    // Excluir Endereço
    @Test
    public void testValidarExclusaoEnderecoComSucesso() {
        String mensagem = addressPage.excluirEndereco();
        validation.validateText("MY ADDRESSES", mensagem);
    }
}
