import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Empleado } from 'src/app/intranet/empleados/model/empleado';
import { Vacaciones } from '../../models/vacaciones';
import { VacacionesService } from '../../service/vacaciones.service';
import { Popup } from 'src/app/shared/helper/popup';

@Component({
  selector: 'app-vacaciones-detalles',
  templateUrl: './vacaciones-detalles.component.html',
  styleUrls: ['./vacaciones-detalles.component.css']
})
export class VacacionesDetallesComponent implements OnInit {

  model: Vacaciones = new Vacaciones(null, null, '', '', '', new Empleado('', '', '', '', null, null, null, null, null,''))

  constructor(
    private service: VacacionesService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    const id = this.activatedRoute.snapshot.params.id
    this.service.detail(id).subscribe(
      data => {
        this.model = data
        //console.log(data)
      },
      err => {
        Popup.toastDanger('Ocurrió un error', err.message);
        console.log(err)
      }
    )

  }


  onDelete(): void {
    const id = this.activatedRoute.snapshot.params.id
    Popup.dangerConfirmBox('¿Desea eliminar las vacaciones?', 'Esta operación no se puede deshacer', 'SI', 'NO').openConfirmBox$().subscribe(resp => {
      // IConfirmBoxPublicResponse
      if (resp.Success) {
        this.delete(id)
      }
    });
  }

  delete(id: number) {
    this.service.delete(id).subscribe(
      data => {
        Popup.toastWarning('', 'Vacaciones Eliminadas');
        this.router.navigate(['intranet/vacaciones'])
      },
      err => {
        Popup.toastDanger('Ocurrió un error', err.message);
        console.log(err)
      }
    )
  }

  onApprove(): void {
    const id = this.activatedRoute.snapshot.params.id
    this.service.aprobar(id).subscribe(
      data => {
        Popup.toastSucess('', 'Vacaciones Aprobadas');
        this.load();
      },
      err => {
        Popup.toastDanger('Ocurrió un error', err.message);
        console.log(err)
      }
    )
  }

  onDeny(): void {
    const id = this.activatedRoute.snapshot.params.id
    this.service.denegar(id).subscribe(
      data => {
        Popup.toastDanger('', 'Vacaciones Denegadas');
        this.load();
      },
      err => {
        Popup.toastDanger('Ocurrió un error', err.message);
        console.log(err)
      }
    )
  }

}
