package com.vemser.rest.tests.produtos;

import com.vemser.rest.client.ProdutosClient;
import com.vemser.rest.data.factory.ProdutosDataFactory;
import com.vemser.rest.model.ProdutosModel;
import com.vemser.rest.model.ProdutosResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeletarProdutos {

    private final ProdutosClient produtosClient = new ProdutosClient("http://localhost:3000");

    @Test
    public void testDeletarProdutoComSucesso() {
        ProdutosModel produto = ProdutosDataFactory.produtoValido();

        ProdutosResponse response = produtosClient.cadastrarProduto(produto)
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_CREATED)
                    .extract()
                    .as(ProdutosResponse.class);

        String produtoId = response.getId();

        ProdutosResponse responseDelete = produtosClient.deletarProdutoPorId(produtoId)
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .as(ProdutosResponse.class);

        Assertions.assertAll(
                () -> Assertions.assertEquals("Cadastro realizado com sucesso", response.getMessage()),
                () -> Assertions.assertEquals("Registro excluído com sucesso", responseDelete.getMessage())
        );
    }

    @Test
    public void testDeletarProdutoQueEstaNoCarrinho() {

        ProdutosResponse response =produtosClient.deletarProdutoQueEstaNoCarrinho()
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract().response()
                    .as(ProdutosResponse.class);

        Assertions.assertEquals("Não é permitido excluir produto que faz parte de carrinho", response.getMessage());
    }

    @Test
    public void testDeletarProdutoComIdInexistente() {

        ProdutosResponse response =
       produtosClient.deletarProdutoPorIdInexistente()
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_OK)
                    .extract().response()
                    .as(ProdutosResponse.class);
        Assertions.assertEquals("Nenhum registro excluído", response.getMessage());

    }

}
