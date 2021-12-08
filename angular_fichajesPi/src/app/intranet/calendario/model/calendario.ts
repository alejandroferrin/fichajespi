import { DiaDto } from "./diaDto"

export class Calendario {
  id?: number
  nombre: string
  year: number
  minutosMasEntrada: number
  minutosMenosEntrada: number
  dias: DiaDto[]
  active: boolean

  constructor(
    nombre: string,
    year: number,
    minutosMasEntrada: number,
    minutosMenosEntrada: number,
    dias: DiaDto[],
    active: boolean
  ) {
    this.nombre = nombre
    this.year = year
    this.minutosMasEntrada = minutosMasEntrada
    this.minutosMenosEntrada = minutosMenosEntrada
    this.dias = dias
    this.active = active
  }

}