package org.selenium.task_automation_practice.page;

import org.openqa.selenium.By;
import org.selenium.task_automation_practice.factory.data.AddressData;
import org.selenium.task_automation_practice.dto.AddressDto;
import org.selenium.task_automation_practice.factory.selenium.Interactions;

public class AddressPage extends Interactions {
    private static final By btnAddresses = By.cssSelector("#center_column > div > div > ul > li:nth-child(3) > a");
    private static final By btnAddNewAddress = By.cssSelector("#center_column > div.clearfix.main-page-indent > a");
    private static final By btnSubmitAddress = By.cssSelector("#submitAddress");
    private static final By btnUpdateAddress = By.cssSelector("#center_column > div.addresses > div:nth-child(3) > div:nth-child(2) > ul > li.address_update > a:nth-child(1) > span");
    private static final By btnDeleteAddress = By.cssSelector("#center_column > div.addresses > div:nth-child(3) > div:nth-child(1) > ul > li.address_update > a:nth-child(2) > span");

    private static final By campoAddressLine1 = By.cssSelector("#address1");
    private static final By campoAddressLine2 = By.cssSelector("#address2");
    private static final By campoCity = By.cssSelector("#city");
    private static final By campoStateOpt = By.cssSelector("#id_state > option:nth-child(6)");
    private static final By campoPostCode = By.cssSelector("#postcode");
    private static final By campoHomePhone = By.cssSelector("#phone");
    private static final By campoMobilePhone = By.cssSelector("#phone_mobile");
    private static final By campoAdditionalInformation = By.cssSelector("#other");
    private static final By campoAddressTitle = By.cssSelector("#alias");

    private static final By myAddressesMsg = By.cssSelector("#center_column > h1");
    private static final By camposVaziosErrorMsg = By.cssSelector("#center_column > div > div > p");
    private static final By zipCodeErrorMsg = By.cssSelector("#center_column > div > div > ol > li");
    private static final By invalidPhoneErrorMsg = By.cssSelector("#center_column > div > div > ol > li");

    public String cadastrarEndereco(AddressDto address) {
        click(btnAddresses);
        click(btnAddNewAddress);

        fillAddressFields(address);

        click(btnSubmitAddress);

        return readText(myAddressesMsg);
    }

    public String cadastroEnderecoCamposVazios() {
        click(btnAddresses);
        click(btnAddNewAddress);
        click(btnSubmitAddress);

        return readText(camposVaziosErrorMsg);
    }

    public String cadastroEnderecoZipCodeInvalido(AddressDto address) {
        click(btnAddresses);
        click(btnAddNewAddress);

        fillAddressFields(address);

        click(btnSubmitAddress);

        return readText(zipCodeErrorMsg);
    }

    public String cadastroEnderecoHomePhoneInvalido(AddressDto address) {
        click(btnAddresses);
        click(btnAddNewAddress);

        fillAddressFields(address);

        click(btnSubmitAddress);

        return readText(invalidPhoneErrorMsg);
    }

    // Atualizar Endereço
    public String atualizarEndereco(AddressDto address) {
        click(btnAddresses);
        click(btnUpdateAddress);

        clearAddressFields();

        fillAddressFields(address);

        click(btnSubmitAddress);

        return readText(myAddressesMsg);
    }

    public String atualizarEnderecoCamposVazios() {
        click(btnAddresses);
        click(btnUpdateAddress);

        clearAddressFields();

        click(btnSubmitAddress);

        return readText(camposVaziosErrorMsg);
    }

    public String atualizarEnderecoZipCodeInvalido(AddressDto address) {
        click(btnAddresses);
        click(btnUpdateAddress);

        clearAddressFields();

        fillAddressFields(address);

        click(btnSubmitAddress);

        return readText(zipCodeErrorMsg);
    }

    public String excluirEndereco() {
        AddressData addressData = new AddressData();
        AddressDto address = addressData.addressDadosValidos();
        cadastrarEndereco(address);
        click(btnDeleteAddress);

        acceptAlert();

        return readText(myAddressesMsg);
    }

    private static void fillAddressFields(AddressDto address) {
        sendKeys(campoAddressLine1, address.getAddressLine1());
        sendKeys(campoAddressLine2, address.getAddressLine2());
        sendKeys(campoCity, address.getCity());
        click(campoStateOpt);
        sendKeys(campoPostCode, address.getZipCode());
        sendKeys(campoHomePhone, address.getHomePhone());
        sendKeys(campoMobilePhone, address.getMobilePhone());
        sendKeys(campoAdditionalInformation, address.getAdditionalInformation());
        sendKeys(campoAddressTitle, address.getAddressTitle());
    }

    private static void clearAddressFields() {
        clearKey(campoAddressLine1);
        clearKey(campoAddressLine2);
        clearKey(campoCity);
        clearKey(campoPostCode);
        clearKey(campoHomePhone);
        clearKey(campoMobilePhone);
        clearKey(campoAdditionalInformation);
        clearKey(campoAddressTitle);
    }


}
