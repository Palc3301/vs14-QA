package com.vemser.rest.tests.usuarios;

import com.vemser.rest.model.usuarios.UsuariosResponse;
import net.datafaker.Faker;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class ListarUsuarios {

    Faker faker = new Faker();
    Random geradorBoolean = new Random();

    @BeforeEach
    public void setup() {
        baseURI = "http://localhost:3000";
    }

    @Test
    public void testDeveValidarContratoUsuariosPorIDComSucesso() {

        String id = "4Rj3vrHUKwYtfPrl";

        given()
                .pathParam("_id", id)
        .when()
                .get("/usuarios/{_id}")
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("schemas/usuarios_por_id.json"))
        ;
    }

    @Test
    public void testDeveValidarContratoUsuariosComSucesso() {
        given()
        .when()
                .get("/usuarios")
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("schemas/usuarios_get.json"))
        ;
    }

    @Test
    public void testListarUsuariosPorNomeComSucesso() {
        String nome = "Alyson";

        given()
                .log().all()
                .queryParam("nome", nome)
        .when()
                .get("/usuarios")
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
        ;
    }

    @Test
    public void testBuscarUsuarioPorIDComHamcrest() {

        String id = "4Rj3vrHUKwYtfPrl";

        given()
                .log().all()
                .pathParam("id", id)
        .when()
                .get("/usuarios/{id}")
        .then()
                .log().all()
                .header("Content-Type", "application/json; charset=utf-8")
                .statusCode(HttpStatus.SC_OK)
                .time(lessThan(3000L))
                .body("_id", is(notNullValue()))
                .body("nome", is(equalTo("Alyson QA")))
                .body("email", containsStringIgnoringCase("alyson"));
    }

    @Test
    public void testBuscarUsuarioPorIDComAssertions() {

        String id = "4Rj3vrHUKwYtfPrl";

        UsuariosResponse response =
        given()
                .log().all()
                .pathParam("id", id)
        .when()
                .get("/usuarios/{id}")
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(UsuariosResponse.class);

        Assertions.assertEquals("Alyson QA", response.getNome());
        Assertions.assertTrue(response.getEmail().contains("alyson"));
    }

    @Test
    public void testBuscarUsuarioPorIDComAssertAll() {

        String id = "4Rj3vrHUKwYtfPrl";

        UsuariosResponse response =
                given()
                        .log().all()
                        .pathParam("id", id)
                .when()
                        .get("/usuarios/{id}")
                .then()
                        .log().all()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .as(UsuariosResponse.class);

        Assertions.assertAll("response",
                () -> Assertions.assertEquals("Alyson QA", response.getNome()),
                () -> Assertions.assertEquals("alysons@qa.com.br", response.getEmail()),
                () -> Assertions.assertTrue(response.getEmail().contains("alyson")),
                () -> Assertions.assertNotNull(response.getPassword())
        );
    }


    @Test
    public void testBuscarUsuarioPorIDVazio() {
        String id = " ";

        var response = given()
                .log().all()
                .pathParam("id", id)
        .when()
                .get("/usuarios/{id}")
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .response();

        Assertions.assertAll(
                () -> Assertions.assertFalse(response.asString().contains("ID inválido")));
    }

    @Test
    public void testBuscarUsuarioPorIDInexistente() {
        String id = "123456";

        var response = given()
                .log().all()
                .pathParam("id", id)
        .when()
                .get("/usuarios/{id}")
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .response();

        Assertions.assertAll(
                () -> Assertions.assertTrue(response.asString().contains("Usuário não encontrado")));
    }

}
