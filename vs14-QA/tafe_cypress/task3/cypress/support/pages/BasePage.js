Cypress.Commands.add('validarTexto', (selector, dados) => {
  cy.get(selector, { timeout: 10000 }) 
    .should('be.visible') 
    .should('contain.text', dados);
});
Cypress.Commands.add('clicar', (selector) => 
  cy.get(selector).click({force: true}) );

Cypress.Commands.add('preencherCampo', (selector, dados) => 
  cy.get(selector).invoke("show").type(dados, {force:true}) );
