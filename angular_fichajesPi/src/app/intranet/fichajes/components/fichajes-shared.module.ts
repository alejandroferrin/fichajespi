import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import { CoreModule } from 'src/app/core/core.module';
import { FichajeService } from '../service/fichaje.service';
import { FichajesListaComponent } from './fichajes-lista/fichajes-lista.component';



@NgModule({
  declarations: [
    FichajesListaComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    CoreModule
  ],
  exports: [FichajesListaComponent],
  providers: [FichajeService]
})
export class FichajesSharedModule { }
