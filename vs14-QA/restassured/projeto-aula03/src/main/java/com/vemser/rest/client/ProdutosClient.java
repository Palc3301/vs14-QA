package com.vemser.rest.client;

import com.vemser.rest.model.ProdutosModel;
import com.vemser.rest.model.ProdutosResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ProdutosClient extends BaseClient{

    private final String PRODUTOS = "/produtos";
    private final String FIXED_ID = "BTsN8hjdOJxWFknM";
    private final String CART_ID = "Rmhbc1gZuOvl6bbY";
    private final String FAKE_ID = "Rmhb";

    public ProdutosClient(String baseURI) {
        super(baseURI);
    }


    public Response cadastrarProduto(ProdutosModel produto) {
        return
                given()
                    .spec(super.set())
                    .contentType(ContentType.JSON)
                    .header("Authorization", getBearerToken())
                    .body(produto)
                .when()
                    .post(PRODUTOS);
    }
    public Response cadastrarProdutoSemAutenticacao(ProdutosModel produto) {
        return
                given()
                        .spec(super.set())
                        .contentType(ContentType.JSON)
                        .body(produto)
                .when()
                        .post(PRODUTOS);
    }


    public Response atualizarProdutoSemAutenticacao(ProdutosModel produto) {
        return given()
                    .spec(super.set())
                    .contentType(ContentType.JSON)
                    .body(produto)
                    .pathParam("id", FIXED_ID)
                .when()
                    .put(PRODUTOS + "/{id}");
    }

    public Response listarProdutos() {
        return given()
                    .spec(super.set())
                    .header("Authorization", getBearerToken())
                .when()
                    .get(PRODUTOS);
    }

    public Response listarProdutoPorId() {
        return given()
                    .spec(super.set())
                    .header("Authorization", getBearerToken())
                    .pathParam("id", CART_ID)
                .when()
                    .get(PRODUTOS + "/{id}");
    }

    public Response listarProdutoPorIdInexistente() {
        return given()
                    .spec(super.set())
                    .header("Authorization", getBearerToken())
                    .pathParam("id", FAKE_ID)
                .when()
                    .get(PRODUTOS + "/{id}");
    }

    public Response listarProdutoPorIdVazio() {
        return given()
                    .spec(super.set())
                    .header("Authorization", getBearerToken())
                .when()
                    .get(PRODUTOS + "/{id}");
    }

    public Response listarProdutosPorNome(String nome) {
        return given()
                    .spec(super.set())
                    .queryParam("nome", nome)
                    .header("Authorization", getBearerToken())
                .when()
                    .get(PRODUTOS);
    }

    public Response deletarProdutoPorId(String id) {
        return given()
                    .spec(super.set())
                    .pathParam("id", id)  // Use the passed ID here
                    .header("Authorization", getBearerToken())
                .when()
                    .delete(PRODUTOS + "/{id}");
    }
    public Response deletarProdutoQueEstaNoCarrinho() {
        return given()
                    .spec(super.set())
                    .pathParam("id", CART_ID)
                    .header("Authorization", getBearerToken())
                .when()
                    .delete(PRODUTOS + "/{id}");
    }

    public Response deletarProdutoPorIdInexistente() {
        return given()
                    .spec(super.set())
                    .pathParam("id", FAKE_ID)
                    .header("Authorization", getBearerToken())
                .when()
                    .delete(PRODUTOS + "/{id}");
    }

    public String cadastrarProdutoEObterId(ProdutosModel produto) {
        Response response = given()
                .spec(super.set())
                    .contentType(ContentType.JSON)
                    .header("Authorization", getBearerToken())
                    .body(produto)
                .when()
                    .post(PRODUTOS);

        return response.then().extract().path("_id");
    }

    public Response atualizarProduto(String id, ProdutosModel produto) {
        return given()
                .spec(super.set())
                .contentType(ContentType.JSON)
                .header("Authorization", getBearerToken())
                .body(produto)
                .pathParam("id", id)
                .when()
                .put(PRODUTOS + "/{id}");
    }


    public String getBearerToken() {
        String email = "alyson@qa.com.br";
        String senha = "teste";

        return
                given()
                    .spec(super.set())
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

    public Response atualizarProdutoT(ProdutosModel produto) {
        return given()
                .spec(super.set())
                .contentType(ContentType.JSON)
                .header("Authorization", getBearerToken())
                .body(produto)
                .pathParam("id", FIXED_ID)
                .when()
                .put(PRODUTOS + "/{id}");
    }
}
