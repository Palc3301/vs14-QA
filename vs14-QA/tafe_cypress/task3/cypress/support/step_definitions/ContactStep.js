import { Given, When, Then } from 'cypress-cucumber-preprocessor/steps';
import dadosContact from '../../fixtures/dadosContact.json';
import { contactSelector } from '../pages/selectors/ContactSelector';

// Cenário de contato válido
Given('que o usuário está na página de contato', () => {
    cy.visit('/');
    cy.generateFakeContact()
    cy.clicar(contactSelector.btnContact);
});

When('o usuário preenche todos os campos obrigatórios do formulário de contato', () => {
     cy.contatoBank(dadosContact);
});

Then('o sistema deve enviar o formulário de contato com sucesso', () => {
  // Aqui você pode adicionar uma validação que verifica se o formulário foi enviado corretamente.
     cy.validarTexto(contactSelector.msgSucess, 'A Customer Care Representative will be contacting you.');
});

// Cenário de contato com campos vazios
When('o usuário tenta enviar o formulário de contato sem preencher os campos', () => {
     cy.contatoBankVazio();
});

Then('o sistema deve exibir mensagens de erro informando que os campos são obrigatórios', () => {
    cy.url().should('eq', 'https://parabank.parasoft.com/parabank/contact.htm')
});