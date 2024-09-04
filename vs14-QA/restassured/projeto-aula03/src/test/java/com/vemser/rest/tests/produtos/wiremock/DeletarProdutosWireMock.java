package com.vemser.rest.tests.produtos.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.google.gson.Gson;
import com.vemser.rest.client.ProdutosClient;
import com.vemser.rest.model.ProdutosModel;
import com.vemser.rest.model.ProdutosResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.vemser.rest.tests.Utils.stubLogin;
import static io.restassured.RestAssured.defaultParser;

public class DeletarProdutosWireMock {

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
    public void testDeletarProdutoComSucesso() {
        ProdutosModel produto = new ProdutosModel();
        produto.setNome("Produto Teste");
        produto.setDescricao("Descrição do Produto Teste");
        produto.setPreco("50");
        produto.setQuantidade("10");

        String produtoId = "123";

        stubFor(post(urlEqualTo("/produtos"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_CREATED)
                        .withBody(gson.toJson(produto))));

        stubFor(delete(urlEqualTo("/produtos/" + produtoId))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withBody("{\"message\": \"Registro excluído com sucesso\"}")));

        ProdutosResponse responseDelete = produtosClient.deletarProdutoPorId(produtoId)
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .as(ProdutosResponse.class);
    }

    @Test
    public void testDeletarProdutoQueEstaNoCarrinho() {
        String produtoId = "Rmhbc1gZuOvl6bbY";

        // Simular a tentativa de excluir um produto que está no carrinho
        stubFor(delete(urlEqualTo("/produtos/" + produtoId))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_BAD_REQUEST)
                        .withBody("{\"message\": \"Não é permitido excluir produto que faz parte de carrinho\"}")));

        ProdutosResponse response = produtosClient.deletarProdutoPorId(produtoId)
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract().response()
                    .as(ProdutosResponse.class);
    }

    @Test
    public void testDeletarProdutoComIdInexistente() {
        String produtoIdInexistente = "Rmhbc";

        stubFor(delete(urlEqualTo("/produtos/" + produtoIdInexistente))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withBody("{\"message\": \"Nenhum registro excluído\"}")));

        ProdutosResponse response = produtosClient.deletarProdutoPorId(produtoIdInexistente)
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_OK)
                    .extract().response()
                    .as(ProdutosResponse.class);
    }
}
