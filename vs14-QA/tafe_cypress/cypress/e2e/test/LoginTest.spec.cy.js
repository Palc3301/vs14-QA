import LoginPage from "../pages/loginPage"
import loginData from "../../fixtures/loginData.json"

const loginPage = new LoginPage

describe('Login', () => {
  it('validar login com dados invalidos', () => {
    loginPage.navegar()
    loginPage.preencherUsername("arruda")
    loginPage.preencherSenha("123456789")
    loginPage.clicarBtnLogin()
  })

  it('validar login com dados invalidos', () => {
    loginPage.navegar()
    loginPage.preencherUsername('mailton@gmail.com')
    loginPage.preencherSenha('123456789')
    loginPage.clicarBtnLogin()
  })

  it('validar login com dados invalidos', () => {
    cy.log(loginData)  
    loginPage.navegar(loginData.url)
    loginPage.preencherUsername(loginData.email)
    loginPage.preencherSenha(loginData.senha)
    loginPage.clicarBtnLogin()
  })

})