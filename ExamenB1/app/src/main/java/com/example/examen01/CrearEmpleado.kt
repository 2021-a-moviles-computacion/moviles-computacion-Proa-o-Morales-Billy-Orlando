package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

class CrearEmpleado : AppCompatActivity() {

    var posicionItemSelecionado = 0
    val baseDatos = BaseDatos(this)
    var idEempresa = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_editar_empleado)

        val empresa = intent.getParcelableExtra<Empresa>("empresa")

        idEempresa = empresa!!.id

        val idEmpresa = findViewById<TextView>(R.id.txt_id_empresa_cre_empl)
        idEmpresa.text = empresa.id.toString()


        val bt_crear_empleado = findViewById<ImageView>(R.id.img_view_guardar_emp)

        bt_crear_empleado.setOnClickListener {
            crearEmpleado()
        }

        val btn_cancelar = findViewById<ImageView>(R.id.img_view_cancelar_emp)
        btn_cancelar.setOnClickListener {
            abrirActividad(EmpresaActivity1::class.java)
        }



    }


    fun crearEmpleado(){
        val dni = findViewById<TextView>(R.id.txt_dni)
        val nombre = findViewById<TextView>(R.id.txt_nombre)
        val fechaNac = findViewById<TextView>(R.id.txt_fec_nac)
        val telefonoEmpl = findViewById<TextView>(R.id.txt_telefono_emp)
        val idEmpresa = findViewById<TextView>(R.id.txt_id_empresa_cre_empl)

        val dniIngreso = dni.text.toString()
        val nombreIngreso = nombre.text.toString()
        val fechaNacIngreso = fechaNac.text.toString()
        val telefonoIngreso = telefonoEmpl.text.toString()

        val idEmpresaIngreso = idEempresa.toString()


        if(baseDatos!= null) {
            if( !nombreIngreso.isEmpty() && !fechaNacIngreso.isEmpty() && !fechaNacIngreso.isEmpty() && !telefonoIngreso.isEmpty() ){

                baseDatos.crearEmpleadoFormulario(dniIngreso,nombreIngreso, fechaNacIngreso, telefonoIngreso.toInt(), idEmpresaIngreso.toInt())

                Log.i("base-datos", "empresa ingresado ${nombreIngreso}")
                dni.text = ""
                nombre.text = ""
                fechaNac.text = ""
                telefonoEmpl.text = ""
                idEmpresa.text = ""

                abrirActividad(EmpresaActivity1::class.java)

            } else {
                Log.i("base-datos", "llene los campos")
            }
        }
    }

    fun abrirActividad(
        clase: Class<*>,
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }



}