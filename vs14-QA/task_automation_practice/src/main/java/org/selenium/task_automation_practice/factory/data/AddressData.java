package org.selenium.task_automation_practice.factory.data;

import org.selenium.task_automation_practice.dto.AddressDto;
import org.selenium.task_automation_practice.util.DataFakerGeneretor;

public class AddressData {

    DataFakerGeneretor dataFakerGeneretor = new DataFakerGeneretor();

    public AddressDto addressDadosValidos () {

        AddressDto addressDto = new AddressDto();
        addressDto.setAddressLine1(dataFakerGeneretor.addressLine1Faker());
        addressDto.setAddressLine2(dataFakerGeneretor.addressLine2Faker());
        addressDto.setCity(dataFakerGeneretor.cityFaker());
        addressDto.setZipCode(dataFakerGeneretor.zipCodeFaker());
        addressDto.setHomePhone(dataFakerGeneretor.homeNumberFaker());
        addressDto.setMobilePhone(dataFakerGeneretor.mobileNumberFaker());
        addressDto.setAdditionalInformation(dataFakerGeneretor.additionalInformationFaker());
        addressDto.setAddressTitle(dataFakerGeneretor.addressTitleFaker());

        return addressDto;
    }

    public AddressDto addressZipCodeInvalido() {

        String INVALID_ZIP_CODE = "123abc";

        AddressDto addressDto = new AddressDto();
        addressDto.setAddressLine1(dataFakerGeneretor.addressLine1Faker());
        addressDto.setAddressLine2(dataFakerGeneretor.addressLine2Faker());
        addressDto.setCity(dataFakerGeneretor.cityFaker());
        addressDto.setZipCode(INVALID_ZIP_CODE);
        addressDto.setHomePhone(dataFakerGeneretor.homeNumberFaker());
        addressDto.setMobilePhone(dataFakerGeneretor.mobileNumberFaker());
        addressDto.setAdditionalInformation(dataFakerGeneretor.additionalInformationFaker());
        addressDto.setAddressTitle(dataFakerGeneretor.addressTitleFaker());

        return addressDto;
    }

    public AddressDto addressHomePhoneInvalido() {

        String INVALID_HOME_PHONE = "ABC";

        AddressDto addressDto = new AddressDto();
        addressDto.setAddressLine1(dataFakerGeneretor.addressLine1Faker());
        addressDto.setAddressLine2(dataFakerGeneretor.addressLine2Faker());
        addressDto.setCity(dataFakerGeneretor.cityFaker());
        addressDto.setZipCode(dataFakerGeneretor.zipCodeFaker());
        addressDto.setHomePhone(INVALID_HOME_PHONE);
        addressDto.setMobilePhone(dataFakerGeneretor.mobileNumberFaker());
        addressDto.setAdditionalInformation(dataFakerGeneretor.additionalInformationFaker());
        addressDto.setAddressTitle(dataFakerGeneretor.addressTitleFaker());

        return addressDto;
    }
}
