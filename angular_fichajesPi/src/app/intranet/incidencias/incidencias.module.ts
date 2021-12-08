import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { IncidenciasRoutingModule } from './incidencias-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { CoreModule } from 'src/app/core/core.module';
import { IncidenciasComponent } from './containers/incidencias/incidencias.component';
import { IncidenciasDetallesComponent } from './containers/incidencias-detalles/incidencias-detalles.component';
import { IncidenciaService } from './service/incidencia.service';
import { IncidenciasStatisticsComponent } from './containers/incidencias-statistics/incidencias-statistics.component';


@NgModule({
  declarations: [
    IncidenciasComponent,
    IncidenciasDetallesComponent,
    IncidenciasStatisticsComponent
  ],
  imports: [
    CommonModule,
    IncidenciasRoutingModule,
    SharedModule,
    CoreModule
  ],
  providers: [
    IncidenciaService
  ]
})
export class IncidenciasModule { }
