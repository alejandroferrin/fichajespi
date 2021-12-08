import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CalendarioRoutingModule } from './calendario-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { CoreModule } from 'src/app/core/core.module';
import { MonthComponent } from './components/month/month.component';
import { CalendarioComponent } from './containers/calendario/calendario.component';
import { CalendariosComponent } from './containers/calendarios/calendarios.component';
import { EditarCalendarioComponent } from './containers/editar-calendario/editar-calendario.component';
import { NuevoDiaComponent } from './containers/nuevo-dia/nuevo-dia.component';
import { CalendarioService } from './service/calendario.service';
import { DiaService } from './service/dia.service';


@NgModule({
  declarations: [
    MonthComponent,
    CalendarioComponent,
    CalendariosComponent,
    EditarCalendarioComponent,
    NuevoDiaComponent
  ],
  imports: [
    CommonModule,
    CalendarioRoutingModule,
    SharedModule,
    CoreModule
  ],
  providers: [
    CalendarioService,
    DiaService
  ]
})
export class CalendarioModule { }
