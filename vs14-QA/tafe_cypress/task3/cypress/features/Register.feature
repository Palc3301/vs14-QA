Feature: Teste registro no sistema

Background:
    Given que o usuário está na página de login

Scenario: Ct-01 Tentar Registrar Com Sucesso
    And o usuario clica no botao register
    When o usuario preencher todos os campos obrigatorios
    And o usuario clica em "REGISTER"
    Then o usuario deve ser redirecionado para a tela inicial

Scenario: Ct-02 Tentar Registrar Em Branco
    And o usuario clica no botao register
    When o usuario deixa campos em branco
    And o usuario clica em "REGISTER"
    Then o sistema nao deve permitir o registro e exibir mensagens de erro

Scenario: Ct-03 Tentar Registrar Com Senha Diferente
    And o usuario clica no botao register
    When o usuario preenche duas senhas diferentes uma da outra
    And o usuario clica em "REGISTER"
    Then o sistema deve exibir uma mensagem de erro e nao permitir o acesso