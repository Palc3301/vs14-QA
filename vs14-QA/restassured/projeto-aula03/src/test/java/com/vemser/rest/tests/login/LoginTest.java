package com.vemser.rest.tests.login;

import com.vemser.rest.client.LoginClient;
import com.vemser.rest.data.factory.LoginDataFactory;
import com.vemser.rest.model.LoginResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private final LoginClient loginClient = new LoginClient();


    @Test
    public void testLoginComSucesso() {
        LoginResponse response = loginClient.realizarLoginComSucesso()
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(LoginResponse.class);

        assertAll(() -> {
            assertEquals("Login realizado com sucesso", response.getMessage());
            assertFalse(response.getAuthorization().contains("Bearer " + response.getAuthorization()));
        });

        System.out.println(response.getAuthorization());
    }

    @Test
    public void testLoginComEmailVazio() {
        LoginResponse response = loginClient.realizarLoginSemAutenticacao(LoginDataFactory.loginComEmailVazio().getEmail(), LoginDataFactory.loginComEmailVazio().getPassword())
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(LoginResponse.class);

        assertEquals("email não pode ficar em branco", response.getEmail());
    }

    @Test
    public void testLoginComUsuarioInexistente() {
        LoginResponse response = loginClient.realizarLoginSemAutenticacao(LoginDataFactory.loginComUsuarioInexistente().getEmail(), LoginDataFactory.loginComUsuarioInexistente().getPassword())
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .extract()
                .as(LoginResponse.class);

        assertEquals("Email e/ou senha inválidos", response.getMessage());
    }

    /////////////////////////MOCK/////////////////////////

    @Test
    public void testLoginComSucessoMock() {
        LoginResponse response = loginClient.realizarLoginComSucessoMock()
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(LoginResponse.class);

        assertAll(() -> {
            assertEquals("Login realizado com sucesso", response.getMessage());
            assertTrue(response.getAuthorization().startsWith("Bearer "));
        });

        System.out.println(response.getAuthorization());
    }
}
