package com.vemser.rest.client;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginClient extends BaseClient {

    private static final String BASE_URI = "http://localhost:8080";
    private final String LOGIN = "/login";

    private static final String EMAIL = "alyson@qa.com.br";
    private static final String PASSWORD = "teste";

    public Response realizarLogin(String email, String password) {
        return given()
                    .spec(super.set())
                    .contentType(ContentType.JSON)
                    .body(createLoginBody(email, password))
                .when()
                .post(LOGIN);
    }

    public Response realizarLoginComSucesso() {
        return realizarLogin(EMAIL, PASSWORD);
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


    /////////////////////////MOCK///////////////////
    public Response realizarLoginMock(String email, String password) {
        return RestAssured.given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(createLoginBody(email, password))
                .post(LOGIN);
    }

    public Response realizarLoginComSucessoMock() {
        return realizarLoginMock(EMAIL, PASSWORD);
    }

}
