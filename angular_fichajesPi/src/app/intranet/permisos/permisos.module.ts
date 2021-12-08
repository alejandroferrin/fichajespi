import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PermisosRoutingModule } from './permisos-routing.module';
import { PermisosDetallesComponent } from './containers/permisos-detalles/permisos-detalles.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { CoreModule } from 'src/app/core/core.module';
import { PermisoService } from './service/permiso.service';
import { PermisosComponent } from './containers/permisos/permisos.component';
import { PermisosSharedModule } from './components/permisos-shared.module';
import { PermisosStatisticsComponent } from './containers/permisos-statistics/permisos-statistics.component';


@NgModule({
  declarations: [
    PermisosComponent,
    PermisosDetallesComponent,
    PermisosStatisticsComponent
  ],
  imports: [
    CommonModule,
    PermisosRoutingModule,
    SharedModule,
    CoreModule,
    PermisosSharedModule
  ],
  providers: [
    PermisoService
  ]
})
export class PermisosModule { }
