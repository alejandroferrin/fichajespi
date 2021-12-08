import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CalendarioComponent } from './containers/calendario/calendario.component';
import { CalendariosComponent } from './containers/calendarios/calendarios.component';
import { EditarCalendarioComponent } from './containers/editar-calendario/editar-calendario.component';
import { NuevoDiaComponent } from './containers/nuevo-dia/nuevo-dia.component';

const routes: Routes = [
  { path: '', redirectTo: 'list', pathMatch: 'full' },
  { path: 'list', component: CalendariosComponent },
  { path: 'nuevo', component: CalendarioComponent },
  { path: 'nuevo/dia/:id', component: NuevoDiaComponent },
  { path: 'list/:id', component: EditarCalendarioComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CalendarioRoutingModule { }
