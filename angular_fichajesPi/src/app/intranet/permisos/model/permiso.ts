import { Empleado } from "../../empleados/model/empleado"

export class Permiso {

  id?: number
  aprobado: boolean | null
  dia: string
  descripcion: string
  horaFin: string
  horaInicio: string
  usuario: Empleado | null
  estado: string

  constructor(
    aprobado: boolean | null,
    dia: string,
    descripcion: string,
    horaFin: string,
    horaInicio: string,
    estado: string,
    usuario: Empleado | null

  ) {
    this.aprobado = aprobado;
    this.dia = dia;
    this.descripcion = descripcion;
    this.horaFin = horaFin;
    this.horaInicio = horaInicio;
    this.estado = estado;
    this.usuario = usuario;
  }

}