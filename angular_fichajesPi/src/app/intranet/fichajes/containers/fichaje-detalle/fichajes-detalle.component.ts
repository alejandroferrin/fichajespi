import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Empleado } from 'src/app/intranet/empleados/model/empleado';
import { Fichaje } from '../../model/fichaje';
import { FichajeService } from '../../service/fichaje.service';

import { Popup } from 'src/app/shared/helper/popup';

@Component({
  selector: 'app-fichajes-detalle',
  templateUrl: './fichajes-detalle.component.html',
  styleUrls: ['./fichajes-detalle.component.css']
})
export class FichajesDetalleComponent implements OnInit {

  model: Fichaje = new Fichaje('', '', '', null, new Empleado('', '', '', '', null, null, null, null, null,''))

  constructor(
    private service: FichajeService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
  ) {
  }


  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.params.id
    this.service.detail(id).subscribe(
      data => {
        console.log(data)
        this.model = data
      },
      err => {
        Popup.toastDanger('Ocurrió un error', err.message);
        console.log(err)
      }
    )
  }

  onUpdate(): void {
    const id = this.activatedRoute.snapshot.params.id
    this.service.update(id, this.model).subscribe(
      data => {
        Popup.toastSucess('', 'Cambios Guardados');
      },
      err => {
        Popup.toastDanger('Ocurrió un error', err.message);
        console.log(err)
      }
    )
  }

  onDelete(): void {
    const id = this.activatedRoute.snapshot.params.id
    Popup.dangerConfirmBox('¿Desea eliminar el fichaje?', 'Esta operación no se puede deshacer', 'SI', 'NO').openConfirmBox$().subscribe(resp => {
      // IConfirmBoxPublicResponse
      if (resp.Success) {
        this.delete(id)
      }
    });
  }

  delete(id: number) {
    this.service.delete(id).subscribe(
      data => {
        Popup.toastWarning('', 'Fichaje Eliminado');
        this.router.navigate(['intranet/fichajes'])
      },
      err => {
        Popup.toastDanger('Ocurrió un error', err.message);
        console.log(err)
      }
    )
  }



}
