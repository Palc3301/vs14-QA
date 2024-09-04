import TransferenciaPage from "../pages/TransferenciaPage";
import LoginData from "../../fixtures/LoginData.json"
import { faker } from '@faker-js/faker';
import LoginPage from "../pages/LoginPage"

const loginPage = new LoginPage()
const transferenciaPage = new TransferenciaPage()

describe('Tranferencia', () => {
  it('Validar Tranferencia invalida', () => {  
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
    transferenciaPage.clicarTransferencia()
    transferenciaPage.preencherNumeroDaConta('123')
    transferenciaPage.preencherDigito('1')
    transferenciaPage.preencherValor('100')
    transferenciaPage.preencherDescricao('Teste')
    transferenciaPage.clicarTrasferirAgora()
    transferenciaPage.validarTextoErro('Conta inválida ou inexistente')
  })

  it('Validar Tranferencia com campos em branco', () => {
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
    transferenciaPage.clicarTransferencia()
    transferenciaPage.clicarTrasferirAgora()
  })

})