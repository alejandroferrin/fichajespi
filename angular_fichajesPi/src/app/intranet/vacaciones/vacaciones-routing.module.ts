import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VacacionesDetallesComponent } from './containers/vacaciones-detalles/vacaciones-detalles.component';
import { VacacionesComponent } from './containers/vacaciones/vacaciones.component';

const routes: Routes = [
  { path: '', redirectTo: 'list', pathMatch: 'full' },
  { path: 'list', component: VacacionesComponent },
  { path: 'list/empleado/:numero', component: VacacionesComponent },
  { path: 'list/:id', component: VacacionesDetallesComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class VacacionesRoutingModule { }
