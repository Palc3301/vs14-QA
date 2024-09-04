package org.selenium.task_automation_practice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDto {

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String zipCode;
    private String homePhone;
    private String mobilePhone;
    private String additionalInformation;
    private String addressTitle;

}
