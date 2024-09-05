import LoginData from "../../fixtures/LoginData.json"
import { faker } from '@faker-js/faker';

context('Pagamentos', () => {
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
  it('Ct-01 Validar Pagina Pagamento', () => {

    cy.realizarRegistroComSucesso(LoginData)
    cy.fazerLogin(LoginData)
    cy.acessarPagamento()
  })

  //Outra forma
  it('Ct-02 Validar Mensagem de Pagamento', () => {
    cy.realizarRegistroComSucesso(LoginData)
    cy.fazerLogin(LoginData)
    cy.acessarPagamento()
    cy.validarPagamento({ texto: 'Funcionalidade em desenvolvimento' })
  })

})