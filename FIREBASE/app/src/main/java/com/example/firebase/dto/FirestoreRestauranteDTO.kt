package com.example.firebase.dto

class FirestoreRestauranteDTO (
    var nombreRestaurante: String = "",
    var uid: String =  ""
        ) {

    override fun toString(): String {
        return nombreRestaurante.toString()
    }

}