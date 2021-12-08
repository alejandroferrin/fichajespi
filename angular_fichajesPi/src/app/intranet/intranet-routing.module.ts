import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GuardService } from '../core/auth/guards/prod-guards.service';
import { IntranetComponent } from './intranet.component';

const routes: Routes = [
  {
    path: '', component: IntranetComponent, children: [
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      {
        path: 'empleados', loadChildren: () => import('./empleados/empleados.module').then(m => m.EmpleadosModule)
        , canActivate: [GuardService], data: { expectedRol: ['admin'] }
      },
      {
        path: 'incidencias', loadChildren: () => import('./incidencias/incidencias.module').then(m => m.IncidenciasModule)
        , canActivate: [GuardService], data: { expectedRol: ['admin'] }
      },
      {
        path: 'fichajes', loadChildren: () => import('./fichajes/fichajes.module').then(m => m.FichajesModule)
        , canActivate: [GuardService], data: { expectedRol: ['admin'] }
      },
      {
        path: 'permisos', loadChildren: () => import('./permisos/permisos.module').then(m => m.PermisosModule)
        , canActivate: [GuardService], data: { expectedRol: ['admin'] }
      },
      {
        path: 'calendario', loadChildren: () => import('./calendario/calendario.module').then(m => m.CalendarioModule)
        , canActivate: [GuardService], data: { expectedRol: ['admin'] }
      },
      {
        path: 'vacaciones', loadChildren: () => import('./vacaciones/vacaciones.module').then(m => m.VacacionesModule)
        , canActivate: [GuardService], data: { expectedRol: ['admin'] }
      },
      {
        path: 'register', loadChildren: () => import('./register/register.module').then(m => m.RegisterModule)
        , canActivate: [GuardService], data: { expectedRol: ['admin'] }
      },
      { path: 'home', loadChildren: () => import('./home/home.module').then(m => m.HomeModule) },
    ]
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class IntranetRoutingModule { }
