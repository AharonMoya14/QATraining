/*
 All Rights Reserved
 This software is property information of AKUREY S.A.
 Contact us at contact@akurey.com
 @summary The code checks if Small and Large modals appears when user press the correct button
*/
describe('Modal Dialogs Practice', () => {
  beforeEach(() => {
    cy.on('uncaught:exception', (err) => {
      return false;
    });
    cy.visit('https://demoqa.com');
    cy.get('.card.mt-4.top-card').contains('Alerts, Frame & Windows').click();
    cy.get('.element-list').contains('Modal Dialogs').click();
  });

  it('First test case', () => {
    cy.get('#showSmallModal').click();
    cy.get('.modal-content').should('be.visible');
    cy.get('#closeSmallModal').click();
  });

  it('Second test case', () => {
    cy.get('#showLargeModal').click();
    cy.get('.modal-content').should('be.visible');
    cy.get('#closeLargeModal').click();
  });
});
