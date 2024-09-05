
  let btnPagamento = '#btn-PAGAMENTOS'
  let texto = '#modalText'


  Cypress.Commands.add('acessarPagamento', () => {
    cy.clicar(btnPagamento)
    cy.get(texto).should('contain', 'Funcionalidade em desenvolvimento')
  })

  Cypress.Commands.add('validarPagamento', (dados) => {
    cy.get(texto).should('contain', dados.texto)
  })