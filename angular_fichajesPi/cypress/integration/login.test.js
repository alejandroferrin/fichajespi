
describe('The Login Page', () => {

  it('should login', function () {

    const password = 'fichajesPi000';
    const numero = 'fichajesPi000';

    cy.visit('/login')

    cy.get('input[name=numero]').type(numero)

    // {enter} causes the form to submit
    cy.get('input[name=password]').type(`${password}{enter}`)

    cy.url().should('include', '/intranet/home')

    //cy.getCookie('').should('exist')

    cy.getLocalStorage('AuthToken').should('exist')

    //cy.get('h1').should('contain', 'jane.lane')
  })

  it('should not login', function () {

    const password = 'no';
    const numero = 'no';

    cy.visit('/login')

    cy.get('input[name=numero]').type(numero)

    // {enter} causes the form to submit
    cy.get('input[name=password]').type(`${password}{enter}`)

    cy.get('.alert').should('contain', 'Unauthorized')
  })

})



///ejemplo de curso cypress


  /* describe('Home Page', () => {
  
      beforeEach(() => {
  
          cy.fixture('courses.json').as("coursesJSON");
  
          cy.server();
  
          cy.route('/api/courses', "@coursesJSON").as("courses");
  
          cy.visit('/');
  
      });
  
      it('should display a list of courses', () => {
  
          cy.contains("All Courses");
  
          cy.wait('@courses');
  
          cy.get("mat-card").should("have.length", 9);
  
      });
  
      it('should display the advanced courses', () => {
  
          cy.get('.mat-tab-label').should("have.length", 2);
  
          cy.get('.mat-tab-label').last().click();
  
          cy.get('.mat-tab-body-active .mat-card-title').its('length').should('be.gt', 1);
  
          cy.get('.mat-tab-body-active .mat-card-title').first()
              .should('contain', "Angular Security Course");
  
      });
  
  
  }); */
