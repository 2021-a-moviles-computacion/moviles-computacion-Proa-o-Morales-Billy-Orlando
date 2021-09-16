package com.example.examen01.DTO

import android.os.Parcel
import android.os.Parcelable

class FirestoreEmpresaDto (
    var id: String? ="",
    var ruc: String? = "",
    var razonSocial: String? = "",
    var direccion: String? = "",
    var telefono: String? = "",
    ): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel?.readString(),
        parcel?.readString(),
        parcel?.readString(),
        parcel?.readString()
    ) {
    }

    override fun toString(): String {
        return "ID: ${id} -" +
                "RUC: ${ruc} -" +
                " Razon Social:  ${razonSocial} -" +
                "Dirección: ${direccion} -" +
                "Telefono: ${telefono} "

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel?.writeString(id)
        parcel?.writeString(ruc)
        parcel?.writeString(razonSocial)
        parcel?.writeString(direccion)
        parcel?.writeString(telefono)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FirestoreEmpresaDto> {
        override fun createFromParcel(parcel: Parcel): FirestoreEmpresaDto {
            return FirestoreEmpresaDto(parcel)
        }

        override fun newArray(size: Int): Array<FirestoreEmpresaDto?> {
            return arrayOfNulls(size)
        }
    }

}