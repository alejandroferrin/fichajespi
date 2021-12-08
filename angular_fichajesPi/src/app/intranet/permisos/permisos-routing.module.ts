import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PermisosDetallesComponent } from './containers/permisos-detalles/permisos-detalles.component';
import { PermisosStatisticsComponent } from './containers/permisos-statistics/permisos-statistics.component';
import { PermisosComponent } from './containers/permisos/permisos.component';

const routes: Routes = [
  { path: '', redirectTo: 'list', pathMatch: 'full' },
  { path: 'list', component: PermisosComponent },
  { path: 'list/empleado/:numero', component: PermisosComponent },
  { path: 'list/:id', component: PermisosDetallesComponent },
  { path: 'statistics', component: PermisosStatisticsComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PermisosRoutingModule { }
