import LoginData from "../../fixtures/LoginData.json"
import { faker } from '@faker-js/faker';

context('Registrar', () => {

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
    cy.realizarRegistroComSucesso(LoginData);
  });

  it('Ct-02 Realizar registro com email inválido', () => {
    cy.validarRegistroComEmailInvalido(LoginData);
  });

  it('Ct-03 Realizar registro com campos em branco', () => {
    cy.validarRegistroComCamposEmBranco(LoginData);
  });

})