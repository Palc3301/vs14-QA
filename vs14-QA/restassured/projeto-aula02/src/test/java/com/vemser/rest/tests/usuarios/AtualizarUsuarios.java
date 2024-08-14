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

public class AtualizarUsuarios {

    Faker faker = new Faker();
    Random geradorBoolean = new Random();

    @BeforeEach
    public void setup() {
        baseURI = "http://localhost:3000";
    }

    @Test
    public void testAtualizarUsuariosComSucesso() {
        String id = "BTsN8hjdOJxWFknM";

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
                        .pathParam("id", id)
                        .when()
                        .put("/usuarios/{id}")
                        .then()
                        .log().all()
                        .statusCode(HttpStatus.SC_CREATED) // Assuming that the correct status code for update is 200
                        .extract()
                        .as(UsuariosResponse.class);
        System.out.println(response);
        Assertions.assertAll("Atualizar usuário com sucesso",
                () -> Assertions.assertNotNull(response.getId(), "O ID do usuário não deve ser nulo")
          );
    }

    @Test
    public void testAtualizarUsuariosComCamposEmBranco() {
        String id = "BTsN8hjdOJxWFknM";

        Usuarios usuario = new Usuarios();
        usuario.setNome(faker.name().fullName());
        usuario.setEmail("");
        usuario.setPassword(faker.internet().password());
        usuario.setAdministrador(String.valueOf(geradorBoolean.nextBoolean()));

        UsuariosResponse response =
                given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .body(usuario)
                        .pathParam("id", id)
                        .when()
                        .put("/usuarios/{id}")
                        .then()
                        .log().all()
                        .statusCode(HttpStatus.SC_BAD_REQUEST)
                        .extract()
                        .as(UsuariosResponse.class);

        Assertions.assertAll("Atualizar usuário com campos em branco",
                () -> Assertions.assertEquals("email não pode ficar em branco", response.getEmail(), "Mensagem de erro de email esperada")
        );
    }

    @Test
    public void testAtualizarUsuariosComEmailExistente() {
        String id = "BTsN8hjdOJxWFknM";

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
                        .pathParam("id", id)
                        .when()
                        .put("/usuarios/{id}")
                        .then()
                        .log().all()
                        .statusCode(HttpStatus.SC_BAD_REQUEST)
                        .extract()
                        .as(UsuariosResponse.class);

        Assertions.assertAll("Atualizar usuário com email existente",
                () -> Assertions.assertEquals("Este email já está sendo usado", response.getMessage(), "Mensagem de erro esperada")
        );
    }
}


