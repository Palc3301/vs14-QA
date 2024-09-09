import { Given, When, Then, And} from 'cypress-cucumber-preprocessor/steps';
import { registerSelectors } from '../pages/selectors/RegisterSelector';
import Dados from '../../fixtures/Dados.json';


//valid register
Given ('que o usuário está na página de login', () => {
    cy.visit('/')
})

And ('o usuario clica no botao register', () => {
    cy.clicar(registerSelectors.btnRegister)
})

When ('o usuario preencher todos os campos obrigatorios', () => {
    cy.generateFakerRegister()
    cy.registerValido(Dados)
})

Then ('o usuario deve ser redirecionado para a tela inicial', () => {
    cy.validarTexto(".title", "Welcome")
})


//try empty register

When ('o usuario deixa campos em branco', () => {
    cy.generateFakerRegister()
    cy.registerEmBranco(Dados)
})

Then ('o sistema nao deve permitir o registro e exibir mensagens de erro', () => {
    cy.validarTexto(registerSelectors.msgFirstNameEmBranco , "First name is required.")
    cy.validarTexto(registerSelectors.msgLastNameEmBranco, "Last name is required.")
    cy.validarTexto(registerSelectors.msgAdressEmBranco, "Address is required.")
})


// try register between two diferents passwords 

When ('o usuario preenche duas senhas diferentes uma da outra', () => {
    cy.generateFakerRegister()
    cy.registerPasswordDiferente(Dados)
})

And ('o usuario clica em "REGISTER"', () => {
    cy.clicar(registerSelectors.btnRegisterUser)
})

Then ('o sistema deve exibir uma mensagem de erro e nao permitir o acesso', () => {
    cy.validarTexto(registerSelectors.msgPasswordDiferente, "Passwords did not match.")
})