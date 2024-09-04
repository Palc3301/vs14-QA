package com.vemser.rest.tests.login;

import com.vemser.rest.model.LoginResponse;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

public class LoginTest {

    @BeforeEach
    public void setup() {
        baseURI = "http://localhost:3000";
    }

    @Test
    public void testLoginComSucesso() {
        String email = "alyson@qa.com.br";
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
                .statusCode(200)
                .extract()
                .as(LoginResponse.class);
        Assertions.assertAll(() ->
                Assertions.assertEquals("Login realizado com sucesso", response.getMessage()));
        System.out.println(response.getAuthorization());
        Assertions.assertAll(() -> Assertions.assertFalse(response.getAuthorization().contains("Bearer "+response.getAuthorization())));


    }

    @Test
    public void testLoginComEmailVazio() {
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
