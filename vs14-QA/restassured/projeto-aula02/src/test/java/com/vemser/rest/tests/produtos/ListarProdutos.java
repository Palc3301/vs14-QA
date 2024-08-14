package com.vemser.rest.tests.produtos;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ListarProdutos {

    private String bearerToken;
    Faker faker = new Faker();
    Random geradorBoolean = new Random();

    @BeforeEach
    public void setup() {
        baseURI = "http://localhost:3000";
        bearerToken = getBearerToken();
    }

    private String getBearerToken() {
        String email = "alyson@qa.com.br";
        String senha = "teste";

        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\"email\":\"" + email + "\",\"password\":\"" + senha + "\"}")
                .when()
                .post("/login")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .path("authorization");
    }

    @Test
    public void testDeveValidarContratoTodosOsProdutos() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
        .when()
                .get("/produtos")
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("schemas/produtos.json"))
        ;
    }

    @Test
    public void testGetTodosOsProdutosPorId() {
        String produtoId = "RWtxAwZ1MR66g4kn";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", produtoId)
        .when()
                .get("/produtos/{id}")
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("schemas/produtos_por_id.json"))
        ;
    }

    @Test
    public void testGetTodosOsProdutosIdInexistente() {
        String produtoId = "IDInexistente";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", produtoId)
        .when()
                .get("/produtos/{id}")
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);

    }

    @Test
    public void testGetProdutosIdVazio() {
        String produtoId = " ";


        Response response = RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", produtoId)
        .when()
                .get("/produtos/{id}");

        response.then().log().all();

        Assertions.assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusCode());

        Assertions.assertTrue(response.asString().contains("Produto não encontrado"));
    }

}
