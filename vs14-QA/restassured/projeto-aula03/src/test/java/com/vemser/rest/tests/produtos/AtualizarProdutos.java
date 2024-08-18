package com.vemser.rest.tests.produtos;

import com.vemser.rest.client.ProdutosClient;
import com.vemser.rest.data.factory.ProdutosDataFactory;
import com.vemser.rest.model.ProdutosModel;
import com.vemser.rest.model.ProdutosResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AtualizarProdutos {

    private final ProdutosClient produtosClient = new ProdutosClient();

    @Test
    public void testAtualizarProdutoComSucesso() {
        ProdutosModel produto = ProdutosDataFactory.produtoValido();
        String produtoId = produtosClient.cadastrarProdutoEObterId(produto);

        ProdutosModel produtoAtualizado = ProdutosDataFactory.produtoValido();
        ProdutosResponse response = produtosClient.atualizarProduto(produtoId, produtoAtualizado)
                .then()
                    .log().all()
                    .extract()
                    .as(ProdutosResponse.class);

        Assertions.assertEquals("Registro alterado com sucesso", response.getMessage());
    }

    @Test
    public void testAtualizarProdutoComTokenInvalido() {
        ProdutosModel produto = ProdutosDataFactory.produtoValido();

        ProdutosResponse response = produtosClient.atualizarProdutoSemAutenticacao(produto)
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_UNAUTHORIZED)
                    .extract()
                    .as(ProdutosResponse.class);

                Assertions.assertEquals("Token de acesso ausente, inválido, expirado ou usuário do token não existe mais", response.getMessage());
    }

    @Test
    public void testAtualizarProdutoComCamposVazios() {
        ProdutosModel produto = ProdutosDataFactory.produtoComCamposVazios();

        ProdutosResponse response = produtosClient.atualizarProdutoT(produto)
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
}
