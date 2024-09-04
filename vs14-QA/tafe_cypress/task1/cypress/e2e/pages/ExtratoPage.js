export default class ExtratoPage {

  url = 'https://bugbank.netlify.app/'
  btnExtrato = '#btn-EXTRATO'

  //ExtratoPage
  textoSaldo = '.bank-statement__LabelText-sc-7n8vh8-10'
  textoValorSaldo = '#textBalanceAvailable'

  navegar() {
    cy.visit(this.url)
  }

  clicarExtrato() {
    cy.get(this.btnExtrato).click({ force: true })
  }

  validarTextoSaldo(texto) {
    cy.get(this.textoSaldo).contains(texto)
  }

  validarValorSaldo(texto) {
    cy.get(this.textoValorSaldo).contains(texto)
  }

}
