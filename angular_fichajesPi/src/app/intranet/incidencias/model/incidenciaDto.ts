export interface IncidenciaDto {
  descripcion: string 
  diaDesde: string 
  diaHasta: string 
  explicacion: string | null
  resuelta: boolean | null

  usuarioEmail: string
  usuarioNumero: string
  usuarioNombre: string
  usuarioDni: string
}