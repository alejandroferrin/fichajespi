import { Empleado } from "../../empleados/model/empleado"

export class Vacaciones {

  id?: number
  consumidas: boolean | null
  aprobado: boolean | null
  estado: string
  inicio: string
  fin: string
  usuario: Empleado | null

  constructor(
    aprobado: boolean | null,
    consumidas: boolean | null,
    inicio: string,
    fin: string,
    estado: string,
    usuario: Empleado | null

  ) {
    this.aprobado = aprobado;
    this.inicio = inicio;
    this.fin = fin;
    this.consumidas = consumidas;
    this.estado = estado;
    this.usuario = usuario;
  }

}