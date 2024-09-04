import RequesitosPage from "../pages/RequesitosPage"
import LoginData from "../../fixtures/LoginData.json"

const requesitosPage = new RequesitosPage()

describe('Requisitos', () => {  
    it('Navegar até requisitos', () => {
      requesitosPage.navegar()
      requesitosPage.clicarRequisitos()
    })

    it('RequesitosButtons', () => {
      requesitosPage.navegar()
      requesitosPage.clicarLogin()
      requesitosPage.clicarCadastro()
      requesitosPage.clicarTransferencia()
      requesitosPage.clicarPagamento()
      requesitosPage.clicarExtrato()
      requesitosPage.clicarSaque()
      requesitosPage.clicarVoltar()
    })

    it('RequesitosLinkGitHub', () => {
      requesitosPage.navegar()
      requesitosPage.clicarLinkGitHub()
    })
    
  }
)