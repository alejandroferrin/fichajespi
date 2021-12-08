export class NuevasVacaciones {

  id?: number
  inicio: string
  fin: string
  numeroUsuario: string
  nombreUsuario: string


  constructor(
    inicio: string,
    fin: string,
    numeroUsuario: string,
    nombreUsuario: string
  ) {
    this.inicio = inicio
    this.fin = fin
    this.numeroUsuario = numeroUsuario
    this.nombreUsuario = nombreUsuario
  }


}