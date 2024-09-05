//RegistrarPage.js
  let btnRegistrar = '.ihdmxA'

  let email = ':nth-child(2) > .input__default'
  let nome = ':nth-child(3) > .input__default'
  let senha = ':nth-child(4) > .style__ContainerFieldInput-sc-s3e9ea-0 > .input__default'
  let confirmarSenha = ':nth-child(5) > .style__ContainerFieldInput-sc-s3e9ea-0 > .input__default'
  
  let btnContaComSaldo = '#toggleAddBalance'
  let btnCadastrar = '.styles__ContainerFormRegister-sc-7fhc7g-0 > .style__ContainerButton-sc-1wsixal-0'
  
  let texto = '#modalText'
  let campoObrigatorio = ':nth-child(5) > .style__ContainerFieldInput-sc-s3e9ea-0 > .input__warging'
  let btnFechar = '#btnCloseModal'

  Cypress.Commands.add('realizarRegistroComSucesso', (dados) => {
    cy.clicar(btnRegistrar);
    cy.preencherCampo(email, dados.email);
    cy.preencherCampo(nome, dados.nome);
    cy.preencherCampo(senha, dados.password);
    cy.preencherCampo(confirmarSenha, dados.password);
    cy.clicar(btnContaComSaldo);
    cy.clicar(btnCadastrar);
    cy.clicar(btnFechar);
  });

  Cypress.Commands.add('validarRegistroComEmailInvalido', (dados) => {
    cy.clicar(btnRegistrar);
    cy.preencherCampo(email, dados.emailInvalido);
    cy.preencherCampo(nome, dados.nome);
    cy.preencherCampo(senha, dados.password);
    cy.preencherCampo(confirmarSenha, dados.password);
    cy.clicar(btnContaComSaldo);
    cy.clicar(btnCadastrar);
  })

  Cypress.Commands.add('validarRegistroComCamposEmBranco', (dados) => {
    cy.clicar(btnRegistrar);
    cy.clicar(btnContaComSaldo);
    cy.clicar(btnCadastrar);
  })




  