package com.vemser.rest.tests.usuarios;

import com.vemser.rest.model.usuarios.Usuarios;
import com.vemser.rest.model.usuarios.UsuariosResponse;
import io.restassured.http.ContentType;
import net.datafaker.Faker;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class DeletarUsuarios {

    Faker faker = new Faker();
    Random geradorBoolean = new Random();

    @BeforeEach
    public void setup() {
        baseURI = "http://localhost:3000";
    }

    @Test
    public void testExcluirUsuarioComSucesso() {

        Usuarios usuario = new Usuarios();
        usuario.setNome(faker.name().fullName());
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setPassword(faker.internet().password());
        usuario.setAdministrador(String.valueOf(geradorBoolean.nextBoolean()));

        UsuariosResponse createdUser =
                given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .body(usuario)
                .when()
                        .post("/usuarios")
                .then()
                        .log().all()
                        .statusCode(HttpStatus.SC_CREATED)
                        .extract()
                        .as(UsuariosResponse.class);

        String userId = createdUser.getId();

        given()
                .log().all()
                .pathParam("id", userId)
        .when()
                .delete("/usuarios/{id}")
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);

        given()
                .log().all()
                .pathParam("id", userId)
        .when()
                .get("/usuarios/{id}")
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", equalTo("Usuário não encontrado"));
    }

    @Test
    public void testExcluirUsuarioInexistente() {
        String id = "123456";

        given()
                .log().all()
                .pathParam("id", id)
        .when()
                .delete("/usuarios/{id}")
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body("message", equalTo("Nenhum registro excluído"));

        String message = given()
                .pathParam("id", id)
        .when()
                .get("/usuarios/{id}")
        .then()
                .extract()
                .path("message");

        // Assertions
        Assertions.assertAll(() -> Assertions.assertEquals("Usuário não encontrado", message));
    }

    @Test
    public void testExcluirUsuarioComCarrinho() {
        String id = "RIWXVBqvkPeyfqs6";

        given()
                .log().all()
                .pathParam("id", id)
        .when()
                .delete("/usuarios/{id}")
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST) // 400 Bad Request
                .body("message", equalTo("Não é permitido excluir usuário com carrinho cadastrado"));

        UsuariosResponse response = given()
                .log().all()
                .pathParam("id", id)
        .when()
                .get("/usuarios/{id}")
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK) // 200 OK
                .extract()
                .as(UsuariosResponse.class);

        Assertions.assertAll("response",
                () -> Assertions.assertEquals("Alyson QA", response.getNome()),
                () -> Assertions.assertEquals("alyson@qa.com.br", response.getEmail()),
                () -> Assertions.assertTrue(response.getEmail().contains("alyson")),
                () -> Assertions.assertNotNull(response.getPassword())
        );
    }
}
