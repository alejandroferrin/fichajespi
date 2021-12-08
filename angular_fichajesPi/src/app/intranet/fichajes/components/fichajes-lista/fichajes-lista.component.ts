import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TokenService } from 'src/app/core/auth/service/token.service';
import { Pagination } from 'src/app/shared/components/pagination/model/pagination.model';
import { FichajeDto } from '../../model/fichajeDto';
import { FichajeService } from '../../service/fichaje.service';

@Component({
  selector: 'app-fichajes-lista',
  templateUrl: './fichajes-lista.component.html',
  styleUrls: ['./fichajes-lista.component.css']
})
export class FichajesListaComponent implements OnInit {

  headers = [
    "dia",
    "hora",
    "tipo",
    "usuario.nombreEmpleado",
    "usuario.numero",
    "origen"
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

  dto: FichajeDto = {
    diaDesde: '',
    diaHasta: '',
    horaDesde: '',
    horaHasta: '',
    hora: '',
    dia: '',
    origen: null,
    tipo: '',

    numeroUsuario: '',
    nombreUsuario: '',
  }


  isAdmin: boolean = false;

  constructor(
    public service: FichajeService,
    private activatedRoute: ActivatedRoute,
    private tokenService: TokenService
  ) { }

  ngOnInit(): void {
    this.dto.numeroUsuario = this.activatedRoute.snapshot.params.numero;
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

  clearNumero(): void {
    this.dto.numeroUsuario = ''
    this.listarElementos()
  }
  clearNombre(): void {
    this.dto.nombreUsuario = ''
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
  clearHoraDesde(): void {
    this.dto.horaDesde = ''
    this.listarElementos()
  }
  clearHoraHasta(): void {
    this.dto.horaHasta = ''
    this.listarElementos()
  }
  clearOrigen(): void {
    this.dto.origen = ''
    this.listarElementos()
  }
  clearTipo(): void {
    this.dto.tipo = ''
    this.listarElementos()
  }

  clear(): void {
    this.dto.diaDesde = ''
    this.dto.horaDesde = ''
    this.dto.diaHasta = ''
    this.dto.horaHasta = ''
    this.dto.origen = null
    this.dto.tipo = ''

    this.dto.numeroUsuario = ''
    this.dto.nombreUsuario = ''

    this.listarElementos()
  }

}
