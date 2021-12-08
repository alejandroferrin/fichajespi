import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FichajesDetalleComponent } from './containers/fichaje-detalle/fichajes-detalle.component';
import { FichajesComponent } from './containers/fichajes/fichajes.component';

const routes: Routes = [
  { path: '', redirectTo: 'list', pathMatch: 'full' },
  { path: 'list', component: FichajesComponent },
  { path: 'list/empleado/:numero', component: FichajesComponent },
  { path: 'list/:id', component: FichajesDetalleComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FichajesRoutingModule { }
