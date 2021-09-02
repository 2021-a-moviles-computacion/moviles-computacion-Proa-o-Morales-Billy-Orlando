package com.example.firebase.dto

 data class FirestoreUsuarioDTO (
    var uid: String = "",
    var email: String = "",
    var roles: ArrayList<String> = arrayListOf()) {

    }