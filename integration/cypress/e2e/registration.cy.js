/// <reference types="cypress"/>

/// <reference types="cypress"/>
import {dropDatabase } from "../utils";
import {APP_URL, PASSWORD, USER} from '../consts';

describe("Registration Test Suit", () => {

  beforeEach(() => {
    dropDatabase();
  })

  it("Should not allow user creation with miss-match password", () => {
    cy.visit(APP_URL);
    cy.get(".cadastro").click();

    cy.get("#mat-input-2").type("Usuario Teste");
    cy.get("#mat-input-3").type(USER);
    cy.get("#mat-input-4").type(PASSWORD);
    cy.get("#mat-input-5").type(`${PASSWORD}+123`).blur();

    cy.get("#mat-error-0").should(
      "have.text",
      "As senhas devem ser compativeis"
    );

    cy.get(".main-button").should("be.disabled");
  });

  it("Should create new user", () => {
    cy.visit(APP_URL);
    cy.get(".cadastro").click();

    cy.get("#mat-input-2").type("Usuario Teste");
    cy.get("#mat-input-3").type(USER);
    cy.get("#mat-input-4").type(PASSWORD);
    cy.get("#mat-input-5").type(PASSWORD);

    // create new user
    cy.get(".main-button").click();

    cy.get('.nome').should('have.text', 'Olá, Usuario');
  });
});


