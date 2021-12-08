import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { VacacionesRoutingModule } from './vacaciones-routing.module';
import { VacacionesComponent } from './containers/vacaciones/vacaciones.component';
import { VacacionesDetallesComponent } from './containers/vacaciones-detalles/vacaciones-detalles.component';
import { VacacionesService } from './service/vacaciones.service';
import { SharedModule } from 'src/app/shared/shared.module';
import { CoreModule } from 'src/app/core/core.module';
import { VacacionesListaComponent } from './components/vacaciones-lista/vacaciones-lista.component';
import { VacacionesSharedModule } from './components/vacaciones-shared.module';


@NgModule({
  declarations: [
    VacacionesComponent,
    VacacionesDetallesComponent,
  ],
  imports: [
    CommonModule,
    VacacionesRoutingModule,
    SharedModule,
    CoreModule,
    VacacionesSharedModule
  ],
  providers: [
    VacacionesService
  ]
})
export class VacacionesModule { }
