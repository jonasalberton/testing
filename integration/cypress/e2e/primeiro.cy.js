/// <reference types="cypress"/>

describe('template spec', () => {
  it('Should create a new person', () => {
    cy.visit('https://wcaquino.me/cypress/componentes.html');
    cy.get('#formNome').type('Jonas');
    cy.get('[data-cy="dataSobrenome"]').type('Alberton');
    cy.get('#formSexoMasc').click();
    cy.get('#formComidaCarne').click();
    cy.get('[value="futebol"]').click();
    cy.get('#formCadastrar').click();


    cy.get('#resultado > :nth-child(1)').should('have.text', 'Cadastrado!')
    cy.get('#descNome > span').should('have.text', 'Jonas')
    cy.get('#descSobrenome > span').should('have.text', 'Alberton')
    cy.get('#descSexo > span').should('have.text', 'Masculino')
    cy.get('#descEscolaridade > span').should('have.text', '1grauincomp')

  })
})