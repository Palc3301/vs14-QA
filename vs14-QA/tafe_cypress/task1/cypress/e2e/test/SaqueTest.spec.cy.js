import LoginPage from "../pages/LoginPage"
import SaquePage from "../pages/SaquePage"
import LoginData from "../../fixtures/LoginData.json"

const loginPage = new LoginPage()
const saquePage = new SaquePage()

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
    saquePage.clicarSaque()
    saquePage.validarTexto('Funcionalidade em desenvolvimento')
  })

})