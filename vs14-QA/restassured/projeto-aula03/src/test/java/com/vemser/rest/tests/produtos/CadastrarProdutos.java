package com.vemser.rest.tests.produtos;

import com.vemser.rest.client.ProdutosClient;
import com.vemser.rest.data.factory.ProdutosDataFactory;
import com.vemser.rest.model.ProdutosModel;
import com.vemser.rest.model.ProdutosResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CadastrarProdutos {

    private final ProdutosClient produtosClient = new ProdutosClient("http://localhost:3000");

    @Test
    public void testCriarProdutoComSucesso() {
        ProdutosModel produto = ProdutosDataFactory.produtoValido();

        produtosClient.cadastrarProduto(produto)
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_CREATED)
                    .body(matchesJsonSchemaInClasspath("schemas/produto_post.json"));
    }


    @Test
    public void testCriarProdutoComCamposVazios() {
        ProdutosModel produto = ProdutosDataFactory.produtoComCamposVazios();

        ProdutosResponse response =
                produtosClient.cadastrarProduto(produto)
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(ProdutosResponse.class);

        Assertions.assertFalse(response.getNome().contains("Nome não pode ficar em branco"));
        Assertions.assertFalse(response.getPreco().contains("Preço não pode ficar em branco"));
        Assertions.assertFalse(response.getDescricao().contains("Descrição não pode ficar em branco"));
        Assertions.assertFalse(response.getQuantidade().contains("Quantidade não pode ficar em branco"));
    }

    @Test
    public void testCriarProdutoSemAutenticacao() {
        ProdutosModel produto = ProdutosDataFactory.produtoValido();

        ProdutosResponse response =
        produtosClient.cadastrarProdutoSemAutenticacao(produto)
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .extract()
                .as(ProdutosResponse.class);

         Assertions.assertEquals("Token de acesso ausente, inválido, expirado " +
                    "ou usuário do token não existe mais", response.getMessage());
    }

    @Test
    public void testCriarProdutoComNomeJaUtilizado() {
        ProdutosModel produto = ProdutosDataFactory.produtoComNomeUtilizado();

        ProdutosResponse response = produtosClient.cadastrarProduto(produto)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(ProdutosResponse.class);
        Assertions.assertEquals("Já existe produto com esse nome", response.getMessage());
    }

    @ParameterizedTest
    @MethodSource("com.vemser.rest.provider.ProdutosDataProvider#produtoDataProvider")
    public void testCriarProdutoComCamposVaziosJUnit(ProdutosModel produto, String key, String expectedValue) {
        ProdutosResponse response = produtosClient.cadastrarProduto(produto)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(ProdutosResponse.class);
    }
}


