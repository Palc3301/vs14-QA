package com.vemser.rest.tests.produtos;

import com.vemser.rest.client.ProdutosClient;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;


import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ListarProdutos {

    private final ProdutosClient produtosClient = new ProdutosClient("http://localhost:3000");


    @Test
    public void testDeveValidarContratoTodosOsProdutos() {
        produtosClient.listarProdutos()
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("schemas/produtos.json"))
        ;
    }

    @Test
    public void testGetTodosOsProdutosPorId() {
        produtosClient.listarProdutoPorId()
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("schemas/produtos_por_id.json"))
        ;
    }

    @Test
    public void testGetTodosOsProdutosIdInexistente() {
        produtosClient.listarProdutoPorIdInexistente()
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);

    }

    @Test
    public void testGetProdutosIdVazio() {
        produtosClient.listarProdutoPorIdInexistente()
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

}