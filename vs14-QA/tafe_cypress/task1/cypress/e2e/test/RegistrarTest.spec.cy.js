import RegistarPage from "../pages/RegistrarPage"
import LoginData from "../../fixtures/LoginData.json"
import { faker } from '@faker-js/faker';


const registrarPage = new RegistarPage()
describe('Login', () => {

  it('Validar login com dados invalidos', () => {
    const emailInvalido = faker.internet.email();  
    const senhaInvalida = faker.internet.password(5);  

    registrarPage.navegar();
    registrarPage.clicarRegistrar()
    registrarPage.preencherEmail(emailInvalido);
    registrarPage.preencherSenha(senhaInvalida);

    registrarPage.clicarCadastrar()
  })

  it('Registrar com email invalido', () => {
    registrarPage.navegar()
    registrarPage.clicarRegistrar()
    registrarPage.preencherEmail('arruda')
    registrarPage.preencherUsername('pedro')
    registrarPage.preencherSenha('123')
    registrarPage.preencherConfirmarSenha('123')
    registrarPage.clicarBtnContaComSaldo()
    registrarPage.clicarCadastrar()
  })

  it('Registrar com dados invalidos', () => {
    registrarPage.navegar()
    registrarPage.clicarRegistrar()
    registrarPage.preencherEmail(LoginData.email)
    registrarPage.preencherUsername('Pedro Arruda')
    registrarPage.preencherSenha(LoginData.senha)
    registrarPage.preencherConfirmarSenha(LoginData.senha)
    registrarPage.clicarBtnContaComSaldo()
    registrarPage.clicarCadastrar()
    registrarPage.validarTexto('criada com sucesso')
  })
})