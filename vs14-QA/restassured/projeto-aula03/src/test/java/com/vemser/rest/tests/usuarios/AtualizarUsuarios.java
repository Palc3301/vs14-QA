package com.vemser.rest.tests.usuarios;

import com.vemser.rest.client.UsuariosClient;
import com.vemser.rest.data.factory.UsuariosDataFactory;
import com.vemser.rest.model.UsuariosModel;
import com.vemser.rest.model.UsuariosResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AtualizarUsuarios {

    private final UsuariosClient usuariosClient = new UsuariosClient();


    @Test
    public void testAtualizarUsuariosComSucesso() {
        UsuariosModel usuario = UsuariosDataFactory.usuarioValido();

        UsuariosResponse response =
                usuariosClient.atualizarUsuario(usuario)
                        .then()
                        .log().all()
                        .statusCode(HttpStatus.SC_CREATED)
                        .extract()
                        .as(UsuariosResponse.class);
        Assertions.assertNotNull(response.getId(), "O ID do usuário não deve ser nulo");
    }

    @Test
    public void testAtualizarUsuariosComCamposEmBranco() {
        UsuariosModel usuario = UsuariosDataFactory.usuarioComEmailEmBranco();


        UsuariosResponse response =
                usuariosClient.atualizarUsuario(usuario)
                        .then()
                        .log().all()
                        .statusCode(HttpStatus.SC_BAD_REQUEST)
                        .extract()
                        .as(UsuariosResponse.class);

        Assertions.assertEquals("email não pode ficar em branco",
                response.getEmail());
    }

    @Test
    public void testAtualizarUsuariosComEmailExistente() {
        UsuariosModel usuario = UsuariosDataFactory.usuarioComEmailExistente();

        UsuariosResponse response =
                usuariosClient.atualizarUsuario(usuario)
                        .then()
                        .log().all()
                        .statusCode(HttpStatus.SC_BAD_REQUEST)
                        .extract()
                        .as(UsuariosResponse.class);

    Assertions.assertEquals("Este email já está sendo usado", response.getMessage());
    }
}


