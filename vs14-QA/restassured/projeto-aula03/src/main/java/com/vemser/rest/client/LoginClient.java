package com.vemser.rest.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginClient extends BaseClient {

    private final String LOGIN = "/login";


    public LoginClient(String baseURI) {
        super(baseURI);
    }

    public Response realizarLogin(String email, String password) {
        return given()
                    .spec(super.set())
                    .contentType(ContentType.JSON)
                    .body(createLoginBody(email, password))
                .when()
                .post(LOGIN);
    }

    public Response realizarLoginComSucesso(String email, String password) {
        return realizarLogin(email, password);
    }

    public Response realizarLoginSemAutenticacao(String email, String password) {
        return given()
                .spec(super.set())
                    .contentType(ContentType.JSON)
                    .body(createLoginBody(email, password))
                .when()
                .post(LOGIN);
    }

    private String createLoginBody(String email, String password) {
        return "{ \"email\": \"" + email + "\", \"password\": \"" + password + "\" }";
    }

}
