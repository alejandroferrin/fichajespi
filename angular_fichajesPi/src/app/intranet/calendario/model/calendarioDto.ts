import { DiaDto } from "./diaDto";

export interface CalendarioDto {
  nombre: string,
  year: number,
  minutosMasEntrada: number,
  minutosMenosEntrada: number,
  dias: DiaDto[]
}