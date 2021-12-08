import { Empleado } from "../../empleados/model/empleado"
export class Fichaje {

  id?: number
  hora: string
  dia: string
  tipo: string
  origen: string |null
  usuario: Empleado | null

  constructor(
    hora: string,
    dia: string,
    tipo: string,
    origen: string | null,
    usuario: Empleado | null
  ) {
    this.hora = hora;
    this.dia = dia;
    this.tipo = tipo;
    this.origen = origen;
    this.usuario = usuario;
  }

}