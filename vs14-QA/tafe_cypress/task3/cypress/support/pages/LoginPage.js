//LoginPage.js

let campoUsername = ':nth-child(2) > .input';
let campoPassword = ':nth-child(4) > .input';

let btnLogin = ':nth-child(5) > .button'
let error = '.error'

Cypress.Commands.add('loginInvalido', (dados) => {
    cy.preencherCampo(campoUsername, dados.username)
    cy.preencherCampo(campoPassword, dados.password)
    cy.clicar(btnLogin)
})

Cypress.Commands.add('loginSemDados', () => {
    cy.clicar(btnLogin)
    cy.validarTexto(error, 'Please enter a username and password.')
})