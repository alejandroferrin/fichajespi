import { Component, OnInit } from '@angular/core';
import { IncidenciaService } from '../../service/incidencia.service';

@Component({
  selector: 'app-incidencias-statistics',
  templateUrl: './incidencias-statistics.component.html',
  styleUrls: ['./incidencias-statistics.component.css']
})
export class IncidenciasStatisticsComponent implements OnInit {

  listaElementos: any[] = []

  constructor(
    public service: IncidenciaService,
  ) { }

  ngOnInit(): void {
    this.service?.getRankingIncidencias().subscribe(
      data => {
        this.listaElementos = data
      }
    )
  }

}
