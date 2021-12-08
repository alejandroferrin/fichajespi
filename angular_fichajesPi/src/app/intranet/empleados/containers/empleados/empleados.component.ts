import { Component, OnInit } from '@angular/core';
import { EmpleadosService } from '../../service/empleados.service';
import { EmpleadoDto } from '../../model/empleadoDto'
import { Pagination } from 'src/app/shared/components/pagination/model/pagination.model';
import { TokenService } from 'src/app/core/auth/service/token.service';


@Component({
  selector: 'app-empleados',
  templateUrl: './empleados.component.html',
  styleUrls: ['./empleados.component.css']
})
export class EmpleadosComponent implements OnInit {

  headers =
    [
      "nombreEmpleado",
      "email",
      "numero",
      "dni",
      "diasVacaciones",
      "horasGeneradas",
      "enVacaciones",
      "deBaja",
      "working"
    ];

  //paginación
  order = 'id'
  asc = true

  listaElementos: Array<any> = []

  pag: Pagination = {
    totalPages: [],
    page: 0,
    isFirst: false,
    isLast: false,
    size: 15,
    sizeLimit: 100,
    listPagesLimits: 4,
  }

  dto: EmpleadoDto = {
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

  isAdmin: boolean = false;

  constructor(
    public service: EmpleadosService,
    private tokenService: TokenService
  ) { }

  ngOnInit(): void {
    this.isAdmin = this.tokenService.isAdmin();
    this.listarElementos()
  }


  listarElementos(): void {
    this.service.getElements(this.dto, this.pag.page, this.pag.size, this.order, this.asc).subscribe(
      data => {
        this.listaElementos = data.content
        this.pag.isFirst = data.first
        this.pag.isLast = data.last
        this.pag.totalPages = new Array(data.totalPages)
      },
      err => {
        console.log(err)
      }
    )
  }

  onPaginate(pag: Pagination) {
    this.pag = pag;
    this.listarElementos()
  }

  setOrder(order: string): void {
    this.order = order
    this.asc = !this.asc
    this.listarElementos()
  }
  clearNombre(): void {
    this.dto.nombreEmpleado = ''
    this.listarElementos()
  }
  clearEmail(): void {
    this.dto.email = ''
    this.listarElementos()
  }
  clearNumero(): void {
    this.dto.numero = ''
    this.listarElementos()
  }
  clearDiasVacacionesDesde(): void {
    this.dto.diasVacacionesDesde = null
    this.listarElementos()
  }
  clearDiasVacacionesHasta(): void {
    this.dto.diasVacacionesHasta = null
    this.listarElementos()
  }
  clearHorasDesde(): void {
    this.dto.horasGeneradasDesde = null
    this.listarElementos()
  }
  clearHorasHasta(): void {
    this.dto.horasGeneradasHasta = null
    this.listarElementos()
  }
  clear(): void {
    this.dto.email = ''
    this.dto.numero = ''
    this.dto.nombreEmpleado = ''
    this.dto.dni = ''
    this.dto.diasVacacionesDesde = null
    this.dto.diasVacacionesHasta = null
    this.dto.horasGeneradasDesde = null
    this.dto.horasGeneradasHasta = null
    this.dto.enVacaciones = null
    this.dto.deBaja = null
    this.dto.working = null
    this.listarElementos()
  }

}
