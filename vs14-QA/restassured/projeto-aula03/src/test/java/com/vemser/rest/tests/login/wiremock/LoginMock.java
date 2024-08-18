package com.vemser.rest.tests.login.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;


@ExtendWith(WireMockExtension.class)
public class LoginMock {

    @RegisterExtension
    static WireMockExtension wireMockExtension = WireMockExtension.newInstance()
            .options(wireMockConfig().port(8080))
            .build();

    @BeforeEach
    public void setupStubs() {
        WireMock.stubFor(post("/login")
                .withRequestBody(equalToJson("{ \"email\": \"fulano@qa.com\", \"password\": \"teste\" }"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"message\": \"Login realizado com sucesso\", \"authorization\": \"Bearer exampleToken\" }")));

        WireMock.stubFor(post("/login")
                .withRequestBody(equalToJson("{ \"email\": \"\", \"password\": \"teste\" }"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_BAD_REQUEST)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"email\": \"email não pode ficar em branco\" }")));

        WireMock.stubFor(post("/login")
                .withRequestBody(equalToJson("{ \"email\": \"QueroMinhaVaga@qa.com\", \"password\": \"teste\" }"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_UNAUTHORIZED)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"message\": \"Email e/ou senha inválidos\" }")));
    }
}
