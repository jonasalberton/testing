/// <reference types="cypress"/>
import { doLogin, createAccount, dropDatabase, createContact } from "../utils";

describe("Contacts Test Suit", () => {

  beforeEach(() => {
    dropDatabase();
  })

  it('Should create a new contact', () => {
    createAccount();
    doLogin();

    const name = 'Contact One';
    const email = 'one@test.com';
    const phone = '+55 (48) 9999-8888';

    createContact(name, email, phone);

    cy.get('.qtd-contatos').should('have.text', '1 Contatos Encontrados');

    cy.get('.item > :nth-child(2)').should('have.text', name);
    cy.get('.item > :nth-child(3)').should('have.text', email);
    cy.get('.item > :nth-child(4)').should('have.text', phone);
  });

  it('Should edit an existing contact', () => {
    createAccount();
    doLogin();

    const name = 'Contact One';
    const email = 'one@test.com';
    const phone = '+55 (48) 9999-8888';

    createContact(name, email, phone);

    cy.get('.qtd-contatos').should('have.text', '1 Contatos Encontrados');

    cy.get('.item > :nth-child(2)').should('have.text', name);
    cy.get('.item > :nth-child(3)').should('have.text', email);
    cy.get('.item > :nth-child(4)').should('have.text', phone);


    // click on contact
    cy.get('.item').click();
    // click on 3 dot buttons top-right
    cy.get('.buttons > .mat-icon-button').click();
    // click edit contact
    cy.get('.mat-menu-content > :nth-child(1)').click();

    const newName = 'Contact One Edited';
    // change name + save
    cy.get('#mat-input-5').clear().type(newName);
    cy.get('.save-button').click();

    cy.get('.info > h3').should('have.text', newName);
    cy.get('.mat-simple-snackbar > :nth-child(1)').should('have.text', 'Lista atualizada com sucesso!');
  });

  it('Should delete an existing contact', () => {
    createAccount();
    doLogin();

    const name = 'Contact One';
    const email = 'one@test.com';
    const phone = '+55 (48) 9999-8888';

    createContact(name, email, phone);

    cy.get('.qtd-contatos').should('have.text', '1 Contatos Encontrados');

    cy.get('.item > :nth-child(2)').should('have.text', name);
    cy.get('.item > :nth-child(3)').should('have.text', email);
    cy.get('.item > :nth-child(4)').should('have.text', phone);


    // click on contact
    cy.get('.item').click();
    // click on 3 dot buttons top-right
    cy.get('.buttons > .mat-icon-button').click();
    // click delete contact
    cy.get('.mat-menu-content > :nth-child(2)').click();

    cy.get('.mat-simple-snackbar > :nth-child(1)').should('have.text', 'Contato Deletado');
    cy.get('.qtd-contatos').should('have.text', '0 Contatos Encontrados');
  });
});
