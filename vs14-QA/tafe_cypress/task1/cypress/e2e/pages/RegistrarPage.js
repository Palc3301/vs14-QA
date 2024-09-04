export default class RegistrarPage {

  url = 'https://bugbank.netlify.app/'
  btnRegistrar = '.ihdmxA'

  email = ':nth-child(2) > .input__default'
  nome = ':nth-child(3) > .input__default'
  senha = ':nth-child(4) > .style__ContainerFieldInput-sc-s3e9ea-0 > .input__default'
  confirmarSenha = ':nth-child(5) > .style__ContainerFieldInput-sc-s3e9ea-0 > .input__default'
  
  btnContaComSaldo = '#toggleAddBalance'
  btnCadastrar = '.styles__ContainerFormRegister-sc-7fhc7g-0 > .style__ContainerButton-sc-1wsixal-0'
  
  texto = '#modalText'
  campoObrigatorio = ':nth-child(5) > .style__ContainerFieldInput-sc-s3e9ea-0 > .input__warging'

  navegar() {
    cy.visit(this.url)
  }

  clicarRegistrar() {
    cy.get(this.btnRegistrar).click()
  }

  preencherEmail(email) {
    cy.get(this.email).type(email, { force: true })
  }

  preencherUsername(nome) {
    cy.get(this.nome).type(nome, { force: true })
  }

  preencherSenha(senha) {
    cy.get(this.senha).type(senha, { force: true })
  }

  preencherConfirmarSenha(confirmarSenha) {
    cy.get(this.confirmarSenha).type(confirmarSenha, { force: true })
  }

  clicarBtnContaComSaldo() {
    cy.get(this.btnContaComSaldo).click({ force: true })
  }

  clicarCadastrar() {
    cy.get(this.btnCadastrar).click({ force: true })
  }

  validarTexto(texto) {
    cy.get(this.texto).contains(texto)
  }
  
}