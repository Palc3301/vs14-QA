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
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CadastrarUsuarios {

    Faker faker = new Faker();
    Random geradorBoolean = new Random();

    @BeforeEach
    public void setup() {
        baseURI = "http://localhost:3000";
    }


    @Test
    public void testDeveValidarContratoDePostDeUsuariosComSucesso() {

        Usuarios usuario = new Usuarios();
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
                .statusCode(HttpStatus.SC_CREATED)
                .body(matchesJsonSchemaInClasspath("schemas/usuarios_post.json"));
    }

    @Test
    public void testCadastrarUsuarioComSucesso() {

        Usuarios usuario = new Usuarios();
        usuario.setNome(faker.name().fullName());
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setPassword(faker.internet().password());
        usuario.setAdministrador(String.valueOf(geradorBoolean.nextBoolean()));

        UsuariosResponse response =
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

        Assertions.assertNotNull(response.getId());
    }

    @Test
    public void testCadastrarUsuarioComCampoVazio() {
        Usuarios usuario = new Usuarios();
        usuario.setNome(faker.name().fullName());
        usuario.setEmail(" ");
        usuario.setPassword(faker.internet().password());
        usuario.setAdministrador(String.valueOf(geradorBoolean.nextBoolean()));

        UsuariosResponse response =
                given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .body(usuario)
                .when()
                        .post("/usuarios")
                .then()
                        .log().all()
                        .statusCode(HttpStatus.SC_BAD_REQUEST)
                        .extract()
                        .as(UsuariosResponse.class);

        System.out.println(response);
        Assertions.assertAll("response",
                () -> Assertions.assertEquals("email deve ser um email válido", response.getEmail(), "Mensagem de erro esperada")
        );
    }


    @Test
    public void testCadastrarUsuarioComEmailExistente() {
        Usuarios usuario = new Usuarios();
        usuario.setNome(faker.name().fullName());
        usuario.setEmail("fulano@qa.com");
        usuario.setPassword(faker.internet().password());
        usuario.setAdministrador(String.valueOf(geradorBoolean.nextBoolean()));

        UsuariosResponse response =
                given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .body(usuario)
                .when()
                        .post("/usuarios")
                .then()
                        .log().all()
                        .statusCode(HttpStatus.SC_BAD_REQUEST)
                        .extract()
                        .as(UsuariosResponse.class);
        System.out.println(response);

        Assertions.assertAll("response",
                () -> Assertions.assertEquals("Este email já está sendo usado", response.getMessage(), "Mensagem de erro esperada")
        );
    }


}
