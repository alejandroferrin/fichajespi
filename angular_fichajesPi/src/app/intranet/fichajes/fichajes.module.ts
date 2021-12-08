import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FichajesRoutingModule } from './fichajes-routing.module';
import { FichajesComponent } from './containers/fichajes/fichajes.component';
import { FichajesDetalleComponent } from './containers/fichaje-detalle/fichajes-detalle.component';
import { CoreModule } from 'src/app/core/core.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { FichajeService } from './service/fichaje.service';
import { FichajesSharedModule } from './components/fichajes-shared.module';


@NgModule({
  declarations: [
    FichajesComponent,
    FichajesDetalleComponent,
  ],
  imports: [
    CommonModule,
    FichajesRoutingModule,
    CoreModule,
    SharedModule,
    FichajesSharedModule
  ],
  providers: [
    FichajeService
  ],
  exports: [
  ]
})
export class FichajesModule { }
