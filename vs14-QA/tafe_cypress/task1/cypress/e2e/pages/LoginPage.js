export default class LoginPage {

  url = 'https://bugbank.netlify.app/'
  email = '.style__ContainerFormLogin-sc-1wbjw6k-0 > :nth-child(1) > .input__default'
  senha = '.style__ContainerFormLogin-sc-1wbjw6k-0 > .login__password > .style__ContainerFieldInput-sc-s3e9ea-0 > .input__default'

  btnAcessar = '.otUnI'
  texto = '[data-test="error"]'
  textCriado = '#modalText'

  //cadastras para login 
  btnRegistrar = '.ihdmxA'

  emailCadastro = ':nth-child(2) > .input__default'
  nomeCadastro = ':nth-child(3) > .input__default'
  senhaCadastro = ':nth-child(4) > .style__ContainerFieldInput-sc-s3e9ea-0 > .input__default'
  confirmarSenhaCadastro = ':nth-child(5) > .style__ContainerFieldInput-sc-s3e9ea-0 > .input__default'
  
  btnContaComSaldoCadastro = '#toggleAddBalance'
  btnCadastrar = '.styles__ContainerFormRegister-sc-7fhc7g-0 > .style__ContainerButton-sc-1wsixal-0'
  campoObrigatorio = ':nth-child(5) > .style__ContainerFieldInput-sc-s3e9ea-0 > .input__warging'

  btnFechar = '#btnCloseModal'

  navegar() {
    cy.visit(this.url)
  }

  preencherEmail(email) {
    cy.get(this.email).type(email)
  }

  preencherSenha(senha) {
    cy.get(this.senha).type(senha)
  }

  clicarLogin() {
    cy.get(this.btnAcessar).click()
  }

  validarTexto(texto) {
    cy.get(this.texto).contains(texto)
  }
  
  validarTextoCriado(texto) {
    cy.get(this.textCriado).contains(texto)
  }

  clicarRegistrar() {
    cy.get(this.btnRegistrar).click()
  }

  preencherEmailCadastro(email) {
    cy.get(this.emailCadastro).type(email,  { force: true })
  }

  preencherUsername(nome) {
    cy.get(this.nomeCadastro).type(nome,  { force: true })
  }

  preencherSenhaCadastro(senha) {
    cy.get(this.senhaCadastro).type(senha, { force: true })
  }

  preencherConfirmarSenhaCadastro(confirmarSenha) {
    cy.get(this.confirmarSenhaCadastro).type(confirmarSenha, { force: true })
  }

  clicarBtnContaComSaldoCadastro() {
    cy.get(this.btnContaComSaldoCadastro).click({ force: true })
  }

  clicarCadastrar() {
    cy.get(this.btnCadastrar).click({ force: true })
  }

  validarCampoObrigatorio(texto) {
    cy.get(':nth-child(5) > .style__ContainerFieldInput-sc-s3e9ea-0 > .input__warging').should('contain.text', 'campo obrigatorio');
  }

  clicarFechar() {
    cy.get(this.btnFechar).click({ force: true })
  }
}