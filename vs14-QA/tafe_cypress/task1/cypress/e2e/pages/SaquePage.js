export default class SaquePage {

  url = 'https://bugbank.netlify.app/'
  btnSaque = '#btn-SAQUE'
  texto = '#modalText'

  navegar() {
    cy.visit(this.url)
  }

  clicarSaque() {
    cy.get(this.btnSaque).click({ force: true })
  }

  validarTexto(texto) {
    cy.get(this.texto).contains(texto)
  }

}
