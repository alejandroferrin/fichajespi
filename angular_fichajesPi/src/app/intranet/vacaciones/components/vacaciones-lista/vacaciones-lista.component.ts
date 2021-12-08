import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TokenService } from 'src/app/core/auth/service/token.service';
import { Pagination } from 'src/app/shared/components/pagination/model/pagination.model';
import { VacacionesDto } from '../../models/vacacionesDto';
import { VacacionesService } from '../../service/vacaciones.service';

@Component({
  selector: 'app-vacaciones-lista',
  templateUrl: './vacaciones-lista.component.html',
  styleUrls: ['./vacaciones-lista.component.css']
})
export class VacacionesListaComponent implements OnInit {

  headers = [
    "inicio",
    "fin",
    "estado",
    "consumidas",
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

  dto: VacacionesDto = {
    consumidas: null,
    estado: '',
    inicioDesde: '',
    inicioHasta: '',
    finDesde: '',
    finHasta: '',

    usuarioEmail: '',
    usuarioNumero: '',
    usuarioNombre: '',
    usuarioDni: '',
  }


  isAdmin: boolean = false;

  constructor(
    public service: VacacionesService,
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


  clearEstado(): void {
    this.dto.estado = ''
    this.listarElementos()
  }
  clearConsumidas(): void {
    this.dto.consumidas = null
    this.listarElementos()
  }
  clearInicioDesde(): void {
    this.dto.inicioDesde = ''
    this.listarElementos()
  }
  clearInicioHasta(): void {
    this.dto.inicioHasta = ''
    this.listarElementos()
  }
  clearfinDesde(): void {
    this.dto.finDesde = ''
    this.listarElementos()
  }
  clearfinHasta(): void {
    this.dto.finHasta = ''
    this.listarElementos()
  }

  clear(): void {
    this.dto.consumidas = null
    this.dto.estado = ''
    this.dto.inicioDesde = ''
    this.dto.inicioHasta = ''
    this.dto.finDesde = ''
    this.dto.finHasta = ''

    this.dto.usuarioEmail = ''
    this.dto.usuarioNumero = ''
    this.dto.usuarioNombre = ''
    this.dto.usuarioDni = ''

    this.listarElementos()
  }

}
