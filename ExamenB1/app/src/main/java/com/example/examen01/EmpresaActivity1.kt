package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.examen01.DTO.FirestoreEmpleadoDto
import com.example.examen01.DTO.FirestoreEmpresaDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EmpresaActivity1 : AppCompatActivity() {

    var empresaSeleccioanda: FirestoreEmpresaDto? = null
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 400
    var adapter: ArrayAdapter<FirestoreEmpresaDto>? = null
    var arregloEmpresas = arrayListOf<FirestoreEmpresaDto>()
    var  posiconElementoSeleccionado: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empresa1)

        cargarEmpresas()
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloEmpresas
        )

        val listViewEmpresa = findViewById<ListView>(R.id.list_view_epleados)
        listViewEmpresa.adapter = adapter
        listViewEmpresa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long)
                {
                    empresaSeleccioanda = arregloEmpresas[position]
                }

            override fun onNothingSelected(
                p0: AdapterView<*>?)
                {
                    Log.i("firestore-empresa", "No ha seleccionado ningun item")
                }
        }

        val btnCrearEmpresa = findViewById<Button>(R.id.btn_crear)
        btnCrearEmpresa.setOnClickListener {
            abrirActiviad(CrearEmpresa::class.java)
        }

        registerForContextMenu(listViewEmpresa)
    }

    fun cargarEmpresas(){
        val db = Firebase.firestore
        val referencia = db.collection("empresa")

        referencia
            .get()
            .addOnSuccessListener {
                for (document in it){
                    var empresa = document.toObject(FirestoreEmpresaDto::class.java)
                    empresa!!.id = document.id
                    empresa!!.razonSocial = document.get("razon-social").toString()
                    empresa!!.ruc = document.get("ruc").toString()
                    empresa!!.direccion = document.get("direccion").toString()
                    empresa!!.telefono = document.get("telefono-ingreso").toString()

                    arregloEmpresas.add(empresa)
                    adapter?.notifyDataSetChanged()
                }
            }
            .addOnFailureListener {
                Log.i("firebase-firestore", "no se cargaron los datos al listView")
            }
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

       // Log.i("list-view","onCreate ${id}")
       // Log.i("list-view","Usuario ${baseDatos.consultarEmpresas()[id].id}")

    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
       var idElemento = arregloEmpresas[posiconElementoSeleccionado]
        return when(item?.itemId){
            // Editar
            R.id.men_editar -> {
                abrirActividadporId(EditarEmpresa::class.java, idElemento)
                return true
            }

            /*
            //Eliinar
            R.id.men_eliminar -> {
                Log.i("list-view", "Eliminar ${idElemento.id}")
                Log.i("bdd", "${baseDatos.consultarEmpresas().toString()}")
                    baseDatos.eliminarEmpresa(idElemento.id)
                    adapter?.remove(adapter!!.getItem(posiconElementoSeleccionado))
                    adapter?.notifyDataSetChanged()
                return true
            }

             */

            R.id.men_ver_empleados -> {
                abrirActividadporId(EmpleadoActivity::class.java, idElemento)
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }



    fun abrirActividadporId(
        clase: Class<*>,
        empresa: FirestoreEmpresaDto
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("empresa", empresa)
        startActivityForResult(intentExplicito,CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

}