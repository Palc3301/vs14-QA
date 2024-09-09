Feature: Teste de Login no Sistema

  Background:
    Given que o usuário está na página de login

  Scenario: Ct-01 Tentar Realizar Login Inválido
    When o usuário tenta realizar login com dados inválidos
    Then o sistema deve exibir uma mensagem de erro

  Scenario: Ct-02 Tentar Realizar Login Sem Dados
    When o usuário tenta realizar login sem preencher os campos
    Then o sistema deve exibir uma mensagem de erro informando que os campos são obrigatórios