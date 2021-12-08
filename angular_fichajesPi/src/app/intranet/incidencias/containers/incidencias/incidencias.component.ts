import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TokenService } from 'src/app/core/auth/service/token.service';
import { Pagination } from 'src/app/shared/components/pagination/model/pagination.model';
import { IncidenciaDto } from '../../model/incidenciaDto'
import { IncidenciaService } from '../../service/incidencia.service';

@Component({
  selector: 'app-incidencias',
  templateUrl: './incidencias.component.html',
  styleUrls: ['./incidencias.component.css']
})
export class IncidenciasComponent implements OnInit {

  headers = [
    "dia",
    "resuelta",
    "descripcion",
    "usuario.nombreEmpleado",
    "usuario.numero"
  ]
  //paginación
  order = 'id'
  asc = false

  listaElementos: Array<any> = [];


  pag: Pagination = {
    totalPages: [],
    page: 0,
    isFirst: false,
    isLast: false,
    size: 15,
    sizeLimit: 100,
    listPagesLimits: 4,
  }

  dto: IncidenciaDto = {
    descripcion: '',
    diaDesde: '',
    diaHasta: '',
    explicacion: null,
    resuelta: null,

    usuarioEmail: '',
    usuarioNumero: '',
    usuarioNombre: '',
    usuarioDni: '',
  }

  isAdmin: boolean = false;

  listaMensualIncidencias: Array<any> = [];

  constructor(
    public service: IncidenciaService,
    private activatedRoute: ActivatedRoute,
    private tokenService: TokenService
  ) { }

  ngOnInit(): void {
    this.dto.usuarioNumero = this.activatedRoute.snapshot.params.numero;
    this.isAdmin = this.tokenService.isAdmin();
    this.listarElementos()

    /*
        let dtoChart: IncidenciaDto = {
          descripcion: '',
          diaDesde: '',
          diaHasta: '',
          explicacion: null,
          resuelta: null,
    
          usuarioEmail: '',
          usuarioNumero: '',
          usuarioNombre: '',
          usuarioDni: '',
        }
    
         for (let i = 1; i <= 12; i++) {
          let year: number = new Date().getFullYear();
          let first = new Date(`${year}-${i}`);
    
          let last;
          if (i == 12) {
            last = new Date(`${year + 1}-${1}`);
          } else {
            last = new Date(`${year}-${i + 1}`);
          }
    
          last.setDate(last.getDate() - 1);
    
          dtoChart.diaDesde = this.formatDate(first);
          dtoChart.diaHasta = this.formatDate(last);
    
          this.service.getCsvData(dtoChart).subscribe(
            data => {
              this.listaMensualIncidencias[first.getMonth()+1]=data.length;
            }
          )
    
        }
    
        console.log(this.listaMensualIncidencias); */

  }

  listarElementos(): void {
    this.service.getElements(this.dto, this.pag.page, this.pag.size, this.order, this.asc).subscribe(
      data => {
        console.log(data)
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
  clearExplicacion(): void {
    this.dto.explicacion = ''
    this.listarElementos()
  }
  clearResuelta(): void {
    this.dto.resuelta = null
    this.listarElementos()
  }

  clear(): void {
    this.dto.descripcion = ''
    this.dto.diaDesde = ''
    this.dto.diaHasta = ''
    this.dto.explicacion = ''
    this.dto.resuelta = null

    this.dto.usuarioEmail = ''
    this.dto.usuarioNumero = ''
    this.dto.usuarioNombre = ''
    this.dto.usuarioDni = ''

    this.listarElementos()
  }

  formatDate(date: Date) {
    var d = new Date(date),
      month = '' + (d.getMonth() + 1),
      day = '' + d.getDate(),
      year = d.getFullYear();

    if (month.length < 2)
      month = '0' + month;
    if (day.length < 2)
      day = '0' + day;

    return [year, month, day].join('-');
  }

}
