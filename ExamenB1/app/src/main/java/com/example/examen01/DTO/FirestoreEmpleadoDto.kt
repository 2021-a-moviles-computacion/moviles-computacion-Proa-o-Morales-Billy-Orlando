package com.example.examen01.DTO

import android.os.Parcel
import android.os.Parcelable

class FirestoreEmpleadoDto (
    var id: String? = "",
    var dni: String? = "",
    var nombre: String? = "",
    var telefono: String? ="",
    var fechaNacimiento: String? = "",
    var latitud: String? = "",
    var longitud: String? = "",

    var idEmpresa: String? = ""): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
    ) {
    }

    override fun toString(): String {

        return "ID Empleado: ${id} -" +
                "DNI: ${dni} -" +
                "Nombre:  ${nombre} -" +
                "Telefono: ${telefono} -" +
                "fecha de nacimiento: ${fechaNacimiento} -" +
                "ID Empresa: ${idEmpresa} -" +
                "Latiud: ${latitud} -" +
                "Longitud ${longitud}"

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(dni)
        parcel.writeString(nombre)
        parcel.writeString(telefono)
        parcel.writeString(fechaNacimiento)
        parcel.writeString(idEmpresa)
        parcel.writeString(latitud)
        parcel.writeString(longitud)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FirestoreEmpleadoDto> {
        override fun createFromParcel(parcel: Parcel): FirestoreEmpleadoDto {
            return FirestoreEmpleadoDto(parcel)
        }

        override fun newArray(size: Int): Array<FirestoreEmpleadoDto?> {
            return arrayOfNulls(size)
        }
    }

}