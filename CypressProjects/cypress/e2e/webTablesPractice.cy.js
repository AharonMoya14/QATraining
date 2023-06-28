/*
 All Rights Reserved
 This software is property information of AKUREY S.A.
 Contact us at contact@akurey.com
 @summary The code checks an user has been added correctly, and if some input is missing, the div has to change border color
*/
import { faker } from '@faker-js/faker';

describe('Web Tables Practice', () => {
  const randomFirstName = faker.name.firstName();
  const randomLastName = faker.name.lastName();
  const randomEmail = faker.internet.email();
  const randomAge = Math.floor(Math.random() * (100 - 18)) + 18;
  const randomSalary = Math.floor(Math.random() * (300000 - 1000 + 1)) + 1000;
  const randomDepartment = faker.commerce.department();
  beforeEach(() => {
    cy.on('uncaught:exception', (err) => {
      return false;
    });
    cy.visit('https://demoqa.com');
    cy.get('.card.mt-4.top-card').contains('Elements').click();
    cy.get('.element-list').contains('Web Tables').click();
    cy.get('#addNewRecordButton').click();
    cy.get('#firstName').type(randomFirstName);
    cy.get('#lastName').type(randomLastName);
    cy.get('#age').type(randomAge);
    cy.get('#salary').type(randomSalary);
    cy.get('#department').type(randomDepartment);
    
  });
  it('First test case', () => {
    cy.get('#userEmail').type(randomEmail);
    cy.get('#submit').click();
    cy.get('.rt-tbody .rt-tr').contains(randomEmail).parents('.rt-tr').within((element) => {
      cy.wrap(element).contains(randomFirstName).should('be.visible');
      cy.wrap(element).contains(randomLastName).should('be.visible');
      cy.wrap(element).contains(randomEmail).should('be.visible');
      cy.wrap(element).contains(randomAge).should('be.visible');
      cy.wrap(element).contains(randomSalary).should('be.visible');
      cy.wrap(element).contains(randomDepartment).should('be.visible');
    });
  });
  it('Second test case', () => {
    cy.get('#submit').click();
    cy.get('#userEmail').should('have.css', 'border-color', 'rgb(220, 53, 69)');

  });
});
