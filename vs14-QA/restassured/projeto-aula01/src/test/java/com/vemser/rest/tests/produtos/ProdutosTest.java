package com.vemser.rest.tests.produtos;

import com.vemser.rest.pojo.ProdutosPOJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class ProdutosTest {

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
                .statusCode(200)
                .extract()
                .path("authorization");
    }


///////////////////////////// SECTION GET /////////////////////////////
    @Test
    public void testGetTodosOsProdutos() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
        .when()
                .get("/produtos")
        .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void testGetTodosOsProdutosPorId() {
        String produtoId = "RWtxAwZ1MR66g4kn";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", produtoId)
        .when()
                .get("/produtos/{id}")
        .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void testGetTodosOsProdutosIdInexistente() {
        String produtoId = "IDInexistente";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", produtoId)
        .when()
                .get("/produtos/{id}")
        .then()
                .log().all()
                .statusCode(400);
    }

    @Test
    public void testGetProdutosIdVazio() {
        String produtoId = " ";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", produtoId)
        .when()
                .get("/produtos/{id}")
        .then()
                .log().all()
                .statusCode(400);
    }

///////////////////////////// SECTION POST /////////////////////////////

    @Test
    public void testCriarProdutoComSucesso() {
        ProdutosPOJO produto = new ProdutosPOJO();
        produto.setNome(faker.commerce().productName());
        produto.setPreco(String.valueOf(faker.number().numberBetween(1, 100)));
        produto.setDescricao(faker.lorem().sentence());
        produto.setQuantidade(String.valueOf(faker.number().numberBetween(1, 100)));

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", bearerToken)
                .body(produto)
        .when()
                .post("/produtos")
        .then()
                .log().all()
                .statusCode(201);
    }

    @Test
    public void testCriarProdutoComCamposVazios() {
        ProdutosPOJO produto = new ProdutosPOJO();
        produto.setNome("");
        produto.setPreco("");
        produto.setDescricao("");
        produto.setQuantidade("");

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", bearerToken)
                .body(produto)
        .when()
                .post("/produtos")
        .then()
                .log().all()
                .statusCode(400);
    }

    @Test
    public void testCriarProdutoSemAutenticacao() {
        ProdutosPOJO produto = new ProdutosPOJO();
        produto.setNome(faker.commerce().productName());
        produto.setPreco(String.valueOf(faker.number().numberBetween(1, 100)));
        produto.setDescricao(faker.lorem().sentence());
        produto.setQuantidade(String.valueOf(faker.number().numberBetween(1, 100)));

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(produto)
        .when()
                .post("/produtos")
        .then()
                .log().all()
                .statusCode(401);
    }

    @Test
    public void testCriarProdutoComNomeJaUtilizado() {
        // Primeiro, criar um produto com um nome específico
        ProdutosPOJO produtoExistente = new ProdutosPOJO();
        String nomeDuplicado = "Logitech";
        produtoExistente.setNome(nomeDuplicado);
        produtoExistente.setPreco(String.valueOf(faker.number().numberBetween(1, 100)));
        produtoExistente.setDescricao(faker.lorem().sentence());
        produtoExistente.setQuantidade(String.valueOf(faker.number().numberBetween(1, 100)));

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", bearerToken)
                .body(produtoExistente)
        .when()
                .post("/produtos")
        .then()
                .log().all()
                .statusCode(400);
    }

///////////////////////////// SECTION PUT /////////////////////////////
    @Test
    public void testAtualizarProdutoComSucesso() {
        // Existing product details
        String productId = "RWtxAwZ1MR66g4kn";

        ProdutosPOJO produto = new ProdutosPOJO();
        produto.setNome("NOTE");
        produto.setPreco(String.valueOf(faker.number().numberBetween(1, 100)));
        produto.setDescricao(faker.lorem().sentence());
        produto.setQuantidade(String.valueOf(faker.number().numberBetween(1, 100)));

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", bearerToken)
                .body(produto)
        .when()
                .put("/produtos/" + productId)
        .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void testAtualizarProdutoComTokenInvalido() {
        // Existing product details
        String productId = "RWtxAwZ1MR66g4kn";

        ProdutosPOJO produto = new ProdutosPOJO();
        produto.setNome("NOTE");
        produto.setPreco(String.valueOf(faker.number().numberBetween(1, 100)));
        produto.setDescricao(faker.lorem().sentence());
        produto.setQuantidade(String.valueOf(faker.number().numberBetween(1, 100)));

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(produto)
        .when()
                .put("/produtos/" + productId)
        .then()
                .log().all()
                .statusCode(401);
    }

    @Test
    public void testAtualizarProdutoComCamposVazios() {
        // Existing product details
        String productId = "RWtxAwZ1MR66g4kn";

        ProdutosPOJO produto = new ProdutosPOJO();
        produto.setNome("");
        produto.setPreco("");
        produto.setDescricao("");
        produto.setQuantidade("");

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", bearerToken)
                .body(produto)
        .when()
                .put("/produtos/" + productId)
        .then()
                .log().all()
                .statusCode(400);
    }

    ///////////////////////////// SECTION DELETE /////////////////////////////

    //Tive ajuda do GPT//
    @Test
    public void testDeletarProdutoComSucesso() {
        ProdutosPOJO produto = new ProdutosPOJO();
        produto.setNome(faker.commerce().productName());
        produto.setPreco(String.valueOf(faker.number().numberBetween(1, 100)));
        produto.setDescricao(faker.lorem().sentence());
        produto.setQuantidade(String.valueOf(faker.number().numberBetween(1, 100)));

        Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", bearerToken)
                .body(produto)
        .when()
                .post("/produtos")
        .then()
                .log().all()
                .statusCode(201)
                .extract().response();

        //Pega o id
        String productId = response.jsonPath().getString("_id");

        //Deletar
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", bearerToken)
        .when()
                .delete("/produtos/" + productId)
        .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void testDeletarProdutoComIdInexistente() {
        // Non-existent product ID
        String productId = "inexistente";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", bearerToken)
        .when()
                .delete("/produtos/" + productId)
        .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void testDeletarProdutoQueEstaNoCarrinho() {
        String productId = "Rmhbc1gZuOvl6bbY";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", bearerToken)
        .when()
                .delete("/produtos/" + productId)
        .then()
                .log().all()
                .statusCode(400);
    }

}
