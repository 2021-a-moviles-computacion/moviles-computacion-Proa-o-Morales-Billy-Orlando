package com.example.examen01

class EjemploEmpresa {

    companion object{
        val arrgloEntrenador = arrayListOf<Empresa>()

        init {

            //val empleado : Empleado = Empleado("1725996365", "Billy", 3441319, "01-10-1997")

            arrgloEntrenador.add(
                Empresa(0,"1709492159001", "KMSOLUTIONS", "El Condado", 2497782)
            )

            arrgloEntrenador.add(
                Empresa(1, "1709492159001", "KMSOLUTIONS", "El Condado", 2497782)
            )

            arrgloEntrenador.add(
                Empresa(2, "1709492159001", "KMSOLUTIONS", "El Condado", 2497782)
            )

        }
    }

}