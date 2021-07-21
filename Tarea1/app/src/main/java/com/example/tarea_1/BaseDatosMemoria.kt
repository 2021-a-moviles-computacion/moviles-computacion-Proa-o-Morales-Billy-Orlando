package com.example.tarea_1

class BaseDatosMemoria {

    companion object{
        val arrgloEntrenador = arrayListOf<Entrenador>()
        init {
            arrgloEntrenador.add(
                Entrenador("Billy", "entrenador a", null )
            )

            arrgloEntrenador.add(
                Entrenador("Carlos", "entrenador b", null)
            )

            arrgloEntrenador.add(
                Entrenador("Cristian", "entrenador c", null)
            )
        }
    }
}