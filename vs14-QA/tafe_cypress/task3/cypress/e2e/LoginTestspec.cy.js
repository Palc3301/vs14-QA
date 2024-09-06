import { faker } from '@faker-js/faker';
import Dados from '../../cypress/fixtures/Dados.json';

context('Login', () => {
  beforeEach(() => {
    const login = {
      username: faker.person.fullName(),
      password: faker.string.alphanumeric(8)
    };

    cy.writeFile('cypress/fixtures/Dados.json', login);
    cy.visit('/');
});
  
  it('Ct-01 Tentar Realizar Login Invalido', () => {
    cy.loginInvalido(Dados)
  })

  it('Ct-02 Tentar Realizar Login Sem Dados', () => {
    cy.loginSemDados()
  })

})