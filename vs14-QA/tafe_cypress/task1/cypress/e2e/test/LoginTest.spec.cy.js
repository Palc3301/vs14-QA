import LoginPage from "../pages/LoginPage"
import LoginData from "../../fixtures/LoginData.json"
import { faker } from '@faker-js/faker';

const loginPage = new LoginPage()
describe('Login', () => {

  it('Validar login com dados invalidos', () => {
    const emailInvalido = faker.internet.email();  
    const senhaInvalida = faker.internet.password(5);  

    loginPage.navegar();
    loginPage.preencherEmail(emailInvalido);
    loginPage.preencherSenha(senhaInvalida);
    loginPage.clicarLogin();
  })

  it('Validar login com dados validos, mas sem estar cadastrado', () => {
    loginPage.navegar()
    loginPage.preencherEmail(LoginData.email)
    loginPage.preencherSenha(LoginData.senha)
    loginPage.clicarLogin()
    loginPage.validarTextoCriado('Usuário ou senha inválido.')
  })

  it('Validar login com dados validos', () => {
    loginPage.navegar()
    loginPage.clicarRegistrar()
    loginPage.preencherEmailCadastro(LoginData.email)
    loginPage.preencherUsername('Pedro Arruda')
    loginPage.preencherSenhaCadastro(LoginData.senha)
    loginPage.preencherConfirmarSenhaCadastro(LoginData.senha)
    loginPage.clicarBtnContaComSaldoCadastro()
    loginPage.clicarCadastrar()
    loginPage.validarTextoCriado('criada com sucesso')
    loginPage.clicarFechar()
    loginPage.preencherEmail(LoginData.email)
    loginPage.preencherSenha(LoginData.senha)
    loginPage.clicarLogin()
    })
})