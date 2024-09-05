const { defineConfig } = require("cypress");

module.exports = defineConfig({
  e2e: {
    setupNodeEvents(on, config) {},
    baseUrl: "https://bugbank.netlify.app/",
  },
  "chromeWebSecurity": false,
  watchForFileChanges: false
});
