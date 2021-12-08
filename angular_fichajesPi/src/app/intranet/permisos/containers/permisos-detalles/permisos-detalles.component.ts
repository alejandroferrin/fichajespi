import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Empleado } from 'src/app/intranet/empleados/model/empleado';
import { Permiso } from '../../model/permiso';
import { PermisoService } from '../../service/permiso.service';
import { Popup } from 'src/app/shared/helper/popup';

@Component({
  selector: 'app-permisos-detalles',
  templateUrl: './permisos-detalles.component.html',
  styleUrls: ['./permisos-detalles.component.css']
})
export class PermisosDetallesComponent implements OnInit {

  model: Permiso = new Permiso(null, '', '', '', '', '', new Empleado('', '', '', '', null, null, null, null, null,''))

  constructor(
    private service: PermisoService,
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
      },
      err => {
        Popup.toastDanger('Ocurrió un error', err.message);
        console.log(err)
      }
    )

  }



  onDelete(): void {
    const id = this.activatedRoute.snapshot.params.id
    Popup.dangerConfirmBox('¿Desea eliminar el permiso?', 'Esta operación no se puede deshacer', 'SI', 'NO').openConfirmBox$().subscribe(resp => {
      // IConfirmBoxPublicResponse
      if (resp.Success) {
        this.delete(id)
      }
    });
  }

  delete(id: number) {
    this.service.delete(id).subscribe(
      data => {
        Popup.toastWarning('', 'Permiso Eliminado');
        this.router.navigate(['intranet/permisos'])
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
        Popup.toastSucess('', 'Permiso Aprobado');
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
        Popup.toastDanger('', 'Permiso Denegado');
        this.load();
      },
      err => {
        Popup.toastDanger('Ocurrió un error', err.message);
        console.log(err)
      }
    )
  }

}
