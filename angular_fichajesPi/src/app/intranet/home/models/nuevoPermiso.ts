export class NuevoPermiso {

  id?: number
  dia: string
  horaInicio: string
  horaFin: string
  descripcion: string
  numeroUsuario: string
  nombreUsuario: string

  constructor(
    dia: string,
    horaInicio: string,
    horaFin: string,
    descripcion: string,
    numeroUsuario: string,
    nombreUsuario: string
  ) {
    this.dia = dia
    this.horaInicio = horaInicio
    this.horaFin = horaFin
    this.descripcion = descripcion
    this.numeroUsuario = numeroUsuario
    this.nombreUsuario = nombreUsuario
  }

}