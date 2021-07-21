package com.example.tarea_1

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class UsoBaseDatos : AppCompatActivity() {

    val baseDatos = SqliteHelPerUsiario(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uso_base_datos)

        BaseDatos.tablaUsuario = SqliteHelPerUsiario(this)

        val botonInsertar = findViewById<Button>(R.id.bt_registrar)
        botonInsertar.setOnClickListener {
            crearUsuario()
        }

        val botonConsultar = findViewById<Button>(R.id.bt_consultar)
        botonConsultar.setOnClickListener {
            consultarUSuario()
        }

        val botonActualizar = findViewById<Button>(R.id.bt_modificar)
        botonActualizar.setOnClickListener {
            actualizarUsuario()
        }

        val botonEliminar = findViewById<Button>(R.id.bt_eliminar)
        botonEliminar.setOnClickListener {
            elimarUsuario()
        }
    }

    fun crearUsuario(){
        //val id = findViewById<TextView>(R.id.txt_id)

        val nombre = findViewById<TextView>(R.id.txt_nombre)
        val descripcion = findViewById<TextView>(R.id.txt_descripcion)
        //val id_ingreso= id.text.toString()
        val nombreIngreso = nombre.text.toString()
        Log.i("base-datos", "recibiendo el parametro nombre")
        val descripcionIngreso = descripcion.text.toString()
        Log.i("base-datos", "recibiendo el parametro descripcion")

        if(BaseDatos.tablaUsuario!= null) {
            if( !nombreIngreso.isEmpty() && !descripcionIngreso.isEmpty()){
                Log.i("base-datos", "ingresando al if")
                baseDatos.crearUsuarioFormulario(nombreIngreso, descripcionIngreso)
                Log.i(" base-datos", "Usuario creado con exito ${nombreIngreso}")

                nombre.text = ""
                descripcion.text = ""

            } else {
                Log.i("base-datos", "llene los campos")
            }
        }

    }

    fun consultarUSuario (){
        val id = findViewById<TextView>(R.id.txt_id)
        val idIngreso = id.text.toString()
        val nombre = findViewById<TextView>(R.id.txt_nombre)
        val descripcion = findViewById<TextView>(R.id.txt_descripcion)

        if(!idIngreso.isEmpty()){
            val usuaroEncontrado = baseDatos.consultarUsuarioPorID(idIngreso.toInt())
            Log.i("base-datos", "${usuaroEncontrado.nombre}")
            nombre.text = usuaroEncontrado.nombre
            descripcion.text = usuaroEncontrado.descripcion
        }
        else {
            Log.i("base-datos", "Debe ingresar un identificador ")
        }
    }

    fun actualizarUsuario(){
        val id = findViewById<TextView>(R.id.txt_id)
        val idIngreso = id.text.toString()
        val nombre = findViewById<TextView>(R.id.txt_nombre)
        val descripcion = findViewById<TextView>(R.id.txt_descripcion)

        baseDatos.actualizarUsuarioFormulario(nombre.text.toString(), descripcion.text.toString(), idIngreso.toInt())
        Log.i("base-datos", "usuario modificado con exito")

        nombre.text = ""
        descripcion.text = ""
        id.text = ""
    }

    fun elimarUsuario(){
        val id = findViewById<TextView>(R.id.txt_id)
        val idIngreso = id.text.toString()
        val nombre = findViewById<TextView>(R.id.txt_nombre)
        val descripcion = findViewById<TextView>(R.id.txt_descripcion)

        baseDatos.eliminarUsuarioFormulario(idIngreso.toInt())
        Log.i("base-datos", "usuario eliminado con exito")

        nombre.text = ""
        descripcion.text = ""
        id.text = ""
    }


    
}