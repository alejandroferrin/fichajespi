import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EmpleadosRoutingModule } from './empleados-routing.module';
import { EmpleadosComponent } from './containers/empleados/empleados.component';
import { EmpleadoDetalleComponent } from './containers/empleado-detalle/empleado-detalle.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { CoreModule } from 'src/app/core/core.module';
import { EmpleadosService } from './service/empleados.service';


@NgModule({
  declarations: [
    EmpleadosComponent,
    EmpleadoDetalleComponent
  ],
  imports: [
    CommonModule,
    EmpleadosRoutingModule,
    SharedModule,
    CoreModule
  ],
  providers:[
    EmpleadosService
  ]
})
export class EmpleadosModule { }
