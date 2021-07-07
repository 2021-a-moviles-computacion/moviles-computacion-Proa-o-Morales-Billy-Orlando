package com.example.tarea_1

import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import java.util.ArrayList
import com.example.tarea_1.ListView as ListView1

class ListView : AppCompatActivity() {

    var posiconElementoSeleccionado = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        //arreglos con los elementos que se van a ostrar en l list view
        val arregloNumeros = BaseDatosMemoria.arrgloEntrenador

        //adaptador que se encarga de cargar los elementos de nuestro arreglo
        //al lst view cargado manualmente
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloNumeros
        )

        //identificamos el list view de la interfaz grafica y cargamos los elementos
        val listViewEjemplo = findViewById<ListView>(R.id.list_view_ejemplo)
        listViewEjemplo.adapter = adaptador

        listViewEjemplo
           .setOnItemLongClickListener { adapterView, view, posicion, id ->

                Log.i("list-view", "Dio click ${posicion}")

               val builder = AlertDialog.Builder(this)

               builder.setTitle("titulo")
               builder.setMessage("Mensaje")

               val seleccionUsuario = booleanArrayOf(
                   true,
                   false,
                   false
               )

               val opciones = resources.getStringArray(R.array.string_array_opiones_dialogo)


               builder.setMultiChoiceItems(
                   opciones,
                   seleccionUsuario,
                   { dialog, which, isChecked ->
                       Log.i("list-view","${which} ${isChecked}"  )
                   }
               )

               builder.setPositiveButton(
                   "SI",
                   DialogInterface.OnClickListener{ dialog, which ->
                       Log.i("list-view","Si")
                   }
               )

               builder.setNegativeButton(
                   "NO",
                   null
               )
               val dialogo = builder.create()
               dialogo.show()
                return@setOnItemLongClickListener true
            }


        val botonAnadirNumero = findViewById<Button>(R.id.bt_aniadir_numero)
        botonAnadirNumero.setOnClickListener{
            anadirItemsListView(
                Entrenador("Prueba", "entrenador Prueba ",null),
                arregloNumeros,
                adaptador
            )
        }
        //registramos para el menu contextual
        //registerForContextMenu(listViewEjemplo)



    }

    override fun onCreateContextMenu(

        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position

        posiconElementoSeleccionado = id
        Log.i("list-view", "List view ${posiconElementoSeleccionado}")
        Log.i("list-view", "Entrenador ${BaseDatosMemoria.arrgloEntrenador[id]}")
    }

    fun anadirItemsListView(
        valor: Entrenador,
        arreglo: ArrayList<Entrenador>,
        adaptador: ArrayAdapter<Entrenador> //mportante lanzar un adaptador que nos notifique el cambio en la lista
    ){
        arreglo.add(valor)
        adaptador.notifyDataSetChanged()
    }

    //MENU PARA FUNCIONMIENTO DE LAS OPCIOES EDITAR Y ELIMINAR
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item?.itemId){
            // Editar
                R.id.menu_editar -> {
                    Log.i("list-view", "Editar ${BaseDatosMemoria.arrgloEntrenador[
                            posiconElementoSeleccionado]} ")
                    return true
                }
            //Eliinar
            R.id.menu_elimiar -> {
                Log.i("list-view", "Eliminar ${BaseDatosMemoria.arrgloEntrenador[
                        posiconElementoSeleccionado]} ")
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

}