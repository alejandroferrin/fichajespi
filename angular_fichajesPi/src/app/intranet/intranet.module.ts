import { NgModule } from '@angular/core';
import { CoreModule } from '../core/core.module';
import { SharedModule } from '../shared/shared.module';

import { IntranetRoutingModule } from './intranet-routing.module';
import { IntranetComponent } from './intranet.component';


@NgModule({
  declarations: [
    IntranetComponent
  ],
  imports: [
    IntranetRoutingModule,
    CoreModule,
    SharedModule
  ],
  exports: [
  ]
})
export class IntranetModule { }
