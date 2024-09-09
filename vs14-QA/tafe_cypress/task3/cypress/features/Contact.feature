Feature: Teste contact

Background:
    Given que o usuário está na página de contato

  Scenario: Ct-01 Tentar Realizar Contato Válido
    When o usuário preenche todos os campos obrigatórios do formulário de contato
    Then o sistema deve enviar o formulário de contato com sucesso

  Scenario: Ct-02 Tentar Realizar Contato com Campos Vazios
    When o usuário tenta enviar o formulário de contato sem preencher os campos
    Then o sistema deve exibir mensagens de erro informando que os campos são obrigatórios