//e2e.js
import './commands'

Cypress.on('uncaught:exception', (err, runnable) => {
  return false
})