package com.vemser.rest.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class OlaMundoTest {

    @Test
    public void testOlaMundo() {
        System.out.println("Olá, Mundo!");
    }

    @Test
    public void testBuscarUsuarioPorIDComSucesso() {

        baseURI = "https://reqres.in/api";

            given()
            .when()
                .get("/users/1")
            .then()
                .statusCode(200);
    }
}
