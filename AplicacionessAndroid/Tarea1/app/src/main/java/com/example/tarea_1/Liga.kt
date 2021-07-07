package com.example.tarea_1

import android.os.Parcel
import android.os.Parcelable

class Liga (
    val nombre: String?,
    val descripcion: String?) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "nombre: ${nombre} - descripcion ${descripcion} "
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(descripcion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Liga> {
        override fun createFromParcel(parcel: Parcel): Liga {
            return Liga(parcel)
        }

        override fun newArray(size: Int): Array<Liga?> {
            return arrayOfNulls(size)
        }
    }

}