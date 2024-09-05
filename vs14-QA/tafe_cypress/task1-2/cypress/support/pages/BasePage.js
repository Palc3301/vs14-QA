Cypress.Commands.add('validarTexto', (selector, dados) => cy.get(selector).contains(dados) );

Cypress.Commands.add('clicar', (selector) => 
  cy.get(selector).click({force: true}) );

Cypress.Commands.add('preencherCampo', (selector, dados) => 
  cy.get(selector).invoke("show").type(dados, {force:true}) );
