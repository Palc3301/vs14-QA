export const registerSelectors = {
    
    btnRegister:' #loginPanel > :nth-child(3) > a ',

    firstName: "input[id=\"customer.firstName\"]",
    lastName: "input[id=\"customer.lastName\"]",
    address: "input[id=\"customer.address.street\"]",
    city: "input[id=\"customer.address.city\"]",
    state: "input[id=\"customer.address.state\"]",
    zipCode: "input[id=\"customer.address.zipCode\"]",
    phoneNumber: "input[id=\"customer.phoneNumber\"]",
    ssn: "input[id=\"customer.ssn\"]",
    username: "input[id=\"customer.username\"]",
    password: "input[id=\"customer.password\"]",
    confirmPassword: "input[id=\"repeatedPassword\"]",
    
    btnRegisterUser: "[colspan=\"2\"] > .button",
    
    msgFirstNameEmBranco: "#customer\\.firstName\\.errors",
    msgLastNameEmBranco: "[id=\"customer\.lastName\.errors\"]",
    msgAdressEmBranco: "[id=\"customer\.address\.street\.errors\"]",
    
    msgPasswordDiferente: "[id=\"repeatedPassword\.errors\"]"
}