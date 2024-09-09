import { faker } from '@faker-js/faker';
import {contactSelector} from '../pages/selectors/ContactSelector';

Cypress.Commands.add('contatoBank', (dados) => {
    cy.preencherCampo(contactSelector.labelName, dados.username)
    cy.preencherCampo(contactSelector.labelEmail, dados.email)
    cy.preencherCampo(contactSelector.labelPhone, dados.phoneNumber)
    cy.preencherCampo(contactSelector.labelMessage, dados.message)
    cy.clicar(contactSelector.btnSend)
})

Cypress.Commands.add('contatoBankVazio', () => {
    cy.clicar(contactSelector.btnContact)
    cy.clicar(contactSelector.btnSend)
})

Cypress.Commands.add('generateFakeContact', () => {
    cy.writeFile('cypress/fixtures/dadosContact.json', {
      "username": faker.internet.userName(),
      "email":faker.internet.email(),
      "phoneNumber": faker.phone.number(),
      "message": faker.lorem.sentence()
    })
})