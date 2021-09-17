package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.examen01.DTO.FirestoreEmpleadoDto
import com.google.firebase.firestore.FirebaseFirestore

class EditarEmpleado : AppCompatActivity() {

    //val baseDatos = BaseDatos(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_empleado)

        val empleado = intent.getParcelableExtra<FirestoreEmpleadoDto>("empleado")

        Log.i("list-view", "Empleado pasado ${empleado!!}")

        val txt_dni_ingerso = findViewById<TextView>(R.id.txt_dni_editar)
        val txt_nombre_ingreso = findViewById<TextView>(R.id.txt_nombre_editar)
        val txt_fecha_nac_ingreso = findViewById<TextView>(R.id.txt_fec_nac_editar)
        val txt_telefono_ingreso = findViewById<TextView>(R.id.txt_telefono_emp_editar)
        val txt_id_empleado = findViewById<TextView>(R.id.txt_id_empleado_editar)
        val txt_longitud_empr = findViewById<TextView>(R.id.txt_longitud_emp)
        val txt_latitud_empr = findViewById<TextView>(R.id.txt_latitud_emp)
        val txt_id_empresa_emp = findViewById<TextView>(R.id.txt_id_empresa_empl)


        txt_id_empleado.text = empleado!!.id.toString()
        txt_dni_ingerso.text = empleado!!.dni
        txt_nombre_ingreso.text = empleado!!.nombre
        txt_fecha_nac_ingreso.text = empleado!!.fechaNacimiento
        txt_telefono_ingreso.text = empleado!!.telefono.toString()
        txt_longitud_empr.text = empleado!!.longitud.toString()
        txt_latitud_empr.text = empleado!!.latitud.toString()
        txt_id_empresa_emp.text = empleado!!.idEmpresa.toString()

        val bton_editar_empleado = findViewById<ImageView>(R.id.img_view_guardar_emp_editar)
        bton_editar_empleado.setOnClickListener {

            val nuevoEmpresa = hashMapOf<String, Any>(
                "dni" to txt_dni_ingerso.text.toString(),
                "nombre-empleado" to txt_nombre_ingreso.text.toString(),
                "fecha-nacimiento" to txt_fecha_nac_ingreso.text.toString(),
                "telefono-empleado" to txt_telefono_ingreso.text.toString(),
                "latitud" to txt_latitud_empr.text.toString(),
                "longitud" to txt_longitud_empr.text.toString(),
                "id-empresa" to txt_id_empresa_emp.text.toString(),
            )

            val db = FirebaseFirestore.getInstance()
            db.collection("empleado")
                .document(empleado.id!!)
                .set(
                    nuevoEmpresa
                )
                .addOnSuccessListener {
                    txt_dni_ingerso.text = ""
                    txt_nombre_ingreso.text = ""
                    txt_telefono_ingreso.text = ""
                    txt_fecha_nac_ingreso.text = ""
                }

            abrirActividadEmpresa(EmpresaActivity1::class.java)

        }


        val btn_canclar_editar = findViewById<ImageView>(R.id.img_view_cancelar_emp_editar)
        btn_canclar_editar.setOnClickListener{
            abrirActividadEmpresa(EmpresaActivity1::class.java)
        }


    }

    fun abrirActividadEmpresa(
        clase: Class<*>,
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }
}