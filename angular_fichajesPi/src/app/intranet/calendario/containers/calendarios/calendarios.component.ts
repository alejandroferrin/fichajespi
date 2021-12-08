import { Component, OnInit } from '@angular/core';
import { CalendarioService } from '../../service/calendario.service';

@Component({
  selector: 'app-calendarios',
  templateUrl: './calendarios.component.html',
  styleUrls: ['./calendarios.component.css']
})
export class CalendariosComponent implements OnInit {

  listaElementos: Array<any> = []

  constructor(private service: CalendarioService) { }

  ngOnInit(): void {
    this.listarElementos()
  }

  listarElementos(): void {
    this.service.getElements().subscribe(
      data => {
        this.listaElementos = data
        //console.log(data)
      },
      err => {
        console.log(err)
      }
    )
  }

}
