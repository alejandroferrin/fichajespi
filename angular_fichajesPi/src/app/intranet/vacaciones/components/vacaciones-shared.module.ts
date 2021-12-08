import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VacacionesListaComponent } from './vacaciones-lista/vacaciones-lista.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { CoreModule } from 'src/app/core/core.module';
import { VacacionesService } from '../service/vacaciones.service';



@NgModule({
  declarations: [
    VacacionesListaComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    CoreModule
  ],
  exports: [VacacionesListaComponent],
  providers: [VacacionesService]
})
export class VacacionesSharedModule { }
