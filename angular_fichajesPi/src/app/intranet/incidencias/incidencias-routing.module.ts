import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IncidenciasDetallesComponent } from './containers/incidencias-detalles/incidencias-detalles.component';
import { IncidenciasStatisticsComponent } from './containers/incidencias-statistics/incidencias-statistics.component';
import { IncidenciasComponent } from './containers/incidencias/incidencias.component';
import { IncidenciasModule } from './incidencias.module';

const routes: Routes = [
  { path: '', redirectTo: 'list', pathMatch: 'full' },
  { path: 'list', component: IncidenciasComponent },
  { path: 'list/empleado/:numero', component: IncidenciasComponent },
  { path: 'list/:id', component: IncidenciasDetallesComponent },
  { path: 'statistics', component: IncidenciasStatisticsComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class IncidenciasRoutingModule { }
