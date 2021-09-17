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
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.protobuf.DescriptorProtos

class EmpleadoActivity : AppCompatActivity() {

    var posiconElementoSeleccionado = 0
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 400
    var idEempresa: String? = ""
    var empresaSeleccioanda: FirestoreEmpleadoDto? = null
    var adpatador: ArrayAdapter<FirestoreEmpleadoDto>? = null
    var arregloEmpleados = arrayListOf<FirestoreEmpleadoDto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empleado)

        //CARGAR INFORMACION DE EMPRESA DEL EMPLEADO
        val empresa = intent.getParcelableExtra<FirestoreEmpresaDto>("empresa")
        idEempresa = empresa!!.id
        val idEmpresa = findViewById<TextView>(R.id.txt_nombre_empresa_ven_empl)
        idEmpresa.text = empresa.razonSocial


        cargarEmpleado(idEempresa!!)
        adpatador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloEmpleados

        )

        val listViewEmpleados = findViewById<ListView>(R.id.list_view_epleados)
        listViewEmpleados.adapter = adpatador
        listViewEmpleados.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long)
            {
                empresaSeleccioanda = arregloEmpleados[position]
            }

            override fun onNothingSelected(
                p0: AdapterView<*>?)
            {
                Log.i("firestore-empresa", "No ha seleccionado ningun item")
            }
        }


        registerForContextMenu(listViewEmpleados)

        val btNuevoEmpleado = findViewById<Button>(R.id.btn_nuevo_empleado)
        btNuevoEmpleado.setOnClickListener {
            abrirActiviad(CrearEmpleado::class.java, empresa!!)
        }

    }

    fun cargarEmpleado(idEmpresa: String){
        val db = Firebase.firestore
        val referencia = db.collection("empleado")

        referencia
            .whereEqualTo("id-empresa", idEmpresa)
            .get()
            .addOnSuccessListener {
                for (document in it){
                   // Log.i("EMPRESA-SELECCIONADA-CARGA ", "${idEmpresa}")
                    var empleado = document.toObject(FirestoreEmpleadoDto::class.java)
                    empleado!!.id = document.id
                    empleado!!.dni = document.get("dni").toString()
                    empleado!!.fechaNacimiento = document.get("fecha-nacimiento").toString()
                    empleado!!.nombre = document.get("nombre-empleado").toString()
                    empleado!!.telefono = document.get("telefono-empleado").toString()
                    empleado!!.latitud = document.get("latitud").toString()
                    empleado!!.longitud = document.get("longitud").toString()

                    arregloEmpleados.add(empleado)
                    adpatador?.notifyDataSetChanged()

                }
            }
            .addOnFailureListener {
                Log.i("NO INGRESO AL ADD ON SUCCES FILE LISTENER  ", "${idEmpresa}")
            }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menuempleado,menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position

        posiconElementoSeleccionado = id

        Log.i("list-view","onCreate ${id}")

    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        var empleadoSel = arregloEmpleados[posiconElementoSeleccionado]
        return when(item?.itemId){
            // Editar
            R.id.men_editar_empleado -> {
                Log.i("list-view", "Editar ${empleadoSel} ")
                abrirActiviadEmpleado(EditarEmpleado::class.java, empleadoSel)
                return true
            }
            //Eliinar
            R.id.men_eliminar_empleado -> {
                Log.i("list-view", "Eliminar ${empleadoSel} ")
                val db = FirebaseFirestore.getInstance()
                db.collection("empleado")
                    .document(empleadoSel.id!!)
                    .delete()
                    .addOnSuccessListener {
                        adpatador?.remove(adpatador!!.getItem(posiconElementoSeleccionado))
                        adpatador?.notifyDataSetChanged()
                    }

                return true
            }
            R.id.men_ver_mapa -> {
                abrirActiviadEmpleado(Mapas::class.java, empleadoSel)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirActiviadEmpleado(
        clase: Class<*>,
        empleado: FirestoreEmpleadoDto
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("empleado", empleado)
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

    fun abrirActiviad(
        clase: Class<*>,
        empresa: FirestoreEmpresaDto
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("empresa", empresa)
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

    fun abrirActiviadSola(
        clase: Class<*>
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }


}