package com.example.examen01

import android.os.Parcel
import android.os.Parcelable

class Empresa (
    var id: Int,
    var ruc: String?,
    var razonSocial: String?,
    var direccion: String?,
    var telefono: Int,
    //val empleados: Empleado?
     ) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
       // parcel.readParcelable(Empleado::class.java.classLoader)
    ) {
    }

    override fun toString(): String {
            return "ID: ${id} -" +
                    "RUC: ${ruc} -" +
                    " Razon Social:  ${razonSocial} -" +
                    "Dirección: ${direccion} -" +
                    "Telefono: ${telefono} "

    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(parcel: Parcel?, flag: Int) {
        parcel?.writeInt(id)
        parcel?.writeString(ruc)
        parcel?.writeString(razonSocial)
        parcel?.writeString(direccion)
        parcel?.writeInt(telefono)
        //parcel?.writeParcelable(empleados, flag)
    }

    companion object CREATOR : Parcelable.Creator<Empresa> {
        override fun createFromParcel(parcel: Parcel): Empresa {
            return Empresa(parcel)
        }

        override fun newArray(size: Int): Array<Empresa?> {
            return arrayOfNulls(size)
        }
    }
}