package com.example.examen01

import android.os.Parcel
import android.os.Parcelable

class Empleado (
    var id: Int,
    var dni: String?,
    var nombre: String?,
    var telefono: Int,
    var fechaNacimiento: String?,
    var idEmpresa: Int
        ) :Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun toString(): String {
        return "ID Empleado: ${id} -" +
                "DNI: ${dni} -" +
                "Nombre:  ${nombre} -" +
                "Telefono: ${telefono} -" +
                "fecha de nacimiento: ${fechaNacimiento} -" +
                "ID Empresa: ${idEmpresa} "
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        parcel?.writeInt(id)
        parcel?.writeString(dni)
        parcel?.writeString(nombre)
        parcel?.writeInt(telefono)
        parcel?.writeString(fechaNacimiento)
        parcel?.writeInt(idEmpresa)
    }

    companion object CREATOR : Parcelable.Creator<Empleado> {
        override fun createFromParcel(parcel: Parcel): Empleado {
            return Empleado(parcel)
        }

        override fun newArray(size: Int): Array<Empleado?> {
            return arrayOfNulls(size)
        }
    }
}