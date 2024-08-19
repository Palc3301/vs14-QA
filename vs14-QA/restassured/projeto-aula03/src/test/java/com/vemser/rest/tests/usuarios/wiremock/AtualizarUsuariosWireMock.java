package com.vemser.rest.tests.usuarios.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.google.gson.Gson;
import com.vemser.rest.client.UsuariosClient;
import com.vemser.rest.data.factory.UsuariosDataFactory;
import com.vemser.rest.model.UsuariosModel;
import com.vemser.rest.model.UsuariosResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.vemser.rest.tests.Utils.stubLogin;
import static io.restassured.RestAssured.defaultParser;

public class AtualizarUsuariosWireMock {

    private final Gson gson = new Gson();
    private WireMockServer wiremock;
    private final UsuariosClient usuariosClient = new UsuariosClient("http://localhost:3030");
    private final String FIXED_ID = "RWtxAwZ1MR66g4kn";

    @BeforeEach
    public void setup() {
        defaultParser = io.restassured.parsing.Parser.JSON;
        wiremock = new WireMockServer(3030);
        wiremock.start();
        configureFor("localhost", 3030);
        stubLogin();
    }

    @AfterEach
    public void tearDown() {
        wiremock.stop();
    }

    @Test
    public void testAtualizarUsuariosComSucesso() {
        UsuariosModel usuario = UsuariosDataFactory.usuarioValido();

        stubFor(put(urlEqualTo("/usuarios/" + FIXED_ID))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withBody(gson.toJson(usuario))));

        UsuariosResponse response = usuariosClient.atualizarUsuario(usuario)
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_OK)
                    .extract().as(UsuariosResponse.class);
    }

    @Test
    public void testAtualizarUsuariosComCamposEmBranco() {
        UsuariosModel usuario = UsuariosDataFactory.usuarioComCamposEmBranco();

        stubFor(put(urlEqualTo("/usuarios/" + FIXED_ID))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_BAD_REQUEST)
                        .withBody("{\"nome\": \"nome não pode ficar em branco\", \"email\": \"email não pode ficar em branco\", \"password\": \"password não pode ficar em branco\"}")));

        UsuariosResponse response = usuariosClient.atualizarUsuario(usuario)
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract().as(UsuariosResponse.class);
    }

    @Test
    public void testAtualizarUsuariosComEmailExistente() {
        UsuariosModel usuario = UsuariosDataFactory.usuarioComEmailExistente();

        stubFor(put(urlEqualTo("/usuarios/" + FIXED_ID))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_BAD_REQUEST)
                        .withBody("{\"message\": \"Este e-mail já está sendo usado\"}")));

        UsuariosResponse response = usuariosClient.atualizarUsuario(usuario)
                .then()
                .log().all()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract().as(UsuariosResponse.class);
    }
}
