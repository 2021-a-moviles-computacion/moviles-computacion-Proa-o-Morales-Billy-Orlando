package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.examen01.DTO.FirestoreEmpresaDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditarEmpresa : AppCompatActivity() {


  //  val baseDatos = BaseDatos(this)
    var idEmpresa: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_empresa)

        val empresa = intent.getParcelableExtra<FirestoreEmpresaDto>("empresa")
        idEmpresa = empresa!!.id


        val txtID = findViewById<TextView>(R.id.txt_id_empresa_editar)
        val txtRuc = findViewById<TextView>(R.id.txt_ruc_editar)
        val txtRazonSocial = findViewById<TextView>(R.id.txt_razon_social_editar)
        val txtDireccion = findViewById<TextView>(R.id.txt_direccion_editar)
        val txtTelefono = findViewById<TextView>(R.id.txt_telefono_editar)

        txtID.text = empresa!!.id
        txtRuc.text = empresa!!.ruc
        txtRazonSocial.text = empresa!!.razonSocial
        txtDireccion.text = empresa!!.direccion
        txtTelefono.text = empresa!!.telefono

        val btn_editar_empresa = findViewById<ImageView>(R.id.img_view_guardar_editar)
        btn_editar_empresa.setOnClickListener {

               val actualizarUsuario = hashMapOf<String, Any>(
                   "ruc" to txtRuc.text.toString(),
                   "razon-social" to txtRazonSocial.text.toString(),
                   "direccion" to txtDireccion.text.toString(),
                   "telefono-ingreso" to txtTelefono.text.toString()
               )

                val db = FirebaseFirestore.getInstance()
                db.collection("empresa")
                    .document(idEmpresa!!)
                    .set(
                        actualizarUsuario
                    )
                    .addOnSuccessListener {
                        txtRuc.text = ""
                        txtRazonSocial.text = ""
                        txtTelefono.text = ""
                        txtDireccion.text = ""
                    }

            abrirActividadEmpresaId(EmpresaActivity1::class.java)
        }


        val btn_cancelar = findViewById<ImageView>(R.id.img_view_cancelar_editar)
        btn_cancelar.setOnClickListener {
            abrirActividadEmpresaId(EmpresaActivity1::class.java)
        }

    }


    fun abrirActividadEmpresaId(
        clase: Class<*>,
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }



}