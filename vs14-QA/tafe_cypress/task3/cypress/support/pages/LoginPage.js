import {loginSelectors} from "./selectors/LoginSelector";

Cypress.Commands.add('loginInvalido', (dados) => {
    cy.preencherCampo(loginSelectors.campoUsername, dados.username)
    cy.preencherCampo(loginSelectors.campoPassword, dados.password)
    cy.clicar(loginSelectors.btnLogin)
})

Cypress.Commands.add('loginSemDados', () => {
    cy.clicar(loginSelectors.btnLogin)
    cy.validarTexto(loginSelectors.error, 'Please enter a username and password.')
})