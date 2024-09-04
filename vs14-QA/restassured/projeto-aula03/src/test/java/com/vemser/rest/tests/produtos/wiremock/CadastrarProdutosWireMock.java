package com.vemser.rest.tests.produtos.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.google.gson.Gson;
import com.vemser.rest.client.ProdutosClient;
import com.vemser.rest.model.ProdutosModel;
import com.vemser.rest.model.ProdutosResponse;
import net.datafaker.Faker;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.vemser.rest.tests.Utils.stubLogin;
import static io.restassured.RestAssured.defaultParser;

public class CadastrarProdutosWireMock {

    private final Gson gson = new Gson();
    private WireMockServer wiremock;
    private final ProdutosClient produtosClient = new ProdutosClient("http://localhost:3030");
    private static final Faker faker = new Faker(new Locale("pt-BR"));

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
    public void testDeveCadastrarProdutoComSucesso() {
        ProdutosModel produto = new ProdutosModel();
        produto.setNome(faker.commerce().productName());
        produto.setDescricao(faker.lorem().sentence());
        produto.setPreco(String.valueOf(faker.number().numberBetween(0, 100)));
        produto.setQuantidade(String.valueOf(faker.number().numberBetween(0, 100)));

        String json = gson.toJson(produto);

        stubFor(post(urlEqualTo("/produtos"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_CREATED)
                        .withBody(json)));

        ProdutosResponse response = produtosClient.cadastrarProduto(produto)
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_CREATED)
                    .extract()
                    .as(ProdutosResponse.class);

        assert response.getNome().equals(produto.getNome());
    }

    @Test
    public void testDeveRetornarErroAoCadastrarProdutoComCamposVazios() {
        // Criando um produto com campos vazios
        ProdutosModel produto = new ProdutosModel();
        produto.setNome("");
        produto.setDescricao("");
        produto.setPreco("");
        produto.setQuantidade("");

        String json = gson.toJson(produto);

        stubFor(post(urlEqualTo("/produtos"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_BAD_REQUEST)
                        .withBody(json)));

        produtosClient.cadastrarProduto(produto)
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
