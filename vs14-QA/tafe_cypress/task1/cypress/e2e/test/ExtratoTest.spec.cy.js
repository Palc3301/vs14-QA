import LoginPage from "../pages/LoginPage"
import ExtratoPage from "../pages/ExtratoPage"
import LoginData from "../../fixtures/LoginData.json"

const loginPage = new LoginPage()
const extratoPage = new ExtratoPage()

describe('Pagamentos', () => {

  it('Validar Pagina Pagamento', () => {
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
    extratoPage.clicarExtrato()
    extratoPage.validarTextoSaldo('Saldo disponível')
    extratoPage.validarValorSaldo('R$')
  })

})