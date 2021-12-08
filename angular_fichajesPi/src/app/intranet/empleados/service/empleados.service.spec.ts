import { TestBed } from '@angular/core/testing';

import { EmpleadosService } from './empleados.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { EmpleadoDto } from '../model/empleadoDto';
import { Pagination } from 'src/app/shared/components/pagination/model/pagination.model';
import { EMPLEADOS, EMPLEADO_DETALLE } from 'src/mock/mock.empleados';
import { Empleado } from '../model/empleado';



describe('EmpleadosService', () => {

  let service: EmpleadosService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ],
      providers: [
        EmpleadosService
      ]
    });

    service = TestBed.inject(EmpleadosService),
      httpTestingController = TestBed.inject(HttpTestingController);

  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should retrieve filtered users', () => {

    let dto: EmpleadoDto = {
      email: '',
      numero: '',
      nombreEmpleado: '',
      dni: '',
      diasVacacionesDesde: null,
      diasVacacionesHasta: null,
      horasGeneradasDesde: null,
      horasGeneradasHasta: null,
      enVacaciones: null,
      deBaja: null,
      working: null
    }
    let pag: Pagination = {
      totalPages: [],
      page: 0,
      isFirst: false,
      isLast: false,
      size: 15,
      sizeLimit: 100,
      listPagesLimits: 4,
    }
    let order = 'id'
    let asc = true

    service.getElements(dto, pag.page, pag.size, order, asc)
      .subscribe(elements => {

        expect(elements).toBeTruthy('No users returned');

        expect(elements.totalElements).toBe(5,
          "incorrect number of users");

        const element = elements.content.find((user: { id: number; }) => user.id == 2);

        expect(element.email).toBe(
          "bbb@bbb.es");

      });

    const req = httpTestingController.expectOne(`${service.endPoint}/pagesFiltered?page=${pag.page}&size=${pag.size}&order=${order}&asc=${asc}`);

    expect(req.request.method).toEqual("POST");

    req.flush(EMPLEADOS);

  });

  it('should get the details of one user', () => {

    let id: number = 1

    service.detail(id)
      .subscribe(elementSelected => {

        expect(elementSelected).toBeTruthy('No user returned');

        expect(elementSelected.email).toBe("aaa@aaa.es");

      });

    const req = httpTestingController.expectOne(`${service.endPoint}/${id}`);

    expect(req.request.method).toEqual("GET");

    req.flush(EMPLEADO_DETALLE);

  })

  it('should update the data of one user', () => {

    const changes: Partial<Empleado> = { nombreEmpleado: 'bbb' };

    let empleado = new Empleado(
      EMPLEADO_DETALLE.email,
      EMPLEADO_DETALLE.numero,
      'bbb',
      EMPLEADO_DETALLE.dni,
      EMPLEADO_DETALLE.diasVacaciones,
      EMPLEADO_DETALLE.horasGeneradas,
      EMPLEADO_DETALLE.working,
      EMPLEADO_DETALLE.enVacaciones,
      EMPLEADO_DETALLE.deBaja,
      EMPLEADO_DETALLE.ultimoFichaje
    );

    let id: number = 1

    service.update(id, empleado)
      .subscribe(empleado => {
        expect(empleado.id).toBe(1);

      });

    const req = httpTestingController.expectOne(`${service.endPoint}/${id}`);

    expect(req.request.method).toEqual("PUT");
    expect(req.request.body.nombreEmpleado).toEqual(changes.nombreEmpleado);

    req.flush({
      ...EMPLEADO_DETALLE,
      ...changes
    })


  })

});
