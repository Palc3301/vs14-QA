package com.vemser.rest.tests.usuarios;

import com.vemser.rest.client.UsuariosClient;
import com.vemser.rest.data.factory.UsuariosDataFactory;
import com.vemser.rest.model.UsuariosModel;
import com.vemser.rest.model.UsuariosResponse;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CadastrarUsuarios {

    private final UsuariosClient usuariosClient = new UsuariosClient();

    @Test
    public void testDeveValidarContratoDePostDeUsuariosComSucesso() {

        UsuariosModel usuario = UsuariosDataFactory.usuarioValido();

        usuariosClient.cadastrarUsuarios(usuario)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_CREATED)
                .body(matchesJsonSchemaInClasspath("schemas/usuarios_post.json"));
    }

    @Test
    public void testCadastrarUsuarioComSucesso() {

        UsuariosModel usuario = UsuariosDataFactory.usuarioValido();

        UsuariosResponse response =
                usuariosClient.cadastrarUsuarios(usuario)
                .then()
                        .log().all()
                        .statusCode(HttpStatus.SC_CREATED)
                        .extract()
                        .as(UsuariosResponse.class);

        Assertions.assertNotNull(response.getId());
    }

    @Test
    public void testCadastrarUsuarioSemNome() {
        UsuariosModel usuario = UsuariosDataFactory.usuarioComNomeVazio();

        UsuariosResponse response =
                usuariosClient.cadastrarUsuarios(usuario)
                        .then()
                        .log().all()
                        .statusCode(HttpStatus.SC_BAD_REQUEST)
                        .extract()
                        .as(UsuariosResponse.class);

        System.out.println(response);
        Assertions.assertAll("response",
                () -> Assertions.assertEquals("nome não pode ficar em branco", response.getNome(), "Mensagem de erro esperada")
        );
    }

    @Test
    public void testCadastrarUsuarioComCampoVazio() {
        UsuariosModel usuario = UsuariosDataFactory.usuarioComEmailEmBranco();

        UsuariosResponse response =
                usuariosClient.cadastrarUsuarios(usuario)
                .then()
                        .log().all()
                        .statusCode(HttpStatus.SC_BAD_REQUEST)
                        .extract()
                        .as(UsuariosResponse.class);

        System.out.println(response);
       Assertions.assertEquals("email não pode ficar em branco", response.getEmail(), "Mensagem de erro esperada");
    }


    @Test
    public void testCadastrarUsuarioComEmailExistente() {
        UsuariosModel usuario = UsuariosDataFactory.usuarioComEmailExistente();

        UsuariosResponse response =
                usuariosClient.cadastrarUsuarios(usuario)
                        .then()
                        .log().all()
                        .statusCode(HttpStatus.SC_BAD_REQUEST)
                        .extract()
                        .as(UsuariosResponse.class);

        Assertions.assertEquals("Este email já está sendo usado", response.getMessage());
    }

    @ParameterizedTest
    @MethodSource("com.vemser.rest.provider.UsusariosDataProvider#usuarioDataProvider")
    public void testDeveCadastrarUsuariosComDataProvider(UsuariosModel usuario, String key, String valiue) {
        usuariosClient.cadastrarUsuarios(usuario)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(key, Matchers.equalTo(valiue))
                .extract()
                .as(UsuariosResponse.class);
    }

}
