/// <reference types="cypress"/>
import { doLogin, createAccount, dropDatabase } from "../utils";

describe("Login Test Suit", () => {

  beforeEach(() => {
    dropDatabase();
  })

  it('Should do login', () => {
    createAccount();
    doLogin();

    cy.get('.qtd-contatos').should('have.text', '0 Contatos Encontrados');
  });
 
  it("Should Logout", () => {
    createAccount();
    doLogin();

    cy.get('.button-exit').click();
    cy.get('.mat-menu-item').click();

    cy.get('.login-container').should('be.visible');
  });

  it("Should not login with wrong credentials", () => {
    createAccount();
    doLogin('wrong@test.com', 'wrong-PW');

    cy.get('.mat-simple-snackbar > :nth-child(1)').should('have.text', 'Usuário desconhecido');
  });

});
