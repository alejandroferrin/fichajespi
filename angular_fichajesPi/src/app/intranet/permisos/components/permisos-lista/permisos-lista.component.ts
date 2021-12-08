import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TokenService } from 'src/app/core/auth/service/token.service';
import { Pagination } from 'src/app/shared/components/pagination/model/pagination.model';
import { PermisoDto } from '../../model/permisoDto';
import { PermisoService } from '../../service/permiso.service';

@Component({
  selector: 'app-permisos-lista',
  templateUrl: './permisos-lista.component.html',
  styleUrls: ['./permisos-lista.component.css']
})
export class PermisosListaComponent implements OnInit {


  headers = [
    "dia",
    "horaInicio",
    "horaFin",
    "estado",
    "descripcion",
    "usuario.nombreEmpleado",
    "usuario.numero"
  ]

  //paginación
  order = 'id'
  asc = false

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

  dto: PermisoDto = {
    descripcion: '',
    diaDesde: '',
    diaHasta: '',
    horaFinDesde: '',
    horaFinHasta: '',
    horaInicioDesde: '',
    horaInicioHasta: '',
    estado: '',

    usuarioEmail: '',
    usuarioNumero: '',
    usuarioNombre: '',
    usuarioDni: '',
  }

  isAdmin: boolean = false;

  constructor(
    public service: PermisoService,
    private activatedRoute: ActivatedRoute,
    private tokenService: TokenService
  ) { }

  ngOnInit(): void {
    this.dto.usuarioNumero = this.activatedRoute.snapshot.params.numero;
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

  clearEmail(): void {
    this.dto.usuarioEmail = ''
    this.listarElementos()
  }
  clearNumero(): void {
    this.dto.usuarioNumero = ''
    this.listarElementos()
  }
  clearNombre(): void {
    this.dto.usuarioNombre = ''
    this.listarElementos()
  }
  clearDni(): void {
    this.dto.usuarioDni = ''
    this.listarElementos()
  }


  clearDescripcion(): void {
    this.dto.descripcion = ''
    this.listarElementos()
  }
  clearDiaDesde(): void {
    this.dto.diaDesde = ''
    this.listarElementos()
  }
  clearDiaHasta(): void {
    this.dto.diaHasta = ''
    this.listarElementos()
  }
  clearHoraFinDesde(): void {
    this.dto.horaFinDesde = ''
    this.listarElementos()
  }
  clearHoraFinHasta(): void {
    this.dto.horaFinHasta = ''
    this.listarElementos()
  }
  clearHoraInicioDesde(): void {
    this.dto.horaInicioDesde = ''
    this.listarElementos()
  }
  clearHoraInicioHasta(): void {
    this.dto.horaInicioHasta = ''
    this.listarElementos()
  }
  clearEstado(): void {
    this.dto.estado = ''
    this.listarElementos()
  }

  clear(): void {
    this.dto.descripcion = ''
    this.dto.diaDesde = ''
    this.dto.diaHasta = ''
    this.dto.horaFinDesde = ''
    this.dto.horaFinHasta = ''
    this.dto.horaInicioDesde = ''
    this.dto.horaInicioHasta = ''
    this.dto.estado = ''

    this.dto.usuarioEmail = ''
    this.dto.usuarioNumero = ''
    this.dto.usuarioNombre = ''
    this.dto.usuarioDni = ''

    this.listarElementos()
  }

}
