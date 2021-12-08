import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginGuard } from './core/auth/guards/login.guard';
import { GuardService } from './core/auth/guards/prod-guards.service';



const routes: Routes = [
  { path: 'public', loadChildren: () => import('./public/public.module').then(m => m.PublicModule), canActivate:[LoginGuard]  },
  { path: 'intranet', loadChildren: () => import('./intranet/intranet.module').then(m => m.IntranetModule), canActivate: [GuardService], data: { expectedRol: ['user','admin'] } },
  { path: '**', redirectTo: 'public', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
