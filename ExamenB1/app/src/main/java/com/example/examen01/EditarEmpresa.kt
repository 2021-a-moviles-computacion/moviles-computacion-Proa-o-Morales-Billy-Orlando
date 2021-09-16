package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class EditarEmpresa : AppCompatActivity() {


  //  val baseDatos = BaseDatos(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_empresa)

        val empresa = intent.getParcelableExtra<Empresa>("empresa")

        val txtID = findViewById<TextView>(R.id.txt_id_empresa_editar)
        val txtRuc = findViewById<TextView>(R.id.txt_ruc_editar)
        val txtRazonSocial = findViewById<TextView>(R.id.txt_razon_social_editar)
        val txtDireccion = findViewById<TextView>(R.id.txt_direccion_editar)
        val txtTelefono = findViewById<TextView>(R.id.txt_telefono_editar)

        txtID.text = empresa!!.id.toString()
        txtRuc.text = empresa!!.ruc
        txtRazonSocial.text = empresa!!.razonSocial
        txtDireccion.text = empresa!!.direccion
        txtTelefono.text = empresa!!.telefono.toString()

        /*
        val btn_editar_empresa = findViewById<ImageView>(R.id.img_view_guardar_editar)
        btn_editar_empresa.setOnClickListener {
            if(empresa!= null){
                baseDatos.actualizarEmpresaFormulario(
                    txtRuc.text.toString(),
                    txtRazonSocial.text.toString(),
                    txtDireccion.text.toString(),
                    txtTelefono.text.toString().toInt(),
                    txtID.text.toString().toInt()
                )
                txtID.text = ""
                txtRuc.text = ""
                txtRazonSocial.text = ""
                txtDireccion.text = ""
                txtTelefono.text = ""
            }
            abrirActividadEmpresaId(EmpresaActivity1::class.java)
        }

         */

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