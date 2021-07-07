package com.example.tarea_1

import android.os.Parcel
import android.os.Parcelable

class Entrenador(
    val nombre: String?,
    val descripcion: String?,
    val liga: Liga?,
) :Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Liga::class.java.classLoader)
    ) {
    }

    override fun toString(): String {
        return "nombre: ${nombre} - descripcion ${descripcion} "
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(parcel: Parcel, flag: Int) {
        parcel?.writeString(nombre)
        parcel?.writeString(descripcion)
        parcel?.writeParcelable(liga, flag)
    }

    companion object CREATOR : Parcelable.Creator<Entrenador> {
        override fun createFromParcel(parcel: Parcel): Entrenador {
            return Entrenador(parcel)
        }

        override fun newArray(size: Int): Array<Entrenador?> {
            return arrayOfNulls(size)
        }
    }
}