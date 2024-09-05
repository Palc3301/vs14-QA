//LoginTest.spec.cy.js
import LoginData from "../../fixtures/LoginData.json";
import { faker } from '@faker-js/faker';

context('Login', () => {

  beforeEach(() => {
    const login = {
      nome: faker.person.fullName(),
      email: faker.internet.email(),
      password: faker.string.alphanumeric(8),
      emailInvalido: faker.word.words(1),
    };

    cy.writeFile('cypress/fixtures/LoginData.json', login);
    cy.visit("/");
});
  
  it('Ct-01 Realizar registro com saldo com sucesso', () => {
    cy.realizarRegistroComSucesso(LoginData)
    cy.fazerLogin(LoginData)
  })

  it('Ct-02 Realizar registro com campos em branco', () => {
    cy.fazerLoginComDadosEmBranco(LoginData)
  })

  it('Ct-03 Realizar login com dados inválidos', () => {
    cy.loginDadoInvalido(LoginData)
  })

})