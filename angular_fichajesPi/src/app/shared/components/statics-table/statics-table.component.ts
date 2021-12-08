import { Component, Input, OnInit } from '@angular/core';
import { ChartDataService } from '../../interfaces/ChartDataService';

@Component({
  selector: 'app-statics-table',
  templateUrl: './statics-table.component.html',
  styleUrls: ['./statics-table.component.css']
})
export class StaticsTableComponent implements OnInit {

  listaElementos: any[] = []

  @Input() service: ChartDataService | null = null;
  @Input() tableName: string = 'Nombre Tabla';
  @Input() columnName: string = 'Nombre Columna';
  @Input() route: string = 'incidencias/list/empleado/';

  constructor() { }

  ngOnInit(): void {
    this.service?.getUserTableData().subscribe(
      data => {
        this.listaElementos = data
      }
    )
  }

}
