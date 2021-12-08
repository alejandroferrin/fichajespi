
describe('The intranet/empleados Page', () => {

  beforeEach(() => {
    cy.login({ "password": "fichajesPi000", "numero": "fichajesPi000" });
  })

  it('should go to empleados/list and filter by empleado nombre', function () {
    cy.visit('/intranet/empleados/list')
    cy.get('input[id=nombre]').type('AdminFichajesPi{enter}')
    cy.get('tbody').children().should('have.length', 1);
  })

  it('should go to empleados/list and filter by empleado email', function () {
    cy.visit('/intranet/empleados/list')
    cy.get('input[id=email]').type('fichajespi@fichajespi.com{enter}')
    cy.get('tbody').children().should('have.length', 1);
  })

  it('should go to empleados/list and filter by empleado numero', function () {
    cy.visit('/intranet/empleados/list')
    cy.get('input[id=numero]').type('fichajesPi000{enter}')
    cy.get('tbody').children().should('have.length', 1);
  })

})