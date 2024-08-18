package com.vemser.rest.tests;

import com.google.gson.Gson;
import com.vemser.rest.model.LoginResponse;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class Utils {

    private static final Gson gson = new Gson();

    public static void stubLogin() {

        // ISSO DAQUI TEM QUE TER NO SETUP DE TODOS OS TESTES QUE PRECISAM DE AUTENTICAÇÃO
        // PRODUTOS CLIENT SEMPRE FAZ UMA CHAMADA DE AUTENTICAÇÃO NA FUNCAO getBearerToken()
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("Login realizado com sucesso");
        loginResponse.setAuthorization("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImFseXNvbkBxYS5jb20uYnIiLCJpYXQiOjE2MzIwNjQwNzIsImV4cCI6MTYzMjA2NzY3Mn0.1");

        String json = gson.toJson(loginResponse);

        stubFor(post(urlEqualTo("/login"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(json)));
    }
}
