
import { APP_URL, USER, PASSWORD } from './consts';

export function doLogin(user = USER, password = PASSWORD) {
    cy.visit(APP_URL);
    cy.get('#mat-input-0').type(user);
    cy.get('#mat-input-1').type(password);
    cy.get('.main-button').click();
}

export function createContact(name, email, phone) {
    cy.get('.button-add').click();
    cy.get('#mat-input-2').type(name);
    cy.get('#mat-input-3').type(email);
    cy.get('#mat-input-4').type(phone);

    cy.get('.save-button').click();
}

export function createAccount() {
    cy.visit(APP_URL);
    cy.get(".cadastro").click();

    cy.get("#mat-input-2").type("Usuario Teste");
    cy.get("#mat-input-3").type(USER);
    cy.get("#mat-input-4").type(PASSWORD);
    cy.get("#mat-input-5").type(PASSWORD);

    // create new user
    cy.get(".main-button").click();

}

/*
Essa função será executa em todo BeforeEach para deletar o indexedDB
*/
export function dropDatabase() {
    window.indexedDB.deleteDatabase("APP");
}