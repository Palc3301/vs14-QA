export default class PagamentosPage {

  url = 'https://bugbank.netlify.app/'
  btnPagamento = '#btn-PAGAMENTOS'
  texto = '#modalText'

  navegar() {
    cy.visit(this.url)
  }

  clicarPagamento() {
    cy.get(this.btnPagamento).click({ force: true })
  }

  validarTexto(texto) {
    cy.get(this.texto).contains(texto)
  }

}
