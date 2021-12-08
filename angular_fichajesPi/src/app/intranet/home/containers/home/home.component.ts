import { Component, OnInit } from '@angular/core';
import { HomeService } from '../../service/home.service';
import { Popup } from 'src/app/shared/helper/popup';
import { FichajeDto } from 'src/app/intranet/fichajes/model/fichajeDto';
import { Empleado } from 'src/app/intranet/empleados/model/empleado';
import { Router } from '@angular/router';
import { EmpleadosService } from 'src/app/intranet/empleados/service/empleados.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  model: Empleado = new Empleado('', '', '', '', null, null, null, null, null, '')

  dto: FichajeDto = {
    diaDesde: '',
    diaHasta: '',
    horaDesde: '',
    horaHasta: '',
    hora: '',
    dia: '',
    origen: 'web',
    tipo: '',

    numeroUsuario: '',
    nombreUsuario: '',
  }

  constructor(
    private service: HomeService,
    private empleadoService: EmpleadosService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData(): void {
    this.empleadoService.getMyUsuario().subscribe(
      data => {
        this.model = data
        this.dto.numeroUsuario = data.numero;
      },
      err => {
        Popup.toastDanger('Ocurrió un error', err.message);
        console.log(err)
      }
    )
  }

  fichar(): void {
    this.service.now(this.dto).subscribe(
      data => {
        Popup.toastSucess('', 'Fichaje realizado');
        this.loadData();
      },
      err => {
        Popup.toastDanger('Ocurrió un error', err.message);
        console.log(err)
      }
    )

  }

}
