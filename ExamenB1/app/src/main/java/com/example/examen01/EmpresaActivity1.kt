package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*

class EmpresaActivity1 : AppCompatActivity() {

    var posiconElementoSeleccionado = 0
    val baseDatos = BaseDatos(this)
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 400
    var adapter: ArrayAdapter<Empresa>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empresa1)

        val arregloEmpresas =  baseDatos.consultarEmpresas()
       // Log.i("base-datos", "listaEmpresas ${arregloEmpresas[4]}")

        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloEmpresas

        )

        val listViewEmpresa = findViewById<ListView>(R.id.list_view_epleados)
        listViewEmpresa.adapter = adapter

        val btnCrearEmpresa = findViewById<Button>(R.id.btn_crear)
        btnCrearEmpresa.setOnClickListener {
            abrirActiviad(CrearEmpresa::class.java)
        }

        registerForContextMenu(listViewEmpresa)
    }

    fun abrirActiviad(
        clase: Class<*>
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu,menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position

        posiconElementoSeleccionado = id

        Log.i("list-view","onCreate ${id}")
        Log.i("list-view","Usuario ${baseDatos.consultarEmpresas()[id].id}")

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var idElemento = baseDatos.consultarEmpresas()[posiconElementoSeleccionado]
        return when(item?.itemId){
            // Editar
            R.id.men_editar -> {
                abrirActividadporId(EditarEmpresa::class.java, idElemento)
                return true
            }
            //Eliinar
            R.id.men_eliminar -> {
                Log.i("list-view", "Eliminar ${idElemento.id}")
                Log.i("bdd", "${baseDatos.consultarEmpresas().toString()}")
                    baseDatos.eliminarEmpresa(idElemento.id)
                    adapter?.remove(adapter!!.getItem(posiconElementoSeleccionado))
                    adapter?.notifyDataSetChanged()
                return true
            }

            R.id.men_ver_empleados -> {
                abrirActividadporId(EmpleadoActivity::class.java, idElemento)
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirActividadporId(
        clase: Class<*>,
        empresa: Empresa
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("empresa", empresa)
        startActivityForResult(intentExplicito,CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

}