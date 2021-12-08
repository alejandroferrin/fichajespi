export class NuevoUsuario {
    numero: string
    nombreEmpleado: string
    email: string
    dni: string
    roles: string[]

    constructor(numero: string, nombreEmpleado: string, email: string, dni: string, roles: string[]) {
        this.numero = numero
        this.nombreEmpleado = nombreEmpleado
        this.email = email
        this.roles = roles
        this.dni = dni
    }

}
