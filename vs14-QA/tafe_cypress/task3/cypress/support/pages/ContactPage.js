
let btnContact = '.contact > a' 
let labelName = '#name'
let labelEmail = '#email'
let labelPhone = '#phone'
let labelMessage = '#message'
let btnSend = '[colspan="2"] > .button'

let nameRequired = '#name\.errors'
let emailRequired = '#email\.errors'
let phoneRequired = '#phone\.errors'
let messageRequired = '#phone\.errors'

Cypress.Commands .add('contatoBank', (dados) => {
    cy.clicar(btnContact)
    cy.preencherCampo(labelName, dados.username)
    cy.preencherCampo(labelEmail, dados.email)
    cy.preencherCampo(labelPhone, dados.phoneNumber)
    cy.preencherCampo(labelMessage, dados.message)
    cy.clicar(btnSend)
})

Cypress.Commands.add('contatoBankVazio', () => {
    cy.clicar(btnContact)
    cy.clicar(btnSend)
})