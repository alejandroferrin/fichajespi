import { Empleado } from "../../empleados/model/empleado"

export class Incidencia {

  id?: number
  resumen: string
  dia: string
  explicacion: string
  resuelta: boolean | null
  usuario: Empleado | null

  constructor(
    resumen: string,
    dia: string,
    explicacion: string,
    resuelta: boolean | null,
    usuario: Empleado | null
  ) {
    this.resumen = resumen;
    this.dia = dia;
    this.explicacion = explicacion;
    this.resuelta = resuelta;
    this.usuario = usuario;
  }

}