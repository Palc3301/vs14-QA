package com.vemser.rest.tests.usuarios.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.vemser.rest.client.UsuariosClient;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.vemser.rest.tests.Utils.stubLogin;
import static io.restassured.RestAssured.defaultParser;

public class ListarUsuariosWireMock {

    private WireMockServer wiremock;
    private final UsuariosClient usuariosClient = new UsuariosClient("http://localhost:3030");
    private final String USUARIOS = "/usuarios";

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
    public void testListarUsuariosComSucesso() {
        String responseJson = "[{" +
                "\"id\": \"RWtxAwZ1MR66g4kn\"," +
                "\"nome\": \"Fulano de Tal\"," +
                "\"email\": \"fulano@qa.com\"," +
                "\"password\": \"senha123\"," +
                "\"administrador\": \"true\"" +
                "}]";

        stubFor(get(urlEqualTo(USUARIOS))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseJson)));

        usuariosClient.listarUsuarios()
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_OK)
                    .extract().response();
    }
}
