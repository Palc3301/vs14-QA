export default class RequesitosPage {
  
  url = 'https://bugbank.netlify.app/'
  btnRequisitos = '.styles__Link-sc-osobjw-0'

  //RequesitoPage
  btnLogin = '#accordionLogin'
  btnCadastrp = '#accordionCadastro'
  btnTransferencia = '#accordionTransferência'
  btnpagamento = '#accordionPagamento'
  btnExtrato = '#accordionExtrato'
  btnSaque = '#accordionSaque'
  btnVoltar = '#btnBack'

  btnLinkGitHub = '#linkGithub'


  navegar() {
    cy.visit(this.url)
  }

  clicarRequisitos() {
    cy.get(this.btnRequisitos).click({ force: true })
  }

  clicarLogin() {
    cy.get(this.btnLogin).click({ force: true })
  }

  clicarCadastro() {
    cy.get(this.btnCadastrp).click({ force: true })
  }

  clicarTransferencia() {
    cy.get(this.btnTransferencia).click({ force: true })
  }

  clicarPagamento() {
    cy.get(this.btnpagamento).click({ force: true })
  }

  clicarExtrato() {
    cy.get(this.btnExtrato).click({ force: true })
  }

  clicarSaque() {
    cy.get(this.btnSaque).click({ force: true })
  }

  clicarVoltar() {
    cy.get(this.btnVoltar).click({ force: true })
  }

  clicarLinkGitHub() {
    cy.get(this.btnLinkGitHub).click({ force: true })
  }
}