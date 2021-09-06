package com.example.firebase.dto

class OrdenDTO (
    val nombreProductoOrden: String,
    val precioUnitario: Double,
    val cantidad: Int,
    val totalUnitario: Double
        )
    {

        override fun toString(): String {
            return "Cant. \t \t \t des. \t \t \t Prec. Unit \t \t \t  Prec. Tot. \n" +
                    "${cantidad} \t \t \t \t \t ${nombreProductoOrden} \t \t \t ${precioUnitario} \t \t \t \t\t \t \t ${totalUnitario}"

        }
    }