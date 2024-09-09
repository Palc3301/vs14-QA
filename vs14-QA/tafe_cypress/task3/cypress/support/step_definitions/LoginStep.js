import { Given, When, Then } from 'cypress-cucumber-preprocessor/steps';
import { loginSelectors } from '../pages/selectors/LoginSelector';
import Dados from '../../fixtures/Dados.json';

// Cenário de login inválido
Given('que o usuário está na página de login', () => {
  cy.visit('/');
});

When('o usuário tenta realizar login com dados inválidos', () => {
  cy.loginInvalido(Dados);
});

Then('o sistema deve exibir uma mensagem de erro', () => {
  cy.validarTexto(loginSelectors.error, 'An internal error has occurred and has been logged.');
});

// Cenário de login sem dados
When('o usuário tenta realizar login sem preencher os campos', () => {
  cy.loginSemDados();
});

Then('o sistema deve exibir uma mensagem de erro informando que os campos são obrigatórios', () => {
  cy.validarTexto(loginSelectors.error, 'Please enter a username and password.');
});