package org.selenium.task_automation_practice.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MyAccountDto {

    private String email;
    private String senha;

}
