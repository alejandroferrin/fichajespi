import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from 'src/app/core/auth/service/token.service';
import { VacacionesService } from 'src/app/intranet/vacaciones/service/vacaciones.service';
import { NuevasVacaciones } from '../../models/nuevasVacaciones';
import { Popup } from 'src/app/shared/helper/popup';

@Component({
  selector: 'app-nuevas-vacaciones',
  templateUrl: './nuevas-vacaciones.component.html',
  styleUrls: ['./nuevas-vacaciones.component.css']
})
export class NuevasVacacionesComponent implements OnInit {

  inicio: string = '';
  fin: string = '';
  numeroUsuario: string = '';
  nombreUsuario: string = '';

  constructor(
    private service: VacacionesService,
    private tokenService: TokenService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.numeroUsuario = this.tokenService.getNumero();
    this.nombreUsuario = this.tokenService.getNombre();
  }

  clear(): void {
  }

  onRegister(): void {

    let vacaciones = new NuevasVacaciones(this.inicio, this.fin, this.numeroUsuario, this.nombreUsuario);

    this.service.create(vacaciones).subscribe(
      data => {
        console.log(data)
        Popup.toastSucess('', 'Vacaciones Guardadas');
        this.router.navigate([`intranet/home/vacaciones/${this.numeroUsuario}`])
      },
      err => {
        console.log(err)
        Popup.toastDanger('Error', err.error.mensaje);
      }
    )
  }

}
