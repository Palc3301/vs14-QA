import { faker } from '@faker-js/faker';
import Dados from '../../cypress/fixtures/Dados.json';

context('Login', () => {
  beforeEach(() => {
    const login = {
      username: faker.person.fullName(),
      email: faker.internet.email(),
      phoneNumber: faker.phone.number(),
      message: faker.lorem.sentence()
    };

    cy.writeFile('cypress/fixtures/Dados.json', login);
    cy.visit('/');
});
  it('Ct-01 Tentar Realizar Contato Valido', () => {
    cy.contatoBank(Dados)
  })

  it.only('Ct-02 Tentar Realizar Contato Vazio', () => {
    cy.contatoBankVazio(Dados)
  })

})