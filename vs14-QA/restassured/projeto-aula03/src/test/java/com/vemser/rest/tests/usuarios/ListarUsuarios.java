package com.vemser.rest.tests.usuarios;

import com.vemser.rest.client.UsuariosClient;
import com.vemser.rest.model.UsuariosResponse;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class ListarUsuarios {

    private final UsuariosClient usuariosClient = new UsuariosClient("http://localhost:3000");

    @BeforeEach
    public void setup() {
        baseURI = "http://localhost:3000";
    }

    @Test
    public void testDeveValidarContratoUsuariosPorIDComSucesso() {

        String id = "4Rj3vrHUKwYtfPrl";

        usuariosClient.listarUsuarioPorId(id)
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("schemas/usuarios_por_id.json"))
        ;
    }

    @Test
    public void testDeveValidarContratoUsuariosComSucesso() {
        usuariosClient.listarUsuarios()
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("schemas/usuarios_get.json"))
        ;
    }

    @Test
    public void testDeveListarTodosOsUsuariosComSucesso() {
        List<UsuariosResponse> response =
                usuariosClient.listarUsuarios()
                        .then()
                            .log().all()
                            .statusCode(HttpStatus.SC_OK)
                            .extract()
                            .jsonPath()
                            .getList("usuarios", UsuariosResponse.class);

        assertFalse(response.isEmpty(), "A lista de usuários não deve estar vazia");
    }

    @Test
    public void testListarUsuariosPorNomeComSucesso() {
        String nome = "Alyson";

        List<Map<String, ?>> response =
                usuariosClient.listarUsuariosPorNome(nome)
                        .then()
                            .log().all()
                            .statusCode(HttpStatus.SC_OK)
                            .extract()
                            .jsonPath()
                            .getList("usuarios");


        assertFalse(response.isEmpty(), "A lista de usuários não deve estar vazia");
        assertFalse(response.stream().anyMatch(user -> ((String)user.get("nome")).equals(nome)),
                "A lista deve conter um usuário com o nome " + nome);
    }

    @Test
    public void testBuscarUsuarioPorIDComHamcrest() {

        String id = "4Rj3vrHUKwYtfPrl";

        usuariosClient.listarUsuarioPorId(id)
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
                usuariosClient.listarUsuarioPorId(id)
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(UsuariosResponse.class);

        assertEquals("Alyson QA", response.getNome());
        assertTrue(response.getEmail().contains("alyson"));
    }

    @Test
    public void testBuscarUsuarioPorIDComAssertAll() {

        String id = "4Rj3vrHUKwYtfPrl";


        UsuariosResponse response =
                usuariosClient.listarUsuarioPorId(id)
                        .then()
                        .log().all()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .as(UsuariosResponse.class);

        Assertions.assertAll("response",
                () -> assertEquals("Alyson QA", response.getNome()),
                () -> assertEquals("alysons@qa.com.br", response.getEmail()),
                () -> assertTrue(response.getEmail().contains("alyson")),
                () -> Assertions.assertNotNull(response.getPassword())
        );
    }


    @Test
    public void testBuscarUsuarioPorIDVazio() {
        String idVazio = " ";

        Response response = usuariosClient.listarUsuarioPorId(idVazio);

        assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusCode());

        assertTrue(response.asString().contains("Usuário não encontrado"));
    }

    @Test
    public void testBuscarUsuarioPorIDInexistente() {
        String idInexistente = "123456";

        Response response = usuariosClient.listarUsuarioPorId(idInexistente);

        assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusCode());

        assertTrue(response.asString().contains("Usuário não encontrado"));

    }

}
