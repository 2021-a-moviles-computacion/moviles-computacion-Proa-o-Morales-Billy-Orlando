package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.examen01.DTO.FirestoreEmpresaDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearEmpleado : AppCompatActivity() {

    var posicionItemSelecionado = 0
    //val baseDatos = BaseDatos(this)
    var idEempresa: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_editar_empleado)

        val empresa = intent.getParcelableExtra<FirestoreEmpresaDto>("empresa")

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
        val latEmpleado = findViewById<TextView>(R.id.txt_latitud)
        val longEmpleado = findViewById<TextView>(R.id.txt_longitud)

        val dniIngreso = dni.text.toString()
        val nombreIngreso = nombre.text.toString()
        val fechaNacIngreso = fechaNac.text.toString()
        val telefonoIngreso = telefonoEmpl.text.toString()
        val latEmpleadoIngreso = latEmpleado.text.toString()
        val longempleadoIngreso = longEmpleado.text.toString()

        val idEmpresaIngreso = idEempresa.toString()

        val nuevoEmpresa = hashMapOf<String, Any>(
            "dni" to dniIngreso,
            "nombre-empleado" to nombreIngreso,
            "fecha-nacimiento" to fechaNacIngreso,
            "telefono-empleado" to telefonoIngreso,
            "id-empresa" to idEmpresaIngreso,
            "latitud" to latEmpleadoIngreso,
            "longitud" to longempleadoIngreso,
        )
        val db = Firebase.firestore
        val referencia = db.collection("empleado")

        referencia
            .add(nuevoEmpresa)
            .addOnSuccessListener {
                dni.text = ""
                nombre.text = ""
                fechaNac.text = ""
                telefonoEmpl.text = ""
                idEmpresa.text = ""
                latEmpleado.text = ""
                longEmpleado.text = ""

                //abrirActividad(EmpresaActivity1::class.java)
            }
            .addOnFailureListener {
                Log.i("firestore-empresa", "no se pudo cargar los datos al firestore ")
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