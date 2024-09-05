import LoginData from "../../fixtures/LoginData.json"
import DadosTransfer from "../../fixtures/DadosTransfer.json"
import { faker } from '@faker-js/faker';

context('Tranferencia', () => {
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

  it('Ct-01 Realizar transferencia Invalida', () => {
    cy.realizarRegistroComSucesso(LoginData)
    cy.fazerLogin(LoginData)
    cy.transferenciaInvalida(DadosTransfer)
  })

  it('Ct-02 Realizar transferencia com campos em branco', () => {
    cy.realizarRegistroComSucesso(LoginData)
    cy.fazerLogin(LoginData)
    cy.fazerTransferenciaComDadosEmBranco(DadosTransfer)
  })

  it('Ct-03 Realizar transferencia com dados invalidos', () => {
    cy.realizarRegistroComSucesso(LoginData)
    cy.fazerLogin(LoginData)
    cy.transferenciaComDadosInvalidos(DadosTransfer)
  })

})