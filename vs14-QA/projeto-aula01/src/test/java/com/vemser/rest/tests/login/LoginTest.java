package com.vemser.rest.tests.login;

import com.vemser.rest.pojo.UsuariosPOJO;
import io.restassured.http.ContentType;
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

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{ \"email\": \"" + email + "\", \"password\": \"" + password + "\" }")
        .when()
                .post("/login")
        .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void testLoginComEmailVazio() {
        String email = "";
        String password = "teste";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{ \"email\": \"" + email + "\", \"password\": \"" + password + "\" }")
        .when()
                .post("/login")
        .then()
                .log().all()
                .statusCode(400);
    }

    @Test
    public void testLoginComUsuarioInexistente() {
        String email = "usuarioInexistente@qa.com.br";
        String password = "senhaInvalida";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{ \"email\": \"" + email + "\", \"password\": \"" + password + "\" }")
        .when()
                .post("/login")
        .then()
                .log().all()
                .statusCode(401);
    }
}
