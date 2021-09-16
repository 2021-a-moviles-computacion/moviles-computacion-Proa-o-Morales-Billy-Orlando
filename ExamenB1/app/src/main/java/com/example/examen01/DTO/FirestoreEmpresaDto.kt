package com.example.examen01.DTO

class FirestoreEmpresaDto (
    var id: String ="",
    var ruc: String = "",
    var razonSocial: String = "",
    var direccion: String = "",
    var telefono: String = "",
    ) {

    override fun toString(): String {
        return "ID: ${id} -" +
                "RUC: ${ruc} -" +
                " Razon Social:  ${razonSocial} -" +
                "Dirección: ${direccion} -" +
                "Telefono: ${telefono} "

    }

}