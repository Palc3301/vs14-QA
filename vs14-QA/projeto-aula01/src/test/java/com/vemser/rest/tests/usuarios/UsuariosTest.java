package com.vemser.rest.tests.usuarios;

import com.vemser.rest.pojo.UsuariosPOJO;
import io.restassured.http.ContentType;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static io.restassured.RestAssured.*;

public class UsuariosTest {

    Faker faker = new Faker();
    Random geradorBoolean = new Random();

    @BeforeEach
    public void setup() {
        baseURI = "http://localhost:3000";
    }

///////////////////////////// SECTION GET /////////////////////////////
    @Test
    public void testListarTodosUsuariosComSucesso() {

        given()
        .when()
                .get("/usuarios")
        .then()
                .statusCode(200)
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
                .statusCode(200)
        ;
    }

    @Test
    public void testBuscarUsuarioPorIDComSucesso() {

        String id = "4Rj3vrHUKwYtfPrl";

        given()
                .log().all()
                .pathParam("id", id)
        .when()
                .get("/usuarios/{id}")
        .then()
                .log().all()
                .statusCode(200)
        ;
    }


    @Test
    public void testBuscarUsuarioPorIDVazio() {

            String id = " ";

            given()
                    .log().all()
                    .pathParam("id", id)
            .when()
                    .get("/usuarios/{id}")
            .then()
                    .log().all()
                    .statusCode(400);
    }

    @Test
    public void testBuscarUsuarioPorIDInexistente() {

            String id = "123456";

            given()
                    .log().all()
                    .pathParam("id", id)
            .when()
                    .get("/usuarios/{id}")
            .then()
                    .log().all()
                    .statusCode(400);
    }

///////////////////////////// SECTION POST /////////////////////////////
    @Test
    public void testCadastrarUsuarioComSucesso() {

        UsuariosPOJO usuario = new UsuariosPOJO();
        usuario.setNome(faker.name().fullName());
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setPassword(faker.internet().password());
        usuario.setAdministrador(String.valueOf(geradorBoolean.nextBoolean()));

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(usuario)
        .when()
                .post("/usuarios")
        .then()
                .log().all()
                .statusCode(201);
    }

    @Test
    public void testCadastrarUsuarioComCampoVazio() {
        UsuariosPOJO usuario = new UsuariosPOJO();
        usuario.setNome(faker.name().fullName());
        usuario.setEmail(" ");
        usuario.setPassword(faker.internet().password());
        usuario.setAdministrador(String.valueOf(geradorBoolean.nextBoolean()));

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(usuario)
        .when()
                .post("/usuarios")
        .then()
                .log().all()
                .statusCode(400);
    }

    @Test
    public void testCadastrarUsuarioComEmailExistente() {
        UsuariosPOJO usuario = new UsuariosPOJO();
        usuario.setNome(faker.name().fullName());
        usuario.setEmail("fulano@qa.com");
        usuario.setPassword(faker.internet().password());
        usuario.setAdministrador(String.valueOf(geradorBoolean.nextBoolean()));

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(usuario)
        .when()
                .post("/usuarios")
        .then()
                .log().all()
                .statusCode(400);
    }

///////////////////////////// SECTION PUT /////////////////////////////
    @Test
    public void testAtualizarUsuariosComSucesso() {
        String id = "BTsN8hjdOJxWFknM";

        UsuariosPOJO usuario = new UsuariosPOJO();
        usuario.setNome(faker.name().fullName());
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setPassword(faker.internet().password());
        usuario.setAdministrador(String.valueOf(geradorBoolean.nextBoolean()));

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(usuario)
                .pathParam("id", id)
        .when()
                .put("/usuarios/{id}")
        .then()
                .log().all()
                .statusCode(201);
    }

    @Test
    public void testAtualizarUsuariosComCamposEmBranco() {
        String id = "BTsN8hjdOJxWFknM";

        UsuariosPOJO usuario = new UsuariosPOJO();
        usuario.setNome(faker.name().fullName());
        usuario.setEmail("");
        usuario.setPassword(faker.internet().password());
        usuario.setAdministrador(String.valueOf(geradorBoolean.nextBoolean()));

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(usuario)
                .pathParam("id", id)
        .when()
                .put("/usuarios/{id}")
        .then()
                .log().all()
                .statusCode(400);
    }

    @Test
    public void testAtualizarUsuariosComEmailExistente() {
        String id = "BTsN8hjdOJxWFknM";

        UsuariosPOJO usuario = new UsuariosPOJO();
        usuario.setNome(faker.name().fullName());
        usuario.setEmail("fulano@qa.com");
        usuario.setPassword(faker.internet().password());
        usuario.setAdministrador(String.valueOf(geradorBoolean.nextBoolean()));

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(usuario)
                .pathParam("id", id)
        .when()
                .put("/usuarios/{id}")
        .then()
                .log().all()
                .statusCode(400);
    }


///////////////////////////// SECTION DELETE /////////////////////////////
    @Test
    public void testExcluirUsuarioComSucesso() {
        String id = "BTsN8hjdOJxWFknM";

        given()
                .log().all()
                .pathParam("id", id)
        .when()
                .delete("/usuarios/{id}")
        .then()
                .log().all()
                .statusCode(200);
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
                .statusCode(200);
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
                .statusCode(400);
    }
}
