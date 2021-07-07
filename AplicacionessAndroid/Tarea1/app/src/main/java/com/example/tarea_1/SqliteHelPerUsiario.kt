package com.example.tarea_1

import android.content.ClipDescription
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SqliteHelPerUsiario(
    contexto: Context?
) : SQLiteOpenHelper (
     contexto,
    "moviles",
    null,
    1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaUsuario =
            """
                CREATE TABLE USUARIO (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR (50),
                descripcion varchar (50))
                
            """.trimIndent()
        Log.i("base-datos", "Creando la tabla usuarios")
        db?.execSQL(scriptCrearTablaUsuario)
    }

    fun crearUsuarioFormulario(
        nombre: String,
        descripcion: String
    ): Boolean{

        val conexionExcritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descripcion", descripcion)
        val resultadoEscritura: Long = conexionExcritura
            .insert("USUARIO",
            null,
            valoresAGuardar)

        conexionExcritura.close()
        return if (resultadoEscritura.toInt() == -1) false else true
    }

    fun consultarUsuarioPorID(id: Int): UsuarioBDD{

        val scriptConsultarUsuario = "SELECT * FROM USUARIO WHERE ID = ${id}"
        val baseDatosLectura = readableDatabase
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            null
        )
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val usuarioEcontrado = UsuarioBDD(0,"", "")
        if(existeUsuario)
            do{
                val id = resultadoConsultaLectura.getInt(0)
                val nombre = resultadoConsultaLectura.getString(1)
                val descripcion =
                    resultadoConsultaLectura.getString(2)

                if (id!= null){
                    usuarioEcontrado.id = id
                    usuarioEcontrado.nombre = nombre
                    usuarioEcontrado.descripcion = descripcion
                    //arregloUsuario.add(usuarioEncontrado)
                }
            } while ( resultadoConsultaLectura.moveToNext())

            resultadoConsultaLectura.close()
            baseDatosLectura.close()
            return usuarioEcontrado
    }

    fun eliminarUsuarioFormulario( id: Int): Boolean{

        val conexionEscritura = writableDatabase
        val resultadoEliminacion  = conexionEscritura.delete(
            "USUARIO",
            "id=?",
            arrayOf(
                id.toString()
            )
        )

        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarUsuarioFormulario(
        nombre: String,
        descripcion: String,
        id: Int
    ): Boolean{

        val conexionEscritura = writableDatabase
        val valoresActualizar = ContentValues()
        valoresActualizar.put("nombre", nombre )
        valoresActualizar.put("descripcion", descripcion)
        val resultadoActualizacion = conexionEscritura
            .update(
                "USUARIO",
                valoresActualizar,
                "id=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if(resultadoActualizacion == -1) false else true
    }



    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }


}
