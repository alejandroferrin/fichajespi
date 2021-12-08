import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { CoreModule } from 'src/app/core/core.module';
import { HomeService } from './service/home.service';
import { HomeComponent } from './containers/home/home.component';
import { FichajeService } from '../fichajes/service/fichaje.service';
import { FichajesUserComponent } from './containers/fichajes-user/fichajes-user.component';
import { FichajesSharedModule } from '../fichajes/components/fichajes-shared.module';
import { PermisosUserComponent } from './containers/permisos-user/permisos-user.component';
import { PermisosSharedModule } from '../permisos/components/permisos-shared.module';
import { NuevoPermisoComponent } from './containers/nuevo-permiso/nuevo-permiso.component';
import { NuevasVacacionesComponent } from './containers/nuevas-vacaciones/nuevas-vacaciones.component';
import { VacacionesUserComponent } from './containers/vacaciones-user/vacaciones-user.component';
import { VacacionesSharedModule } from '../vacaciones/components/vacaciones-shared.module';
import { CuentaComponent } from './containers/cuenta/cuenta.component';
import { EmpleadosService } from '../empleados/service/empleados.service';


@NgModule({
  declarations: [
    HomeComponent,
    FichajesUserComponent,
    PermisosUserComponent,
    NuevoPermisoComponent,
    NuevasVacacionesComponent,
    VacacionesUserComponent,
    CuentaComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    SharedModule,
    CoreModule,
    FichajesSharedModule,
    PermisosSharedModule,
    VacacionesSharedModule
  ],
  providers: [
    HomeService,
    EmpleadosService
  ]
})
export class HomeModule { }
