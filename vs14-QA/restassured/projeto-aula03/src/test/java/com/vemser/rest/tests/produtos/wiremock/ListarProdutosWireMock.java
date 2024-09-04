package com.vemser.rest.tests.produtos.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.google.gson.Gson;
import com.vemser.rest.client.ProdutosClient;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.vemser.rest.tests.Utils.stubLogin;
import static io.restassured.RestAssured.defaultParser;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ListarProdutosWireMock {

    private final Gson gson = new Gson();
    private WireMockServer wiremock;
    private final ProdutosClient produtosClient = new ProdutosClient("http://localhost:3030");

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
    public void testDeveValidarContratoTodosOsProdutos() {
        Map<String, Object> produto = new HashMap<>();
        produto.put("nome", "Produto 1");
        produto.put("preco", 100);
        produto.put("descricao", "Descrição do produto 1");
        produto.put("quantidade", 10);
        produto.put("message", "Produto cadastrado com sucesso");
        produto.put("_id", "1");

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("produtos", List.of(produto));
        responseMap.put("quantidade", 1);

        String json = gson.toJson(responseMap);

        stubFor(get(urlEqualTo("/produtos"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withBody(json)));

        produtosClient.listarProdutos()
                .then()
                .log().all()
                    .statusCode(HttpStatus.SC_OK)
                    .body(matchesJsonSchemaInClasspath("schemas/produtos.json"))
        ;
    }
}
