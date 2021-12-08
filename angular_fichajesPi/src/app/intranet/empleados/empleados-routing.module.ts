import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmpleadoDetalleComponent } from './containers/empleado-detalle/empleado-detalle.component';
import { EmpleadosComponent } from './containers/empleados/empleados.component';

const routes: Routes = [
  { path: '', redirectTo: 'list', pathMatch: 'full' },
  { path: 'list', component: EmpleadosComponent },
  { path: 'list/:id', component: EmpleadoDetalleComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmpleadosRoutingModule { }
