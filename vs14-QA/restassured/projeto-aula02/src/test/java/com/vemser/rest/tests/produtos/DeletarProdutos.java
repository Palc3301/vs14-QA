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

public class DeletarProdutos {

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

        return
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\"email\":\"" + email + "\",\"password\":\"" + senha + "\"}")
        .when()
                .post("/login")
        .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("authorization");
    }


    @Test
    public void testDeletarProdutoComSucesso() {
        Produtos produto = new Produtos();
        produto.setNome(faker.commerce().productName());
        produto.setPreco(String.valueOf(faker.number().numberBetween(1, 100)));
        produto.setDescricao(faker.lorem().sentence());
        produto.setQuantidade(String.valueOf(faker.number().numberBetween(1, 100)));


        ProdutosResponse response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", bearerToken)
                .body(produto)
        .when()
                .post("/produtos")
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().response()
                .as(ProdutosResponse.class);

        String productId = response.getId();

        ProdutosResponse responseDelete=

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", bearerToken)
        .when()
                .delete("/produtos/" + productId)
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().response()
                .as(ProdutosResponse.class);
        Assertions.assertAll(() -> Assertions.assertEquals("Cadastro realizado com sucesso", response.getMessage()));
        Assertions.assertAll(() -> Assertions.assertEquals("Registro excluído com sucesso", responseDelete.getMessage()));

    }

    @Test
    public void testDeletarProdutoComIdInexistente() {
        String productId = "inexistente";

        ProdutosResponse response =
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", bearerToken)
                .when()
                .delete("/produtos/" + productId)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().response()
                .as(ProdutosResponse.class);
        Assertions.assertAll(() -> Assertions.assertEquals("Nenhum registro excluído", response.getMessage()));

    }

    @Test
    public void testDeletarProdutoQueEstaNoCarrinho() {
        String productId = "Rmhbc1gZuOvl6bbY";

        ProdutosResponse response =
                given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .header("Authorization", bearerToken)
                .when()
                        .delete("/produtos/" + productId)
                .then()
                        .log().all()
                        .statusCode(HttpStatus.SC_BAD_REQUEST)
                        .extract().response()
                        .as(ProdutosResponse.class);

        Assertions.assertAll(() -> Assertions.assertEquals("Não é permitido excluir produto que faz parte de carrinho", response.getMessage()));
        Assertions.assertAll(() -> Assertions.assertFalse(response.getIdCarrinhos().contains(productId)));
    }


}
