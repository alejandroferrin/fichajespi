import { NgModule } from '@angular/core';
import { ChunkPipe } from './pipes/chunk.pipe';
import { CalendarFilterPipe } from './pipes/calendar-filter.pipe';
import { AuthService } from './auth/service/auth.service';
import { TokenService } from './auth/service/token.service';
import { TildePipe } from './pipes/tilde.pipe';



@NgModule({
  declarations: [
    ChunkPipe,
    CalendarFilterPipe,
    TildePipe
  ],
  imports: [
  ],
  exports: [
    ChunkPipe,
    CalendarFilterPipe,
    TildePipe
  ],
  providers:[
    AuthService,
    TokenService
  ]

})
export class CoreModule { }
