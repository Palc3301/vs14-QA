package com.vemser.rest.tests.produtos.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.google.gson.Gson;
import com.vemser.rest.client.ProdutosClient;
import com.vemser.rest.model.ProdutosResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.vemser.rest.tests.Utils.stubLogin;
import static io.restassured.RestAssured.defaultParser;

public class AtualizarProdutosWireMock {

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
    public void testDeveAtualizarProdutoComSucesso() {
        // Simulando a resposta do servidor ao atualizar um produto
        Map<String, Object> produtoAtualizado = new HashMap<>();
        produtoAtualizado.put("nome", "Produto Atualizado");
        produtoAtualizado.put("preco", 200);
        produtoAtualizado.put("descricao", "Descrição do produto atualizado");
        produtoAtualizado.put("quantidade", 5);
        produtoAtualizado.put("_id", "1");
        produtoAtualizado.put("message", "Registro alterado com sucesso");

        String json = gson.toJson(produtoAtualizado);

        stubFor(put(urlEqualTo("/produtos/BTsN8hjdOJxWFknM"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withBody(json)));

        ProdutosResponse response = produtosClient.atualizarProdutoMock("BTsN8hjdOJxWFknM", produtoAtualizado)
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .as(ProdutosResponse.class);

        // Validando a resposta
        assert response.getMessage().equals("Registro alterado com sucesso");
    }

    @Test
    public void testDeveRetornarErroAoAtualizarProdutoInexistente() {
        // Criando um produto que não existe
        Map<String, Object> produtoAtualizado = new HashMap<>();
        produtoAtualizado.put("nome", "Produto Inexistente");
        produtoAtualizado.put("preco", 300);
        produtoAtualizado.put("descricao", "Descrição do produto inexistente");
        produtoAtualizado.put("quantidade", 3);
        produtoAtualizado.put("_id", "nãoExiste");

        String json = gson.toJson(produtoAtualizado);

        stubFor(put(urlEqualTo("/produtos/nãoExiste"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_NOT_FOUND)
                        .withBody(json)));

        produtosClient.atualizarProdutoMock("BTsN8hjdOJxWFknM", produtoAtualizado)
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void testDeveRetornarErroAoAtualizarProdutoComCamposVazios() {
        Map<String, Object> produtoAtualizado = new HashMap<>();
        produtoAtualizado.put("nome", "");
        produtoAtualizado.put("preco", "");
        produtoAtualizado.put("descricao", "");
        produtoAtualizado.put("quantidade", "");
        produtoAtualizado.put("_id", "1");

        String json = gson.toJson(produtoAtualizado);

        stubFor(put(urlEqualTo("/produtos/BTsN8hjdOJxWFknM"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_BAD_REQUEST)
                        .withBody(json)));

        produtosClient.atualizarProdutoMock("BTsN8hjdOJxWFknM", produtoAtualizado)
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
