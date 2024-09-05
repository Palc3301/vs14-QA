  let btnTransferencia = '#btn-TRANSFERÊNCIA'

  //TransferenciaPage
  let inputNumeroDaConta = ':nth-child(1) > .input__default'
  let inputDigito = '.account__data > :nth-child(2) > .input__default'
  let inputValor = '.styles__ContainerFormTransfer-sc-1oow0wh-0 > :nth-child(2) > .input__default'
  let inputDescricao = ':nth-child(3) > .input__default'
  let btnTrasferirAgora = '.style__ContainerButton-sc-1wsixal-0' 

  let textoErro = '#modalText'

  Cypress.Commands.add('transferenciaInvalida', (dados) => {
    cy.clicar(btnTransferencia)
    cy.preencherCampo(inputNumeroDaConta, dados.numeroDaConta)
    cy.preencherCampo(inputDigito, dados.digito)
    cy.preencherCampo(inputValor, dados.valor)
    cy.preencherCampo(inputDescricao, dados.descricao)
    cy.clicar(btnTrasferirAgora)
  })

  Cypress.Commands.add('fazerTransferenciaComDadosEmBranco', (dados) => {
    cy.clicar(btnTransferencia)
    cy.clicar(btnTrasferirAgora)
  })

  Cypress.Commands.add('transferenciaComDadosInvalidos', (dados) => {
    cy.clicar(btnTransferencia)
    cy.preencherCampo(inputNumeroDaConta, dados.numeroDaConta)
    cy.preencherCampo(inputDigito, dados.digito)
    cy.preencherCampo(inputValor, dados.valor)
    cy.preencherCampo(inputDescricao, dados.descricao)
    cy.clicar(btnTrasferirAgora)
  })