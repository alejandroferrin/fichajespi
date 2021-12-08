import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NuevoUsuario } from 'src/app/core/auth/model/nuevo-usuario';
import { AuthService } from 'src/app/core/auth/service/auth.service';
import { Popup } from 'src/app/shared/helper/popup';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {

  numero = ''
  nombreEmpleado = ''
  email = ''
  dni = ''
  rol = ''


  constructor(
    private service: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  clear(): void {
    this.numero = ''
    this.nombreEmpleado = ''
    this.email = ''
    this.dni = ''
    this.rol = ''
  }

  onRegister(): void {

    let roles = [this.rol];

    let nuevoUsuario = new NuevoUsuario(this.numero, this.nombreEmpleado, this.email, this.dni, roles);

    this.service.nuevo(nuevoUsuario).subscribe(
      data => {
        Popup.toastSucess('', data.mensaje);
        this.router.navigate([`intranet/empleados`])
      },
      err => {
        console.log(err)
        Popup.toastDanger('Error', err.error.mensaje);
      }
    )
  }

}
