import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

const TOKEN_KEY = 'AuthToken'

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  roles: Array<string> = []

  constructor(
    private router: Router
  ) { }

  public setToken(token: string): void {
    window.localStorage.removeItem(TOKEN_KEY)
    window.localStorage.setItem(TOKEN_KEY, token)
  }

  public getToken(): string | null {
    return localStorage.getItem(TOKEN_KEY)

  }

  public isLogged(): boolean {
    if (this.getToken()) {
      return true
    }
    return false;
  }

  public getNumero(): string {
    if (!this.isLogged()) {
      return ''
    }
    const token = this.getToken()
    const payload = token!.split('.')[1]
    const payloadDecoded = atob(payload)
    const values = JSON.parse(payloadDecoded)
    const username = values.sub
    return username
  }

  public getId(): number {
    if (!this.isLogged()) {
      return 0 
    }
    const token = this.getToken()
    const payload = token!.split('.')[1]
    const payloadDecoded = atob(payload)
    const values = JSON.parse(payloadDecoded)
    const id = values.id
    return id
  }

  public getNombre(): string {
    if (!this.isLogged()) {
      return ''
    }
    const token = this.getToken()
    const payload = token!.split('.')[1]
    const payloadDecoded = atob(payload)
    const values = JSON.parse(payloadDecoded)
    const username = values.nombre
    return username
  }

  public isExpired(): boolean {
    if (!this.isLogged()) {
      return true
    }
    const token = this.getToken()
    const payload = token!.split('.')[1]
    const payloadDecoded = atob(payload)
    const values = JSON.parse(payloadDecoded)
    const expiry = values.exp
    return (Math.floor((new Date).getTime() / 1000)) >= expiry;
  }


  public isAdmin(): boolean {
    if (!this.isLogged()) {
      return false
    }
    const token = this.getToken()
    const payload = token!.split('.')[1]
    const payloadDecoded = atob(payload)
    const values = JSON.parse(payloadDecoded)
    const roles = values.roles
    if (roles.indexOf('ROLE_RRHH') < 0) {
      return false
    }
    return true
  }

  public logOut(): void {
    window.localStorage.clear()
    this.router.navigate(['/'])
  }

}
