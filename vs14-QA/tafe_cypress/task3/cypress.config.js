const { defineConfig } = require("cypress");

module.exports = defineConfig({
  e2e: {
    setupNodeEvents(on, config) {},
    baseUrl: "https://parabank.parasoft.com/parabank",
  },
  watchForFileChanges: false
});
