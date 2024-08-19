package com.vemser.rest.tests.usuarios.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.google.gson.Gson;
import com.vemser.rest.client.UsuariosClient;
import com.vemser.rest.model.UsuariosModel;
import com.vemser.rest.model.UsuariosResponse;
import com.vemser.rest.tests.ConfigLoader;
import net.datafaker.Faker;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.vemser.rest.tests.Utils.stubLogin;
import static io.restassured.RestAssured.defaultParser;

public class CadastrarUsuariosWireMock {

    private final Gson gson = new Gson();
    private WireMockServer wiremock;
    private final UsuariosClient usuariosClient = new UsuariosClient("http://localhost:3030");
    private final Faker faker = new Faker();
    private final Random geradorBoolean = new Random();
    private final ConfigLoader configLoader = new ConfigLoader();
    private final String email = configLoader.getEmail();
    private final String password = configLoader.getPassword();

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
    public void testCadastrarUsuarioComSucesso() {
        UsuariosModel usuario = new UsuariosModel();
        usuario.setNome(faker.name().fullName());
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setPassword(faker.internet().password());
        usuario.setAdministrador(String.valueOf(geradorBoolean.nextBoolean()));

        stubFor(post(urlEqualTo("/usuarios"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_CREATED)
                        .withBody(gson.toJson(usuario))));

        UsuariosResponse response = usuariosClient.cadastrarUsuarios(usuario)
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_CREATED)
                    .extract().as(UsuariosResponse.class);
    }

    @Test
    public void testCadastrarUsuarioComEmailJaExistente() {
        UsuariosModel usuario = new UsuariosModel();
        usuario.setNome(faker.name().fullName());
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setAdministrador(String.valueOf(geradorBoolean.nextBoolean()));

        stubFor(post(urlEqualTo("/usuarios"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_BAD_REQUEST)
                        .withBody("{\"message\": \"Este e-mail já está sendo usado\"}")));

        UsuariosResponse response = usuariosClient.cadastrarUsuarios(usuario)
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract().as(UsuariosResponse.class);
    }

    @Test
    public void testCadastrarUsuarioComCamposVazios() {
        UsuariosModel usuario = new UsuariosModel();
        usuario.setNome("");
        usuario.setEmail("");
        usuario.setPassword("");
        usuario.setAdministrador(String.valueOf(geradorBoolean.nextBoolean()));

        stubFor(post(urlEqualTo("/usuarios"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_BAD_REQUEST)
                        .withBody("{\"nome\": \"nome não pode ficar em branco\", \"email\": \"email não pode ficar em branco\", \"password\": \"password não pode ficar em branco\"}")));

        UsuariosResponse response = usuariosClient.cadastrarUsuarios(usuario)
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract().as(UsuariosResponse.class);
    }
}
