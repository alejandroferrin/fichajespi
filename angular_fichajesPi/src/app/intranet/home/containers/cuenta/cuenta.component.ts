import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from 'src/app/core/auth/service/token.service';
import { EmpleadosService } from 'src/app/intranet/empleados/service/empleados.service';
import { Popup } from 'src/app/shared/helper/popup';
import { Password } from '../../models/password';

@Component({
  selector: 'app-cuenta',
  templateUrl: './cuenta.component.html',
  styleUrls: ['./cuenta.component.css']
})
export class CuentaComponent implements OnInit {

  pass1: string = '';
  pass2: string = '';


  constructor(
    private service: EmpleadosService,
    private tokenService: TokenService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  submit(): void {
    if (this.checkPasswords()) {
      this.service.changePassword(this.tokenService.getId(), new Password(this.pass1)).subscribe(
        data => {
          console.log(data);
          Popup.toastSucess('', 'Password cambiado');
          this.router.navigate(['intranet/home'])
        },
        err => {
          Popup.toastDanger('Ocurrió un error', err.message);
          console.log(err)
        }
      )
    }
  }

  private checkPasswords(): boolean {
    if (this.pass1 === this.pass2) {
      return true;
    } else {
      Popup.toastDanger('', 'Las contraseñas no coinciden');
      return false;
    }

  }

}
