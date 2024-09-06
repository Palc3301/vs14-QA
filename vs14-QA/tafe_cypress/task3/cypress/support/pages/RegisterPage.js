//RegisterPage.js
let btnRegister = "#loginPanel > :nth-child(3) > a";

let firstName = "input[id=\"customer.firstName\"]";
let lastName = "input[id=\"customer.lastName\"]";
let address = "input[id=\"customer.address.street\"]";
let city = "input[id=\"customer.address.city\"]";
let state = "input[id=\"customer.address.state\"]";
let zipCode = "input[id=\"customer.address.zipCode\"]";
let phoneNumber = "input[id=\"customer.phoneNumber\"]";
let ssn = "input[id=\"customer.ssn\"]";
let username = "input[id=\"customer.username\"]";
let password = "input[id=\"customer.password\"]";
let confirmPassword = "input[id=\"repeatedPassword\"]";

let btnRegisterUser = "[colspan=\"2\"] > .button";

let msgFirstNameEmBranco = "#customer\\.firstName\\.errors";
let msgLastNameEmBranco = "[id=\"customer\.lastName\.errors\"]"
let msgAdressEmBranco = "[id=\"customer\.address\.street\.errors\"]"

let msgPasswordDiferente = "[id=\"repeatedPassword\.errors\"]"

Cypress.Commands.add('registerValido', (dados) => {
  cy.clicar(btnRegister)
  cy.preencherCampo(firstName, dados.firstName)
  cy.preencherCampo(lastName, dados.lastName)
  cy.preencherCampo(address, dados.address)
  cy.preencherCampo(city, dados.city)
  cy.preencherCampo(state, dados.state)
  cy.preencherCampo(zipCode, dados.zipCode)
  cy.preencherCampo(phoneNumber, dados.phoneNumber)
  cy.preencherCampo(ssn, dados.ssn)

  cy.preencherCampo(username, dados.username)
  cy.preencherCampo(password, dados.password)
  cy.preencherCampo(confirmPassword, dados.password)
  cy.clicar(btnRegisterUser)
  cy.validarTexto(".title", "Welcome")
})

Cypress.Commands.add('registerEmBranco', (dados) => {
  cy.clicar(btnRegister)
  cy.preencherCampo(city, dados.city)
  cy.preencherCampo(state, dados.state)
  cy.preencherCampo(zipCode, dados.zipCode)
  cy.preencherCampo(phoneNumber, dados.phoneNumber)
  cy.preencherCampo(ssn, dados.ssn)
  
  cy.clicar(btnRegisterUser)
  cy.validarTexto(msgFirstNameEmBranco , "First name is required.")
  cy.validarTexto(msgLastNameEmBranco, "Last name is required.")
  cy.validarTexto(msgAdressEmBranco, "Address is required.")
})

Cypress.Commands.add('registerPasswordDiferente', (dados) => {
  cy.clicar(btnRegister)
  cy.preencherCampo(firstName, dados.firstName)
  cy.preencherCampo(lastName, dados.lastName)
  cy.preencherCampo(address, dados.address)
  cy.preencherCampo(city, dados.city)
  cy.preencherCampo(state, dados.state)
  cy.preencherCampo(zipCode, dados.zipCode)
  cy.preencherCampo(phoneNumber, dados.phoneNumber)
  cy.preencherCampo(ssn, dados.ssn)

  cy.preencherCampo(username, dados.username)
  cy.preencherCampo(password, dados.password)
  cy.preencherCampo(confirmPassword, dados.confirmPassword)
  cy.clicar(btnRegisterUser)
  cy.validarTexto(msgPasswordDiferente, 'Passwords did not match.')
})





