import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CalendarioService } from '../../service/calendario.service';
import { CalendarioDto } from '../../model/calendarioDto';
import { DiaDto } from '../../model/diaDto';
import { Popup } from 'src/app/shared/helper/popup';

@Component({
  selector: 'app-calendario',
  templateUrl: './calendario.component.html',
  styleUrls: ['./calendario.component.css'],
})
export class CalendarioComponent {

  monthsOfYear: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
  horaInicio: string = '09:00'
  horaFin: string = '17:00'


  dto: CalendarioDto = {
    nombre: 'Calendario ' + new Date().getFullYear(),
    year: new Date().getFullYear(),
    minutosMasEntrada: 0,
    minutosMenosEntrada: 0,
    dias: []
  }

  constructor(
    private service: CalendarioService,
    private router: Router,
  ) { }

  onAdded(dia: DiaDto) {
    let exists = false

    this.dto.dias.forEach(d => {
      if (d.dia === dia.dia) {
        exists = true
      }
    })

    if (exists) {
      this.dto.dias = this.dto.dias.filter((value) => dia.dia != value.dia)
    } else {
      this.dto.dias.push(dia)
    }
    //console.log(this.dto.dias)
  }


  onSave(): void {
    this.service.saveCalendar(this.dto).subscribe(
      data => {
        Popup.toastSucess('', 'Calendario Guardado');
        this.router.navigate(['intranet/calendario'])
      },
      err => {
        Popup.toastDanger('Ocurrió un error', err.message);
        console.log(err)
      }
    )
  }


}
