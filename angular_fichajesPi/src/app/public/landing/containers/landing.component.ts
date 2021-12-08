import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginUsuario } from 'src/app/core/auth/model/login-usuario';
import { AuthService } from 'src/app/core/auth/service/auth.service';
import { TokenService } from 'src/app/core/auth/service/token.service';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {

  loginUsuario: LoginUsuario | null = null
  numero: string = ''
  password: string = ''
  errMsg: string = ''

  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  onLogin(): void {
    this.loginUsuario = new LoginUsuario(this.numero, this.password)
    this.authService.login(this.loginUsuario).subscribe(
      data => {
        this.tokenService.setToken(data.token)
        this.router.navigate(['/intranet'])
      },
      err => {
        this.errMsg = err.error.error
        this.clear();
      }
    )
  }

  private clear(): void {
    this.numero = '';
    this.password = '';
  }


}
