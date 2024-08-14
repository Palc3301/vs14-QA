package com.vemser.rest.tests.produtos;

import com.vemser.rest.model.produtos.Produtos;
import com.vemser.rest.model.produtos.ProdutosResponse;
import io.restassured.http.ContentType;
import net.datafaker.Faker;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class AtualizarProdutos {
    private String bearerToken;
    Faker faker = new Faker();
    Random geradorBoolean = new Random();

    @BeforeEach
    public void setup() {
        baseURI = "http://localhost:3000";
        bearerToken = getBearerToken();
    }

    private String getBearerToken() {
        String email = "alyson@qa.com.br";
        String senha = "teste";

        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\"email\":\"" + email + "\",\"password\":\"" + senha + "\"}")
                .when()
                .post("/login")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .path("authorization");
    }

    @Test
    public void testAtualizarProdutoComSucesso() {
        String productId = "RWtxAwZ1MR66g4kn";

        Produtos produto = new Produtos();
        produto.setNome("NOTE");
        produto.setPreco(String.valueOf(faker.number().numberBetween(1, 100)));
        produto.setDescricao(faker.lorem().sentence());
        produto.setQuantidade(String.valueOf(faker.number().numberBetween(1, 100)));

        ProdutosResponse response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", bearerToken)
                .body(produto)
                .when()
                .put("/produtos/" + productId) // Assumindo que há um método PUT e que o ID do produto é necessário
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(ProdutosResponse.class);

        Assertions.assertAll(() ->
                Assertions.assertEquals("Registro alterado com sucesso", response.getMessage()));
    }

    @Test
    public void testAtualizarProdutoComTokenInvalido() {
        String productId = "RWtxAwZ1MR66g4kn";

        Produtos produto = new Produtos();
        produto.setNome("NOTE");
        produto.setPreco(String.valueOf(faker.number().numberBetween(1, 100)));
        produto.setDescricao(faker.lorem().sentence());
        produto.setQuantidade(String.valueOf(faker.number().numberBetween(1, 100)));

        String tokenInvalido = "Invalido";

        ProdutosResponse response =
                given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .header("Authorization", tokenInvalido)
                        .body(produto)
                .when()
                        .put("/produtos/" + productId)
                .then()
                        .log().all()
                        .statusCode(HttpStatus.SC_UNAUTHORIZED)
                        .extract()
                        .as(ProdutosResponse.class);

        Assertions.assertAll(() ->
                Assertions.assertEquals("Token de acesso ausente, inválido, expirado ou usuário do token não existe mais", response.getMessage())
        );
    }

    @Test
    public void testAtualizarProdutoComCamposVazios() {
        // Existing product details
        String productId = "RWtxAwZ1MR66g4kn";

        Produtos produto = new Produtos();
        produto.setNome("");
        produto.setPreco("");
        produto.setDescricao("");
        produto.setQuantidade("");

        ProdutosResponse response =
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", bearerToken)
                .body(produto)
        .when()
                .put("/produtos/" + productId)
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
