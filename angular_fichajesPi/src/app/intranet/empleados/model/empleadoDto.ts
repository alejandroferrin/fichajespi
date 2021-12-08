export interface EmpleadoDto {
  email: string
  numero: string
  nombreEmpleado: string
  dni: string
  diasVacacionesDesde: number | null
  diasVacacionesHasta: number | null
  horasGeneradasDesde: number | null
  horasGeneradasHasta: number | null
  enVacaciones: boolean | null
  deBaja: boolean | null
  working: boolean | null
}