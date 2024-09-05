 //LoginPage.js
  let campoEmail = '.style__ContainerFormLogin-sc-1wbjw6k-0 > :nth-child(1) > .input__default'
  let campoSenha = '.style__ContainerFormLogin-sc-1wbjw6k-0 > .login__password > .style__ContainerFieldInput-sc-s3e9ea-0 > .input__default'

  let btnAcessar = '.otUnI'
  let texto = '[data-test="error"]'
  let textCriado = '#modalText'

  //cadastras para login 
  let btnRegistrar = '.ihdmxA'

  let emailCadastro = ':nth-child(2) > .input__default'
  let nomeCadastro = ':nth-child(3) > .input__default'
  let senhaCadastro = ':nth-child(4) > .style__ContainerFieldInput-sc-s3e9ea-0 > .input__default'
  let confirmarSenhaCadastro = ':nth-child(5) > .style__ContainerFieldInput-sc-s3e9ea-0 > .input__default'
  
  let btnContaComSaldoCadastro = '#toggleAddBalance'
  let btnCadastrar = '.styles__ContainerFormRegister-sc-7fhc7g-0 > .style__ContainerButton-sc-1wsixal-0'
  let campoObrigatorio = ':nth-child(5) > .style__ContainerFieldInput-sc-s3e9ea-0 > .input__warging'

  let btnFechar = '#btnCloseModal'

  Cypress.Commands.add('fazerLogin', (dados) => {
    cy.preencherCampo(campoEmail, dados.email)
    cy.preencherCampo(campoSenha, dados.password)
    cy.clicar(btnAcessar)
  })

  Cypress.Commands.add('fazerLoginComDadosEmBranco', (dados) => {
    cy.clicar(btnAcessar)
  })

  Cypress.Commands.add('loginDadoInvalido', (dados) => {
    cy.preencherCampo(campoEmail, dados.emailInvalido)
    cy.preencherCampo(campoSenha, dados.password)
    cy.clicar(btnAcessar)
  })

  Cypress.Commands.add('loginNaoCadastrado', (dados) => {
    cy.preencherCampo(campoEmail, dados.emailNCadastrado)
    cy.preencherCampo(campoSenha, dados.password)
    cy.clicar(btnAcessar)
  })