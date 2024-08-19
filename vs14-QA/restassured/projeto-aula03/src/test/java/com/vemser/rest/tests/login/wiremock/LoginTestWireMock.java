package com.vemser.rest.tests.login.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.google.gson.Gson;
import com.vemser.rest.client.LoginClient;
import com.vemser.rest.model.LoginResponse;
import com.vemser.rest.tests.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTestWireMock {

    ConfigLoader configLoader = new ConfigLoader();
    String email = configLoader.getEmail();
    String password = configLoader.getPassword();


    private final LoginClient loginClient = new LoginClient("http://localhost:3030");

    private final Gson gson = new Gson();

    private WireMockServer wireMock = new WireMockServer();

    @BeforeEach
    public void setup() {
        baseURI = "http://localhost:3030";
        defaultParser = Parser.JSON;
        wireMock = new WireMockServer(3030);
        wireMock.start();
        configureFor("localhost", 3030);
    }

    @AfterEach
    public void tearDown() {
        wireMock.stop();
    }


    @Test
    public void testLoginComSucessoMock() {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("Login realizado com sucesso");
        loginResponse.setAuthorization("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImFseXNvbkBxYS5jb20uYnIiLCJpYXQiOjE2MzIwNjQwNzIsImV4cCI6MTYzMjA2NzY3Mn0.1");

        String json = gson.toJson(loginResponse);

        wireMock.stubFor(post(urlEqualTo("/login"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(json)));

        LoginResponse response = loginClient.realizarLoginComSucesso(email, password)
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .as(LoginResponse.class);

        assertAll(() -> {
            assertEquals("Login realizado com sucesso", response.getMessage());
            assertFalse(response.getAuthorization().contains("Bearer " + response.getAuthorization()));
        });

        System.out.println(response.getAuthorization());
    }

    @Test
    public void testLoginComEmailVazio() {

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setEmail("email não pode ficar em branco");
        String json = gson.toJson(loginResponse);

        stubFor(post(urlEqualTo("/login"))
                .willReturn(aResponse()
                        .withStatus(400)
                        .withBody(json)));

        String email = "";
        String password = "teste";

        LoginResponse response =
                given()
                            .log().all()
                            .contentType(ContentType.JSON)
                            .body("{ \"email\": \"" + email + "\", \"password\": \"" + password + "\" }")
                        .when()
                            .post("/login")
                        .then()
                            .log().all()
                            .statusCode(HttpStatus.SC_BAD_REQUEST)
                            .extract()
                            .as(LoginResponse.class);
        Assertions.assertAll("Atualizar usuário com campos em branco",
                () -> Assertions.assertEquals("email não pode ficar em branco", response.getEmail())
        );
    }

    @Test
    public void testLoginComUsuarioInexistente() {

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("Email e/ou senha inválidos");
        String json = gson.toJson(loginResponse);

        stubFor(post(urlEqualTo("/login"))
                .willReturn(aResponse()
                        .withStatus(401)
                        .withBody(json)));

        String email = "usuarioInexistente@qa.com.br";
        String password = "senhaInvalida";

        LoginResponse response =
                given()
                            .log().all()
                            .contentType(ContentType.JSON)
                            .body("{ \"email\": \"" + email + "\", \"password\": \"" + password + "\" }")
                        .when()
                            .post("/login")
                        .then()
                            .log().all()
                            .statusCode(HttpStatus.SC_UNAUTHORIZED)
                            .extract()
                            .as(LoginResponse.class);
        Assertions.assertEquals("Email e/ou senha inválidos", response.getMessage());
    }

}
