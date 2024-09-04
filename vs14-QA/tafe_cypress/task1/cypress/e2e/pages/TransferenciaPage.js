export default class TransferenciaPage {

  url = 'https://bugbank.netlify.app/'
  btnTransferencia = '#btn-TRANSFERÊNCIA'

  //TransferenciaPage
  inputNumeroDaConta = ':nth-child(1) > .input__default'
  inputDigito = '.account__data > :nth-child(2) > .input__default'
  inputValor = '.styles__ContainerFormTransfer-sc-1oow0wh-0 > :nth-child(2) > .input__default'
  inputDescricao = ':nth-child(3) > .input__default'
  btnTrasferirAgora = '.style__ContainerButton-sc-1wsixal-0' 

  textoErro = '#modalText'

  navegar() {
    cy.visit(this.url)
  }

  clicarTransferencia() {
    cy.get(this.btnTransferencia).click({ force: true })
  }

  preencherNumeroDaConta(numero) {
    cy.get(this.inputNumeroDaConta).type(numero)
  }

  preencherDigito(digito) {
    cy.get(this.inputDigito).type(digito)
  }

  preencherValor(valor) {
    cy.get(this.inputValor).type(valor)
  }

  preencherDescricao(descricao) {
    cy.get(this.inputDescricao).type(descricao)
  }

  clicarTrasferirAgora() {
    cy.get(this.btnTrasferirAgora).click()
  }

  validarTextoErro(texto) {
    cy.get(this.textoErro).contains(texto)
  }

}
