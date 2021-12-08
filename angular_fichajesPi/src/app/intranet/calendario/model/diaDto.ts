export class DiaDto {
  id?:number
  diaSemana?:number
  diaFecha?:number
  mes?:number
  dia: string
  horaInicio: string
  horaFin: string
  calendarioNombre: string


  constructor(
    dia: string,
    horaInicio: string,
    horaFin: string,
    calendarioNombre: string
  ) {
    this.dia = dia
    this.horaInicio = horaInicio
    this.horaFin = horaFin
    this.calendarioNombre = calendarioNombre

  }
}
