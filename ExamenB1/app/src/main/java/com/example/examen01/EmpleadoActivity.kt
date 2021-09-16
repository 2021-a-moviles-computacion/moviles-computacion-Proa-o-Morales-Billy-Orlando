package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*

class EmpleadoActivity : AppCompatActivity() {

    var posiconElementoSeleccionado = 0
    //val baseDatos = BaseDatos(this)
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 400
    var idEempresa = 0
    var idEmpleadoSeleccionado = 0
    var adpatador: ArrayAdapter<Empleado>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empleado)

        val empresa = intent.getParcelableExtra<Empresa>("empresa")

        idEempresa = empresa!!.id

        val idEmpresa = findViewById<TextView>(R.id.txt_nombre_empresa_ven_empl)
        idEmpresa.text = empresa.razonSocial

      //  val arregloEmpleados =  baseDatos.consultarEmpleadoPorIDEmpresa(idEempresa)

        adpatador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
          //  arregloEmpleados

        )

        val listViewEmpleados = findViewById<ListView>(R.id.list_view_epleados)
        listViewEmpleados.adapter = adpatador

        registerForContextMenu(listViewEmpleados)

        val btNuevoEmpleado = findViewById<Button>(R.id.btn_nuevo_empleado)
        btNuevoEmpleado.setOnClickListener {
            abrirActiviad(CrearEmpleado::class.java, empresa!!)
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

    /*
    override fun onContextItemSelected(item: MenuItem): Boolean {
        var empleadoSel = baseDatos.consultarEmpleadoPorIDEmpresa(idEempresa)[posiconElementoSeleccionado]
        return when(item?.itemId){
            // Editar
            R.id.men_editar_empleado -> {
                Log.i("list-view", "Editarr ${empleadoSel} ")
                abrirActiviadEmpleado(EditarEmpleado::class.java, empleadoSel)
                return true
            }
            //Eliinar
            R.id.men_eliminar_empleado -> {
                Log.i("list-view", "Eliminar ${empleadoSel} ")
                baseDatos.eliminarEmpleadoPorId(empleadoSel.id)
                adpatador?.remove(adpatador!!.getItem(posiconElementoSeleccionado))
                adpatador?.notifyDataSetChanged()
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

     */

    fun abrirActiviadEmpleado(
        clase: Class<*>,
        empleado: Empleado
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
        empresa: Empresa
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("empresa", empresa)
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }


}