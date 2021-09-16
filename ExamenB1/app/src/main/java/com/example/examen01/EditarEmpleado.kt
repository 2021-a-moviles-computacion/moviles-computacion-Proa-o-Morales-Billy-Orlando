package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.examen01.DTO.FirestoreEmpleadoDto

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

        txt_id_empleado.text = empleado!!.id.toString()
        txt_dni_ingerso.text = empleado!!.dni
        txt_nombre_ingreso.text = empleado!!.nombre
        txt_fecha_nac_ingreso.text = empleado!!.fechaNacimiento
        txt_telefono_ingreso.text = empleado!!.telefono.toString()



        /*
        val bton_editar_empleado = findViewById<ImageView>(R.id.img_view_guardar_emp_editar)
        bton_editar_empleado.setOnClickListener {
            if(baseDatos!= null){
              baseDatos.actualizarEmpleadoFormulario(
                  txt_dni_ingerso.text.toString(),
                  txt_nombre_ingreso.text.toString(),
                  txt_fecha_nac_ingreso.text.toString(),
                  txt_telefono_ingreso.text.toString().toInt(),
                  txt_id_empleado.text.toString().toInt()
              )
              abrirActividadEmpresa(EmpresaActivity1::class.java)
            }
        }
         */

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