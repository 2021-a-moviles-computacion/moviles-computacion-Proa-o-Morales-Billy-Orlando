package com.example.firebase.dto

class FirestoreProductoDTO (
    var nombreCProducto: String = "",
    var precioProductp: Double = 0.0 ,
    var uid: String = ""
        ) {

    override fun toString(): String {
        return nombreCProducto
    }

}