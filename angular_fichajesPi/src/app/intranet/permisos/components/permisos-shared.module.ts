import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PermisosListaComponent } from './permisos-lista/permisos-lista.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { CoreModule } from 'src/app/core/core.module';
import { PermisoService } from '../service/permiso.service';



@NgModule({
  declarations: [
    PermisosListaComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    CoreModule
  ],
  exports: [PermisosListaComponent],
  providers: [PermisoService]
})
export class PermisosSharedModule { }
