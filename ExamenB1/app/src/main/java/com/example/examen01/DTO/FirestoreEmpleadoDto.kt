package com.example.examen01.DTO

class FirestoreEmpleadoDto (
    var id: Int = 0,
    var dni: String = "",
    var nombre: String = "",
    var telefono: Int =0,
    var fechaNacimiento: String = "",
    var idEmpresa: Int = 0) {

    override fun toString(): String {

        return "ID Empleado: ${id} -" +
                "DNI: ${dni} -" +
                "Nombre:  ${nombre} -" +
                "Telefono: ${telefono} -" +
                "fecha de nacimiento: ${fechaNacimiento} -" +
                "ID Empresa: ${idEmpresa} "

    }

}