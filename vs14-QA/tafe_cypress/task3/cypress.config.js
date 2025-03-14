const { defineConfig } = require("cypress");
const cucumber = require ('cypress-cucumber-preprocessor').default;

module.exports = defineConfig({
  e2e: {
    setupNodeEvents(on, config) {
      on('file:preprocessor', cucumber());
    },
    baseUrl:"https://parabank.parasoft.com/parabank",
    specPattern: 'cypress/features',
    stepDefinitions: 'cypress/support/step_definitions',
    video: true, 
    videoCompression: 32, 
  },
  watchForFileChanges: false
});