import { registerSelectors } from "./selectors/RegisterSelector";
import { faker } from '@faker-js/faker';

Cypress.Commands.add('registerValido', (dados) => {
    cy.preencherCampo(registerSelectors.firstName, dados.firstName)
    cy.preencherCampo(registerSelectors.lastName, dados.lastName)
    cy.preencherCampo(registerSelectors.address, dados.address)
    cy.preencherCampo(registerSelectors.city, dados.city)
    cy.preencherCampo(registerSelectors.state, dados.state)
    cy.preencherCampo(registerSelectors.zipCode, dados.zipCode)
    cy.preencherCampo(registerSelectors.phoneNumber, dados.phoneNumber)
    cy.preencherCampo(registerSelectors.ssn, dados.ssn)
  
    cy.preencherCampo(registerSelectors.username, dados.username)
    cy.preencherCampo(registerSelectors.password, dados.password)
    cy.preencherCampo(registerSelectors.confirmPassword, dados.password)

  })
  
  Cypress.Commands.add('registerEmBranco', (dados) => {
    cy.preencherCampo(registerSelectors.city, dados.city)
    cy.preencherCampo(registerSelectors.state, dados.state)
    cy.preencherCampo(registerSelectors.zipCode, dados.zipCode)
    cy.preencherCampo(registerSelectors.phoneNumber, dados.phoneNumber)
    cy.preencherCampo(registerSelectors.ssn, dados.ssn)
  })
  
  Cypress.Commands.add('registerPasswordDiferente', (dados) => {
    cy.preencherCampo(registerSelectors.firstName, dados.firstName)
    cy.preencherCampo(registerSelectors.lastName, dados.lastName)
    cy.preencherCampo(registerSelectors.address, dados.address)
    cy.preencherCampo(registerSelectors.city, dados.city)
    cy.preencherCampo(registerSelectors.state, dados.state)
    cy.preencherCampo(registerSelectors.zipCode, dados.zipCode)
    cy.preencherCampo(registerSelectors.phoneNumber, dados.phoneNumber)
    cy.preencherCampo(registerSelectors.ssn, dados.ssn)
  
    cy.preencherCampo(registerSelectors.username, dados.username)
    cy.preencherCampo(registerSelectors.password, dados.password)
    cy.preencherCampo(registerSelectors.confirmPassword, 12345)
  })

  Cypress.Commands.add('generateFakerRegister', (dados) => {
    const passFake = faker.internet.password()

    cy.writeFile('cypress/fixtures/Dados.json', {
      "username": faker.internet.userName(),
      "password": passFake,
      "confirmPassword": passFake,
      "firstName": faker.person.firstName(),
      "lastName": faker.person.lastName(),
      "address": faker.address.streetAddress(),
      "city": faker.address.city(),
      "state": faker.address.state(),
      "zipCode": faker.address.zipCode(),
      "phoneNumber": faker.phone.number(),
      "ssn": faker.string.numeric(9),
    })
})