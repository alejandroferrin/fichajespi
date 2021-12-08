import { Component, OnInit, Pipe, PipeTransform } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Calendario } from '../../model/calendario';
import { CalendarioService } from '../../service/calendario.service';
import { DiaService } from '../../service/dia.service';
import { Popup } from 'src/app/shared/helper/popup';


@Component({
  selector: 'app-editar-calendario',
  templateUrl: './editar-calendario.component.html',
  styleUrls: ['./editar-calendario.component.css']
})
export class EditarCalendarioComponent implements OnInit {

  model: Calendario = new Calendario('', 0, 0, 0, [], false)
  diasSemana: string[] = ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado']
  id: number = 0
  diaSemana: number | null = null
  diaFecha: number | null = null
  mes: number | null = null

  constructor(
    private service: CalendarioService,
    private diaService: DiaService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.params.id
    this.service.detail(this.id).subscribe(
      data => {
        this.model = data
        this.assignExtraData()
        //console.log(this.model.dias)
        //console.log(this.model)
      },
      err => {
        Popup.toastDanger('Ocurrió un error', err.message);
        console.log(err)
      }
    )
  }

  assignExtraData() {
    this.model.dias.map(d => {
      d.diaSemana = new Date(d.dia).getDay()
      d.diaFecha = new Date(d.dia).getDate()
      d.mes = new Date(d.dia).getMonth()
    })
  }

  onPropertyChange(model: any) {
    return model.viewModel
  }

  onUpdate(): void {

    //const id = this.activatedRoute.snapshot.params.id
    this.service.update(this.id, this.model).subscribe(
      data => {
        Popup.toastSucess('', 'Cambios Guardados');
        this.ngOnInit()
      },
      err => {
        Popup.toastDanger('Ocurrió un error', err.message);
        console.log(err)
      }
    )
  }

  onDelete(): void {
    const id = this.activatedRoute.snapshot.params.id
    Popup.dangerConfirmBox('¿Desea eliminar el calendario?', 'Esta operación no se puede deshacer', 'SI', 'NO').openConfirmBox$().subscribe(resp => {
      // IConfirmBoxPublicResponse
      if (resp.Success) {
        this.delete(id)
      }
    });
  }

  delete(id: number) {
    this.service.delete(id).subscribe(
      data => {
        Popup.toastWarning('', 'Calendario Eliminado');
        this.router.navigate(['intranet/calendario'])
      },
      err => {
        Popup.toastDanger('Ocurrió un error', err.message);
        console.log(err)
      }
    )
  }

  onDeleteDay(idDia: number): void {
    Popup.dangerConfirmBox('¿Desea eliminar el Día?', 'Esta operación no se puede deshacer', 'SI', 'NO').openConfirmBox$().subscribe(resp => {
      // IConfirmBoxPublicResponse
      if (resp.Success) {
        this.deleteDay(idDia)
      }
    });
  }

  deleteDay(id: number) {
    this.diaService.delete(id).subscribe(
      data => {
        Popup.toastWarning('', 'Día Eliminado');
        this.ngOnInit()
      },
      err => {
        Popup.toastDanger('Ocurrió un error', err.message);
        console.log(err)
      }
    )
  }

}
