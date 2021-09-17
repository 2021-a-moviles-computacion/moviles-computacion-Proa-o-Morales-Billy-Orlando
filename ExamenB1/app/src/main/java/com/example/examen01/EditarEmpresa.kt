package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.examen01.DTO.FirestoreEmpresaDto
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
                   "ruc" to txtRuc.text,
                   "razon-social" to txtRazonSocial.text,
                   "direccion" to txtDireccion.text,
                   "telefono-ingreso" to txtTelefono.text
               )

                val db = Firebase.firestore
                val referencia = db.collection("empresa")

                Log.i("EMPRESA SELECCIONADA ", "${idEmpresa}")
                db.collection("empresa")
                    .document(it.id.toString())
                    .set(actualizarUsuario)
                    .addOnSuccessListener {
                    Log.i("ACTUALIZAR", "EMPRESA ACTUALIZADA CONE XITO" )
                    }
               /* referencia
                    .get()
                    .addOnSuccessListener {
                        for(document in it)
                        {
                            var empresa = document.toObject(FirestoreEmpresaDto::class.java)
                            empresa.id = document.id
                            db.collection("empresa")
                                .document(identificadorEmpresa)
                                .set(actualizarUsuario)
                                .addOnSuccessListener {
                                    txtID.text = ""
                                    txtRuc.text = ""
                                    txtRazonSocial.text = ""
                                    txtDireccion.text = ""
                                    txtTelefono.text = ""
                                }
                        }
                    }

                */

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