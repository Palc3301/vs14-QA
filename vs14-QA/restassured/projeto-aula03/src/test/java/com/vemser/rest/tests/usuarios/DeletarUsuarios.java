package com.vemser.rest.tests.usuarios;

import com.vemser.rest.client.UsuariosClient;
import com.vemser.rest.data.factory.UsuariosDataFactory;
import com.vemser.rest.model.UsuariosModel;
import com.vemser.rest.model.UsuariosResponse;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class DeletarUsuarios {

    private final UsuariosClient usuariosClient = new UsuariosClient();

    @BeforeEach
    public void setup() {
        baseURI = "http://localhost:3000";
    }

    @Test
    public void testExcluirUsuarioComSucesso() {

        UsuariosModel usuario = UsuariosDataFactory.usuarioValido();

        UsuariosResponse createdUser =
                usuariosClient.cadastrarUsuarios(usuario)
                        .then()
                        .log().all()
                        .statusCode(HttpStatus.SC_CREATED)
                        .extract()
                        .as(UsuariosResponse.class);

        String userId = createdUser.getId();

        usuariosClient.deletarUsuarioPorId(userId)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);

        usuariosClient.listarUsuarioPorId(userId)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", equalTo("Usuário não encontrado"));
    }

    @Test
    public void testExcluirUsuarioInexistente() {
        String id = "123456";

        Response response = usuariosClient.deletarUsuarioPorId(id);

        response.then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body("message", equalTo("Nenhum registro excluído"));

        String message = usuariosClient.listarUsuarioPorId(id)
                .then()
                .extract()
                .path("message");

        Assertions.assertAll(() -> Assertions.assertEquals("Usuário não encontrado", message));
    }

    @Test
    public void testExcluirUsuarioComCarrinho() {
        String id = "RIWXVBqvkPeyfqs6";

        usuariosClient.deletarUsuarioPorId(id)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST) // 400 Bad Request
                .body("message", equalTo("Não é permitido excluir usuário com carrinho cadastrado"));

        UsuariosResponse response = usuariosClient.listarUsuarioPorId(id)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK) // 200 OK
                .extract()
                .as(UsuariosResponse.class);

        Assertions.assertAll(
                () -> Assertions.assertEquals("Alyson QA", response.getNome()),
                () -> Assertions.assertEquals("alyson@qa.com.br", response.getEmail()),
                () -> Assertions.assertTrue(response.getEmail().contains("alyson")),
                () -> Assertions.assertNotNull(response.getPassword())
        );

    }
}
