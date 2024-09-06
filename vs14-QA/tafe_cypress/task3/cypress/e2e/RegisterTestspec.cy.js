//RegisterTestspec.cy.js
import { faker } from '@faker-js/faker';
import Dados from '../../cypress/fixtures/Dados.json';

context('Login', () => {
  beforeEach(() => {
    const login = {
      username: faker.person.fullName(),
      password: faker.string.alphanumeric(8),
      confirmPassword: faker.animal.horse(),
      firstName: faker.person.firstName(),
      lastName: faker.person.lastName(),
      address: faker.address.streetAddress(),
      city: faker.address.city(),
      state: faker.address.state(),
      zipCode: faker.address.zipCode(),
      phoneNumber: faker.phone.number(),
      ssn: faker.string.numeric(9),
    };

    cy.writeFile('cypress/fixtures/Dados.json', login);
    cy.visit('/');
});
  
  it('Ct-01 Tentar Registrar Com Sucesso', () => {
    cy.registerValido(Dados)
  })

  it('Ct-02 Tentar Registrar Em Branco', () => {
    cy.registerEmBranco(Dados)
  })

  it('Ct-03 Tentar Registrar Com Senha Diferente', () => {
    cy.registerPasswordDiferente(Dados)
  })

})