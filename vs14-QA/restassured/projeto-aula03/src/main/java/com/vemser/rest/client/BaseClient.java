package com.vemser.rest.client;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.specification.RequestSpecification;

public abstract class BaseClient {

    String baseURI;

    public BaseClient(String baseURI) {
        this.baseURI = baseURI;
    }

    public RequestSpecification set() {
        return new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .setConfig(RestAssured.config().logConfig(
                        LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .build();
    }
}
